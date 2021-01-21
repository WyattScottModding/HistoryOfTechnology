package villagerTrades;

import java.util.Random;

import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;

public class TradeButcher implements ITrade
{
	@Override
	public MerchantOffer getOffer(Entity entity, Random rand) {
		return new MerchantOffer(new ItemStack(Items.EMERALD,1), new ItemStack(ItemInit.PENICILLIN,1), 10, 2, 3);
	}
}
