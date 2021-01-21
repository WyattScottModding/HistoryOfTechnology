package items.future;

import entity.EntityEnergy;
import init.ItemInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class WunderWaffe extends Item
{
	public WunderWaffe(String name, Item.Properties builder)
	{
		super(builder);
		this.setRegistryName(name);
	}

	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack itemstack = player.getHeldItem(hand);

		ItemStack energy = findAmmo(player);

		boolean flag = player.isCreative();

		if(isEnergy(energy) || flag)
		{
			if (!world.isRemote)
			{
				EntityEnergy entityenergy = new EntityEnergy(world, player, player.posX + ((double)player.getWidth() * .5), player.posY + (double)player.getEyeHeight() - (double)player.getYOffset() - .4, player.posZ + (double)player.getWidth() * .5);
				entityenergy.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 8.0F, 0.0F);
				world.addEntity(entityenergy);

				if (!flag)
				{
					energy.shrink(1);
				}
			}
		}

		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemstack);
	}


	private ItemStack findAmmo(PlayerEntity player)
	{
		player.inventory.getCurrentItem();
		if (ItemStack.areItemsEqualIgnoreDurability(player.getHeldItem(Hand.OFF_HAND), new ItemStack(ItemInit.ENERGY_ITEM)))
		{
			return player.getHeldItem(Hand.OFF_HAND);
		}
		player.inventory.getCurrentItem();
		if (ItemStack.areItemsEqualIgnoreDurability(player.getHeldItem(Hand.MAIN_HAND), new ItemStack(ItemInit.ENERGY_ITEM)))
		{
			return player.getHeldItem(Hand.MAIN_HAND);
		}
		else
		{
			for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
			{
				ItemStack itemstack = player.inventory.getStackInSlot(i);

				player.inventory.getCurrentItem();
				if (ItemStack.areItemsEqualIgnoreDurability(itemstack, new ItemStack(ItemInit.ENERGY_ITEM)))
				{
					return itemstack;
				}
			}

			return ItemStack.EMPTY;
		}
	}

	protected boolean isEnergy(ItemStack stack)
	{
		return !stack.isEmpty();
	}



}
