package models;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class ModelParachute extends BipedModel
{
	//fields
	RendererModel String1;
	RendererModel String2;
	RendererModel String3;
	RendererModel String4;
	RendererModel Part1;
	RendererModel Part2;
	RendererModel Part3;
	RendererModel Part4;
	RendererModel Part5;


	//Backpack
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

	public ModelParachute()
	{
		textureWidth = 256;
		textureHeight = 256;

		String1 = new RendererModel(this, 0, 51);
		String1.addBox(0F, 0F, 0F, 1, 20, 1);
		String1.setRotationPoint(13F, -16F, -7F);
		String1.setTextureSize(128, 128);
		String1.mirror = true;
		setRotation(String1, 0.2617994F, 0F, 0.5235988F);
		this.bipedBody.addChild(String1);

		String2 = new RendererModel(this, 0, 51);
		String2.addBox(0F, 0F, 0F, 1, 20, 1);
		String2.setRotationPoint(13F, -16F, 6F);
		String2.setTextureSize(128, 128);
		String2.mirror = true;
		setRotation(String2, -0.2617994F, 0F, 0.5235988F);
		this.bipedBody.addChild(String2);

		String3 = new RendererModel(this, 0, 51);
		String3.addBox(0F, 0F, 0F, 1, 20, 1);
		String3.setRotationPoint(-14F, -15.9F, -7F);
		String3.setTextureSize(128, 128);
		String3.mirror = true;
		setRotation(String3, 0.2617994F, 0F, -0.5235988F);
		this.bipedBody.addChild(String3);

		String4 = new RendererModel(this, 0, 51);
		String4.addBox(0F, 0F, 0F, 1, 20, 1);
		String4.setRotationPoint(-14F, -16F, 6F);
		String4.setTextureSize(128, 128);
		String4.mirror = true;
		setRotation(String4, -0.2617994F, 0F, -0.5235988F);
		this.bipedBody.addChild(String4);

		Part1 = new RendererModel(this, 25, 51);
		Part1.addBox(0F, 0F, 0F, 5, 1, 14);
		Part1.setRotationPoint(10F, -18.5F, -7F);
		Part1.setTextureSize(128, 128);
		Part1.mirror = true;
		setRotation(Part1, 0F, 0F, 0.5235988F);
		this.bipedBody.addChild(Part1);

		Part2 = new RendererModel(this, 7, 51);
		Part2.addBox(0F, 0F, 0F, 5, 1, 14);
		Part2.setRotationPoint(-14.5F, -16F, -7F);
		Part2.setTextureSize(128, 128);
		Part2.mirror = true;
		setRotation(Part2, 0F, 0F, -0.5235988F);
		this.bipedBody.addChild(Part2);

		Part3 = new RendererModel(this, 9, 51);
		Part3.addBox(0F, 0F, 0F, 5, 1, 14);
		Part3.setRotationPoint(5.2F, -19.8F, -7F);
		Part3.setTextureSize(128, 128);
		Part3.mirror = true;
		setRotation(Part3, 0F, 0F, 0.2617994F);
		this.bipedBody.addChild(Part3);

		Part4 = new RendererModel(this, 13, 51);
		Part4.addBox(0F, 0F, 0F, 5, 1, 14);
		Part4.setRotationPoint(-10.2F, -18.5F, -7F);
		Part4.setTextureSize(128, 128);
		Part4.mirror = true;
		setRotation(Part4, 0F, 0F, -0.2617994F);
		this.bipedBody.addChild(Part4);

		Part5 = new RendererModel(this, 14, 51);
		Part5.addBox(0F, 0F, 0F, 11, 1, 14);
		Part5.setRotationPoint(-5.5F, -19.8F, -7F);
		Part5.setTextureSize(128, 128);
		Part5.mirror = true;
		setRotation(Part5, 0F, 0F, 0F);
		this.bipedBody.addChild(Part5);


		textureWidth = 128;
		textureHeight = 128;
		
		//Backpack
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
