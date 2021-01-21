package entity;

import handlers.EntityProjectile;
import handlers.EntityRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public class EntityFire extends EntityProjectile
{
	private int timer = 15;
	public final static EntityType<?> type = EntityRegistry.FIRE;


	public EntityFire(World worldIn)
	{
		super(type, worldIn);
		this.world = worldIn;
	}

	public EntityFire(World worldIn, LivingEntity throwerIn)
	{
		super(type, worldIn);
		this.world = worldIn;

	}

	public EntityFire(World worldIn, LivingEntity throwerIn, double x, double y, double z)
	{
		super(type, worldIn);
		this.world = worldIn;

		this.posX = x;
		this.posY = y;
		this.posZ = z;
	}

	public EntityFire(World worldIn, double x, double y, double z)
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
				world.addParticle(ParticleTypes.FLAME, this.posX + (rand.nextDouble() - 0.5D) * (double)this.getWidth(), this.posY + rand.nextDouble() * (double)this.getHeight() - (double)this.getYOffset(), this.posZ + (rand.nextDouble() - 0.5D) * (double)this.getWidth(), 0.0D, 0.0D, 0.0D);
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
		if(this.inGround)
			this.remove();

		if (timer > 0 && this.world.isRemote && !this.inGround)
		{
			world.addParticle(ParticleTypes.FLAME, this.posX + (rand.nextDouble() - 0.5D) * (double)this.getWidth(), this.posY + rand.nextDouble() * (double)this.getHeight() - (double)this.getYOffset(), this.posZ + (rand.nextDouble() - 0.5D) * (double)this.getWidth(), 0.0D, 0.0D, 0.0D);
			world.addParticle(ParticleTypes.FLAME, this.posX + (rand.nextDouble() - 0.5D) * (double)this.getWidth(), this.posY + rand.nextDouble() * (double)this.getHeight() - (double)this.getYOffset(), this.posZ + (rand.nextDouble() - 0.5D) * (double)this.getWidth(), 0.0D, 0.0D, 0.0D);
			timer--;
		}


		BlockPos pos = this.getPosition();
		Block block = world.getBlockState(pos).getBlock();

		if(world != null && !world.isRemote && block != Blocks.AIR)
		{
			if(block == Blocks.WATER || block == Blocks.LAVA)
				this.remove();
			else
				setFire(world, pos, block);
		}

	}


	public void setFire(World world, BlockPos pos, Block block)
	{
		if((block.isFlammable(world.getBlockState(pos), world, pos, Direction.UP) || block == Blocks.NETHERRACK) && world.getBlockState(pos.up()).getBlock() == Blocks.AIR)
		{
			world.setBlockState(pos.up(), Blocks.FIRE.getDefaultState());
		}
		else if((block.isFlammable(world.getBlockState(pos), world, pos, Direction.DOWN) || block == Blocks.NETHERRACK) && world.getBlockState(pos.down()).getBlock() == Blocks.AIR)
		{
			world.setBlockState(pos.down(), Blocks.FIRE.getDefaultState());
		}
		else if((block.isFlammable(world.getBlockState(pos), world, pos, Direction.NORTH) || block == Blocks.NETHERRACK) && world.getBlockState(pos.north()).getBlock() == Blocks.AIR)
		{
			world.setBlockState(pos.north(), Blocks.FIRE.getDefaultState());
		}
		else if((block.isFlammable(world.getBlockState(pos), world, pos, Direction.SOUTH) || block == Blocks.NETHERRACK) && world.getBlockState(pos.south()).getBlock() == Blocks.AIR)
		{
			world.setBlockState(pos.south(), Blocks.FIRE.getDefaultState());
		}
		else if((block.isFlammable(world.getBlockState(pos), world, pos, Direction.EAST) || block == Blocks.NETHERRACK) && world.getBlockState(pos.east()).getBlock() == Blocks.AIR)
		{
			world.setBlockState(pos.east(), Blocks.FIRE.getDefaultState());
		}
		else if((block.isFlammable(world.getBlockState(pos), world, pos, Direction.WEST) || block == Blocks.NETHERRACK) && world.getBlockState(pos.west()).getBlock() == Blocks.AIR)
		{
			world.setBlockState(pos.west(), Blocks.FIRE.getDefaultState());
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