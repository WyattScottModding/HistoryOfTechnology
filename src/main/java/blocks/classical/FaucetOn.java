package blocks.classical;

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
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import tileEntities.TileEntityFaucet;

public class FaucetOn extends ContainerBlock
{
	Item.Properties properties;

	public static final DirectionProperty FACING = DirectionProperty.create("facing", new Predicate<Direction>()
	{
		public boolean apply(@Nullable Direction facing)
		{
			return (facing != Direction.DOWN) && (facing != Direction.UP);
		}
	});

	protected static final VoxelShape FAUCET_NORTH = Block.makeCuboidShape(6.4D, 4.8D, 0.0D, 9.6D, 11.2D, 4.8D);
	protected static final VoxelShape FAUCET_SOUTH = Block.makeCuboidShape(6.4D, 4.8D, 11.2D, 9.6D, 11.2D, 16.0D);
	protected static final VoxelShape FAUCET_EAST = Block.makeCuboidShape(11.2D, 4.8D, 6.4D, 16.0D, 11.2D, 9.6D);
	protected static final VoxelShape FAUCET_WEST = Block.makeCuboidShape(0.0D, 4.8D, 6.4D, 4.8D, 11.2D, 9.6D);


	public FaucetOn(String name)
	{
		super(Block.Properties.create(Material.IRON).hardnessAndResistance(5, 20).sound(SoundType.METAL));
		this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
		this.setRegistryName(name);

		properties = new Item.Properties().maxStackSize(64);

	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		if(state.get(FACING) == Direction.NORTH)
		{
			return FAUCET_NORTH;
		}
		else if(state.get(FACING) == Direction.SOUTH)
		{
			return FAUCET_SOUTH;
		}
		else if(state.get(FACING) == Direction.EAST)
		{
			return FAUCET_EAST;
		}
		else
		{
			return FAUCET_WEST;
		}
	}


	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
			BlockRayTraceResult hit) {
		if(!world.isRemote)
		{
			Block blockDown = world.getBlockState(pos.down()).getBlock();

			if(blockDown != Blocks.WATER)
			{
				world.setBlockState(pos.down(), Blocks.WATER.getDefaultState());
			}
			else if(blockDown == Blocks.WATER)
			{
				world.setBlockState(pos.down(), Blocks.AIR.getDefaultState());
			}
		}
		return true;
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
	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
		PlayerEntity player = context.getPlayer();

		return this.getDefaultState().with(FACING, player.getHorizontalFacing());
	}

	/**
	 * Checks if this block can be placed exactly at the given position.
	 */
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	{
		for (Direction enumfacing : FACING.getAllowedValues())
		{
			if (this.canPlaceAt(worldIn, pos, enumfacing))
			{
				return true;
			}
		}
		return false;
	}

	private boolean canPlaceAt(World worldIn, BlockPos pos, Direction facing)
	{
		BlockPos blockpos = pos.offset(facing.getOpposite());
		BlockState iblockstate = worldIn.getBlockState(blockpos);
		Block block = iblockstate.getBlock();
		VoxelShape blockfaceshape = iblockstate.getShape(worldIn, blockpos);

		if (facing != Direction.UP && facing != Direction.DOWN)
		{
			return iblockstate.isNormalCube(worldIn, blockpos);
		}
		else
		{
			return false;
		}
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
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) 
	{
		Direction facing = getFacing(world, placer, pos);

		if(facing != null)
			world.setBlockState(pos, this.getDefaultState().with(FACING, placer.getHorizontalFacing()));
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
	public TileEntity createNewTileEntity(IBlockReader worldIn) 
	{
		return new TileEntityFaucet();
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
}