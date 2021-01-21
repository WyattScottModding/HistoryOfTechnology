package render;

import com.mojang.blaze3d.platform.GlStateManager;

import entity.EntityBeam;
import entity.EntityModelT;
import main.Reference;
import models.ModelT;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderModelT extends EntityRenderer<EntityModelT>
{
	public static final ResourceLocation MODELT = new ResourceLocation(Reference.MODID + ":textures/entity/entitymodelt.png");
	private ModelT model = new ModelT();

    public RenderModelT(EntityRendererManager manager)
	{
		super(manager);
		this.shadowSize = 2.0F;
	}

	/**
	 * Renders the desired {@code T} type Entity.
	 */
	public void doRender(EntityModelT entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		GlStateManager.pushMatrix();
		this.bindEntityTexture(entity);
		long i = (long)entity.getEntityId() * 493286711L;
		i = i * i * 4392167121L + i * 98761L;
		float f = (((float)(i >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
		float f1 = (((float)(i >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
		float f2 = (((float)(i >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
		GlStateManager.translatef(f, f1, f2);
		float f3 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;


		GlStateManager.translatef((float)x, (float)y + 1.375F, (float)z);
		GlStateManager.rotatef(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotatef(-f3, 0.0F, 0.0F, 1.0F);
		float f6 = entity.getDamage() - partialTicks;

		if (f6 < 0.0F)
		{
			f6 = 0.0F;
		}

		if (this.renderOutlines)
		{
			GlStateManager.enableColorMaterial();
			GlStateManager.setupSolidRenderingTextureCombine(this.getTeamColor(entity));
		}

		GlStateManager.scalef(-1.0F, -1.0F, 1.0F);
		this.model.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.popMatrix();

		if (this.renderOutlines)
		{
			GlStateManager.tearDownSolidRenderingTextureCombine();
			GlStateManager.disableColorMaterial();
		}

		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityModelT entity) 
	{
		return MODELT;
	}
}