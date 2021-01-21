package entity;

import java.util.List;

import handlers.EntityProjectile;
import handlers.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityEnergy extends EntityProjectile
{
	private int timer = 90;
	public final static EntityType<?> type = EntityRegistry.ENERGY;
	public Entity thrower = this.getShooter();


	public EntityEnergy(World worldIn)
	{
		super(type, worldIn);
		this.world = worldIn;
	}

	public EntityEnergy(World worldIn, LivingEntity throwerIn)
	{
		super(type, worldIn);
		this.world = worldIn;
		
		thrower = throwerIn;
	}

	public EntityEnergy(World worldIn, LivingEntity throwerIn, double x, double y, double z)
	{
		super(type, worldIn);
		this.world = worldIn;

		this.posX = x;
		this.posY = y;
		this.posZ = z;
		
		thrower = throwerIn;
	}

	public EntityEnergy(World worldIn, double x, double y, double z)
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
	public void onHitEntity(EntityRayTraceResult entityRay) {
		if(entityRay.getEntity() instanceof LivingEntity)
		{
			LivingEntity entity = (LivingEntity) entityRay.getEntity();

			if(thrower != null && entity != thrower)
			{
				LightningBoltEntity lightning = new LightningBoltEntity(world, this.posX, this.posY, this.posZ, false);
				world.addEntity(lightning);

				world.playSound((PlayerEntity)null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, SoundCategory.NEUTRAL, 0.5F, 1.0F);


				List<Entity> entityList = findEntities(thrower, world);

				for(Entity element : entityList)
				{
					if(element instanceof LivingEntity)
					{
						BlockPos pos1 = entity.getPosition();
						BlockPos pos2 = element.getPosition();


						BlockPos pos3 = pos2.subtract(pos1);

						double xDiff = pos3.getX() - this.posX;
						double yDiff = pos3.getY() - this.posY;
						double zDiff = pos3.getZ() - this.posZ;

						double magnitude = Math.sqrt(xDiff*xDiff + yDiff*yDiff + zDiff*zDiff);

						double x = xDiff / magnitude;
						double y = yDiff / magnitude;
						double z = zDiff / magnitude;

						EntityEnergy entityenergy = new EntityEnergy(world);
						entityenergy.shoot(x, y, z, 2, 0);

						//entityenergy.getMotion().x += thrower.getMotion().x;
						//entityenergy.getMotion().z += thrower.getMotion().z;

					//	if (!thrower.onGround)
					//	{
							//entityenergy.getMotion().y += thrower.getMotion().y;
					//	}

						world.addEntity(entityenergy);					
					}
				}

				entity.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 10F);

				/*
				for(int i = 0; i < entityList.size(); i++)
				{
					lightning = new EntityLightningBolt(world, entityList.get(i).posX, entityList.get(i).posY, entityList.get(i).posZ, false);
					world.addEntity(lightning);
				}
				 */
				this.remove();
			}
		}
	}


	@Override
	public void tick() 
	{
		if(timer == 0)
			this.remove();
		if(this.inGround)
			this.remove();

		if (timer > 0 && this.world.isRemote && !this.inGround)
		{
			world.addParticle(ParticleTypes.CRIT, this.posX + (rand.nextDouble() - 0.5D) * (double)this.getWidth(), this.posY + rand.nextDouble() * (double)this.getHeight() - (double)this.getYOffset(), this.posZ + (rand.nextDouble() - 0.5D) * (double)this.getWidth(), 0.0D, 0.0D, 0.0D);

			timer--;
		}

		super.tick();		
	}



	public List<Entity> findEntities(Entity entity, World world)
	{
		AxisAlignedBB entityPos = new AxisAlignedBB(this.posX + 3, this.posY + 3, this.posZ + 3, this.posX - 3, this.posY - 3, this.posZ - 3);

		List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(entity, entityPos);

		return list;
	}


}