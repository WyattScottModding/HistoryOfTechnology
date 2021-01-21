package entity;

import java.util.ArrayList;

import handlers.EntityProjectile;
import handlers.EntityRegistry;
import init.ItemInit;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.inventory.EquipmentSlotType;

public class EntityBullet extends EntityProjectile
{
	private int timer = 50;
	private float damage = 10;
	private String animation = "crit";
	public Entity thrower = this.getShooter();
	private static final DataParameter<Byte> dataSerialize = EntityDataManager.createKey(EntityBullet.class, DataSerializers.BYTE);

	public EntityBullet(World worldIn)
	{
		super(EntityRegistry.BULLET, worldIn);
	}

	public EntityBullet(EntityType<?> type, World worldIn)
	{
		super(type, worldIn);
		this.world = worldIn;
	}

	public EntityBullet(EntityType<?> type, World worldIn, LivingEntity throwerIn, float damage, String animation)
	{
		super(type, worldIn);
		this.world = worldIn;
		this.damage = damage;
		this.animation = animation;
		this.thrower = throwerIn;
	}

	public EntityBullet(EntityType<?> type, World worldIn, LivingEntity throwerIn, double x, double y, double z, float damage, String animation)
	{
		super(type, worldIn);
		this.world = worldIn;
		this.damage = damage;
		this.animation = animation;

		this.posX = x;
		this.posY = y;
		this.posZ = z;

		this.thrower = throwerIn;
	}

	public EntityBullet(EntityType<?> type, World worldIn, double x, double y, double z, float damage)
	{
		super(type, worldIn);
		this.world = worldIn;
		this.damage = damage;
	}

	/**
	 * Handler for {@link World#setEntityState}
	 */
	@Override
	public void handleStatusUpdate(byte id)
	{
		if (id == 3)
		{
			for (int i = 0; i < 8; ++i)
			{
				if(animation.equals("crit"))
					this.world.addParticle(ParticleTypes.CRIT, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			}
		}
	}


	@Override
	public void onHitEntity(EntityRayTraceResult entityRay) 
	{
		Entity entity = entityRay.getEntity();

		if(entity instanceof LivingEntity && !world.isRemote)
		{
			LivingEntity livingEntity = (LivingEntity) entity;

			boolean hasVest = livingEntity.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ItemInit.BULLETPROOFVEST;

			int chance = 0;

			if(hasVest)
			{
				chance = (int)(Math.random() * 5);
			}

			if(chance == 0)
			{
				if(thrower != null)
				{
					if(livingEntity != thrower)
					{
						livingEntity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.thrower), damage);
					}
				}
				else
				{
					livingEntity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.thrower), damage);
				}
			}
			this.remove();
		}
	}

	@Override
	public void onHitBlock(BlockRayTraceResult blockRay) {
		BlockPos pos = blockRay.getPos();

		if(pos != null && world != null)
		{
			Block block = world.getBlockState(pos).getBlock();

			ArrayList<Block> breakableBlocks = new ArrayList<Block>();

			breakableBlocks.add(Blocks.GLASS);
			breakableBlocks.add(Blocks.BLACK_STAINED_GLASS);
			breakableBlocks.add(Blocks.BLUE_STAINED_GLASS);
			breakableBlocks.add(Blocks.BROWN_STAINED_GLASS);
			breakableBlocks.add(Blocks.CYAN_STAINED_GLASS);
			breakableBlocks.add(Blocks.GRAY_STAINED_GLASS);
			breakableBlocks.add(Blocks.GREEN_STAINED_GLASS);
			breakableBlocks.add(Blocks.LIGHT_BLUE_STAINED_GLASS);
			breakableBlocks.add(Blocks.LIGHT_GRAY_STAINED_GLASS);
			breakableBlocks.add(Blocks.LIME_STAINED_GLASS);
			breakableBlocks.add(Blocks.MAGENTA_STAINED_GLASS);
			breakableBlocks.add(Blocks.ORANGE_STAINED_GLASS);
			breakableBlocks.add(Blocks.PINK_STAINED_GLASS);
			breakableBlocks.add(Blocks.RED_STAINED_GLASS);
			breakableBlocks.add(Blocks.WHITE_STAINED_GLASS);
			breakableBlocks.add(Blocks.YELLOW_STAINED_GLASS);

			breakableBlocks.add(Blocks.GLASS_PANE);
			breakableBlocks.add(Blocks.BLACK_STAINED_GLASS_PANE);
			breakableBlocks.add(Blocks.BLUE_STAINED_GLASS_PANE);
			breakableBlocks.add(Blocks.BROWN_STAINED_GLASS_PANE);
			breakableBlocks.add(Blocks.CYAN_STAINED_GLASS_PANE);
			breakableBlocks.add(Blocks.GRAY_STAINED_GLASS_PANE);
			breakableBlocks.add(Blocks.GREEN_STAINED_GLASS_PANE);
			breakableBlocks.add(Blocks.LIGHT_BLUE_STAINED_GLASS_PANE);
			breakableBlocks.add(Blocks.LIGHT_GRAY_STAINED_GLASS_PANE);
			breakableBlocks.add(Blocks.LIME_STAINED_GLASS_PANE);
			breakableBlocks.add(Blocks.MAGENTA_STAINED_GLASS_PANE);
			breakableBlocks.add(Blocks.ORANGE_STAINED_GLASS_PANE);
			breakableBlocks.add(Blocks.PINK_STAINED_GLASS_PANE);
			breakableBlocks.add(Blocks.PURPLE_STAINED_GLASS_PANE);
			breakableBlocks.add(Blocks.RED_STAINED_GLASS_PANE);
			breakableBlocks.add(Blocks.WHITE_STAINED_GLASS_PANE);
			breakableBlocks.add(Blocks.YELLOW_STAINED_GLASS_PANE);

			breakableBlocks.add(Blocks.FLOWER_POT);
			breakableBlocks.add(Blocks.REDSTONE_LAMP);
			breakableBlocks.add(Blocks.REDSTONE_LAMP);
			breakableBlocks.add(Blocks.GLOWSTONE);
			breakableBlocks.add(Blocks.ICE);

			for(Block element : breakableBlocks)
			{
				if(element == block)
				{
					if(!world.isRemote)
					{
						world.destroyBlock(pos, true);							
						this.remove();
					}
				}
			}
		}
	}


	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void tick()
	{
		super.tick();

		if(timer == 0)
			this.remove();

		if (timer > 0 && this.world.isRemote && !this.onGround)
		{
			world.addParticle(ParticleTypes.CRIT, this.posX + (rand.nextDouble() - 0.5D) * (double)this.getWidth(), this.posY + rand.nextDouble() * (double)this.getHeight() - (double)this.getYOffset(), this.posZ + (rand.nextDouble() - 0.5D) * (double)this.getWidth(), 0.0D, 0.0D, 0.0D);
			timer--;
		}

		Block block = world.getBlockState(this.getPosition()).getBlock();

		if(block.getDefaultState().isSolid())
		{
			this.remove();
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
	public IPacket<?> createSpawnPacket() {
		// TODO Auto-generated method stub
		return null;
	}

}