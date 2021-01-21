package blocks.ancient;

import java.util.Random;

import init.BlockInit;
import init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class BlockBasic extends Block
{	
	Item.Properties properties;

	public BlockBasic(String name, Material material, ItemGroup group, float hardness, int light, float resistance) 
	{
		super(Properties.create(Material.ROCK).hardnessAndResistance(hardness, resistance).lightValue(light).sound(SoundType.STONE));
		this.setRegistryName(name);
		this.setDefaultState(this.getDefaultState());
		properties = new Item.Properties().maxStackSize(64).group(group);
	}

	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		if (this == BlockInit.SALTPETRE)
		{
			return new ItemStack(ItemInit.SALTPETRE_DUST);
		}
		return super.getItem(worldIn, pos, state);
	}



	/**
	 * Get the quantity dropped based on the given fortune level
	 */
	public int quantityDroppedWithBonus(int fortune, Random random)
	{
		return this.quantityDropped(random) + random.nextInt(fortune + 1);
	}


	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random random)
	{
		if(this == BlockInit.SALTPETRE)
			return 4 + random.nextInt(2);

		return 1;
	}


	@Override
	public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
		Random rand = world instanceof World ? ((World)world).rand : new Random();
		
		if(silktouch == 1)
			return 0;
		
		int i = 0;

		if (this == Blocks.COAL_ORE)
		{
			i = MathHelper.nextInt(rand, 0, 2);
		}
		else if (this == Blocks.DIAMOND_ORE)
		{
			i = MathHelper.nextInt(rand, 3, 7);
		}
		else if (this == Blocks.EMERALD_ORE)
		{
			i = MathHelper.nextInt(rand, 3, 7);
		}
		else if (this == Blocks.LAPIS_ORE)
		{
			i = MathHelper.nextInt(rand, 2, 5);
		}
		else if (this == Blocks.NETHER_QUARTZ_ORE)
		{
			i = MathHelper.nextInt(rand, 2, 5);
		}

		return i;
		//		}
		//		return 0;
	}

	public ItemStack getItem(World worldIn, BlockPos pos, BlockState state)
	{
		return new ItemStack(this);
	}
}