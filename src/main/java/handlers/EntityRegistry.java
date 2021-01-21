package handlers;

import com.google.common.base.Function;

import entity.EntityBeam;
import entity.EntityBlackHole;
import entity.EntityBullet;
import entity.EntityContactGrenade;
import entity.EntityEnergy;
import entity.EntityFire;
import entity.EntityFissionBomb;
import entity.EntityFissionMissile;
import entity.EntityFragGrenade;
import entity.EntityFusionBomb;
import entity.EntityFusionMissile;
import entity.EntityKnife;
import entity.EntityMissile;
import entity.EntityModelT;
import entity.EntityMustardGas;
import entity.EntityRock;
import entity.EntitySpear;
import entity.EntityThreatGrenade;
import entity.EntityTrackingMissile;
import entity.EntityTurret;
import entity.EntityWater;
import main.History;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityRegistry 
{
	public static final EntityType<?> SPEAR = EntityType.Builder.<EntitySpear>create(EntityClassification.MISC).setCustomClientFactory(EntitySpear::new).size(0.25F, 0.25F).build("spear").setRegistryName("spear");
	public static final EntityType<?> ROCK;
	public static final EntityType<?> KNIFE = EntityType.Builder.<EntityKnife>create(EntityClassification.MISC).setCustomClientFactory(EntityKnife::new).size(0.25F, 0.25F).build("knife").setRegistryName("knife");
	
	public static final EntityType<?> WATER;
	
	public static final EntityType<?> BULLET;
		
	public static final EntityType<?> FRAGGRENADE;
	//public static final EntityType<?> MODELT;
	
	public static final EntityType<?> FISSIONBOMB;
	public static final EntityType<?> FUSIONBOMB;
	public static final EntityType<?> MUSTARDGAS;
	public static final EntityType<?> CONTACTGRENADE;
	public static final EntityType<?> FUSIONMISSILE;
	public static final EntityType<?> FISSIONMISSILE;
	public static final EntityType<?> FIRE;

	public static final EntityType<?> THREATGRENADE;
	public static final EntityType<?> TRACKINGMISSILE;
	public static final EntityType<?> MISSILE;
	//public static final EntityType<?> TURRET;

	public static final EntityType<?> ENERGY;
	public static final EntityType<?> BEAM;
	public static final EntityType<?> BLACKHOLE;


	static
	{
	//	SPEAR = register("spear", EntitySpear.class, EntitySpear::new, 100, 1, false);
		ROCK = register("rock", EntityRock.class, EntityRock::new, 100, 1, false);
		//KNIFE = register("knife", EntityKnife.class, EntityKnife::new, 100, 1, false);

		WATER = register("water", EntityWater.class, EntityWater::new, 100, 1, false);

		BULLET = register("bullet", EntityBullet.class, EntityBullet::new, 100, 1, false);

		FRAGGRENADE = register("fraggrenade", EntityFragGrenade.class, EntityFragGrenade::new, 100, 1, false);
		//MODELT = register("modelt", EntityModelT.class, EntityModelT::new, 100, 1, false);

		FISSIONBOMB = register("fissionbomb", EntityFissionBomb.class, EntityFissionBomb::new, 100, 1, false);
		FUSIONBOMB = register("fusionbomb", EntityFusionBomb.class, EntityFusionBomb::new, 100, 1, false);
		MUSTARDGAS = register("mustardgas", EntityMustardGas.class, EntityMustardGas::new, 100, 1, false);
		CONTACTGRENADE = register("contactgrenade", EntityContactGrenade.class, EntityContactGrenade::new, 100, 1, false);
		FUSIONMISSILE = register("fusionmissile", EntityFusionMissile.class, EntityFusionMissile::new, 100, 1, false);
		FISSIONMISSILE = register("fissionmissile", EntityFissionMissile.class, EntityFissionMissile::new, 100, 1, false);
		FIRE = register("fire", EntityFire.class, EntityFire::new, 100, 1, false);

		THREATGRENADE = register("threatgrenade", EntityThreatGrenade.class, EntityThreatGrenade::new, 100, 1, false);
		TRACKINGMISSILE = register("trackingmissile", EntityTrackingMissile.class, EntityTrackingMissile::new, 100, 1, false);
		MISSILE = register("missile", EntityMissile.class, EntityMissile::new, 100, 1, false);
		//TURRET = register("turret", EntityTurret.class, EntityTurret::new, 100, 1, false);

		ENERGY = register("energy", EntityEnergy.class, EntityEnergy::new, 100, 1, false);
		BEAM = register("beam", EntityBeam.class, EntityBeam::new, 100, 1, false);
		BLACKHOLE = register("blackhole", EntityBlackHole.class, EntityBlackHole::new, 100, 1, false);

	}
	
	public static void register()
	{
		History.EntityTypes.add(SPEAR);
		History.EntityTypes.add(ROCK);
		History.EntityTypes.add(WATER);

		History.EntityTypes.add(BULLET);

		History.EntityTypes.add(FRAGGRENADE);
		//History.EntityTypes.add(MODELT);

		History.EntityTypes.add(FISSIONBOMB);
		History.EntityTypes.add(FUSIONBOMB);
		History.EntityTypes.add(MUSTARDGAS);
		History.EntityTypes.add(CONTACTGRENADE);
		History.EntityTypes.add(FUSIONMISSILE);
		History.EntityTypes.add(FISSIONMISSILE);
		History.EntityTypes.add(FIRE);

		History.EntityTypes.add(THREATGRENADE);
		History.EntityTypes.add(TRACKINGMISSILE);
		History.EntityTypes.add(MISSILE);

		History.EntityTypes.add(ENERGY);
		History.EntityTypes.add(BEAM);
		History.EntityTypes.add(BLACKHOLE);

	}


	private static <T extends Entity> EntityType<?> register(String id, Class<? extends T> entityClass, Function<? super World, ? extends T> factory, int range, int updateFrequency, boolean sendsVelocityUpdates)
	{
		EntityType<?> type = EntityType.Builder.create(EntityClassification.AMBIENT).setCustomClientFactory(EntitySpear::new).setTrackingRange(range).build(History.modid + ":" + id);
		type.setRegistryName(new ResourceLocation(History.modid, id));
		return type;
	}
	


}