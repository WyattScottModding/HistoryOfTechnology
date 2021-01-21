package items.renaissance;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;

public class Coffee extends Item
{
	public Coffee(String name, Item.Properties builder)
	{
		super(builder);
		this.setRegistryName(name);
	}
	
	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.DRINK;
	}
}