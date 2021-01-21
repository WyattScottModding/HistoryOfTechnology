package tileEntities;

import blocks.industrial.LightSource;
import containers.ContainerForge;
import handlers.TileEntities;
import init.BlockInit;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class TileEntityLightSource extends TileEntity implements ITickableTileEntity, INamedContainerProvider
{
    public PlayerEntity thePlayer;
    
    public TileEntityLightSource() 
	{
		super(TileEntities.LIGHT_SOURCE);
	}
    
    @Override
    public void tick()
    {
        // check if player has moved away from the tile entity
        PlayerEntity thePlayer = world.getClosestPlayer(getPos().getX()+0.5D, getPos().getY()+0.5D, getPos().getZ()+0.5D, 2.0D, false);
      
        if (thePlayer == null)
        {
            if (world.getBlockState(getPos()).getBlock() == BlockInit.LIGHT_SOURCE)
            {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
        else if (thePlayer.getHeldItemMainhand().getItem() != null && !LightSource.isLightEmittingItem(thePlayer.getHeldItemMainhand().getItem()))
        {
            if (world.getBlockState(getPos()).getBlock() == BlockInit.LIGHT_SOURCE)
            {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }            
        }
        
        setPlayer(thePlayer);
    }  
    
    public void setPlayer(PlayerEntity parPlayer)
    {
        thePlayer = parPlayer;
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