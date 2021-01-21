package tabs;

import init.ItemInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class MedievalTab extends ItemGroup
{

	public MedievalTab()
	{
		super("tabMedieval");
	}

	@Override
	public ItemStack createIcon() {
        return new ItemStack(ItemInit.PISTOL);
	}
	
}
