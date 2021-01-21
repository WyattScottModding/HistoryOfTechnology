package specialRender;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;

import init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import tileEntities.TileEntityTerniLapilli;

public class RenderTerniLapilli extends TileEntityRenderer<TileEntityTerniLapilli>
{
	static World world = Minecraft.getInstance().world;
	private static final ItemEntity rock_white = new ItemEntity(world, 0, 0, 0, new ItemStack(ItemInit.ROCK_WHITE));
	private static final ItemEntity rock_black = new ItemEntity(world, 0, 0, 0, new ItemStack(ItemInit.ROCK_BLACK));

	@Override
	public void render(TileEntityTerniLapilli tileEntity, double x, double y, double z, float partialTicks,
			int destroyStage)
	{
		super.render(tileEntity, x, y, z, partialTicks, destroyStage);
		ItemStackHandler handler = tileEntity.getHandler();

		//rock_white.hoverStart = 0.0F;
		//rock_black.hoverStart = 0.0F;

		GL11.glPushMatrix();
		{
			GlStateManager.translated(x, y, z);
			GlStateManager.rotatef(90F, 1F, 0F, 0F);
			GlStateManager.translatef(0.7F, 0.55F, -0.2F);
			GlStateManager.scalef(0.3F, 0.3F, 0.3F);


			ItemStack stack = handler.getStackInSlot(0);

			if(stack.isItemEqual(new ItemStack(ItemInit.ROCK_WHITE)))
				Minecraft.getInstance().getRenderManager().renderEntity(rock_white, 0, 0, 0, 0F, 0F, false);
			else if(stack.isItemEqual(new ItemStack(ItemInit.ROCK_BLACK)))
				Minecraft.getInstance().getRenderManager().renderEntity(rock_black, 0, 0, 0, 0F, 0F, false);


			stack = handler.getStackInSlot(1);
			GlStateManager.translatef(-0.68F, 0.0F, 0.0F);

			if(stack.isItemEqual(new ItemStack(ItemInit.ROCK_WHITE)))
				Minecraft.getInstance().getRenderManager().renderEntity(rock_white, 0, 0, 0, 0F, 0F, false);
			else if(stack.isItemEqual(new ItemStack(ItemInit.ROCK_BLACK)))
				Minecraft.getInstance().getRenderManager().renderEntity(rock_black, 0, 0, 0, 0F, 0F, false);


			stack = handler.getStackInSlot(2);
			GlStateManager.translatef(-0.68F, 0.0F, 0.0F);

			if(stack.isItemEqual(new ItemStack(ItemInit.ROCK_WHITE)))
				Minecraft.getInstance().getRenderManager().renderEntity(rock_white, 0, 0, 0, 0F, 0F, false);
			else if(stack.isItemEqual(new ItemStack(ItemInit.ROCK_BLACK)))
				Minecraft.getInstance().getRenderManager().renderEntity(rock_black, 0, 0, 0, 0F, 0F, false);


			stack = handler.getStackInSlot(3);
			GlStateManager.translatef(1.36F, -0.7F, 0.0F);

			if(stack.isItemEqual(new ItemStack(ItemInit.ROCK_WHITE)))
				Minecraft.getInstance().getRenderManager().renderEntity(rock_white, 0, 0, 0, 0F, 0F, false);
			else if(stack.isItemEqual(new ItemStack(ItemInit.ROCK_BLACK)))
				Minecraft.getInstance().getRenderManager().renderEntity(rock_black, 0, 0, 0, 0F, 0F, false);


			stack = handler.getStackInSlot(4);
			GlStateManager.translatef(-0.68F, 0.0F, 0.0F);

			if(stack.isItemEqual(new ItemStack(ItemInit.ROCK_WHITE)))
				Minecraft.getInstance().getRenderManager().renderEntity(rock_white, 0, 0, 0, 0F, 0F, false);
			else if(stack.isItemEqual(new ItemStack(ItemInit.ROCK_BLACK)))
				Minecraft.getInstance().getRenderManager().renderEntity(rock_black, 0, 0, 0, 0F, 0F, false);


			stack = handler.getStackInSlot(5);
			GlStateManager.translatef(-0.68F, 0.0F, 0.0F);

			if(stack.isItemEqual(new ItemStack(ItemInit.ROCK_WHITE)))
				Minecraft.getInstance().getRenderManager().renderEntity(rock_white, 0, 0, 0, 0F, 0F, false);
			else if(stack.isItemEqual(new ItemStack(ItemInit.ROCK_BLACK)))
				Minecraft.getInstance().getRenderManager().renderEntity(rock_black, 0, 0, 0, 0F, 0F, false);
			
			
			stack = handler.getStackInSlot(6);
			GlStateManager.translatef(1.36F, -0.7F, 0.0F);

			if(stack.isItemEqual(new ItemStack(ItemInit.ROCK_WHITE)))
				Minecraft.getInstance().getRenderManager().renderEntity(rock_white, 0, 0, 0, 0F, 0F, false);
			else if(stack.isItemEqual(new ItemStack(ItemInit.ROCK_BLACK)))
				Minecraft.getInstance().getRenderManager().renderEntity(rock_black, 0, 0, 0, 0F, 0F, false);
			
			
			stack = handler.getStackInSlot(7);
			GlStateManager.translatef(-0.68F, 0.0F, 0.0F);

			if(stack.isItemEqual(new ItemStack(ItemInit.ROCK_WHITE)))
				Minecraft.getInstance().getRenderManager().renderEntity(rock_white, 0, 0, 0, 0F, 0F, false);
			else if(stack.isItemEqual(new ItemStack(ItemInit.ROCK_BLACK)))
				Minecraft.getInstance().getRenderManager().renderEntity(rock_black, 0, 0, 0, 0F, 0F, false);
			
			
			stack = handler.getStackInSlot(8);
			GlStateManager.translatef(-0.68F, 0.0F, 0.0F);

			if(stack.isItemEqual(new ItemStack(ItemInit.ROCK_WHITE)))
				Minecraft.getInstance().getRenderManager().renderEntity(rock_white, 0, 0, 0, 0F, 0F, false);
			else if(stack.isItemEqual(new ItemStack(ItemInit.ROCK_BLACK)))
				Minecraft.getInstance().getRenderManager().renderEntity(rock_black, 0, 0, 0, 0F, 0F, false);
		}
		GL11.glPopMatrix();
	}

}