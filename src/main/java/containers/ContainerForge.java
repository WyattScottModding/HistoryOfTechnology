package containers;

import init.BlockInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import tileEntities.TileEntityForge;

import static handlers.Containers.FORGE_CONTAINER;

public class ContainerForge extends Container
{
	private final TileEntityForge tileentity;
	private int cookTime, totalCookTime, burnTime, currentBurnTime;
	private PlayerEntity player;
	private IItemHandler playerInventory;

	public ContainerForge(int windowId, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player)
	{
		super(FORGE_CONTAINER, windowId);
		this.tileentity = (TileEntityForge) world.getTileEntity(pos);
		this.player = player;
		this.playerInventory = new InvWrapper(inventory);

		tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {

			//This decides which slots are which
			//Ex: Slots 0 and 1 are the inputs, slot 2 is the fuel, and slot 3 is the output
			addSlot(new SlotItemHandler(h, 0, 56, 25));
			addSlot(new SlotItemHandler(h, 1, 102, 25));
			addSlot(new SlotItemHandler(h, 2, 17, 38));
			addSlot(new SlotItemHandler(h, 3, 79, 59));
		});
		layoutPlayerInventorySlots(10, 70);

	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return isWithinUsableDistance(IWorldPosCallable.of(tileentity.getWorld(), tileentity.getPos()), player, BlockInit.FORGE);
	}

	private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
		for (int i = 0; i < amount; i++) {
			addSlot(new SlotItemHandler(handler, index, x, y));
			x +=dx;
			index++;
		}
		return index;
	}

	private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
		for (int i = 0; i < verAmount; i++) {
			index = addSlotRange(handler, index, x, y, horAmount, dx);
			y += dy;
		}
		return index;
	}

	private void layoutPlayerInventorySlots(int leftCol, int topRow) {
		//Player Inventory
		addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

		//Hotbar
		topRow += 58;
		addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
	}

	/*
	@Override
	public void detectAndSendChanges() 
	{
		super.detectAndSendChanges();

		for(int i = 0; i < this.listeners.size(); ++i) 
		{
			IContainerListener listener = (IContainerListener)this.listeners.get(i);

			if(this.cookTime != this.tileentity.getField(2)) listener.sendWindowProperty(this, 2, this.tileentity.getField(2));
			if(this.burnTime != this.tileentity.getField(0)) listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
			if(this.currentBurnTime != this.tileentity.getField(1)) listener.sendWindowProperty(this, 1, this.tileentity.getField(1));
			if(this.totalCookTime != this.tileentity.getField(3)) listener.sendWindowProperty(this, 3, this.tileentity.getField(3));
		}

		this.cookTime = this.tileentity.getField(2);
		this.burnTime = this.tileentity.getField(0);
		this.currentBurnTime = this.tileentity.getField(1);
		this.totalCookTime = this.tileentity.getField(3);
	}

	@Override
	public void updateProgressBar(int id, int data) 
	{
		this.tileentity.setField(id, data);
	}
	 */

	/**
	 * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
	 * inventory and the other inventory(s).
	 */
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index)
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < 1)
			{
				if (!this.mergeItemStack(itemstack1, 1, this.inventorySlots.size(), true))
				{
					return ItemStack.EMPTY;
				}
			}
			else if (!this.mergeItemStack(itemstack1, 0, 1, false))
			{
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty())
			{
				slot.putStack(ItemStack.EMPTY);
			}
			else
			{
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

	@OnlyIn(Dist.CLIENT)
	public int getBurnLeftScaled(int pixels)
	{
		int i = this.tileentity.getField(1);
		if(i == 0) 
			i = 9600;
		return this.tileentity.getField(0) * pixels / i;
	}

	@OnlyIn(Dist.CLIENT)
	public int getCookProgressScaled(int pixels)
	{
		int i = this.tileentity.getField(2);
		int j = this.tileentity.getField(3);

		return j != 0 && i != 0 ? i * pixels / j : 0;
	}
	
	@OnlyIn(Dist.CLIENT)
	public boolean isBurning() {
		return this.tileentity.isBurning();
	}
}