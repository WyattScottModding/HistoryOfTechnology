package handlers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.ItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import updates.EntityUpdater;
import updates.PlayerUpdaters;

@EventBusSubscriber
public class RegistryHandler 
{
	static World world = null;

	public static boolean hidePlayer = false;


	public static void preInitRegistries()
	{
		//FluidInit.registerFluids();

		//BiomeInit.registerBiomes();
		//DimensionInit.registerDimensions();

		//EntityRegistry.registerEntities();
		//EntityLivingRegistry.registerEntities();

		//FluidHandler.registerCustomMeshesAndStates();

		//NetworkHandler.init();
	}

	public static void initRegistries()
	{
		//NetworkRegistry.INSTANCE.registerGuiHandler(History.instance, new GuiHandler());
	}



	@SubscribeEvent
	public static void onLivingUpdateEvent(LivingEvent.LivingUpdateEvent event)
	{		
		if (event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity)event.getEntity();
			RegistryHandler.world = player.world;

			PlayerUpdaters.run(player, world);

		}
		if(event.getEntityLiving() instanceof LivingEntity)
		{
			LivingEntity entity = (LivingEntity) event.getEntityLiving();
			RegistryHandler.world = entity.world;

			if(!world.isRemote)
			{
				EntityUpdater.run(entity, world);
			}
		}
	}

	//@SubscribeEvent
	public static void onItemUpdateEvent(ItemPickupEvent event)
	{	
		/*
		ItemEntity item = event.getOriginalEntity();
		ItemStack stack = event.getStack();
		PlayerEntity player = event.getPlayer();
		World world = null;
		ItemEntity item2 = new ItemEntity(world, player.posX, player.posY, player.posZ, new ItemStack(Items.APPLE));


		if(!player.world.isRemote)
			world = player.world;

		System.out.println("Picked Up: " + stack.getItem().toString());

		if(stack.getItem().equals(Items.BREAD))
		{
			if(!findItemStack(player, Items.BREAD).isEmpty())
				findItemStack(player, Items.BREAD).shrink(1);
			if(!player.inventory.addItemStackToInventory(new ItemStack(Items.APPLE)) && world != null)
				world.addEntity(item2);
		}
		 */
	}

	public static ItemStack findItemStack(PlayerEntity player, Item item)
	{
		player.inventory.getCurrentItem();
		if (ItemStack.areItemsEqualIgnoreDurability(player.getHeldItem(Hand.OFF_HAND), new ItemStack(item)))
		{
			return player.getHeldItem(Hand.OFF_HAND);
		}
		player.inventory.getCurrentItem();
		if (ItemStack.areItemsEqualIgnoreDurability(player.getHeldItem(Hand.MAIN_HAND), new ItemStack(item)))
		{
			return player.getHeldItem(Hand.MAIN_HAND);
		}
		else
		{
			for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
			{
				ItemStack itemstack = player.inventory.getStackInSlot(i);

				player.inventory.getCurrentItem();
				if (ItemStack.areItemsEqualIgnoreDurability(itemstack, new ItemStack(item)))
				{
					return itemstack;
				}
			}

			return ItemStack.EMPTY;
		}
	}

	/*
	@SubscribeEvent
	public static void entityItem(ItemEvent event)
	{
		Entity entity = event.getEntity();

		if(entity instanceof ItemEntity)
		{
			ItemEntity entityItem = (ItemEntity) entity;

			if(entityItem.getItem().isItemEqual(new ItemStack(ItemInit.LODESTONE_INGOT)))
			{
				System.out.println("LODESTONE_INGOT");

				AxisAlignedBB bb = new AxisAlignedBB(entity.posX, entity.posY, entity.posZ, entity.posX + 1, entity.posY + 1, entity.posZ + 1);

				List<ItemEntity> list = world.getEntitiesWithinAABB(ItemEntity.class, bb);


				for(int j = 0; j < list.size(); ++j)
				{
					if(list.get(j) instanceof ItemEntity && list.get(j) != entityItem)
					{
						ItemEntity entityItem2 = list.get(j);

						double distanceSquared = entityItem.getDistanceSq(entityItem2);

						double force = 1 / distanceSquared;

						if(distanceSquared < 0.05)
						{
							entityItem.getMotion().x = 0;
							entityItem.getMotion().y = 0;
							entityItem.getMotion().z = 0;

							entityItem2.getMotion().x = 0;
							entityItem2.getMotion().y = 0;
							entityItem2.getMotion().z = 0;
						}
						else
						{
							double xDiff = entityItem.posX - entityItem2.posX;
							double yDiff = entityItem.posY - entityItem2.posY;
							double zDiff = entityItem.posZ - entityItem2.posZ;

							double magnitude = Math.sqrt(xDiff*xDiff + yDiff*yDiff + zDiff*zDiff);

							entityItem.getMotion().x = force * xDiff / magnitude;
							entityItem.getMotion().y = force * yDiff / magnitude;
							entityItem.getMotion().z = force * zDiff / magnitude;

							entityItem2.getMotion().x = force * xDiff / magnitude;
							entityItem2.getMotion().y = force * yDiff / magnitude;
							entityItem2.getMotion().z = force * zDiff / magnitude;
						}
					}
				}
			}

		}
	}
	 */

	/*
	@SubscribeEvent
	public static void Advancements(WorldEvent.Load event)
	{
		AdvancementManager manager = event.getWorld().getMinecraftServer().getAdvancementManager();

		Iterable<Advancement> iterator = manager.getAdvancements();
		Iterator<Advancement> advancements = iterator.iterator();


		while(advancements.hasNext())
		{
			Advancement advancement = advancements.next();

			System.out.println(advancement.toString());
		}

		//CriterionProgress criterionprogress = playeradvancements.getProgress().getCriterionProgress(player);

	}
	 */
}