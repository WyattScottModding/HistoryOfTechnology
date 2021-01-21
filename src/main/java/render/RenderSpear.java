package render;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;

import entity.EntityBeam;
import entity.EntitySpear;
import init.ItemInit;
import main.Reference;
import models.ModelSpear;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RenderSpear extends EntityRenderer<EntitySpear>
{
	public static final ResourceLocation SPEAR = new ResourceLocation(Reference.MODID + ":textures/entity/entityspear.png");
	private ModelSpear model = new ModelSpear();
	
	public RenderSpear(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn);
		this.shadowSize = .2F;
	}

	/**
	 * Renders the desired {@code T} type Entity.
	 */
	public void doRender(EntitySpear entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		GL11.glPushMatrix();
        GlStateManager.disableLighting();
		bindTexture(SPEAR);
		GL11.glTranslated(x, y, z);
        GlStateManager.rotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 0.0F, 0.0F, 1.0F);
        GlStateManager.enableRescaleNormal();

        /*  Spear Shake
        if (f9 > 0.0F)
        {
            float f10 = -MathHelper.sin(f9 * 3.0F) * f9;
            GlStateManager.rotatef(f10, 0.0F, 0.0F, 1.0F);
        }
        */
        
        GlStateManager.translated(0, -0.1, 0);

		//this.itemRenderer.renderItem(ItemInit.SPEAR, ItemCameraTransforms.TransformType.GROUND);

        //Rotates left and right
        //GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
        
        //Rotate along it's length
        //GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
        
        //Rotates forward and backward
        GlStateManager.rotatef(90.0F, 0.0F, 0.0F, 1.0F);

        GlStateManager.enableLighting();

        model.render(entity, 0.0F, 0.0F, 0.0f, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySpear entity) 
	{
		return SPEAR;
	}
}