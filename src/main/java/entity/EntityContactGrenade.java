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
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityContactGrenade extends EntityProjectile
{
	private int fuse = 100;
	private float power = 3;
	public final static EntityType<?> type = EntityRegistry.CONTACTGRENADE;
	public Entity thrower = this.getShooter();


	public EntityContactGrenade(World worldIn)
	{
		super(type, worldIn);
		this.world = worldIn;
	}

	public EntityContactGrenade(World worldIn, LivingEntity throwerIn)
	{
		super(type, worldIn);
		this.world = worldIn;
	}

	public EntityContactGrenade(World worldIn, LivingEntity throwerIn, float power)
	{
		super(type, worldIn);
		this.world = worldIn;
		this.power = power;
	}

	public EntityContactGrenade(World worldIn, double x, double y, double z)
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
			world.createExplosion(this.getEntity(), this.posX, this.posY, this.posZ, power, false, Explosion.Mode.BREAK);


			AxisAlignedBB bb1 = new AxisAlignedBB(this.posX - 3, this.posY - 3, this.posZ - 3, this.posX + 3, this.posY + 3, this.posZ + 3);
			AxisAlignedBB bb2 = new AxisAlignedBB(this.posX - 6, this.posY - 6, this.posZ - 6, this.posX + 6, this.posY + 6, this.posZ + 6);

			List<LivingEntity> entites1 = world.getEntitiesWithinAABB(LivingEntity.class, bb1);
			List<LivingEntity> entites2 = world.getEntitiesWithinAABB(LivingEntity.class, bb2);

			for(LivingEntity element : entites1)
			{
				element.addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE, 1, 4));
			}

			for(LivingEntity element : entites2)
			{
				element.addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE, 1, 3));
			}

			this.remove();;

		}

		super.tick();
	}

	@Override
	public void onHit(RayTraceResult result) 
	{	
		fuse = 0;
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