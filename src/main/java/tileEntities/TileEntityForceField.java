package tileEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import containers.ContainerForge;
import handlers.TileEntities;
import init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class TileEntityForceField extends TileEntity implements ITickableTileEntity, INamedContainerProvider
{
	public final BasicParticleType particles = ParticleTypes.CLOUD;
	public boolean nearbyForceFields = false;
	private boolean forceFieldOn = false;

	public TileEntityForceField() 
	{
		super(TileEntities.FORCE_FIELD);
	}
	
	public boolean isOn()
	{
		return forceFieldOn;
	}

	private void setOn(boolean on)
	{
		forceFieldOn = on;
	}


	@Override
	public void tick()
	{
		int size = 10;

		size = getPower(world, this.pos) * 2;

		//int rate = size;

		size *= enhancers(world, this.pos);

		//Updates every 2 seconds to minimize lag
		if(world.getGameTime() % 40 == 0)
			nearbyForceFields = findOtherForceFields(world, this.pos, size);

		//Will not make a force field if other force field generators are nearby
		if(!nearbyForceFields)
		{
			if(size >= 4)
			{
				setOn(true);

				int xPos = this.pos.getX();
				int yPos = this.pos.getY();
				int zPos = this.pos.getZ();

				int mod = (int) world.getGameTime() % (size*2 - 1);


				int rate = 4;

				if(size >= 60)
					rate = 3;
				if(size >= 90)
					rate = 2;
				if(size >= 120)
					rate = 1;

				//Animation
				if(world.getGameTime() % 25 == 0)
				{
					for(float yaw = -360; yaw < 360; yaw += rate)
					{
						for(float pitch = -90; pitch < 90; pitch += rate)
						{
							double x = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * size);
							double y = (double)(-MathHelper.sin((pitch) / 180.0F * (float)Math.PI) * size);
							double z = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * size);

							BlockPos pos = new BlockPos(x + xPos, y + yPos, z + zPos);

							if(world.getBlockState(pos).getBlock() == Blocks.AIR || world.getBlockState(pos).getBlock() == BlockInit.MUSTARD_GAS) //|| world.getBlockState(pos).getBlock() == BlockInit.BLOCK_FORCE_FIELD)
							{
								this.world.addParticle(particles, x + xPos, y + yPos, z + zPos, 0.0D, 0.0D, 0.0D);

								/*
								if(world.getBlockState(pos).getBlock() != BlockInit.BLOCK_FORCE_FIELD)
								{
									world.setBlockState(pos, BlockInit.BLOCK_FORCE_FIELD.getDefaultState());
								}
								 */
							}
						}
					}
				}
				int y = mod - size;

				//ParticleTypes that come up from the generator
				if(y > 1)
				{
					double width = .125;

					this.world.addParticle(particles, xPos + width, y + yPos, zPos + width, 0.0D, 0.0D, 0.0D);
					this.world.addParticle(particles, xPos + 1 - width, y + yPos, zPos + width, 0.0D, 0.0D, 0.0D);
					this.world.addParticle(particles, xPos + width, y + yPos, zPos + 1 - width, 0.0D, 0.0D, 0.0D);
					this.world.addParticle(particles, xPos + 1 - width, y + yPos, zPos + 1 - width, 0.0D, 0.0D, 0.0D);
				}


				//Push Entity
				AxisAlignedBB bb = new AxisAlignedBB(xPos - (size + 1), yPos - (size + 1), zPos - (size + 1), xPos + (size + 1), yPos + (size + 1), zPos + (size + 1));

				List<Entity> entites = world.getEntitiesWithinAABB(Entity.class, bb);

				BlockPos position = new BlockPos(xPos, yPos, zPos);

				for(Entity element : entites)
				{	
					//Detects if an entity hit the force field
					if(Math.sqrt(element.getDistanceSq(xPos, yPos, zPos)) > (size - 1) && Math.sqrt(element.getDistanceSq(xPos, yPos, zPos)) < (size + 1))
					{
						//Determines if the entity is currently inside or outside the forcefield
						if(!isMotionTowards(element, position))
							pushEntityIn(element, position);
						else
							pushEntityOut(element, position);

						//Plays a bouncy sound when an entity hits the force field
						//if(element instanceof PlayerEntity)
						//	world.playSound((PlayerEntity) element, element.getPosition(), SoundsHandler.BLOCK_BOUNCE, SoundCategory.BLOCKS, 0.7F, 0.3F);
						//else
						//	world.playSound(null, element.getPosition(), SoundsHandler.BLOCK_BOUNCE, SoundCategory.BLOCKS, 0.7F, 0.3F);

					}
				}
			}
			else
				setOn(false);
		}  
		else
			setOn(false);
	}



	public boolean isMotionTowards(Entity entity, BlockPos pos)
	{
		double x1 = entity.posX;
		double y1 = entity.posY;
		double z1 = entity.posZ;

		double x2 = pos.getX();
		double y2 = pos.getY();
		double z2 = pos.getZ();

		double x = Math.abs(x1 - x2);
		double y = Math.abs(y1 - y2);
		double z = Math.abs(z1 - z2);


		double distance1 = Math.sqrt(x*x + y*y + z*z);

		x1 += entity.getMotion().x;
		y1 += entity.getMotion().y;
		z1 += entity.getMotion().z;

		x = Math.abs(x1 - x2);
		y = Math.abs(y1 - y2);
		z = Math.abs(z1 - z2);

		double distance2 = Math.sqrt(x*x + y*y + z*z);

		return distance2 <= distance1;
	}

	public void pushEntityOut(Entity entity, BlockPos pos)
	{
		double x1 = entity.posX;
		double y1 = entity.posY;
		double z1 = entity.posZ;

		double x2 = pos.getX();
		double y2 = pos.getY();
		double z2 = pos.getZ();

		double x = x1 - x2;
		double y = y1 - y2;
		double z = z1 - z2;

		Random rand = new Random();
		double velocity = 1.0;

		float f = MathHelper.sqrt(x * x + y * y + z * z);
		x = x / (double)f;
		y = y / (double)f;
		z = z / (double)f;
		x = x + rand.nextGaussian() * 0.007499999832361937D;
		y = y + rand.nextGaussian() * 0.007499999832361937D;
		z = z + rand.nextGaussian() * 0.007499999832361937D;
		x = x * velocity;
		y = y * velocity;
		z = z * velocity;

		entity.setMotion(x, y, z);
	}

	public void pushEntityIn(Entity entity, BlockPos pos)
	{
		double x1 = entity.posX;
		double y1 = entity.posY;
		double z1 = entity.posZ;

		double x2 = pos.getX();
		double y2 = pos.getY();
		double z2 = pos.getZ();


		double x = x2 - x1;
		double y = y2 - y1;
		double z = z2 - z1;

		Random rand = new Random();
		double velocity = 1.0;

		float f = MathHelper.sqrt(x * x + y * y + z * z);
		x = x / (double)f;
		y = y / (double)f;
		z = z / (double)f;
		x = x + rand.nextGaussian() * 0.007499999832361937D;
		y = y + rand.nextGaussian() * 0.007499999832361937D;
		z = z + rand.nextGaussian() * 0.007499999832361937D;
		x = x * velocity;
		y = y * velocity;
		z = z * velocity;

		entity.setMotion(x, y, z);
	}

	protected int getPower(World worldIn, BlockPos pos)
	{
		List<Direction> list = new ArrayList<Direction>();

		list.add(Direction.UP);
		list.add(Direction.DOWN);
		list.add(Direction.NORTH);
		list.add(Direction.SOUTH);
		list.add(Direction.EAST);
		list.add(Direction.WEST);

		int i = 0;

		for(Direction element : list)
		{
			BlockPos blockpos = pos.offset(element);
			int j = worldIn.getRedstonePower(blockpos, element);

			if(j > i)
				i = j;
		}


		return i;
	}

	protected int enhancers(World worldIn, BlockPos pos)
	{
		List<BlockPos> list = new ArrayList<BlockPos>();

		list.add(pos.up());
		list.add(pos.down());
		list.add(pos.north());
		list.add(pos.south());
		list.add(pos.east());
		list.add(pos.west());
		list.add(pos.north().up());
		list.add(pos.south().up());
		list.add(pos.east().up());
		list.add(pos.west().up());

		int i = 1;

		for(BlockPos element : list)
		{
			Block block = world.getBlockState(element).getBlock();

			if(block == Blocks.DIAMOND_BLOCK)
				i++;
		}

		return i;
	}


	public boolean findOtherForceFields(World world, BlockPos pos, int size)
	{
		int rangeMinX = pos.getX() - (size / 2);
		int rangeMaxX = pos.getX() + (size / 2);

		int rangeMinY = pos.getY() - (size / 2);
		int rangeMaxY = pos.getY() + (size / 2);

		int rangeMinZ = pos.getZ() - (size / 2);
		int rangeMaxZ = pos.getZ() + (size / 2);

		for(int x = rangeMinX; x < rangeMaxX; x++)
		{
			for(int y = rangeMinY; y < rangeMaxY; y++)
			{
				for(int z = rangeMinZ; z < rangeMaxZ; z++)
				{
					BlockPos pos2 = new BlockPos(x, y, z);

					if(!pos.equals(pos2))
					{
						Block block = world.getBlockState(pos2).getBlock();

						if(block == BlockInit.FORCE_FIELD_GENERATOR)
						{
							TileEntityForceField tileentity = (TileEntityForceField) world.getTileEntity(pos2);

							if(tileentity.isOn())
								return true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public Container createMenu(int menuId, PlayerInventory inv, PlayerEntity player) {
		return new ContainerForge(menuId, world, pos, inv, player);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent(getType().getRegistryName().getPath());
	}
}