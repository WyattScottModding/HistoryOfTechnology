package worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.WoodlandMansionStructure;
import net.minecraftforge.registries.ForgeRegistries;

public class StructureGenerator
{
	
	public static void setupStructures()
	{
		for(Biome biome : ForgeRegistries.BIOMES)
		{
			
			
			//biome.addStructure(pyramid, config);
		}
	}

	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider)
	{
		switch(world.getWorldInfo().getDimension())
		{
		case -1:
			break;
		case 0:
			//this.generateStructure(new WorldGenStructure("pyramid"), world, random, chunkX, chunkZ, 200, Biomes.FOREST, Biomes.DESERT, Biomes.PLAINS, Biomes.SAVANNA);

			break;
		case 1:
			break;
		}
	}

/*
	private void generateStructure(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Biome...biomes)
	{
		ArrayList<Biome> classesList = new ArrayList<Biome> (Arrays.asList(biomes));
		Block topBlock = Blocks.GRASS;

		int x = (chunkX * 16) + random.nextInt(15) + 8;
		int z = (chunkZ * 16) + random.nextInt(15) + 8;
		
		BlockPos tempPos = new BlockPos(x, 10, z);
		
		Biome biome = world.getBiome(tempPos);

		if(biome.equals(Biomes.DESERT))
		{
			topBlock = Blocks.SAND;
		}
	
		int y = calculateGenerationHeight(world, x, z, topBlock) - 1;


		BlockPos pos = new BlockPos(x, y, z);

		Block block = world.getBlockState(new BlockPos(pos.getX() + 9, pos.getY(), pos.getZ() + 17)).getBlock();

		if(block == topBlock)
		{

			if(world.getWorldType() != WorldType.FLAT)
			{
				if(classesList.contains(biome))
				{
					if(random.nextInt(chance) == 0)
					{
						generator.generate(world, random, pos);
					}
				}
			}
		}
	}
*/
	
	private static int calculateGenerationHeight(World world, int x, int z, Block topBlock)
	{
		int y = world.getHeight();
		boolean foundGround = false;

		while(!foundGround && y-- >= 0)
		{
			Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
			foundGround = block == topBlock;
		}

		return y;
	}


}