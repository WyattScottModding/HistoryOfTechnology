package blocks.industrial;

import java.util.ArrayList;
import java.util.List;

import init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import tileEntities.TileEntityLightSource;

public class LightSource extends ContainerBlock
{
	Item.Properties properties;

	public static List<Item> lightSourceList = new ArrayList<Item>() 
	{
		{
			add(ItemInit.FLASHLIGHT);
		}
	};

	public LightSource(String name)
	{
		super(Block.Properties.create(Material.AIR).lightValue(16));
		this.setRegistryName(name);
		this.setDefaultState(this.getDefaultState());

		properties = new Item.Properties().maxStackSize(64);
	}
	
	@Override
	public int tickRate(IWorldReader worldIn) {
		return 1;
	}

	public static boolean isLightEmittingItem(Item item)
	{
		return lightSourceList.contains(item);
	}

	@Override
	public boolean canBeConnectedTo(BlockState state, IBlockReader world, BlockPos pos, Direction facing) {
		return false;
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) 
	{
		return BlockRenderType.INVISIBLE;
	}

	@Override
	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
	{
		return;
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) 
	{
		return new TileEntityLightSource();
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
	public boolean isNormalCube(BlockState state, IBlockReader world, BlockPos pos) {
		return false;
	}
	
	@Override
	public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
		return true;
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