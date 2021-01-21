package init;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Potion;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.registries.ForgeRegistries;
import potions.Caffeine;
import potions.Cancer;
import potions.HIV;
import potions.Radiation;
import potions.ThePlague;
import potions.Tuberculosis;

public class PotionInit 
{
	//.registerPotionAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE, MathHelper.getRandomUUID().toString(), 3.0D, 2)


	public static final Effect TUBERCULOSIS_EFFECT = new Tuberculosis("tuberculosis_potion", EffectType.HARMFUL, 16529206).addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, MathHelper.getRandomUUID().toString(), 3.0D, AttributeModifier.Operation.MULTIPLY_TOTAL);	
	public static final Potion TUBERCULOSIS_POTION = new Potion("tuberculosis_potion", new EffectInstance[] {new EffectInstance(TUBERCULOSIS_EFFECT, 24000)}).setRegistryName("tuberculosis_potion");
	public static final Potion TUBERCULOSIS_POTION_LONG = new Potion("tuberculosis_potion", new EffectInstance[] {new EffectInstance(TUBERCULOSIS_EFFECT, 48000)}).setRegistryName("tuberculosis_potion_long");

	public static final Effect PLAGUE_EFFECT = new ThePlague("plague_potion", EffectType.HARMFUL, 165123).addAttributesModifier(SharedMonsterAttributes.ARMOR_TOUGHNESS, MathHelper.getRandomUUID().toString(), -0.10D, AttributeModifier.Operation.MULTIPLY_TOTAL).addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, MathHelper.getRandomUUID().toString(), -0.10D, AttributeModifier.Operation.MULTIPLY_TOTAL);
	public static final Potion PLAGUE_POTION = new Potion("plague_potion", new EffectInstance[] {new EffectInstance(PLAGUE_EFFECT, 24000)}).setRegistryName("plague_potion");
	public static final Potion PLAGUE_POTION_LONG = new Potion("plague_potion", new EffectInstance[] {new EffectInstance(PLAGUE_EFFECT, 48000)}).setRegistryName("plague_potion_long");

	public static final Effect CANCER_EFFECT = new Cancer("cancer_potion", EffectType.HARMFUL, 5112073).addAttributesModifier(SharedMonsterAttributes.ARMOR_TOUGHNESS, MathHelper.getRandomUUID().toString(), -0.10D, AttributeModifier.Operation.MULTIPLY_TOTAL).addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, MathHelper.getRandomUUID().toString(), -0.10D, AttributeModifier.Operation.MULTIPLY_TOTAL).addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, MathHelper.getRandomUUID().toString(), -0.10D, AttributeModifier.Operation.MULTIPLY_TOTAL).addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, MathHelper.getRandomUUID().toString(), -0.20D, AttributeModifier.Operation.MULTIPLY_TOTAL);

	public static final Effect RADIATION_EFFECT = new Radiation("radiation_potion", EffectType.HARMFUL, 16774656).addAttributesModifier(SharedMonsterAttributes.ARMOR_TOUGHNESS, MathHelper.getRandomUUID().toString(), -0.05D, AttributeModifier.Operation.MULTIPLY_TOTAL).addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, MathHelper.getRandomUUID().toString(), -0.05D, AttributeModifier.Operation.MULTIPLY_TOTAL).addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, MathHelper.getRandomUUID().toString(), -0.10D, AttributeModifier.Operation.MULTIPLY_TOTAL);
	public static final Potion RADIATION_POTION = new Potion("radiation_potion", new EffectInstance[] {new EffectInstance(RADIATION_EFFECT, 24000)}).setRegistryName("radiation_potion");
	public static final Potion RADIATION_POTION_LONG = new Potion("radiation_potion", new EffectInstance[] {new EffectInstance(RADIATION_EFFECT, 48000)}).setRegistryName("radiation_potion_long");

	public static final Effect HIV_EFFECT = new HIV("hiv_potion", EffectType.HARMFUL, 14380685).addAttributesModifier(SharedMonsterAttributes.ARMOR_TOUGHNESS, MathHelper.getRandomUUID().toString(), -0.2D, AttributeModifier.Operation.MULTIPLY_TOTAL).addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, MathHelper.getRandomUUID().toString(), -0.1D, AttributeModifier.Operation.MULTIPLY_TOTAL).addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, MathHelper.getRandomUUID().toString(), -0.25D, AttributeModifier.Operation.MULTIPLY_TOTAL).addAttributesModifier(SharedMonsterAttributes.LUCK, MathHelper.getRandomUUID().toString(), -0.25D, AttributeModifier.Operation.MULTIPLY_TOTAL);
	public static final Potion HIV_POTION = new Potion("hiv_potion", new EffectInstance[] {new EffectInstance(HIV_EFFECT, 24000)}).setRegistryName("hiv_potion");
	public static final Potion HIV_POTION_LONG = new Potion("hiv_potion", new EffectInstance[] {new EffectInstance(HIV_EFFECT, 48000)}).setRegistryName("hiv_potion_long");

	public static final Effect CAFFEINE_EFFECT = new Caffeine("caffeine_potion", EffectType.BENEFICIAL, 6831152).addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, MathHelper.getRandomUUID().toString(), 0.02D, AttributeModifier.Operation.ADDITION).addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, MathHelper.getRandomUUID().toString(), 0.02D, AttributeModifier.Operation.ADDITION);


	public static void registerPotions()
	{
		registerPotion(TUBERCULOSIS_POTION, TUBERCULOSIS_POTION_LONG, TUBERCULOSIS_EFFECT);
		registerPotion(PLAGUE_POTION, PLAGUE_POTION_LONG, PLAGUE_EFFECT);
		registerPotion(RADIATION_POTION, RADIATION_POTION_LONG, RADIATION_EFFECT);
		registerPotion(HIV_POTION, HIV_POTION_LONG, HIV_EFFECT);

		registerPotionMixes();
		registerEffects();
	}

	public static void registerPotion(Potion defaultPotion, Potion longPotion, Effect effect)
	{
		ForgeRegistries.POTIONS.register(effect);
		ForgeRegistries.POTION_TYPES.register(defaultPotion);
		ForgeRegistries.POTION_TYPES.register(longPotion);
	}

	public static void registerEffects()
	{
		ForgeRegistries.POTIONS.register(CANCER_EFFECT);
		ForgeRegistries.POTIONS.register(CAFFEINE_EFFECT);
	}

	private static void registerPotionMixes()
	{
		//PotionHelper.addMix(TUBERCULOSIS_EFFECT, Items.REDSTONE, TUBERCULOSIS_POTION_LONG);
		//PotionHelper.addMix(PotionTypes.AWKWARD, ItemInit.SALT_PETRE_DUST, TUBERCULOSIS_EFFECT);
	}
	/*
	private static Effect register(int id, String key, Effect effectIn) {
		return Registry.register(Registry.EFFECTS, id, key, effectIn);
	}
	 */
}