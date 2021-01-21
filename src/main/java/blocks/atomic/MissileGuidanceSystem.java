package blocks.atomic;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.ItemStackHandler;
import tileEntities.TileEntityMissileGuidanceSystem;

public class MissileGuidanceSystem extends ContainerBlock
{
	Item.Properties properties;

	public MissileGuidanceSystem(String name)
	{
		super(Block.Properties.create(Material.IRON).sound(SoundType.STONE).hardnessAndResistance(2.5F, 16));
		this.setRegistryName(name);

		properties = new Item.Properties().maxStackSize(64);
	}

	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return false;
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
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) 
	{
		return new ItemStack(this);
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) 
	{
		return new TileEntityMissileGuidanceSystem();
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
	 protected Stat<ResourceLocation> getOpenStat() {
	      return Stats.CUSTOM.get(Stats.OPEN_CHEST);
	   }
	 */

	@Override
	public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) 
	{
		TileEntityMissileGuidanceSystem tileentity = (TileEntityMissileGuidanceSystem) world.getTileEntity(pos);
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
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos,
			PlayerEntity player) 
	{
		return new ItemStack(new BlockItem(this,  properties));
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
	
	/*
	public static class BlockMissileGuidanceSystem implements IInteractionObject {
		private final World world;
		private final BlockPos position;

		public BlockMissileGuidanceSystem(World worldIn, BlockPos pos) {
			this.world = worldIn;
			this.position = pos;
		}

		public ITextComponent getName() {
			return new TextComponentTranslation(BlockInit.MISSILE_GUIDANCE.getTranslationKey());
		}

		public boolean hasCustomName() {
			return false;
		}

		@Nullable
		public ITextComponent getCustomName() {
			return null;
		}

		public Container createContainer(InventoryPlayer playerInventory, PlayerEntity playerIn) {
			return new ContainerRepair(playerInventory, this.world, this.position, playerIn);
		}

		public String getGuiID() {
			return "history:missile_guidance";
		}
	}
	 */
}
