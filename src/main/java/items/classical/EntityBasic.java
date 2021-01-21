package items.classical;

import net.minecraft.item.Item;

public class EntityBasic extends Item
{

	public EntityBasic(String name, Item.Properties builder)
	{
		super(builder);
		this.setRegistryName(name);
	}
}