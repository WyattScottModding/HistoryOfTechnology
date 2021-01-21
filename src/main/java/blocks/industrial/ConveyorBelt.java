package blocks.industrial;

import java.util.ArrayList;
import java.util.List;

import main.History;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class ConveyorBelt extends Block{

	public static final DirectionProperty FACING = BlockStateProperties.FACING;

	Item.Properties properties;

	private double speed = 0;

	public ConveyorBelt(String name)
	{
		super(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(4.0F, 10.0F));
		this.setRegistryName(name);

		properties = new Item.Properties().maxStackSize(64).group(History.industrialTab);
	}

	@Override
	public void onLanded(IBlockReader world, Entity entity) {

		BlockPos pos = entity.getPosition().add(0, -1, 0);
		BlockState state = world.getBlockState(pos);
		World world2 = entity.getEntityWorld();

		speed = getPower(world2, pos, state) / 10;
		double motionX = entity.getMotion().x;
		double motionY = entity.getMotion().y;
		double motionZ = entity.getMotion().z;

		if(state.get(FACING).equals(Direction.NORTH))
		{
			motionZ -= speed;
		}
		else if(state.get(FACING).equals(Direction.SOUTH))
		{
			motionZ += speed;
		}
		else if(state.get(FACING).equals(Direction.EAST))
		{
			motionX += speed;
		}
		else
		{
			motionX -= speed;
		}
		entity.setMotion(motionX, motionY, motionZ);

		super.onLanded(world, entity);
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {
		if(entity != null) {
			world.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacingFromEntity(pos, entity)), 2);
		}
	}
	
	public static Direction getFacingFromEntity(BlockPos clickedBlock, LivingEntity entity) {
		return Direction.getFacingFromVector((float) (entity.posX - clickedBlock.getX()), (float) (entity.posY - clickedBlock.getY()), (float) (entity.posZ - clickedBlock.getZ()));
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

	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks for render
	 */
	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return true;
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
		builder.add(BlockStateProperties.FACING);
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos,
			PlayerEntity player) 
	{
		return new ItemStack(new BlockItem(this,  properties));
	}
}