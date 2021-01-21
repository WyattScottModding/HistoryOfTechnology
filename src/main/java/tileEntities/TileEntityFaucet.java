package tileEntities;

import containers.ContainerForge;
import handlers.TileEntities;
import init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class TileEntityFaucet extends TileEntity implements ITickableTileEntity, INamedContainerProvider
{
	public TileEntityFaucet() 
	{
		super(TileEntities.FAUCET);
	}


	@Override
	public void tick()
	{
		Direction facing = world.getBlockState(pos).get(HorizontalBlock.HORIZONTAL_FACING);

		Block blockFacing = world.getBlockState(pos.offset(facing)).getBlock();
		Block block = world.getBlockState(pos).getBlock();

		if(blockFacing == BlockInit.PIPE_WATER)
		{
			if(block != BlockInit.FAUCET_ON)
				world.setBlockState(pos, BlockInit.FAUCET_ON.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, facing));
		}
		else if(blockFacing != BlockInit.PIPE_WATER)
		{
			if(block != BlockInit.FAUCET)
				world.setBlockState(pos, BlockInit.FAUCET.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, facing));
			
			Block blockDown = world.getBlockState(pos.down()).getBlock();

			if(blockDown == Blocks.WATER)
			{
				world.setBlockState(pos.down(), Blocks.AIR.getDefaultState());
			}
		}
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