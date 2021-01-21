package blocks.ancient;

import java.util.ArrayList;
import init.BlockInit;
import main.History;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class Rope extends Block
{
	protected static final VoxelShape ROPE = Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 7.0D, 16.0D, 7.0D);

	Item.Properties properties;

	public Rope(String name)
	{
		super(Block.Properties.create(Material.WOOL).hardnessAndResistance(.08F, .4F));
		this.setDefaultState(this.getDefaultState());
		this.setRegistryName(name);


		properties = new Item.Properties().maxStackSize(64).group(History.ancientTab);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return ROPE;
	}

	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) 
	{
		return false;
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) 
	{		
		if(worldIn.getBlockState(pos).getBlock() == BlockInit.ROPE)
		{
			//	if(!worldIn.isRemote)
			//		worldIn.setBlockState(pos.down(), BlockInit.ROPE.getDefaultState());

			return true;
		}
		else if (this.canAttachTo(worldIn, pos.up()))
		{
			return true;
		}

		return false;
	}



	private boolean canAttachTo(IWorldReader world, BlockPos pos)
	{
		BlockState iblockstate = world.getBlockState(pos);

		ArrayList<Block> blockList = new ArrayList<Block>();
		blockList.add(Blocks.ACACIA_LEAVES);
		blockList.add(Blocks.BIRCH_LEAVES);
		blockList.add(Blocks.DARK_OAK_LEAVES);
		blockList.add(Blocks.JUNGLE_LEAVES);
		blockList.add(Blocks.OAK_LEAVES);
		blockList.add(Blocks.SPRUCE_LEAVES);

		return (iblockstate.isNormalCube(world, pos) && !iblockstate.canProvidePower()) || (blockList.contains(iblockstate.getBlock()));
	}

	/**
	 * Called by BlockItems just before a block is actually set in the world, to allow for adjustments to the
	 * IBlockstate
	 */
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		World world = context.getWorld();
		BlockPos pos = context.getPos();
		Direction facing = context.getFace();

		if(world.getBlockState(pos).getBlock() == BlockInit.ROPE)
		{
			return this.getDefaultState();
		}
		else if (facing.getAxis().isHorizontal() && this.canAttachTo(world, pos.offset(facing.getOpposite())))
		{
			return this.getDefaultState();
		}
		else
		{
			for (Direction enumfacing : Direction.Plane.HORIZONTAL)
			{
				if (this.canAttachTo(world, pos.offset(enumfacing.getOpposite())))
				{
					return this.getDefaultState();
				}
			}

			return this.getDefaultState();
		}
	}

	@Override
	public void observedNeighborChange(BlockState state, World world, BlockPos pos, Block changedBlock,
			BlockPos changedBlockPos) 
	{
		if(pos.down().getY() == pos.getY())
		{
			if(world.getBlockState(pos).getBlock() == Blocks.AIR)
			{
				if (!world.isRemote()) { // do not drop items while restoring blockstates, prevents item dupe
					NonNullList<ItemStack> drops = NonNullList.create();
					for (ItemStack stack : drops) {
						spawnAsEntity(world, pos, stack);
					}
				}

				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			}
		}	
		super.observedNeighborChange(state, world, pos, changedBlock, changedBlockPos);
	}
	

	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
			BlockRayTraceResult hit) {
		if(state.getBlock() == BlockInit.ROPE)
		{
			boolean hitGround = false;

			for(int i = pos.getY(); !hitGround && i >= 0; i--)
			{ 
				BlockPos pos2 = new BlockPos(pos.getX(), i, pos.getZ());

				if(world.getBlockState(pos2.down()).getBlock() == Blocks.AIR)
				{
					world.setBlockState(pos2.down(), state, 0);

					if(!player.isCreative())
						player.getHeldItemMainhand().shrink(1);		

					hitGround = true;
				}
				else if(world.getBlockState(pos2.down()).getBlock() != BlockInit.ROPE)
				{
					hitGround = true;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public BlockRenderLayer getRenderLayer()
	{	
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean isLadder(BlockState state, IWorldReader world, BlockPos pos, LivingEntity entity) {
		return true;
	}


	/*
	public static boolean isConnectedTo(IBlockAccess worldIn, BlockPos pos, BlockState state, Direction direction)
	{
		BlockPos blockpos = pos.offset(direction);
		BlockState iblockstate = worldIn.getBlockState(blockpos);
		Block block = iblockstate.getBlock();

		return block == BlockInit.ROPE;
	}
	 */
}