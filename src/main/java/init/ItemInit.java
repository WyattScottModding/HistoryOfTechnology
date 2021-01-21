package init;

import armor.ArmorBase;
import armor.ArmorModel;
import armor.GravityBoots;
import armor.Parachute;
import armor.RocketBoots;
import handlers.EntityLivingRegistry;
import handlers.SoundsHandler;
import items.ancient.BookOfTechnologies;
import items.ancient.ItemBasic;
import items.ancient.Mace;
import items.ancient.Spear;
import items.ancient.ThrowingKnife;
import items.atomic.ContactGrenade;
import items.atomic.MissileLauncher;
import items.classical.EntityBasic;
import items.classical.Hose;
import items.future.Blackhole;
import items.future.GravityGun;
import items.future.IMR;
import items.future.MagnetGun;
import items.future.Tac19;
import items.future.WunderWaffe;
import items.industrial.CustomRecords;
import items.industrial.FlameThrower;
import items.industrial.FlashLight;
import items.industrial.FragGrenade;
import items.industrial.GatlingGun;
import items.industrial.ModelT_Item;
import items.industrial.MustardGas;
import items.industrial.Syringe;
import items.industrial.SyringeBlood;
import items.industrial.SyringeHIVBlood;
import items.industrial.SyringePenicillin;
import items.industrial.ThreatGrenade;
import items.industrial.TurretItem;
import items.medieval.Pistol;
import items.modern.Calculator;
import items.modern.VariableGrenade;
import items.renaissance.Coffee;
import items.renaissance.CoffeeBean;
import main.History;
import models.ModelExoSuitChest;
import models.ModelExoSuitLegs;
import models.ModelFlameTank;
import models.ModelMetalTank;
import models.ModelWaterTank;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(History.modid)
public class ItemInit {
	
	//Armor
	public static final Item RUBBER_HELMET = new ArmorBase("rubberhelmet", CustomArmorMaterial.RUBBER, EquipmentSlotType.HEAD, new Item.Properties().group(History.atomicTab));

	public static final Item RUBBER_CHESTPLATE = new ArmorBase("rubberchestplate", CustomArmorMaterial.RUBBER, EquipmentSlotType.CHEST, new Item.Properties().group(History.atomicTab));

	public static final Item RUBBER_LEGGINGS = new ArmorBase("rubberleggings", CustomArmorMaterial.RUBBER, EquipmentSlotType.LEGS, new Item.Properties().group(History.atomicTab));

	public static final Item RUBBER_BOOTS = new ArmorBase("rubberboots", CustomArmorMaterial.RUBBER, EquipmentSlotType.FEET, new Item.Properties().group(History.atomicTab));

	public static final Item ROCKET_BOOTS = new RocketBoots("rocketboots", CustomArmorMaterial.HOVER, EquipmentSlotType.FEET, new Item.Properties().group(History.futureTab));

	public static final Item GRAVITY_BOOTS = new GravityBoots("gravityboots", CustomArmorMaterial.GRAVITY, EquipmentSlotType.FEET, new Item.Properties().group(History.futureTab));

	public static final Item SCUBA_HELMET = new ArmorBase("scubahelmet", CustomArmorMaterial.SCUBA, EquipmentSlotType.HEAD, new Item.Properties().group(History.renaissanceTab));

	public static final Item SCUBA_CHESTPLATE = new ArmorBase("scubachestplate", CustomArmorMaterial.SCUBA, EquipmentSlotType.CHEST, new Item.Properties().group(History.renaissanceTab));

	public static final Item SCUBA_LEGGINGS =  new ArmorModel("scubaleggings", CustomArmorMaterial.SCUBA, EquipmentSlotType.LEGS, new ModelMetalTank(), new Item.Properties().group(History.renaissanceTab));

	public static final Item SCUBA_BOOTS = new ArmorBase("scubaboots", CustomArmorMaterial.SCUBA, EquipmentSlotType.FEET, new Item.Properties().group(History.renaissanceTab));

	public static final Item BULLETPROOFVEST = new ArmorBase("bulletproofvest", CustomArmorMaterial.HOVER, EquipmentSlotType.CHEST, new Item.Properties().group(History.industrialTab));

	public static final Item FLAMETHROWER_TANK = new ArmorModel("flametank", CustomArmorMaterial.FLAME, EquipmentSlotType.CHEST, new ModelFlameTank(), new Item.Properties().group(History.industrialTab).defaultMaxDamage(400));

	public static final Item WATERTANK = new ArmorModel("watertank", CustomArmorMaterial.WATER, EquipmentSlotType.CHEST, new ModelWaterTank(), new Item.Properties().group(History.classicalTab).defaultMaxDamage(400));

	public static final Item EXO_CHESTPLATE = new ArmorModel("exochestplate", CustomArmorMaterial.EXO, EquipmentSlotType.CHEST, new ModelExoSuitChest(), new Item.Properties().group(History.futureTab));

	public static final Item EXO_LEGGINGS = new ArmorModel("exoleggings", CustomArmorMaterial.EXO, EquipmentSlotType.LEGS, new ModelExoSuitLegs(), new Item.Properties().group(History.futureTab));

	public static final Item EXO_HOVER = new ArmorModel("exohover", CustomArmorMaterial.EXO, EquipmentSlotType.FEET, new ModelExoSuitLegs(), new Item.Properties().group(History.futureTab));

	public static final Item METAL_TANK = new ArmorModel("metaltank", CustomArmorMaterial.SCUBA, EquipmentSlotType.CHEST, new ModelMetalTank(), new Item.Properties().group(History.classicalTab));

	public static final Item PARACHUTE = new Parachute("parachute", CustomArmorMaterial.CLOTH, EquipmentSlotType.CHEST, new Item.Properties().group(History.renaissanceTab).maxStackSize(1));

	//Spawn Eggs
	public static Item silkworm_egg;

	//Ingots
	public static final Item COPPER_INGOT = new ItemBasic("copper_ingot", new Item.Properties().group(History.ancientTab));

	public static final Item TIN_INGOT = new ItemBasic("tin_ingot", new Item.Properties().group(History.ancientTab));

	public static final Item BRONZE_INGOT = new ItemBasic("bronze_ingot", new Item.Properties().group(History.ancientTab));

	public static final Item ALUMINIUM_INGOT = new ItemBasic("aluminium_ingot", new Item.Properties().group(History.industrialTab));

	public static final Item SALTPETRE_DUST = new ItemBasic("saltpetre_dust", new Item.Properties().group(History.medievalTab));

	public static final Item URANIUM_INGOT = new ItemBasic("uranium_ingot", new Item.Properties().group(History.atomicTab));


	//Ancient
	public static final Item SPEAR = new Spear("spear", new Item.Properties().maxStackSize(16).group(History.ancientTab));

	public static final Item STON_MACE = new Mace("stone_mace", ItemTier.IRON, 50, new Item.Properties().maxStackSize(1).group(History.ancientTab).addToolType(ToolType.AXE, 3));

	public static final Item IRON_MACE = new Mace("iron_mace", ItemTier.DIAMOND, 100, new Item.Properties().maxStackSize(1).group(History.ancientTab).addToolType(ToolType.AXE, 4));

	public static final Item WHEEL = new ItemBasic("wheel", new Item.Properties().group(History.ancientTab));

	public static final Item THROWING_KNIFE = new ThrowingKnife("knife", new Item.Properties().group(History.ancientTab));

	public static final Item ROCK = new ItemBasic("rock", new Item.Properties().group(History.ancientTab));

	public static final Item BOOK_OF_TECHNOLOGIES = new BookOfTechnologies("book_technology", new Item.Properties().group(History.ancientTab).maxStackSize(1));


	//Classical
	public static final Item HOSE = new Hose("hose", new Item.Properties().group(History.classicalTab).maxStackSize(1).defaultMaxDamage(1600));

	public static final Item ROCK_WHITE = new ItemBasic("rock_white", new Item.Properties().group(History.classicalTab).maxStackSize(1));

	public static final Item ROCK_BLACK = new ItemBasic("rock_black", new Item.Properties().group(History.classicalTab).maxStackSize(1));


	//Medieval
	public static final Item PISTOL = new Pistol("pistol_basic", new Item.Properties().group(History.medievalTab).maxStackSize(1));

	public static final Item BULLET = new ItemBasic("bullet", new Item.Properties().group(History.medievalTab));

	public static final Item WATER = new EntityBasic("entitywater", new Item.Properties());

	public static final Item SILK = new ItemBasic("silk", new Item.Properties().group(History.medievalTab));

	public static final Item SILK_SHEET = new ItemBasic("silk_sheet", new Item.Properties().group(History.medievalTab));

	//Renaissance
	public static final Item COFFEEBEAN = new CoffeeBean("coffeebean", new Item.Properties().group(History.renaissanceTab));

	public static final Item COFFEEBEAN_ROASTED = new ItemBasic("coffeebean_roasted", new Item.Properties().group(History.renaissanceTab));

//	public static final Item COFFEE = new Coffee("coffee", new Item.Properties().group(History.renaissanceTab));


	//Industrial
	public static final Item LATEX = new ItemBasic("latex", new Item.Properties().group(History.industrialTab));

	public static final Item RUBBER = new ItemBasic("rubber", new Item.Properties().group(History.industrialTab));

	public static final Item SYRINGE = new Syringe("syringe", new Item.Properties().group(History.industrialTab));

	public static final Item SYRINGE_BLOOD = new SyringeBlood("syringe_blood", new Item.Properties().group(History.industrialTab).maxStackSize(1));

	public static final Item SYRINGE_HIV_BLOOD = new SyringeHIVBlood("syringe_blood_2", new Item.Properties().maxStackSize(1));
	
	public static final Item OIL_BUCKET = new ItemBasic("oil_bucket", new Item.Properties().group(History.industrialTab).maxStackSize(1));

	public static final Item GATLING_GUN = new GatlingGun("gatling_gun", new Item.Properties().group(History.industrialTab).maxStackSize(1));

	public static final Item BATTERY = new ItemBasic("battery", new Item.Properties().group(History.industrialTab));

	public static final Item FLASHLIGHT = new FlashLight("flashlight", new Item.Properties().group(History.industrialTab).maxStackSize(1));

	public static final Item FLAMETHROWER = new FlameThrower("flamethrower", new Item.Properties().group(History.industrialTab).maxStackSize(1).defaultMaxDamage(3200));

	public static final Item MODEL_T = new ModelT_Item("modelt_item", new Item.Properties().maxStackSize(1));

	public static final Item TURRET = new TurretItem("turret", new Item.Properties().maxStackSize(1));

	public static final Item FRAGGRENADE = new FragGrenade("frag_grenade", new Item.Properties().group(History.industrialTab));

	public static final Item MUSTARDGAS = new MustardGas("mustardgas", new Item.Properties().group(History.industrialTab));

	public static final Item PENICILLIN = new ItemBasic("penicillin", new Item.Properties().group(History.industrialTab));

	public static final Item SYRINGE_PENICILLIN = new SyringePenicillin("syringe_penicillin", new Item.Properties().group(History.industrialTab).maxStackSize(1));

	public static final Item ROCKET = new ItemBasic("rocket", new Item.Properties().group(History.industrialTab).maxStackSize(1));



	//Atomic
	public static final Item CONTANCT_GRENADE = new ContactGrenade("contact_grenade", new Item.Properties().group(History.atomicTab));

	public static final Item FISSION_MISSILE = new ItemBasic("fission_missile", new Item.Properties().group(History.atomicTab));

	public static final Item FUSION_MISSILE = new ItemBasic("fusion_missile", new Item.Properties().group(History.atomicTab));

	public static final Item MISSILE = new ItemBasic("missile", new Item.Properties().group(History.atomicTab));

	public static final Item MISSILE_LAUNCHER = new MissileLauncher("missile_launcher", false, new Item.Properties().group(History.atomicTab).maxStackSize(1), ItemTier.IRON);

	public static final Item CIRCUIT = new ItemBasic("circuit", new Item.Properties().group(History.atomicTab));



	//Modern
	public static final Item THREAT_GRENADE = new ThreatGrenade("threat_grenade", new Item.Properties().group(History.modernTab));

	public static final Item VARIABLE_GRENADE = new VariableGrenade("variable_grenade", new Item.Properties());

	public static final Item GUIDED_MISSILE_LAUNCHER = new MissileLauncher("guided_missile_launcher", true, new Item.Properties().group(History.modernTab).maxStackSize(1), ItemTier.DIAMOND);

	public static final Item CALCULATOR = new Calculator("calculator", new Item.Properties().group(History.modernTab).maxStackSize(1));


	//Future
	public static final Item WUNDER_WAFFE = new WunderWaffe("wunderwaffe", new Item.Properties().group(History.futureTab).maxStackSize(1));

	public static final Item ADVANCED_BULLET= new ItemBasic("bullet_advanced", new Item.Properties().group(History.futureTab));

	public static final Item MAGNET_GUN = new MagnetGun("magnetgun", new Item.Properties().group(History.futureTab).maxStackSize(1));

	public static final Item ENERGY_ITEM = new ItemBasic("energybullet", new Item.Properties().group(History.futureTab));

	public static final Item ENERGY = new EntityBasic("entityenergy", new Item.Properties());

	public static final Item GRAVITY_GUN = new GravityGun("gravitygun", new Item.Properties().group(History.futureTab).maxStackSize(1));

	public static final Item TAC19 = new Tac19("tac19", new Item.Properties().group(History.futureTab).maxStackSize(1));

	public static final Item IMR = new IMR("imr", new Item.Properties().group(History.futureTab).maxStackSize(1));

	public static final Item BLACKHOLE = new Blackhole("blackhole", new Item.Properties().group(History.futureTab).maxStackSize(1));

	
	//Music Discs
	public static final Item DISC_SWEDEN = new CustomRecords("sweden", SoundsHandler.RECORD_SWEDEN, new Item.Properties().group(ItemGroup.MISC).rarity(Rarity.RARE).maxStackSize(1));

	
	//Food
	public static final Item COFFEE = new Coffee("coffee", new Item.Properties().group(History.renaissanceTab).food(FoodInit.COFFEE_FOOD).maxStackSize(1));


	   
	@Mod.EventBusSubscriber(modid = History.modid, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Register
	{	
		@SubscribeEvent
		public static void registerItem(final RegistryEvent.Register<Item> event)
		{
			final Item[] items = {
					//Armor
					RUBBER_HELMET,
					RUBBER_CHESTPLATE,
					RUBBER_LEGGINGS,
					RUBBER_BOOTS,
					ROCKET_BOOTS,
					GRAVITY_BOOTS,
					SCUBA_HELMET,
					SCUBA_CHESTPLATE,
					SCUBA_LEGGINGS,
					SCUBA_BOOTS,
					BULLETPROOFVEST,
					FLAMETHROWER_TANK,
					WATERTANK,
					EXO_CHESTPLATE,
					EXO_LEGGINGS ,
					EXO_HOVER,
					METAL_TANK,


					//Ingots
					COPPER_INGOT,
					TIN_INGOT,
					BRONZE_INGOT,
					ALUMINIUM_INGOT,
					SALTPETRE_DUST,
					URANIUM_INGOT,


					//Ancient
					SPEAR,
					STON_MACE,
					IRON_MACE,
					WHEEL,
					THROWING_KNIFE,
					ROCK,
					BOOK_OF_TECHNOLOGIES,


					//Classical
					HOSE,
					ROCK_WHITE,
					ROCK_BLACK,


					//Medieval
					PISTOL,
					BULLET,
					WATER,
					SILK,
					SILK_SHEET,

					
					//Renaissance
					PARACHUTE,
					COFFEEBEAN,
					COFFEEBEAN_ROASTED,


					//Industrial
					FLAMETHROWER,
					FRAGGRENADE,
					LATEX,
					RUBBER,
					ROCKET,
					MUSTARDGAS,
					FLASHLIGHT,
					BATTERY,
					GATLING_GUN,
					TURRET,
					PENICILLIN,
					SYRINGE,
					SYRINGE_PENICILLIN,
					SYRINGE_BLOOD,
					SYRINGE_HIV_BLOOD,
					MODEL_T,
					OIL_BUCKET,

					
					//Atomic
					CONTANCT_GRENADE,
					FISSION_MISSILE,
					FUSION_MISSILE,
					MISSILE,
					MISSILE_LAUNCHER,
					CIRCUIT,


					//Modern
					THREAT_GRENADE,
					VARIABLE_GRENADE,
					GUIDED_MISSILE_LAUNCHER,
					CALCULATOR,


					//Future
					WUNDER_WAFFE,
					ADVANCED_BULLET,
					MAGNET_GUN,
					ENERGY_ITEM,
					ENERGY,
					GRAVITY_GUN,
					TAC19,
					IMR,
					BLACKHOLE,
					
					//Music Discs
					DISC_SWEDEN,
					
					//Food
					COFFEE
			};
		
			PotionInit.registerPotions();

			event.getRegistry().registerAll(items);
			EntityLivingRegistry.registerEntitySpawnEggs(event);
		}
	}
}