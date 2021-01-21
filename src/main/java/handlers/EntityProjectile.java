package handlers;

import java.util.UUID;

import javax.annotation.Nullable;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EntityProjectile extends Entity implements IProjectile {

	private int ticksInGround;
	protected boolean inGround;
	protected int timeInGround;
	private int ticksInAir;
	public UUID shootingEntity;
	private BlockState inBlockState;
	private IntOpenHashSet hashSet;

	public EntityProjectile(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);

	}

	/**
	 * Checks if the entity is in range to render.
	 */
	@OnlyIn(Dist.CLIENT)
	public boolean isInRangeToRenderDist(double distance) {
		double d0 = this.getBoundingBox().getAverageEdgeLength() * 10.0D;
		if (Double.isNaN(d0)) {
			d0 = 1.0D;
		}

		d0 = d0 * 64.0D * getRenderDistanceWeight();
		return distance < d0 * d0;
	}

	public void shoot(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) {
		float f = -MathHelper.sin(yaw * ((float)Math.PI / 180F)) * MathHelper.cos(pitch * ((float)Math.PI / 180F));
		float f1 = -MathHelper.sin(pitch * ((float)Math.PI / 180F));
		float f2 = MathHelper.cos(yaw * ((float)Math.PI / 180F)) * MathHelper.cos(pitch * ((float)Math.PI / 180F));
		this.shoot((double)f, (double)f1, (double)f2, velocity, inaccuracy);
		this.setMotion(this.getMotion().add(shooter.getMotion().x, shooter.onGround ? 0.0D : shooter.getMotion().y, shooter.getMotion().z));
	}

	/**
	 * Similar to setArrowHeading, it's point the throwable entity to a x, y, z direction.
	 */
	public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
		Vec3d vec3d = (new Vec3d(x, y, z)).normalize().add(this.rand.nextGaussian() * (double)0.0075F * (double)inaccuracy, this.rand.nextGaussian() * (double)0.0075F * (double)inaccuracy, this.rand.nextGaussian() * (double)0.0075F * (double)inaccuracy).scale((double)velocity);
		this.setMotion(vec3d);
		float f = MathHelper.sqrt(func_213296_b(vec3d));
		this.rotationYaw = (float)(MathHelper.atan2(vec3d.x, vec3d.z) * (double)(180F / (float)Math.PI));
		this.rotationPitch = (float)(MathHelper.atan2(vec3d.y, (double)f) * (double)(180F / (float)Math.PI));
		this.prevRotationYaw = this.rotationYaw;
		this.prevRotationPitch = this.rotationPitch;
		this.ticksInGround = 0;
	}

	/**
	 * Sets a target for the client to interpolate towards over the next few ticks
	 */
	@OnlyIn(Dist.CLIENT)
	public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
		this.setPosition(x, y, z);
		this.setRotation(yaw, pitch);
	}

	/**
	 * Updates the entity motion clientside, called by packets from the server
	 */
	@OnlyIn(Dist.CLIENT)
	public void setVelocity(double x, double y, double z) {
		this.setMotion(x, y, z);
		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt(x * x + z * z);
			this.rotationPitch = (float)(MathHelper.atan2(y, (double)f) * (double)(180F / (float)Math.PI));
			this.rotationYaw = (float)(MathHelper.atan2(x, z) * (double)(180F / (float)Math.PI));
			this.prevRotationPitch = this.rotationPitch;
			this.prevRotationYaw = this.rotationYaw;
			this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			this.ticksInGround = 0;
		}

	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void tick() {
		super.tick();
		boolean flag = this.noClip;
		Vec3d vec3d = this.getMotion();
		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt(func_213296_b(vec3d));
			this.rotationYaw = (float)(MathHelper.atan2(vec3d.x, vec3d.z) * (double)(180F / (float)Math.PI));
			this.rotationPitch = (float)(MathHelper.atan2(vec3d.y, (double)f) * (double)(180F / (float)Math.PI));
			this.prevRotationYaw = this.rotationYaw;
			this.prevRotationPitch = this.rotationPitch;
		}

		BlockPos blockpos = new BlockPos(this.posX, this.posY, this.posZ);
		BlockState blockstate = this.world.getBlockState(blockpos);
		if (!blockstate.isAir(this.world, blockpos) && !flag) {
			VoxelShape voxelshape = blockstate.getCollisionShape(this.world, blockpos);
			if (!voxelshape.isEmpty()) {
				for(AxisAlignedBB axisalignedbb : voxelshape.toBoundingBoxList()) {
					if (axisalignedbb.offset(blockpos).contains(new Vec3d(this.posX, this.posY, this.posZ))) {
						this.inGround = true;
						break;
					}
				}
			}
		}

		if (this.isWet()) {
			this.extinguish();
		}

		if (this.inGround && !flag) {
			if (this.inBlockState != blockstate && this.world.areCollisionShapesEmpty(this.getBoundingBox().grow(0.06D))) {
				this.inGround = false;
				this.setMotion(vec3d.mul((double)(this.rand.nextFloat() * 0.2F), (double)(this.rand.nextFloat() * 0.2F), (double)(this.rand.nextFloat() * 0.2F)));
				this.ticksInGround = 0;
				this.ticksInAir = 0;
			} else if (!this.world.isRemote) {
				this.tryDespawn();
			}

			++this.timeInGround;
		} else {
			this.timeInGround = 0;
			++this.ticksInAir;
			Vec3d vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
			Vec3d vec3d2 = vec3d1.add(vec3d);
			RayTraceResult raytraceresult = this.world.rayTraceBlocks(new RayTraceContext(vec3d1, vec3d2, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this));
			if (raytraceresult.getType() != RayTraceResult.Type.MISS) {
				vec3d2 = raytraceresult.getHitVec();
			}

			while(!this.removed) {
				EntityRayTraceResult entityraytraceresult = this.projectileHelper(vec3d1, vec3d2);
				if (entityraytraceresult != null) {
					raytraceresult = entityraytraceresult;
				}

				if (raytraceresult != null && raytraceresult.getType() == RayTraceResult.Type.ENTITY) {
					Entity entity = ((EntityRayTraceResult)raytraceresult).getEntity();
					Entity entity1 = this.getShooter();
					if (entity instanceof PlayerEntity && entity1 instanceof PlayerEntity && !((PlayerEntity)entity1).canAttackPlayer((PlayerEntity)entity)) {
						raytraceresult = null;
						entityraytraceresult = null;
					}
				}

				if (raytraceresult != null && !flag && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
					this.onHit(raytraceresult);
					this.isAirBorne = true;
				}

				if (entityraytraceresult == null) {
					break;
				}

				raytraceresult = null;
			}

			vec3d = this.getMotion();
			double d1 = vec3d.x;
			double d2 = vec3d.y;
			double d0 = vec3d.z;


			this.posX += d1;
			this.posY += d2;
			this.posZ += d0;
			float f4 = MathHelper.sqrt(func_213296_b(vec3d));
			if (flag) {
				this.rotationYaw = (float)(MathHelper.atan2(-d1, -d0) * (double)(180F / (float)Math.PI));
			} else {
				this.rotationYaw = (float)(MathHelper.atan2(d1, d0) * (double)(180F / (float)Math.PI));
			}

			for(this.rotationPitch = (float)(MathHelper.atan2(d2, (double)f4) * (double)(180F / (float)Math.PI)); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
				;
			}

			while(this.rotationPitch - this.prevRotationPitch >= 180.0F) {
				this.prevRotationPitch += 360.0F;
			}

			while(this.rotationYaw - this.prevRotationYaw < -180.0F) {
				this.prevRotationYaw -= 360.0F;
			}

			while(this.rotationYaw - this.prevRotationYaw >= 180.0F) {
				this.prevRotationYaw += 360.0F;
			}

			this.rotationPitch = MathHelper.lerp(0.2F, this.prevRotationPitch, this.rotationPitch);
			this.rotationYaw = MathHelper.lerp(0.2F, this.prevRotationYaw, this.rotationYaw);
			float f1 = 0.99F;
			float f2 = 0.05F;
			if (this.isInWater()) {
				for(int j = 0; j < 4; ++j) {
					float f3 = 0.25F;
					this.world.addParticle(ParticleTypes.BUBBLE, this.posX - d1 * 0.25D, this.posY - d2 * 0.25D, this.posZ - d0 * 0.25D, d1, d2, d0);
				}

				f1 = this.getWaterDrag();
			}

			this.setMotion(vec3d.scale((double)f1));
			if (!this.hasNoGravity() && !flag) {
				Vec3d vec3d3 = this.getMotion();
				this.setMotion(vec3d3.x, vec3d3.y - (double)0.05F, vec3d3.z);
			}

			this.setPosition(this.posX, this.posY, this.posZ);
			this.doBlockCollisions();
		}
	}


	protected void tryDespawn() {
		++this.ticksInGround;
		if (this.ticksInGround >= 1200) {
			this.remove();
		}

	}

	@Nullable
	public Entity getShooter() {
		return this.shootingEntity != null && this.world instanceof ServerWorld ? ((ServerWorld)this.world).getEntityByUuid(this.shootingEntity) : null;
	}

	@Nullable
	protected EntityRayTraceResult projectileHelper(Vec3d p_213866_1_, Vec3d p_213866_2_) {
		return ProjectileHelper.func_221271_a(this.world, this, p_213866_1_, p_213866_2_, this.getBoundingBox().expand(this.getMotion()).grow(1.0D), (p_213871_1_) -> {
			return !p_213871_1_.isSpectator() && p_213871_1_.isAlive() && p_213871_1_.canBeCollidedWith() && (p_213871_1_ != this.getShooter() || this.ticksInAir >= 5) && (this.hashSet == null || !this.hashSet.contains(p_213871_1_.getEntityId()));
		});
	}

	protected float getWaterDrag() {
		return 0.6F;
	}

	/**
	 * Called when the arrow hits a block or an entity
	 */
	public void onHit(RayTraceResult raytraceResultIn) {
		RayTraceResult.Type raytraceresult$type = raytraceResultIn.getType();
		if (raytraceresult$type == RayTraceResult.Type.ENTITY) {
			this.onHitEntity((EntityRayTraceResult)raytraceResultIn);
		} else if (raytraceresult$type == RayTraceResult.Type.BLOCK) {
			this.onHitBlock((BlockRayTraceResult)raytraceResultIn);
		}
	}

	public void onHitEntity(EntityRayTraceResult entityRay) {
	}

	public void onHitBlock(BlockRayTraceResult blockRay) {
	}

	@Override
	protected void registerData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void readAdditional(CompoundNBT compound) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void writeAdditional(CompoundNBT compound) {
		// TODO Auto-generated method stub

	}

	@Override
	public IPacket<?> createSpawnPacket() {
		// TODO Auto-generated method stub
		return null;
	}

}
