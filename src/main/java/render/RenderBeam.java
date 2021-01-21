package render;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;

import entity.EntityBeam;
import main.Reference;
import mobs.EntitySilkWorm;
import models.ModelBeam;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RenderBeam extends EntityRenderer<EntityBeam>
{
	public static final ResourceLocation BEAM = new ResourceLocation(Reference.MODID + ":textures/entity/beam.png");
	private ModelBeam model = new ModelBeam();

    public RenderBeam(EntityRendererManager manager)
    {
		super(manager);
		this.shadowSize = .1F;
    }
    
    
    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityBeam entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
    	GL11.glPushMatrix();
        GlStateManager.disableLighting();
		bindTexture(BEAM);
		GL11.glTranslated(x, y, z);
        GlStateManager.enableLighting();
        model.render(entity, 0.0F, 0.0F, 0.0f, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
    }


	@Override
	protected ResourceLocation getEntityTexture(EntityBeam entity) 
	{
		return BEAM;
	}


}