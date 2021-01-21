package items.renaissance;

import init.BlockInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class CoffeeBean extends Item implements IPlantable
{
	public CoffeeBean(String name, Item.Properties builder)
	{
		super(builder);
		this.setRegistryName(name);
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) 
	{
		World world = context.getWorld();
		BlockPos pos = context.getPos();
		PlayerEntity player = context.getPlayer();
		Direction facing = context.getFace();
		ItemStack stack = context.getItem();
		BlockState state = world.getBlockState(pos);

		if(facing == Direction.UP && player.canPlayerEdit(pos.offset(facing), facing, stack) && state.getBlock().canSustainPlant(state, world, pos, Direction.UP, this) && world.isAirBlock(pos.up()))
		{
			world.setBlockState(pos.up(), BlockInit.COFFEE_PLANT.getDefaultState());
			stack.shrink(1);
			return ActionResultType.SUCCESS;
		}

		return ActionResultType.FAIL;
	}

	@Override
	public PlantType getPlantType(IBlockReader world, BlockPos pos) 
	{
		return PlantType.Crop;
	}

	@Override
	public BlockState getPlant(IBlockReader world, BlockPos pos)
	{
		return BlockInit.COFFEE_PLANT.getDefaultState();
	}
}