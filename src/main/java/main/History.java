package main;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.EntitySpear;
import handlers.EntityLivingRegistry;
import handlers.EntityRegistry;
import handlers.KeyBindings;
import handlers.RenderHandler;
import handlers.SoundsHandler;
import handlers.VillagerTradeHandler;
import init.PotionInit;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Potion;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import network.Messages;
import proxy.ClientProxy;
import proxy.IProxy;
import proxy.ServerProxy;
import specialRender.RenderTerniLapilli;
import tabs.AncientTab;
import tabs.AtomicTab;
import tabs.ClassicalTab;
import tabs.FutureTab;
import tabs.IndustrialTab;
import tabs.MedievalTab;
import tabs.ModernTab;
import tabs.RenaissanceTab;
import tileEntities.TileEntityTerniLapilli;
import worldgen.OreGenerator;

@Mod("history")
public class History {

	public static History instance;
	public static final String modid = "history";
	public static final Logger logger = LogManager.getLogger(modid);

	public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

	
	public History()
	{
		//Minecraft.getInstance().player.getRecipeBook();

		instance = this;

		//FluidRegistry.enableUniversalBucket();

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

		MinecraftForge.EVENT_BUS.register(this);


		//ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY, () -> GuiHandler::getClientGuiElement);
	}


	public static final ItemGroup ancientTab = new AncientTab();
	public static final ItemGroup classicalTab = new ClassicalTab();
	public static final ItemGroup medievalTab = new MedievalTab();
	public static final ItemGroup renaissanceTab = new RenaissanceTab();
	public static final ItemGroup industrialTab = new IndustrialTab();
	public static final ItemGroup atomicTab = new AtomicTab();
	public static final ItemGroup modernTab = new ModernTab();
	public static final ItemGroup futureTab = new FutureTab();



	private void setup(final FMLCommonSetupEvent event)
	{
		logger.info("Setup method registered.");

		proxy.init();
		proxy.setup(event);
		OreGenerator.setupOregen();
		KeyBindings.init();
		Messages.registerMessages();

		//MinecraftForge.EVENT_BUS.register(WorldGenCustomStructures.instance);

		//configDir = new File(event.getModConfigurationDirectory() + "/" + Reference.MODID);
		//configDir.mkdirs();
		//ConfigHandler.init(new File(configDir.getPath(), Reference.MODID + ".cfg"));


		//ClientProxy.registerKeyBinds();
	}

	public void clientRegistries(final FMLClientSetupEvent event)
	{
		logger.info("clientRegistries method registered.");

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTerniLapilli.class, new RenderTerniLapilli());
	}

	private void enqueueIMC(final InterModEnqueueEvent event)
	{
		// some example code to dispatch IMC to another mod
		InterModComms.sendTo("examplemod", "helloworld", () -> { logger.info("Hello world from the MDK"); return "Hello world";});
	}

	private void processIMC(final InterModProcessEvent event)
	{
		// some example code to receive and process InterModComms from other mods
		logger.info("Got IMC {}", event.getIMCStream().
				map(m->m.getMessageSupplier().get()).
				collect(Collectors.toList()));
	}
	
	@Mod.EventBusSubscriber(modid = History.modid, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegisterPotions
	{
		@SubscribeEvent
		public static void registerPotions(final RegistryEvent.Register<Potion> event)
		{
			
		}
	}
	

	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{	
		@SubscribeEvent
		public static void registerSounds(final RegistryEvent.Register<SoundEvent> event)
		{
			SoundsHandler.registerSounds();
		}
	}



	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class EntityTypes
	{
		private static final List<EntityType<?>> ENTITY_TYPES = new LinkedList<>();

		public static <T extends Entity> void add(EntityType<T> type)
		{
			ENTITY_TYPES.add(type);
		}

		public static List<EntityType<?>> getEntityTypes()
		{
			return Collections.unmodifiableList(ENTITY_TYPES);
		}

		@SubscribeEvent
		public static void registerEntityTypes(final RegistryEvent.Register<EntityType<?>> event)
		{
			EntityRegistry.register();
			RenderHandler.registerEntityRenders();

			ENTITY_TYPES.forEach(entityType -> event.getRegistry().register(entityType));
		}
	}

	@SubscribeEvent
	public static void registerEntites(final RegistryEvent.Register<EntityType<?>> event) {
		event.getRegistry().registerAll
		(
				EntityLivingRegistry.SILKWORM
				);

		//Entities are spawned in the correct biomes
		EntityLivingRegistry.registerEntityWorldSpawns();
	}

	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {

		logger.info("HELLO from server starting");
	}

	@SubscribeEvent
	public void serverInitRegistries(final FMLServerStartingEvent event)
	{
		//EntityRegistry.registerEntities();
		//EntityLivingRegistry.registerEntities();
		//event.registerServerCommand(new CommandDimensionTeleport());

		//GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
		//GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 0);

	}

	@SubscribeEvent
	public void serverPostInitRegistries(final FMLServerStartedEvent event)
	{
		VillagerTradeHandler.init();
	}


	//Limited Crafting
	/*
	@EventHandler
    public void onWorldLoad(FMLServerStartedEvent event) {
        WorldServer worldServer = DimensionManager.getWorld(0); // default world
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "FakePlayer");
        FakePlayer fakePlayer = new FakePlayer(worldServer, gameProfile);
        MinecraftServer minecraftServer = fakePlayer.mcServer;
        minecraftServer.getServer().worlds[0].getGameRules().setOrCreateGameRule("doLimitedCrafting", "true");
    }
	 */

}