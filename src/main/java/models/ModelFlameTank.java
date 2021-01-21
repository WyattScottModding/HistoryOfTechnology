package models;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class ModelFlameTank extends BipedModel
{
	RendererModel FlameTank;
	RendererModel FlameTankRight;
	RendererModel FlameTankLeft;
	RendererModel FlameTankFront;
	RendererModel FlameTankBehind;
	RendererModel FlameTankBottom;
	RendererModel FlameTankTop;

	public ModelFlameTank()
	{
		textureWidth = 64;
		textureHeight = 32;

		FlameTank = new RendererModel(this, 0, 0);
		FlameTank.addBox(0F, 0F, 0F, 4, 10, 4);
		FlameTank.setRotationPoint(-2F, 1F, 3F);
		FlameTank.setTextureSize(64, 32);
		FlameTank.mirror = true;
		this.bipedBody.addChild(FlameTank);
		
		FlameTankRight = new RendererModel(this, 0, 14);
		FlameTankRight.addBox(0F, 0F, 0F, 1, 8, 2);
		FlameTankRight.setRotationPoint(-3F, 2F, 4F);
		FlameTankRight.setTextureSize(64, 32);
		FlameTankRight.mirror = true;
		this.bipedBody.addChild(FlameTankRight);

		FlameTankLeft = new RendererModel(this, 0, 14);
		FlameTankLeft.addBox(0F, 0F, 0F, 1, 8, 2);
		FlameTankLeft.setRotationPoint(2F, 2F, 4F);
		FlameTankLeft.setTextureSize(64, 32);
		FlameTankLeft.mirror = true;
		this.bipedBody.addChild(FlameTankLeft);

		FlameTankFront = new RendererModel(this, 0, 14);
		FlameTankFront.addBox(0F, 0F, 0F, 2, 8, 1);
		FlameTankFront.setRotationPoint(-1F, 2F, 2F);
		FlameTankFront.setTextureSize(64, 32);
		FlameTankFront.mirror = true;
		this.bipedBody.addChild(FlameTankFront);

		FlameTankBehind = new RendererModel(this, 0, 14);
		FlameTankBehind.addBox(0F, 0F, 0F, 2, 8, 1);
		FlameTankBehind.setRotationPoint(-1F, 2F, 7F);
		FlameTankBehind.setTextureSize(64, 32);
		FlameTankBehind.mirror = true;
		this.bipedBody.addChild(FlameTankBehind);

		FlameTankBottom = new RendererModel(this, 0, 29);
		FlameTankBottom.addBox(0F, 0F, 0F, 2, 1, 2);
		FlameTankBottom.setRotationPoint(-1F, 11F, 4F);
		FlameTankBottom.setTextureSize(64, 32);
		FlameTankBottom.mirror = true;
		this.bipedBody.addChild(FlameTankBottom);

		FlameTankTop = new RendererModel(this, 0, 29);
		FlameTankTop.addBox(0F, 0F, 0F, 2, 1, 2);
		FlameTankTop.setRotationPoint(-1F, 0F, 4F);
		FlameTankTop.setTextureSize(64, 32);
		FlameTankTop.mirror = true;
		this.bipedBody.addChild(FlameTankTop);

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
