package blocks.industrial;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;


public class MicroScope extends Block{

	Item.Properties properties;

	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

	protected static final VoxelShape SCOPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	
	public MicroScope(String name)
	{
		super(Block.Properties.create(Material.IRON).sound(SoundType.METAL).variableOpacity());
		this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
		this.setRegistryName(name);

		properties = new Item.Properties().maxStackSize(1);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SCOPE;
	}

	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks for render
	 */
	@Override
	public boolean isNormalCube(BlockState state, IBlockReader world, BlockPos pos) 
	{
		return false;
	}

	/**
	 * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
	 * blockstate.
	 */
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
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos,
			PlayerEntity player) 
	{
		return new ItemStack(new BlockItem(this,  properties));
	}

}