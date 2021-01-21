package gui;

import com.mojang.blaze3d.platform.GlStateManager;

import containers.ContainerForge;
import containers.ContainerMissileGuidance;
import io.netty.buffer.Unpooled;
import main.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent ;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import network.MessageFireMissile;
import proxy.ClientProxy;
import tileEntities.TileEntityMissileGuidanceSystem;

public class GuiMissileGuidance extends ContainerScreen<ContainerMissileGuidance>
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/atomic/missile_guidance_gui.png");

	private TextFieldWidget textFieldX;
	private TextFieldWidget textFieldZ;

	private static PlayerEntity player = null;

	public GuiMissileGuidance(ContainerMissileGuidance container, PlayerInventory inventory, ITextComponent name) 
	{
		super(container, inventory, name);
		player = inventory.player;
	}


	@Override
	public void init()
	{		
		addButton(new Button(230, 90, 60, 20, "Launch", button -> launch()));

		this.textFieldX = new TextFieldWidget(this.font, 140, 70, 80, 10, "fieldX");
		this.textFieldX.setText("");
		this.textFieldX.setTextColor(1303812);

		this.textFieldZ = new TextFieldWidget(this.font, 140, 95, 80, 10, "fieldZ");
		this.textFieldZ.setText("");
		this.textFieldZ.setTextColor(1303812);

		CompoundNBT nbt = tileentity.getNbt();

		if(nbt != null)
		{
			this.textFieldX.setText("" + nbt.getInt("posX"));
			this.textFieldZ.setText("" + nbt.getInt("posZ"));
		}

		super.init();
	}
	
	@Override
	public boolean isPauseScreen() {
		return false;
	}


	@Override
	public void tick()
	{
		//this.textFieldX.updateCursorCounter();
		//this.textFieldZ.updateCursorCounter();


		/*
		if(Keyboard.isKeyDown(Keyboard.KEY_TAB) && player.world.getWorldTime() % 3 == 0)
		{
			if(textFieldX.isFocused())
			{
				textFieldX.setFocused(false);
				textFieldZ.setFocused(true);
			}
			else if(textFieldZ.isFocused())
			{
				textFieldX.setFocused(true);
				textFieldZ.setFocused(false);
			}
			else
			{
				textFieldX.setFocused(true);
			}
		}
		 */
		super.tick();
	}


	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) 
	{
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(TEXTURES);
		this.blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		//this.textFieldX.drawTextBox();
		//this.textFieldZ.drawTextBox();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		String title = this.title.getFormattedText();
		this.font.drawString(title, (this.xSize / 2 - this.font.getStringWidth(title) / 2) + 3, 8, 4210752);
		this.font.drawString("Inventory", 8, this.ySize - 96 + 2, 4210752);

		this.font.drawString("posX", 20, 23, 4210752);
		this.font.drawString("posZ", 20, 48, 4210752);


		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
	}


	public boolean launch()
	{
		PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());

		if(textFieldX.isFocused())
			packetbuffer.writeString(this.textFieldX.getText());

		if(textFieldZ.isFocused())
			packetbuffer.writeString(this.textFieldZ.getText());


		int posX = 0;
		int posZ = 0;

		try
		{
			String textX = this.textFieldX.getText().trim();
			String textZ = this.textFieldZ.getText().trim();

			if(textX.length() > 0 && textX.substring(0, 1).equals("~"))
			{
				posX = (int) tileentity.getPos().getX();
				textX = textX.substring(1, textX.length());
			}

			if(textZ.length() > 0 && textZ.substring(0, 1).equals("~"))
			{
				posZ = (int) tileentity.getPos().getZ();
				textZ = textZ.substring(1, textZ.length());
			}

			if(textX.equals(""))
				textX = "0";
			if(textZ.equals(""))
				textZ = "0";

			posX += Integer.parseInt(textX);
			posZ += Integer.parseInt(textZ);
		}
		catch (NumberFormatException ex)
		{

			ITextComponent msg = new StringTextComponent ("You must enter an integer");
			player.sendMessage(msg);
			player.closeScreen();
			return false;
		}

		if(tileentity != null)
		{
			//this.tileentity.fireMissile(posX, posZ, player);
			int tilePosX = tileentity.getPos().getX();
			int tilePosY = tileentity.getPos().getY();
			int tilePosZ = tileentity.getPos().getZ();

			NetworkHandler.sendToServer(new MessageFireMissile(tilePosX, tilePosY, tilePosZ, posX, posZ));
		}		
	}


	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
		this.textFieldX.mouseClicked(mouseX, mouseY, mouseButton);
		this.textFieldZ.mouseClicked(mouseX, mouseY, mouseButton);
		return super.mouseClicked(mouseX, mouseY, mouseButton);
	}
}