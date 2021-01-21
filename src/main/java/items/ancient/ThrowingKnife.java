package items.ancient;

import entity.EntityKnife;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ThrowingKnife extends Item
{
	public ThrowingKnife(String name, Item.Properties builder)
	{
		super(builder);
		this.setRegistryName(name);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
	{
		ItemStack itemstack = playerIn.getHeldItem(handIn);

		worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

		if (!worldIn.isRemote)
		{
			EntityKnife entityknife = new EntityKnife(worldIn, playerIn);
			entityknife.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2.5F, 0.0F);
			worldIn.addEntity(entityknife);
			
			if (!playerIn.isCreative())
			{
				itemstack.shrink(1);
			}
		}

		//playerIn.addStat(StatList.getObjectUseStats(this));
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemstack);
	}
}