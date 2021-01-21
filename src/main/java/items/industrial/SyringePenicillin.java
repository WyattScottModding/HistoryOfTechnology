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

public class SyringePenicillin extends Item
{
	public SyringePenicillin(String name, Item.Properties builder)
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
			boolean curedEntity = false;

			if(player.isPotionActive(PotionInit.TUBERCULOSIS_EFFECT))
			{
				player.removeActivePotionEffect(PotionInit.TUBERCULOSIS_EFFECT);
				curedEntity = true;
			}

			if(player.isPotionActive(PotionInit.PLAGUE_EFFECT))
			{
				player.removeActivePotionEffect(PotionInit.PLAGUE_EFFECT);
				curedEntity = true;
			}

			if(curedEntity && !player.isCreative())
			{
				stack.shrink(1);
				player.inventory.addItemStackToInventory(new ItemStack(ItemInit.SYRINGE));
			}
			
			if(curedEntity)
			{
		        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, player.getHeldItem(hand));
			}
		}

		
        return new ActionResult<ItemStack>(ActionResultType.PASS, player.getHeldItem(hand));
	}


	@Override
	public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity)
	{
		if(entity instanceof LivingEntity && !player.world.isRemote)
		{
			LivingEntity entityLiving = (LivingEntity) entity;

			boolean curedEntity = false;

			if(entityLiving.isPotionActive(PotionInit.TUBERCULOSIS_EFFECT))
			{
				entityLiving.removePotionEffect(PotionInit.TUBERCULOSIS_EFFECT);
				curedEntity = true;
			}

			if(entityLiving.isPotionActive(PotionInit.PLAGUE_EFFECT))
			{
				entityLiving.removePotionEffect(PotionInit.PLAGUE_EFFECT);
				curedEntity = true;
			}

			if(curedEntity && !player.isCreative())
			{
				stack.shrink(1);
				player.inventory.addItemStackToInventory(new ItemStack(ItemInit.SYRINGE));
			}
		}


		return true;
	}
}