package blocks.future;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import tileEntities.TileEntityLightBridgeBlock;

public class LightBridgeBlock extends ContainerBlock
{
	protected static final VoxelShape LIGHT_BRIDGE_BLOCK = Block.makeCuboidShape(0.0D, 7.5D, 0.0D, 16.0D, 8.5D, 16.0D);

	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

	Item.Properties properties;

	public LightBridgeBlock(String name) 
	{
		super(Block.Properties.create(Material.BARRIER).hardnessAndResistance(100000000, 1000000).sound(SoundType.STONE));
		this.setRegistryName(name);
		this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));

		properties = new Item.Properties().maxStackSize(64);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return LIGHT_BRIDGE_BLOCK;
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

	/**
	 * Called by BlockItems just before a block is actually set in the world, to allow for adjustments to the
	 * IBlockstate
	 */
	@Override
	public BlockState getStateForPlacement(BlockState state, Direction facing, BlockState state2, IWorld world,
			BlockPos pos1, BlockPos pos2, Hand hand) {

		return this.getDefaultState().with(FACING, facing);
	}

	/**
	 * Called by BlockItems after a block is set in the world, to allow post-place logic
	 */
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
	{
		worldIn.setBlockState(pos, state.with(FACING, placer.getHorizontalFacing()), 2);
	}

	@Override
	public boolean isNormalCube(BlockState state, IBlockReader world, BlockPos pos) 
	{
		return false;
	}

	@Override
	public boolean isSolid(BlockState state) 
	{
		return false;
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos,
			PlayerEntity player) 
	{
		return new ItemStack(Item.getItemFromBlock(this));
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) 
	{
		return BlockRenderType.MODEL;
	}

	@Override
	public BlockRenderLayer getRenderLayer()
	{	
		return BlockRenderLayer.CUTOUT;
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
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(FACING);
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new TileEntityLightBridgeBlock();
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}
}
