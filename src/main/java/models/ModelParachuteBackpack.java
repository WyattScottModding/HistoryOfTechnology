package models;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;

public class ModelParachuteBackpack extends BipedModel
{
	//fields
	RendererModel LeftArm1;
	RendererModel RightArm1;
	RendererModel LeftArm2;
	RendererModel RightArm2;
	RendererModel Body2;
	RendererModel Body3;
	RendererModel Body4;
	RendererModel LeftArm3;
	RendererModel RightArm3;
	RendererModel Body1;

	public ModelParachuteBackpack()
	{
		textureWidth = 128;
		textureHeight = 128;

		LeftArm1 = new RendererModel(this, 0, 21);
		LeftArm1.addBox(0F, 0F, 0F, 1, 3, 1);
		LeftArm1.setRotationPoint(4F, -1F, 2F);
		LeftArm1.setTextureSize(64, 64);
		LeftArm1.mirror = true;
		setRotation(LeftArm1, 0F, 0F, 0.7853982F);
		this.bipedBody.addChild(LeftArm1);

		RightArm1 = new RendererModel(this, 0, 21);
		RightArm1.addBox(0F, 0F, 0F, 1, 3, 1);
		RightArm1.setRotationPoint(-5F, 0F, 2F);
		RightArm1.setTextureSize(64, 64);
		RightArm1.mirror = true;
		setRotation(RightArm1, 0F, 0F, -0.7853982F);
		this.bipedBody.addChild(RightArm1);

		LeftArm2 = new RendererModel(this, 0, 57);
		LeftArm2.addBox(0F, 0F, 0F, 2, 1, 6);
		LeftArm2.setRotationPoint(4F, -1F, -3F);
		LeftArm2.setTextureSize(64, 64);
		LeftArm2.mirror = true;
		setRotation(LeftArm2, 0F, 0F, 0F);
		this.bipedBody.addChild(LeftArm2);

		RightArm2 = new RendererModel(this, 0, 57);
		RightArm2.addBox(0F, 0F, 0F, 2, 1, 6);
		RightArm2.setRotationPoint(-6F, -1F, -3F);
		RightArm2.setTextureSize(64, 64);
		RightArm2.mirror = true;
		setRotation(RightArm2, 0F, 0F, 0F);
		this.bipedBody.addChild(RightArm2);

		Body2 = new RendererModel(this, 22, 0);
		Body2.addBox(0F, 0F, 0F, 4, 1, 1);
		Body2.setRotationPoint(-2F, 10.5F, 3F);
		Body2.setTextureSize(64, 64);
		Body2.mirror = true;
		setRotation(Body2, 0F, 0F, 0F);
		this.bipedBody.addChild(Body2);

		Body3 = new RendererModel(this, 21, 0);
		Body3.addBox(0F, 0F, 0F, 2, 1, 1);
		Body3.setRotationPoint(-1F, 0.5F, 3F);
		Body3.setTextureSize(64, 64);
		Body3.mirror = true;
		setRotation(Body3, 0F, 0F, 0F);
		this.bipedBody.addChild(Body3);

		Body4 = new RendererModel(this, 23, 0);
		Body4.addBox(0F, 0F, 0F, 4, 3, 1);
		Body4.setRotationPoint(-2F, 7F, 5F);
		Body4.setTextureSize(64, 64);
		Body4.mirror = true;
		setRotation(Body4, 0F, 0F, 0F);
		this.bipedBody.addChild(Body4);

		LeftArm3 = new RendererModel(this, 0, 21);
		LeftArm3.addBox(0F, 0F, 0F, 2, 2, 1);
		LeftArm3.setRotationPoint(4F, 0F, -3F);
		LeftArm3.setTextureSize(64, 64);
		LeftArm3.mirror = true;
		setRotation(LeftArm3, 0F, 0F, 0F);
		this.bipedBody.addChild(LeftArm3);

		RightArm3 = new RendererModel(this, 0, 21);
		RightArm3.addBox(0F, 0F, 0F, 2, 2, 1);
		RightArm3.setRotationPoint(-6F, 0F, -3F);
		RightArm3.setTextureSize(64, 64);
		RightArm3.mirror = true;
		setRotation(RightArm3, 0F, 0F, 0F);
		this.bipedBody.addChild(RightArm3);

		Body1 = new RendererModel(this, 0, 0);
		Body1.addBox(0F, 0F, 0F, 6, 10, 3);
		Body1.setRotationPoint(-3F, 1F, 2F);
		Body1.setTextureSize(64, 64);
		Body1.mirror = true;
		setRotation(Body1, 0F, 0F, 0F);
		this.bipedBody.addChild(Body1);

	}

	public void render(LivingEntity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
	}

	private void setRotation(RendererModel model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, LivingEntity entity)
	{
		super.setRotationAngles(entity, f1, f2, f3, f4, f5, f);
	}

}
