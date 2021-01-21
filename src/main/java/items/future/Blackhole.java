package items.future;

import entity.EntityBlackHole;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Blackhole extends Item
{

	public Blackhole(String name, Item.Properties builder)
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
			EntityBlackHole blackhole = new EntityBlackHole(world, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.0625D, (double)pos.getZ() + 0.5D);

			if (itemstack.hasDisplayName())
			{
				blackhole.setCustomName(itemstack.getDisplayName());
			}

			world.addEntity(blackhole);

			if(!player.isCreative())
				itemstack.shrink(1);

			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
	}
}