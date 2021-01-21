package handlers;

import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import villagerTrades.TradeFarmer;
import villagerTrades.TradeButcher;

public class VillagerTradeHandler
{
	public static void init()
	{
		VillagerTrades.field_221239_a.get(VillagerProfession.FARMER).put(5, new VillagerTrades.ITrade[]{new TradeFarmer()});
		VillagerTrades.field_221239_a.get(VillagerProfession.BUTCHER).put(5, new VillagerTrades.ITrade[]{new TradeButcher()});
	}

}