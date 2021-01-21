package items.ancient;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import network.MessageOpenTechBook;
import network.Messages;

public class BookOfTechnologies extends Item
{
	public BookOfTechnologies(String name, Item.Properties builder)
	{
		super(builder);
		this.setRegistryName(name);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) 
	{
		if(!world.isRemote && player instanceof ServerPlayerEntity) {
			ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
			Messages.INSTANCE.sendTo(new MessageOpenTechBook(),  serverPlayer.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
			
		}
		return super.onItemRightClick(world, player, hand);
	}
}
