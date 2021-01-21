package containers;

import java.util.ArrayList;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.IContainerListener;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import tileEntities.TileEntityTerniLapilli;

import static handlers.Containers.TERNI_CONTAINER;

public class ContainerTerniLapilli extends Container
{
	private final TileEntityTerniLapilli tileentity;
	private int slot1 = 0;
	private int slot2 = 0;
	private int slot3 = 0;
	private int slot4 = 0;
	private int slot5 = 0;
	private int slot6 = 0;
	private int slot7 = 0;
	private int slot8 = 0;
	private int slot9 = 0;
	public World world;
	
	private PlayerEntity player;
	private IItemHandler playerInventory;
	
	private ArrayList<IContainerListener> listeners;

	public ContainerTerniLapilli(int windowId, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player)
	{
		super(TERNI_CONTAINER, windowId);
		this.tileentity = (TileEntityTerniLapilli) world.getTileEntity(pos);
		this.player = player;
		this.playerInventory = new InvWrapper(inventory);

		tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {

			//This decides which slots are which
			this.addSlot(new SlotTerniLapilli(handler, 0, 61, 17, tileentity));
			this.addSlot(new SlotTerniLapilli(handler, 1, 79, 17, tileentity));
			this.addSlot(new SlotTerniLapilli(handler, 2, 97, 17, tileentity));
			this.addSlot(new SlotTerniLapilli(handler, 3, 61, 35, tileentity));
			this.addSlot(new SlotTerniLapilli(handler, 4, 79, 35, tileentity));
			this.addSlot(new SlotTerniLapilli(handler, 5, 97, 35, tileentity));
			this.addSlot(new SlotTerniLapilli(handler, 6, 61, 53, tileentity));
			this.addSlot(new SlotTerniLapilli(handler, 7, 79, 53, tileentity));
			this.addSlot(new SlotTerniLapilli(handler, 8, 97, 53, tileentity));
		});
		layoutPlayerInventorySlots(10, 70);

		
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

	@Override
	public void detectAndSendChanges() 
	{
		super.detectAndSendChanges();

		for(int i = 0; i < this.listeners.size(); ++i) 
		{
			IContainerListener listener = (IContainerListener)this.listeners.get(i);

			if(this.slot1 != this.tileentity.getField(0))
				listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
			if(this.slot2 != this.tileentity.getField(1))
				listener.sendWindowProperty(this, 1, this.tileentity.getField(1));
			if(this.slot3 != this.tileentity.getField(2))
				listener.sendWindowProperty(this, 2, this.tileentity.getField(2));
			if(this.slot4 != this.tileentity.getField(3))
				listener.sendWindowProperty(this, 3, this.tileentity.getField(3));
			if(this.slot5 != this.tileentity.getField(4))
				listener.sendWindowProperty(this, 4, this.tileentity.getField(4));
			if(this.slot6 != this.tileentity.getField(5))
				listener.sendWindowProperty(this, 5, this.tileentity.getField(5));
			if(this.slot7 != this.tileentity.getField(6))
				listener.sendWindowProperty(this, 6, this.tileentity.getField(6));
			if(this.slot8 != this.tileentity.getField(7))
				listener.sendWindowProperty(this, 7, this.tileentity.getField(7));
			if(this.slot9 != this.tileentity.getField(8))
				listener.sendWindowProperty(this, 8, this.tileentity.getField(8));

		}

		this.slot1 = tileentity.getField(0);
		this.slot2 = tileentity.getField(1);
		this.slot3 = tileentity.getField(2);
		this.slot4 = tileentity.getField(3);
		this.slot5 = tileentity.getField(4);
		this.slot6 = tileentity.getField(5);
		this.slot7 = tileentity.getField(6);
		this.slot8 = tileentity.getField(7);
		this.slot9 = tileentity.getField(8);
	}


	@Override
	public boolean canInteractWith(PlayerEntity playerIn) 
	{
		return this.tileentity.isUsableByPlayer(playerIn);
	}

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

			if (!this.mergeItemStack(itemstack1, 0, 1, false))
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
}