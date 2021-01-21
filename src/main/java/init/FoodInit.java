package init;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;

public class FoodInit {

	public static final Food COFFEE_FOOD = (new Food.Builder()).setAlwaysEdible().effect(new EffectInstance(PotionInit.CAFFEINE_EFFECT, 6000, 1), 1.0F).build();

}
