package items.renaissance;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.World;

public class CustomPainting extends Item
{
	public CustomPainting(String name, Item.Properties builder)
	{
		super(builder);
		this.setRegistryName(name);
		//this.group = History.renaissanceTab;
	}
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context)
	{
		PlayerEntity player = context.getPlayer();
		ItemStack itemstack = context.getItem();
		World world = context.getWorld();

		if (!world.isRemote)
        {
           
		}
		
        return ActionResultType.PASS;
	}
	

}