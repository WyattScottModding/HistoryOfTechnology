package init;

import com.google.common.base.Preconditions;

import blocks.ancient.BlockBasic;
import blocks.ancient.Forge;
import blocks.ancient.MetalOres;
import blocks.ancient.Rope;
import blocks.atomic.FissionBomb;
import blocks.atomic.FusionBomb;
import blocks.atomic.MissileGuidanceSystem;
import blocks.classical.Catapult;
import blocks.classical.Faucet;
import blocks.classical.FaucetOn;
import blocks.classical.Pipe;
import blocks.classical.TerniLapilli;
import blocks.classical.WaterPipe;
import blocks.future.ForceFieldGenerator;
import blocks.future.LightBridge;
import blocks.future.LightBridgeBlock;
import blocks.industrial.BlockMustardGas;
import blocks.industrial.BlockOil;
import blocks.industrial.ConveyorBelt;
import blocks.industrial.ElectricLamp;
import blocks.industrial.Fridge;
import blocks.industrial.LightSource;
import blocks.industrial.MicroScope;
import blocks.medieval.Cannon;
import blocks.medieval.LandMine;
import blocks.renaissance.CoffeePlant;
import main.History;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(History.modid)
public class BlockInit 
{
	//Metal Ores
	public static final Block ALUMINIUM_ORE = new MetalOres("aluminium_ore", Material.ROCK, History.industrialTab, 2.5F, 0, 30.0F);

	public static final Block COPPER_ORE = new MetalOres("copper_ore", Material.ROCK, History.ancientTab, 2.0F, 0, 10.0F);

	public static final Block SALTPETRE = new MetalOres("saltpetre", Material.ROCK, History.medievalTab, 3.0F, 0, 8.0F);

	public static final Block TIN_ORE = new MetalOres("tin_ore", Material.ROCK, History.ancientTab, 2.0F, 0, 10.0F);

	public static final Block URANIUM_ORE = new MetalOres("uranium_ore", Material.ROCK, History.atomicTab, 6.0F, 8, 50.0F);



	//Ancient Blocks
	public static final Block COPPER_BLOCK = new BlockBasic("copper_block", Material.IRON, History.ancientTab, 5.0F, 0, 30.0F);

	public static final Block FORGE = new Forge("forge");
	
	public static final Block ROPE = new Rope("rope");

	public static final Block TIN_BLOCK = new BlockBasic("tin_block", Material.IRON, History.ancientTab, 5.0F, 0, 30.0F);


	//Classical Blocks
	public static final Block CATAPULT = new Catapult("catapult");
	
	public static final Block FAUCET = new Faucet("faucet");

	public static final Block FAUCET_ON = new FaucetOn("faucet_on");

	public static final Block PIPE = new Pipe("pipe");

	public static final Block PIPE_WATER = new WaterPipe("pipe_water");

	public static final Block TERNI_LAPILLI = new TerniLapilli("terni_lapilli");


	//Medieval Blocks
	public static final Block CANNON = new Cannon("cannon");

	public static final Block LANDMINE = new LandMine("landmine");


	//Renaissance Blocks
	public static final Block COFFEE_PLANT = new CoffeePlant("coffee_plant");

	public static final Block MICROSCOPE = new MicroScope("microscope");


	//Industrial Blocks
	public static final Block ALUMINIUM_BLOCK = new BlockBasic("aluminium_block", Material.IRON, History.industrialTab, 5.0F, 0, 30.0F);

	public static final Block CONVEYOR_BELT = new ConveyorBelt("conveyorbelt");

	public static final Block ELECTRIC_LAMP = new ElectricLamp("electric_lamp", Block.Properties.create(Material.GLASS));
	public static final Block ELECTRIC_LAMP1 = new ElectricLamp("electric_lamp1", Block.Properties.create(Material.GLASS).lightValue(2));
	public static final Block ELECTRIC_LAMP2 = new ElectricLamp("electric_lamp2", Block.Properties.create(Material.GLASS).lightValue(4));
	public static final Block ELECTRIC_LAMP3 = new ElectricLamp("electric_lamp3", Block.Properties.create(Material.GLASS).lightValue(6));
	public static final Block ELECTRIC_LAMP4 = new ElectricLamp("electric_lamp4", Block.Properties.create(Material.GLASS).lightValue(8));
	public static final Block ELECTRIC_LAMP5 = new ElectricLamp("electric_lamp5", Block.Properties.create(Material.GLASS).lightValue(10));
	public static final Block ELECTRIC_LAMP6 = new ElectricLamp("electric_lamp6", Block.Properties.create(Material.GLASS).lightValue(12));
	public static final Block ELECTRIC_LAMP7 = new ElectricLamp("electric_lamp7", Block.Properties.create(Material.GLASS).lightValue(14));
	public static final Block ELECTRIC_LAMP8 = new ElectricLamp("electric_lamp8", Block.Properties.create(Material.GLASS).lightValue(16));
	public static final Block ELECTRIC_LAMP9 = new ElectricLamp("electric_lamp9", Block.Properties.create(Material.GLASS).lightValue(18));
	public static final Block ELECTRIC_LAMP10 = new ElectricLamp("electric_lamp10", Block.Properties.create(Material.GLASS).lightValue(20));
	public static final Block ELECTRIC_LAMP11 = new ElectricLamp("electric_lamp11", Block.Properties.create(Material.GLASS).lightValue(22));
	public static final Block ELECTRIC_LAMP12 = new ElectricLamp("electric_lamp12", Block.Properties.create(Material.GLASS).lightValue(24));
	public static final Block ELECTRIC_LAMP13 = new ElectricLamp("electric_lamp13", Block.Properties.create(Material.GLASS).lightValue(26));
	public static final Block ELECTRIC_LAMP14 = new ElectricLamp("electric_lamp14", Block.Properties.create(Material.GLASS).lightValue(28));
	public static final Block ELECTRIC_LAMP15 = new ElectricLamp("electric_lamp15", Block.Properties.create(Material.GLASS).lightValue(30));

	public static final Block FRIDGE = new Fridge("fridge");

	public static final Block LIGHT_SOURCE = new LightSource("light_source");

	public static final Block MUSTARD_GAS = new BlockMustardGas("mustard_gas");
	
	public static final Block OIL_BLOCK = new BlockOil("oil");


	//Atomic Blocks
	public static final Block FISSION_BOMB = new FissionBomb("fissionbomb");

	public static final Block FUSION_BOMB = new FusionBomb("fusionbomb");

	public static final Block MISSILE_GUIDANCE = new MissileGuidanceSystem("missile_guidance");


	//Future
	public static final Block FORCE_FIELD_GENERATOR = new ForceFieldGenerator("force_field_generator");

	public static final Block LIGHT_BRIDGE = new LightBridge("light_bridge");

	public static final Block LIGHT_BRIDGE_BLOCK = new LightBridgeBlock("light_bridge_block");


	@Mod.EventBusSubscriber(modid = History.modid, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Register
	{
		@SubscribeEvent
		public static void registerBlock(final RegistryEvent.Register<Block> event)
		{
			final Block[] blocks = {
					//Metal Ores
					COPPER_ORE,
					TIN_ORE,
					ALUMINIUM_ORE,
					URANIUM_ORE,
					SALTPETRE,


					//Ancient Blocks
					ROPE,
					FORGE,
					COPPER_BLOCK,
					TIN_BLOCK,


					//Classical Blocks
					CATAPULT,
					PIPE,
					PIPE_WATER,
					FAUCET,
					FAUCET_ON,
					TERNI_LAPILLI,


					//Renaissance Blocks
					COFFEE_PLANT,


					//Medieval Blocks
					CANNON,


					//Industrial Blocks
					MICROSCOPE,
					CONVEYOR_BELT,
					LANDMINE,
					ALUMINIUM_BLOCK,
					MUSTARD_GAS,
					LIGHT_SOURCE,
					ELECTRIC_LAMP,
					ELECTRIC_LAMP1,
					ELECTRIC_LAMP2,
					ELECTRIC_LAMP3,
					ELECTRIC_LAMP4,
					ELECTRIC_LAMP5,
					ELECTRIC_LAMP6,
					ELECTRIC_LAMP7,
					ELECTRIC_LAMP8,
					ELECTRIC_LAMP9,
					ELECTRIC_LAMP10,
					ELECTRIC_LAMP11,
					ELECTRIC_LAMP12,
					ELECTRIC_LAMP13,
					ELECTRIC_LAMP14,
					ELECTRIC_LAMP15,
					FRIDGE,
					//	OIL_BLOCK,


					//Atomic Blocks
					FISSION_BOMB,
					FUSION_BOMB,
					MISSILE_GUIDANCE,


					//Future
					FORCE_FIELD_GENERATOR,
					LIGHT_BRIDGE,
					LIGHT_BRIDGE_BLOCK
			};

			event.getRegistry().registerAll(blocks);
		}

		@SubscribeEvent
		public static void registerBlockItems(final RegistryEvent.Register<Item> event)
		{
			final BlockItem[] items = {
					//Metal Ores
					new BlockItem(BlockInit.COPPER_ORE, new Item.Properties().group(History.ancientTab)),

					new BlockItem(BlockInit.TIN_ORE, new Item.Properties().group(History.ancientTab)),

					new BlockItem(BlockInit.ALUMINIUM_ORE, new Item.Properties().group(History.industrialTab)),

					new BlockItem(BlockInit.URANIUM_ORE, new Item.Properties().group(History.atomicTab)),

					new BlockItem(BlockInit.SALTPETRE, new Item.Properties().group(History.medievalTab)),


					//Ancient Blocks
					new BlockItem(BlockInit.ROPE, new Item.Properties().group(History.ancientTab)),

					new BlockItem(BlockInit.FORGE, new Item.Properties().group(History.ancientTab)),

					new BlockItem(BlockInit.COPPER_BLOCK, new Item.Properties().group(History.ancientTab)),

					new BlockItem(BlockInit.TIN_BLOCK, new Item.Properties().group(History.ancientTab)),


					//Classical Blocks
					new BlockItem(BlockInit.CATAPULT, new Item.Properties().maxStackSize(1)),

					new BlockItem(BlockInit.PIPE, new Item.Properties().group(History.classicalTab)),

					new BlockItem(BlockInit.PIPE_WATER, new Item.Properties()),

					new BlockItem(BlockInit.FAUCET, new Item.Properties().group(History.classicalTab)),

					new BlockItem(BlockInit.FAUCET_ON, new Item.Properties()),

					new BlockItem(BlockInit.TERNI_LAPILLI, new Item.Properties().group(History.classicalTab).maxStackSize(1)),


					//Medieval Blocks
					new BlockItem(BlockInit.CANNON, new Item.Properties().maxStackSize(1)),

					new BlockItem(BlockInit.LANDMINE, new Item.Properties().group(History.medievalTab)),


					//Renaissance Blocks
					new BlockItem(BlockInit.COFFEE_PLANT, new Item.Properties()),

					new BlockItem(BlockInit.MICROSCOPE, new Item.Properties().maxStackSize(1)),


					//Industrial Blocks
					new BlockItem(BlockInit.CONVEYOR_BELT, new Item.Properties().group(History.industrialTab)),

					new BlockItem(BlockInit.ALUMINIUM_BLOCK, new Item.Properties().group(History.industrialTab)),

					new BlockItem(BlockInit.MUSTARD_GAS, new Item.Properties()),

					new BlockItem(BlockInit.LIGHT_SOURCE, new Item.Properties()),

					new BlockItem(BlockInit.ELECTRIC_LAMP, new Item.Properties().group(History.industrialTab)),

					new BlockItem(BlockInit.ELECTRIC_LAMP1, new Item.Properties()),

					new BlockItem(BlockInit.ELECTRIC_LAMP2, new Item.Properties()),

					new BlockItem(BlockInit.ELECTRIC_LAMP3, new Item.Properties()),

					new BlockItem(BlockInit.ELECTRIC_LAMP4, new Item.Properties()),

					new BlockItem(BlockInit.ELECTRIC_LAMP5, new Item.Properties()),

					new BlockItem(BlockInit.ELECTRIC_LAMP6, new Item.Properties()),

					new BlockItem(BlockInit.ELECTRIC_LAMP7, new Item.Properties()),

					new BlockItem(BlockInit.ELECTRIC_LAMP8, new Item.Properties()),

					new BlockItem(BlockInit.ELECTRIC_LAMP9, new Item.Properties()),

					new BlockItem(BlockInit.ELECTRIC_LAMP10, new Item.Properties()),

					new BlockItem(BlockInit.ELECTRIC_LAMP11, new Item.Properties()),

					new BlockItem(BlockInit.ELECTRIC_LAMP12, new Item.Properties()),

					new BlockItem(BlockInit.ELECTRIC_LAMP13, new Item.Properties()),

					new BlockItem(BlockInit.ELECTRIC_LAMP14, new Item.Properties()),

					new BlockItem(BlockInit.ELECTRIC_LAMP15, new Item.Properties()),

					new BlockItem(BlockInit.FRIDGE, new Item.Properties().group(History.industrialTab)),


					//	new BlockItem(BlockInit.OIL_BLOCK, new Item.Properties()),


					//Atomic Blocks
					new BlockItem(BlockInit.FISSION_BOMB, new Item.Properties().group(History.atomicTab)),

					new BlockItem(BlockInit.FUSION_BOMB, new Item.Properties().group(History.atomicTab)),

					new BlockItem(BlockInit.MISSILE_GUIDANCE, new Item.Properties().group(History.atomicTab)),


					//Future
					new BlockItem(BlockInit.FORCE_FIELD_GENERATOR, new Item.Properties().group(History.futureTab).maxStackSize(1)),

					new BlockItem(BlockInit.LIGHT_BRIDGE, new Item.Properties().group(History.futureTab).maxStackSize(1)),

					new BlockItem(BlockInit.LIGHT_BRIDGE_BLOCK, new Item.Properties().maxStackSize(1))
			};

			for(final BlockItem item : items)
			{
				final Block block = item.getBlock();
				final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(), "Block %s has a null registry name", block);
				event.getRegistry().register(item.setRegistryName(registryName));
			}
		}
	}
}