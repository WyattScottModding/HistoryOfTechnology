package armor;

import init.CustomArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public class ArmorBase extends ArmorItem{

	Item.Properties properties = new Item.Properties();
	Item.Properties builder;
	
	public ArmorBase(String name, CustomArmorMaterial materialIn, EquipmentSlotType equipmentSlotIn, Item.Properties builder) 
	{
		super(materialIn, equipmentSlotIn, builder);
		this.setRegistryName(name);		
	}
}