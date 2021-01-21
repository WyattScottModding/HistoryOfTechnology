package models;

import mobs.EntitySilkWorm;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelSilkWorm extends EntityModel<EntitySilkWorm>
{
	RendererModel Body;

	public ModelSilkWorm()
	{
		textureWidth = 32;
		textureHeight = 32;

		Body = new RendererModel(this, 0, 0);
		Body.addBox(0F, 20F, 0F, 2, 1, 1);
		Body.setRotationPoint(1F, 0.5F, 0.5F);
		Body.setTextureSize(32, 32);
		Body.mirror = true;


	}

	@Override
	public void render(EntitySilkWorm entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch, float scale) {
		super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

		Body.renderWithRotation(scale);
	}

	public void setRotationAngles(ModelSilkWorm entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {

	}
}