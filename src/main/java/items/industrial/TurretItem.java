package items.industrial;

import entity.EntityTurret;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TurretItem extends Item
{
	public TurretItem(String name, Item.Properties builder)
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
			EntityTurret turret = new EntityTurret(world, pos.getX(), pos.getY(), pos.getZ());
			
            if (itemstack.hasDisplayName())
            {
            	turret.setCustomName(itemstack.getDisplayName());
            }

            world.addEntity(turret);     
            
			if(!player.isCreative())
				itemstack.shrink(1);
			
			return ActionResultType.SUCCESS;
		}
		
        return ActionResultType.PASS;
	}
}