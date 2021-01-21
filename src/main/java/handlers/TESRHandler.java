package handlers;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import specialRender.RenderTerniLapilli;
import tileEntities.TileEntityTerniLapilli;


public class TESRHandler 
{
	public static void registerTileEntites()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTerniLapilli.class, new RenderTerniLapilli());
	}
}
