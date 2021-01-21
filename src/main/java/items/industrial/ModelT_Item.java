package items.industrial;

import entity.EntityModelT;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ModelT_Item extends Item
{
	public ModelT_Item(String name, Item.Properties builder)
	{
		super(builder);
		this.setRegistryName(name);
	}
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) 
	{
		PlayerEntity player = context.getPlayer();
		ItemStack itemstack = context.getItem();
		World world = context.getWorld();
		BlockPos pos = context.getPos();

		if (!world.isRemote)
        {
            EntityModelT modelT = new EntityModelT(world, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.0625D, (double)pos.getZ() + 0.5D);

            if (itemstack.hasDisplayName())
            {
            	modelT.setCustomName(itemstack.getDisplayName());
            }

            world.addEntity(modelT);
        
			if(!player.isCreative())
				itemstack.shrink(1);
			
			return ActionResultType.SUCCESS;
		}
		
        return ActionResultType.PASS;
	}


}