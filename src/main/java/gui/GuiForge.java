package gui;

import com.mojang.blaze3d.platform.GlStateManager;

import containers.ContainerForge;
import main.Reference;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class GuiForge extends ContainerScreen<ContainerForge>
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/ancient/forge_gui.png");

	public GuiForge(ContainerForge container, PlayerInventory inventory, ITextComponent name) 
	{
		super(container, inventory, name);
	}

	//Contains text that goes on top
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		String title = this.title.getFormattedText();
		this.font.drawString(title, (this.xSize / 2 - this.font.getStringWidth(title) / 2) + 3, 8, 4210752);
		this.font.drawString(this.playerInventory.getDisplayName().getUnformattedComponentText(), 8, this.ySize - 96 + 2, 4210752);
		//		this.renderHoveredToolTip(mouseX, mouseY);

	}

	//Contains the texture as well as the loading arrow and fire
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		renderBackground();
		GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.minecraft.getTextureManager().bindTexture(TEXTURES);
		this.blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

		//Fire animation

		if (((ContainerForge)this.container).isBurning()) {
			{
				int k = ((ContainerForge)this.container).getBurnLeftScaled(13);
				this.blit(this.guiLeft + 18, this.guiTop + 56 + 12 - k, 187, 12 - k, 14, k + 1);
			}


			//Loading arrow
			int l = ((ContainerForge)this.container).getCookProgressScaled(28);
			this.blit(this.guiLeft + 97, this.guiTop + 45, 176, 0, 11, l);
		}
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
}