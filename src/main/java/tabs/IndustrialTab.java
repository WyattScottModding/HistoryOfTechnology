package tabs;

import init.ItemInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class IndustrialTab extends ItemGroup
{

	public IndustrialTab()
	{
		super("tabIndustrial");
	}


	@Override
	public ItemStack createIcon() {
        return new ItemStack(ItemInit.FLAMETHROWER);
	}
	
	
}
