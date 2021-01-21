package containers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import tileEntities.TileEntityMissileGuidanceSystem;

import static handlers.Containers.MISSILE_CONTAINER;

public class ContainerMissileGuidance extends Container 
{
	private final TileEntityMissileGuidanceSystem tileentity;
	IItemHandler handler;
	private PlayerEntity player;
	private IItemHandler playerInventory;

	public ContainerMissileGuidance(int windowId, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player)
	{
		super(MISSILE_CONTAINER, windowId);
		this.tileentity = (TileEntityMissileGuidanceSystem) world.getTileEntity(pos);
		this.player = player;
		this.playerInventory = new InvWrapper(inventory);
		
		tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
			
			//This decides which slots are which
			addSlot(new SlotItemHandler(h, 0, 123, 25));
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

		//missile 0 = nothing
		//missile 1 = basic missile
		//missile 2 = fission missile
		//missile 3 = fusion missile

		if(this.tileentity.missileStack != null)
		{
			/*
			int tileentityMissile = 0;

			if(!this.tileentity.missileStack.isEmpty())
			{
				if(this.tileentity.missileStack.isItemEqual(new ItemStack(ItemInit.MISSILE)))
				{
					tileentityMissile = 1;
				}
				if(this.tileentity.missileStack.isItemEqual(new ItemStack(ItemInit.FISSION_MISSILE)))
				{
					tileentityMissile = 2;
				}
				if(this.tileentity.missileStack.isItemEqual(new ItemStack(ItemInit.FUSION_MISSILE)))
				{
					tileentityMissile = 3;
				}
			}
			else
			{
				tileentityMissile = 0;
			}

			
			for(int i = 0; i < this.listeners.size(); ++i) 
			{
				IContainerListener listener = (IContainerListener)this.listeners.get(i);

				if(this.missile != tileentityMissile) 
				{
					listener.sendWindowProperty(this, 0, tileentityMissile);
				}
				if(this.missileSize != tileentity.missileStack.getCount()) 
				{
					listener.sendWindowProperty(this, 0, tileentity.missileStack.getCount());
				}
			}

			this.missile = tileentityMissile;
			this.missileSize = tileentity.missileStack.getCount();
			handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
			
			System.out.println("Count: " + tileentity.missileStack.getCount());
			*/
		}
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

}