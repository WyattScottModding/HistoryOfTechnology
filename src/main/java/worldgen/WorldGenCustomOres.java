package worldgen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import handlers.RegistryHandler;
import init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.structure.VillageStructure;
import net.minecraftforge.fml.common.IWorldGenerator;
/*
public abstract class WorldGenCustomOres {

	private WorldGenerator uranium = new WorldGenMinable(BlockInit.URANIUM_ORE.getDefaultState(), 2);
	private WorldGenerator copper = new WorldGenMinable(BlockInit.COPPER_ORE.getDefaultState(), 8);
	private WorldGenerator tin = new WorldGenMinable(BlockInit.TIN_ORE.getDefaultState(), 8);
	private WorldGenerator aluminum = new WorldGenMinable(BlockInit.ALUMINIUM_ORE.getDefaultState(), 6);
	private WorldGenerator saltpetre = new WorldGenMinable(BlockInit.SALTPETRE.getDefaultState(), 4);
	private WorldGenerator oil = new WorldGenMinable(BlockInit.OIL_BLOCK.getDefaultState(), 1);


	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider)
	{

		switch(world.getWorldInfo().getDimension())
		{
		case -1:
			break;
		case 0:
			this.generateOre(uranium, world, random, chunkX, chunkZ, 8, 20);
			this.generateOre(copper, world, random, chunkX, chunkZ, 6, 60);
			this.generateOre(tin, world, random, chunkX, chunkZ, 6, 60);
			this.generateOre(aluminum, world, random, chunkX, chunkZ, 4, 40);
			this.generateOre(saltpetre, world, random, chunkX, chunkZ, 4, 50);

			ArrayList<BlockPos> posOil = this.generateOre(oil, world, random, chunkX, chunkZ, 5, 40);
			for(BlockPos element : posOil)
				generateLiquid(world, random, element, BlockInit.OIL_BLOCK);

			break;
		case 1:
			break;

		}
	}



	private ArrayList<BlockPos> generateOre(WorldGenerator gen, World world, Random random, int chunkX, int chunkZ, int frequency, int maxHeight)
	{		
		ArrayList<BlockPos> posList = new ArrayList<BlockPos>();

		for(int i = 0; i < frequency; i++)
		{
			int x = (chunkX * 16) + random.nextInt(15) + 8;
			int z = (chunkZ * 16) + random.nextInt(15) + 8;
			int y = random.nextInt(maxHeight);


			BlockPos pos = new BlockPos(x, y, z);


			if(world.getWorldType() != WorldType.FLAT)
			{
				gen.generate(world, random, pos);

				posList.add(pos);
			}
		}
		return posList;
	}

	public boolean generateLiquid(World world, Random rand, BlockPos position, Block block)
	{
		if (world.getBlockState(position.up()).getBlock() != Blocks.STONE)
		{
			return false;
		}
		else if (world.getBlockState(position.down()).getBlock() != Blocks.STONE)
		{
			return false;
		}
		else
		{
			BlockState iblockstate = world.getBlockState(position);

			if (!iblockstate.getBlock().isAir(iblockstate, world, position) && iblockstate.getBlock() != Blocks.STONE)
			{
				return false;
			}
			else
			{
				int i = 0;

				if (world.getBlockState(position.west()).getBlock() == Blocks.STONE)
				{
					++i;
				}

				if (world.getBlockState(position.east()).getBlock() == Blocks.STONE)
				{
					++i;
				}

				if (world.getBlockState(position.north()).getBlock() == Blocks.STONE)
				{
					++i;
				}

				if (world.getBlockState(position.south()).getBlock() == Blocks.STONE)
				{
					++i;
				}

				int j = 0;

				if (world.isAirBlock(position.west()))
				{
					++j;
				}

				if (world.isAirBlock(position.east()))
				{
					++j;
				}

				if (world.isAirBlock(position.north()))
				{
					++j;
				}

				if (world.isAirBlock(position.south()))
				{
					++j;
				}

				if (i == 3 && j == 1)
				{
					BlockState iblockstate1 = block.getDefaultState();
					world.setBlockState(position, iblockstate1, 2);
					world.immediateBlockTick(position, iblockstate1, rand);
				}

				return true;
			}
		}
	}
}
*/
