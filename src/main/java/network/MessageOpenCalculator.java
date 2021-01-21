package network;

import java.util.function.Supplier;

import gui.GuiCalculator;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.network.NetworkEvent;

public class MessageOpenCalculator {

	public MessageOpenCalculator() {

	}

	//Bytes must be in the same order when reading and writing

	public MessageOpenCalculator(ByteBuf buf) 
	{

	}

	public void toBytes(ByteBuf buf) 
	{


	}

	public void handle(Supplier<NetworkEvent.Context> ctx)
	{
		ctx.get().enqueueWork(GuiCalculator::open);
		
		//This lets the system know the packet was handled correctly
		ctx.get().setPacketHandled(true);
	}
}
