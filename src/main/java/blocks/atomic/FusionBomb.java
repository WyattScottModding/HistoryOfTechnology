package blocks.atomic;

import entity.EntityFusionBomb;
import main.History;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class FusionBomb extends Block
{		
	public static final BooleanProperty UNSTABLE = BlockStateProperties.UNSTABLE;

	Item.Properties properties;

	public FusionBomb(String name) 
	{
		super(Block.Properties.create(Material.TNT));
		this.setRegistryName(name);
		this.setDefaultState(this.getDefaultState().with(UNSTABLE, Boolean.valueOf(false)));

		properties = new Item.Properties().maxStackSize(64).group(History.atomicTab);
	}


	public void explode(World worldIn, BlockPos pos, BlockState state, LivingEntity igniter)
	{
		if (!worldIn.isRemote)
		{
			if (((Boolean)state.get(UNSTABLE)).booleanValue())
			{
				EntityFusionBomb entityfusionbomb = new EntityFusionBomb(worldIn, (double)((float)pos.getX() + 0.5F), (double)pos.getY(), (double)((float)pos.getZ() + 0.5F), igniter);
				worldIn.addEntity(entityfusionbomb);
				worldIn.playSound((PlayerEntity)null, entityfusionbomb.posX, entityfusionbomb.posY, entityfusionbomb.posZ, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
			}
		}
	}

	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) 
	{
		if (worldIn.isBlockPowered(pos))
		{
			this.onBlockDestroyedByPlayer(worldIn, pos, state.with(UNSTABLE, Boolean.valueOf(true)));
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
		}
	}



	/**
	 * Called when a neighboring block was changed and marks that this state should perform any checks during a neighbor
	 * change. Cases may include when redstone power is updated, cactus blocks popping off due to a neighboring solid
	 * block, etc.
	 */
	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
			boolean isMoving)	{
		if (worldIn.isBlockPowered(pos))
		{
			this.onBlockDestroyedByPlayer(worldIn, pos, state.with(UNSTABLE, Boolean.valueOf(true)));
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
		}
	}

	/**
	 * Called when this Block is destroyed by an Explosion
	 */
	public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn)
	{
		if (!worldIn.isRemote)
		{
			TNTEntity entitytntprimed = new TNTEntity(worldIn, (double)((float)pos.getX() + 0.5F), (double)pos.getY(), (double)((float)pos.getZ() + 0.5F), explosionIn.getExplosivePlacedBy());
			entitytntprimed.setFuse((short)(worldIn.rand.nextInt(entitytntprimed.getFuse() / 4) + entitytntprimed.getFuse() / 8));
			worldIn.addEntity(entitytntprimed);
		}
	}

	/**
	 * Called after a player destroys this Block - the posiiton pos may no longer hold the state indicated.
	 */
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, BlockState state)
	{
		this.explode(worldIn, pos, state, (LivingEntity)null);
	}

	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand,
			BlockRayTraceResult hit) 
	{
		ItemStack itemstack = player.getHeldItem(hand);
		Item item = itemstack.getItem();

		if (item != Items.FLINT_AND_STEEL && item != Items.FIRE_CHARGE)
		{
			return super.onBlockActivated(state, worldIn, pos, player, hand, hit);
		} else {

			this.explode(worldIn, pos, state.with(UNSTABLE, Boolean.valueOf(true)), player);
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);

			if (item == Items.FLINT_AND_STEEL) {
				itemstack.damageItem(1, player, (p_220287_1_) -> {
					p_220287_1_.sendBreakAnimation(hand);
				});
			} else {
				itemstack.shrink(1);
			}

			return true;
		}
	}

	@Override
	public void onProjectileCollision(World worldIn, BlockState state, BlockRayTraceResult hit, Entity projectile) {
		if (!worldIn.isRemote && projectile instanceof AbstractArrowEntity) {
			AbstractArrowEntity abstractarrowentity = (AbstractArrowEntity)projectile;
			Entity entity = abstractarrowentity.getShooter();
			if (abstractarrowentity.isBurning()) {
				BlockPos blockpos = hit.getPos();
				explode(worldIn, blockpos, state, entity instanceof LivingEntity ? (LivingEntity)entity : null);
				worldIn.removeBlock(blockpos, false);
			}
		}

	}



	/**
	 * Return whether this block can drop from an explosion.
	 */
	public boolean canDropFromExplosion(Explosion explosionIn)
	{
		return false;
	}

	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(UNSTABLE);
	}
}