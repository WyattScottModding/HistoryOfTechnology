package blocks.industrial;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.SoundType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import tileEntities.TileEntityElectricLamp;

public class ElectricLamp extends ContainerBlock
{
	Item.Properties properties;

	public ElectricLamp(String name, Block.Properties blockProperties)
	{
		super(blockProperties.sound(SoundType.STONE).hardnessAndResistance(0.3F, 1.5F));
		this.setRegistryName(name);

		properties = new Item.Properties().maxStackSize(64);
	}

	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return true;
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
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new TileEntityElectricLamp();
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
}