package tileEntities;

import blocks.ancient.Forge;
import containers.ContainerForge;
import handlers.TileEntities;
import init.ForgeRecipes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityForge extends TileEntity implements ITickableTileEntity, INamedContainerProvider
{
	private ItemStackHandler handler = new ItemStackHandler(4);
	private ItemStack smelting = ItemStack.EMPTY;

	private int burnTime;
	private int currentBurnTime;
	private int cookTime;
	private int totalCookTime = 300;

	public TileEntityForge() 
	{
		super(TileEntities.FORGE);
	}
	
	public ItemStackHandler getHandler() 
	{
		return handler;
	}

	
	
    public boolean canInteractWith(PlayerEntity entityPlayer) {
    	//BlockPos pos = this.getPos();
        return !isRemoved(); //&& entityPlayer.getDistanceSq(pos.getX(), pos.getY(), pos.getZ()) <= 64D;
    }

    /**
     * IGuiTile
     */
    /*
    @Override
    public GuiContainer createGui(PlayerEntity entityPlayer) {
        return new GuiForge(this, new ContainerForge(entityPlayer.inventory, this));
    }
   */
    

    /**
     * IInteractionObject
     */
    /*
    @Override
    public Container createContainer(PlayerInventory playerInventory, PlayerEntity playerIn) {
        return new ContainerForge(playerInventory, this);
    }
    */

	
/*
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.burnTime = compound.getInteger("BurnTime");
		this.cookTime = compound.getInteger("CookTime");
		this.totalCookTime = compound.getInteger("CookTimeTotal");
		this.currentBurnTime = getItemBurnTime((ItemStack)this.handler.getStackInSlot(2));

		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setInteger("BurnTime", (short)this.burnTime);
		compound.setInteger("CookTime", (short)this.cookTime);
		compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
		compound.setTag("Inventory", this.handler.serializeNBT());

		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
*/

	public boolean isBurning() 
	{
		return this.burnTime > 0;
	}

	public static boolean isBurning(TileEntityForge te) 
	{
		return te.getField(0) > 0;
	}

	@Override
	public void tick() 
	{	
		//Forge.setState(cookTime > 0, world, pos);

		if(this.isBurning())
		{
			--this.burnTime;
			Forge.setState(cookTime > 0, world, pos);
		}


		ItemStack[] inputs = new ItemStack[] {handler.getStackInSlot(0), handler.getStackInSlot(1)};
		ItemStack fuel = this.handler.getStackInSlot(2);

		if(this.isBurning() || !fuel.isEmpty() && !this.handler.getStackInSlot(0).isEmpty() || this.handler.getStackInSlot(1).isEmpty())
		{
			if(!this.isBurning() && this.canSmelt())
			{
				this.burnTime = getItemBurnTime(fuel);
				this.currentBurnTime = burnTime;

				if(this.isBurning() && !fuel.isEmpty())
				{
					Item item = fuel.getItem();
					fuel.shrink(1);

					if(fuel.isEmpty())
					{
						ItemStack item1 = item.getContainerItem(fuel);
						this.handler.setStackInSlot(2, item1);
					}
				}
			}
		}

		if(this.isBurning() && this.canSmelt() && cookTime > 0)
		{
			cookTime++;
			if(cookTime == totalCookTime)
			{
				if(handler.getStackInSlot(3).getCount() > 0)
				{
					handler.getStackInSlot(3).grow(1);
				}
				else
				{
					handler.insertItem(3, smelting, false);
				}

				smelting = ItemStack.EMPTY;
				cookTime = 0;
				return;
			}
		}
		else
		{
			if(this.canSmelt() && this.isBurning())
			{
				ItemStack output = ForgeRecipes.getInstance().getForgeResult(inputs[0], inputs[1]);
				if(!output.isEmpty())
				{
					smelting = output;
					cookTime++;
					inputs[0].shrink(1);
					inputs[1].shrink(1);
					handler.setStackInSlot(0, inputs[0]);
					handler.setStackInSlot(1, inputs[1]);
				}
			}
		}
	}

	private boolean canSmelt() 
	{
		if(((ItemStack)this.handler.getStackInSlot(0)).isEmpty() || ((ItemStack)this.handler.getStackInSlot(1)).isEmpty()) return false;
		else 
		{
			ItemStack result = ForgeRecipes.getInstance().getForgeResult((ItemStack)this.handler.getStackInSlot(0), (ItemStack)this.handler.getStackInSlot(1));	
			if(result.isEmpty()) return false;
			else
			{
				ItemStack output = (ItemStack)this.handler.getStackInSlot(3);
				if(output.isEmpty()) return true;
				if(!output.isItemEqual(result)) return false;
				int res = output.getCount() + result.getCount();
				return res <= 64 && res <= output.getMaxStackSize();
			}
		}
	}

	public static int getItemBurnTime(ItemStack fuel)
	{
		if(fuel.isEmpty())
			return 0;
		else
		{
			Item item = fuel.getItem();
			
			//if(item instanceof BlockItem && Block.getBlockFromItem(item) != Blocks.AIR)
			//{
			//	Block block = Block.getBlockFromItem(item);	
			//}
			
			if(item ==Items.LAVA_BUCKET)
				return 9600;
		}

		return 0;

		//return GameRegistry.getFuelValue(fuel);
	}

	public static boolean isItemFuel(ItemStack fuel)
	{
		return getItemBurnTime(fuel) > 0;
	}

	public boolean isUsableByPlayer(PlayerEntity player) 
	{
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	public int getField(int id) 
	{
		switch(id) 
		{
		case 0:
			return this.burnTime;
		case 1:
			return this.currentBurnTime;
		case 2:
			return this.cookTime;
		case 3:
			return this.totalCookTime;
		default:
			return 0;
		}
	}

	public void setField(int id, int value) 
	{
		switch(id) 
		{
		case 0:
			this.burnTime = value;
			break;
		case 1:
			this.currentBurnTime = value;
			break;
		case 2:
			this.cookTime = value;
			break;
		case 3:
			this.totalCookTime = value;
		}
	}
/*
	@Nullable @Override
	public ITextComponent getCustomName() {
		return null;
	}

	@Override
	public String getGuiID() {
		return "history:forge";
	}
*/
	@Override
	public Container createMenu(int menuId, PlayerInventory inv, PlayerEntity player) {
		return new ContainerForge(menuId, world, pos, inv, player);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent(getType().getRegistryName().getPath());
	}

}