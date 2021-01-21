package handlers;

import containers.ContainerForge;
import containers.ContainerMissileGuidance;
import containers.ContainerTerniLapilli;
import main.History;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

public class Containers {
	//Container Types
	@ObjectHolder("history:forge")
	public static ContainerType<ContainerForge> FORGE_CONTAINER;

	@ObjectHolder("history:missile_guidance")
	public static ContainerType<ContainerMissileGuidance> MISSILE_CONTAINER;

	@ObjectHolder("history:terni_lapilli")
	public static ContainerType<ContainerTerniLapilli> TERNI_CONTAINER;


	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegisterContainers {
		@SubscribeEvent
		public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> event) {
			event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> {
				BlockPos pos = data.readBlockPos();
				return new ContainerForge(windowId, History.proxy.getClientWorld(), pos, inv, History.proxy.getClientPlayer());
			}).setRegistryName("forge"));
			 
			event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> {
				BlockPos pos = data.readBlockPos();
				return new ContainerMissileGuidance(windowId, History.proxy.getClientWorld(), pos, inv, History.proxy.getClientPlayer());
			}).setRegistryName("missile_guidance"));

			event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> {
				BlockPos pos = data.readBlockPos();
				return new ContainerTerniLapilli(windowId, History.proxy.getClientWorld(), pos, inv, History.proxy.getClientPlayer());
			}).setRegistryName("terni_lapilli"));
		}
	}

}
