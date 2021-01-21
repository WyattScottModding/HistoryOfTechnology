package mobs;

import java.util.ArrayList;

import javax.annotation.Nullable;

import handlers.EntityLivingRegistry;
import handlers.LootTableHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntitySilkWorm extends AnimalEntity
{
	public EntitySilkWorm(EntityType<? extends EntitySilkWorm> type, World world) 
	{
		super(type, world);
	}

	@Override
	protected void registerGoals()
	{
		super.registerGoals();

		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(4, new TemptGoal(this, 1.25D, Ingredient.fromStacks(new ItemStack(Blocks.ACACIA_LEAVES)), false));
		this.goalSelector.addGoal(5, new TemptGoal(this, 1.25D, Ingredient.fromStacks(new ItemStack(Blocks.BIRCH_LEAVES)), false));
		this.goalSelector.addGoal(6, new TemptGoal(this, 1.25D, Ingredient.fromStacks(new ItemStack(Blocks.DARK_OAK_LEAVES)), false));
		this.goalSelector.addGoal(7, new TemptGoal(this, 1.25D, Ingredient.fromStacks(new ItemStack(Blocks.SPRUCE_LEAVES)), false));
		this.goalSelector.addGoal(8, new TemptGoal(this, 1.25D, Ingredient.fromStacks(new ItemStack(Blocks.JUNGLE_LEAVES)), false));
		this.goalSelector.addGoal(9, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(11, new LookRandomlyGoal(this));
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1D);
	}
	
	/*
	@Override
	protected SoundEvent getAmbientSound()
	{
		return SoundEvents.ENTITY_COW_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return SoundEvents.ENTITY_COW_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundEvents.ENTITY_COW_DEATH;
	}
*/
	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn)
	{
		this.playSound(SoundEvents.ENTITY_SILVERFISH_STEP, 0.15F, 1.0F);
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	@Override
	protected float getSoundVolume()
	{
		return 0.1F;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable()
	{
		return LootTableHandler.SILKWORM;
	}

	@Override
	public EntitySilkWorm createChild(AgeableEntity ageable)
	{
	      return (EntitySilkWorm) EntityLivingRegistry.SILKWORM.create(this.world);
	}

	@Override
	public float getEyeHeight(Pose p_213307_1_) {
		return 0.1F;
	}

	@Override
	public void livingTick() 
	{
		//Set in love if on leaves
		int chance = (int) (Math.random() * 4000);
		Block block = world.getBlockState(this.getPosition().down()).getBlock();

		ArrayList<Block> blockList = new ArrayList<Block>();
		blockList.add(Blocks.ACACIA_LEAVES);
		blockList.add(Blocks.BIRCH_LEAVES);
		blockList.add(Blocks.DARK_OAK_LEAVES);
		blockList.add(Blocks.JUNGLE_LEAVES);
		blockList.add(Blocks.OAK_LEAVES);
		blockList.add(Blocks.SPRUCE_LEAVES);

		for(Block element : blockList)
		{
			if(element == block)
			{
				if(chance == 0)
				{			
					this.setInLove(world.getClosestPlayer(this, 10));
				}
			}
		}

		super.livingTick();
	}
}