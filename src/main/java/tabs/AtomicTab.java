package tabs;

import init.ItemInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class AtomicTab extends ItemGroup
{

	public AtomicTab()
	{
		super("tabAtomic");
	}

	@Override
	public ItemStack createIcon() {
        return new ItemStack(ItemInit.URANIUM_INGOT);
	}
	
}
