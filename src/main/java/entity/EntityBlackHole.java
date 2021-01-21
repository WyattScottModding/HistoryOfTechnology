package entity;

import java.util.List;

import handlers.EntityRegistry;
import init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityBlackHole extends Entity
{
	public final static EntityType<?> type = EntityRegistry.BLACKHOLE;

	public EntityBlackHole(World worldIn)
	{
		super(type, worldIn);
		this.world = worldIn;
	}

	public EntityBlackHole(World worldIn, LivingEntity throwerIn)
	{
		super(type, worldIn);
		this.world = worldIn;

	}

	public EntityBlackHole(World worldIn, double x, double y, double z)
	{
		super(type, worldIn);
		this.world = worldIn;

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
				this.world.addParticle(ParticleTypes.CRIT, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			}
		}
	}


	@Override
	public void tick() 
	{
		if(world != null && !world.isRemote && world.getGameTime() % 10 == 0)
		{				
			AxisAlignedBB bb = new AxisAlignedBB(this.posX - 50, this.posY - 50, this.posZ - 50, this.posX + 50, this.posY + 50, this.posZ + 50);

			List<LivingEntity> entites = world.getEntitiesWithinAABB(LivingEntity.class, bb);

			for(LivingEntity element : entites)
			{
				if(element.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() != ItemInit.GRAVITY_BOOTS)	
				{
					double distance = element.getDistanceSq(this);

					if(distance < 1)
					{
						element.setVelocity(0, 0, 0);
					}
					else
					{
						double force = 40.0 / distance;

						double x = (this.posX - element.posX) / (Math.abs(this.posX - element.posX));
						double y = (this.posY - element.posY) / (Math.abs(this.posY - element.posY));
						double z = (this.posZ - element.posZ) / (Math.abs(this.posZ - element.posZ));

						element.addVelocity(x * force, y * force, z * force);
						element.velocityChanged = true;
					}
				}
			}
		}

		this.setVelocity(0, 0, 0);
		this.velocityChanged = true;

		super.tick();

		Block block = world.getBlockState(this.getPosition()).getBlock();

		if(block.getDefaultState().isSolid())
		{
			this.setVelocity(0, .1, 0);
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

	public ItemStack getItem() {
		return new ItemStack(ItemInit.BLACKHOLE);
	}

}