package models;

import entity.EntityTurret;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelTurret extends EntityModel<EntityTurret>
{

	RendererModel droidBody;
	

	public ModelTurret()
	{
		this.textureWidth = 256;
		this.textureHeight = 256;
		
		this.droidBody = new RendererModel(this, 0, 0);
	    this.droidBody.addBox(0.0F, 16.0F, 0.0F, 16, 16, 16);
	    this.droidBody.setRotationPoint(-30.0F, -15.0F, -30.0F);
	    this.droidBody.setTextureSize(256, 256);
	    this.droidBody.showModel = true;
		
		
	}

	public void render(EntityTurret entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		this.droidBody.renderWithRotation(scale);
	}


	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	{
		this.droidBody.offsetY = MathHelper.cos(ageInTicks * 1.3F) * (float)Math.PI * 0.25F;

	}
}