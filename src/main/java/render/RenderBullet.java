package render;

import com.mojang.blaze3d.platform.GlStateManager;

import entity.EntityBullet;
import init.ItemInit;
import main.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderBullet extends EntityRenderer<EntityBullet>
{
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/entitybullet.png");

	protected final Item item = ItemInit.BULLET;
	private final net.minecraft.client.renderer.ItemRenderer itemRenderer;

	public RenderBullet(EntityRendererManager renderManagerIn, net.minecraft.client.renderer.ItemRenderer itemR)
	{
		super(renderManagerIn);
		this.itemRenderer = itemR;
	}

	/**
	 * Renders the desired {@code T} type Entity.
	 */
	public void doRender(EntityBullet entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		GlStateManager.pushMatrix();
		GlStateManager.translatef((float)x, (float)y, (float)z);
		GlStateManager.enableRescaleNormal();
		GlStateManager.rotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotatef((float)(this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);
		this.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);

		if (this.renderOutlines)
		{
			GlStateManager.enableColorMaterial();
			GlStateManager.setupSolidRenderingTextureCombine(this.getTeamColor(entity));
		}

		this.itemRenderer.renderItem(this.getStackToRender(entity), ItemCameraTransforms.TransformType.GROUND);

		if (this.renderOutlines)
		{
			GlStateManager.tearDownSolidRenderingTextureCombine();
			GlStateManager.disableColorMaterial();
		}

		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	public ItemStack getStackToRender(EntityBullet entityIn)
	{
		return new ItemStack(item);
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(EntityBullet entity)
	{
		return AtlasTexture.LOCATION_BLOCKS_TEXTURE;
	}

}