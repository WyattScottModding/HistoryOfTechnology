package tileEntities;

import java.util.ArrayList;

import containers.ContainerForge;
import handlers.TileEntities;
import init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.state.IntegerProperty;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class TileEntityPipe extends TileEntity implements ITickableTileEntity, INamedContainerProvider
{
	public TileEntityPipe()
	{
		super(TileEntities.PIPE);
	}


	@Override
	public void tick()
	{	
		if(world.getGameTime() % 5 == 0)
		{
			shouldHaveWater(world, this.pos);
		}
	}


	public boolean shouldHaveWater(World world, BlockPos pos)
	{
		BlockPos posUp = pos.up();
		BlockPos posNorth = pos.north();
		BlockPos posSouth = pos.south();
		BlockPos posWest = pos.west();
		BlockPos posEast = pos.east();
		BlockPos posDown = pos.down();

		ArrayList<BlockPos> positions = new ArrayList<BlockPos>();

		positions.add(posUp);
		positions.add(posNorth);
		positions.add(posSouth);
		positions.add(posWest);
		positions.add(posEast);
		positions.add(posDown);

		for(BlockPos element : positions)
		{
			Block block = world.getBlockState(element).getBlock();

			//Water Block Nearby
			if(block == Blocks.WATER)
			{

				IntegerProperty LEVEL = IntegerProperty.create("level", 0, 15);
				int size = world.getBlockState(element).get(LEVEL);

				//If the water is a full water block
				if(size == 0)
				{
					return world.setBlockState(pos, BlockInit.PIPE_WATER.getDefaultState());
				}

			}
			else if(block == BlockInit.PIPE_WATER)
			{
				return world.setBlockState(pos, BlockInit.PIPE_WATER.getDefaultState());
			}
		}
		return false;
	}

	@Override
	public Container createMenu(int menuId, PlayerInventory inv, PlayerEntity player) {
		return new ContainerForge(menuId, world, pos, inv, player);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent(getType().getRegistryName().getPath());
	}
}