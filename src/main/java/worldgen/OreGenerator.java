package worldgen;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.CountRange;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

import init.BlockInit;

public class OreGenerator {

	public static void setupOregen()
	{
		for(Biome biome : ForgeRegistries.BIOMES)
		{
			//number of ores in one vein;  min place it can spawn; some top adjust number;  max place it can spawn;  
			CountRangeConfig placementAluminium = new CountRangeConfig(8, 2, 0, 40);

			biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, 
					new OreFeatureConfig(FillerBlockType.NATURAL_STONE, BlockInit.ALUMINIUM_ORE.getDefaultState(), 6), 
					Placement.COUNT_RANGE, placementAluminium));

			
			CountRangeConfig placementUranium = new CountRangeConfig(2, 2, 0, 20);

			biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, 
					new OreFeatureConfig(FillerBlockType.NATURAL_STONE, BlockInit.URANIUM_ORE.getDefaultState(), 2), 
					Placement.COUNT_RANGE, placementUranium));

			
			CountRangeConfig placementCopper = new CountRangeConfig(8, 2, 0, 60);

			biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, 
					new OreFeatureConfig(FillerBlockType.NATURAL_STONE, BlockInit.COPPER_ORE.getDefaultState(), 8), 
					Placement.COUNT_RANGE, placementCopper));
			
			
			CountRangeConfig placementTin = new CountRangeConfig(8, 2, 0, 60);

			biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, 
					new OreFeatureConfig(FillerBlockType.NATURAL_STONE, BlockInit.TIN_ORE.getDefaultState(), 8), 
					Placement.COUNT_RANGE, placementTin));
			
			
			CountRangeConfig placementSaltpetre = new CountRangeConfig(8, 2, 0, 50);

			biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, 
					new OreFeatureConfig(FillerBlockType.NATURAL_STONE, BlockInit.SALTPETRE.getDefaultState(), 6), 
					Placement.COUNT_RANGE, placementSaltpetre));
			
			/*
			CountRangeConfig placementOil = new CountRangeConfig(5, 2, 2, 40);

			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createCompositeFeature(Feature.MINABLE, 
					new MinableConfig(MinableConfig.IS_ROCK, BlockInit.oil.getDefaultState(), 10), 
					new CountRange(), placementOil));
			*/
		}	
	}
}