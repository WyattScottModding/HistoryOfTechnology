package armor;

import init.CustomArmorMaterial;
import init.ItemInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GravityBoots extends ArmorItem{

	public GravityBoots(String name, CustomArmorMaterial materialIn, EquipmentSlotType equipmentSlotIn, Item.Properties builder) 
	{
		super(materialIn, equipmentSlotIn, builder);
		this.setRegistryName(name);
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) 
	{			
		if(player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ItemInit.GRAVITY_BOOTS)
		{
			player.fallDistance = 0;
		}
	}

}