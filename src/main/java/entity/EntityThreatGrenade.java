package entity;

import java.util.List;

import handlers.EntityProjectile;
import handlers.EntityRegistry;
import handlers.SoundsHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityThreatGrenade extends EntityProjectile
{
	private int fuse = 100;

	public final static EntityType<?> type = EntityRegistry.THREATGRENADE;


	public EntityThreatGrenade(World worldIn)
	{
		super(type, worldIn);
		this.world = worldIn;
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
				this.world.addParticle(ParticleTypes.CRIT, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public void tick() 
	{
		if(fuse > 0)
			fuse--;

		if(fuse == 0 && world != null)
			world.playSound(null, this.posX, this.posY, this.posZ, SoundsHandler.ITEM_THREATGRENADE_HIT, SoundCategory.PLAYERS, 1.0F, 1.0F);

		if(fuse == 0 && world != null && !world.isRemote)
		{
			AxisAlignedBB bb = new AxisAlignedBB(this.posX - 10, this.posY - 10, this.posZ - 10, this.posX + 10, this.posY + 10, this.posZ + 10);

			List<LivingEntity> entites = world.getEntitiesWithinAABB(LivingEntity.class, bb);

			for(LivingEntity element : entites)
			{
				element.addPotionEffect(new EffectInstance(Effects.GLOWING, 300, 1));
			}


			this.remove();

		}

		Block block = world.getBlockState(this.getPosition()).getBlock();

		if(block.getDefaultState().isSolid())
		{
			this.setVelocity(0, .1, 0);
		}

		super.tick();
	}

	protected void onImpact(RayTraceResult result) 
	{	
	}
}