package handlers;

import main.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;


public class SoundsHandler
{
	public static SoundEvent ITEM_MAGNETGUN_FIRED;

	public static SoundEvent PLAYER_BOOSTJUMP;
	
	public static SoundEvent ITEM_TAC_FIRED;

	public static SoundEvent ITEM_IMR_FIRED;
	
	public static SoundEvent ITEM_THREATGRENADE_HIT;

	public static SoundEvent BLOCK_BOUNCE;

	public static SoundEvent BLOCK_LIGHTBRIDGE;


	public static final SoundEvent RECORD_SWEDEN;



	static
	{
		RECORD_SWEDEN = registerSound("record.sweden");

	}
	
	public static void registerSounds()
	{
		ITEM_MAGNETGUN_FIRED = registerSound("item.magnetgun.fired");

		PLAYER_BOOSTJUMP = registerSound("player.boostjump");

		ITEM_TAC_FIRED = registerSound("item.tac19.fired");

		ITEM_IMR_FIRED = registerSound("item.imr.fired");

		ITEM_THREATGRENADE_HIT = registerSound("item.threatgrenade.hit");

		BLOCK_BOUNCE = registerSound("block.bounce");

		BLOCK_LIGHTBRIDGE = registerSound("block.lightbridge");

	}

	private static SoundEvent registerSound(String name)
	{
		ResourceLocation location = new ResourceLocation(Reference.MODID, name);
		SoundEvent event = new SoundEvent(location);
		event.setRegistryName(name);
		ForgeRegistries.SOUND_EVENTS.register(event);

		return event;
	}
}