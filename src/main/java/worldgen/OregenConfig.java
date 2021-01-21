package worldgen;

import net.minecraftforge.common.ForgeConfigSpec;

import net.minecraftforge.common.ForgeConfig;

public class OregenConfig {

	public static ForgeConfigSpec.IntValue aluminium_chance;
	public static ForgeConfigSpec.IntValue uranium_chance;
	public static ForgeConfigSpec.IntValue copper_chance;
	public static ForgeConfigSpec.IntValue tin_chance;
	public static ForgeConfigSpec.IntValue saltpetre_chance;

	public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client) {
		server.comment("Oregen Config");
		
		aluminium_chance = server
				.comment("Maximum number of ore veins of the aluminium ore that can spawn in one chunk.")
				.defineInRange("oregen.aluminium_chance", 6, 1, 100000);
		
		uranium_chance = server
				.comment("Maximum number of ore veins of the uranium ore that can spawn in one chunk.")
				.defineInRange("oregen.uranium_chance", 3, 1, 100000);
		
		copper_chance = server
				.comment("Maximum number of ore veins of the copper ore that can spawn in one chunk.")
				.defineInRange("oregen.copper_chance", 8, 1, 100000);
		
		tin_chance = server
				.comment("Maximum number of ore veins of the tin ore that can spawn in one chunk.")
				.defineInRange("oregen.tin_chance", 8, 1, 100000);
		
		saltpetre_chance = server
				.comment("Maximum number of ore veins of the saltpetre ore that can spawn in one chunk.")
				.defineInRange("oregen.saltpetre_chance", 6, 1, 100000);
	}
}
