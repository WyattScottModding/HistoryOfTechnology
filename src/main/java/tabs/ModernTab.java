package tabs;

import init.ItemInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModernTab extends ItemGroup
{

	public ModernTab()
	{
		super("tabModern");
	}

	@Override
	public ItemStack createIcon() {
        return new ItemStack(ItemInit.THREAT_GRENADE);
	}
	
}
