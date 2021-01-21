package handlers;

import init.ItemInit;
import main.History;
import mobs.EntitySilkWorm;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.event.RegistryEvent;

public class EntityLivingRegistry 
{
	public static EntityType<?> SILKWORM = EntityType.Builder.create(EntitySilkWorm::new, EntityClassification.CREATURE).build(History.modid + ":silkworm_entity").setRegistryName("silkworm");
	//public static EntityType<EntityTurret> TURRET;

	public static void registerEntitySpawnEggs(final RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll
		(
			ItemInit.silkworm_egg = registerEntitySpawnEgg(SILKWORM, 000, 11, "history_silkworm_egg")
		);
	}
	
	public static SpawnEggItem registerEntitySpawnEgg(EntityType<?> type, int color1, int color2, String name)
	{
		SpawnEggItem item = new SpawnEggItem(type, color1, color2, new Item.Properties().group(ItemGroup.MISC));
		item.setRegistryName(name);
		return item;
	}
	
	public static void registerEntityWorldSpawns()
	{
		registerEntityWorldSpawn(SILKWORM, EntityClassification.CREATURE, Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.DARK_FOREST, Biomes.DARK_FOREST_HILLS, Biomes.FOREST, Biomes.TALL_BIRCH_FOREST);
	}
	
	public static void registerEntityWorldSpawn(EntityType<?> entity, EntityClassification classifaction, Biome... biomes)
	{
		for (Biome biome : biomes)
		{
			if (biome != null)
			{
				biome.getSpawns(classifaction).add(new SpawnListEntry(entity, 10, 0, 20));
			}
		}
	}
}