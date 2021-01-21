package worldgen;

import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.world.gen.feature.template.PlacementSettings;

public interface IStructure 
{
	//public static final WorldServer worldServer = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(0);
	public static final PlacementSettings settings = (new PlacementSettings()).setChunk(null).setIgnoreEntities(false).setMirror(Mirror.NONE).setRotation(Rotation.NONE);
}
