package tileEntities;

import containers.ContainerForge;
import handlers.TileEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class TileEntityBookTechnology extends TileEntity implements ITickableTileEntity, INamedContainerProvider
{
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(0, ItemStack.EMPTY);
	private String customName;

	public TileEntityBookTechnology() 
	{
		super(TileEntities.BOOK_TECH);
	}

	public void setCustomName(String customName) 
	{
		this.customName = customName;
	}

	public boolean isEmpty() 
	{
		for(ItemStack stack : this.inventory)
		{
			if(!stack.isEmpty())
				return false;
		}
		return true;
	}


	
	public ItemStack getStackInSlot(int index) 
	{
		return (ItemStack)this.inventory.get(index);
	}

	
	public ItemStack decrStackSize(int index, int count)
	{
		return ItemStackHelper.getAndSplit(this.inventory, index, count);
	}

	
	public ItemStack removeStackFromSlot(int index)
	{
		return ItemStackHelper.getAndRemove(this.inventory, index);
	}

	
	public void setInventorySlotContents(int index, ItemStack stack1) 
	{


	}

	/*
	@Override
	public void readFromNBT(NBTTagCompound compound) 
	{
		super.readFromNBT(compound);
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);


		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		ItemStackHelper.saveAllItems(compound, this.inventory);

		if(this.hasCustomName())
			compound.setString("CustomName", this.customName);
		return compound;
	}
	 */


	
	public int getInventoryStackLimit() 
	{	
		return 1;
	}



	
	public boolean isUsableByPlayer(PlayerEntity player) 
	{
		return this.world.getTileEntity(this.getPos()) == this && player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	
	public void openInventory(PlayerEntity player)
	{
	}

	
	public void closeInventory(PlayerEntity player) 
	{	
	}

	
	public boolean isItemValidForSlot(int index, ItemStack stack) 
	{
		return false;
	}


	
	public int getField(int id)
	{
		return 0;
	}

	
	public void setField(int id, int value)
	{

	}

	
	public int getFieldCount() 
	{
		return 0;
	}

	
	public void clear()
	{
		this.inventory.clear();
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub	
	}
	
	@Override
	public Container createMenu(int menuId, PlayerInventory inv, PlayerEntity player) {
		return new ContainerForge(menuId, world, pos, inv, player);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent(getType().getRegistryName().getPath());
	}
}
