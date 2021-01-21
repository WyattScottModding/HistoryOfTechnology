package handlers;

import org.jline.utils.Log;

import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class KeyInputHandler 
{
	@SubscribeEvent
	public void handleKeyInputEvent(KeyInputEvent event)
	{
		if(KeyBindings.ITEM1.isPressed())
		{
			Log.info("Item 1");
		}
		if(KeyBindings.ITEM2.isPressed())
		{
			Log.info("Item 2");
		}
		if(KeyBindings.ARMOR1.isPressed())
		{
			Log.info("Armor 1");
		}
		if(KeyBindings.ARMOR2.isPressed())
		{
			Log.info("Armor 2");
		}
		if(KeyBindings.EXTRA1.isPressed())
		{
			Log.info("Extra 1");
			//NetworkHandler.sendToServer(new MessageExplode(3));
		}
	} 
}