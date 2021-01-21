package network;

import java.util.function.Supplier;

import gui.GuiBookTechnology;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.network.NetworkEvent;

public class MessageOpenTechBook {

	public MessageOpenTechBook() {

	}

	//Bytes must be in the same order when reading and writing

	public MessageOpenTechBook(ByteBuf buf) 
	{

	}

	public void toBytes(ByteBuf buf) 
	{


	}

	public void handle(Supplier<NetworkEvent.Context> ctx)
	{
		ctx.get().enqueueWork(GuiBookTechnology::open);
		
		//This lets the system know the packet was handled correctly
		ctx.get().setPacketHandled(true);
	}
}
