package entity;

import handlers.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;


public class EntityBeam extends Entity
{
	int lifetime = 40;

	public EntityBeam(World worldIn)
	{
		super(EntityRegistry.BEAM, worldIn);
	}
	
	public EntityBeam(EntityType<?> type, World worldIn)
	{
		super(type, worldIn);
	}

	public EntityBeam(EntityType<?> type, World worldIn, double x, double y, double z)
	{
		super(type, worldIn);

		this.setPosition(x, y, z);
	}

	/**
	 * Handler for {@link World#setEntityState}
	 */
	@Override
	public void handleStatusUpdate(byte id)
	{
		if (id == 3)
		{
			for (int i = 0; i < 8; ++i)
			{
				this.world.addParticle(ParticleTypes.END_ROD, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	/**
	 * Called when this Entity hits a block or entity.
	 */
	protected void onImpact(RayTraceResult result)
	{
	}



	@Override
	public void tick()
	{
		if(lifetime > 0)
			lifetime--;
		else
			this.remove();


		super.tick();
	}

	@Override
	protected void registerData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void readAdditional(CompoundNBT compound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeAdditional(CompoundNBT compound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		// TODO Auto-generated method stub
		return null;
	}


}