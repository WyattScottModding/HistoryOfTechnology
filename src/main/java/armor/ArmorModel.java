package armor;

import init.CustomArmorMaterial;
import models.ModelExoSuitChest;
import models.ModelExoSuitChestAlex;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ArmorModel extends ArmorItem
{
	private BipedModel<LivingEntity> model;

	public ArmorModel(String name, CustomArmorMaterial materialIn, EquipmentSlotType equipmentSlotIn, BipedModel model, Item.Properties builder) 
	{
		super(materialIn, equipmentSlotIn, builder);
		setRegistryName(name);

		this.model = model;
	}


	@Override
	public BipedModel getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot,
			BipedModel _default) 
	{
		if(itemStack != ItemStack.EMPTY)
		{
			if(itemStack.getItem() instanceof ArmorItem)
			{				
				NetworkPlayerInfo networkEntity = Minecraft.getInstance().getConnection().getPlayerInfo(entityLiving.getUniqueID());
				
				if(networkEntity.getSkinType().equals("slim") && model instanceof ModelExoSuitChest)
				{
					model = new ModelExoSuitChestAlex();
				}
				
				
				model.bipedHead.showModel =  armorSlot == EquipmentSlotType.HEAD;

				if(armorSlot == EquipmentSlotType.CHEST)
				{
					model.bipedBody.showModel = true;
					model.bipedLeftArm.showModel = true;
					model.bipedRightArm.showModel = true;
				}
				
				if(armorSlot == EquipmentSlotType.LEGS)
				{
					model.bipedLeftLeg.showModel = true;
					model.bipedRightLeg.showModel = true;
				}
				
				

				model.isChild = _default.isChild;
				//model.isRiding = _default.isRiding;
				model.rightArmPose = _default.rightArmPose;
				model.leftArmPose = _default.leftArmPose;
				model.isSneak = _default.isSneak;
				
				model.bipedLeftLeg.showModel = false;
				model.bipedRightLeg.showModel = false;

				return model;
			}
		}

		return null;
	}
}