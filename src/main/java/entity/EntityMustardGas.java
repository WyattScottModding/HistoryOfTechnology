package entity;

import handlers.EntityProjectile;
import handlers.EntityRegistry;
import init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityMustardGas extends EntityProjectile
{
	private int fuse = 100;
	public final static EntityType<?> type = EntityRegistry.MUSTARDGAS;


	public EntityMustardGas(World worldIn)
	{
		super(type, worldIn);
		this.world = worldIn;
	}

	public EntityMustardGas(World worldIn, LivingEntity throwerIn)
	{
		super(type, worldIn);
		this.world = worldIn;
	}

	public EntityMustardGas(World worldIn, double x, double y, double z)
	{
		super(type, worldIn);
		this.world = worldIn;
	}

	/**
	 * Handler for {@link World#setEntityState}
	 */

	public void handleStatusUpdate(byte id)
	{
		if (id == 3)
		{
			for (int i = 0; i < 8; ++i)
			{
				this.world.addParticle(ParticleTypes.CRIT, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			}
		}
	}


	@Override
	public void tick() 
	{
		if(fuse > 0)
			fuse--;

		if(fuse == 0 && world != null && !world.isRemote)
		{
			world.createExplosion(this, this.posX, this.posY, this.posZ, 0.5F, false, Explosion.Mode.BREAK);
			
			createGas();
			
			this.remove();
		}
		super.tick();

		Block block = world.getBlockState(this.getPosition()).getBlock();
		
		if(block.getDefaultState().isSolid())
		{
			this.setVelocity(0, .1, 0);
		}	
	}
	
	public void createGas()
	{	
		for(int i = -5; i <= 5; i++)
		{
			for(int j = -5; j <= 5; j++)
			{
				for(int k = -5; k <= 5; k++)
				{
					BlockPos pos = new BlockPos(i + this.posX, j + this.posY, k + this.posZ);
					
					if(world.getBlockState(pos).getBlock() == Blocks.AIR)
					{
						world.setBlockState(pos, BlockInit.MUSTARD_GAS.getDefaultState());
					}
				}
			}
		}
	}

}