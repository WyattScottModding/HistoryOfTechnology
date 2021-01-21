package render;

import entity.EntityTurret;
import main.Reference;
import models.ModelTurret;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderTurret extends LivingRenderer<EntityTurret, ModelTurret>
{
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/turret.png");
	

	public RenderTurret(EntityRendererManager manager) 
	{
		super(manager, new ModelTurret(), 1.0F);	
	}

	protected ResourceLocation getEntityTexture(EntityTurret entity) 
	{	
		return TEXTURES;
	}
	
	@Override
	protected void applyRotations(EntityTurret entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
	{
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}

}