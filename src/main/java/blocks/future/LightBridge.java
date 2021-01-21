package blocks.future;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

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
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import tileEntities.TileEntityLightBridge;

public class LightBridge extends ContainerBlock
{   
	protected static final VoxelShape LIGHT_BRIDGE_NORTH = Block.makeCuboidShape(0.0D, 6.0D, 0.0D, 16.0D, 10.0D, 4.0D);
	protected static final VoxelShape LIGHT_BRIDGE_SOUTH = Block.makeCuboidShape(0.0D, 6.0D, 12.0D, 16.0D, 10.0D, 16.0D);
	protected static final VoxelShape LIGHT_BRIDGE_EAST = Block.makeCuboidShape(12.0D, 6.0D, 0.0D, 16.0D, 10.0D, 16.0D);
	protected static final VoxelShape LIGHT_BRIDGE_WEST = Block.makeCuboidShape(0.0D, 6.0D, 0.0D, 4.0D, 10.0D, 16.0D);

	protected static final VoxelShape LIGHT_BRIDGE_ON = Block.makeCuboidShape(0.0D, 7.5D, 0.0D, 16.0D, 8.5D, 16.0D);

	Item.Properties properties;

	public static final BooleanProperty ON = BooleanProperty.create("on");

	public static final DirectionProperty FACING = DirectionProperty.create("facing", new Predicate<Direction>()
	{
		public boolean apply(@Nullable Direction facing)
		{
			return (facing != Direction.DOWN) && (facing != Direction.UP);
		}
	});

	public LightBridge(String name) 
	{
		super(Block.Properties.create(Material.ANVIL).sound(SoundType.STONE).hardnessAndResistance(1.0F, 20));
		//this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH).with(ON, false));
		this.setRegistryName(name);

		properties = new Item.Properties().maxStackSize(64);

	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		if(state.get(ON).booleanValue())
		{
			switch((Direction)state.get(FACING)) {
			case NORTH:
				return VoxelShapes.combine(LIGHT_BRIDGE_NORTH, LIGHT_BRIDGE_ON, IBooleanFunction.AND);
			case SOUTH:
				return VoxelShapes.combine(LIGHT_BRIDGE_SOUTH, LIGHT_BRIDGE_ON, IBooleanFunction.AND);
			case EAST:
				return VoxelShapes.combine(LIGHT_BRIDGE_EAST, LIGHT_BRIDGE_ON, IBooleanFunction.AND);
			case WEST:
				return VoxelShapes.combine(LIGHT_BRIDGE_WEST, LIGHT_BRIDGE_ON, IBooleanFunction.AND);
			case UP:
				return VoxelShapes.combine(LIGHT_BRIDGE_NORTH, LIGHT_BRIDGE_ON, IBooleanFunction.AND);
			case DOWN:
				return VoxelShapes.combine(LIGHT_BRIDGE_SOUTH, LIGHT_BRIDGE_ON, IBooleanFunction.AND);
			}
		}
		else
		{
			switch((Direction)state.get(FACING)) {
			case NORTH:
				return LIGHT_BRIDGE_NORTH;
			case SOUTH:
				return LIGHT_BRIDGE_SOUTH;
			case EAST:
				return LIGHT_BRIDGE_EAST;
			case WEST:
				return LIGHT_BRIDGE_WEST;
			case UP:
				return LIGHT_BRIDGE_NORTH;
			case DOWN:
				return LIGHT_BRIDGE_SOUTH;
			}
		}
		return LIGHT_BRIDGE_NORTH;	
	}


	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		PlayerEntity player = context.getPlayer();
		return this.getDefaultState().with(FACING, player.getHorizontalFacing());
	}

	public boolean isFullCube(BlockState state)
	{
		return false;
	}

	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks for render
	 */
	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	@Override
	public boolean isSolid(BlockState state) {
		return false;
	}

	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return func_220055_a(worldIn, pos.down(), Direction.UP);
	}

	@Override
	public BlockState getStateForPlacement(BlockState state, Direction facing, BlockState state2, IWorld world,
			BlockPos pos1, BlockPos pos2, Hand hand)
	{
		return this.getDefaultState().with(FACING, facing).with(ON, getPower(world, pos1, world.getBlockState(pos1)) >= 1);
	}


	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) 
	{
		Direction facing = getFacing(world, placer, pos);

		if(facing != null)
			world.setBlockState(pos, this.getDefaultState().with(FACING, facing).with(ON, getPower(world, pos, state) >= 1));
	}

	public Direction getFacing(World world, LivingEntity placer, BlockPos pos)
	{
		Direction facing = placer.getHorizontalFacing();
		BlockPos pos2 = null;


		if(facing.equals(Direction.NORTH))
		{
			pos2 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
		}
		else if(facing.equals(Direction.SOUTH))
		{
			pos2 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
		}
		else if(facing.equals(Direction.WEST))
		{
			pos2 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
		}
		else
		{
			pos2 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
		}

		if(world.getBlockState(pos2).isNormalCube(world, pos2))
		{
			if(world.getBlockState(pos.north()).isNormalCube(world, pos2))
			{
				return Direction.NORTH;
			}
			else if(world.getBlockState(pos.south()).isNormalCube(world, pos2))
			{
				return Direction.SOUTH;
			}
			else if(world.getBlockState(pos.east()).isNormalCube(world, pos2))
			{
				return Direction.EAST;
			}
			else if(world.getBlockState(pos.west()).isNormalCube(world, pos2))
			{
				return Direction.WEST;
			}
			else
			{
				return null;
			}
		}
		else
		{
			return placer.getHorizontalFacing();
		}
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
		builder.add(FACING, ON);
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) 
	{
		return new TileEntityLightBridge();
	}


	@Override
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) 
	{		
		if(getPower(world, pos, state) >= 1)
		{
			world.setBlockState(pos, state.with(ON, true));
		}
		else
		{
			world.setBlockState(pos, state.with(ON, false));	
		}

		Direction facing = state.get(FACING);
		BlockPos pos2 = null;


		if(facing.equals(Direction.NORTH))
		{
			pos2 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
		}
		else if(facing.equals(Direction.SOUTH))
		{
			pos2 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
		}
		else if(facing.equals(Direction.WEST))
		{
			pos2 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
		}
		else
		{
			pos2 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
		}

		if(world.getBlockState(pos2).isNormalCube(world, pos2))
		{
			//if(world.getBlockState(pos2).getBlock() == Blocks.AIR)
			//{
			this.spawnAdditionalDrops(state, world, pos2, this.getItem(world, pos2, state));
			world.setBlockState(pos2, Blocks.AIR.getDefaultState());
		}
	}


	protected int getPower(IWorld world, BlockPos pos, BlockState state)
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
			int j = state.getStrongPower(world, blockpos, element);

			if(j > i)
				i = j;
		}

		return i;
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

}