package items.industrial;

import init.ItemInit;
import init.PotionInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SyringeHIVBlood extends Item
{
	public boolean containsHIV = false;

	public SyringeHIVBlood(String name, Item.Properties builder)
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
			player.inventory.addItemStackToInventory(new ItemStack(ItemInit.SYRINGE));
			player.addPotionEffect(new EffectInstance(PotionInit.HIV_EFFECT, 10000, 0));

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
			player.inventory.addItemStackToInventory(new ItemStack(ItemInit.SYRINGE));
			entityLiving.addPotionEffect(new EffectInstance(PotionInit.HIV_EFFECT, 10000, 0));
		}

		return true;
	}


}