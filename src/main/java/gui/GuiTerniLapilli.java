package gui;

import com.mojang.blaze3d.platform.GlStateManager;

import containers.ContainerForge;
import containers.ContainerTerniLapilli;
import main.Reference;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import tileEntities.TileEntityTerniLapilli;

public class GuiTerniLapilli extends ContainerScreen<ContainerTerniLapilli>
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/classical/terni_lapilli_gui.png");

	public GuiTerniLapilli(ContainerTerniLapilli container, PlayerInventory inventory, ITextComponent name) 
	{
		super(container, inventory, name);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) 
	{
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(TEXTURES);
		this.blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		//String tileName = this.tileentity.getDisplayName().getUnformattedText();
		//this.fontRenderer.drawString(tileName, 8, 8, 4210752);
		//this.fontRenderer.drawString("Inventory", 8, this.ySize - 96 + 2, 4210752);

		if(tileentity.whiteTurn)
			this.font.drawString("White's", 125, 40, 4210752);
		else
			this.font.drawString("Black's", 125, 40, 4210752);

		this.font.drawString("Turn", 128, 50, 4210752);

		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
	}
	
	@Override
	public boolean isPauseScreen() {
		return false;
	}


}