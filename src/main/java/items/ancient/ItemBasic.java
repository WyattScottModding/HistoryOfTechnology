package items.ancient;

import net.minecraft.item.Item;

public class ItemBasic extends Item
{
	public ItemBasic(String name, Item.Properties builder)
	{
		super(builder);
		this.setRegistryName(name);

	}

}