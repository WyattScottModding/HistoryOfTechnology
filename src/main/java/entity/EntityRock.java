package entity;

import handlers.EntityProjectile;
import handlers.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityRock extends EntityProjectile
{
	private int damage = 1;
	
	public final static EntityType<?> type = EntityRegistry.ROCK;
	

	public EntityRock(World worldIn)
	{
		super(type, worldIn);
		this.world = worldIn;
	}

	public EntityRock(World worldIn, LivingEntity throwerIn)
	{
		super(type, worldIn);
		this.world = worldIn;
	}

	public EntityRock(World worldIn, LivingEntity throwerIn, int damage)
	{
		super(type, worldIn);
		this.world = worldIn;
		this.damage = damage;
	}
	
	public EntityRock(World worldIn, double x, double y, double z)
	{
		super(type, worldIn);
		this.world = worldIn;
	}
	
	public EntityRock(World worldIn, double x, double y, double z, int damage)
	{
		super(type, worldIn);
		this.world = worldIn;
		this.damage = damage;
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
	public void onHit(RayTraceResult raytraceResultIn) {
		super.onHit(raytraceResultIn);
		
		if (!this.world.isRemote)
		{
			this.world.setEntityState(this, (byte)3);

			this.remove();
		}
	}
	
	@Override
	public void onHitEntity(EntityRayTraceResult entityRay) {
		Entity entity = entityRay.getEntity();
		
		if(this.getShooter() != null)
			entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getShooter()), (float)damage);
	}


}