package tabs;

import init.ItemInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class AncientTab extends ItemGroup
{

	public AncientTab()
	{
		super("tabAncient");
	}

	@Override
	public ItemStack createIcon() {
        return new ItemStack(ItemInit.SPEAR);
	}
	
}
