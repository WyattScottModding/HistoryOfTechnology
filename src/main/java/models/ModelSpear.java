package models;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.entity.Entity;

public class ModelSpear extends Model
{
  //fields
    RendererModel Base;
    RendererModel Flint1;
    RendererModel Flint2;
    RendererModel Flint3;
  
  public ModelSpear()
  {
    textureWidth = 32;
    textureHeight = 32;
    
      Base = new RendererModel(this, 0, 0);
      Base.addBox(0F, 0F, 0F, 2, 16, 2);
      Base.setRotationPoint(-1F, 8F, -1F);
      Base.setTextureSize(32, 32);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      Flint1 = new RendererModel(this, 10, 0);
      Flint1.addBox(0F, 0F, 0F, 3, 1, 1);
      Flint1.setRotationPoint(-1.5F, 7F, -0.5F);
      Flint1.setTextureSize(32, 32);
      Flint1.mirror = true;
      setRotation(Flint1, 0F, 0F, 0F);
      Flint2 = new RendererModel(this, 10, 3);
      Flint2.addBox(0F, 0F, 0F, 2, 1, 1);
      Flint2.setRotationPoint(-1F, 6F, -0.5F);
      Flint2.setTextureSize(32, 32);
      Flint2.mirror = true;
      setRotation(Flint2, 0F, 0F, 0F);
      Flint3 = new RendererModel(this, 10, 6);
      Flint3.addBox(0F, 0F, 0F, 1, 1, 1);
      Flint3.setRotationPoint(-0.5F, 5F, -0.5F);
      Flint3.setTextureSize(32, 32);
      Flint3.mirror = true;
      setRotation(Flint3, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Base.render(f5);
    Flint1.render(f5);
    Flint2.render(f5);
    Flint3.render(f5);
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
