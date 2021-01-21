package entity;

import javax.annotation.Nullable;

import handlers.EntityRegistry;
import init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class EntityModelT extends Entity
{
	public PlayerEntity player;
	private static final DataParameter<Float> DATA_HEALTH_ID = EntityDataManager.<Float>createKey(EntityModelT.class, DataSerializers.FLOAT);
    private static final DataParameter<Float> DAMAGE = EntityDataManager.<Float>createKey(EntityMinecart.class, DataSerializers.FLOAT);

	public double acceleration = 1.0;
	public boolean passenger = false;
	public PlayerEntity driver = null;
	public PlayerEntity rider = null;
    
	public final static EntityType<?> type = EntityRegistry.MODELT;

	public EntityModelT(World worldIn)
	{
		super(type, worldIn);
		this.setSize(1.8F, 2.8F);
		this.stepHeight = 1;
		this.world = worldIn;
	}

	public EntityModelT(World worldIn, double x, double y, double z)
	{
		super(type, worldIn);
		this.setPosition(x, y, z);
		this.getMotion().x = 0.0D;
		this.getMotion().y = 0.0D;
		this.getMotion().z = 0.0D;
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
		this.setSize(1.8F, 2.8F);
		this.stepHeight = 1;
		this.world = worldIn;
	}
	
	@Override
	protected void initEntityAI() 
	{
		super.initEntityAI();
        this.dataManager.register(DAMAGE, Float.valueOf(0.0F));
	}

	
	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.7D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(500.0D);
	}


	protected Item getDropItem()
	{
		return ItemInit.MODEL_T;
	}

	@Override
	public boolean canSpawn(IWorld worldIn, boolean p_205020_2_) {
		return true;
	}

	@Override
	public boolean processInteract(PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getHeldItemMainhand();

		this.player = player;

		this.height = 0.7F;
		this.width = 2.5F;


		if(stack != null)// && stack.getItem() == Items.SPAWN_EGG)
		{
			return super.processInteract(player, hand);
		}
		else
		{
			if(!this.isBeingRidden())
			{
				if(stack != null && stack.interactWithEntity(player, this, hand))
				{
					return true;
				}
				else
				{
					this.mountToCart(player);
					driver = player;
					return true;
				}
			}
			else if(!passenger)
			{
				this.mountToCart(player);
				passenger = true;
				rider = player;
				return true;
			}
			else
			{
				//	Minecraft.getMinecraft().gameSettings.thirdPersonView = 0;
				return super.processInteract(player, hand);
			}
		}
	}

	private void mountToCart(PlayerEntity player)
	{
		player.rotationYaw = this.rotationYaw;
		player.rotationPitch = this.rotationPitch;
		player.startRiding(this);

	}


	public boolean canBeSteered()
	{
		Entity entity = this.getControllingPassenger();
		return entity instanceof LivingEntity;	
	}

	@Override
	public void travel(float strafe, float vertical, float forward) 
	{
		if(this.isBeingRidden() && this.canBeSteered())
		{
			forward *= acceleration;
			LivingEntity entitylivingbase = (LivingEntity)this.getControllingPassenger();

			/*
			if(Keyboard.isKeyDown(Component.Identifier.Key.A))
			{
				this.rotationYaw -= 5;
				player.rotationYaw -=5;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_D))
			{
				this.rotationYaw += 5;
				player.rotationYaw +=5;
			}
			*/
			this.prevRotationYaw = this.rotationYaw;
			this.rotationPitch = entitylivingbase.rotationPitch;
			this.prevRotationPitch = this.rotationPitch * 0.5f;
			this.setRotation(this.rotationYaw, this.rotationPitch);
			this.renderYawOffset = this.rotationYaw;
			this.rotationYawHead = this.renderYawOffset;
			strafe = 0;
			forward = entitylivingbase.moveForward;

			if(forward <= 0.0f)
			{
				forward *= 0.25f;
			}

			this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1f;

			if(this.canPassengerSteer())
			{
				this.setAIMoveSpeed((float)this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
				super.travel(strafe, vertical, forward);
			}
			else if(entitylivingbase instanceof PlayerEntity)
			{
				this.getMotion().x = 0.0d;
				this.getMotion().y = 0.0d;
				this.getMotion().z = 0.0d;
			}

			this.prevLimbSwingAmount = this.limbSwingAmount;
			double d1 = this.posX - this.prevPosX;
			double d0 = this.posZ - this.prevPosZ;
			float f2 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0f;

			if(f2 > 1.0f)
			{
				f2 = 1.0f;
			}

			this.limbSwingAmount += (f2 - this.limbSwingAmount) * 0.4f;
			this.limbSwing += this.limbSwingAmount;
		}
		else
		{
			this.jumpMovementFactor = 0.02f;
			super.travel(strafe, vertical, forward);
		}
	}

	@Nullable
	public Entity getControllingPassenger()
	{
		return this.getPassengers().isEmpty() ? null : (Entity)this.getPassengers().get(0);
	}


	@Override
	protected boolean isMovementBlocked()
	{
		return false;
	}

	@Override
	public boolean canRiderInteract() 
	{
		return true;
	}

	@Override
	public boolean shouldRiderSit() 
	{
		return true;
	}

	@Override
	protected boolean canBeRidden(Entity entityIn) 
	{
		return true;
	}

	@Override
	public boolean isAIDisabled()
	{
		return false;
	}

	@Override
	public boolean canDespawn()
	{
		return false;
	}

	@Override
	public boolean canBePushed() 
	{
		return false;
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return this.isAlive();
	}


	@Override
	public void tick() 
	{
		/*
		if(Keyboard.isKeyDown(Keyboard.KEY_W) && acceleration < 10 && player != null)
			acceleration += 0.1;
		else
			acceleration = 1.0;

		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && player != null)
		{
			dismountEntity(player);
			player.dismountRidingEntity();
			player.attemptTeleport(player.posX - 1, player.posY, player.posZ - 1);
			this.attemptTeleport(this.posX, this.posY - 1, this.posZ);
			player = null;
		}
		*/

		if(getAttackingEntity() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) getAttackingEntity();

			if(!player.isCreative())
				this.entityDropItem(ItemInit.MODEL_T, 1);
			
			this.remove();
		}

		super.tick();
	}

	public void dismountEntity(Entity entityIn)
	{
		double d1 = entityIn.posX;
		double d13 = entityIn.getBoundingBox().minY + (double)entityIn.height;
		double d14 = entityIn.posZ;
		Direction enumfacing1 = entityIn.getAdjustedHorizontalFacing();

		if (enumfacing1 != null)
		{
			Direction enumfacing = enumfacing1.rotateY();
			int[][] aint1 = new int[][] {{0, 1}, {0, -1}, { -1, 1}, { -1, -1}, {1, 1}, {1, -1}, { -1, 0}, {1, 0}, {0, 1}};
			double d5 = Math.floor(this.posX) + 0.5D;
			double d6 = Math.floor(this.posZ) + 0.5D;
			double d7 = this.getBoundingBox().maxX - this.getBoundingBox().minX;
			double d8 = this.getBoundingBox().maxZ - this.getBoundingBox().minZ;
			AxisAlignedBB axisalignedbb = new AxisAlignedBB(d5 - d7 / 2.0D, entityIn.getBoundingBox().minY, d6 - d8 / 2.0D, d5 + d7 / 2.0D, Math.floor(entityIn.getBoundingBox().minY) + (double)this.height, d6 + d8 / 2.0D);

			for (int[] aint : aint1)
			{
				double d9 = (double)(enumfacing1.getXOffset() * aint[0] + enumfacing.getXOffset() * aint[1]);
				double d10 = (double)(enumfacing1.getZOffset() * aint[0] + enumfacing.getZOffset() * aint[1]);
				double d11 = d5 + d9;
				double d12 = d6 + d10;
				AxisAlignedBB axisalignedbb1 = axisalignedbb.offset(d9, 0.0D, d10);

				if (!this.world.checkBlockCollision(axisalignedbb1))
				{
					if (this.world.getBlockState(new BlockPos(d11, this.posY, d12)).isSolid())//(world, new BlockPos(d11, this.posY, d12), Direction.UP))
					{
						this.setPositionAndUpdate(d11, this.posY + 1.0D, d12);
						return;
					}

					BlockPos blockpos = new BlockPos(d11, this.posY - 1.0D, d12);

					if (this.world.getBlockState(blockpos).isSolid())//(world, blockpos, Direction.UP) || this.world.getBlockState(blockpos).getMaterial() == Material.WATER)
					{
						d1 = d11;
						d13 = this.posY + 1.0D;
						d14 = d12;
					}
				}
				else if (!this.world.checkBlockCollision(axisalignedbb1.offset(0.0D, 1.0D, 0.0D)) && this.world.getBlockState(new BlockPos(d11, this.posY + 1.0D, d12)).isSolid())//.isSideSolid(world, new BlockPos(d11, this.posY + 1.0D, d12), Direction.UP))
				{
					d1 = d11;
					d13 = this.posY + 2.0D;
					d14 = d12;
				}
			}
		}

		this.setPositionAndUpdate(d1, d13, d14);
	}

	@Override
	public void enablePersistence() 
	{
		super.enablePersistence();
	}

	@Override
	public void notifyDataManagerChange(DataParameter<?> key)
	{
		super.notifyDataManagerChange(key);
	}
	
    /**
     * Gets the current amount of damage the minecart has taken. Decreases over time. The cart breaks when this is over
     * 40.
     */
    public float getDamage()
    {
        return ((Float)this.dataManager.get(DAMAGE)).floatValue();
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