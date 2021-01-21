package updates;

import blocks.industrial.LightSource;
import handlers.SoundsHandler;
import init.BlockInit;
import init.ItemInit;
import init.PotionInit;
import items.future.MagnetGun;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PlayerUpdaters 
{
	public static boolean oneJump = false;
	public static boolean twoJumps = false;
	public static boolean sideJump = false;
	public static boolean onWall = false;
	public static int hoverTimer = 200;

	public static boolean clicked = false;

	static World world = null;
	public static KeyBinding[] keyBindings = null;

	public static boolean hidePlayer = false;


	public static void run(PlayerEntity player, World world)
	{
		if(!world.isRemote)
		{
			coffeeUpdate(player);
			scubaUpdate(player, world);
			magnetgunUpdate(player, world);
			portalHandler(player, world);
			//morphTest(player, world);
			flashlightRender(player);

		}
		exoSuitUpdate(player, world);
	}

	public static void coffeeUpdate(PlayerEntity player)
	{
		if(player.isSleeping() && player.isPotionActive(PotionInit.CAFFEINE_EFFECT))
		{
			player.wakeUpPlayer(true, true, false);

			ITextComponent msg = new StringTextComponent("You are too hyper to sleep.");
			player.sendMessage(msg);
		}
	}


	public static void scubaUpdate(PlayerEntity player, World world)
	{
		BlockPos pos = player.getPosition();

		Block block1 = world.getBlockState(pos).getBlock();
		Block block2 = world.getBlockState(pos.up()).getBlock();

		if(block1 == Blocks.WATER || block2 == Blocks.WATER)
		{
			boolean hasHelmet = false;
			boolean hasChestplate = false;
			boolean hasLeggings = false;
			boolean hasBoots = false;

			if(player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ItemInit.SCUBA_HELMET)
			{
				hasHelmet = true;
			}

			if(player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ItemInit.SCUBA_CHESTPLATE)
			{
				hasChestplate = true;
			}

			if(player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ItemInit.SCUBA_LEGGINGS)
			{
				hasLeggings = true;
			}

			if(player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ItemInit.SCUBA_BOOTS)
			{
				hasBoots = true;
			}

			if(hasHelmet && hasChestplate && hasLeggings && hasBoots)
			{
				player.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 20, 1));
				player.addPotionEffect(new EffectInstance(Effects.SPEED, 20, 4));

			}

		}
	}


	public static void magnetgunUpdate(PlayerEntity player, World world)
	{
		if(player.getHeldItemMainhand().getItem() instanceof MagnetGun)
		{						
			MagnetGun gun = (MagnetGun) player.getHeldItemMainhand().getItem();

			if(keyBindings != null)
			{				
				if (keyBindings[0].isKeyDown()) 
				{
					gun.reverse = false;
				}

				if (keyBindings[1].isKeyDown()) 
				{
					gun.reverse = true;
				}
			}
		}
	}


	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public static void onEvent(KeyInputEvent event)
	{
		// DEBUG
		//System.out.println("Key Input Event");


		// check each enumerated key binding type for pressed and take appropriate action
		if(keyBindings != null)
		{
			if (keyBindings[0].isPressed()) 
			{
				// DEBUG
				//System.out.println("Key binding ="+keyBindings[0].getKeyDescription());

				// do stuff for this key binding here
				// remember you may need to send packet to server				
			}
			if (keyBindings[1].isPressed()) 
			{
				// DEBUG
				//System.out.println("Key binding ="+keyBindings[1].getKeyDescription());

				// do stuff for this key binding here
				// remember you may need to send packet to server
			}
		}
	}


	public static void portalHandler(PlayerEntity player, World world)
	{

	}

	public static void mustardgasUpdate(PlayerEntity player, World world)
	{
		boolean hasArmor = false;

		if(player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ItemInit.RUBBER_HELMET)
		{
			if(player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ItemInit.RUBBER_CHESTPLATE)
			{
				if(player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ItemInit.RUBBER_LEGGINGS)
				{
					if(player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ItemInit.RUBBER_BOOTS)
					{
						hasArmor = true;
					}
				}
			}
		}


		if(!hasArmor)
		{

			if(!player.isCreative())
			{
				Block block1 = world.getBlockState(player.getPosition()).getBlock();
				Block block2 = world.getBlockState(player.getPosition().up()).getBlock();

				if(block1 == BlockInit.MUSTARD_GAS || block2 == BlockInit.MUSTARD_GAS)
				{
					player.addPotionEffect(new EffectInstance(Effects.POISON, 10, 5));
					player.addPotionEffect(new EffectInstance(Effects.NAUSEA, 10, 5));
				}
			}
		}

	}

	public static void morphTest(LivingEntity entity, World world)
	{
		if(entity.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ItemInit.FLAMETHROWER_TANK)
		{
			hidePlayer = true;
		}
		else
		{
			hidePlayer = false;
		}

	}

	@SubscribeEvent
	public static void renderPlayer(RenderPlayerEvent event)
	{
		PlayerModel<?> model = event.getRenderer().getEntityModel();

		model.bipedLeftLeg.showModel = !hidePlayer;
		model.bipedRightLeg.showModel = !hidePlayer;
		model.bipedBody.showModel = !hidePlayer;
		model.bipedHead.showModel = !hidePlayer;
		model.bipedLeftArm.showModel = !hidePlayer;
		model.bipedRightArm.showModel = !hidePlayer;

	}
/*
	public static void recipes(PlayerEntity player)
	{
		if(player instanceof PlayerEntityMP)
		{
			PlayerEntityMP playerMP = (PlayerEntityMP) player;


			IRecipe recipe = ForgeRegistries.RECIPES.getValue(new ResourceLocation("book_technology"));

			List<IRecipe> recipes = new ArrayList<IRecipe>();

			recipes.add(recipe);

			playerMP.resetRecipes(recipes);
		}
	}
*/

	public static void exoSuitUpdate(PlayerEntity player, World world)
	{		
		if(world.getGameTime() % 10 == 0)
			player.fallDistance--;

		if(player.onGround)
		{
			oneJump = false;
			twoJumps = false;
			sideJump = false;
			hoverTimer = 200;
		}

		boolean hasChestplate = false;
		boolean hasLeggings = false;
		boolean hasHover = false;

		if(player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ItemInit.EXO_CHESTPLATE)
		{
			hasChestplate = true;
			player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 20, 1, false , false));
		}

		if(player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == ItemInit.EXO_LEGGINGS)
		{
			hasLeggings = true;
			player.addPotionEffect(new EffectInstance(Effects.SPEED, 20, 1, false, false));
		}

		if(hasChestplate && hasLeggings)
		{
			player.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 20, 1, false, false));

			//Boosting
			if(!onWall)
			{
				if(Minecraft.getInstance().gameSettings.keyBindJump.isPressed())
				{
					if(!oneJump)
					{
						oneJump = true;
					}
					else if(!twoJumps)
					{
						boostUp(player, world);
						twoJumps = true;
					}
				}
				if(Minecraft.getInstance().gameSettings.keyBindForward.isPressed())
				{
					if(oneJump && !sideJump)
					{
						boostForward(player, world);
						sideJump = true;
					}
				}
				else if(Minecraft.getInstance().gameSettings.keyBindBack.isPressed())
				{
					if(oneJump && !sideJump)
					{
						boostBack(player, world);
						sideJump = true;
					}
				}
				else if(Minecraft.getInstance().gameSettings.keyBindLeft.isPressed())
				{
					if(oneJump && !sideJump)
					{
						boostLeft(player, world);
						sideJump = true;
					}
				}
				else if(Minecraft.getInstance().gameSettings.keyBindRight.isPressed())
				{
					if(oneJump && !sideJump)
					{
						boostRight(player, world);
						sideJump = true;
					}
				}
			}


			//Wall Running
			/*
			BlockPos pos = player.getPosition();

			if(world.getBlockState(pos.down()).getBlock() == Blocks.AIR && Minecraft.getInstance().gameSettings.keyBindForward.isKeyDown() && player.getMotion().y < .1 && !player.capabilities.isFlying && player.collided)
			{
				boolean west = world.getBlockState(pos.west()).isSideSolid(world, pos.west(), Direction.EAST);
				boolean east = world.getBlockState(pos.east()).isSideSolid(world, pos.east(), Direction.WEST);
				boolean north = world.getBlockState(pos.north()).isSideSolid(world, pos.north(), Direction.SOUTH);
				boolean south = world.getBlockState(pos.south()).isSideSolid(world, pos.south(), Direction.NORTH);

				onWallEast = west || east;
				onWallSouth = south || north;

				if(onWallEast || onWallSouth)
				{
					onWall = true;
					twoJumps = true;

					player.addVelocity(0, -player.getMotion().y + .01, 0);
					player.velocityChanged = true;
					player.fallDistance = 0.0F;

					//Ability to jump while wall running
					if(Minecraft.getInstance().gameSettings.keyBindJump.isPressed())
					{
						boostUp(player, world);
					}

					if(east && player.rotationYaw <= 45 && player.rotationYaw >= -45)
					{
						player.getMotion().z = .35;							
					}
					else if(west && player.rotationYaw >= 135 || player.rotationYaw <= -135)
					{
						player.getMotion().z = -.35;
					}
					else if(south && player.rotationYaw <= 45 && player.rotationYaw >= -135)
					{
						player.getMotion().x = .35;
					}
					else if(north && player.rotationYaw >= 45 && player.rotationYaw <= 135)
					{
						player.getMotion().x = -.35;
					}

					player.velocityChanged = true;
				}
				else
					onWall = false;
			}
			 */


			if(player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == ItemInit.EXO_HOVER)
			{
				hasHover = true;
			}

			//Ability to Hover
			if(hasHover)
			{
				if(oneJump || twoJumps)
				{
					if(Minecraft.getInstance().gameSettings.keyBindSprint.isKeyDown() && !player.isCreative() && hoverTimer > 0)
					{
						float pitch = player.rotationPitch;
						float yaw = player.rotationYaw;

						float f = .2F;

						double x = 0;
						double z = 0;

						if(Minecraft.getInstance().gameSettings.keyBindLeft.isKeyDown())
						{ 
							yaw = player.rotationYaw - 90;
							x = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
							z = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
						}

						if(Minecraft.getInstance().gameSettings.keyBindRight.isKeyDown())
						{ 
							yaw = player.rotationYaw + 90;
							x += (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
							z += (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
						}

						if(Minecraft.getInstance().gameSettings.keyBindForward.isKeyDown())
						{ 
							yaw = player.rotationYaw;
							x += (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
							z += (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
						}

						if(Minecraft.getInstance().gameSettings.keyBindBack.isKeyDown())
						{ 
							yaw = player.rotationYaw + 180;
							x += (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
							z += (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
						}


						player.setVelocity(x, 0, z);
						player.velocityChanged = true;	
						sideJump = true;

						//Spawn ParticleTypes
						double x2 = Math.cos(Math.toRadians(player.rotationYaw)) / 4;
						double z2 = Math.sin(Math.toRadians(player.rotationYaw)) / 4;

						world.addParticle(ParticleTypes.CLOUD, player.posX + x2, player.posY -.2, player.posZ + z2, 0, -.1, 0);
						world.addParticle(ParticleTypes.CLOUD, player.posX - x2, player.posY -.2, player.posZ - z2, 0, -.1, 0);


						hoverTimer--;
					}
				}
			}
		}
	}

	public static void boostUp(PlayerEntity player, World world)
	{
		if(player.isInWater())
			player.addVelocity(0, 20, 0);
		else
			player.addVelocity(0, 1.8, 0);
		player.velocityChanged = true;
		player.jump();
		player.fallDistance = 0.0F;

		if(player.getMotion().x != 0 || player.getMotion().z != 0)
			player.moveForward = 2.0F;

		world.playSound(player, player.posX, player.posY, player.posZ, SoundsHandler.PLAYER_BOOSTJUMP, SoundCategory.PLAYERS, 1.0F, 1.0F);
	}

	public static void boostForward(PlayerEntity player, World world)
	{
		float pitch = player.rotationPitch;
		float yaw = player.rotationYaw;

		float f = 0.75F;

		double x = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
		double z = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);

		player.addVelocity(x, 0, z);
		player.velocityChanged = true;
		player.fallDistance = 0.0F;

		world.playSound(player, player.posX, player.posY, player.posZ, SoundsHandler.PLAYER_BOOSTJUMP, SoundCategory.PLAYERS, 1.0F, 1.0F);
	}

	public static void boostBack(PlayerEntity player, World world)
	{
		float pitch = player.rotationPitch;
		float yaw = player.rotationYaw;

		float f = 0.75F;

		double x = -(double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
		double z = -(double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);

		player.addVelocity(x, 0, z);
		player.velocityChanged = true;
		player.fallDistance = 0.0F;

		world.playSound(player, player.posX, player.posY, player.posZ, SoundsHandler.PLAYER_BOOSTJUMP, SoundCategory.PLAYERS, 1.0F, 1.0F);
	}

	public static void boostLeft(PlayerEntity player, World world)
	{
		float pitch = player.rotationPitch;
		float yaw = player.rotationYaw - 90;

		float f = 0.75F;

		double x = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
		double z = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);

		player.addVelocity(x, 0, z);
		player.velocityChanged = true;
		player.fallDistance = 0.0F;

		world.playSound(player, player.posX, player.posY, player.posZ, SoundsHandler.PLAYER_BOOSTJUMP, SoundCategory.PLAYERS, 1.0F, 1.0F);
	}

	public static void boostRight(PlayerEntity player, World world)
	{
		float pitch = player.rotationPitch;
		float yaw = player.rotationYaw + 90;

		float f = 0.75F;

		double x = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
		double z = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);

		player.addVelocity(x, 0, z);
		player.velocityChanged = true;
		player.fallDistance = 0.0F;

		world.playSound(player, player.posX, player.posY, player.posZ, SoundsHandler.PLAYER_BOOSTJUMP, SoundCategory.PLAYERS, 1.0F, 1.0F);
	}


	public static void flashlightRender(PlayerEntity player)
	{
		if (clicked)
		{
			if ((player.getHeldItemMainhand() != null && LightSource.isLightEmittingItem(player.getHeldItemMainhand().getItem())) || (player.getHeldItemOffhand() != null && LightSource.isLightEmittingItem(player.getHeldItemOffhand().getItem())))
			{
				int blockX = MathHelper.floor(player.posX);
				int blockY = MathHelper.floor(player.posY-0.2D - player.getYOffset());
				int blockZ = MathHelper.floor(player.posZ);
				// place light at head level
				BlockPos blockLocation = new BlockPos(blockX, blockY, blockZ).up();
				if (player.world.getBlockState(blockLocation).getBlock() == Blocks.AIR)
				{
					player.world.setBlockState(blockLocation, BlockInit.LIGHT_SOURCE.getDefaultState());

				}
				else if (player.world.getBlockState(blockLocation.add(player.getLookVec().x, 
						player.getLookVec().y, player.getLookVec().z)).getBlock() == Blocks.AIR)
				{

					player.world.setBlockState(blockLocation.add(player.getLookVec().x, player.getLookVec().y, 
							player.getLookVec().z), BlockInit.LIGHT_SOURCE.getDefaultState());

				}
			}
		}
	}

	
}