package tabs;

import init.ItemInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ClassicalTab extends ItemGroup
{

	public ClassicalTab()
	{
		super("tabClassical");
	}

	@Override
	public ItemStack createIcon() {
        return new ItemStack(ItemInit.HOSE);
	}	
}