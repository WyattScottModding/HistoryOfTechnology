package proxy;

import gui.GuiForge;
import gui.GuiMissileGuidance;
import gui.GuiTerniLapilli;
import handlers.Containers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.fml.client.registry.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ClientProxy implements IProxy{

	public static <T extends Entity> void registerEntityRenderer(Class<T> clazz, IRenderFactory<? super T> rendererFactory) {
		RenderingRegistry.registerEntityRenderingHandler(clazz, rendererFactory);
	}

	public static <T extends TileEntity> void registerSpecialTileEntityRenderer(Class<T> clazz, TileEntityRenderer<? super T> renderer) {
		net.minecraftforge.fml.client.registry.ClientRegistry.bindTileEntitySpecialRenderer(clazz, renderer);
	}

	public static void registerKeybinding(KeyBinding key) {
		net.minecraftforge.fml.client.registry.ClientRegistry.registerKeyBinding(key);
	}

	@Override
	public void init() {
		ScreenManager.registerFactory(Containers.FORGE_CONTAINER, (container, playerInventory, title) -> {
			return new GuiForge(container, playerInventory, title);
		});
		ScreenManager.registerFactory(Containers.MISSILE_CONTAINER, (container, playerInventory, title) -> {
			return new GuiMissileGuidance(container, playerInventory, title);
		});
		ScreenManager.registerFactory(Containers.TERNI_CONTAINER, (container, playerInventory, title) -> {
			return new GuiTerniLapilli(container, playerInventory, title);
		});
	}

	@Override
	public void setup(FMLCommonSetupEvent event) {

	}

	@Override
	public PlayerEntity getClientPlayer()
	{
		return Minecraft.getInstance().player;
	}

	@Override
	public World getClientWorld()
	{
		return Minecraft.getInstance().world;
	}


}