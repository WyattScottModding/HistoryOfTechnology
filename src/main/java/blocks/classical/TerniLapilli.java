package blocks.classical;

import java.util.Random;

import main.History;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.ItemStackHandler;
import tileEntities.TileEntityForge;
import tileEntities.TileEntityTerniLapilli;

public class TerniLapilli extends ContainerBlock
{
	protected static final VoxelShape BOARD = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.88D, 16.0D);
	Item.Properties properties;

	public TerniLapilli(String name)
	{
		super(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5F, 10F).sound(SoundType.WOOD));
		this.setDefaultState(this.getDefaultState());

		this.setRegistryName(name);
		properties = new Item.Properties().group(History.classicalTab);

	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return BOARD;
	}

	@Override
	public boolean isNormalCube(BlockState state, IBlockReader world, BlockPos pos) 
	{
		return false;
	}

	@Override
	public boolean isSolid(BlockState state) 
	{
		return false;
	}

	@Override
	public BlockRenderLayer getRenderLayer()
	{	
		return BlockRenderLayer.CUTOUT;
	}


	@Override
	public BlockRenderType getRenderType(BlockState state) 
	{
		return BlockRenderType.MODEL;
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) 
	{
		return new TileEntityTerniLapilli();
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

	@Override
	public void dropXpOnBlockBreak(World world, BlockPos pos, int amount)
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
		super.dropXpOnBlockBreak(world, pos, amount);
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
	public boolean hasTileEntity() {
		return true;
	}
	/*
	public static class BlockTerniLapilli implements IInteractionObject {
		private final World world;
		private final BlockPos position;

		public BlockTerniLapilli(World worldIn, BlockPos pos) {
			this.world = worldIn;
			this.position = pos;
		}

		public ITextComponent getName() {
			return new TextComponentTranslation(BlockInit.TERNI_LAPILLI.getTranslationKey());
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
			return "history:terni_lapilli";
		}
	}
	 */
}