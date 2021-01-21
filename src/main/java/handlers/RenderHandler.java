package handlers;

import entity.EntityBeam;
import entity.EntityFire;
import entity.EntitySpear;
import mobs.EntitySilkWorm;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import render.RenderBeam;
import render.RenderFire;
import render.RenderSilkWorm;
import render.RenderSpear;

@OnlyIn(Dist.CLIENT)
public class RenderHandler {


	public static void registerEntityRenders()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntitySilkWorm.class, RenderSilkWorm::new);
		//RenderingRegistry.registerEntityRenderingHandler(EntityBeam.class, RenderBeam::new);
		//RenderingRegistry.registerEntityRenderingHandler(EntitySpear.class, RenderSpear::new);
		//RenderingRegistry.registerEntityRenderingHandler(EntityFire.class, RenderFire::new);
	}
}