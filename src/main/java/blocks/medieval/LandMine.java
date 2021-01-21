package blocks.medieval;

import java.util.List;

import main.History;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class LandMine extends Block{

	protected static final VoxelShape MINE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.6D, 16.0D);

	Item.Properties properties;

	public LandMine(String name)
	{
		super(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(5.0F, 20.0F));
		this.setDefaultState(this.getDefaultState());
		this.setRegistryName(name);

		properties = new Item.Properties().maxStackSize(64).group(History.industrialTab);
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity)
	{
		if(!world.isRemote)
		{
			world.setBlockState(pos, Blocks.AIR.getDefaultState());		

			world.createExplosion(entity, pos.getX(), pos.getY(), pos.getZ(), 4.0F, false, Explosion.Mode.BREAK);

			AxisAlignedBB bb1 = new AxisAlignedBB(pos.getX() - 3, pos.getY() - 3, pos.getZ() - 3, pos.getX() + 3, pos.getY() + 3, pos.getZ() + 3);
			AxisAlignedBB bb2 = new AxisAlignedBB(pos.getX() - 6, pos.getY() - 6, pos.getZ() - 6, pos.getX() + 6, pos.getY() + 6, pos.getZ() + 6);

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
			
			List<ItemEntity> entites3 = world.getEntitiesWithinAABB(ItemEntity.class, bb1);

			for(ItemEntity element : entites3)
			{
				element.ticksExisted = 5999;
			}
		}
	}
	
	@Override
	public boolean canBeConnectedTo(BlockState state, IBlockReader world, BlockPos pos, Direction facing) 
	{
		return world.getBlockState(pos.down()).isNormalCube(world, pos);	
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return MINE;
	}

	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks for render
	 */
	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return false;
	}
}