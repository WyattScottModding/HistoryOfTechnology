package items.medieval;

import entity.EntityBullet;
import handlers.EntityRegistry;
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
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent.MissingMappings.Action;

public class GunBase extends SwordItem
{
	private final int delayMax;
	private int delay = 60;
	private float damage = 10;
	private float speed = 5.0F;
	private Item bullet = ItemInit.BULLET;
	private String animation = "crit";

	public GunBase(String name, int delay, float damage, float speed, Item bullet, String animation, Item.Properties builder, ItemTier tier)
	{
		super(tier, (int)damage, speed, builder);
		this.setRegistryName(name);

		this.delayMax = delay;
		this.damage = damage;
		this.bullet = bullet;
		this.speed = speed;
		this.delay = delay;
		this.animation = animation;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack itemstack = player.getHeldItem(hand);

		ItemStack bullets = findAmmo(player);

		boolean flag = player.isCreative();

		if(delay == 0)
		{			
			if(isBullet(bullets) || flag)
			{
				if (!world.isRemote)
				{
					int chance = (int) (Math.random() * 10);

					if(chance != 0)
					{
						EntityBullet entitybullet = new EntityBullet(EntityRegistry.BULLET, world, player, player.posX + ((double)player.getWidth() * .5), player.posY + (double)player.getEyeHeight() - (double)player.getYOffset() - .4, player.posZ + (double)player.getWidth() * .5, damage, animation);
						entitybullet.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, speed, 0.0F);
						world.addEntity(entitybullet);
					}
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
					playSound(world, player);
				}
			}
		}

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
