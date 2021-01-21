package villagerTrades;

import java.util.Random;

import init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;

public class TradeFarmer implements ITrade
{
	@Override
	public MerchantOffer getOffer(Entity entity, Random rand) {
		return new MerchantOffer(new ItemStack(Items.EMERALD,1), new ItemStack(ItemInit.COFFEEBEAN,1), 15, 2, 2);
	}

}
