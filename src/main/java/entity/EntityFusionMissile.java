package entity;

import java.util.List;

import handlers.EntityProjectile;
import handlers.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityFusionMissile extends EntityProjectile
{
	private float power = 800F;
	public final static EntityType<?> type = EntityRegistry.FUSIONMISSILE;
	public Entity thrower = this.getShooter();


	public EntityFusionMissile(World worldIn)
	{
		super(type, worldIn);
		this.world = worldIn;
	}

	public EntityFusionMissile(World worldIn, LivingEntity throwerIn)
	{
		super(type, worldIn);
		this.world = worldIn;
	}

	public EntityFusionMissile(World worldIn, LivingEntity throwerIn, float power)
	{
		super(type, worldIn);
		this.world = worldIn;
		this.power = power;
	}
	
	public EntityFusionMissile(World worldIn, double x, double y, double z)
	{
		super(type, worldIn);
		this.world = worldIn;
	}

	public EntityFusionMissile(World worldIn, double x, double y, double z, float power)
	{
		super(type, worldIn);
		this.world = worldIn;
		this.power = power;
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
				this.world.addParticle(ParticleTypes.LARGE_SMOKE, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			}
		}
	}


	@Override
	public void tick() 
	{	
		this.setNoGravity(true);

		super.tick();
	}


	@Override
	public void onHit(RayTraceResult result) 
	{
		if(world != null && !world.isRemote)
		{
			//Hurt Mobs around it
			int radius2 = (int) (power / 4);
			
			BlockPos pos1 = new BlockPos(this.posX - radius2, this.posY - radius2, this.posZ - radius2);
			BlockPos pos2 = new BlockPos(this.posX + radius2, this.posY + radius2, this.posZ + radius2);

			AxisAlignedBB bb = new AxisAlignedBB(pos1, pos2);
			List<LivingEntity> list = world.getEntitiesWithinAABB(LivingEntity.class, bb);


			//Create Explosion
			Explosion explosion = world.createExplosion(this.thrower, this.posX, this.posY, this.posZ, power, true, Explosion.Mode.BREAK);
			world.createExplosion(this.thrower, this.posX + 10, this.posY, this.posZ, power / 4, true, Explosion.Mode.BREAK);
			world.createExplosion(this.thrower, this.posX - 10, this.posY, this.posZ, power / 4, true, Explosion.Mode.BREAK);
			world.createExplosion(this.thrower, this.posX, this.posY, this.posZ + 10, power / 4, true, Explosion.Mode.BREAK);
			world.createExplosion(this.thrower, this.posX, this.posY, this.posZ - 10, power / 4, true, Explosion.Mode.BREAK);

			for(LivingEntity element : list)
			{
				float distance = this.getDistance(element);

				element.attackEntityFrom(DamageSource.causeExplosionDamage(explosion), power - (distance * 4));
			}


			this.remove();
		}
	}
	
	public float getPower()
	{
		return power;
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