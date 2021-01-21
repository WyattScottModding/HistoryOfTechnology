package models;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.entity.Entity;

public class ModelT extends Model
{
  RendererModel MainBody1;
  RendererModel Questiony1;
  RendererModel BlackCover1;
  RendererModel MainBody2;
  RendererModel BackFender;
  RendererModel BlackCover2;
  RendererModel BlackCover3;
  RendererModel MainBody3;
  RendererModel MainBody4;
  RendererModel MainBody5;
  RendererModel FrontButton1;
  RendererModel Questiony2;
  RendererModel MainBody6;
  RendererModel Wheel1;
  RendererModel MainBody7;
  RendererModel MainBody8;
  RendererModel MainBody9;
  RendererModel SupportBeam1;
  RendererModel Roof1;
  RendererModel SupportBeam2;
  RendererModel BackBeam1;
  RendererModel SupportBeam3;
  RendererModel Roof2;
  RendererModel SupportBeam4;
  RendererModel SupportBeam5;
  RendererModel SupportBeam6;
  RendererModel MainBody10;
  RendererModel MainBody11;
  RendererModel Questiony3;
  RendererModel MainBody12;
  RendererModel Questiony4;
  RendererModel Questiony5;
  RendererModel Questiony6;
  RendererModel Questiony7;
  RendererModel Questiony8;
  RendererModel FrontButton2;
  RendererModel MainBody20;
  RendererModel Wheel2;
  RendererModel Wheel3;
  RendererModel MainBody13;
  RendererModel Wheel4;
  RendererModel Wheel5;
  RendererModel MainBody14;
  RendererModel FrontLight1;
  RendererModel FrontLight2;
  RendererModel FrontLight3;
  RendererModel FrontLight4;
  RendererModel FrontLight5;
  RendererModel FrontLight6;
  RendererModel MainBody15;
  RendererModel Wheel6;
  RendererModel Wheel7;
  RendererModel MainBody16;
  RendererModel Wheel8;
  RendererModel Wheel9;
  RendererModel Chair1;
  RendererModel Chair2;
  RendererModel Chair3;
  RendererModel Chair4;
  RendererModel Chair5;
  RendererModel Chair6;
  RendererModel MainBody17;
  RendererModel BlackCover4;
  RendererModel BlackCover5;
  RendererModel Chair7;
  RendererModel MainBody18;
  RendererModel StickShift1;
  RendererModel BlackCover6;
  RendererModel SupportBeam7;
  RendererModel BackBeam2;
  RendererModel BackBeam3;
  RendererModel BackBeam4;
  RendererModel BackBeam5;
  RendererModel BackBeam6;
  RendererModel BackBeam7;
  RendererModel BackBeam8;
  RendererModel FrontFender;
  RendererModel MainBody19;
  RendererModel Wheel13;
  RendererModel Wheel14;
  RendererModel Wheel10;
  RendererModel Wheel11;
  RendererModel Wheel12;
  RendererModel BlackCover7;
  RendererModel StickShift2;
  
  public ModelT()
  {
    this.textureHeight = 256;
    this.textureWidth = 256;
    
    this.MainBody1 = new RendererModel(this, 0, 45);
    this.MainBody1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 4);
    this.MainBody1.setRotationPoint(-30.0F, 18.0F, 17.0F);
    this.MainBody1.setTextureSize(256, 256);
    this.MainBody1.showModel = true;
    setRotation(this.MainBody1, 0.0F, 0.0F, 0.0F);
    
    this.Questiony1 = new RendererModel(this, 111, 105);
    this.Questiony1.addBox(0.0F, -3.1F, -1.0F, 4, 4, 4);
    this.Questiony1.setRotationPoint(-9.0F, 5.5F, -23.9F);
    this.Questiony1.setTextureSize(256, 256);
    this.Questiony1.showModel = true;
    setRotation(this.Questiony1, -0.4363323F, 0.0F, 0.0F);
    
    this.BlackCover1 = new RendererModel(this, 151, 99);
    this.BlackCover1.addBox(0.0F, 0.0F, 0.0F, 34, 21, 2);
    this.BlackCover1.setRotationPoint(-28.0F, -2.0F, 35.0F);
    this.BlackCover1.setTextureSize(256, 256);
    this.BlackCover1.showModel = true;
    setRotation(this.BlackCover1, 0.0F, 0.0F, 0.0F);
    
    this.MainBody2 = new RendererModel(this, 0, 41);
    this.MainBody2.addBox(0.0F, 0.0F, 0.0F, 2, 21, 7);
    this.MainBody2.setRotationPoint(-30.0F, -2.0F, 21.0F);
    this.MainBody2.setTextureSize(256, 256);
    this.MainBody2.showModel = true;
    setRotation(this.MainBody2, -0.1919862F, 0.0F, 0.0F);
    
    this.BackFender = new RendererModel(this, 69, 58);
    this.BackFender.addBox(0.0F, 0.0F, 0.0F, 42, 3, 7);
    this.BackFender.setRotationPoint(-32.0F, 17.5F, 34.5F);
    this.BackFender.setTextureSize(256, 256);
    this.BackFender.showModel = true;
    setRotation(this.BackFender, 0.0F, 0.0F, 0.0F);
    
    this.BlackCover2 = new RendererModel(this, 145, 99);
    this.BlackCover2.addBox(0.0F, 2.0F, 3.0F, 34, 21, 4);
    this.BlackCover2.setRotationPoint(-28.0F, -2.0F, -17.0F);
    this.BlackCover2.setTextureSize(256, 256);
    this.BlackCover2.showModel = true;
    setRotation(this.BlackCover2, 0.1919862F, 0.0F, 0.0F);
    
    this.BlackCover3 = new RendererModel(this, 118, 99);
    this.BlackCover3.addBox(0.0F, 0.0F, 0.0F, 34, 1, 43);
    this.BlackCover3.setRotationPoint(-28.0F, 19.0F, -6.0F);
    this.BlackCover3.setTextureSize(256, 256);
    this.BlackCover3.showModel = true;
    setRotation(this.BlackCover3, 0.0F, 0.0F, 0.0F);
    
    this.MainBody3 = new RendererModel(this, 0, 53);
    this.MainBody3.addBox(0.0F, 0.0F, 0.0F, 2, 17, 3);
    this.MainBody3.setRotationPoint(-30.0F, 3.0F, 34.0F);
    this.MainBody3.setTextureSize(256, 256);
    this.MainBody3.showModel = true;
    setRotation(this.MainBody3, 0.0F, 0.0F, 0.0F);
    
    this.MainBody4 = new RendererModel(this, 0, 38);
    this.MainBody4.addBox(0.0F, 0.0F, 0.0F, 2, 21, 7);
    this.MainBody4.setRotationPoint(6.0F, -2.0F, 21.0F);
    this.MainBody4.setTextureSize(256, 256);
    this.MainBody4.showModel = true;
    setRotation(this.MainBody4, -0.1919862F, 0.0F, 0.0F);
    
    this.MainBody5 = new RendererModel(this, 0, 89);
    this.MainBody5.addBox(0.0F, 0.0F, 0.0F, 38, 22, 2);
    this.MainBody5.setRotationPoint(-30.0F, -2.0F, 37.0F);
    this.MainBody5.setTextureSize(256, 256);
    this.MainBody5.showModel = true;
    setRotation(this.MainBody5, 0.0F, 0.0F, 0.0F);
    
    this.FrontButton1 = new RendererModel(this, 70, 58);
    this.FrontButton1.addBox(0.0F, 0.3F, -1.9F, 2, 2, 6);
    this.FrontButton1.setRotationPoint(-6.0F, 13.0F, -25.0F);
    this.FrontButton1.setTextureSize(256, 256);
    this.FrontButton1.showModel = true;
    setRotation(this.FrontButton1, -0.122173F, 0.0F, 0.0F);
    
    this.Questiony2 = new RendererModel(this, 110, 104);
    this.Questiony2.addBox(0.0F, 2.5F, -1.9F, 4, 4, 6);
    this.Questiony2.setRotationPoint(-13.0F, 6.0F, -23.5F);
    this.Questiony2.setTextureSize(256, 256);
    this.Questiony2.showModel = true;
    setRotation(this.Questiony2, -0.2094395F, 0.0F, 0.0F);
    
    this.MainBody6 = new RendererModel(this, 0, 94);
    this.MainBody6.addBox(0.0F, 0.0F, 0.0F, 38, 16, 11);
    this.MainBody6.setRotationPoint(-30.0F, -2.0F, -20.7F);
    this.MainBody6.setTextureSize(256, 256);
    this.MainBody6.showModel = true;
    setRotation(this.MainBody6, 0.0F, 0.0F, 0.0F);
    
    this.Wheel1 = new RendererModel(this, 159, 99);
    this.Wheel1.addBox(0.0F, 0.0F, 0.0F, 2, 5, 2);
    this.Wheel1.setRotationPoint(-1.5F, 0.0F, -5.0F);
    this.Wheel1.setTextureSize(256, 256);
    this.Wheel1.showModel = true;
    setRotation(this.Wheel1, 0.0F, 0.0F, 0.0F);
    
    this.MainBody7 = new RendererModel(this, 0, 86);
    this.MainBody7.addBox(0.0F, 1.5F, 2.0F, 2, 21, 5);
    this.MainBody7.setRotationPoint(6.0F, -2.0F, -17.0F);
    this.MainBody7.setTextureSize(256, 256);
    this.MainBody7.showModel = true;
    setRotation(this.MainBody7, 0.1919862F, 0.0F, 0.0F);
    
    this.MainBody8 = new RendererModel(this, 0, 90);
    this.MainBody8.addBox(0.0F, 0.0F, 0.0F, 2, 17, 11);
    this.MainBody8.setRotationPoint(6.0F, -2.0F, -20.7F);
    this.MainBody8.setTextureSize(256, 256);
    this.MainBody8.showModel = true;
    setRotation(this.MainBody8, 0.0F, 0.0F, 0.0F);
    
    this.MainBody9 = new RendererModel(this, 0, 79);
    this.MainBody9.addBox(0.0F, 0.0F, 0.0F, 2, 2, 32);
    this.MainBody9.setRotationPoint(6.0F, 18.0F, -11.0F);
    this.MainBody9.setTextureSize(256, 256);
    this.MainBody9.showModel = true;
    setRotation(this.MainBody9, 0.0F, 0.0F, 0.0F);
    
    this.SupportBeam1 = new RendererModel(this, 77, 56);
    this.SupportBeam1.addBox(0.0F, 2.0F, 2.0F, 1, 8, 1);
    this.SupportBeam1.setRotationPoint(-30.0F, -25.0F, -17.5F);
    this.SupportBeam1.setTextureSize(256, 256);
    this.SupportBeam1.showModel = true;
    setRotation(this.SupportBeam1, -0.122173F, 0.0F, 0.0F);
    
    this.Roof1 = new RendererModel(this, 0, 196);
    this.Roof1.addBox(0.0F, 0.0F, 0.0F, 37, 3, 55);
    this.Roof1.setRotationPoint(-29.5F, -29.0F, -16.5F);
    this.Roof1.setTextureSize(256, 256);
    this.Roof1.showModel = true;
    setRotation(this.Roof1, 0.0F, 0.0F, 0.0F);
    
    this.SupportBeam2 = new RendererModel(this, 75, 53);
    this.SupportBeam2.addBox(0.0F, 0.0F, 0.0F, 1, 23, 1);
    this.SupportBeam2.setRotationPoint(-29.5F, -25.0F, 37.5F);
    this.SupportBeam2.setTextureSize(256, 256);
    this.SupportBeam2.showModel = true;
    setRotation(this.SupportBeam2, 0.0F, 0.0F, 0.0F);
    
    this.BackBeam1 = new RendererModel(this, 75, 53);
    this.BackBeam1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
    this.BackBeam1.setRotationPoint(-24.5F, 3.0F, 40.0F);
    this.BackBeam1.setTextureSize(256, 256);
    this.BackBeam1.showModel = true;
    setRotation(this.BackBeam1, 0.0F, 0.0F, 0.0F);
    
    this.SupportBeam3 = new RendererModel(this, 75, 53);
    this.SupportBeam3.addBox(0.0F, 0.0F, 0.0F, 35, 1, 1);
    this.SupportBeam3.setRotationPoint(-28.5F, -13.0F, 37.5F);
    this.SupportBeam3.setTextureSize(256, 256);
    this.SupportBeam3.showModel = true;
    setRotation(this.SupportBeam3, 0.0F, 0.0F, 0.0F);
    
    this.Roof2 = new RendererModel(this, 0, 196);
    this.Roof2.addBox(0.0F, 0.0F, 0.0F, 39, 3, 57);
    this.Roof2.setRotationPoint(-30.5F, -28.0F, -17.5F);
    this.Roof2.setTextureSize(256, 256);
    this.Roof2.showModel = true;
    setRotation(this.Roof2, 0.0F, 0.0F, 0.0F);
    
    this.SupportBeam4 = new RendererModel(this, 75, 53);
    this.SupportBeam4.addBox(0.0F, -1.0F, 2.0F, 1, 24, 1);
    this.SupportBeam4.setRotationPoint(6.5F, -25.0F, -17.5F);
    this.SupportBeam4.setTextureSize(256, 256);
    this.SupportBeam4.showModel = true;
    setRotation(this.SupportBeam4, -0.122173F, 0.0F, 0.0F);
    
    this.SupportBeam5 = new RendererModel(this, 75, 53);
    this.SupportBeam5.addBox(0.0F, 9.0F, 2.0F, 1, 16, 1);
    this.SupportBeam5.setRotationPoint(-29.5F, -25.0F, -17.5F);
    this.SupportBeam5.setTextureSize(256, 256);
    this.SupportBeam5.showModel = true;
    setRotation(this.SupportBeam5, -0.122173F, 0.0F, 0.0F);
    
    this.SupportBeam6 = new RendererModel(this, 77, 56);
    this.SupportBeam6.addBox(0.0F, -1.0F, 2.0F, 1, 4, 1);
    this.SupportBeam6.setRotationPoint(-29.5F, -25.0F, -17.5F);
    this.SupportBeam6.setTextureSize(256, 256);
    this.SupportBeam6.showModel = true;
    setRotation(this.SupportBeam6, -0.122173F, 0.0F, 0.0F);
    
    this.MainBody10 = new RendererModel(this, 0, 95);
    this.MainBody10.addBox(0.0F, 0.0F, 0.0F, 38, 3, 6);
    this.MainBody10.setRotationPoint(-30.0F, 17.0F, -26.5F);
    this.MainBody10.setTextureSize(256, 256);
    this.MainBody10.showModel = true;
    setRotation(this.MainBody10, 0.0F, 0.0F, 0.0F);
   
    this.MainBody11 = new RendererModel(this, 0, 102);
    this.MainBody11.addBox(0.0F, -0.5F, -0.9F, 38, 7, 6);
    this.MainBody11.setRotationPoint(-30.0F, 6.0F, -23.5F);
    this.MainBody11.setTextureSize(256, 256);
    this.MainBody11.showModel = true;
    setRotation(this.MainBody11, -0.2094395F, 0.0F, 0.0F);
    
    this.Questiony3 = new RendererModel(this, 110, 104);
    this.Questiony3.addBox(0.0F, -1.5F, -1.9F, 4, 2, 6);
    this.Questiony3.setRotationPoint(-9.0F, 7.0F, -23.5F);
    this.Questiony3.setTextureSize(256, 256);
    this.Questiony3.showModel = true;
    setRotation(this.Questiony3, -0.2094395F, 0.0F, 0.0F);
    
    this.MainBody12 = new RendererModel(this, 0, 93);
    this.MainBody12.addBox(0.0F, -0.7F, -0.9F, 38, 5, 6);
    this.MainBody12.setRotationPoint(-30.0F, 13.0F, -25.0F);
    this.MainBody12.setTextureSize(256, 256);
    this.MainBody12.showModel = true;
    setRotation(this.MainBody12, -0.122173F, 0.0F, 0.0F);
    
    this.Questiony4 = new RendererModel(this, 110, 104);
    this.Questiony4.addBox(0.0F, -1.5F, -1.9F, 5, 2, 6);
    this.Questiony4.setRotationPoint(-11.0F, 7.0F, -23.5F);
    this.Questiony4.setTextureSize(256, 256);
    this.Questiony4.showModel = true;
    setRotation(this.Questiony4, -0.2094395F, 0.0F, 0.0F);
    
    this.Questiony5 = new RendererModel(this, 111, 105);
    this.Questiony5.addBox(0.0F, -5.1F, -1.0F, 4, 4, 4);
    this.Questiony5.setRotationPoint(-17.0F, 5.5F, -23.9F);
    this.Questiony5.setTextureSize(256, 256);
    this.Questiony5.showModel = true;
    setRotation(this.Questiony5, -0.4363323F, 0.0F, 0.0F);
    
    this.Questiony6 = new RendererModel(this, 111, 105);
    this.Questiony6.addBox(0.0F, -5.1F, -1.0F, 10, 3, 4);
    this.Questiony6.setRotationPoint(-15.0F, 5.5F, -23.9F);
    this.Questiony6.setTextureSize(256, 256);
    this.Questiony6.showModel = true;
    setRotation(this.Questiony6, -0.4363323F, 0.0F, 0.0F);
    
    this.Questiony7 = new RendererModel(this, 110, 104);
    this.Questiony7.addBox(0.0F, -0.5F, -1.9F, 7, 2, 6);
    this.Questiony7.setRotationPoint(-12.0F, 7.0F, -23.5F);
    this.Questiony7.setTextureSize(256, 256);
    this.Questiony7.showModel = true;
    setRotation(this.Questiony7, -0.2094395F, 0.0F, 0.0F);
    
    this.Questiony8 = new RendererModel(this, 112, 104);
    this.Questiony8.addBox(0.0F, 0.3F, -1.9F, 4, 3, 6);
    this.Questiony8.setRotationPoint(-13.0F, 13.0F, -25.0F);
    this.Questiony8.setTextureSize(256, 256);
    this.Questiony8.showModel = true;
    setRotation(this.Questiony8, -0.122173F, 0.0F, 0.0F);
    
    this.FrontButton2 = new RendererModel(this, 70, 58);
    this.FrontButton2.addBox(0.0F, 0.3F, -1.9F, 2, 2, 6);
    this.FrontButton2.setRotationPoint(-18.0F, 13.0F, -25.0F);
    this.FrontButton2.setTextureSize(256, 256);
    this.FrontButton2.showModel = true;
    setRotation(this.FrontButton2, -0.122173F, 0.0F, 0.0F);
    
    this.MainBody20 = new RendererModel(this, 0, 42);
    this.MainBody20.addBox(0.0F, 0.0F, 0.0F, 2, 22, 4);
    this.MainBody20.setRotationPoint(6.0F, -2.0F, 21.0F);
    this.MainBody20.setTextureSize(256, 256);
    this.MainBody20.showModel = true;
    setRotation(this.MainBody20, 0.0F, 0.0F, 0.0F);
    
    this.Wheel2 = new RendererModel(this, 30, 147);
    this.Wheel2.addBox(0.0F, 0.0F, 0.3F, 2, 8, 7);
    this.Wheel2.setRotationPoint(-31.5F, 16.0F, 25.6F);
    this.Wheel2.setTextureSize(256, 256);
    this.Wheel2.showModel = true;
    setRotation(this.Wheel2, 0.0F, 0.0F, 0.0F);
    
    this.Wheel3 = new RendererModel(this, 30, 147);
    this.Wheel3.addBox(0.0F, 0.0F, 0.3F, 3, 6, 8);
    this.Wheel3.setRotationPoint(-32.0F, 17.0F, 25.1F);
    this.Wheel3.setTextureSize(256, 256);
    this.Wheel3.showModel = true;
    setRotation(this.Wheel3, 0.0F, 0.0F, 0.0F);
    
    this.MainBody13 = new RendererModel(this, 0, 42);
    this.MainBody13.addBox(0.0F, 0.0F, 0.0F, 2, 17, 12);
    this.MainBody13.setRotationPoint(6.0F, -2.0F, 25.0F);
    this.MainBody13.setTextureSize(256, 256);
    this.MainBody13.showModel = true;
    setRotation(this.MainBody13, 0.0F, 0.0F, 0.0F);
    
    this.Wheel4 = new RendererModel(this, 30, 147);
    this.Wheel4.addBox(0.0F, 0.0F, 0.3F, 2, 8, 7);
    this.Wheel4.setRotationPoint(-31.5F, 16.0F, -19.5F);
    this.Wheel4.setTextureSize(256, 256);
    this.Wheel4.showModel = true;
    setRotation(this.Wheel4, 0.0F, 0.0F, 0.0F);
    
    this.Wheel5 = new RendererModel(this, 30, 147);
    this.Wheel5.addBox(0.0F, 0.0F, 0.3F, 3, 6, 8);
    this.Wheel5.setRotationPoint(-32.0F, 17.0F, -20.0F);
    this.Wheel5.setTextureSize(256, 256);
    this.Wheel5.showModel = true;
    setRotation(this.Wheel5, 0.0F, 0.0F, 0.0F);
    
    this.MainBody14 = new RendererModel(this, 0, 105);
    this.MainBody14.addBox(0.0F, -7.1F, 0.0F, 38, 8, 4);
    this.MainBody14.setRotationPoint(-30.0F, 4.5F, -23.9F);
    this.MainBody14.setTextureSize(256, 256);
    this.MainBody14.showModel = true;
    setRotation(this.MainBody14, -0.4363323F, 0.0F, 0.0F);
    
    this.FrontLight1 = new RendererModel(this, 155, 150);
    this.FrontLight1.addBox(0.0F, -5.1F, -1.0F, 4, 2, 4);
    this.FrontLight1.setRotationPoint(0.0F, 4.5F, -23.9F);
    this.FrontLight1.setTextureSize(256, 256);
    this.FrontLight1.showModel = true;
    setRotation(this.FrontLight1, -0.4363323F, 0.0F, 0.0F);
    
    this.FrontLight2 = new RendererModel(this, 121, 150);
    this.FrontLight2.addBox(-1.0F, -0.1F, -1.0F, 6, 2, 4);
    this.FrontLight2.setRotationPoint(0.0F, 4.5F, -23.9F);
    this.FrontLight2.setTextureSize(256, 256);
    this.FrontLight2.showModel = true;
    setRotation(this.FrontLight2, -0.4363323F, 0.0F, 0.0F);
    
    this.FrontLight3 = new RendererModel(this, 155, 150);
    this.FrontLight3.addBox(-1.0F, -4.1F, -1.0F, 6, 4, 4);
    this.FrontLight3.setRotationPoint(0.0F, 4.5F, -23.9F);
    this.FrontLight3.setTextureSize(256, 256);
    this.FrontLight3.showModel = true;
    setRotation(this.FrontLight3, -0.4363323F, 0.0F, 0.0F);
    
    this.FrontLight4 = new RendererModel(this, 155, 150);
    this.FrontLight4.addBox(0.0F, -5.1F, -1.0F, 4, 2, 4);
    this.FrontLight4.setRotationPoint(-26.0F, 4.5F, -23.9F);
    this.FrontLight4.setTextureSize(256, 256);
    this.FrontLight4.showModel = true;
    setRotation(this.FrontLight4, -0.4363323F, 0.0F, 0.0F);
    
    this.FrontLight5 = new RendererModel(this, 155, 150);
    this.FrontLight5.addBox(-1.0F, -4.1F, -1.0F, 6, 4, 4);
    this.FrontLight5.setRotationPoint(-26.0F, 4.5F, -23.9F);
    this.FrontLight5.setTextureSize(256, 256);
    this.FrontLight5.showModel = true;
    setRotation(this.FrontLight5, -0.4363323F, 0.0F, 0.0F);
    
    this.FrontLight6 = new RendererModel(this, 121, 150);
    this.FrontLight6.addBox(-1.0F, -0.1F, -1.0F, 6, 2, 4);
    this.FrontLight6.setRotationPoint(-26.0F, 4.5F, -23.9F);
    this.FrontLight6.setTextureSize(256, 256);
    this.FrontLight6.showModel = true;
    setRotation(this.FrontLight6, -0.4363323F, 0.0F, 0.0F);
    
    this.MainBody15 = new RendererModel(this, 0, 87);
    this.MainBody15.addBox(0.0F, 0.0F, 0.0F, 2, 2, 32);
    this.MainBody15.setRotationPoint(-30.0F, 18.0F, -11.0F);
    this.MainBody15.setTextureSize(256, 256);
    this.MainBody15.showModel = true;
    setRotation(this.MainBody15, 0.0F, 0.0F, 0.0F);
    
    this.Wheel6 = new RendererModel(this, 30, 147);
    this.Wheel6.addBox(0.0F, 0.0F, 0.3F, 2, 8, 7);
    this.Wheel6.setRotationPoint(7.5F, 16.0F, -19.5F);
    this.Wheel6.setTextureSize(256, 256);
    this.Wheel6.showModel = true;
    setRotation(this.Wheel6, 0.0F, 0.0F, 0.0F);
    
    this.Wheel7 = new RendererModel(this, 30, 147);
    this.Wheel7.addBox(0.0F, 0.0F, 0.3F, 3, 6, 8);
    this.Wheel7.setRotationPoint(7.0F, 17.0F, -20.0F);
    this.Wheel7.setTextureSize(256, 256);
    this.Wheel7.showModel = true;
    setRotation(this.Wheel7, 0.0F, 0.0F, 0.0F);
    
    this.MainBody16 = new RendererModel(this, 0, 49);
    this.MainBody16.addBox(0.0F, 0.0F, 0.0F, 2, 17, 3);
    this.MainBody16.setRotationPoint(6.0F, 3.0F, 34.0F);
    this.MainBody16.setTextureSize(256, 256);
    this.MainBody16.showModel = true;
    setRotation(this.MainBody16, 0.0F, 0.0F, 0.0F);
    
    this.Wheel8 = new RendererModel(this, 30, 147);
    this.Wheel8.addBox(0.0F, 0.0F, 0.3F, 2, 8, 7);
    this.Wheel8.setRotationPoint(7.5F, 16.0F, 25.6F);
    this.Wheel8.setTextureSize(256, 256);
    this.Wheel8.showModel = true;
    setRotation(this.Wheel8, 0.0F, 0.0F, 0.0F);
    
    this.Wheel9 = new RendererModel(this, 30, 146);
    this.Wheel9.addBox(0.0F, 0.0F, 0.3F, 3, 6, 8);
    this.Wheel9.setRotationPoint(7.0F, 17.0F, 25.1F);
    this.Wheel9.setTextureSize(256, 256);
    this.Wheel9.showModel = true;
    setRotation(this.Wheel9, 0.0F, 0.0F, 0.0F);
    
    this.Chair1 = new RendererModel(this, 146, 26);
    this.Chair1.addBox(0.0F, 1.0F, -1.0F, 12, 14, 3);
    this.Chair1.setRotationPoint(-9.0F, -4.0F, 18.0F);
    this.Chair1.setTextureSize(256, 256);
    this.Chair1.showModel = true;
    setRotation(this.Chair1, -0.122173F, 0.0F, 0.0F);
    
    this.Chair2 = new RendererModel(this, 129, 30);
    this.Chair2.addBox(0.0F, 0.0F, 0.0F, 12, 3, 10);
    this.Chair2.setRotationPoint(-9.0F, 11.0F, 4.0F);
    this.Chair2.setTextureSize(256, 256);
    this.Chair2.showModel = true;
    setRotation(this.Chair2, 0.0F, 0.0F, 0.0F);
    
    this.Chair3 = new RendererModel(this, 155, 107);
    this.Chair3.addBox(0.0F, 0.0F, 3.0F, 32, 22, 3);
    this.Chair3.setRotationPoint(-27.0F, -4.0F, 18.0F);
    this.Chair3.setTextureSize(256, 256);
    this.Chair3.showModel = true;
    setRotation(this.Chair3, -0.122173F, 0.0F, 0.0F);
    
    this.Chair4 = new RendererModel(this, 146, 26);
    this.Chair4.addBox(0.0F, 1.0F, -1.0F, 12, 14, 3);
    this.Chair4.setRotationPoint(-25.0F, -4.0F, 18.0F);
    this.Chair4.setTextureSize(256, 256);
    this.Chair4.showModel = true;
    setRotation(this.Chair4, -0.122173F, 0.0F, 0.0F);
    
    this.Chair5 = new RendererModel(this, 169, 62);
    this.Chair5.addBox(0.0F, 0.0F, 0.0F, 32, 3, 16);
    this.Chair5.setRotationPoint(-27.0F, 12.0F, 3.0F);
    this.Chair5.setTextureSize(256, 256);
    this.Chair5.showModel = true;
    setRotation(this.Chair5, 0.0F, 0.0F, 0.0F);
    
    this.Chair6 = new RendererModel(this, 129, 30);
    this.Chair6.addBox(0.0F, 0.0F, 0.0F, 12, 3, 10);
    this.Chair6.setRotationPoint(-25.0F, 11.0F, 4.0F);
    this.Chair6.setTextureSize(256, 256);
    this.Chair6.showModel = true;
    setRotation(this.Chair6, 0.0F, 0.0F, 0.0F);
    
    this.MainBody17 = new RendererModel(this, 0, 50);
    this.MainBody17.addBox(0.0F, 0.0F, 0.0F, 2, 17, 16);
    this.MainBody17.setRotationPoint(-30.0F, -2.0F, 21.0F);
    this.MainBody17.setTextureSize(256, 256);
    this.MainBody17.showModel = true;
    setRotation(this.MainBody17, 0.0F, 0.0F, 0.0F);
    
    this.BlackCover4 = new RendererModel(this, 151, 99);
    this.BlackCover4.addBox(0.0F, 0.0F, 0.0F, 36, 13, 14);
    this.BlackCover4.setRotationPoint(-29.0F, 7.0F, -24.0F);
    this.BlackCover4.setTextureSize(256, 256);
    this.BlackCover4.showModel = true;
    setRotation(this.BlackCover4, 0.0F, 0.0F, 0.0F);
    
    this.BlackCover5 = new RendererModel(this, 151, 99);
    this.BlackCover5.addBox(0.0F, 0.0F, 0.0F, 2, 21, 16);
    this.BlackCover5.setRotationPoint(-28.0F, -2.0F, 21.0F);
    this.BlackCover5.setTextureSize(256, 256);
    this.BlackCover5.showModel = true;
    setRotation(this.BlackCover5, 0.0F, 0.0F, 0.0F);
    
    this.Chair7 = new RendererModel(this, 175, 62);
    this.Chair7.addBox(0.0F, 0.0F, 0.0F, 32, 16, 3);
    this.Chair7.setRotationPoint(-27.0F, -4.0F, 18.0F);
    this.Chair7.setTextureSize(256, 256);
    this.Chair7.showModel = true;
    setRotation(this.Chair7, -0.122173F, 0.0F, 0.0F);
    
    this.MainBody18 = new RendererModel(this, 0, 43);
    this.MainBody18.addBox(0.0F, 1.5F, 2.0F, 2, 21, 5);
    this.MainBody18.setRotationPoint(-30.0F, -2.0F, -17.0F);
    this.MainBody18.setTextureSize(256, 256);
    this.MainBody18.showModel = true;
    setRotation(this.MainBody18, 0.1919862F, 0.0F, 0.0F);
    
    this.StickShift1 = new RendererModel(this, 118, 101);
    this.StickShift1.addBox(0.0F, -4.5F, 1.5F, 2, 2, 2);
    this.StickShift1.setRotationPoint(-14.5F, 5.0F, -7.0F);
    this.StickShift1.setTextureSize(256, 256);
    this.StickShift1.showModel = true;
    setRotation(this.StickShift1, -0.5061455F, 0.0F, 0.0F);
    
    this.BlackCover6 = new RendererModel(this, 151, 99);
    this.BlackCover6.addBox(0.0F, 0.0F, 0.0F, 2, 21, 16);
    this.BlackCover6.setRotationPoint(4.0F, -2.0F, 21.0F);
    this.BlackCover6.setTextureSize(256, 256);
    this.BlackCover6.showModel = true;
    setRotation(this.BlackCover6, 0.0F, 0.0F, 0.0F);
    
    this.SupportBeam7 = new RendererModel(this, 75, 53);
    this.SupportBeam7.addBox(0.0F, 0.0F, 0.0F, 1, 23, 1);
    this.SupportBeam7.setRotationPoint(6.5F, -25.0F, 37.5F);
    this.SupportBeam7.setTextureSize(256, 256);
    this.SupportBeam7.showModel = true;
    setRotation(this.SupportBeam7, 0.0F, 0.0F, 0.0F);
    
    this.BackBeam2 = new RendererModel(this, 75, 53);
    this.BackBeam2.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
    this.BackBeam2.setRotationPoint(-25.5F, 3.0F, 39.0F);
    this.BackBeam2.setTextureSize(256, 256);
    this.BackBeam2.showModel = true;
    setRotation(this.BackBeam2, 0.0F, 0.0F, 0.0F);
    
    this.BackBeam3 = new RendererModel(this, 75, 53);
    this.BackBeam3.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
    this.BackBeam3.setRotationPoint(1.5F, 3.0F, 39.0F);
    this.BackBeam3.setTextureSize(256, 256);
    this.BackBeam3.showModel = true;
    setRotation(this.BackBeam3, 0.0F, 0.0F, 0.0F);
    
    this.BackBeam4 = new RendererModel(this, 75, 53);
    this.BackBeam4.addBox(0.0F, 0.0F, 0.0F, 2, 10, 2);
    this.BackBeam4.setRotationPoint(-12.0F, 3.0F, 41.0F);
    this.BackBeam4.setTextureSize(256, 256);
    this.BackBeam4.showModel = true;
    setRotation(this.BackBeam4, -0.4014257F, 0.0F, 0.0F);
    
    this.BackBeam5 = new RendererModel(this, 75, 53);
    this.BackBeam5.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
    this.BackBeam5.setRotationPoint(0.5F, 3.0F, 40.0F);
    this.BackBeam5.setTextureSize(256, 256);
    this.BackBeam5.showModel = true;
    setRotation(this.BackBeam5, 0.0F, 0.0F, 0.0F);
    
    this.BackBeam6 = new RendererModel(this, 75, 53);
    this.BackBeam6.addBox(0.0F, 0.0F, 0.0F, 2, 10, 2);
    this.BackBeam6.setRotationPoint(-22.5F, 3.0F, 41.0F);
    this.BackBeam6.setTextureSize(256, 256);
    this.BackBeam6.showModel = true;
    setRotation(this.BackBeam6, -0.4014257F, 0.0F, 0.0F);
    
    this.BackBeam7 = new RendererModel(this, 75, 53);
    this.BackBeam7.addBox(0.0F, 0.0F, 0.0F, 2, 10, 2);
    this.BackBeam7.setRotationPoint(-1.5F, 3.0F, 41.0F);
    this.BackBeam7.setTextureSize(256, 256);
    this.BackBeam7.showModel = true;
    setRotation(this.BackBeam7, -0.4014257F, 0.0F, 0.0F);
    
    this.BackBeam8 = new RendererModel(this, 75, 53);
    this.BackBeam8.addBox(0.0F, 0.0F, 0.0F, 25, 2, 2);
    this.BackBeam8.setRotationPoint(-23.5F, 3.0F, 41.0F);
    this.BackBeam8.setTextureSize(256, 256);
    this.BackBeam8.showModel = true;
    setRotation(this.BackBeam8, 0.0F, 0.0F, 0.0F);
    
    this.FrontFender = new RendererModel(this, 70, 58);
    this.FrontFender.addBox(0.0F, 0.0F, 0.0F, 42, 3, 6);
    this.FrontFender.setRotationPoint(-32.0F, 17.5F, -28.5F);
    this.FrontFender.setTextureSize(256, 256);
    this.FrontFender.showModel = true;
    setRotation(this.FrontFender, 0.0F, 0.0F, 0.0F);
    
    this.MainBody19 = new RendererModel(this, 0, 86);
    this.MainBody19.addBox(0.0F, 0.0F, 0.0F, 38, 17, 11);
    this.MainBody19.setRotationPoint(-30.0F, -2.0F, -20.0F);
    this.MainBody19.setTextureSize(256, 256);
    this.MainBody19.showModel = true;
    setRotation(this.MainBody19, 0.0F, 0.0F, 0.0F);
    
    this.Wheel13 = new RendererModel(this, 75, 53);
    this.Wheel13.addBox(0.0F, 0.0F, 0.0F, 8, 8, 4);
    this.Wheel13.setRotationPoint(-2.0F, -1.0F, -10.0F);
    this.Wheel13.setTextureSize(256, 256);
    this.Wheel13.showModel = true;
    setRotation(this.Wheel8, 0.0F, 0.0F, 0.0F);
    
    this.Wheel14 = new RendererModel(this, 159, 99);
    this.Wheel14.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
    this.Wheel14.setRotationPoint(1.5F, 2.0F, -7.0F);
    this.Wheel14.setTextureSize(256, 256);
    this.Wheel14.showModel = true;
    setRotation(this.Wheel9, 0.0F, 0.0F, 0.0F);
    
    this.Wheel10 = new RendererModel(this, 159, 99);
    this.Wheel10.addBox(0.0F, 0.0F, 0.0F, 5, 2, 2);
    this.Wheel10.setRotationPoint(-0.5F, 4.0F, -5.0F);
    this.Wheel10.setTextureSize(256, 256);
    this.Wheel10.showModel = true;
    setRotation(this.Wheel10, 0.0F, 0.0F, 0.0F);
    
    this.Wheel11 = new RendererModel(this, 159, 99);
    this.Wheel11.addBox(0.0F, 0.0F, 0.0F, 2, 5, 2);
    this.Wheel11.setRotationPoint(3.5F, 0.0F, -5.0F);
    this.Wheel11.setTextureSize(256, 256);
    this.Wheel11.showModel = true;
    setRotation(this.Wheel11, 0.0F, 0.0F, 0.0F);
    
    this.Wheel12 = new RendererModel(this, 159, 99);
    this.Wheel12.addBox(0.0F, 0.0F, 0.0F, 5, 2, 2);
    this.Wheel12.setRotationPoint(-0.5F, -1.0F, -5.0F);
    this.Wheel12.setTextureSize(256, 256);
    this.Wheel12.showModel = true;
    setRotation(this.Wheel12, 0.0F, 0.0F, 0.0F);
    
    this.BlackCover7 = new RendererModel(this, 145, 99);
    this.BlackCover7.addBox(0.0F, 1.5F, 2.0F, 34, 21, 5);
    this.BlackCover7.setRotationPoint(-28.0F, -2.0F, -17.0F);
    this.BlackCover7.setTextureSize(256, 256);
    this.BlackCover7.showModel = true;
    setRotation(this.BlackCover7, 0.1919862F, 0.0F, 0.0F);
    
    this.StickShift2 = new RendererModel(this, 75, 53);
    this.StickShift2.addBox(0.0F, -2.5F, 2.0F, 1, 15, 1);
    this.StickShift2.setRotationPoint(-14.0F, 5.0F, -7.0F);
    this.StickShift2.setTextureSize(256, 256);
    this.StickShift2.showModel = true;
    setRotation(this.StickShift2, -0.5061455F, 0.0F, 0.0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    this.MainBody1.renderWithRotation(f5);
    this.Questiony1.renderWithRotation(f5);
    this.BlackCover1.renderWithRotation(f5);
    this.MainBody2.renderWithRotation(f5);
    this.BackFender.renderWithRotation(f5);
    this.BlackCover2.renderWithRotation(f5);
    this.BlackCover3.renderWithRotation(f5);
    this.MainBody3.renderWithRotation(f5);
    this.MainBody4.renderWithRotation(f5);
    this.MainBody5.renderWithRotation(f5);
    this.FrontButton1.renderWithRotation(f5);
    this.Questiony2.renderWithRotation(f5);
    this.MainBody6.renderWithRotation(f5);
    this.Wheel1.renderWithRotation(f5);
    this.MainBody7.renderWithRotation(f5);
    this.MainBody8.renderWithRotation(f5);
    this.MainBody9.renderWithRotation(f5);
    this.SupportBeam1.renderWithRotation(f5);
    this.Roof1.renderWithRotation(f5);
    this.SupportBeam2.renderWithRotation(f5);
    this.BackBeam1.renderWithRotation(f5);
    this.SupportBeam3.renderWithRotation(f5);
    this.Roof2.renderWithRotation(f5);
    this.SupportBeam4.renderWithRotation(f5);
    this.SupportBeam5.renderWithRotation(f5);
    this.SupportBeam6.renderWithRotation(f5);
    this.MainBody10.renderWithRotation(f5);
    this.MainBody11.renderWithRotation(f5);
    this.Questiony3.renderWithRotation(f5);
    this.MainBody12.renderWithRotation(f5);
    this.Questiony4.renderWithRotation(f5);
    this.Questiony5.renderWithRotation(f5);
    this.Questiony6.renderWithRotation(f5);
    this.Questiony7.renderWithRotation(f5);
    this.Questiony8.renderWithRotation(f5);
    this.FrontButton2.renderWithRotation(f5);
    this.MainBody20.renderWithRotation(f5);
    this.Wheel2.renderWithRotation(f5);
    this.Wheel3.renderWithRotation(f5);
    this.MainBody13.renderWithRotation(f5);
    this.Wheel4.renderWithRotation(f5);
    this.Wheel5.renderWithRotation(f5);
    this.MainBody14.renderWithRotation(f5);
    this.FrontLight1.renderWithRotation(f5);
    this.FrontLight2.renderWithRotation(f5);
    this.FrontLight3.renderWithRotation(f5);
    this.FrontLight4.renderWithRotation(f5);
    this.FrontLight5.renderWithRotation(f5);
    this.FrontLight6.renderWithRotation(f5);
    this.MainBody15.renderWithRotation(f5);
    this.Wheel6.renderWithRotation(f5);
    this.Wheel7.renderWithRotation(f5);
    this.MainBody16.renderWithRotation(f5);
    this.Wheel8.renderWithRotation(f5);
    this.Wheel9.renderWithRotation(f5);
    this.Chair1.renderWithRotation(f5);
    this.Chair2.renderWithRotation(f5);
    this.Chair3.renderWithRotation(f5);
    this.Chair4.renderWithRotation(f5);
    this.Chair5.renderWithRotation(f5);
    this.Chair6.renderWithRotation(f5);
    this.MainBody17.renderWithRotation(f5);
    this.BlackCover4.renderWithRotation(f5);
    this.BlackCover5.renderWithRotation(f5);
    this.Chair7.renderWithRotation(f5);
    this.MainBody18.renderWithRotation(f5);
    this.StickShift1.renderWithRotation(f5);
    this.BlackCover6.renderWithRotation(f5);
    this.SupportBeam7.renderWithRotation(f5);
    this.BackBeam2.renderWithRotation(f5);
    this.BackBeam3.renderWithRotation(f5);
    this.BackBeam4.renderWithRotation(f5);
    this.BackBeam5.renderWithRotation(f5);
    this.BackBeam6.renderWithRotation(f5);
    this.BackBeam7.renderWithRotation(f5);
    this.BackBeam8.renderWithRotation(f5);
    this.FrontFender.renderWithRotation(f5);
    this.MainBody19.renderWithRotation(f5);
    this.Wheel13.renderWithRotation(f5);
    this.Wheel14.renderWithRotation(f5);
    this.Wheel10.renderWithRotation(f5);
    this.Wheel11.renderWithRotation(f5);
    this.Wheel12.renderWithRotation(f5);
    this.BlackCover7.renderWithRotation(f5);
    this.StickShift2.renderWithRotation(f5);
  }
  
  private void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
	
}

private void setRotation(RendererModel model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

}
