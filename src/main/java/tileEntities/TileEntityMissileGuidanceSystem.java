package tileEntities;

import javax.annotation.Nullable;

import containers.ContainerForge;
import entity.EntityFissionMissile;
import entity.EntityFusionMissile;
import entity.EntityMissile;
import handlers.TileEntities;
import init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityMissileGuidanceSystem extends TileEntity implements ITickableTileEntity, INamedContainerProvider
{
	private ItemStackHandler handler = new ItemStackHandler(1);
	private String customName;
	private boolean sendToClient;

	public boolean missileFired;
	public ItemStack missileStack;
	public Entity missile;
	public PlayerEntity player;
	
	public int posX, posZ;

	public MinecraftServer server;

	public CompoundNBT nbt;
	private boolean hitCeiling = false;


	public ItemStackHandler getHandler() 
	{
		return handler;
	}

	public CompoundNBT getNbt() 
	{
		return nbt;
	}

	
	public TileEntityMissileGuidanceSystem() 
	{
		super(TileEntities.MISSILE_GUIDANCE);
		
		posX = 0;
		posZ = 0;
		missileFired = false;

		sendToClient = true;
	}


	@Override
	public void tick() 
	{
		//server = FMLCommonHandler.instance().getMinecraftServerInstance();
		//world = server.worlds[0];

		if(missileFired && missile != null && !world.isRemote)
		{	
			//Once at this height it will move horizontally towards the target location
			if((int) missile.posY >= 300)
			{
				double xDiff = posX - missile.posX;
				double zDiff = posZ - missile.posZ;

				double magnitude = Math.sqrt(xDiff*xDiff + zDiff*zDiff);

				missile.setMotion((xDiff / magnitude) * 3, 0, (zDiff / magnitude) * 3);

				hitCeiling = true;
			}
			//Will keep moving up until it hits 300
			if(!hitCeiling)
			{
				missile.setMotion(0, 3, 0);
			}
			else if(posX >= (missile.posX - 4) && posX <= (missile.posX + 4) &&  posZ >= (missile.posZ - 4) && posZ <= (missile.posZ + 4))
			{
				missile.setMotion(0, -4, 0);
			}

			if(!missile.isAlive())
				missileFired = false;

		}

		if(missile != null && !missile.isAlive())
		{
			missile = null;
			missileFired = false;
		}
	}

	public void fireMissile(int posX, int posZ, PlayerEntity player)
	{
		/*
		if(localWorld == null || localWorld.isRemote)
		{
			server = FMLCommonHandler.instance().getMinecraftServerInstance();
			localWorld = server.worlds[0];
		}
		*/
		
		System.out.println("World is remote " + world.isRemote);
		
		this.player = player;
		this.posX = posX;
		this.posZ = posZ;

		BlockPos pos = this.getPos();

		ItemStack stack = handler.getStackInSlot(0);
		missile = null;

		if(stack.isItemEqual(new ItemStack(ItemInit.FUSION_MISSILE)))
		{
			missile = new EntityFusionMissile(world, pos.getX(), pos.getY() + 2, pos.getZ(), 1000F);
		}
		else if(stack.isItemEqual(new ItemStack(ItemInit.FISSION_MISSILE)))
		{
			missile = new EntityFissionMissile(world, pos.getX(), pos.getY() + 2, pos.getZ(), 200F);
		}
		else if(stack.isItemEqual(new ItemStack(ItemInit.MISSILE)))
		{
			missile = new EntityMissile(world, pos.getX(), pos.getY() + 2, pos.getZ());
		}
		
		if(missile != null)
		{
			if(world.canBlockSeeSky(pos))
			{
				/*
				if(missile instanceof EntityFusionMissile)
					missileStack = new ItemStack(ItemInit.FUSION_MISSILE);
				if(missile instanceof EntityFissionMissile)
					missileStack = new ItemStack(ItemInit.FISSION_MISSILE);
				if(missile instanceof EntityMissile)
					missileStack = new ItemStack(ItemInit.MISSILE);
				 */

				//Easter Egg
				
				//Figure out how to make custom discs
				/*
				if(missile instanceof EntityFusionMissile || missile instanceof EntityFissionMissile)
				{
					if(this.posX >= (this.pos.getX() - 5) && this.posX <= (this.pos.getX() + 5) && this.posZ >= (this.pos.getZ() - 5) && this.posZ <= (this.pos.getZ() + 5))
					{
						
						ITextComponent msg1 = new StringTextComponent ("Woah there, you almost blew yourself up.");
						ITextComponent msg2 = new StringTextComponent ("Here, take the sounds of Sweden.");

						AxisAlignedBB bb = new AxisAlignedBB(this.pos.getX() - 6, this.pos.getY() - 6, this.pos.getZ() - 6, this.pos.getX() + 6, this.pos.getY() + 6, this.pos.getZ() + 6);
						List<PlayerEntity> entities = world.getEntitiesWithinAABB(PlayerEntity.class, bb);

						for(PlayerEntity element : entities)
						{
							
							if(!element.inventory.hasItemStack(new ItemStack(ItemInit.DISC_SWEDEN)))
							{
								element.sendMessage(msg1);
								element.sendMessage(msg2);

								//Give the player the sweden disc
								element.inventory.addItemStackToInventory(new ItemStack(ItemInit.DISC_SWEDEN));

								element.closeScreen();
							}
							
							
						}
						return;
					}
				}
				*/
				
				stack.shrink(1);
				this.handler.setStackInSlot(0, stack);				
				
				missile.setMotion(missile.getMotion().x, 3, missile.getMotion().z);
				missileFired = true;
				hitCeiling = false;
				world.addEntity(missile);

				//ParticleTypes that come up from the block
				double width = .5;
				this.world.addParticle(ParticleTypes.EXPLOSION, this.pos.getX() + width, this.pos.getY() + 1, this.pos.getZ() + width, 0.0D, 0.0D, 0.0D);
			}
		}
	}

/*
	@Override
	public void readFromNBT(CompoundNBT nbt) 
	{
		this.posX = nbt.getInteger("posX");
		this.posZ = nbt.getInteger("posZ");

		this.missileFired = nbt.getBoolean("missileFired");

		this.handler.deserializeNBT(nbt.getCompoundTag("Inventory"));

		super.readFromNBT(nbt);
		this.nbt = nbt;

	}

	@Override
	public CompoundNBT writeToNBT(CompoundNBT nbt) 
	{
		nbt.setInteger("posX", this.posX);
		nbt.setInteger("posZ", this.posZ);

		nbt.setBoolean("missileFired", this.missileFired);

		nbt.setTag("Inventory", this.handler.serializeNBT());

		super.writeToNBT(nbt);
		return nbt;
	}
*/

	@Nullable
	public SUpdateTileEntityPacket getUpdatePacket()
	{
		if (this.isSendToClient())
		{
			this.setSendToClient(false);
			CompoundNBT nbttagcompound = this.serializeNBT();
			tick();
			return new SUpdateTileEntityPacket(this.pos, 2, nbttagcompound);
		}
		else
		{
			return null;
		}
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) 
	{
		this.read(pkt.getNbtCompound());
		super.onDataPacket(net, pkt);
	}

	@Override
	public CompoundNBT getUpdateTag()
	{
		CompoundNBT nbt = new CompoundNBT();
		this.deserializeNBT(nbt);
		return super.getUpdateTag();
	}

	@Override
	public void handleUpdateTag(CompoundNBT tag) 
	{
		this.read(tag);
		super.handleUpdateTag(tag);
	}

	@Override
	public CompoundNBT getTileData() 
	{
		CompoundNBT nbt = new CompoundNBT();
		this.write(nbt);

		super.getTileData();

		return nbt;
	}

	public boolean isSendToClient()
	{
		return this.sendToClient;
	}

	public void setSendToClient(boolean client)
	{
		this.sendToClient = client;
	}

	public boolean hasCustomName() 
	{
		return this.customName != null && !this.customName.isEmpty();
	}

	public void setCustomName(String customName) 
	{
		this.customName = customName;
	}



	public boolean isUsableByPlayer(PlayerEntity player) 
	{
		if(player != null && world != null && pos != null)
			return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
		else
			return false;
	}

	public String getGuiID()
	{
		return "history:missileguidance";
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