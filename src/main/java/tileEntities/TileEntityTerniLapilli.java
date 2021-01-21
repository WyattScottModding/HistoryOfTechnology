package tileEntities;

import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;

import containers.ContainerForge;
import handlers.TileEntities;
import init.ItemInit;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityTerniLapilli extends TileEntity implements ITickableTileEntity, INamedContainerProvider
{
	public TileEntityTerniLapilli() 
	{
		super(TileEntities.TERNI);
	}

	private ItemStackHandler handler = new ItemStackHandler(9);
	private String customName;
	private int slot1 = 0;
	private int slot2 = 0;
	private int slot3 = 0;
	private int slot4 = 0;
	private int slot5 = 0;
	private int slot6 = 0;
	private int slot7 = 0;
	private int slot8 = 0;
	private int slot9 = 0;

	private boolean sentMessage = false;
	public boolean whiteTurn = false;

	 @Nonnull
	    @Override
	    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability) {
	        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
	            return LazyOptional.of(() -> (T) inputHandler);
	        }
	        return super.getCapability(capability);
	    }

	    @Override
	    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, Direction facing) {
	        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
	            return LazyOptional.of(() -> (T) inputHandler);
	        }
	        return super.getCapability(capability, facing);
	    }
	 

	
	public ItemStackHandler getHandler() 
	{
		return handler;
	}

	public boolean hasCustomName() 
	{
		return this.customName != null && !this.customName.isEmpty();
	}

	public void setCustomName(String customName) 
	{
		this.customName = customName;
	}

/*
	@Override
	public void readFromNBT(CompoundNBT compound)
	{
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.readUpdateTag(compound);

		if(compound.hasKey("CustomName", 8)) 
			this.setCustomName(compound.getString("CustomName"));
	}

	@Override
	public CompoundNBT writeToNBT(CompoundNBT compound) 
	{
		super.writeToNBT(compound);
		compound.setTag("Inventory", this.handler.serializeNBT());
		this.writeUpdateTag(compound);

		if(this.hasCustomName()) 
			compound.setString("CustomName", this.customName);
		return compound;
	}
	*/

	@Override
	public void tick() 
	{
		if(!world.isRemote)
		{
			updateSlots();

			ITextComponent msg = null;

			if(hasPlayerWon(1))
			{
				msg = new StringTextComponent ("The white player has won!");
			}
			else if(hasPlayerWon(2))
			{
				msg = new StringTextComponent ("The black player has won!");
			}
			else
			{
				sentMessage = false;
			}

			if(msg != null && !sentMessage)
			{
				AxisAlignedBB bb = new AxisAlignedBB(this.pos.getX() - 6, this.pos.getY() - 6, this.pos.getZ() - 6, this.pos.getX() + 6, this.pos.getY() + 6, this.pos.getZ() + 6);
				List<PlayerEntity> entities = world.getEntitiesWithinAABB(PlayerEntity.class, bb);

				for(PlayerEntity element : entities)
				{
					element.sendMessage(msg);
					element.closeScreen();
				}

				for (int i = 0; i < handler.getSlots(); ++i)
				{
					ItemStack itemstack = handler.getStackInSlot(i);

					if (!itemstack.isEmpty())
					{
						spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), itemstack);
						handler.setStackInSlot(0, ItemStack.EMPTY);
						handler.serializeNBT();
					}
				}

				//EntityFireworkRocket rocket = new EntityFireworkRocket(world, pos.getX(), pos.getY(), pos.getZ(), null);

				sentMessage = true;
				whiteTurn = false;
			}
			//markDirty();
			//BlockState state = world.getBlockState(pos);
			//world.notifyBlockUpdate(pos, state, state, 3);

		}
	}

	public void updateSlots()
	{
		for(int i = 0; i < 9; i++)
		{
			ItemStack stack = handler.getStackInSlot(i);

			if(ItemStack.areItemsEqualIgnoreDurability(stack, new ItemStack(ItemInit.ROCK_BLACK)))
			{
				setField(i, 2);
			}
			else if(ItemStack.areItemsEqualIgnoreDurability(stack, new ItemStack(ItemInit.ROCK_WHITE)))
			{
				setField(i, 1);
			}
			else
			{
				setField(i, 0);
			}
		}
	}

	public boolean hasPlayerWon(int color)
	{
		//Row 1
		if(slot1 == color && slot2 == color && slot3 == color)
		{
			return true;
		}
		//Row 2
		else if(slot4 == color && slot5 == color && slot6 == color)
		{
			return true;
		}
		//Row 3
		else if(slot7 == color && slot8 == color && slot9 == color)
		{
			return true;
		}
		//Column 1
		else if(slot1 == color && slot4 == color && slot7 == color)
		{
			return true;
		}
		//Column 2
		else if(slot2 == color && slot5 == color && slot8 == color)
		{
			return true;
		}
		//Column 3
		else if(slot3 == color && slot6 == color && slot9 == color)
		{
			return true;
		}
		//Diagonal 1
		else if(slot1 == color && slot5 == color && slot9 == color)
		{
			return true;
		}
		//Diagonal 2
		else if(slot3 == color && slot5 == color && slot7 == color)
		{
			return true;
		}

		return false;
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
			return this.slot1;
		case 1:
			return this.slot2;
		case 2:
			return this.slot3;
		case 3:
			return this.slot4;
		case 4:
			return this.slot5;
		case 5:
			return this.slot6;
		case 6:
			return this.slot7;
		case 7:
			return this.slot8;
		case 8:
			return this.slot9;
		default:
			return 0;
		}
	}

	public void setField(int id, int value) 
	{
		switch(id) 
		{
		case 0:
			this.slot1 = value;
			break;
		case 1:
			this.slot2 = value;
			break;
		case 2:
			this.slot3 = value;
			break;
		case 3:
			this.slot4 = value;
			break;
		case 4:
			this.slot5 = value;
			break;
		case 5:
			this.slot6 = value;
			break;
		case 6:
			this.slot7 = value;
			break;
		case 7:
			this.slot8 = value;
			break;
		case 8:
			this.slot9 = value;
			break;
		}
	}

	public static void spawnItemStack(World worldIn, double x, double y, double z, ItemStack stack)
	{
		Random RANDOM = new Random();

		float f = RANDOM.nextFloat() * 0.8F + 0.1F;
		float f1 = RANDOM.nextFloat() * 0.8F + 0.1F;
		float f2 = RANDOM.nextFloat() * 0.8F + 0.1F;

		while (!stack.isEmpty())
		{
			ItemEntity entityitem = new ItemEntity(worldIn, x + (double)f, y + (double)f1, z + (double)f2, stack.split(RANDOM.nextInt(21) + 10));
			entityitem.setMotion(RANDOM.nextGaussian() * 0.05000000074505806D, RANDOM.nextGaussian() * 0.05000000074505806D + 0.20000000298023224D, RANDOM.nextGaussian() * 0.05000000074505806D);
			worldIn.addEntity(entityitem);
		}
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) 
	{
		CompoundNBT tag = pkt.getNbtCompound();
		readUpdateTag(tag);
	}

	@Override
	public SUpdateTileEntityPacket getUpdatePacket() 
	{
		CompoundNBT tag = new CompoundNBT();
		this.writeUpdateTag(tag);

		return new SUpdateTileEntityPacket(pos, 0, tag);
	}

	@Override
	public CompoundNBT getUpdateTag()
	{
		CompoundNBT tag = super.getUpdateTag();
		writeUpdateTag(tag);

		return tag;
	}

	public void writeUpdateTag(CompoundNBT tag)
	{
		tag.putInt("slot1", slot1);
		tag.putInt("slot2", slot2);
		tag.putInt("slot3", slot3);
		tag.putInt("slot4", slot4);
		tag.putInt("slot5", slot5);
		tag.putInt("slot6", slot6);
		tag.putInt("slot7", slot7);
		tag.putInt("slot8", slot8);
		tag.putInt("slot9", slot9);

	}

	public void readUpdateTag(CompoundNBT tag)
	{
		this.slot1 = tag.getInt("slot1");
		this.slot2 = tag.getInt("slot2");
		this.slot3 = tag.getInt("slot3");
		this.slot4 = tag.getInt("slot4");
		this.slot5 = tag.getInt("slot5");
		this.slot6 = tag.getInt("slot6");
		this.slot7 = tag.getInt("slot7");
		this.slot8 = tag.getInt("slot8");
		this.slot9 = tag.getInt("slot9");
	}
	
	public String getGuiID() {
		return "history:ternilapilli";
	}
	
	 // This item handler will hold our input slot
    private ItemStackHandler inputHandler = new ItemStackHandler(9) {

        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            return (stack.getItem() == ItemInit.ROCK_BLACK) || (stack.getItem() == ItemInit.ROCK_WHITE);
        }


        @Override
        protected void onContentsChanged(int slot) {
            // We need to tell the tile entity that something has changed so
            // that the chest contents is persisted
            TileEntityTerniLapilli.this.markDirty();
        }
    };

	@Override
	public Container createMenu(int menuId, PlayerInventory inv, PlayerEntity player) {
		return new ContainerForge(menuId, world, pos, inv, player);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent(getType().getRegistryName().getPath());
	}
}
