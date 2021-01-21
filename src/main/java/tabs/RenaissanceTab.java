package tabs;

import init.ItemInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class RenaissanceTab extends ItemGroup
{

	public RenaissanceTab()
	{
		super("tabRenaissance");
	}

	@Override
	public ItemStack createIcon() {
        return new ItemStack(ItemInit.PARACHUTE);
	}
}