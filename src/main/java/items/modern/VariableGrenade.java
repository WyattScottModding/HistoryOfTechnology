package items.modern;

import entity.EntityContactGrenade;
import entity.EntityFragGrenade;
import entity.EntityThreatGrenade;
import handlers.KeyBindings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class VariableGrenade extends Item
{
	public int grenade = 1;

	public VariableGrenade(String name, Item.Properties builder)
	{
		super(builder);
		this.setRegistryName(name);

		
		this.addPropertyOverride(new ResourceLocation("grenade"), new IItemPropertyGetter()
		{
			@Override
			public float call(ItemStack stack, World world, LivingEntity entity) 
			{
				if(entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack)
				{
					System.out.println("Grenade: " + grenade);
					if(grenade == 1)
						return 0.1F;
					else if(grenade == 2)
						return 0.2F;
					else
						return 0.3F;
				}
				else
					return 0.0F;
			}
		});
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
	{
		ItemStack itemstack = playerIn.getHeldItem(handIn);

		if (!worldIn.isRemote)
		{
			worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

			switch(grenade)
			{
			case 1: //Frag Grenade
			{
				EntityFragGrenade entitygrenade = new EntityFragGrenade(worldIn);
				entitygrenade.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.0F, 0.5F);
				worldIn.addEntity(entitygrenade);
			}
			case 2: //Contact Grenade
			{
				EntityContactGrenade entitygrenade = new EntityContactGrenade(worldIn, playerIn, 4);
				entitygrenade.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.0F, 0.5F);
				worldIn.addEntity(entitygrenade);
			}
			case 3: //Threat Grenade
			{
				EntityThreatGrenade entitygrenade = new EntityThreatGrenade(worldIn);
				entitygrenade.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.0F, 0.5F);
				worldIn.addEntity(entitygrenade);
			}
			}
			
			if (!playerIn.isCreative())
			{
				itemstack.shrink(1);
			}
		}

		//playerIn.addStat(StatList.getObjectUseStats(this));
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemstack);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{		
		Entity entityLiving = entity.getEntity();
		
		if(entityLiving instanceof PlayerEntity)
		{
			/*
			if(KeyBindings.ITEM1.isKeyDown() && world.isRemote)
			{
				if(grenade < 3)
					grenade++;
				else
					grenade = 1;				
			}
			*/
		}	
		super.inventoryTick(stack, world, entity, itemSlot, isSelected);
	}
}