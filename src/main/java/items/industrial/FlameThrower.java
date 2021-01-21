package items.industrial;

import java.util.Random;

import entity.EntityFire;
import init.ItemInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class FlameThrower extends Item
{

	public FlameThrower(String name, Item.Properties builder)
	{
		super(builder);
		this.setRegistryName(name);
	}	

	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack itemstack = player.getHeldItem(hand);

		ItemStack flametank = player.getItemStackFromSlot(EquipmentSlotType.CHEST);

		if (!world.isRemote && (flametank.getItem() == ItemInit.FLAMETHROWER_TANK || player.isCreative()))
		{			
			world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.PLAYERS, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

			Random rand = new Random();

			EntityFire entityfire = new EntityFire(world, player, player.posX + (rand.nextDouble() - 0.5D) * (double)player.getWidth(), player.posY + (double)player.getEyeHeight() - (double)player.getYOffset() - .4, player.posZ + (rand.nextDouble() - 0.5D) * (double)player.getWidth());
			entityfire.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.0F, 4.0F);
			world.addEntity(entityfire);

			entityfire = new EntityFire(world, player, player.posX + (rand.nextDouble() - 0.5D) * (double)player.getWidth(), player.posY + (double)player.getEyeHeight() - (double)player.getYOffset() - .4, player.posZ + (rand.nextDouble() - 0.5D) * (double)player.getWidth());
			entityfire.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.0F, 4.0F);
			world.addEntity(entityfire);

			if (player instanceof ServerPlayerEntity) {
				flametank.attemptDamageItem(1, new Random(0), (ServerPlayerEntity) player);
				player.getHeldItemMainhand().attemptDamageItem(1, new Random(0), (ServerPlayerEntity) player);
			}
		}

		//player.addStat(StatList.getObjectUseStats(this));
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemstack);
	}

}