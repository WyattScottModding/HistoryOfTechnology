package init;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import net.minecraft.item.ItemStack;

public class ForgeRecipes
{
	public static final ForgeRecipes INSTANCE = new ForgeRecipes();
	public final Table<ItemStack, ItemStack, ItemStack> smeltingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	public final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

	public static ForgeRecipes getInstance()
	{
		return INSTANCE;
	}

	private ForgeRecipes()
	{
		addForgeRecipes(new ItemStack(ItemInit.COPPER_INGOT), new ItemStack(ItemInit.TIN_INGOT), new ItemStack(ItemInit.BRONZE_INGOT), 1.0F);
		addForgeRecipes(new ItemStack(ItemInit.TIN_INGOT), new ItemStack(ItemInit.COPPER_INGOT), new ItemStack(ItemInit.BRONZE_INGOT), 1.0F);
		//addForgeRecipes(new ItemStack(ItemInit.LODESTONE_INGOT), new ItemStack(ItemInit.LODESTONE_INGOT), new ItemStack(Items.IRON_INGOT), 1.0F);

	}

	public void addForgeRecipes(ItemStack input1, ItemStack input2, ItemStack result, float experience)
	{
		if(getForgeResult(input1, input2) != ItemStack.EMPTY)
		{
			return;
		}
		this.smeltingList.put(input1, input2, result);
		this.experienceList.put(result, Float.valueOf(experience));
	}

	public ItemStack getForgeResult(ItemStack input1, ItemStack input2)
	{
		for(Entry<ItemStack, Map<ItemStack, ItemStack>>  entry : this.smeltingList.columnMap().entrySet())
		{
			if(input1.isItemEqualIgnoreDurability((ItemStack)entry.getKey()));
			{
				for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet())
				{
					if(input2.isItemEqualIgnoreDurability((ItemStack)entry.getKey()));
					{						
						return (ItemStack)ent.getValue();
					}
				}
			}
		}

		return ItemStack.EMPTY;
	}
	
	public Table<ItemStack, ItemStack, ItemStack> getDualSmeltingList()
	{
		return this.smeltingList;
	}
	
	public float getForgeExperience(ItemStack stack)
	{
		for(Entry<ItemStack, Float> entry : this.experienceList.entrySet())
		{
			if(stack.isItemEqualIgnoreDurability((ItemStack)entry.getKey()))
			{
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
	
	public boolean isForgeItemStack(ItemStack input)
	{	
		ArrayList<ItemStack> stacklist = new ArrayList<ItemStack>();
		stacklist.add(new ItemStack(ItemInit.COPPER_INGOT));
		stacklist.add(new ItemStack(ItemInit.TIN_INGOT));
		
		
		for(ItemStack element : stacklist)
		{
			if(input.areItemsEqualIgnoreDurability(input, element))
			{
				return true;
			}
		}
		return false;
		
	}
}
