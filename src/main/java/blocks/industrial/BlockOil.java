package blocks.industrial;

import init.FluidInit;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;

public class BlockOil extends FlowingFluidBlock {
		
	public BlockOil(String name) {
		super(FluidInit.OIL, Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(50.0F).noDrops());
		this.setRegistryName(name);
	}
	
	
}