package fluids;

import java.util.Random;

import javax.annotation.Nullable;

import init.BlockInit;
import init.FluidInit;
import init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.Item;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FluidOil extends FlowingFluid
{
	@Override
	public Fluid getFlowingFluid() {
		return FluidInit.FLOWING_OIL;
	}

	@Override
	public Fluid getStillFluid() {
		return FluidInit.OIL;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.SOLID;
	}

	@Override
	public Item getFilledBucket() {
		return ItemInit.OIL_BUCKET;
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(World worldIn, BlockPos pos, IFluidState state, Random random) {
		if (!state.isSource() && !state.get(FALLING)) {
			if (random.nextInt(64) == 0) {
				worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_WATER_AMBIENT, SoundCategory.BLOCKS, random.nextFloat() * 0.25F + 0.75F, random.nextFloat() + 0.5F, false);
			}
		} else if (random.nextInt(10) == 0) {
			worldIn.addParticle(ParticleTypes.UNDERWATER, (double)((float)pos.getX() + random.nextFloat()), (double)((float)pos.getY() + random.nextFloat()), (double)((float)pos.getZ() + random.nextFloat()), 0.0D, 0.0D, 0.0D);
		}
	}

	@Nullable
	@OnlyIn(Dist.CLIENT)
	public IParticleData getDripParticleData() {
		return ParticleTypes.DRIPPING_WATER;
	}

	@Override
	protected boolean canSourcesMultiply() {
		return false;
	}

	@Override
	protected void beforeReplacingBlock(IWorld worldIn, BlockPos pos, BlockState state) {
		TileEntity tileentity = state.getBlock().hasTileEntity() ? worldIn.getTileEntity(pos) : null;
		Block.spawnDrops(state, worldIn.getWorld(), pos, tileentity);
	}

	@Override
	protected int getSlopeFindDistance(IWorldReader worldIn) {
		return 4;
	}

	@Override
	protected BlockState getBlockState(IFluidState state) {
		return BlockInit.OIL_BLOCK.getDefaultState().with(FlowingFluidBlock.LEVEL, Integer.valueOf(getLevelFromState(state)));
	}

	public boolean isEquivalentTo(Fluid fluidIn) {
		return fluidIn == FluidInit.OIL || fluidIn == FluidInit.FLOWING_OIL;
	}

	@Override
	protected int getLevelDecreasePerBlock(IWorldReader worldIn) {
		return 2;
	}

	@Override
	protected boolean func_215665_a(IFluidState state, IBlockReader reader, BlockPos pos,
			Fluid fluid, Direction direction) {
		return direction == Direction.DOWN && !fluid.isIn(FluidTags.WATER);
	}

	@Override
	protected float getExplosionResistance() {
		return 50.0F;
	}

	public static class Flowing extends WaterFluid {
		protected void fillStateContainer(StateContainer.Builder<Fluid, IFluidState> builder) {
			super.fillStateContainer(builder);
			builder.add(LEVEL_1_8);
		}

		public int getLevel(IFluidState p_207192_1_) {
			return p_207192_1_.get(LEVEL_1_8);
		}

		public boolean isSource(IFluidState state) {
			return false;
		}
	}

	public static class Source extends WaterFluid {
		public int getLevel(IFluidState p_207192_1_) {
			return 8;
		}

		public boolean isSource(IFluidState state) {
			return true;
		}
	}

	@Override
	public int getTickRate(IWorldReader p_205569_1_) {
		return 15;
	}

	@Override
	public boolean isSource(IFluidState state) {
		return false;
	}

	@Override
	public int getLevel(IFluidState state) {
		return 0;
	}
}