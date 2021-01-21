package items.future;

import java.util.List;

import entity.EntityBullet;
import handlers.EntityRegistry;
import handlers.SoundsHandler;
import init.ItemInit;
import items.medieval.GunBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class IMR extends GunBase
{
	private boolean fired = false;
	private static int delay = 0;
	private int bullets = 0;
	private ItemStack bulletStack;
	private static float speed = 10;
	private static float damage = 0;
	private static String animation = "crit";


	private static Item bullet = ItemInit.BULLET;


	public IMR(String name, Item.Properties builder)
	{
		super(name, delay, damage, speed, bullet, animation, builder, ItemTier.DIAMOND);
	}


	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack itemstack = player.getHeldItem(hand);

		if(!fired && itemstack.getItem() == this)
		{
			bulletStack = findAmmo(player);

			boolean flag = player.isCreative();

			if((isBullet(bulletStack) && bulletStack.getCount() >= 4) || flag)
			{
				playSound(world, player);

				if (!world.isRemote)
				{
					fired = true;
					bullets = 4;
					delay = 5;

					EntityBullet entitybullet = new EntityBullet(EntityRegistry.BULLET, world, player, player.posX + ((double)player.getWidth() * .5), player.posY + (double)player.getEyeHeight() - (double)player.getYOffset() - .4, player.posZ + (double)player.getWidth() * .5, damage, animation);
					entitybullet.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, speed, 0.0F);
					world.addEntity(entitybullet);
				}
				return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemstack);
			}
		}
		return new ActionResult<ItemStack>(ActionResultType.FAIL, itemstack);
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entity, int itemSlot, boolean isSelected)
	{		
		Entity entityLiving = entity.getEntity();
		World world = entity.getEntityWorld();

		if(entityLiving instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) entityLiving;

			if(fired && !world.isRemote)
			{
				boolean flag = player.isCreative();

				if(delay > 0)
					delay--;
				else if(delay == 0 && bullets > 0 && bulletStack.getCount() > 0)
				{
					getMouseOver(player, world);
					bullets--;
					delay = 5;

					if (!flag && bullet != null)
					{	
						bulletStack.shrink(1);
					}

				}

				if(bullets == 0 && delay == 0)
				{
					fired = false;
				}
			}

			if(isSelected && !world.isRemote && (int)world.getGameTime() % 400 == 0)
			{
				player.addItemStackToInventory(new ItemStack(ItemInit.BULLET));
			}
		}
		super.inventoryTick(stack, worldIn, entity, itemSlot, isSelected);
	}


	public void getMouseOver(PlayerEntity player, World world)
	{
		BlockPos pos = player.getPosition();

		float yaw = player.rotationYaw;
		float pitch = player.rotationPitch;

		List<Entity> list = null;

		for(int f = 1; f <= 80; f++)
		{
			double x = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
			double y = (double)(-MathHelper.sin((pitch) / 180.0F * (float)Math.PI) * f);
			double z = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);


			AxisAlignedBB entityPos;

			if(f < 10)
				entityPos = new AxisAlignedBB(pos.getX() + x, pos.getY() + y, pos.getZ() + z, pos.getX() + x + 1, pos.getY() + y + 1, pos.getZ() + z + 1);
			else
				entityPos = new AxisAlignedBB(pos.getX() + x - 1, pos.getY() + y - 1, pos.getZ() + z - 1, pos.getX() + x + 2, pos.getY() + y + 2, pos.getZ() + z + 2);

			list = world.getEntitiesWithinAABBExcludingEntity(player, entityPos);


			for(int j = 0; j < list.size(); ++j)
			{
				Entity entity = list.get(j);

				if(entity instanceof LivingEntity)
				{
					LivingEntity entityLiving = (LivingEntity) entity;

					float distance = player.getDistance(entity);

					boolean hasVest = entityLiving.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ItemInit.BULLETPROOFVEST;

					int chance = 0;

					if(hasVest)
					{
						chance = (int)(Math.random() * 4);
					}
					if(chance == 0)
						entityLiving.attackEntityFrom(DamageSource.causePlayerDamage(player), 8 - (distance / 10F));
				}
			}
		}
	}


	@Override
	public void playSound(World world, PlayerEntity player) 
	{
		world.playSound(player, player.posX, player.posY, player.posZ, SoundsHandler.ITEM_IMR_FIRED, SoundCategory.PLAYERS, 1.0F, 1.0F);

		super.playSound(world, player);
	}
}