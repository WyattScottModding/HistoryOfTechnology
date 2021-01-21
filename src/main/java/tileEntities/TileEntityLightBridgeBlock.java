package tileEntities;

import java.util.ArrayList;

import containers.ContainerForge;
import handlers.SoundsHandler;
import handlers.TileEntities;
import init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class TileEntityLightBridgeBlock extends TileEntity implements ITickableTileEntity, INamedContainerProvider
{
	private int number = 100;

	public TileEntityLightBridgeBlock() 
	{
		super(TileEntities.LIGHT_BRIDGE_BLOCK);
	}


	@Override
	public void tick()
	{
		if(!world.isRemote)
		{
			if(world.getGameTime() == 0 || world.getGameTime() % 3480 == 0) 
			{
				world.playSound(null, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundsHandler.BLOCK_LIGHTBRIDGE, SoundCategory.BLOCKS, 1.0F, 1.0F);    	
			}
		}

		if(!world.isRemote && (neighborChanged() || world.getGameTime() % 40 == 0))
		{
			this.number = findNumber();

			if(this.number == -1)
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			}
		}
	}

	public boolean neighborChanged()
	{
		BlockPos posNorth = pos.north();
		BlockPos posSouth = pos.south();
		BlockPos posWest = pos.west();
		BlockPos posEast = pos.east();

		ArrayList<BlockPos> positions = new ArrayList<BlockPos>();
		positions.add(posNorth);
		positions.add(posSouth);
		positions.add(posWest);
		positions.add(posEast);

		for(BlockPos element : positions)
		{
			Block block = world.getBlockState(element).getBlock();

			if(block == BlockInit.LIGHT_BRIDGE_BLOCK || block == BlockInit.LIGHT_BRIDGE || block == Blocks.AIR)
			{
				return true;
			}
		}


		return false;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int findNumber()
	{
		int number = this.number;
		DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

		boolean facingNorthOrSouth = world.getBlockState(pos).get(FACING).equals(Direction.NORTH) || world.getBlockState(pos).get(FACING).equals(Direction.SOUTH);

		BlockPos posNorth = pos.north();
		BlockPos posSouth = pos.south();
		BlockPos posWest = pos.west();
		BlockPos posEast = pos.east();

		ArrayList<BlockPos> positions = new ArrayList<BlockPos>();

		if(facingNorthOrSouth)
		{
			positions.add(posNorth);
			positions.add(posSouth);
		}
		else
		{
			positions.add(posWest);
			positions.add(posEast);
		}

		for(BlockPos element : positions)
		{
			Block block = world.getBlockState(element).getBlock();

			//Get the light bridge nearby
			if(block == BlockInit.LIGHT_BRIDGE)
			{
				BooleanProperty ON = BooleanProperty.create("on");
				boolean on = world.getBlockState(element).get(ON);

				//If the light bridge is on
				if(on)
				{
					return 0;
				}

			}
			//If there is a light bridge block nearby
			if(block == BlockInit.LIGHT_BRIDGE_BLOCK)
			{
				if(world.getTileEntity(element) instanceof TileEntityLightBridgeBlock)
				{
					TileEntityLightBridgeBlock tileentity = (TileEntityLightBridgeBlock) world.getTileEntity(element);

					boolean facingNorthOrSouth2 = world.getBlockState(element).get(FACING).equals(Direction.NORTH) || world.getBlockState(element).get(FACING).equals(Direction.SOUTH);

					//Only works if they are facing the same direction
					if(facingNorthOrSouth == facingNorthOrSouth2)
					{
						//The number of the nearby light bridge
						int number2 = tileentity.getNumber();

						//If the nearby light bridge nearby has a smaller water number, then this light bridge will connect to that					
						if(number2 < number)
						{
							return number2 + 1;
						}
					}
				}
			}
		}
		return -1;
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