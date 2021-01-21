package entity;

import java.util.List;

import handlers.EntityProjectile;
import handlers.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EntityTrackingMissile extends EntityProjectile
{
	private float power = 6;
	private boolean tracking = false;
	private float yaw = 0;
	private float pitch = 0;
	private double motionX2 = 0;
	private double motionY2 = 0;
	private double motionZ2 = 0;
	private int lifetime = 0;
	public Entity thrower = this.getShooter();
	public Entity entity = null;
	

	public final static EntityType<?> type = EntityRegistry.TRACKINGMISSILE;

	
	public EntityTrackingMissile(World worldIn)
	{
		super(type, worldIn);
		this.world = worldIn;
		this.setNoGravity(true);
	}

	public EntityTrackingMissile(World worldIn, LivingEntity throwerIn, boolean tracking, float pitch, float yaw)
	{
		super(type, worldIn);
		this.world = worldIn;
		this.setNoGravity(true);
		this.tracking = tracking;
		this.yaw = yaw;
		this.pitch = pitch;
	}
	
	public EntityTrackingMissile(World worldIn, LivingEntity throwerIn, boolean tracking, float pitch, float yaw, float power)
	{
		super(type, worldIn);
		this.world = worldIn;
		this.setNoGravity(true);
		this.tracking = tracking;
		this.yaw = yaw;
		this.pitch = pitch;
		this.power = power;
	}


	public EntityTrackingMissile(World worldIn, double x, double y, double z, boolean tracking, float pitch, float yaw)
	{
		super(type, worldIn);
		this.world = worldIn;
		this.setNoGravity(true);
		this.tracking = tracking;
		this.yaw = yaw;
		this.pitch = pitch;
	}
	
	/**
	 * Handler for {@link World#setEntityState}
	 */
	@OnlyIn(Dist.CLIENT) @Override
	public void handleStatusUpdate(byte id)
	{
		if (id == 3)
		{
			for (int i = 0; i < 8; ++i)
			{
				this.world.addParticle(ParticleTypes.CRIT, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return this.isAlive();
	}


	@Override
	public boolean canBePushed() 
	{
		return true;
	}

	@Override
	public void tick() 
	{
		if(!world.isRemote)
		{
			lifetime++;
			
			if(tracking && lifetime > 20)
			{
				//If not found an entity
				if(entity == null)
				{
					AxisAlignedBB bb = new AxisAlignedBB(this.posX - 10, this.posY - 10, this.posZ - 10, this.posX + 10, this.posY + 10, this.posZ + 10);
					List<LivingEntity> entites = world.getEntitiesWithinAABB(LivingEntity.class, bb);
					double minDistance = 100;

					for(LivingEntity element : entites)
					{
						if(element != this.getShooter())	
						{
							double xDiff = element.posX - this.posX;
							double yDiff = element.posY - this.posY;
							double zDiff = element.posZ - this.posZ;

							double distance = Math.sqrt(xDiff*xDiff + yDiff*yDiff + zDiff*zDiff);

							if(distance < minDistance)
							{
								minDistance = distance;
								entity = element;
							}
						}
					}

				}
				//If found an entity
				if(entity != null)
				{
					double xDiff = entity.posX - this.posX;
					double yDiff = entity.posY - this.posY;
					double zDiff = entity.posZ - this.posZ;

					double magnitude = Math.sqrt(xDiff*xDiff + yDiff*yDiff + zDiff*zDiff);

					//The Unit Vector
					double xVelocity = xDiff / magnitude;
					double yVelocity = yDiff / magnitude;
					double zVelocity = zDiff / magnitude;

					this.setMotion(xVelocity, yVelocity, zVelocity);

					motionX2 = xVelocity;
					motionY2 = yVelocity;
					motionZ2 = zVelocity;
				}

			}
			//If the entity is still null, keep moving in the same direction
			if(entity == null && world.getGameTime() % 30 == 0)
			{
				double xVelocity = motionX2;
				double yVelocity = motionY2;
				double zVelocity = motionZ2;

				if(motionX2 == 0 && motionY2 == 0 && motionZ2 == 0)
				{
					xVelocity = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
					yVelocity = -MathHelper.sin((pitch) * 0.017453292F);
					zVelocity = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
				}

				this.setMotion(xVelocity, yVelocity, zVelocity);
			}
			 
		}
		super.tick();
	}

	public void fireMissile(float yaw, float pitch)
	{
		double xVelocity = motionX2;
		double yVelocity = motionY2;
		double zVelocity = motionZ2;

		xVelocity = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		yVelocity = -MathHelper.sin((pitch) * 0.017453292F);
		zVelocity = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);

		this.setMotion(xVelocity, yVelocity, zVelocity);
	}
	
	@Override
	public void onHitEntity(EntityRayTraceResult entityRay) {
		if(entityRay.getEntity() != this.getShooter())
			explode();
	}

	public void explode()
	{
		if(world != null && !world.isRemote)
		{
			world.createExplosion(this, this.posX, this.posY, this.posZ, power, false, Explosion.Mode.BREAK);


			AxisAlignedBB bb1 = new AxisAlignedBB(this.posX - 4, this.posY - 4, this.posZ - 4, this.posX + 4, this.posY + 4, this.posZ + 4);
			AxisAlignedBB bb2 = new AxisAlignedBB(this.posX - 6, this.posY - 6, this.posZ - 6, this.posX + 6, this.posY + 6, this.posZ + 6);

			List<LivingEntity> entites1 = world.getEntitiesWithinAABB(LivingEntity.class, bb1);
			List<LivingEntity> entites2 = world.getEntitiesWithinAABB(LivingEntity.class, bb2);

			for(LivingEntity element : entites1)
			{
				element.addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE, 1, 5));
			}

			for(LivingEntity element : entites2)
			{
				element.addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE, 1, 4));
			}

			this.remove();
		}
	}

}