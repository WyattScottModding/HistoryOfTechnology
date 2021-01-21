package blocks.renaissance;

import init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class CoffeePlant extends CropsBlock
{
	private static final VoxelShape[] COFFEE = new VoxelShape[] {Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};
	private static final Properties builder = Properties.create(Material.PLANTS).sound(SoundType.PLANT);
	Item.Properties properties;

	public CoffeePlant(String name)
	{
		super(builder);
		this.setDefaultState(this.getDefaultState());
		this.setRegistryName(name);

		properties = new Item.Properties();
	}

	/*
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, BlockState state, PlayerEntity player,
			Hand hand, Direction facing, float hitX, float hitY, float hitZ) 
	{
		if(!world.isRemote)
		{
			if(this.isMaxAge(state))
			{
				int random = (int)(Math.random()*3) + 2;

				world.addEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ItemInit.COFFEE_BEAN, random)));
				world.setBlockState(pos, this.withAge(0));
				return true;
			}
		}
		return false;
	}
	 */
	
	@Override
	protected IItemProvider getSeedsItem()
	{
		return ItemInit.COFFEEBEAN;
	}

	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(ItemInit.COFFEEBEAN);
	}
	
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return COFFEE[((Integer)state.get(this.getAgeProperty())).intValue()];
	}
}