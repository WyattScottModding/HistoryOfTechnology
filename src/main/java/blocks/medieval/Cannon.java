package blocks.medieval;

import entity.EntityRock;
import init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class Cannon extends Block{

	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	public static final IntegerProperty ROCK = IntegerProperty.create("rock", 0, 4);

	public int rock = 0;

	Item.Properties properties;

	public Cannon(String name)
	{
		super(Block.Properties.create(Material.IRON).sound(SoundType.METAL));
		this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH).with(ROCK, 0));

		this.setRegistryName(name);

		properties = new Item.Properties().maxStackSize(64);
	}

	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks for render
	 */
	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return false;
	}

	@Override
	public boolean canBeConnectedTo(BlockState state, IBlockReader world, BlockPos pos, Direction facing) 
	{
		if (this.canPlaceAt(world, pos))
		{
			return true;
		}

		return false;
	}


	public boolean canPlaceAt(IBlockReader worldIn, BlockPos pos)
	{
		BlockState downState = worldIn.getBlockState(pos.down());
		return downState.isSolid() || downState.isNormalCube(worldIn, pos) || worldIn.getBlockState(pos.down()).getBlock() == Blocks.GLOWSTONE;
	}


	@Override
		public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn,
				BlockRayTraceResult hit) {
		if(player.getHeldItemMainhand() == new ItemStack(ItemInit.ROCK))
		{
			if(rock == 0)
			{
				world.setBlockState(pos, state.with(ROCK, 1));
				rock = 1;
				player.getHeldItemMainhand().shrink(1);
			}
			else if(rock == 1)
			{
				world.setBlockState(pos, state.with(ROCK, 2));
				rock = 2;
				player.getHeldItemMainhand().shrink(1);
			}
			else if(rock == 2)
			{
				world.setBlockState(pos, state.with(ROCK, 3));
				rock = 3;
				player.getHeldItemMainhand().shrink(1);
			}
			else if(rock == 3)
			{
				world.setBlockState(pos, state.with(ROCK, 4));
				rock = 4;
				player.getHeldItemMainhand().shrink(1);
			}
			else
			{
				launch(world, player, rock);
				rock = 0;
				world.setBlockState(pos, state.with(ROCK, 0));
			}
		}
		else if(rock != 0)
		{
			launch(world, player, rock);
			rock = 0;
			world.setBlockState(pos, state.with(ROCK, 0));
		}
		return false;
	}


	public void launch(World world, PlayerEntity player, int rocks)
	{
		world.playSound((PlayerEntity)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (0.4F + 0.8F));

		if (!world.isRemote)
		{
			float speed = 16;

			if(rocks == 2)
				speed = 12;
			else if(rocks == 3)
				speed = 8;
			else if(rocks == 4)
				speed = 4;

			for(int i = 0; i < rocks; i++)
			{
				EntityRock entityrock = new EntityRock(world, player, 20);
				entityrock.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, speed, 0.5F);
				world.addEntity(entityrock);
			}

		}

	}

	@Override
	public BlockRenderType getRenderType(BlockState state) 
	{
		return BlockRenderType.MODEL;
	}

	@Override
	public BlockRenderLayer getRenderLayer() 
	{
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) 
	{
		return state.rotate(world, pos, direction);
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirror)
	{
		return state.mirror(mirror);
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(FACING, ROCK);
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos,
			PlayerEntity player) 
	{
		return new ItemStack(new BlockItem(this,  properties));
	}

}