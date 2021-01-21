package init;

import fluids.FluidOil;
import main.History;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.util.registry.Registry;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;

public class FluidInit 
{
	public static final FlowingFluid FLOWING_OIL = register(History.modid + ":oil_flow", new FluidOil.Flowing());
	public static final FlowingFluid OIL = register(History.modid + ":blocks/oil_still", new FluidOil.Source());

	private static <T extends Fluid> T register(String key, T p_215710_1_) {
		return (T)(Registry.register(Registry.FLUID, key, p_215710_1_));
	}

	static {
		for(Fluid fluid : Registry.FLUID) {
			for(IFluidState ifluidstate : fluid.getStateContainer().getValidStates()) {
				Fluid.STATE_REGISTRY.add(ifluidstate);
			}
		}
	}
}