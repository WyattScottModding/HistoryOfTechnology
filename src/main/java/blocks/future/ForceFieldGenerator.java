package blocks.future;

import init.BlockInit;
import main.History;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import tileEntities.TileEntityForceField;

public class ForceFieldGenerator extends ContainerBlock
{	
	protected static final VoxelShape FORCE_FIELD = Block.makeCuboidShape(0.0D, 6.0D, 0.0D, 16.0D, 30.0D, 16.0D);

	Item.Properties properties;

	public ForceFieldGenerator(String name)
	{
		super(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.5F, 16F));
		this.setRegistryName(name);

		properties = new Item.Properties().maxStackSize(64).group(History.futureTab);
	}

	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return FORCE_FIELD;
	}
	
	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks for render
	 */
	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
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
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) 
	{
		return new ItemStack(this);
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos,
			PlayerEntity player)
	{
		return new ItemStack(BlockInit.FORCE_FIELD_GENERATOR);
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) 
	{
		return new TileEntityForceField();
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
}
