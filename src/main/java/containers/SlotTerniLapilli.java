package containers;

import javax.annotation.Nonnull;

import init.ItemInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import tileEntities.TileEntityTerniLapilli;

public class SlotTerniLapilli extends Slot
{
	private static IInventory emptyInventory = new Inventory(0);
	private final int index;
	public TileEntityTerniLapilli tileentity;
	private final IItemHandler itemHandler;


	public SlotTerniLapilli(IItemHandler handler, int index, int xPosition, int yPosition, TileEntityTerniLapilli tileentity)
	{
		super(emptyInventory, index, xPosition, yPosition);
		this.index = index;
		this.tileentity = tileentity;
		this.itemHandler = handler;

	}

	/**
	 * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
	 */
	@Override
	public boolean isItemValid(@Nonnull ItemStack stack)
	{
		int blackRocks = getBlackRocks();
		int whiteRocks = getWhiteRocks();
		boolean canPlace = false;

		if(blackRocks > whiteRocks)
			tileentity.whiteTurn = true;
		else if(blackRocks < whiteRocks)
			tileentity.whiteTurn = false;

		if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.ROCK_BLACK)) && !tileentity.whiteTurn)
		{
			if(blackRocks < 3)
			{
				canPlace = true;
				tileentity.whiteTurn = true;
			}
		}
		if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.ROCK_WHITE)) && tileentity.whiteTurn)
		{
			if(whiteRocks < 3)
			{
				canPlace = true;
				tileentity.whiteTurn = false;
			}
		}


		if(canPlace)
		{
			if (stack.isEmpty() || !itemHandler.isItemValid(index, stack))
				return false;

			ItemStack remainder;
			if (itemHandler instanceof IItemHandlerModifiable)
			{
				IItemHandlerModifiable handlerModifiable = (IItemHandlerModifiable) itemHandler;
				ItemStack currentStack = handlerModifiable.getStackInSlot(index);

				handlerModifiable.setStackInSlot(index, ItemStack.EMPTY);

				remainder = handlerModifiable.insertItem(index, stack, true);

				handlerModifiable.setStackInSlot(index, currentStack);
			}
			else
			{
				remainder = itemHandler.insertItem(index, stack, true);
			}

			tileentity.markDirty();
			return remainder.getCount() < stack.getCount();
		}

		tileentity.markDirty();
		return false;
	}

	/**
	 * Helper fnct to get the stack in the slot.
	 */
	@Override
	@Nonnull
	public ItemStack getStack()
	{
		return this.getInventory().getStackInSlot(index);
	}

	// Override if your IItemHandler does not implement IItemHandlerModifiable
	/**
	 * Helper method to put a stack in the slot.
	 */
	@Override
	public void putStack(@Nonnull ItemStack stack)
	{
		((IItemHandlerModifiable) itemHandler).setStackInSlot(index, stack);
		this.onSlotChanged();
		
		//((IItemHandlerModifiable) this.getInventory()).setStackInSlot(index, stack);
	}

	/**
	 * if par2 has more items than par1, onCrafting(item,countIncrease) is called
	 */
	@Override
	public void onSlotChange(@Nonnull ItemStack p_75220_1_, @Nonnull ItemStack p_75220_2_)
	{

	}

	/**
	 * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1 in the case
	 * of armor slots)
	 */
	@Override
	public int getSlotStackLimit()
	{
		return 1;
	}

	@Override
	public int getItemStackLimit(@Nonnull ItemStack stack)
	{
		/*
		ItemStack maxAdd = stack.copy();
		int maxInput = stack.getMaxStackSize();
		maxAdd.setCount(maxInput);

		IItemHandler handler = this.getItemHandler();
		ItemStack currentStack = handler.getStackInSlot(index);
		if (handler instanceof IItemHandlerModifiable) {
			IItemHandlerModifiable handlerModifiable = (IItemHandlerModifiable) handler;

			handlerModifiable.setStackInSlot(index, ItemStack.EMPTY);

			ItemStack remainder = handlerModifiable.insertItem(index, maxAdd, true);

			handlerModifiable.setStackInSlot(index, currentStack);

			return maxInput - remainder.getCount();
		}
		else
		{
			ItemStack remainder = handler.insertItem(index, maxAdd, true);

			int current = currentStack.getCount();
			int added = maxInput - remainder.getCount();
			return current + added;
		}
		 */
		return 1;
	}

	/**
	 * Return whether this slot's stack can be taken from this slot.
	 */
	@Override
	public boolean canTakeStack(PlayerEntity playerIn)
	{
		if(!this.getInventory().getStackInSlot(index).isEmpty())
		{
			if(tileentity.whiteTurn)
			{
				if(this.getStack().isItemEqualIgnoreDurability(new ItemStack(ItemInit.ROCK_WHITE)))
					return true;
			}
			else
			{
				if(this.getStack().isItemEqualIgnoreDurability(new ItemStack(ItemInit.ROCK_BLACK)))
					return true;
			}
		}

		return false;
	}

	/**
	 * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
	 * stack.
	 */
	@Override
	@Nonnull
	public ItemStack decrStackSize(int amount)
	{
		return this.getInventory().decrStackSize(index, amount);
	}

	public IInventory getInventory()
	{
		return inventory;
	}

	@Override
	public boolean isSameInventory(Slot other)
	{
		return other instanceof SlotItemHandler && ((SlotItemHandler) other) == this.itemHandler;
	}

	public int getBlackRocks()
	{
		int num = 0;

		for(int i = 0; i < 9; i++)
		{
			if(itemHandler.getStackInSlot(i).isItemEqualIgnoreDurability(new ItemStack(ItemInit.ROCK_BLACK)))
				num++;
		}

		return num;
	}

	public int getWhiteRocks()
	{
		int num = 0;

		for(int i = 0; i < 9; i++)
		{
			if(itemHandler.getStackInSlot(i).isItemEqualIgnoreDurability(new ItemStack(ItemInit.ROCK_WHITE)))
				num++;
		}

		return num;
	}
}
