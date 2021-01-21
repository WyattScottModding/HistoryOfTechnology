package armor;

import java.util.Random;

import entity.EntityFire;
import init.CustomArmorMaterial;
import init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RocketBoots extends ArmorItem
{
	public RocketBoots(String name, CustomArmorMaterial materialIn, EquipmentSlotType equipmentSlotIn, Item.Properties builder) 
	{
		super(materialIn, equipmentSlotIn, builder);
		this.setRegistryName(name);
	}


	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) 
	{
		if (player instanceof ServerPlayerEntity) {
			if(world.getGameTime() % 20 == 0 && !player.isCreative())
			{
				player.abilities.allowFlying = false;
			}

			if(player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ItemInit.ROCKET_BOOTS && player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ItemInit.FLAMETHROWER_TANK)
			{
				player.abilities.allowFlying = true;
				player.fallDistance = 0;

				if(world.getGameTime() % 5 == 0 && player.abilities.isFlying)
				{
					double z = Math.sin(Math.toRadians(player.rotationYaw)) / 4;
					double x = Math.cos(Math.toRadians(player.rotationYaw)) / 4;

					EntityFire entityfire = new EntityFire(world, player, player.posX + x, player.posY, player.posZ + z);
					entityfire.shoot(player, 90, player.rotationYaw, 0.0F, 0.2F, 0.0F);
					world.addEntity(entityfire);

					EntityFire entityfire2 = new EntityFire(world, player, player.posX - x, player.posY, player.posZ - z);
					entityfire2.shoot(player, 90, player.rotationYaw, 0.0F, 0.2F, 0.0F);
					world.addEntity(entityfire2);
				}

				if(world.getGameTime() % 30 == 0 && player.abilities.isFlying)
				{
					ItemStack flametank = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
					flametank.attemptDamageItem(1, new Random(0), (ServerPlayerEntity) player);
				}
			}
			else
			{
				if(!player.isCreative())
				{
					player.abilities.allowFlying = false;
					player.isAirBorne = true;
				}
			}
		}
	}
}