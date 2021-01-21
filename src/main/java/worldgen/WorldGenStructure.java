package worldgen;

import java.util.Random;

import main.Reference;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

/*
public class WorldGenStructure extends WorldGenerator implements IStructure
{
	public static String structureName;

	public WorldGenStructure(String name) 
	{
		this.structureName = name;
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) 
	{
		this.generateStructure(worldIn, position);
		return true;
	}

	public static void generateStructure(World world, BlockPos pos)
	{
		//MinecraftServer mcServer = world.getServer();
		TemplateManager manager = worldServer.getStructureTemplateManager();
		ResourceLocation location = new ResourceLocation(Reference.MODID, structureName);
		Template template = manager.getTemplate(location);

		if(template != null)
		{
			BlockState state = world.getBlockState(pos);
			world.notifyBlockUpdate(pos, state, state, 3);
			template.addBlocksToWorldChunk(world, pos, settings);
		}
	}
}
*/
