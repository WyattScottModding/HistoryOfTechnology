package tileEntities;

import containers.ContainerForge;
import handlers.TileEntities;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class TileEntityMustardGas extends TileEntity implements ITickableTileEntity, INamedContainerProvider
{
	public TileEntityMustardGas()
	{
		super(TileEntities.MUSTARDGAS);
	}


	@Override
	public void tick()
	{
		int x = (int)(Math.random() * 2400);

		if(x == 0)
		{
			world.setBlockState(pos, Blocks.AIR.getDefaultState());
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