package entity;

import java.util.List;

import javax.annotation.Nullable;

import handlers.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityFissionBomb extends Entity
{
	private static final DataParameter<Integer> FUSE = EntityDataManager.<Integer>createKey(EntityFissionBomb.class, DataSerializers.VARINT);
	@Nullable
	private LivingEntity tntPlacedBy;
	/** How long the fuse is */
	private int fuse;
	public final static EntityType<?> type = EntityRegistry.FISSIONBOMB;

	public EntityFissionBomb(World worldIn)
	{
		super(type, worldIn);
		this.fuse = 200;
		this.preventEntitySpawning = true;
		//this.setSize(0.98F, 0.98F);
	}

	public EntityFissionBomb(World worldIn, double x, double y, double z, LivingEntity igniter)
	{
		this(worldIn);
		this.setPosition(x, y, z);
		float f = (float)(Math.random() * (Math.PI * 2D));
		this.setMotion((double)(-((float)Math.sin((double)f)) * 0.02F), 0.20000000298023224D, (double)(-((float)Math.cos((double)f)) * 0.02F));
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
		this.tntPlacedBy = igniter;
		this.fuse = 200;
		this.setFuse(fuse);
	}

	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
	 * prevent them from trampling crops
	 */
	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}
	

	/**
	 * Returns true if other Entities should be prevented from moving through this Entity.
	 */
	@Override
	public boolean canBeCollidedWith()
	{
		return this.isAlive();
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void tick()
	{
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		
        Vec3d vec3d = this.getMotion();
        double dx = vec3d.x;
        double dy = vec3d.y;
        double dz = vec3d.z;

		if (!this.hasNoGravity())
		{
			dy -= 0.03999999910593033D;
		}

		this.move(MoverType.SELF, this.getPositionVec());
		dx *= 0.9800000190734863D;
		dy *= 0.9800000190734863D;
		dz *= 0.9800000190734863D;

		if (this.onGround)
		{
			dx *= 0.699999988079071D;
			dz *= 0.699999988079071D;
			dy *= -0.5D;
		}

		--this.fuse;

		if (this.fuse <= 0)
		{
			this.remove();

			if(!world.isRemote)
				this.explode();

		}
		else
		{
			this.handleWaterMovement();
			this.world.addParticle(ParticleTypes.LARGE_SMOKE, this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
		}
	}

	private void explode()
	{
		//Hurt Mobs around it
		int radius2 = (int) (160F / 4);

		BlockPos pos1 = new BlockPos(this.posX - radius2, this.posY - radius2, this.posZ - radius2);
		BlockPos pos2 = new BlockPos(this.posX + radius2, this.posY + radius2, this.posZ + radius2);

		AxisAlignedBB bb = new AxisAlignedBB(pos1, pos2);
		List<LivingEntity> list = world.getEntitiesWithinAABB(LivingEntity.class, bb);


		//Create Explosion
		Explosion explosion = world.createExplosion(this.tntPlacedBy, this.posX, this.posY, this.posZ, 160F, true, Explosion.Mode.BREAK);
		world.createExplosion(this.tntPlacedBy, this.posX + 10, this.posY, this.posZ, 40F, true, Explosion.Mode.BREAK);
		world.createExplosion(this.tntPlacedBy, this.posX - 10, this.posY, this.posZ, 40F, true, Explosion.Mode.BREAK);
		world.createExplosion(this.tntPlacedBy, this.posX, this.posY, this.posZ + 10, 40F, true, Explosion.Mode.BREAK);
		world.createExplosion(this.tntPlacedBy, this.posX, this.posY, this.posZ - 10, 40F, true, Explosion.Mode.BREAK);

		for(LivingEntity element : list)
		{
			float distance = this.getDistance(element);

			element.attackEntityFrom(DamageSource.causeExplosionDamage(explosion), 160F - (distance * 4));
		}	
	}

	/**
	 * returns null or the entityliving it was placed or ignited by
	 */
	@Nullable
	public LivingEntity getTntPlacedBy()
	{
		return this.tntPlacedBy;
	}

	public void setFuse(int fuseIn)
	{
		this.dataManager.set(FUSE, Integer.valueOf(fuseIn));
		this.fuse = fuseIn;
	}

	@Override
	public void notifyDataManagerChange(DataParameter<?> key)
	{
		if (FUSE.equals(key))
		{
			this.fuse = this.getFuseDataManager();
		}
	}

	/**
	 * Gets the fuse from the data manager
	 */
	public int getFuseDataManager()
	{
		return ((Integer)this.dataManager.get(FUSE)).intValue();
	}

	public int getFuse()
	{
		return this.fuse;
	}

	@Override
	protected void registerData() 
	{
		this.dataManager.register(FUSE, 200);
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