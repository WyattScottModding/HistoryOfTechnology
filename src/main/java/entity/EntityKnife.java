package entity;

import java.util.Random;

import handlers.EntityProjectile;
import handlers.EntityRegistry;
import init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityKnife extends EntityProjectile
{
	public final static EntityType<?> type = EntityRegistry.KNIFE;
	public Entity thrower = this.getShooter();

    public EntityKnife(FMLPlayMessages.SpawnEntity packet, World worldIn)
    {
        super(EntityRegistry.KNIFE, worldIn);
    }
	
	public EntityKnife(World worldIn)
	{
		super(type, worldIn);
		this.world = worldIn;
	}

	public EntityKnife(World worldIn, LivingEntity throwerIn)
	{
		super(type, worldIn);
		this.world = worldIn;
	}

	public EntityKnife(World worldIn, double x, double y, double z)
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
				this.world.addParticle(ParticleTypes.CRIT, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public void onHitEntity(EntityRayTraceResult entityRay) {
		Entity entity = entityRay.getEntity();

		if (entity != this.thrower && this.thrower instanceof LivingEntity)// && result.entityHit != this.getThrower())
		{
			LivingEntity entityLiving = (LivingEntity) this.thrower;

			if(entityLiving != null && entityLiving instanceof PlayerEntity)
				entity.attackEntityFrom(DamageSource.causePlayerDamage((PlayerEntity)entityLiving), 12F);
			else
				entity.attackEntityFrom(DamageSource.causeThornsDamage(entity), 12F);

			if(thrower instanceof PlayerEntity)
			{
				PlayerEntity player = (PlayerEntity) thrower;

				if(!player.isCreative())
				{
					int dropChance = (int)(Math.random() * 10);

					//80% chance of dropping after hitting an entity
					if(dropChance < 9)
						spawnItemStack(world, this.posX, this.posY, this.posZ, new ItemStack(ItemInit.THROWING_KNIFE));
				}
			}
			else
			{
				int dropChance = (int)(Math.random() * 10);

				//80% chance of dropping after hitting an entity
				if(dropChance < 9)
					spawnItemStack(world, this.posX, this.posY, this.posZ, new ItemStack(ItemInit.THROWING_KNIFE));
			}
			this.remove();
		}
	}

	@Override
	public void onHitBlock(BlockRayTraceResult blockRay) {
		this.world.setEntityState(this, (byte)3);

		if(this.thrower instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) this.thrower;

			if(!player.isCreative())
			{
				spawnItemStack(world, this.posX, this.posY, this.posZ, new ItemStack(ItemInit.THROWING_KNIFE));

			}
		}

		this.remove();
	}



	public static void spawnItemStack(World worldIn, double x, double y, double z, ItemStack stack)
	{
		Random RANDOM = new Random();

		float f = RANDOM.nextFloat() * 0.8F + 0.1F;
		float f1 = RANDOM.nextFloat() * 0.8F + 0.1F;
		float f2 = RANDOM.nextFloat() * 0.8F + 0.1F;

		while (!stack.isEmpty())
		{
			ItemEntity entityitem = new ItemEntity(worldIn, x + (double)f, y + (double)f1, z + (double)f2, stack.split(RANDOM.nextInt(21) + 10));
			double motionX = RANDOM.nextGaussian() * 0.05000000074505806D;
			double motionY = RANDOM.nextGaussian() * 0.05000000074505806D;
			double motionZ = RANDOM.nextGaussian() * 0.05000000074505806D;

			entityitem.setMotion(motionX, motionY, motionZ);
			worldIn.addEntity(entityitem);
		}
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
	public IPacket<?> createSpawnPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}