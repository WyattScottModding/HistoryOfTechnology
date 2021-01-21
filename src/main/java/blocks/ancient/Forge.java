package blocks.ancient;

import java.util.Random;

import javax.annotation.Nullable;

import init.BlockInit;
import main.History;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
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
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.ItemStackHandler;
import tileEntities.IRestorableTileEntity;
import tileEntities.TileEntityForge;

public class Forge extends ContainerBlock
{
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	public static final BooleanProperty BURNING = RedstoneTorchBlock.LIT;
	Item.Properties properties;

	public Forge(String name)
	{
		super(Block.Properties.create(Material.ROCK).hardnessAndResistance(50F, 6000F).sound(SoundType.STONE));
		this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH).with(BURNING, false));
		this.setRegistryName(name);

		properties = new Item.Properties().maxStackSize(64).group(History.ancientTab);
	}

	@Override
	public boolean isNormalCube(BlockState state, IBlockReader world, BlockPos pos) 
	{
		return false;
	}

	/**
	 * Ensure NBT data is restored from itemstack on block place
	 */
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof IRestorableTileEntity) {
			CompoundNBT nbtTagCompound = stack.getTag();
			if (nbtTagCompound != null)
				((IRestorableTileEntity)te).readRestorableFromNBT(nbtTagCompound);
		}
		
		if(entity != null) {
			world.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacingFromEntity(pos, entity)), 2);
		}
	}
	
	public static Direction getFacingFromEntity(BlockPos clickedBlock, LivingEntity entity) {
		return Direction.getFacingFromVector((float) (entity.posX - clickedBlock.getX()), (float) (entity.posY - clickedBlock.getY()), (float) (entity.posZ - clickedBlock.getZ()));
	}


	public static void setState(boolean active, World worldIn, BlockPos pos) 
	{
		BlockState state = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if(active) 
			worldIn.setBlockState(pos, BlockInit.FORGE.getDefaultState().with(FACING, state.get(FACING)).with(BURNING, true), 3);
		else 
			worldIn.setBlockState(pos, BlockInit.FORGE.getDefaultState().with(FACING, state.get(FACING)).with(BURNING, false), 3);

		if(tileentity != null) 
		{
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}


	@SuppressWarnings("incomplete-switch")
	public void randomDisplayTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		if ((Boolean)stateIn.get(BURNING))
		{
			Direction enumfacing = (Direction)stateIn.get(FACING);
			double d0 = (double)pos.getX() + 0.5D;
			double d1 = (double)pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
			double d2 = (double)pos.getZ() + 0.5D;
			double d4 = rand.nextDouble() * 0.6D - 0.3D;

			if (rand.nextDouble() < 0.1D)
			{
				worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
			}

			switch (enumfacing)
			{
			case EAST:
				worldIn.addParticle(ParticleTypes.SMOKE, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
				worldIn.addParticle(ParticleTypes.FLAME, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
				break;
			case WEST:
				worldIn.addParticle(ParticleTypes.SMOKE, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
				worldIn.addParticle(ParticleTypes.FLAME, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
				break;
			case SOUTH:
				worldIn.addParticle(ParticleTypes.SMOKE, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
				worldIn.addParticle(ParticleTypes.FLAME, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
				break;
			case NORTH:
				worldIn.addParticle(ParticleTypes.SMOKE, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
				worldIn.addParticle(ParticleTypes.FLAME, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
			}
		}
	}


	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult hit) {
		if(!world.isRemote) {

			TileEntity te = world.getTileEntity(pos);

			if(te instanceof INamedContainerProvider) {
				NetworkHooks.openGui((ServerPlayerEntity)player, (INamedContainerProvider)te, te.getPos());
			}
		}
		return true;
	}

	/*
	@Nullable @Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		PlayerEntity player = context.getPlayer();

		return this.getDefaultState().with(FACING, player.getHorizontalFacing());
	}
	 */

	@Nullable @Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) 
	{
		TileEntityForge tileentity = (TileEntityForge) world.getTileEntity(pos);
		ItemStackHandler handler = tileentity.getHandler();

		for (int i = 0; i < handler.getSlots(); ++i)
		{
			ItemStack itemstack = handler.getStackInSlot(i);

			if (!itemstack.isEmpty())
			{
				spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), itemstack);
			}
		}	
		super.onReplaced(state, world, pos, newState, isMoving);
	}


	public static void spawnItemStack(World worldIn, double x, double y, double z, ItemStack stack)
	{
		Random RANDOM = new Random();

		float f = RANDOM.nextFloat() * 0.8F + 0.1F;
		float f1 = RANDOM.nextFloat() * 0.8F + 0.1F;
		float f2 = RANDOM.nextFloat() * 0.8F + 0.1F;

		while (!stack.isEmpty())
		{
			ItemEntity entityitem = new ItemEntity(worldIn, x + (double)f, y + (double)f1, z + (double)f2, stack.split(RANDOM.nextInt(21) + 10));

			entityitem.setMotion(RANDOM.nextGaussian() * 0.05000000074505806D, RANDOM.nextGaussian() * 0.05000000074505806D + 0.20000000298023224D, RANDOM.nextGaussian() * 0.05000000074505806D);
			worldIn.addEntity(entityitem);
		}
	}

	@Override
	public boolean hasComparatorInputOverride(BlockState state)
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos)
	{
		return Container.calcRedstone(worldIn.getTileEntity(pos));
	}

	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(BlockInit.FORGE);
	}

	/**
	 * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
	 * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
	 */
	@Override
	public BlockRenderType getRenderType(BlockState state)
	{
		return BlockRenderType.MODEL;
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
		builder.add(BlockStateProperties.FACING, BURNING);
	}


	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos,
			PlayerEntity player) 
	{
		return new ItemStack(new BlockItem(this,  properties));
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new TileEntityForge();
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, IFluidState fluid) {
		if (willHarvest)
			return true; // delay deletion of the block until after getDrops

		return super.removedByPlayer(state, world, pos, player, willHarvest, fluid);
	}
}