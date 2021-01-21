package entity;

import java.util.List;

import handlers.EntityProjectile;
import handlers.EntityRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EntityFragGrenade extends EntityProjectile
{
	private int fuse = 100;
	private float power = 3;
	public final static EntityType<?> type = EntityRegistry.FRAGGRENADE;

	private static final DataParameter<Integer> FUSE = EntityDataManager.createKey(EntityFragGrenade.class, DataSerializers.VARINT);


	public EntityFragGrenade(World worldIn)
	{
		super(type, worldIn);
		this.world = worldIn;
	}


	/**
	 * Handler for {@link World#setEntityState}
	 */
	@OnlyIn(Dist.CLIENT) @Override
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
	public boolean canBeCollidedWith()
	{
		return this.isAlive();
	}


	@Override
	public boolean canBePushed() 
	{
		return true;
	}

	@Override
	public void tick() 
	{
		if(fuse > 0)
			fuse--;

		Vec3d vec3d = this.getMotion();
		double dx = vec3d.x;
		double dy = vec3d.y;
		double dz = vec3d.z;

		if (this.onGround)
		{
			dx *= 0.699999988079071D;
			dz *= 0.699999988079071D;
			dy *= -0.5D;
		}

		this.posX += dx;
		this.posY += dy;
		this.posZ += dz;


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


			this.remove();
		}

		/*
		Block block = world.getBlockState(this.getPosition()).getBlock();
		Block blockDown = world.getBlockState(this.getPosition().down()).getBlock();
		Block blockUp = world.getBlockState(this.getPosition().up()).getBlock();

		if(block != Blocks.AIR && block != BlockInit.MUSTARDGASS_BLOCK) 
		{
			if(blockUp == Blocks.AIR && blockUp == BlockInit.MUSTARDGASS_BLOCK)
				this.setVelocity(0, 0.1, 0);
			if(blockUp != Blocks.AIR && blockUp != BlockInit.MUSTARDGASS_BLOCK)
				this.setVelocity(0, 0.0, 0);
		}
		 */

		super.tick();
	}


	@Override
	protected void registerData() {
	      this.dataManager.register(FUSE, 100);

	}


	@Override
	protected void readAdditional(CompoundNBT compound) {
		this.setFuse(compound.getShort("Fuse"));
	}


	@Override
	protected void writeAdditional(CompoundNBT compound) {
		compound.putShort("Fuse", (short)this.getFuse());		
	}


	@Override
	public IPacket<?> createSpawnPacket() {
	      return new SSpawnObjectPacket(this);
	}


	public void setFuse(int fuseIn) {
		this.dataManager.set(FUSE, fuseIn);
		this.fuse = fuseIn;
	}

	public int getFuse() {
		return this.fuse;
	}


	/*
	protected void onImpact(RayTraceResult result) 
	{	
		ArrayList<Block> blocks = new ArrayList<Block>();

		blocks.add(Blocks.TALL_GRASS);
		blocks.add(Blocks.TALL_SEAGRASS);
		blocks.add(Blocks.BROWN_MUSHROOM);
		blocks.add(Blocks.RED_MUSHROOM);

		blocks.add(Blocks.BLACK_BED);
		blocks.add(Blocks.BLUE_BED);
		blocks.add(Blocks.CYAN_BED);
		blocks.add(Blocks.BROWN_BED);
		blocks.add(Blocks.GRAY_BED);
		blocks.add(Blocks.GREEN_BED);
		blocks.add(Blocks.LIGHT_BLUE_BED);
		blocks.add(Blocks.LIGHT_GRAY_BED);
		blocks.add(Blocks.LIME_BED);
		blocks.add(Blocks.MAGENTA_BED);
		blocks.add(Blocks.ORANGE_BED);
		blocks.add(Blocks.PINK_BED);
		blocks.add(Blocks.PURPLE_BED);
		blocks.add(Blocks.RED_BED);
		blocks.add(Blocks.WHITE_BED);
		blocks.add(Blocks.YELLOW_BED);

		blocks.add(Blocks.WHEAT);

		blocks.add(Blocks.BLACK_CARPET);
		blocks.add(Blocks.CYAN_CARPET);
		blocks.add(Blocks.BROWN_CARPET);
		blocks.add(Blocks.BLUE_CARPET);
		blocks.add(Blocks.GRAY_CARPET);
		blocks.add(Blocks.GRAY_CARPET);
		blocks.add(Blocks.GREEN_CARPET);
		blocks.add(Blocks.LIGHT_BLUE_CARPET);
		blocks.add(Blocks.LIGHT_GRAY_CARPET);
		blocks.add(Blocks.LIME_CARPET);
		blocks.add(Blocks.MAGENTA_CARPET);
		blocks.add(Blocks.ORANGE_CARPET);
		blocks.add(Blocks.PINK_CARPET);
		blocks.add(Blocks.PURPLE_CARPET);
		blocks.add(Blocks.RED_CARPET);
		blocks.add(Blocks.WHITE_CARPET);
		blocks.add(Blocks.YELLOW_CARPET);

		blocks.add(Blocks.CARROTS);
		blocks.add(Blocks.BREWING_STAND);
		blocks.add(Blocks.POTATOES);
		blocks.add(Blocks.BEETROOTS);
		blocks.add(Blocks.TRIPWIRE_HOOK);
		blocks.add(Blocks.REDSTONE_WIRE);

		blocks.add(Blocks.ACACIA_SAPLING);
		blocks.add(Blocks.SPRUCE_SAPLING);
		blocks.add(Blocks.DARK_OAK_SAPLING);
		blocks.add(Blocks.JUNGLE_SAPLING);
		blocks.add(Blocks.OAK_SAPLING);
		blocks.add(Blocks.BIRCH_SAPLING);

		blocks.add(Blocks.CHORUS_FLOWER);
		blocks.add(Blocks.SUNFLOWER);
		blocks.add(Blocks.POPPY);

		blocks.add(Blocks.ORANGE_TULIP);
		blocks.add(Blocks.PINK_TULIP);
		blocks.add(Blocks.RED_TULIP);
		blocks.add(Blocks.WHITE_TULIP);

		blocks.add(BlockInit.FAUCET);
		blocks.add(BlockInit.FAUCET_ON);
		blocks.add(Blocks.REDSTONE_TORCH);
		blocks.add(Blocks.COMPARATOR);
		blocks.add(Blocks.REPEATER);
		blocks.add(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE);
		blocks.add(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE);
		blocks.add(Blocks.STONE_PRESSURE_PLATE);

		blocks.add(Blocks.ACACIA_PRESSURE_PLATE);
		blocks.add(Blocks.BIRCH_PRESSURE_PLATE);
		blocks.add(Blocks.DARK_OAK_PRESSURE_PLATE);
		blocks.add(Blocks.JUNGLE_PRESSURE_PLATE);
		blocks.add(Blocks.OAK_PRESSURE_PLATE);
		blocks.add(Blocks.SPRUCE_PRESSURE_PLATE);

		blocks.add(Blocks.LEVER);

		if(result.sideHit != null)
		{
			Block block = world.getBlockState(result.getBlockPos()).getBlock();
			Block blockDown = world.getBlockState(result.getBlockPos().down()).getBlock();

			if(block == Blocks.WATER)
			{
				this.getMotion().x *= .9;
				this.getMotion().y *= .9;
				this.getMotion().z *= .9;
			}
			else if(block == Blocks.LAVA)
			{
				this.remove();
			}
			else if(!blocks.contains(block))
			{				
				if(result.sideHit == Direction.UP)
				{
					this.setVelocity(0, 0.1, 0);
				}
				else if(result.sideHit == Direction.DOWN && (blockDown == Blocks.AIR || blockDown == BlockInit.MUSTARD_GAS))
				{
					this.getMotion().y = -.1;
					this.getMotion().x *= .99;
					this.getMotion().z *= .99;
				}
				if(result.sideHit == Direction.NORTH)
				{
					this.getMotion().z = 0;
					this.getMotion().x *= .99;
					this.getMotion().y *= .99;
				}
				else if(result.sideHit == Direction.SOUTH)
				{
					this.getMotion().z = 0;
					this.getMotion().x *= .99;
					this.getMotion().y *= .99;
				}
				if(result.sideHit == Direction.EAST)
				{
					this.getMotion().x = 0;
					this.getMotion().z *= .99;
					this.getMotion().y *= .99;
				}
				else if(result.sideHit == Direction.WEST)
				{
					this.getMotion().x = 0;
					this.getMotion().z *= .99;
					this.getMotion().y *= .99;
				}

			}
		}
	}
	 */

}