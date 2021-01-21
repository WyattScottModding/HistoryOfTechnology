package handlers;

import main.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTable;

public class LootTableHandler 
{
	public static final ResourceLocation SILKWORM = LootTable.register(new ResourceLocation(Reference.MODID, "silkworm"));

}
