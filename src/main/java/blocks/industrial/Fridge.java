package blocks.industrial;

import java.util.Random;

import javax.annotation.Nullable;

import init.BlockInit;
import main.History;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.ItemStackHandler;
import tileEntities.IRestorableTileEntity;
import tileEntities.TileEntityForge;
import tileEntities.TileEntityMissileGuidanceSystem;

public class Fridge extends ContainerBlock
{
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

	Item.Properties properties;

	public Fridge(String name)
	{
		super(Block.Properties.create(Material.IRON).sound(SoundType.STONE).hardnessAndResistance(3.0F, 24));
		this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH));
		this.setRegistryName(name);

		properties = new Item.Properties().maxStackSize(64).group(History.industrialTab);
	}

	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return false;
	}


	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving)
	{
		if (!worldIn.isRemote) 
		{
			BlockState north = worldIn.getBlockState(pos.north());
			BlockState south = worldIn.getBlockState(pos.south());
			BlockState west = worldIn.getBlockState(pos.west());
			BlockState east = worldIn.getBlockState(pos.east());
			Direction face = (Direction)state.get(FACING);

			if (face == Direction.NORTH && north.isNormalCube(worldIn, pos) && !south.isNormalCube(worldIn, pos)) face = Direction.SOUTH;
			else if (face == Direction.SOUTH && south.isNormalCube(worldIn, pos) && !north.isNormalCube(worldIn, pos)) face = Direction.NORTH;
			else if (face == Direction.WEST && west.isNormalCube(worldIn, pos) && !east.isNormalCube(worldIn, pos)) face = Direction.EAST;
			else if (face == Direction.EAST && east.isNormalCube(worldIn, pos) && !west.isNormalCube(worldIn, pos)) face = Direction.WEST;
			worldIn.setBlockState(pos, state.with(FACING, face), 2);
		}
	}

	public static void setState(boolean active, World worldIn, BlockPos pos) 
	{
		BlockState state = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if(active) 
			worldIn.setBlockState(pos, BlockInit.FORGE.getDefaultState().with(FACING, state.get(FACING)), 3);
		else 
			worldIn.setBlockState(pos, BlockInit.FORGE.getDefaultState().with(FACING, state.get(FACING)), 3);

		if(tileentity != null) 
		{
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}

	/**
	 * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
	 * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
	 */
	@Override
	public BlockRenderType getRenderType(BlockState state)
	{
		return BlockRenderType.MODEL;
	}
	
	@Override
	public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) 
	{
		return state.rotate(world, pos, direction);
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirror)
	{
		return state.mirror(mirror);
	}



	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) 
	{
		return new ItemStack(this);
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos,
			PlayerEntity player) 
	{
		return new ItemStack(new BlockItem(this,  properties));
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) 
	{
		return new TileEntityMissileGuidanceSystem();
	}


	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult hit) {
		if(!world.isRemote) {

			TileEntity te = world.getTileEntity(pos);

			if(te instanceof INamedContainerProvider) {
				NetworkHooks.openGui((ServerPlayerEntity)player, (INamedContainerProvider)te, te.getPos());
			}
		}
		return true;
	}


	@Override
	public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) 
	{
		TileEntityMissileGuidanceSystem tileentity = (TileEntityMissileGuidanceSystem) world.getTileEntity(pos);
		ItemStackHandler handler = tileentity.getHandler();

		for (int i = 0; i < handler.getSlots(); ++i)
		{
			ItemStack itemstack = handler.getStackInSlot(i);

			if (!itemstack.isEmpty())
			{
				spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), itemstack);
			}
		}	
		super.onReplaced(state, world, pos, newState, isMoving);
	}

	/**
	 * Ensure NBT data is restored from itemstack on block place
	 */
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

		TileEntity te = worldIn.getTileEntity(pos);
		if (te instanceof IRestorableTileEntity) {
			CompoundNBT nbtTagCompound = stack.getTag();
			if (nbtTagCompound != null)
				((IRestorableTileEntity)te).readRestorableFromNBT(nbtTagCompound);
		}

		worldIn.setBlockState(pos, state.with(FACING, placer.getHorizontalFacing()), 2);
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
			worldIn.addEntity(entityitem);
		}
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}


	@Nullable @Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) 
	{
		return new TileEntityForge();
	}

	@Override
	public boolean hasComparatorInputOverride(BlockState state)
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos)
	{
		return Container.calcRedstone(worldIn.getTileEntity(pos));
	}

	@Nullable @Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(FACING);
	}

	@Override
	public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, IFluidState fluid) {
		if (willHarvest)
			return true; // delay deletion of the block until after getDrops

		return super.removedByPlayer(state, world, pos, player, willHarvest, fluid);
	}
}
