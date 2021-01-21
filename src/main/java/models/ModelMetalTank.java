package models;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class ModelMetalTank extends BipedModel
{
	RendererModel MetalTank;
	RendererModel MetalTankBottom;
	RendererModel MetalTankTop;

	public ModelMetalTank()
	{
		textureWidth = 64;
		textureHeight = 32;

		MetalTank = new RendererModel(this, 0, 0);
		MetalTank.addBox(0F, 0F, 0F, 6, 10, 5);
		MetalTank.setRotationPoint(-3F, 1F, 2.5F);
		MetalTank.setTextureSize(64, 32);
		MetalTank.mirror = true;
		this.bipedBody.addChild(MetalTank);

		MetalTankBottom = new RendererModel(this, 22, 6);
		MetalTankBottom.addBox(0F, 0F, 0F, 4, 1, 3);
		MetalTankBottom.setRotationPoint(-2F, 11F, 3.5F);
		MetalTankBottom.setTextureSize(64, 32);
		MetalTankBottom.mirror = true;
		this.bipedBody.addChild(MetalTankBottom);

		MetalTankTop = new RendererModel(this, 22, 6);
		MetalTankTop.addBox(0F, 0F, 0F, 4, 1, 3);
		MetalTankTop.setRotationPoint(-2F, 0F, 3.5F);
		MetalTankTop.setTextureSize(64, 32);
		MetalTankTop.mirror = true;
		this.bipedBody.addChild(MetalTankTop);

	}

	public void render(LivingEntity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
	}


	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, LivingEntity entity)
	{
		super.setRotationAngles(entity, f1, f2, f3, f4, f5, f);
	}

}
