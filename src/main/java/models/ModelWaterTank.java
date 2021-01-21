package models;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class ModelWaterTank extends BipedModel
{
	RendererModel WaterTank;
	RendererModel WaterTankBottom;
	RendererModel WaterTankTop;

	public ModelWaterTank()
	{
		textureWidth = 64;
		textureHeight = 32;

		WaterTank = new RendererModel(this, 0, 0);
		WaterTank.addBox(0F, 0F, 0F, 6, 10, 5);
		WaterTank.setRotationPoint(-3F, 1F, 2.5F);
		WaterTank.setTextureSize(64, 32);
		WaterTank.mirror = true;
		this.bipedBody.addChild(WaterTank);

		WaterTankBottom = new RendererModel(this, 22, 6);
		WaterTankBottom.addBox(0F, 0F, 0F, 4, 1, 3);
		WaterTankBottom.setRotationPoint(-2F, 11F, 3.5F);
		WaterTankBottom.setTextureSize(64, 32);
		WaterTankBottom.mirror = true;
		this.bipedBody.addChild(WaterTankBottom);

		WaterTankTop = new RendererModel(this, 22, 6);
		WaterTankTop.addBox(0F, 0F, 0F, 4, 1, 3);
		WaterTankTop.setRotationPoint(-2F, 0F, 3.5F);
		WaterTankTop.setTextureSize(64, 32);
		WaterTankTop.mirror = true;
		this.bipedBody.addChild(WaterTankTop);

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
