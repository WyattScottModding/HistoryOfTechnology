package items.future;

import java.util.List;
import handlers.KeyBindings;
import init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class GravityGun extends Item
{
	public Entity entityHeld;

	private int delay = 0;


	public GravityGun(String name, Item.Properties builder)
	{
		super(builder);
		this.setRegistryName(name);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{				
		if(entity instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) entity;

			ItemStack itemstack = player.getHeldItem(Hand.MAIN_HAND);

			if(entityHeld != null && !entityHeld.isAlive())
			{
				entityHeld = null;
			}
			if(itemstack.getItem() == ItemInit.GRAVITY_GUN && Minecraft.getInstance().gameSettings.keyBindUseItem.isKeyDown() && delay == 0)
			{
				if(entityHeld == null)
				{
					if(!world.isRemote)
						getMouseOver(player, world);
				}
				else
				{
					if(!world.isRemote && entityHeld != null)
					{
						if(entityHeld instanceof LivingEntity)
						{
							LivingEntity entityLivingBase = (LivingEntity) entityHeld;
							entityLivingBase.addPotionEffect(new EffectInstance(Effects.GLOWING, 10, 1));
						}

						double f = 5.0;

						float yaw = player.rotationYaw;
						float pitch = player.rotationPitch;

						double posX = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
						double posY = (double)(-MathHelper.sin((pitch) / 180.0F * (float)Math.PI) * f);
						double posZ = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);

						double x = player.getPosition().getX() + posX; 
						double y = player.getPosition().getY() + posY;
						double z = player.getPosition().getZ() + posZ;


						BlockPos blockPos = new BlockPos(x, y, z);

						entityHeld.moveToBlockPosAndAngles(blockPos, player.rotationYaw * -1, player.rotationPitch * -1);

						entityHeld.setMotion(0, 0 , 0);

						entityHeld.fallDistance = 0;
					}
				}
				if(entityHeld != null && KeyBindings.ITEM1.isKeyDown())
				{
					if(!world.isRemote)
					{
						double f = 5.0;

						float yaw = player.rotationYaw;
						float pitch = player.rotationPitch;

						double velocityX = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
						double velocityY = (double)(-MathHelper.sin((pitch) / 180.0F * (float)Math.PI) * f);
						double velocityZ = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);

						entityHeld.setMotion(velocityX, velocityY , velocityZ);

						delay = 20;
						entityHeld = null;
					}
				}
			}
			else
				entityHeld = null;

			if(delay > 0)
				delay--;
		}
		super.inventoryTick(stack, world, entity, itemSlot, isSelected);
	}

	public boolean getMouseOver(PlayerEntity player, World world)
	{
		BlockPos pos = player.getPosition();

		float yaw = player.rotationYaw;
		float pitch = player.rotationPitch;

		List<Entity> list = null;

		for(int f = 1; f <= 40; f++)
		{
			double x = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
			double y = (double)(-MathHelper.sin((pitch) / 180.0F * (float)Math.PI) * f);
			double z = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);


			AxisAlignedBB entityPos = new AxisAlignedBB(pos.getX() + x, pos.getY() + y, pos.getZ() + z, pos.getX() + x + 1, pos.getY() + y + 1, pos.getZ() + z + 1);

			list = world.getEntitiesWithinAABBExcludingEntity(player, entityPos);


			for(int j = 0; j < list.size(); ++j)
			{
				Entity entity = list.get(j);

				this.entityHeld = entity;

				double f2 = 5.0;

				double posX = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f2);
				double posY = (double)(-MathHelper.sin((pitch) / 180.0F * (float)Math.PI) * f2);
				double posZ = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f2);

				double x2 = player.getPosition().getX() - posX; 
				double y2 = player.getPosition().getY() - posY;
				double z2 = player.getPosition().getZ() - posZ;


				BlockPos blockPos = new BlockPos(x2, y2, z2);
				
				entity.moveToBlockPosAndAngles(blockPos, player.rotationYaw * -1, player.rotationPitch * -1);

			}

		}
		if(list != null)
			return list.size() != 0;
		else
			return false;
	}

}
