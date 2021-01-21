package models;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class ModelExoSuitLegs extends BipedModel
{
	RendererModel Belt1;
	RendererModel Belt2;
	RendererModel Belt3;

	RendererModel LegLeft;
	RendererModel LegRight;
	RendererModel LegLeft2;
	RendererModel LegRight2;

	public ModelExoSuitLegs()
	{
		textureWidth = 64;
		textureHeight = 64;

		Belt1 = new RendererModel(this, 0, 44);
		Belt1.addBox(0F, 0F, 0F, 10, 2, 2);
		Belt1.setRotationPoint(-5F, 11F, 2F);
		Belt1.setTextureSize(64, 64);
		Belt1.mirror = true;
		this.bipedBody.addChild(Belt1);

		Belt2 = new RendererModel(this, 0, 44);
		Belt2.addBox(0F, 0F, 0F, 1, 2, 4);
		Belt2.setRotationPoint(4F, 11F, -2F);
		Belt2.setTextureSize(64, 64);
		Belt2.mirror = true;
		this.bipedBody.addChild(Belt2);

		Belt3 = new RendererModel(this, 0, 44);
		Belt3.addBox(0F, 0F, 0F, 1, 2, 4);
		Belt3.setRotationPoint(-5F, 11F, -2F);
		Belt3.setTextureSize(64, 64);
		Belt3.mirror = true;
		this.bipedBody.addChild(Belt3);

		LegLeft = new RendererModel(this, 27, 37);
		LegLeft.addBox(-2F, -12F, 0F, 1, 11, 2);
		LegLeft.setRotationPoint(4F, 13F, -1F);
		LegLeft.setTextureSize(64, 64);
		LegLeft.mirror = true;
		this.bipedLeftLeg.addChild(LegLeft);

		LegRight = new RendererModel(this, 27, 37);
		LegRight.addBox(2F, -12F, 0F, 1, 11, 2);
		LegRight.setRotationPoint(-5F, 13F, -1F);
		LegRight.setTextureSize(64, 64);
		LegRight.mirror = true;
		this.bipedRightLeg.addChild(LegRight);

		LegLeft2 = new RendererModel(this, 27, 37);
		LegLeft2.addBox(-2F, -12F, 0F, 4, 1, 2);
		LegLeft2.setRotationPoint(1F, 24F, -1F);
		LegLeft2.setTextureSize(64, 64);
		LegLeft2.mirror = true;
		this.bipedLeftLeg.addChild(LegLeft2);

		LegRight2 = new RendererModel(this, 27, 37);
		LegRight2.addBox(2F, -12F, 0F, 4, 1, 2);
		LegRight2.setRotationPoint(-5F, 24F, -1F);
		LegRight2.setTextureSize(64, 64);
		LegRight2.mirror = true;
		this.bipedRightLeg.addChild(LegRight2);

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
