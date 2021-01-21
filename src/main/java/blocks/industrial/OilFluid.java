package blocks.industrial;

import java.util.Random;

import javax.annotation.Nullable;

import init.FluidInit;
import init.ItemInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.Item;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
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

public abstract class OilFluid extends FlowingFluid 
{
	public Fluid getFlowingFluid() {
		return FluidInit.FLOWING_OIL;
	}

	public Fluid getStillFluid() {
		return FluidInit.OIL;
	}

	/**
	 * Gets the render layer this block will render on. SOLID for solid blocks, CUTOUT or CUTOUT_MIPPED for on-off
	 * transparency (glass, reeds), TRANSLUCENT for fully blended transparency (stained glass)
	 */
	@OnlyIn(Dist.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

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

	protected boolean canSourcesMultiply() {
		return true;
	}
/*
	protected void beforeReplacingBlock(IWorld worldIn, BlockPos pos, BlockState state) {
		state.dropBlockAsItem(worldIn.getWorld(), pos, 0);
	}
*/
	public int getSlopeFindDistance(IWorldReader worldIn) {
		return 4;
	}

	public BlockState getBlockState(IFluidState state) {
		return Blocks.WATER.getDefaultState().with(FlowingFluid.LEVEL_1_8, Integer.valueOf(getLevelFromState(state)));
	}

	public boolean isEquivalentTo(Fluid fluidIn) {
		return fluidIn == FluidInit.OIL || fluidIn == FluidInit.FLOWING_OIL;
	}

	public int getLevelDecreasePerBlock(IWorldReader worldIn) {
		return 1;
	}

	public int getTickRate(IWorldReader p_205569_1_) {
		return 5;
	}

	public boolean canOtherFlowInto(IFluidState state, Fluid fluidIn, Direction direction) {
		return direction == Direction.DOWN && !fluidIn.isIn(FluidTags.WATER);
	}

	protected float getExplosionResistance() {
		return 100.0F;
	}

	public static class Flowing extends OilFluid {
		protected void fillStateContainer(StateContainer.Builder<Fluid, IFluidState> builder) {
			super.fillStateContainer(builder);
			builder.add(FlowingFluid.LEVEL_1_8);
		}

		public int getLevel(IFluidState p_207192_1_) {
			return p_207192_1_.get(FlowingFluid.LEVEL_1_8);
		}

		public boolean isSource(IFluidState state) {
			return false;
		}

		@Override
		protected void beforeReplacingBlock(IWorld worldIn, BlockPos pos, BlockState state) {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected boolean func_215665_a(IFluidState p_215665_1_, IBlockReader p_215665_2_, BlockPos p_215665_3_,
				Fluid p_215665_4_, Direction p_215665_5_) {
			// TODO Auto-generated method stub
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
}