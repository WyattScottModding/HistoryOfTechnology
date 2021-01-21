package items.classical;

import java.util.Random;

import entity.EntityWater;
import init.ItemInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class Hose extends Item
{
	public Hose(String name, Item.Properties builder)
	{
		super(builder);
		this.setRegistryName(name);
	}


	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack itemstack = player.getHeldItem(hand);

		ItemStack flametank = player.getItemStackFromSlot(EquipmentSlotType.CHEST);

		if (!world.isRemote && (flametank.getItem() == ItemInit.WATERTANK || player.isCreative()) && player instanceof ServerPlayerEntity)
		{			
			world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_WATER_AMBIENT, SoundCategory.PLAYERS, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

			Random rand = new Random();

			EntityWater entitywater = new EntityWater(world, player.posX + (rand.nextDouble() - 0.5D) * (double)player.getWidth(), player.posY + (double)player.getEyeHeight() - (double)player.getYOffset() - .4, player.posZ + (rand.nextDouble() - 0.5D) * (double)player.getWidth());
			entitywater.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.0F, 3.0F);
			world.addEntity(entitywater);
			
			entitywater = new EntityWater(world, player.posX + (rand.nextDouble() - 0.5D) * (double)player.getWidth(), player.posY + (double)player.getEyeHeight() - (double)player.getYOffset() - .4, player.posZ + (rand.nextDouble() - 0.5D) * (double)player.getWidth());
			entitywater.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.0F, 3.0F);
			world.addEntity(entitywater);

			flametank.attemptDamageItem(1, new Random(), (ServerPlayerEntity) player);
			player.getHeldItemMainhand().attemptDamageItem(1, new Random(), (ServerPlayerEntity) player);
		}

		//player.addStat(StatList.getObjectUseStats(this));
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemstack);
	}

}