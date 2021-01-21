package blocks.industrial;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import tileEntities.TileEntityMustardGas;

public class BlockMustardGas extends ContainerBlock
{
	Item.Properties properties;

	public BlockMustardGas(String name) 
	{
		super(Block.Properties.create(Material.AIR));
		this.setRegistryName(name);
		
		properties = new Item.Properties().maxStackSize(64);
	}

	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return null;
	}
	
	@Override
	public boolean isFlammable(BlockState state, IBlockReader world, BlockPos pos, Direction face) 
	{
		return true;
	}
	
	@Override
	public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) 
	{
		return 100;
	}
	
	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) 
	{
		return false;
	}
	
    public boolean canCollideCheck(BlockState state, boolean hitIfLiquid)
    {
        return false;
    }
    
    public boolean isFullCube(BlockState state)
    {
        return false;
    }
    
	@Override
	public boolean isAir(BlockState state) {
		return true;
	}
	
	@Override
	public boolean isAir(BlockState state, IBlockReader world, BlockPos pos) {
		return true;
	}

 
    @Override
    public boolean isReplaceable(BlockState state, BlockItemUseContext useContext)
    {
    	return true;
    }

	@Override
	public BlockRenderType getRenderType(BlockState state) 
	{
		return BlockRenderType.INVISIBLE;
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new TileEntityMustardGas();
	}
	
	@Override
	public BlockRenderLayer getRenderLayer()
	{	
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
}