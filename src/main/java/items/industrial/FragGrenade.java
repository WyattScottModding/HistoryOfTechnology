package items.industrial;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import entity.EntityFragGrenade;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FragGrenade extends Item
{
	public static final DefaultDispenseItemBehavior DISPENSER_BEHAVIOR = new DefaultDispenseItemBehavior() {
		/**
		 * Dispense the specified stack, play the dispense sound and spawn particles.
		 */
		protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
			ItemStack itemstack = FragGrenade.dispenseGrenade(source, stack);
			return itemstack.isEmpty() ? super.dispenseStack(source, stack) : itemstack;
		}
	};
	
	public static final DirectionProperty FACING = DirectionProperty.create("facing", new Predicate<Direction>()
	{
		public boolean apply(@Nullable Direction facing)
		{
			return true;
		}
	});

	public FragGrenade(String name, Item.Properties builder)
	{
		super(builder);
		this.setRegistryName(name);
		DispenserBlock.registerDispenseBehavior(this, DISPENSER_BEHAVIOR);
	}

	public static ItemStack dispenseGrenade(IBlockSource blockSource, ItemStack stack) {
		BlockPos blockpos = blockSource.getBlockPos();
		World world = blockSource.getWorld();
		
		int x = blockpos.getX();
		int y = blockpos.getY();
		int z = blockpos.getZ();

		world.playSound(null, x, y, z, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

		EntityFragGrenade entitygrenade = new EntityFragGrenade(world);

		BlockState state = world.getBlockState(blockpos);
		
		if(state.get(FACING) == Direction.NORTH)
			entitygrenade.shoot(x, y, z - 1, 1.0f, 0.5F);
		else if(state.get(FACING) == Direction.SOUTH)
			entitygrenade.shoot(x, y, z + 1, 1.0f, 0.5F);
		else if(state.get(FACING) == Direction.WEST)
			entitygrenade.shoot(x - 1, y, z, 1.0f, 0.5F);
		else if(state.get(FACING) == Direction.EAST)
			entitygrenade.shoot(x + 1, y, z, 1.0f, 0.5F);
		else if(state.get(FACING) == Direction.UP)
			entitygrenade.shoot(x, y + 1, z, 1.0f, 0.5F);
		else if(state.get(FACING) == Direction.DOWN)
			entitygrenade.shoot(x, y - 1, z, 1.0f, 0.5F);
		
		world.addEntity(entitygrenade);


		return stack;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
	{
		ItemStack itemstack = playerIn.getHeldItem(handIn);

		worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

		if (!worldIn.isRemote)
		{
			EntityFragGrenade entitygrenade = new EntityFragGrenade(worldIn);
			entitygrenade.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.0F, 0.5F);
			worldIn.addEntity(entitygrenade);

			if (!playerIn.isCreative())
			{
				itemstack.shrink(1);
			}
		}

		//playerIn.addStat(StatList.getObjectUseStats(this));
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemstack);
	}
}