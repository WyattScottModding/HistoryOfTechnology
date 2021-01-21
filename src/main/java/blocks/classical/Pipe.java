package blocks.classical;

import init.BlockInit;
import main.History;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import tileEntities.TileEntityPipe;

public class Pipe extends ContainerBlock
{	
	public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
	public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
	public static final BooleanProperty EAST = BlockStateProperties.EAST;
	public static final BooleanProperty WEST = BlockStateProperties.WEST;
	public static final BooleanProperty UP = BlockStateProperties.UP;
	public static final BooleanProperty DOWN = BlockStateProperties.DOWN;

	Item.Properties properties;

	protected static VoxelShape base = Block.makeCuboidShape(6.0D, 6.0D, 6.0D, 10.0D, 10.0D, 10.0D);
	protected static final VoxelShape north = Block.makeCuboidShape(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 6.0D);
	protected static final VoxelShape south = Block.makeCuboidShape(6.0D, 6.0D, 10.0D, 10.0D, 10.0D, 16.0D);
	protected static final VoxelShape east = Block.makeCuboidShape(10.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);
	protected static final VoxelShape west = Block.makeCuboidShape(0.0D, 6.0D, 6.0D, 6.0D, 10.0D, 10.0D);
	protected static final VoxelShape up = Block.makeCuboidShape(6.0D, 10.0D, 6.0D, 10.0D, 16.0D, 10.0D);
	protected static final VoxelShape down = Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 6.0D, 10.0D);


	public Pipe(String name)
	{
		super(Block.Properties.create(Material.IRON).hardnessAndResistance(5, 20).sound(SoundType.METAL));
		
		this.setDefaultState(this.getDefaultState().with(NORTH, false).with(SOUTH, false).with(EAST, false).with(WEST, false).with(UP, false).with(DOWN, false));
		this.setRegistryName(name);
		properties = new Item.Properties().maxStackSize(64).group(History.classicalTab);
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return pipeBoundingBox(worldIn, state, pos);
	}

	public static VoxelShape pipeBoundingBox(IBlockReader world, BlockState state, BlockPos pos)
	{
		//this.blockState.getBaseState().get(DOWN);

		boolean northBool = Boolean.valueOf(isConnectedTo(world, pos, state, Direction.NORTH));
		boolean southBool = Boolean.valueOf(isConnectedTo(world, pos, state, Direction.SOUTH));
		boolean eastBool = Boolean.valueOf(isConnectedTo(world, pos, state, Direction.EAST));
		boolean westBool = Boolean.valueOf(isConnectedTo(world, pos, state, Direction.WEST));
		boolean upBool = Boolean.valueOf(isConnectedTo(world, pos, state, Direction.UP));
		boolean downBool = Boolean.valueOf(isConnectedTo(world, pos, state, Direction.DOWN));


		if(southBool)
		{
			base = VoxelShapes.combine(base, south, IBooleanFunction.AND);
		}
		if(northBool)
		{
			base = VoxelShapes.combine(base, north, IBooleanFunction.AND);
		}
		if(eastBool)
		{
			base = VoxelShapes.combine(base, east, IBooleanFunction.AND);
		}
		if(westBool)
		{
			base = VoxelShapes.combine(base, west, IBooleanFunction.AND);
		}
		if(upBool)
		{
			base = VoxelShapes.combine(base, up, IBooleanFunction.AND);
		}
		if(downBool)
		{
			base = VoxelShapes.combine(base, down, IBooleanFunction.AND);
		}

		return base;
	}

	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) 
	{
		return false;
	}

	@Override
	public boolean isSolid(BlockState state) 
	{
		return false;
	}

	@Override
	public BlockRenderLayer getRenderLayer()
	{	
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(NORTH, SOUTH, EAST, WEST, UP, DOWN);
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos,
			PlayerEntity player)
	{
		return new ItemStack(BlockInit.PIPE);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
		return this.getDefaultState().with(NORTH, false).with(SOUTH, false).with(EAST, false).with(WEST, false).with(UP, false).with(DOWN, false);
	}


	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) 
	{
		worldIn.setBlockState(pos, this.getDefaultState().with(NORTH, false).with(SOUTH, false).with(EAST, false).with(WEST, false).with(UP, false).with(DOWN, false));
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) 
	{
		return BlockRenderType.MODEL;
	}



	public static boolean isConnectedTo(IBlockReader worldIn, BlockPos pos, BlockState state, Direction direction)
	{
		BlockPos blockpos = pos.offset(direction);
		BlockState iblockstate = worldIn.getBlockState(blockpos);
		Block block = iblockstate.getBlock();

		//Get the water level nearby
		if(block == Blocks.WATER)
		{
			IntegerProperty LEVEL = IntegerProperty.create("level", 0, 15);
			int size = worldIn.getBlockState(blockpos).get(LEVEL);

			//If the water is a full water block
			if(size == 0)
			{
				//If there is a water block nearby, then it has a water level of 0
				//nearbyWaterSource = true;
				return true;
			}
		}

		return (block == BlockInit.PIPE) || (block == BlockInit.PIPE_WATER) || (block == BlockInit.FAUCET) || (block == BlockInit.FAUCET_ON);
	}


	/**
	 * Get the actual Block state of this Block at the given position. This applies properties not visible in the
	 * metadata, such as fence connections.
	 */
	@Override
	public BlockState getExtendedState(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return state.with(NORTH, Boolean.valueOf(isConnectedTo(worldIn, pos, state, Direction.NORTH))).with(EAST, Boolean.valueOf(isConnectedTo(worldIn, pos, state, Direction.EAST))).with(SOUTH, Boolean.valueOf(isConnectedTo(worldIn, pos, state, Direction.SOUTH))).with(WEST, Boolean.valueOf(isConnectedTo(worldIn, pos, state, Direction.WEST))).with(UP, Boolean.valueOf(isConnectedTo(worldIn, pos, state, Direction.UP))).with(DOWN, Boolean.valueOf(isConnectedTo(worldIn, pos, state, Direction.DOWN)));
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) 
	{
		return new TileEntityPipe();
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
}