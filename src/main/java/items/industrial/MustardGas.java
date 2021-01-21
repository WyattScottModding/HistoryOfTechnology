package items.industrial;

import entity.EntityMustardGas;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class MustardGas extends Item
{
	public MustardGas(String name, Item.Properties builder)
	{
		super(builder);
		this.setRegistryName(name);
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
	{
		ItemStack itemstack = playerIn.getHeldItem(handIn);

		if (!worldIn.isRemote)
		{
			worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

			EntityMustardGas entitymustardgas = new EntityMustardGas(worldIn, playerIn);
			entitymustardgas.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.0F, 0.5F);
			worldIn.addEntity(entitymustardgas);
			
			if (!playerIn.isCreative())
			{
				itemstack.shrink(1);
			}
		}

		//playerIn.addStat(StatList.getObjectUseStats(this));
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemstack);
	}
	
	
	

}