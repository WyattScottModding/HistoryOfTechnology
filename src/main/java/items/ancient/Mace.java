package items.ancient;

import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.item.SwordItem;

public class Mace extends SwordItem
{
	
	public Mace(String name, ItemTier tier, int damage, Item.Properties builder)
	{
		super(tier, damage, damage, builder);
		this.setRegistryName(name);
		
	}

}