package armor;

import java.util.ArrayList;
import java.util.List;

import init.CustomArmorMaterial;
import init.ItemInit;
import models.ModelParachute;
import models.ModelParachuteBackpack;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Parachute extends ArmorItem
{
	boolean falling = false;
	boolean createParachute = false;

	private BipedModel model = new ModelParachuteBackpack();

	public Parachute(String name, CustomArmorMaterial materialIn, EquipmentSlotType equipmentSlotIn, Item.Properties builder) 
	{
		super(materialIn, equipmentSlotIn, builder);
		this.setRegistryName(name);
	}

	@Override
	public BipedModel getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot,
			BipedModel _default) 
	{
		if(itemStack != ItemStack.EMPTY)
		{
			if(itemStack.getItem() instanceof ArmorItem)
			{					
				if(createParachute)
				{
					model = new ModelParachute();
				}
				else
				{
					model = new ModelParachuteBackpack();
				}

				model.bipedBody.showModel = true;
				model.bipedLeftArm.showModel = true;
				model.bipedRightArm.showModel = true;

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

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) 
	{
		if(!world.isRemote)
		{
			if(!createParachute && falling)
			{
				createParachute = true;
			}

			if(createParachute && !falling)
			{
				createParachute = false;
			}

			if(player.getMotion().y > -.5)
			{
				falling = false;
			}
			else
			{
				falling = findGround(player, world);
			}
		}

		if(player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ItemInit.PARACHUTE && falling)
		{
			BlockPos pos = player.getPosition();

			if(world.getBlockState(pos.down()).getBlock() == Blocks.AIR)
				player.setMotion(player.getMotion().x, -.5, player.getMotion().z);
		}
		if(player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ItemInit.PARACHUTE)
		{
			player.fallDistance = 0;

		}
		super.onArmorTick(stack, world, player);
	}


	public boolean findGround(PlayerEntity player, World world)
	{
		List<Block> list = new ArrayList<Block>();

		list.add(world.getBlockState(player.getPosition().down()).getBlock());
		list.add(world.getBlockState(player.getPosition().down().down()).getBlock());
		list.add(world.getBlockState(player.getPosition().down().down().down()).getBlock());

		for(Block element : list)
		{
			if(element != Blocks.AIR)
				return false;
		}

		return true;
	}

}
