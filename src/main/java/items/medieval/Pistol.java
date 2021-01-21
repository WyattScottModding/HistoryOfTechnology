package items.medieval;

import init.ItemInit;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;

public class Pistol extends GunBase
{
	private static int delay = 80;
	private static float damage = 8;
	private static float speed = 4;
	private static String animation = "crit";
	private static ItemTier tier = ItemTier.WOOD;
	
	private static Item bullet = ItemInit.BULLET;
	
	public Pistol(String name, Item.Properties builder)
	{	
		super(name, delay, damage, speed, bullet, animation, builder, tier);
	}


}
