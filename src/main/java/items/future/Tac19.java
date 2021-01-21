package items.future;

import java.util.ArrayList;
import java.util.List;

import handlers.SoundsHandler;
import init.ItemInit;
import items.medieval.GunBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class Tac19 extends GunBase
{
	private static int delay = 10;
	private static float damage = 0;
	private static float speed = 10;
	private static String animation = "";

	private static Item bullet = ItemInit.ADVANCED_BULLET;

	public Tac19(String name, Item.Properties builder)
	{
		super(name, delay, damage, speed, bullet, animation, builder, ItemTier.DIAMOND);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack itemstack = player.getHeldItem(hand);
		ItemStack bulletStack = findAmmo(player, ItemInit.ADVANCED_BULLET);

		boolean flag = player.isCreative();

		if(isBullet(bulletStack, ItemInit.ADVANCED_BULLET) || flag)
		{
			playSound(world, player);

			float pitch = player.rotationPitch;
			float yaw = player.rotationYaw;

			float f = 1.0F;

			double x = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
			double y = (double)(-MathHelper.sin((pitch) / 180.0F * (float)Math.PI));
			double z = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
			
			world.addParticle(ParticleTypes.SWEEP_ATTACK, player.posX + x + (player.getWidth() / 2), player.posY + player.getEyeHeight(), player.posZ + z + (player.getWidth() / 2), x, y, z);

			if(!world.isRemote)
			{
				ArrayList<LivingEntity> entityList = getMouseOver(player, world);

				ArrayList<Integer> entityIds = new ArrayList<Integer>();

				for(LivingEntity entity : entityList)
				{
					if(!entityIds.contains(entity.getEntityId()))
					{
						float distance = player.getDistance(entity);

						float f2 = 2.0F - (distance / 7.5F);

						double x2 = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f2);
						double y2 = .25 * f2;
						double z2 = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f2);

						entity.addVelocity(x2, y2, z2);
						entity.velocityChanged = true;

						boolean hasVest = entity.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ItemInit.BULLETPROOFVEST;

						int chance = 0;

						if(hasVest)
						{
							chance = (int)(Math.random() * 2);
						}
						if(chance == 0)
						{
							if(distance < 23)
								entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 23 - distance);
						}

						entityIds.add(entity.getEntityId());
					}
				}

				if (!flag)
				{
					bulletStack.shrink(1);
					Item item = itemstack.getItem();
					item.setDamage(itemstack, item.getDamage(itemstack) + 1);
				}
			}
		}

		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemstack);
	}


	public ArrayList<LivingEntity> getMouseOver(PlayerEntity player, World world)
	{
		BlockPos pos = player.getPosition();

		float yaw = player.rotationYaw;
		float pitch = player.rotationPitch;

		List<Entity> list = null;
		ArrayList<LivingEntity> entityList = new ArrayList<LivingEntity>();


		for(int f = 1; f <= 15; f++)
		{
			double x = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
			double y = (double)(-MathHelper.sin((pitch) / 180.0F * (float)Math.PI) * f);
			double z = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);


			AxisAlignedBB entityPos;

			if(f < 8)
				entityPos = new AxisAlignedBB(pos.getX() + x, pos.getY() + y, pos.getZ() + z, pos.getX() + x + 1, pos.getY() + y + 1, pos.getZ() + z + 1);
			else
				entityPos = new AxisAlignedBB(pos.getX() + x - 2, pos.getY() + y - 2, pos.getZ() + z - 2, pos.getX() + x + 3, pos.getY() + y + 3, pos.getZ() + z + 3);

			list = world.getEntitiesWithinAABBExcludingEntity(player, entityPos);

			for(int j = 0; j < list.size(); ++j)
			{
				if(list.get(j) instanceof LivingEntity)
				{
					entityList.add((LivingEntity) list.get(j));
				}
			}
		}

		return entityList;
	}

	@Override
	public void playSound(World world, PlayerEntity player) 
	{
		world.playSound(player, player.posX, player.posY, player.posZ, SoundsHandler.ITEM_TAC_FIRED, SoundCategory.PLAYERS, 1.0F, 1.0F);

		super.playSound(world, player);
	}


	public ItemStack findAmmo(PlayerEntity player, Item bullet)
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

	public boolean isBullet(ItemStack stack, Item bullet)
	{
		return ItemStack.areItemsEqualIgnoreDurability(stack, new ItemStack(bullet));
	}



}