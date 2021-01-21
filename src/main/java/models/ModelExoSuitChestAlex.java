package models;


import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class ModelExoSuitChestAlex extends BipedModel
{
	RendererModel Back1;
	RendererModel Back2;
	RendererModel Back3;
	RendererModel Back4;

	RendererModel ShoulderRight1;
	RendererModel ShoulderLeft1;
	RendererModel ShoulderRight2;
	RendererModel ShoulderLeft2;
	RendererModel ShoulderRight3;
	RendererModel ShoulderLeft3;
	RendererModel ShoulderRight4;
	RendererModel ShoulderLeft4;

	RendererModel ArmRight;
	RendererModel ArmLeft;

	RendererModel CuffRight1;
	RendererModel CuffLeft1;
	RendererModel CuffRight2;
	RendererModel CuffLeft2;
	RendererModel CuffRight3;
	RendererModel CuffLeft3;
	RendererModel CuffRight4;
	RendererModel CuffLeft4;

	public ModelExoSuitChestAlex()
	{
		textureWidth = 64;
		textureHeight = 64;

		Back1 = new RendererModel(this, 0, 30);
		Back1.addBox(0F, 0F, 0F, 6, 6, 2);
		Back1.setRotationPoint(-3F, 2F, 2F);
		Back1.setTextureSize(64, 64);
		Back1.mirror = true;
		this.bipedBody.addChild(Back1);

		Back2 = new RendererModel(this, 3, 32);
		Back2.addBox(0F, 0F, 0F, 4, 1, 2);
		Back2.setRotationPoint(-2F, 1F, 2F);
		Back2.setTextureSize(64, 64);
		Back2.mirror = true;
		this.bipedBody.addChild(Back2);

		Back3 = new RendererModel(this, 3, 30);
		Back3.addBox(0F, 0F, 0F, 4, 2, 2);
		Back3.setRotationPoint(-2F, 8F, 2F);
		Back3.setTextureSize(64, 64);
		Back3.mirror = true;
		this.bipedBody.addChild(Back3);

		Back4 = new RendererModel(this, 6, 32);
		Back4.addBox(0F, 0F, 0F, 2, 1, 2);
		Back4.setRotationPoint(-1F, 10F, 2F);
		Back4.setTextureSize(64, 64);
		Back4.mirror = true;
		this.bipedBody.addChild(Back4);

		ShoulderRight1 = new RendererModel(this, 0, 38);
		ShoulderRight1.addBox(0F, 0F, 0F, 3, 1, 2);
		ShoulderRight1.setRotationPoint(-6F, 3F, 2F);
		ShoulderRight1.setTextureSize(64, 64);
		ShoulderRight1.mirror = true;
		this.bipedBody.addChild(ShoulderRight1);

		ShoulderLeft1 = new RendererModel(this, 0, 38);
		ShoulderLeft1.addBox(0F, 0F, 0F, 3, 1, 2);
		ShoulderLeft1.setRotationPoint(3F, 3F, 2F);
		ShoulderLeft1.setTextureSize(64, 64);
		ShoulderLeft1.mirror = true;
		this.bipedBody.addChild(ShoulderLeft1);

		ShoulderRight2 = new RendererModel(this, 0, 29);
		ShoulderRight2.addBox(0F, 0F, 0F, 2, 4, 2);
		ShoulderRight2.setRotationPoint(-6F, -1F, 2F);
		ShoulderRight2.setTextureSize(64, 64);
		ShoulderRight2.mirror = true;
		this.bipedBody.addChild(ShoulderRight2);

		ShoulderLeft2 = new RendererModel(this, 0, 29);
		ShoulderLeft2.addBox(0F, 0F, 0F, 2, 4, 2);
		ShoulderLeft2.setTextureSize(64, 64);
		ShoulderLeft2.mirror = true;
		this.bipedBody.addChild(ShoulderLeft2);

		ShoulderRight3 = new RendererModel(this, 0, 38);
		ShoulderRight3.setTextureSize(64, 64);
		ShoulderRight3.mirror = true;
		this.bipedRightArm.addChild(ShoulderRight3);

		ShoulderLeft3 = new RendererModel(this, 0, 38);
		ShoulderLeft3.setTextureSize(64, 64);
		ShoulderLeft3.mirror = true;
		this.bipedLeftArm.addChild(ShoulderLeft3);

		ShoulderRight4 = new RendererModel(this, 0, 29);
		ShoulderRight4.setTextureSize(64, 64);
		ShoulderRight4.mirror = true;
		this.bipedRightArm.addChild(ShoulderRight4);

		ShoulderLeft4 = new RendererModel(this, 0, 29);
		ShoulderLeft4.setTextureSize(64, 64);
		ShoulderLeft4.mirror = true;
		this.bipedLeftArm.addChild(ShoulderLeft4);

		ArmRight = new RendererModel(this, 0, 29);
		ArmRight.setTextureSize(64, 64);
		ArmRight.mirror = true;
		this.bipedRightArm.addChild(ArmRight);

		ArmLeft = new RendererModel(this, 0, 29);
		ArmLeft.setTextureSize(64, 64);
		ArmLeft.mirror = true;
		this.bipedLeftArm.addChild(ArmLeft);

		CuffRight1 = new RendererModel(this, 0, 38);
		CuffRight1.setTextureSize(64, 64);
		CuffRight1.mirror = true;
		this.bipedRightArm.addChild(CuffRight1);

		CuffLeft1 = new RendererModel(this, 0, 38);
		CuffLeft1.setTextureSize(64, 64);
		CuffLeft1.mirror = true;
		this.bipedLeftArm.addChild(CuffLeft1);

		CuffRight2 = new RendererModel(this, 0, 38);
		CuffRight2.setTextureSize(64, 64);
		CuffRight2.mirror = true;
		this.bipedRightArm.addChild(CuffRight2);

		CuffLeft2 = new RendererModel(this, 0, 38);
		CuffLeft2.setTextureSize(64, 64);
		CuffLeft2.mirror = true;
		this.bipedLeftArm.addChild(CuffLeft2);

		CuffRight3 = new RendererModel(this, 0, 38);
		CuffRight3.setTextureSize(64, 64);
		CuffRight3.mirror = true;
		this.bipedRightArm.addChild(CuffRight3);

		CuffLeft3 = new RendererModel(this, 0, 38);
		CuffLeft3.setTextureSize(64, 64);
		CuffLeft3.mirror = true;
		this.bipedLeftArm.addChild(CuffLeft3);

		CuffRight4 = new RendererModel(this, 0, 38);
		CuffRight4.setTextureSize(64, 64);
		CuffRight4.mirror = true;
		this.bipedRightArm.addChild(CuffRight4);

		CuffLeft4 = new RendererModel(this, 0, 38);
		CuffLeft4.setTextureSize(64, 64);
		CuffLeft4.mirror = true;
		this.bipedLeftArm.addChild(CuffLeft4);


		ShoulderRight2.addBox(5F, -2F, 0F, 2, 3, 2);
		ShoulderLeft2.addBox(-5F, -2F, 0F, 2, 3, 2);
		ShoulderRight3.addBox(5F, -2F, 0F, 2, 1, 3);
		ShoulderLeft3.addBox(-5F, -2F, 0F, 2, 1, 3);
		ShoulderRight4.addBox(5F, -2F, 0F, 1, 1, 2);
		ShoulderLeft4.addBox(-5F, -2F, 0F, 1, 1, 2);
		ArmRight.addBox(5F, -2F, 0F, 1, 9, 2);
		ArmLeft.addBox(-5F, -2F, 0F, 1, 9, 2);
		CuffRight1.addBox(5F, -2F, 0F, 1, 1, 4);
		CuffLeft1.addBox(-5F, -2F, 0F, 1, 1, 4);
		CuffRight2.addBox(5F, -2F, 0F, 1, 1, 4);
		CuffLeft2.addBox(-5F, -2F, 0F, 1, 1, 4);
		CuffRight3.addBox(5F, -2F, 0F, 5, 1, 1);
		CuffLeft3.addBox(-5F, -2F, 0F, 5, 1, 1);
		CuffRight4.addBox(5F, -2F, 0F, 5, 1, 1);
		CuffLeft4.addBox(-5F, -2F, 0F, 5, 1, 1);

		ShoulderRight2.setRotationPoint(-6F, 0F, 2F);
		ShoulderLeft2.setRotationPoint(4F, 0F, 2F);
		ShoulderRight3.setRotationPoint(-6F, 0F, -1F);
		ShoulderLeft3.setRotationPoint(4F, 0F, -1F);
		ShoulderRight4.setRotationPoint(-7F, 0F, -1F);
		ShoulderLeft4.setRotationPoint(6F, 0F, -1F);
		ArmRight.setRotationPoint(-8F, 0F, -1F);
		ArmLeft.setRotationPoint(7F, 0F, -1F);
		CuffRight1.setRotationPoint(-8F, 9F, -2F);
		CuffLeft1.setRotationPoint(7F, 9F, -2F);
		CuffRight2.setRotationPoint(-4F, 9F, -2F);
		CuffLeft2.setRotationPoint(3F, 9F, -2F);
		CuffRight3.setRotationPoint(-8F, 9F, -3F);
		CuffLeft3.setRotationPoint(3F, 9F, -3F);
		CuffRight4.setRotationPoint(-8F, 9F, 2F);
		CuffLeft4.setRotationPoint(3F, 9F, 2F);

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
