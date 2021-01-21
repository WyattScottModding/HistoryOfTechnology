package tileEntities;

import containers.ContainerForge;
import handlers.TileEntities;
import init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class TileEntityElectricLamp extends TileEntity implements ITickableTileEntity, INamedContainerProvider
{
	private int previousPower = getPower();

	public TileEntityElectricLamp()
	{
		super(TileEntities.ELECTRIC_LAMP);
	}


	@Override
	public void tick()
	{
		int power = getPower();

		Block block = world.getBlockState(pos).getBlock();

		System.out.println("Power: " + power);

		if (previousPower != power)
		{
			switch(power) {
			case 0:
			{
				if(block != BlockInit.ELECTRIC_LAMP)
					world.setBlockState(pos, BlockInit.ELECTRIC_LAMP.getDefaultState());
			}
			case 2:
			{
				if(block != BlockInit.ELECTRIC_LAMP1)
					world.setBlockState(pos, BlockInit.ELECTRIC_LAMP1.getDefaultState());
			}
			case 4:
			{
				if(block != BlockInit.ELECTRIC_LAMP2)
					world.setBlockState(pos, BlockInit.ELECTRIC_LAMP2.getDefaultState());
			}
			case 6:
			{
				if(block != BlockInit.ELECTRIC_LAMP3)
					world.setBlockState(pos, BlockInit.ELECTRIC_LAMP3.getDefaultState());
			}
			case 8:
			{
				if(block != BlockInit.ELECTRIC_LAMP4)
					world.setBlockState(pos, BlockInit.ELECTRIC_LAMP4.getDefaultState());
			}
			case 10:
			{
				if(block != BlockInit.ELECTRIC_LAMP5)
					world.setBlockState(pos, BlockInit.ELECTRIC_LAMP5.getDefaultState());
			}
			case 12:
			{
				if(block != BlockInit.ELECTRIC_LAMP6)
					world.setBlockState(pos, BlockInit.ELECTRIC_LAMP6.getDefaultState());
			}
			case 14:
			{
				if(block != BlockInit.ELECTRIC_LAMP7)
					world.setBlockState(pos, BlockInit.ELECTRIC_LAMP7.getDefaultState());
			}
			case 16:
			{
				if(block != BlockInit.ELECTRIC_LAMP8)
					world.setBlockState(pos, BlockInit.ELECTRIC_LAMP8.getDefaultState());
			}
			case 18:
			{
				if(block != BlockInit.ELECTRIC_LAMP9)
					world.setBlockState(pos, BlockInit.ELECTRIC_LAMP9.getDefaultState());
			}
			case 20:
			{
				if(block != BlockInit.ELECTRIC_LAMP10)
					world.setBlockState(pos, BlockInit.ELECTRIC_LAMP10.getDefaultState());
			}
			case 22:
			{
				if(block != BlockInit.ELECTRIC_LAMP11)
					world.setBlockState(pos, BlockInit.ELECTRIC_LAMP11.getDefaultState());
			}
			case 24:
			{
				if(block != BlockInit.ELECTRIC_LAMP12)
					world.setBlockState(pos, BlockInit.ELECTRIC_LAMP12.getDefaultState());
			}
			case 26:
			{
				if(block != BlockInit.ELECTRIC_LAMP13)
					world.setBlockState(pos, BlockInit.ELECTRIC_LAMP13.getDefaultState());
			}
			case 28:
			{
				if(block != BlockInit.ELECTRIC_LAMP14)
					world.setBlockState(pos, BlockInit.ELECTRIC_LAMP14.getDefaultState());
			}
			case 30:
			{
				if(block != BlockInit.ELECTRIC_LAMP15)
					world.setBlockState(pos, BlockInit.ELECTRIC_LAMP15.getDefaultState());
			}
			}
		}
	}


	protected int getPower()
	{
		return world.getRedstonePowerFromNeighbors(pos) * 2;
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