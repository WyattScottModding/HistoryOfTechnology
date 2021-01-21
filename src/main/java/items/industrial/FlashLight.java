package items.industrial;

import java.util.Random;

import handlers.KeyBindings;
import init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import updates.PlayerUpdaters;

public class FlashLight extends SwordItem
{
	public boolean clicked = false;
	public int counter = 0;
	public static World world = null;
	public ItemStack stack = null;

	public FlashLight(String name, Item.Properties builder)
	{
		super(ItemTier.IRON, 1, 1, builder);
		this.setRegistryName(name);

	
		this.addPropertyOverride(new ResourceLocation("on"), new IItemPropertyGetter()
        {
			@Override
			public float call(ItemStack p_call_1_, World p_call_2_, LivingEntity p_call_3_)
			{
                return !clicked ? 0.0F : 1.0F;
			}
        });
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
	{
		world = worldIn;

		if (!worldIn.isRemote)
		{
			if(clicked && counter == 0)
			{
				clicked = false;
				counter = 4;
			}
			else if(!clicked && counter == 0 && stack != null && stack.getDamage() <= 99)
			{
				clicked = true;
				counter = 4;
			}
		}
		 return new ActionResult<ItemStack>(ActionResultType.FAIL, playerIn.getHeldItem(handIn));
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{		
		if(counter > 0)
			counter--;
		this.stack = stack;
		
		Entity entityLiving = entity.getEntity();
		
		if(entityLiving instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) entityLiving;

			if(stack.getDamage() >= 99)
				PlayerUpdaters.clicked = false;
			else
				PlayerUpdaters.clicked = this.clicked;

			if(clicked && world.getGameTime() % 120 == 0 && player instanceof ServerPlayerEntity)
			{
				stack.attemptDamageItem(1, new Random(0), (ServerPlayerEntity) player);
			}
			
			
			if(stack.getDamage() >= 50 &&  KeyBindings.ITEM1.isKeyDown())
			{
				ItemStack itemstack = findAmmo(player);

				if(ItemStack.areItemsEqual(itemstack, new ItemStack(ItemInit.BATTERY)) && (player.getHeldItemMainhand().getItem() instanceof FlashLight || player.getHeldItemOffhand().getItem() instanceof FlashLight))
				{
					stack.setDamage(stack.getDamage() - 50);
					itemstack.shrink(1);
				}
			}
		} 
		super.inventoryTick(stack, world, entity, itemSlot, isSelected);
	}


	public World getWorld()
	{
		return world;
	}

	private ItemStack findAmmo(PlayerEntity player)
	{
		player.inventory.getCurrentItem();
		if (ItemStack.areItemsEqualIgnoreDurability(player.getHeldItem(Hand.OFF_HAND), new ItemStack(ItemInit.BATTERY)))
		{
			return player.getHeldItem(Hand.OFF_HAND);
		}
		player.inventory.getCurrentItem();
		if (ItemStack.areItemsEqualIgnoreDurability(player.getHeldItem(Hand.MAIN_HAND), new ItemStack(ItemInit.BATTERY)))
		{
			return player.getHeldItem(Hand.MAIN_HAND);
		}
		else
		{
			for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
			{
				ItemStack itemstack = player.inventory.getStackInSlot(i);

				player.inventory.getCurrentItem();
				if (ItemStack.areItemsEqualIgnoreDurability(itemstack, new ItemStack(ItemInit.BATTERY)))
				{
					return itemstack;
				}
			}

			return ItemStack.EMPTY;
		}
	}


}