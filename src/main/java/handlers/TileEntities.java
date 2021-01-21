package handlers;

import init.BlockInit;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import tileEntities.TileEntityElectricLamp;
import tileEntities.TileEntityFaucet;
import tileEntities.TileEntityForceField;
import tileEntities.TileEntityForge;
import tileEntities.TileEntityLightBridge;
import tileEntities.TileEntityLightBridgeBlock;
import tileEntities.TileEntityLightSource;
import tileEntities.TileEntityMissileGuidanceSystem;
import tileEntities.TileEntityMustardGas;
import tileEntities.TileEntityPipe;
import tileEntities.TileEntityTerniLapilli;
import tileEntities.TileEntityWaterPipe;

public class TileEntities 
{

	@ObjectHolder("history:forcefieldblock")
	public static TileEntityType<TileEntityFaucet> FORCE_FIELD_BLOCK;
	
	@ObjectHolder("history:booktech")
	public static TileEntityType<TileEntityFaucet> BOOK_TECH;
	
	@ObjectHolder("history:calc")
	public static TileEntityType<TileEntityFaucet> CALC;
	
	@ObjectHolder("history:electriclamp")
	public static TileEntityType<TileEntityFaucet> ELECTRIC_LAMP;
	
	@ObjectHolder("history:faucet")
	public static TileEntityType<TileEntityFaucet> FAUCET;
	
	@ObjectHolder("history:forcefield")
	public static TileEntityType<TileEntityFaucet> FORCE_FIELD;
	
	@ObjectHolder("history:forge")
	public static TileEntityType<TileEntityFaucet> FORGE;
	
	@ObjectHolder("history:lightbridge")
	public static TileEntityType<TileEntityFaucet> LIGHT_BRIDGE;
	
	@ObjectHolder("history:lightbridgeblock")
	public static TileEntityType<TileEntityFaucet> LIGHT_BRIDGE_BLOCK;
	
	@ObjectHolder("history:lightsource")
	public static TileEntityType<TileEntityFaucet> LIGHT_SOURCE;
	
	@ObjectHolder("history:missile_guidance")
	public static TileEntityType<TileEntityFaucet> MISSILE_GUIDANCE;
	
	@ObjectHolder("history:mustardgas")
	public static TileEntityType<TileEntityFaucet> MUSTARDGAS;
	
	@ObjectHolder("history:pipe")
	public static TileEntityType<TileEntityFaucet> PIPE;
	
	@ObjectHolder("history:terni_lapilli")
	public static TileEntityType<TileEntityFaucet> TERNI;
	
	@ObjectHolder("history:waterpipe")
	public static TileEntityType<TileEntityFaucet> WATER_PIPE;

	
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {
			//event.getRegistry().register(TileEntityType.Builder.create(TileEntityBookTechnology::new, ItemInit.BOOK_OF_TECHNOLOGIES).build(null));
			event.getRegistry().register(TileEntityType.Builder.create(TileEntityElectricLamp::new, BlockInit.ELECTRIC_LAMP, BlockInit.ELECTRIC_LAMP1, BlockInit.ELECTRIC_LAMP3, BlockInit.ELECTRIC_LAMP4, BlockInit.ELECTRIC_LAMP5, BlockInit.ELECTRIC_LAMP6, BlockInit.ELECTRIC_LAMP7, BlockInit.ELECTRIC_LAMP8, BlockInit.ELECTRIC_LAMP9, BlockInit.ELECTRIC_LAMP10, BlockInit.ELECTRIC_LAMP11, BlockInit.ELECTRIC_LAMP12, BlockInit.ELECTRIC_LAMP13, BlockInit.ELECTRIC_LAMP14, BlockInit.ELECTRIC_LAMP15).build(null).setRegistryName("electriclamp"));
			event.getRegistry().register(TileEntityType.Builder.create(TileEntityFaucet::new, BlockInit.FAUCET, BlockInit.FAUCET_ON).build(null).setRegistryName("faucet"));
			event.getRegistry().register(TileEntityType.Builder.create(TileEntityForceField::new, BlockInit.FORCE_FIELD_GENERATOR).build(null).setRegistryName("forcefield"));
			event.getRegistry().register(TileEntityType.Builder.create(TileEntityForge::new, BlockInit.FORGE).build(null).setRegistryName("forge"));
			event.getRegistry().register(TileEntityType.Builder.create(TileEntityLightBridge::new, BlockInit.LIGHT_BRIDGE).build(null).setRegistryName("lightbridge"));
			event.getRegistry().register(TileEntityType.Builder.create(TileEntityLightBridgeBlock::new, BlockInit.LIGHT_BRIDGE_BLOCK).build(null).setRegistryName("lightbridgeblock"));
			event.getRegistry().register(TileEntityType.Builder.create(TileEntityLightSource::new, BlockInit.LIGHT_SOURCE).build(null).setRegistryName("light_source"));
			event.getRegistry().register(TileEntityType.Builder.create(TileEntityMissileGuidanceSystem::new, BlockInit.MISSILE_GUIDANCE).build(null).setRegistryName("missile_guidance"));
			event.getRegistry().register(TileEntityType.Builder.create(TileEntityMustardGas::new, BlockInit.MUSTARD_GAS).build(null).setRegistryName("mustardgas"));
			event.getRegistry().register(TileEntityType.Builder.create(TileEntityPipe::new, BlockInit.PIPE).build(null).setRegistryName("pipe"));
			event.getRegistry().register(TileEntityType.Builder.create(TileEntityWaterPipe::new, BlockInit.PIPE_WATER).build(null).setRegistryName("waterpipe"));
			event.getRegistry().register(TileEntityType.Builder.create(TileEntityTerniLapilli::new, BlockInit.TERNI_LAPILLI).build(null).setRegistryName("terni_lapilli"));	
		
			//Register TESR
			TESRHandler.registerTileEntites();
		}
	}
}
