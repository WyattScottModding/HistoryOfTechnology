package entity;

import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import handlers.EntityProjectile;
import handlers.EntityRegistry;
import init.ItemInit;
import main.History.EntityTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class EntitySpear extends EntityProjectile implements IProjectile
{
	public UUID shootingEntity;

	public final static EntityType<?> type = EntityRegistry.SPEAR;
	public EntitySpear.PickupStatus pickupStatus = EntitySpear.PickupStatus.DISALLOWED;
	public Entity thrower = this.getShooter();
	
    public EntitySpear(FMLPlayMessages.SpawnEntity packet, World worldIn)
    {
        super(EntityRegistry.SPEAR, worldIn);
    }

	protected EntitySpear(EntityType<? extends EntitySpear> type, World world) {
		super(type, world);
		
	}

	public EntitySpear(World worldIn) {
		super(type, worldIn);
	}
	
	public EntitySpear(World worldIn, LivingEntity shooter) {
		super(type, worldIn);
		this.setShooter(shooter);
	}

	/**
	 * Handler for {@link World#setEntityState}
	 */

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

	public void setShooter(@Nullable Entity shooter) {
		this.shootingEntity = shooter == null ? null : shooter.getUniqueID();
		if (shooter instanceof PlayerEntity) {
			this.pickupStatus = ((PlayerEntity)shooter).abilities.isCreativeMode ? EntitySpear.PickupStatus.CREATIVE_ONLY : EntitySpear.PickupStatus.ALLOWED;
		}

	}


	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void tick()
	{
		super.tick();

		if (this.world.isRemote && !this.onGround)
		{
			this.world.addParticle(ParticleTypes.SWEEP_ATTACK, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		}
	}

	protected ItemStack getArrowStack()
	{
		return new ItemStack(ItemInit.SPEAR);
	}



	/**
	 * Called when the arrow hits a block or an entity
	 */
	@Override
	public void onHitEntity(EntityRayTraceResult entityRay) 
	{

		Entity entity = entityRay.getEntity();

		if (entity != null)
		{
			if(this.shootingEntity != null)
			{
				if(this.getShooter() instanceof PlayerEntity)
					entity.attackEntityFrom(DamageSource.causePlayerDamage((PlayerEntity)this.getShooter()), 15F);
				else
					entity.attackEntityFrom(DamageSource.causeThornsDamage(entity), 15F);

				if(this.getShooter() instanceof PlayerEntity)
				{
					PlayerEntity player = (PlayerEntity) getShooter();

					if(!player.isCreative())
					{
						int dropChance = (int)(Math.random() * 10);

						//50% chance of dropping after hitting an entity
						if(dropChance < 6)
							spawnItemStack(world, this.posX, this.posY, this.posZ, new ItemStack(ItemInit.SPEAR));
					}
				}
				else
				{
					int dropChance = (int)(Math.random() * 10);

					//50% chance of dropping after hitting an entity
					if(dropChance < 6)
						spawnItemStack(world, this.posX, this.posY, this.posZ, new ItemStack(ItemInit.SPEAR));
				}
			}
		}
	}

	/**
	 * Called by a player entity when they collide with an entity
	 */
	@Override
	public void onCollideWithPlayer(PlayerEntity entityIn)
	{
		if (!this.world.isRemote && this.onGround)
		{
			boolean flag = this.pickupStatus == EntitySpear.PickupStatus.ALLOWED || this.pickupStatus == EntitySpear.PickupStatus.CREATIVE_ONLY && entityIn.abilities.isCreativeMode || this.getShooter().getUniqueID() == entityIn.getUniqueID();

			if (this.pickupStatus == EntitySpear.PickupStatus.ALLOWED && !entityIn.inventory.addItemStackToInventory(this.getArrowStack()))
			{
				flag = false;
			}

			if (flag)
			{
				entityIn.onItemPickup(this, 1);
				this.remove();
			}
		}
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
			entityitem.setMotion(RANDOM.nextGaussian() * 0.05000000074505806D, RANDOM.nextGaussian() * 0.05000000074505806D + 0.20000000298023224D, RANDOM.nextGaussian() * 0.05000000074505806D);

			entityitem.setMotion(entityitem.getMotion().x, RANDOM.nextGaussian() * 0.05000000074505806D, RANDOM.nextGaussian() * 0.05000000074505806D);
			worldIn.addEntity(entityitem);
		}
	}


	public static enum PickupStatus {
		DISALLOWED,
		ALLOWED,
		CREATIVE_ONLY;

		public static EntitySpear.PickupStatus getByOrdinal(int ordinal) {
			if (ordinal < 0 || ordinal > values().length) {
				ordinal = 0;
			}

			return values()[ordinal];
		}
	}
}