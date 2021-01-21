package blocks.ancient;

import init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class MetalOres extends Block
{	
	Item.Properties properties;
	String name = "";

	public MetalOres(String name, Material material, ItemGroup group, float hardness, int light, float resistance) 
	{
		super(Properties.create(Material.ROCK).hardnessAndResistance(hardness, resistance).lightValue(light).sound(SoundType.STONE));
		this.setRegistryName(name);
		this.setDefaultState(this.getDefaultState());
		this.name = name;

		properties = new Item.Properties().maxStackSize(64).group(group);
	}
	

	/*
	@Override
	public List<ItemStack> getDrops(BlockState state, Builder builder) {

		if (this == BlockInit.SALTPETRE)
		{
			int rand = (int)(Math.random() * 2) + 3;

			drops.add(new ItemStack(ItemInit.SALTPETRE_DUST, rand + fortune));
		}
	}
	 */

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	/*
	@Override
	public int quantityDropped(BlockState state, Random random) {
		if(this == BlockInit.SALTPETRE)
			return 2 + random.nextInt(2);

		return 1;
	}
	 */

	@Override
	public int getExpDrop(BlockState state, IWorldReader reader, BlockPos pos, int fortune, int silktouch)
	{
		World world = reader instanceof World ? (World)reader : null;

		if(world == null) {
			int i = 0;

			if(this == BlockInit.SALTPETRE)
			{
				i = MathHelper.nextInt(RANDOM, 1, 3);
			}

			return i;
		}
		return 0;
	}

	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state)
	{
		/*
		if(name.equals("copper_ore"))
			return new ItemStack(BlockInit.COPPER_ORE);
		if(name.equals("tin_ore"))
			return new ItemStack(BlockInit.TIN_ORE);
		if(name.equals("uranium_ore"))
			return new ItemStack(BlockInit.URANIUM_ORE);
		if(name.equals("aluminium_ore"))
			return new ItemStack(BlockInit.ALUMINIUM_ORE);
		if(name.equals("saltpetre"))
			return new ItemStack(BlockInit.SALTPETRE);
		*/
		
		return new ItemStack(Item.getItemFromBlock(this));
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos,
			PlayerEntity player) 
	{
		return new ItemStack(Item.getItemFromBlock(this));
	}
}