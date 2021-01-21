package init;

import java.util.function.Supplier;

import main.History;
import net.minecraft.util.SoundEvents;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum CustomArmorMaterial implements IArmorMaterial 
{
	RUBBER("rubber", 30, new int[] {2, 3, 4, 2}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () -> {
		return Ingredient.fromItems(ItemInit.RUBBER);
	}),
	HOVER("hover", 90, new int[]{4, 6, 8, 4}, 0, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F, () -> {
		return Ingredient.fromItems(Items.DIAMOND);
	}),
	GRAVITY("gravity", 90, new int[]{4, 6, 8, 4}, 0, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F, () -> {
		return Ingredient.fromItems(Items.DIAMOND);
	}),
	CLOTH("parachute", 20, new int[]{1, 2, 3, 1}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () -> {
		return Ingredient.fromItems(ItemInit.SILK_SHEET);
	}),
	SCUBA("scuba", 40, new int[]{2, 1, 1, 1}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () -> {
		return Ingredient.fromItems(ItemInit.RUBBER);
	}),
	FLAME("flame", 20, new int[]{2, 2, 2, 2}, 0, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, () -> {
		return Ingredient.fromItems(Items.IRON_INGOT);
	}),
	WATER("water", 20, new int[]{2, 2, 2, 2}, 0, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, () -> {
		return Ingredient.fromItems(Items.IRON_INGOT);
	}),
	EXO("exo", 30, new int[]{6, 8, 7, 6}, 0, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F, () -> {
		return Ingredient.fromItems(Item.getItemFromBlock(BlockInit.ALUMINIUM_BLOCK));
	});


	/** Holds the 'base' maxDamage that each armorType have. */
	private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
	private final String name;
	/**
	 * Holds the maximum damage factor (each piece multiply this by it's own value) of the material, this is the item
	 * damage (how much can absorb before breaks)
	 */
	private final int maxDamageFactor;
	/**
	 * Holds the damage reduction (each 1 points is half a shield on gui) of each piece of armor (helmet, plate, legs and
	 * boots)
	 */
	private final int[] damageReductionAmountArray;
	/** Return the enchantability factor of the material */
	private final int enchantability;
	private final SoundEvent soundEvent;
	private final float toughness;
	private final LazyLoadBase<Ingredient> repairMaterial;

	private CustomArmorMaterial(String nameIn, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, Supplier<Ingredient> repairMaterial) {
		this.name = nameIn;
		this.maxDamageFactor = maxDamageFactor;
		this.damageReductionAmountArray = damageReductionAmountArray;
		this.enchantability = enchantability;
		this.soundEvent = soundEvent;
		this.toughness = toughness;
		this.repairMaterial = new LazyLoadBase<>(repairMaterial);
	}

	public int getDurability(EquipmentSlotType slotIn) {
		return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
	}

	public int getDamageReductionAmount(EquipmentSlotType slotIn) {
		return this.damageReductionAmountArray[slotIn.getIndex()];
	}

	public int getEnchantability() {
		return this.enchantability;
	}

	public SoundEvent getSoundEvent() {
		return this.soundEvent;
	}

	public Ingredient getRepairMaterial() {
		return this.repairMaterial.getValue();
	}

	@OnlyIn(Dist.CLIENT)
	public String getName() {
		return History.modid + ":" + this.name;
	}

	public float getToughness() {
		return this.toughness;
	}
	
	
}