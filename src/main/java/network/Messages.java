package network;

import main.History;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class Messages {

    public static SimpleChannel INSTANCE;
    private static int ID = 0;
    
    public static int nextID() {
    	return ID++;
    }

    public static void registerMessages() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(History.modid),
                () -> "1.0",
                s -> true,
                s-> true);
        
        INSTANCE.registerMessage(nextID(), 
        		MessageFireMissile.class,
        		MessageFireMissile::toBytes,
        		MessageFireMissile::new,
        		MessageFireMissile::handle);
        
        INSTANCE.registerMessage(nextID(), 
        		MessageOpenTechBook.class,
        		MessageOpenTechBook::toBytes,
        		MessageOpenTechBook::new,
        		MessageOpenTechBook::handle);
        
        INSTANCE.registerMessage(nextID(), 
        		MessageOpenCalculator.class,
        		MessageOpenCalculator::toBytes,
        		MessageOpenCalculator::new,
        		MessageOpenCalculator::handle);
    }
}