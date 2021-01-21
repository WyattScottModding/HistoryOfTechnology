package entity;

import handlers.EntityProjectile;
import handlers.EntityRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EntityWater extends EntityProjectile
{
	private int timer = 15;
	private BasicParticleType particle = ParticleTypes.SPLASH;
	public Entity thrower = this.getShooter();

	public final static EntityType<?> type = EntityRegistry.WATER;


	public EntityWater(World worldIn)
	{
		super(type, worldIn);
		this.world = worldIn;
	}

	public EntityWater(World worldIn, LivingEntity throwerIn)
	{
		super(type, worldIn);
		this.world = worldIn;
		this.thrower = throwerIn;
	}

	public EntityWater(World worldIn, double x, double y, double z)
	{
		super(type, worldIn);
		this.world = worldIn;
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
				this.world.addParticle(particle, this.posX, this.posY, this.posZ, this.getMotion().x, this.getMotion().y, this.getMotion().z);
			}
		}
	}

	@Override
	public void tick() 
	{
		BlockPos pos = this.getPosition();
		Block block = world.getBlockState(pos).getBlock();

		boolean hitGround = block != Blocks.AIR;

		if(world != null && !world.isRemote && hitGround)
		{
			if(block == Blocks.LAVA)
			{
				if(((int)Math.random()*100) == 0)
					world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState());
			}
			else if(block == Blocks.FIRE)
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			}
			else if(block == Blocks.FARMLAND)
			{
				IntegerProperty MOISTURE = IntegerProperty.create("moisture", 0, 7);
				BlockState state = world.getBlockState(pos);

				world.setBlockState(pos, state.with(MOISTURE, 7));
			}


			this.remove();
		}

		if(timer == 0)
			this.remove();
		if(this.inGround)
			this.remove();

		if (timer > 0 && this.world.isRemote && !this.inGround)
		{
			float f = 0.8F;
			double x = 0;
			double y = 0;
			double z = 0;

			if(this.thrower != null)
			{
				float pitch = this.thrower.rotationPitch;
				float yaw = this.thrower.rotationYaw;

				x = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
				y = (double)(-MathHelper.sin((pitch) / 180.0F * (float)Math.PI) * f);
				z = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
			}

			world.addParticle(particle, this.posX + this.getWidth(), this.posY + (this.getHeight() * 2/3) - this.getYOffset(), this.posZ + this.getWidth(), x, y, z);
			world.addParticle(particle, this.posX + this.getWidth(), this.posY + (this.getHeight() * 2/3) - this.getYOffset(), this.posZ + this.getWidth(), x, y, z);
			world.addParticle(particle, this.posX + this.getWidth(), this.posY + (this.getHeight() * 2/3) - this.getYOffset(), this.posZ + this.getWidth(), x, y, z);
			world.addParticle(particle, this.posX + this.getWidth(), this.posY + (this.getHeight() * 2/3) - this.getYOffset(), this.posZ + this.getWidth(), x, y, z);

			timer--;
		}

		super.tick();		
	}
	
	@Override
	public void onHitEntity(EntityRayTraceResult entityRay) {
		Entity entity = entityRay.getEntity();

		if (entity != null && !world.isRemote)
		{
			if(entity instanceof BlazeEntity)
			{
				entity.attackEntityFrom(DamageSource.DROWN, 3);
			}
			else
			{
				entity.extinguish();	
			}
			this.remove();
		}
	}
}