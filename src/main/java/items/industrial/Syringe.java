package items.industrial;

import init.ItemInit;
import init.PotionInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class Syringe extends Item
{
	public Syringe(String name, Item.Properties builder)
	{
		super(builder);
		this.setRegistryName(name);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) 
	{
		if(!world.isRemote)
		{
			ItemStack stack = player.getHeldItemMainhand();

			stack.shrink(1);

			if(player.isPotionActive(PotionInit.HIV_EFFECT))
				player.inventory.addItemStackToInventory(new ItemStack(ItemInit.SYRINGE_HIV_BLOOD));
			else
				player.inventory.addItemStackToInventory(new ItemStack(ItemInit.SYRINGE_BLOOD));

			return new ActionResult<ItemStack>(ActionResultType.SUCCESS, player.getHeldItem(hand));
		}

		return new ActionResult<ItemStack>(ActionResultType.PASS, player.getHeldItem(hand));
	}


	@Override
	public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity)
	{
		if(entity instanceof LivingEntity && !player.world.isRemote)
		{
			LivingEntity entityLiving = (LivingEntity) entity;

			stack.shrink(1);

			if(entityLiving.getActivePotionEffect(PotionInit.HIV_EFFECT) != null)
				player.inventory.addItemStackToInventory(new ItemStack(ItemInit.SYRINGE_HIV_BLOOD));
			else
				player.inventory.addItemStackToInventory(new ItemStack(ItemInit.SYRINGE_BLOOD));
		}

		return true;
	}
}