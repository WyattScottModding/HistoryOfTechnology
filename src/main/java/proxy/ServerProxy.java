package proxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ServerProxy implements IProxy{
	
	public static void registerEventHandler(Object object) {
		MinecraftForge.EVENT_BUS.register(object);
	}
	
	public static void unregisterEventHandler(Object object) {
		MinecraftForge.EVENT_BUS.unregister(object);
	}
	
	public static void registerEventHandler(Object... objects) {
		for (Object object : objects) {
			MinecraftForge.EVENT_BUS.register(object);
		}
	}
	
	public static void unregisterEventHandler(Object... objects) {
		for (Object object : objects) {
			MinecraftForge.EVENT_BUS.unregister(object);
		}
	}
	

	@Override
	public void init() {
			
	}

	@Override
	public void setup(FMLCommonSetupEvent event) {
		
	}
	
	@Override
	public PlayerEntity getClientPlayer()
	{
		throw new IllegalStateException("Can't call this server-side");
	}
	
	@Override
	public World getClientWorld()
	{
		throw new IllegalStateException("Can't call this server-side");
	}

}