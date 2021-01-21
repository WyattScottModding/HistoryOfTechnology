package items.future;

import java.util.List;

import handlers.SoundsHandler;
import init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class MagnetGun extends BowItem
{

	public boolean active = false;
	public double prevY1 = -5;
	public double prevY2 = -5;
	public double prevY3 = -5;
	public double prevY4 = -5;

	public double prevX1 = -5;
	public double prevX2 = -5;
	public double prevX3 = -5;
	public double prevX4 = -5;

	public double prevZ1 = -5;
	public double prevZ2 = -5;
	public double prevZ3 = -5;
	public double prevZ4 = -5;

	int soundCounter = 0;

	int attackCooldown = 0;

	public boolean reverse = false;


	public MagnetGun(String name, Item.Properties builder)
	{
		super(builder);
		this.setRegistryName(name);

		this.addPropertyOverride(new ResourceLocation("fired"), new IItemPropertyGetter()
		{
			@Override
			public float call(ItemStack stack, World world, LivingEntity entity) 
			{
				if(entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack)
					return !active ? 0.0F : 1.0F;
				else
					return 0.0F;
			}
		});

		this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
		{
			@Override
			public float call(ItemStack stack, World world, LivingEntity entity) 
			{
				if (entity == null)
				{
					return 0.0F;
				}
				else
				{
					return entity.getActiveItemStack().getItem() != ItemInit.MAGNET_GUN ? 0.0F : (float)(stack.getUseDuration() - entity.getItemInUseCount()) / 20.0F;
				}
			}
		});
		this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter()
		{
			@Override
			public float call(ItemStack stack, World world, LivingEntity entity) 
			{
				return entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1.0F : 0.0F;

			}
		});
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{	
		if(soundCounter == 4)
			soundCounter = 0;
		else
			soundCounter++;

		if(attackCooldown != 100)
			attackCooldown++;

		if (entity instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity)entity;

			double f = 500;

			float yaw = player.rotationYaw;
			float pitch = player.rotationPitch;

			double posX = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
			double posY = (double)(-MathHelper.sin((pitch) / 180.0F * (float)Math.PI) * f);
			double posZ = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);

			double x = player.getPosition().getX() - posX; 
			double y = player.getPosition().getY() - posY;
			double z = player.getPosition().getZ() - posZ;


			BlockPos blockPos = new BlockPos(x, y, z);
			
			BlockState block = world.getBlockState(blockPos);

			Block blockType = block.getBlock();


			if(active)
			{
				//Animation
				/*
				if (!world.isRemote)
				{
					float pitch = player.rotationPitch;
					float yaw = player.rotationYaw;

					double x = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI));
					double y = (double)(-MathHelper.sin((pitch) / 180.0F * (float)Math.PI));
					double z = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI));

					EntityBeam entitybeam = new EntityBeam(world, player.posX + x, player.posY + 1.5, player.posZ + z);
					entitybeam.shoot(player, player.rotationPitch, player.rotationYaw, 0F, 3.0F, 0.0F);
					world.addEntity(entitybeam);
				}
				 */


				//Sound Effect
				if(soundCounter == 4)
					world.playSound(player, player.posX, player.posY, player.posZ, SoundsHandler.ITEM_MAGNETGUN_FIRED, SoundCategory.PLAYERS, 1.0F, 1.0F);



				//The gun is only attracted to objects that contain iron
				if(blockType == Blocks.IRON_BLOCK || blockType == Blocks.IRON_BARS || blockType == Blocks.IRON_ORE || blockType == Blocks.IRON_DOOR || blockType == Blocks.DETECTOR_RAIL || blockType == Blocks.RAIL || blockType == Blocks.ACTIVATOR_RAIL || blockType == Blocks.ANVIL || blockType == Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE || blockType == Blocks.PISTON || blockType == Blocks.STICKY_PISTON  || blockType == Blocks.HOPPER)
				{
					double motionX = player.getMotion().x;
					double motionY = player.getMotion().y;
					double motionZ = player.getMotion().z;

					//Amount of movement
					f = 1.2F;

					if(reverse)
					{
						motionX = -(double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
						motionY = -(double)(-MathHelper.sin((pitch) / 180.0F * (float)Math.PI) * f);
						motionZ = -(double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
					}
					else
					{
						motionX = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
						motionY = (double)(-MathHelper.sin((pitch) / 180.0F * (float)Math.PI) * f);
						motionZ = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
					}
					player.setMotion(motionX, motionY, motionZ);

					player.isAirBorne = true;

					if(player.posY == this.prevY1 && player.posY == this.prevY2 && player.posY == this.prevY3 && player.posY == this.prevY4 && !player.onGround)
					{
						f = .1F;

						if(reverse)
						{
							motionX = -(double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
							motionZ = -(double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);						
						}
						else
						{
							motionX = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
							motionZ = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
						}
						player.setMotion(motionX, motionY, motionZ);

					}

					this.prevY4 = this.prevY3;
					this.prevY3 = this.prevY2;
					this.prevY2 = this.prevY1;
					this.prevY1 = player.posY;


					BlockPos pos1 = new BlockPos(player.posX, player.posY, player.posZ + 1);
					BlockPos pos2 = new BlockPos(player.posX, player.posY, player.posZ - 1);
					BlockPos pos3 = new BlockPos(player.posX + 1, player.posY, player.posZ);
					BlockPos pos4 = new BlockPos(player.posX - 1, player.posY, player.posZ);

					boolean xMovement = world.getBlockState(pos3).getBlock() != Blocks.AIR || world.getBlockState(pos4).getBlock() != Blocks.AIR;


					if(xMovement && player.posX == this.prevX1 && player.posX == this.prevX2 && player.posX == this.prevX3 && player.posX == this.prevX4 && !player.onGround)
					{
						f = .2F;

						if(reverse)
						{
							motionZ = -(double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
							motionY = -(double)(-MathHelper.sin((pitch) / 180.0F * (float)Math.PI) * f);
						}
						else
						{
							motionZ = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
							motionY = (double)(-MathHelper.sin((pitch) / 180.0F * (float)Math.PI) * f);
						}
						player.setMotion(motionX, motionY, motionZ);

					}

					this.prevX4 = this.prevX3;
					this.prevX3 = this.prevX2;
					this.prevX2 = this.prevX1;
					this.prevX1 = player.posX;

					boolean zMovement = world.getBlockState(pos1).getBlock() != Blocks.AIR || world.getBlockState(pos2).getBlock() != Blocks.AIR;


					if(zMovement && player.posZ == this.prevZ1 && player.posZ == this.prevZ2 && player.posZ == this.prevZ3 && player.posZ == this.prevZ4 && !player.onGround)
					{
						f = .2F;

						if(reverse)
						{
							motionX = -(double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
							motionY = -(double)(-MathHelper.sin((pitch) / 180.0F * (float)Math.PI) * f);
						}
						else
						{
							motionX = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
							motionY = (double)(-MathHelper.sin((pitch) / 180.0F * (float)Math.PI) * f);
						}
						player.setMotion(motionX, motionY, motionZ);

					}

					this.prevZ4 = this.prevZ3;
					this.prevZ3 = this.prevZ2;
					this.prevZ2 = this.prevZ1;
					this.prevZ1 = player.posZ;


				}

				//If it is an iron trapdoor, remove it
				if(blockType == Blocks.IRON_TRAPDOOR)
				{
					BlockState state = Blocks.AIR.getDefaultState();
					world.setBlockState(blockPos, state);
					world.destroyBlock(blockPos, true);
					player.addItemStackToInventory(new ItemStack(Blocks.IRON_TRAPDOOR));
				}

			}
		}

		super.inventoryTick(stack, world, entity, itemSlot, isSelected);
	}



	private void drawLine(double xPos, double yPos, double zPos) 
	{
		//	GlStateManager.depthFunc(519);
		//	GlStateManager.enableBlend();
		//	GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		//	GlStateManager.disableTexture();
		//	GlStateManager.depthMask(false);
		// some functions to set up the vertices
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
		bufferbuilder.pos(xPos, yPos, zPos).color(1, 1, 1, 0.0F).endVertex();
		bufferbuilder.pos(xPos + 2, yPos, zPos + 2).color(1, 1, 1, 0.0F).endVertex();
		tessellator.draw();
		// a long chain of vertices
		//	GlStateManager.depthMask(true);
		//	GlStateManager.enableTexture();
		//	GlStateManager.disableBlend();
		//GlStateManager.depthFunc(515);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entityLiving, Hand handIn)
	{
		ItemStack itemstack = entityLiving.getHeldItem(handIn);

		entityLiving.setActiveHand(handIn);

		if (!world.isRemote)
		{
			ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, world, entityLiving, handIn, false);
			if (ret != null) return ret;

			active = true;
		}

		//     worldIn.playSound((PlayerEntity)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);


		return (ActionResult<ItemStack>) new ActionResult<ItemStack>(ActionResultType.FAIL, itemstack);

		//return new ActionResult<ItemStack>(ActionResultType.SUCCESS, new ItemStack(ItemInit.MAGNET_GUN));
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		if (entityLiving instanceof PlayerEntity)
		{
			PlayerEntity entityplayer = (PlayerEntity) entityLiving;
			net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, entityplayer, 1, true);
		}
		active = false;
	}


	@Override
	public int getItemEnchantability()
	{
		return 0;
	}

	public void getMouseOver(PlayerEntity player, World world)
	{
		BlockPos pos = player.getPosition();

		float yaw = player.rotationYaw;
		float pitch = player.rotationPitch;

		for(int f = 1; f <= 80; f++)
		{
			double x = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
			double y = (double)(-MathHelper.sin((pitch) / 180.0F * (float)Math.PI) * f);
			double z = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);


			AxisAlignedBB entityPos = new AxisAlignedBB(pos.getX() + x, pos.getY() + y, pos.getZ() + z, pos.getX() + x + 1, pos.getY() + y + 1, pos.getZ() + z + 1);

			List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(player, entityPos);

			for(int j = 0; j < list.size(); ++j)
			{
				Entity entity = list.get(j);
				if(entity instanceof GolemEntity)
				{
					GolemEntity golem = (GolemEntity) entity;
					double golemMotionX = golem.getMotion().x;
					double golemMotionY = golem.getMotion().y;
					double golemMotionZ = golem.getMotion().z;

					if(golem.posX < player.posX)
						golemMotionX = .5;
					else if(golem.posX > player.posX)
						golemMotionX = -.5;
					else
						golemMotionX = 0;

					if(golem.posY < player.posY)
						golemMotionY = .5;
					else if(golem.posY > player.posY)
						golemMotionY = -.5;
					else
						golemMotionY = 0;

					if(golem.posZ < player.posZ)
						golemMotionZ = .5;
					else if(golem.posZ > player.posZ)
						golemMotionZ = -.5;
					else
						golemMotionZ = 0;
					
					golem.setMotion(new Vec3d(golemMotionX, golemMotionY, golemMotionZ));
				}
			}
		}

	}

}