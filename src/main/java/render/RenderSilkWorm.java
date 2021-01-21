package render;

import javax.annotation.Nullable;

import main.Reference;
import mobs.EntitySilkWorm;
import models.ModelSilkWorm;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RenderSilkWorm extends MobRenderer<EntitySilkWorm, ModelSilkWorm>
{
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/silkworm.png");
	

	public RenderSilkWorm(EntityRendererManager manager) 
	{
		super(manager, new ModelSilkWorm(), 0.1F);
		
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntitySilkWorm entity) {
		
		return TEXTURES;
	}
	
	@Override
	protected void applyRotations(EntitySilkWorm entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
	{
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}

	public static class RenderFactory implements IRenderFactory<EntitySilkWorm>
	{
		@Override
		public EntityRenderer<? super EntitySilkWorm> createRenderFor(EntityRendererManager manager) {
			return new RenderSilkWorm(manager);
		}
		
	}
}
