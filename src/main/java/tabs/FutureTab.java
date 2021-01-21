package tabs;

import init.ItemInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class FutureTab extends ItemGroup
{

	public FutureTab()
	{
		super("tabFuture");
	}

	@Override
	public ItemStack createIcon() {
        return new ItemStack(ItemInit.WUNDER_WAFFE);
	}
	
}
