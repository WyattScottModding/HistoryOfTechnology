package updates;

import java.util.ArrayList;
import java.util.List;

import init.BlockInit;
import init.ItemInit;
import init.PotionInit;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;


public class EntityUpdater 
{	
	public static void run(LivingEntity entity, World world)
	{
		mustardgasUpdate(entity, world);
		uraniumUpdate(entity, world);
		DiseaseUpdater.randomlyGiveDiseases(entity, world);

		if(entity.isPotionActive(PotionInit.TUBERCULOSIS_EFFECT))
			DiseaseUpdater.tuberculosisEffect(entity, world);

		if(entity.isPotionActive(PotionInit.PLAGUE_EFFECT))
			DiseaseUpdater.plagueEffect(entity, world);

		if(entity.isPotionActive(PotionInit.CANCER_EFFECT))
			DiseaseUpdater.cancerEffect(entity, world);

		if(entity.isPotionActive(PotionInit.RADIATION_EFFECT))
			DiseaseUpdater.radiationEffect(entity, world);
	}

	public static void mustardgasUpdate(LivingEntity entity, World world)
	{
		boolean hasArmor = false;

		if(entity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ItemInit.RUBBER_HELMET)
		{
			if(entity.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ItemInit.RUBBER_CHESTPLATE)
			{
				if(entity.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ItemInit.RUBBER_LEGGINGS)
				{
					if(entity.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ItemInit.RUBBER_BOOTS)
					{
						hasArmor = true;
					}
				}
			}
		}


		if(!hasArmor)
		{
			Block block1 = world.getBlockState(entity.getPosition()).getBlock();
			Block block2 = world.getBlockState(entity.getPosition().up()).getBlock();

			if(block1 == BlockInit.MUSTARD_GAS || block2 == BlockInit.MUSTARD_GAS)
			{
				entity.addPotionEffect(new EffectInstance(Effects.POISON, 10, 5));
				entity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 10, 0));
			}
		}
	}

	public static void uraniumUpdate(LivingEntity entity, World world)
	{
		boolean hasArmor = false;

		if(entity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ItemInit.RUBBER_HELMET)
		{
			if(entity.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ItemInit.RUBBER_CHESTPLATE)
			{
				if(entity.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ItemInit.RUBBER_LEGGINGS)
				{
					if(entity.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ItemInit.RUBBER_BOOTS)
					{
						hasArmor = true;
					}
				}
			}
		}

		if(entity instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) entity;
			if(player.isCreative())
				hasArmor = true;
		}

		if(!hasArmor)
		{
			Block block1 = world.getBlockState(entity.getPosition()).getBlock();
			Block block2 = world.getBlockState(entity.getPosition().down()).getBlock();
			Block block3 = world.getBlockState(entity.getPosition().up()).getBlock();
			Block block4 = world.getBlockState(entity.getPosition().east()).getBlock();
			Block block5 = world.getBlockState(entity.getPosition().west()).getBlock();
			Block block6 = world.getBlockState(entity.getPosition().north()).getBlock();
			Block block7 = world.getBlockState(entity.getPosition().south()).getBlock();
			Block block8 = world.getBlockState(entity.getPosition().up().up()).getBlock();
			Block block9 = world.getBlockState(entity.getPosition().up().north()).getBlock();
			Block block10 = world.getBlockState(entity.getPosition().up().south()).getBlock();
			Block block11 = world.getBlockState(entity.getPosition().up().west()).getBlock();
			Block block12 = world.getBlockState(entity.getPosition().up().east()).getBlock();
			Block block13 = world.getBlockState(entity.getPosition().north().east()).getBlock();
			Block block14 = world.getBlockState(entity.getPosition().north().west()).getBlock();
			Block block15 = world.getBlockState(entity.getPosition().south().east()).getBlock();
			Block block16 = world.getBlockState(entity.getPosition().south().west()).getBlock();


			List<Block> blocks = new ArrayList<Block>();
			blocks.add(block1);
			blocks.add(block2);
			blocks.add(block3);
			blocks.add(block4);
			blocks.add(block5);
			blocks.add(block6);
			blocks.add(block7);
			blocks.add(block8);
			blocks.add(block9);
			blocks.add(block10);
			blocks.add(block11);
			blocks.add(block12);
			blocks.add(block13);
			blocks.add(block14);
			blocks.add(block15);
			blocks.add(block16);


			for(Block element : blocks)
			{
				if(element == BlockInit.URANIUM_ORE)
				{
					entity.addPotionEffect(new EffectInstance(PotionInit.RADIATION_EFFECT, 2000, 1));
				}
			}

			if(entity instanceof PlayerEntity)
			{
				PlayerEntity player = (PlayerEntity) entity;
				boolean uraniumOre = player.inventory.hasItemStack(new ItemStack(BlockInit.URANIUM_ORE));
				boolean uranium = player.inventory.hasItemStack(new ItemStack(ItemInit.URANIUM_INGOT));

				if(uraniumOre || uranium)
				{
					player.addPotionEffect(new EffectInstance(PotionInit.RADIATION_EFFECT, 2000, 1));
				}
			}
		}
	}

}