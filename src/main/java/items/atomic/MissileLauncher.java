package items.atomic;

import entity.EntityTrackingMissile;
import init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class MissileLauncher extends SwordItem
{
	public boolean guided = false;
	private final int delayMax;
	private int delay = 30;
	private Item bullet = ItemInit.MISSILE;

	public MissileLauncher(String name, boolean guided, Item.Properties builder, ItemTier tier)
	{
		super(tier, 1, 1, builder);
		this.setRegistryName(name);
		this.guided = guided;
		this.delayMax = delay;
	}


	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
	{
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		ItemStack bullets = findAmmo(playerIn);
		boolean flag = playerIn.isCreative();

		worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

		if(delay == 0)
		{			
			if(isBullet(bullets) || flag)
			{
				if (!worldIn.isRemote)
				{
					System.out.println("Not Remote");

					EntityTrackingMissile trackingmissile = new EntityTrackingMissile(worldIn, playerIn, guided, playerIn.rotationPitch ,playerIn.rotationYaw);
					trackingmissile.fireMissile(playerIn.rotationYaw, playerIn.rotationPitch);
					worldIn.addEntity(trackingmissile);


					if (!flag)
					{
						bullets.shrink(1);
						Item item = itemstack.getItem();
						item.setDamage(itemstack, item.getDamage(itemstack) + 1);
					}

					delay = delayMax;
					return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemstack);
				}
				else
				{
					playSound(worldIn, playerIn);
				}
			}
		}

		//playerIn.addStat(StatList.ITEM_USED);
		return new ActionResult<ItemStack>(ActionResultType.FAIL, itemstack);
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{		
		if(delay > 0)
		{
			delay--;
		}
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}


	public ItemStack findAmmo(PlayerEntity player)
	{
		player.inventory.getCurrentItem();
		if (ItemStack.areItemsEqualIgnoreDurability(player.getHeldItem(Hand.OFF_HAND), new ItemStack(bullet)))
		{
			return player.getHeldItem(Hand.OFF_HAND);
		}
		player.inventory.getCurrentItem();
		if (ItemStack.areItemsEqualIgnoreDurability(player.getHeldItem(Hand.MAIN_HAND), new ItemStack(bullet)))
		{
			return player.getHeldItem(Hand.MAIN_HAND);
		}
		else
		{
			for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
			{
				ItemStack itemstack = player.inventory.getStackInSlot(i);

				player.inventory.getCurrentItem();
				if (ItemStack.areItemsEqualIgnoreDurability(itemstack, new ItemStack(bullet)))
				{
					return itemstack;
				}
			}

			return ItemStack.EMPTY;
		}
	}

	public boolean isBullet(ItemStack stack)
	{
		return ItemStack.areItemsEqualIgnoreDurability(stack, new ItemStack(bullet));
	}

	public void playSound(World world, PlayerEntity player)
	{

	}
}