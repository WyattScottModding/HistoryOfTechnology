package models;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.entity.Entity;

public class ModelBeam extends Model
{
	RendererModel Shape1;

	public ModelBeam()
	{
		textureWidth = 28;
		textureHeight = 28;

		Shape1 = new RendererModel(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 3, 16, 3);
		Shape1.setRotationPoint(-2F, 8F, -2F);
		Shape1.setTextureSize(28, 28);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shape1.render(f5);
	}

	private void setRotation(RendererModel model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
	}

}
