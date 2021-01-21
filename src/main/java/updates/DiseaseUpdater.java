package updates;

import java.util.List;

import init.ItemInit;
import init.PotionInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;

public class DiseaseUpdater 
{		
	public static void randomlyGiveDiseases(LivingEntity entity, World world)
	{
		if(entity instanceof AnimalEntity || entity instanceof PlayerEntity)
		{
			int hiv = 1;

			if(entity.isPotionActive(PotionInit.HIV_EFFECT))
			{
				hiv = 20;
			}

			int random = (int) (Math.random() * 4000000 / hiv);
			Biome biome = world.getBiome(entity.getPosition());

			//Tuberculosis

			//Since TB originated in ancient Egypt
			if(biome == Biomes.DESERT || biome == Biomes.DESERT_HILLS || biome == Biomes.DESERT)
				random = (int) (Math.random() * 200000 / hiv);

			if(random == 0)
			{
				entity.addPotionEffect(new EffectInstance(PotionInit.TUBERCULOSIS_EFFECT, 40000, 0));
			}


			//The Plague
			random = (int) (Math.random() * 4000000 / hiv);

			//Since The Plague originated in central Asia
			if(biome == Biomes.TAIGA || biome == Biomes.TAIGA_HILLS || biome == Biomes.TAIGA_MOUNTAINS)
				random = (int) (Math.random() * 200000 / hiv);

			if(random == 0)
			{
				entity.addPotionEffect(new EffectInstance(PotionInit.PLAGUE_EFFECT, 30000, 0));
			}


			//Cancer
			random = (int) (Math.random() * 4000000);

			//Make it 4 times as likely to get if had cancer before
			if(entity.getPersistentData().contains("cancer") && entity.getPersistentData().getBoolean("cancer"))
				random = (int) (Math.random() * 1000000);

			if(random == 0)
			{
				entity.addPotionEffect(new EffectInstance(PotionInit.CANCER_EFFECT, 100000, 0));
			}

			//Only animals can randomly get HIV
			if(entity instanceof AnimalEntity)
			{
				//HIV
				random = (int) (Math.random() * 400000);

				if(random == 0)
				{
					entity.addPotionEffect(new EffectInstance(PotionInit.HIV_EFFECT, 100000, 0));
				}
			}
		}
	}

	public static int spreadDisease(World world, BlockPos pos, String immune_tag, EffectInstance effect)
	{
		AxisAlignedBB entityBB = new AxisAlignedBB(pos.getX() + 2, pos.getY() + 2, pos.getZ() + 2, pos.getX() - 2, pos.getY() - 2, pos.getZ() - 2);
		List<LivingEntity> list = world.getEntitiesWithinAABB(LivingEntity.class, entityBB);

		int newCarriers = 0;

		for(LivingEntity element : list)
		{
			if(element instanceof AnimalEntity || element instanceof PlayerEntity)
			{
				boolean immune = element.getPersistentData().contains(immune_tag) && element.getPersistentData().getBoolean(immune_tag);

				if(!immune)
				{
					boolean hasArmor = false;

					Item head = element.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem();
					Item chest = element.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem();
					Item legs = element.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem();
					Item feet = element.getItemStackFromSlot(EquipmentSlotType.FEET).getItem();

					//If has full rubber armor
					if(head == ItemInit.RUBBER_HELMET || head == ItemInit.SCUBA_HELMET)
					{
						if(chest == ItemInit.RUBBER_CHESTPLATE || chest == ItemInit.SCUBA_CHESTPLATE)
						{
							if(legs == ItemInit.RUBBER_LEGGINGS || legs == ItemInit.SCUBA_LEGGINGS)
							{
								if(feet == ItemInit.RUBBER_BOOTS || feet == ItemInit.SCUBA_BOOTS)
								{
									hasArmor = true;
								}
							}
						}
					}
					//If they have a fully inclosed armor set then they can't get sick from other entites
					if(!hasArmor)
					{
						int random = (int) (Math.random() * 1000);

						if(random == 0)
						{
							element.addPotionEffect(effect);
							newCarriers++;
						}
					}
				}
			}
		}
		return newCarriers;
	}

	public static void tuberculosisEffect(LivingEntity entity, World world)
	{
		//Make Immune to it in the future
		CompoundNBT nbtWrite = new CompoundNBT();
		nbtWrite.putBoolean("tuberculosis_immune", true);
		entity.deserializeNBT(nbtWrite);

		int duration = entity.getActivePotionEffect(PotionInit.TUBERCULOSIS_EFFECT).getDuration();

		//Hurt the entity occasionally
		int random = (int) (Math.random() * 1000);

		if(random == 0)
		{
			float damage = entity.getHealth() / 4;

			entity.setHealth((float) (entity.getHealth() - damage));
		}

		//Give the entity hunger occasionally
		random = (int) (Math.random() * 5000);

		if(random == 0)
		{
			entity.addPotionEffect(new EffectInstance(Effects.HUNGER, 60, 0));
		}

		//Make it worse as time goes on
		if(duration == 20000)
		{
			entity.removeActivePotionEffect(PotionInit.TUBERCULOSIS_EFFECT);
			entity.addPotionEffect(new EffectInstance(PotionInit.TUBERCULOSIS_EFFECT, 20000, 1));

		}
		else if(duration == 10000)
		{
			entity.removeActivePotionEffect(PotionInit.TUBERCULOSIS_EFFECT);
			entity.addPotionEffect(new EffectInstance(PotionInit.TUBERCULOSIS_EFFECT, 10000, 2));

		}
		else if(duration == 1000)
		{
			entity.removeActivePotionEffect(PotionInit.TUBERCULOSIS_EFFECT);
			entity.addPotionEffect(new EffectInstance(PotionInit.TUBERCULOSIS_EFFECT, 1000, 3));

		}


		//Spread the disease
		BlockPos pos = entity.getPosition();
		String immune_tag = "tuberculosis_immune";
		EffectInstance effect = new EffectInstance(PotionInit.TUBERCULOSIS_EFFECT, 40000, 0, true, true);

		spreadDisease(world, pos, immune_tag, effect);


	}

	public static void plagueEffect(LivingEntity entity, World world)
	{
		//Make Immune to it in the future (resets if you die and respawn)
		CompoundNBT nbtWrite = new CompoundNBT();
		nbtWrite.putBoolean("plague_immune", true);
		entity.deserializeNBT(nbtWrite);

		int duration = entity.getActivePotionEffect(PotionInit.PLAGUE_EFFECT).getDuration();

		int randomNausea =  (int) (Math.random() * 6000);
		int randomPoison =  (int) (Math.random() * 6000);
		int randomDeath = (int) (Math.random() * 280000);
		//randomDeath = (int) (Math.random() * 100);

		//Make effects more likely when in the final hours
		if(entity.getActivePotionEffect(PotionInit.PLAGUE_EFFECT).getDuration() < 10000)
		{
			randomNausea =  (int) (Math.random() * 2000);
			randomPoison =  (int) (Math.random() * 2000);
			randomDeath = (int) (Math.random() * 70000);
		}


		//Hurt the entity occasionally
		int random = (int) (Math.random() * 1000);

		if(random == 0)
		{
			float damage = entity.getHealth() / 4;

			entity.setHealth((float) (entity.getHealth() - damage));
		}

		//Give the entity poison occasionally
		if(randomPoison == 0)
		{
			entity.addPotionEffect(new EffectInstance(Effects.POISON, 140, 0));
		}

		//Give the entity nausea occasionally
		if(randomNausea == 0)
		{
			entity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 1000, 0));
		}

		//Make it worse as time goes on
		if(duration == 15000)
		{
			entity.removeActivePotionEffect(PotionInit.PLAGUE_EFFECT);
			entity.addPotionEffect(new EffectInstance(PotionInit.PLAGUE_EFFECT, 25000, 1));

		}
		else if(duration == 10000)
		{
			entity.removeActivePotionEffect(PotionInit.PLAGUE_EFFECT);
			entity.addPotionEffect(new EffectInstance(PotionInit.PLAGUE_EFFECT, 10000, 2));

		}
		else if(duration == 1000)
		{
			entity.removeActivePotionEffect(PotionInit.PLAGUE_EFFECT);
			entity.addPotionEffect(new EffectInstance(PotionInit.PLAGUE_EFFECT, 1000, 3));

		}


		//50% Chance of killing the victim at some point while infected
		if(randomDeath == 0)
		{
			if(entity instanceof PlayerEntity && !((PlayerEntity) entity).isCreative())
			{
				PlayerEntity player = (PlayerEntity) entity;

				ITextComponent msg = new StringTextComponent (player.getName() + " was killed by The Plague");
				world.getServer().sendMessage(msg);

				player.setHealth(0);
			}
			else if(!(entity instanceof PlayerEntity))
			{
				entity.setHealth(0);
			}

		}

		//Spread the disease
		BlockPos pos = entity.getPosition();
		String immune_tag = "plague_immune";
		EffectInstance effect = new EffectInstance(PotionInit.PLAGUE_EFFECT, 30000, 0, true, true);

		spreadDisease(world, pos, immune_tag, effect);

	}


	public static void cancerEffect(LivingEntity entity, World world)
	{
		//Make it more likely to get it in the future (resets if you die and respawn)
		CompoundNBT nbtWrite = new CompoundNBT();
		nbtWrite.putBoolean("cancer", true);
		entity.deserializeNBT(nbtWrite);

		int duration = entity.getActivePotionEffect(PotionInit.CANCER_EFFECT).getDuration();

		double multiplier = (duration + 24000) / 24000;

		int randomNausea =  (int) (Math.random() * 2000 * multiplier);
		int randomPoison =  (int) (Math.random() * 2000 * multiplier);
		int randomHurt = (int) (Math.random() * 4000 * multiplier);
		int randomDeath = (int) (Math.random() * 20000 * multiplier);

		if(duration > 100000)
			randomDeath = 1;


		//Hurt the entity occasionally
		if(randomHurt == 0)
		{
			float damage = entity.getHealth() / 4;

			entity.setHealth((float) (entity.getHealth() - damage));
		}

		//Give the entity poison occasionally
		if(randomPoison == 0)
		{
			entity.addPotionEffect(new EffectInstance(Effects.POISON, 100, 0));
		}

		//Give the entity nausea occasionally
		if(randomNausea == 0)
		{
			entity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 300, 0));
		}

		//Make it worse as time goes on
		if(duration == 50000)
		{
			entity.removeActivePotionEffect(PotionInit.CANCER_EFFECT);
			entity.addPotionEffect(new EffectInstance(PotionInit.CANCER_EFFECT, 50000, 1));

		}
		else if(duration == 25000)
		{
			entity.removeActivePotionEffect(PotionInit.CANCER_EFFECT);
			entity.addPotionEffect(new EffectInstance(PotionInit.CANCER_EFFECT, 25000, 2));

		}
		else if(duration == 1000)
		{
			entity.removeActivePotionEffect(PotionInit.CANCER_EFFECT);
			entity.addPotionEffect(new EffectInstance(PotionInit.CANCER_EFFECT, 1000, 3));

		}

		//Pretty good chance of killing the victim at some point while infected
		if(randomDeath == 0)
		{
			if(entity instanceof PlayerEntity && !((PlayerEntity) entity).isCreative())
			{
				PlayerEntity player = (PlayerEntity) entity;

				ITextComponent msg = new StringTextComponent (player.getName() + " was killed by Cancer");
				world.getServer().sendMessage(msg);

				player.setHealth(0);
			}
			else if(!(entity instanceof PlayerEntity))
			{
				entity.setHealth(0);
			}
		}
	}

	public static void radiationEffect(LivingEntity entity, World world)
	{
		int randomNausea =  (int) (Math.random() * 1000);
		int randomPoison =  (int) (Math.random() * 1000);
		int randomHurt = (int) (Math.random() * 10000);
		int randomDeath = (int) (Math.random() * 100000);
		int randomCancer = (int) (Math.random() * 50000);


		//Hurt the entity occasionally
		if(randomHurt == 0)
		{
			float damage = entity.getHealth() / 4;

			entity.setHealth((float) (entity.getHealth() - damage));
		}

		//Give the entity poison occasionally
		if(randomPoison == 0)
		{
			entity.addPotionEffect(new EffectInstance(Effects.POISON, 100, 0));
		}

		//Give the entity nausea occasionally
		if(randomNausea == 0)
		{
			entity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 300, 0));
		}


		//Pretty good chance of killing the victim at some point while infected
		if(randomDeath == 0)
		{
			if(entity instanceof PlayerEntity && !((PlayerEntity) entity).isCreative())
			{
				PlayerEntity player = (PlayerEntity) entity;

				ITextComponent msg = new StringTextComponent (player.getName() + " was killed by Radiation");
				world.getServer().sendMessage(msg);

				player.setHealth(0);
			}
			else if(!(entity instanceof PlayerEntity))
			{
				entity.setHealth(0);
			}
		}

		if(randomCancer == 0)
		{
			entity.addPotionEffect(new EffectInstance(PotionInit.CANCER_EFFECT, 100000, 0));
		}
	}

	public static void hivEffect(LivingEntity entity, World world)
	{
		int randomFatigue =  (int) (Math.random() * 8000);
		int randomNausea =  (int) (Math.random() * 8000);
		int randomHurt = (int) (Math.random() * 10000);
		int randomDeath = (int) (Math.random() * 200000);


		//Hurt the entity occasionally
		if(randomHurt == 0)
		{
			float damage = entity.getHealth() / 4;

			entity.setHealth((float) (entity.getHealth() - damage));
		}

		//Give the entity poison occasionally
		if(randomFatigue == 0)
		{
			entity.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 600, 0));
		}

		//Give the entity nausea occasionally
		if(randomNausea == 0)
		{
			entity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 300, 0));
		}


		//Pretty good chance of killing the victim at some point while infected
		if(randomDeath == 0)
		{
			if(entity instanceof PlayerEntity && !((PlayerEntity) entity).isCreative())
			{
				PlayerEntity player = (PlayerEntity) entity;

				ITextComponent msg = new StringTextComponent (player.getName() + " was killed by HIV");
				world.getServer().sendMessage(msg);

				player.setHealth(0);
			}
			else if(!(entity instanceof PlayerEntity))
			{
				entity.setHealth(0);
			}
		}
	}
}
