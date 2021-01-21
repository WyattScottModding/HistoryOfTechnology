package tileEntities;

import java.util.ArrayList;
import java.util.List;

import containers.ContainerForge;
import handlers.TileEntities;
import init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class TileEntityLightBridge extends TileEntity implements ITickableTileEntity, INamedContainerProvider
{
	public TileEntityLightBridge() 
	{
		super(TileEntities.LIGHT_BRIDGE);
	}


	@Override
	public void tick()
	{
		if(!world.isRemote)
		{
			//North add Z
			//West add X
			DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;


			int length = getPower(world, pos, world.getBlockState(pos)) * 6;
			Direction facing = world.getBlockState(pos).get(FACING);

			BlockState setBlock = BlockInit.LIGHT_BRIDGE_BLOCK.getDefaultState().with(FACING, facing);


			if(length == 0)
			{
				setBlock = Blocks.AIR.getDefaultState();

				BooleanProperty ON = BooleanProperty.create("on");

				world.setBlockState(pos, this.world.getBlockState(pos).with(ON, false));
			}
			else
			{
				BooleanProperty ON = BooleanProperty.create("on");

				world.setBlockState(pos, this.world.getBlockState(pos).with(ON, true));
			}

			ArrayList<Block> blockList = new ArrayList<Block>();
			blockList.add(BlockInit.MUSTARD_GAS);
			blockList.add(Blocks.AIR);
			blockList.add(Blocks.WATER);
			blockList.add(Blocks.LAVA);
			blockList.add(Blocks.FIRE);
			blockList.add(BlockInit.LIGHT_BRIDGE_BLOCK);


			ArrayList<Block> breakBlockList = new ArrayList<Block>();
			breakBlockList.add(Blocks.TALL_GRASS);
			breakBlockList.add(Blocks.GRASS);
			breakBlockList.add(Blocks.TALL_SEAGRASS);
			breakBlockList.add(Blocks.BROWN_MUSHROOM);
			breakBlockList.add(Blocks.RED_MUSHROOM);
			
			breakBlockList.add(Blocks.BLACK_BED);
			breakBlockList.add(Blocks.BLUE_BED);
			breakBlockList.add(Blocks.CYAN_BED);
			breakBlockList.add(Blocks.BROWN_BED);
			breakBlockList.add(Blocks.GRAY_BED);
			breakBlockList.add(Blocks.GREEN_BED);
			breakBlockList.add(Blocks.LIGHT_BLUE_BED);
			breakBlockList.add(Blocks.LIGHT_GRAY_BED);
			breakBlockList.add(Blocks.LIME_BED);
			breakBlockList.add(Blocks.MAGENTA_BED);
			breakBlockList.add(Blocks.ORANGE_BED);
			breakBlockList.add(Blocks.PINK_BED);
			breakBlockList.add(Blocks.PURPLE_BED);
			breakBlockList.add(Blocks.RED_BED);
			breakBlockList.add(Blocks.WHITE_BED);
			breakBlockList.add(Blocks.YELLOW_BED);
			
			breakBlockList.add(Blocks.WHEAT);
			
			breakBlockList.add(Blocks.BLACK_CARPET);
			breakBlockList.add(Blocks.CYAN_CARPET);
			breakBlockList.add(Blocks.BROWN_CARPET);
			breakBlockList.add(Blocks.BLUE_CARPET);
			breakBlockList.add(Blocks.GRAY_CARPET);
			breakBlockList.add(Blocks.GRAY_CARPET);
			breakBlockList.add(Blocks.GREEN_CARPET);
			breakBlockList.add(Blocks.LIGHT_BLUE_CARPET);
			breakBlockList.add(Blocks.LIGHT_GRAY_CARPET);
			breakBlockList.add(Blocks.LIME_CARPET);
			breakBlockList.add(Blocks.MAGENTA_CARPET);
			breakBlockList.add(Blocks.ORANGE_CARPET);
			breakBlockList.add(Blocks.PINK_CARPET);
			breakBlockList.add(Blocks.PURPLE_CARPET);
			breakBlockList.add(Blocks.RED_CARPET);
			breakBlockList.add(Blocks.WHITE_CARPET);
			breakBlockList.add(Blocks.YELLOW_CARPET);

			breakBlockList.add(Blocks.CARROTS);
			breakBlockList.add(Blocks.BREWING_STAND);
			breakBlockList.add(Blocks.POTATOES);
			breakBlockList.add(Blocks.BEETROOTS);
			breakBlockList.add(Blocks.TRIPWIRE_HOOK);
			breakBlockList.add(Blocks.REDSTONE_WIRE);
			
			breakBlockList.add(Blocks.ACACIA_SAPLING);
			breakBlockList.add(Blocks.SPRUCE_SAPLING);
			breakBlockList.add(Blocks.DARK_OAK_SAPLING);
			breakBlockList.add(Blocks.JUNGLE_SAPLING);
			breakBlockList.add(Blocks.OAK_SAPLING);
			breakBlockList.add(Blocks.BIRCH_SAPLING);

			breakBlockList.add(Blocks.CHORUS_FLOWER);
			breakBlockList.add(Blocks.SUNFLOWER);
			breakBlockList.add(Blocks.POPPY);
			
			breakBlockList.add(Blocks.ORANGE_TULIP);
			breakBlockList.add(Blocks.PINK_TULIP);
			breakBlockList.add(Blocks.RED_TULIP);
			breakBlockList.add(Blocks.WHITE_TULIP);

			breakBlockList.add(BlockInit.FAUCET);
			breakBlockList.add(BlockInit.FAUCET_ON);
			breakBlockList.add(Blocks.REDSTONE_TORCH);
			breakBlockList.add(Blocks.COMPARATOR);
			breakBlockList.add(Blocks.REPEATER);
			breakBlockList.add(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE);
			breakBlockList.add(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE);
			breakBlockList.add(Blocks.STONE_PRESSURE_PLATE);
			
			breakBlockList.add(Blocks.ACACIA_PRESSURE_PLATE);
			breakBlockList.add(Blocks.BIRCH_PRESSURE_PLATE);
			breakBlockList.add(Blocks.DARK_OAK_PRESSURE_PLATE);
			breakBlockList.add(Blocks.JUNGLE_PRESSURE_PLATE);
			breakBlockList.add(Blocks.OAK_PRESSURE_PLATE);
			breakBlockList.add(Blocks.SPRUCE_PRESSURE_PLATE);

			breakBlockList.add(Blocks.LEVER);

			BlockPos pos2;
			Block block;


			for(int i = 1; i <= length; i++)
			{
				if(facing.equals(Direction.NORTH))
				{
					pos2 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + i);
					block = world.getBlockState(pos2).getBlock();
				}
				else if(facing.equals(Direction.WEST))
				{
					pos2 = new BlockPos(pos.getX() + i, pos.getY(), pos.getZ());
					block = world.getBlockState(pos2).getBlock();
				}
				else if(facing.equals(Direction.SOUTH))
				{
					pos2 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - i);
					block = world.getBlockState(pos2).getBlock();
				}
				else
				{
					pos2 = new BlockPos(pos.getX() - i, pos.getY(), pos.getZ());
					block = world.getBlockState(pos2).getBlock();
				}

				//If it hits a solid block then the light bridge ends
				if(breakBlockList.contains(block))
				{
					Block.spawnDrops(world.getBlockState(pos2), world, pos2);
					world.setBlockState(pos2, setBlock);
				}
				else if(blockList.contains(block))
				{
					if(block == BlockInit.LIGHT_BRIDGE_BLOCK)
					{
						if(world.getBlockState(pos2).get(FACING).equals(facing))
						{
							world.setBlockState(pos2, setBlock);
						}
						else
						{
							return;
						}
					}
					else
					{
						world.setBlockState(pos2, setBlock);
					}
				}
				else
				{
					return;
				}
			}
		}
	}


	protected int getPower(World worldIn, BlockPos pos, BlockState state)
	{
		List<Direction> list = new ArrayList<Direction>();

		list.add(Direction.UP);
		list.add(Direction.DOWN);
		list.add(Direction.NORTH);
		list.add(Direction.SOUTH);
		list.add(Direction.EAST);
		list.add(Direction.WEST);

		int i = 0;

		for(Direction element : list)
		{
			BlockPos blockpos = pos.offset(element);
			int j = worldIn.getRedstonePower(blockpos, element);

			if(j > i)
				i = j;
		}

		return i;
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