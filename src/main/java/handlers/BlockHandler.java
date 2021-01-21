package handlers;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;

public class BlockHandler 
{
	private Block block;
	private int X;
	private int Y;
	private int Z;
	
	public BlockHandler(Block block, BlockPos pos)
	{
		this.block = block;
		this.X = pos.getX();
		this.Y = pos.getY();
		this.Z = pos.getZ();
	}
	
	public Block getBlock()
	{
		return block;
	}
	
	public int getX()
	{
		return X;
	}
	
	public int getY()
	{
		return Y;
	}
	
	public int getZ()
	{
		return Z;
	}
	
	public BlockPos blockPos()
	{
		return new BlockPos(X, Y, Z);
	}
}
