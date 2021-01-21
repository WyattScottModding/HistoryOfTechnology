package handlers;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeyBindings 
{
	public static KeyBinding ITEM1;
	public static KeyBinding ITEM2;
	public static KeyBinding ARMOR1;
	public static KeyBinding ARMOR2;
	public static KeyBinding EXTRA1;

	public static void init()
	{
		ITEM1 = new KeyBinding("key.item1", KeyConflictContext.IN_GAME, InputMappings.Type.KEYSYM.getOrMakeInput(GLFW.GLFW_KEY_G), "key.categories.history");
		ITEM2 = new KeyBinding("key.item2", KeyConflictContext.IN_GAME, InputMappings.Type.KEYSYM.getOrMakeInput(GLFW.GLFW_KEY_V), "key.categories.history");
		ARMOR1 = new KeyBinding("key.armor1", KeyConflictContext.IN_GAME, InputMappings.Type.KEYSYM.getOrMakeInput(GLFW.GLFW_KEY_N), "key.categories.history");
		ARMOR2 = new KeyBinding("key.armor2", KeyConflictContext.IN_GAME, InputMappings.Type.KEYSYM.getOrMakeInput(GLFW.GLFW_KEY_M), "key.categories.history");
		EXTRA1 = new KeyBinding("key.extra1", KeyConflictContext.IN_GAME, InputMappings.Type.KEYSYM.getOrMakeInput(GLFW.GLFW_KEY_B), "key.categories.history");
		
		
		ClientRegistry.registerKeyBinding(ITEM1);
		ClientRegistry.registerKeyBinding(ITEM2);
		ClientRegistry.registerKeyBinding(ARMOR1);
		ClientRegistry.registerKeyBinding(ARMOR2);
		ClientRegistry.registerKeyBinding(EXTRA1);

	}
}
