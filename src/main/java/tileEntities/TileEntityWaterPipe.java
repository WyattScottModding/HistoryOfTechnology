package tileEntities;

import java.util.ArrayList;

import containers.ContainerForge;
import handlers.TileEntities;
import init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IntegerProperty;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class TileEntityWaterPipe extends TileEntity implements ITickableTileEntity, INamedContainerProvider
{
	public final BasicParticleType particles = ParticleTypes.DRIPPING_WATER;

	public int waterLevel = 100000;

	public TileEntityWaterPipe()
	{
		super(TileEntities.WATER_PIPE);
	}


	public boolean pipe(Block block)
	{
		return block == BlockInit.PIPE || block == BlockInit.PIPE_WATER;
	}

	@Override
	public void tick()
	{
		if(world.getGameTime() % 20 == 0)
		{
			this.waterLevel = getWaterlevel();

			if(this.waterLevel == -1)
			{
				world.setBlockState(pos, BlockInit.PIPE.getDefaultState());
			}
		}
	}


	public int getWaterlevel()
	{
		int waterLevel = this.waterLevel;
		boolean nearbyWaterSource = false;

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

			//Get the water level nearby
			if(block == Blocks.WATER)
			{
				IntegerProperty LEVEL = IntegerProperty.create("level", 0, 15);
				int size = world.getBlockState(element).get(LEVEL);

				//If the water is a full water block
				if(size == 0)
				{
					//If there is a water block nearby, then it has a water level of 0
					//nearbyWaterSource = true;
					return 0;
				}
			}
			//If there is a water pipe nearby
			if(block == BlockInit.PIPE_WATER)
			{
				if(world.getTileEntity(element) instanceof TileEntityWaterPipe)
				{
					TileEntityWaterPipe tileentity = (TileEntityWaterPipe) world.getTileEntity(element);

					//The water level of the nearby water pipe
					int waterLevel2 = tileentity.waterLevel;

					//If the nearby pipe has a smaller water level, then this pipe will get water from there instead					
					if(waterLevel2 < waterLevel)
					{
						waterLevel = waterLevel2 + 1;
						nearbyWaterSource = true;
					}
				}
			}
		}

		if(!nearbyWaterSource || waterLevel >= 100000)
			return -1;
		else
			return waterLevel;

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