package entity;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import handlers.EntityRegistry;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityTurret extends CreatureEntity implements IRangedAttackMob
{
	/** The entity (as a RangedAttackMob) the AI instance has been applied to. */
	private LivingEntity attackTarget;
	public final EntityAITasks targetTasks;
	public BlockPos currentAttachmentPosition = null;
	public Entity thrower = this.getShooter();

    protected static final DataParameter<Direction> ATTACHED_FACE = EntityDataManager.<Direction>createKey(EntityTurret.class, DataSerializers.FACING);


	/**a
	 * A decrementing tick that spawns a ranged attack once this value reaches 0. It is then set back to the
	 * maxRangedAttackTime.
	 */
	private int rangedAttackTime;
	private final int attackInterval;
	private final float maxAttackDistance;

	private World world;
	public final static EntityType<?> type = EntityRegistry.TURRET;

	public EntityTurret(World world)
	{
		super(type, world);
		this.world = world;
        this.setSize(1.0F, 1.0F);
        this.prevRenderYawOffset = 180.0F;
        this.renderYawOffset = 180.0F;
        
		this.attackInterval = 5;
		this.maxAttackDistance = 30;
		this.isImmuneToFire = true;


		this.targetTasks = new EntityAITasks(world != null && world.profiler != null ? world.profiler : null);

	}

	public EntityTurret(World world, int x, int y, int z)
	{
		super(type, world);
		this.world = world;
		this.setPosition(x, y, z);
		this.currentAttachmentPosition = new BlockPos(x,y,z);
		this.isImmuneToFire = true;
		
        this.setSize(1.0F, 1.0F);
        this.prevRenderYawOffset = 180.0F;
        this.renderYawOffset = 180.0F;

		this.attackInterval = 5;
		this.maxAttackDistance = 30;

		this.targetTasks = new EntityAITasks(world != null && world.profiler != null ? world.profiler : null);
	}

	/**
	 * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
	 * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
	 */
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData entityLivingData,
			NBTTagCompound itemNbt) {
		this.renderYawOffset = 180.0F;
		this.prevRenderYawOffset = 180.0F;
		this.rotationYaw = 180.0F;
		this.prevRotationYaw = 180.0F;
		this.rotationYawHead = 180.0F;
		this.prevRotationYawHead = 180.0F;
		return super.onInitialSpawn(difficulty, entityLivingData, itemNbt);
	}
	
	 /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    protected void registerAttributes() {
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
        super.registerAttributes();
    }
    

	@Override
	public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) 
	{
	}

	@Override
	public void setSwingingArms(boolean swingingArms) 
	{
	}

	
	@Override
	protected void initEntityAI() 
	{
		super.initEntityAI();
		
		this.tasks.addTask(1, new EntityAIWatchClosest(this, PlayerEntity.class, 8.0F));
		this.tasks.addTask(4, new EntityTurret.AIAttack());
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
		this.targetTasks.addTask(2, new EntityTurret.AIAttackNearest(this));
		this.targetTasks.addTask(3, new EntityTurret.AIDefenseAttack(this));
		
        this.dataManager.register(ATTACHED_FACE, Direction.DOWN);
	}


	public void idle()
	{
		this.rotationPitch = 0;

		if(this.rotationYaw > 0 && !world.isRemote && world.getGameTime() % 5 == 0)
			this.rotationYaw--;
	}


	public LivingEntity findEntity()
	{
		BlockPos pos = this.getPosition();

		float yaw = this.rotationYaw;
		float pitch = this.rotationPitch;

		for(int f = 1; f <= 40; f++)
		{
			double x = (double)(-MathHelper.sin(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);
			double y = (double)(-MathHelper.sin((pitch) / 180.0F * (float)Math.PI) * f);
			double z = (double)(MathHelper.cos(yaw / 180.0F * (float)Math.PI) * MathHelper.cos(pitch / 180.0F * (float)Math.PI) * f);


			AxisAlignedBB entityPos = new AxisAlignedBB(pos.getX() + x, pos.getY() + y, pos.getZ() + z, pos.getX() + x + 1, pos.getY() + y + 1, pos.getZ() + z + 1);
			List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(this, entityPos);


			for(int j = 0; j < list.size(); ++j)
			{
				Entity entity = list.get(j);

				if(entity instanceof LivingEntity)
				{
					return (LivingEntity) entity;
				}
			}
		}
		return null;
	}


	class AIAttack extends EntityAIBase
	{
		private int attackTime;

		public AIAttack()
		{
			this.setMutexBits(3);
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		public boolean shouldExecute()
		{
			LivingEntity entitylivingbase = EntityTurret.this.getAttackTarget();

			if (entitylivingbase != null && entitylivingbase.isAlive())
			{
				return EntityTurret.this.world.getDifficulty() != EnumDifficulty.PEACEFUL;
			}
			else
			{
				return false;
			}
		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		public void startExecuting()
		{
			this.attackTime = 20;
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		@Override
		public void tick()
		{
			if (EntityTurret.this.world.getDifficulty() != EnumDifficulty.PEACEFUL)
			{
				--this.attackTime;
				LivingEntity entitylivingbase = EntityTurret.this.getAttackTarget();
				EntityTurret.this.getLookHelper().setLookPositionWithEntity(entitylivingbase, 180.0F, 180.0F);
				double d0 = EntityTurret.this.getDistanceSq(entitylivingbase);

				if (d0 < 400.0D)
				{
					if (this.attackTime <= 0)
					{
						this.attackTime = 20 + EntityTurret.this.rand.nextInt(10) * 20 / 2;
						EntityShulkerBullet entityshulkerbullet = new EntityShulkerBullet(EntityTurret.this.world, EntityTurret.this, entitylivingbase, EntityTurret.this.getAttachmentFacing().getAxis());
						EntityTurret.this.world.addEntity(entityshulkerbullet);
						EntityTurret.this.playSound(SoundEvents.ENTITY_SHULKER_SHOOT, 2.0F, (EntityTurret.this.rand.nextFloat() - EntityTurret.this.rand.nextFloat()) * 0.2F + 1.0F);
					}
				}
				else
				{
					EntityTurret.this.setAttackTarget((LivingEntity)null);
				}

				super.tick();
			}
		}
	}

	class AIAttackNearest extends EntityAINearestAttackableTarget<PlayerEntity>
	{
		public AIAttackNearest(EntityTurret turret)
		{
			super(turret, PlayerEntity.class, true);
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		public boolean shouldExecute()
		{
			return EntityTurret.this.world.getDifficulty() == EnumDifficulty.PEACEFUL ? false : super.shouldExecute();
		}

		protected AxisAlignedBB getTargetableArea(double targetDistance)
		{
			Direction enumfacing = ((EntityTurret)this.taskOwner).getAttachmentFacing();

			if (enumfacing.getAxis() == Direction.Axis.X)
			{
				return this.taskOwner.getBoundingBox().grow(4.0D, targetDistance, targetDistance);
			}
			else
			{
				return enumfacing.getAxis() == Direction.Axis.Z ? this.taskOwner.getBoundingBox().grow(targetDistance, targetDistance, 4.0D) : this.taskOwner.getBoundingBox().grow(targetDistance, 4.0D, targetDistance);
			}
		}
	}

	static class AIDefenseAttack extends EntityAINearestAttackableTarget<LivingEntity>
	{
		public AIDefenseAttack(EntityTurret turret)
		{
			super(turret, LivingEntity.class, 10, true, false, new Predicate<LivingEntity>()
			{
				public boolean apply(@Nullable LivingEntity p_apply_1_)
				{
					return p_apply_1_ instanceof IMob;
				}
			});
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		public boolean shouldExecute()
		{
			return this.taskOwner.getTeam() == null ? false : super.shouldExecute();
		}

		protected AxisAlignedBB getTargetableArea(double targetDistance)
		{
			Direction enumfacing = ((EntityTurret)this.taskOwner).getAttachmentFacing();

			if (enumfacing.getAxis() == Direction.Axis.X)
			{
				return this.taskOwner.getBoundingBox().grow(4.0D, targetDistance, targetDistance);
			}
			else
			{
				return enumfacing.getAxis() == Direction.Axis.Z ? this.taskOwner.getBoundingBox().grow(targetDistance, targetDistance, 4.0D) : this.taskOwner.getBoundingBox().grow(targetDistance, 4.0D, targetDistance);
			}
		}
	}

	public Direction getAttachmentFacing()
	{
		return (Direction)this.dataManager.get(ATTACHED_FACE);
	}

}