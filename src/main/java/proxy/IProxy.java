package proxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public interface IProxy {

	void setup(final FMLCommonSetupEvent event);
	
	PlayerEntity getClientPlayer();
	
	public World getClientWorld();

	void init();

}
