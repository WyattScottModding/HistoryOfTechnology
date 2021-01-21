package items.industrial;

import init.ItemInit;
import items.medieval.GunBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.world.World;

public class GatlingGun extends GunBase
{
	private static int delay = 5;
	private static float damage = 6;
	private static float speed = 8;
	private static String animation = "crit";

	private static Item bullet = ItemInit.BULLET;
	
	public GatlingGun(String name, Item.Properties builder)
	{	
		super(name, delay, damage, speed, bullet, animation, builder, ItemTier.IRON);
	}
	
	@Override
	public void playSound(World world, PlayerEntity player) 
	{
		super.playSound(world, player);	
	}
}
