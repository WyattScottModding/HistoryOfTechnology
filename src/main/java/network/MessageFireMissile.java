package network;

import java.util.function.Supplier;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;
import tileEntities.TileEntityMissileGuidanceSystem;


public class MessageFireMissile 
{
	private int posX, posZ, posX1, posY1, posZ1 = 0;

	
	//public MessageFireMissile() {}

	public MessageFireMissile(int posX1, int posY1, int posZ1, int posX, int posZ)
	{
		this.posX = posX;
		this.posZ = posZ;

		this.posX1 = posX1;
		this.posY1 = posY1;
		this.posZ1 = posZ1;
	}

	//Bytes must be in the same order when reading and writing

	public MessageFireMissile(ByteBuf buf) 
	{
		posX = buf.readInt();
		posZ = buf.readInt();

		posX1 = buf.readInt();
		posY1 = buf.readInt();
		posZ1 = buf.readInt();

	}

	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(posX);
		buf.writeInt(posZ);

		buf.writeInt(posX1);
		buf.writeInt(posY1);
		buf.writeInt(posZ1);

	}

	//Can also use ByteBufUtils instead of ByteBuf

	public void handle(Supplier<NetworkEvent.Context> ctx)
	{
		//ctx.get().enqueueWork() -> OverlayRenderer.i
		PlayerEntity player = ctx.get().getSender();
		//player.world.createExplosion(player, player.posX, player.posY, player.posZ, message.explosionSize, true);

		/*
		World world = player.world;
		BlockPos pos = new BlockPos(message.posX1, message.posY1, message.posZ1);

		TileEntity entity = world.getTileEntity(pos);

		if(entity instanceof TileEntityMissileGuidanceSystem)
		{
			TileEntityMissileGuidanceSystem tileentity = (TileEntityMissileGuidanceSystem) entity;
			
			tileentity.fireMissile(message.posX, message.posZ, player);
			
			//System.out.println("Fired Missile");
		}
		*/
	
	}
}
