package gui;

import com.mojang.blaze3d.platform.GlStateManager;

import main.Reference;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriterionProgress;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import tileEntities.TileEntityBookTechnology;

public class GuiBookTechnology extends Screen
{
	private static final ResourceLocation BACKGROUND = new ResourceLocation(Reference.MODID + ":textures/gui/ancient/background.png");
	private static final ResourceLocation ROCK_ANCIENT = new ResourceLocation(Reference.MODID + ":textures/gui/ancient/rock.png");
	private static final ResourceLocation SPEAR_ANCIENT = new ResourceLocation(Reference.MODID + ":textures/gui/ancient/spear.png");
	private static final ResourceLocation STONE_MACE_ANCIENT = new ResourceLocation(Reference.MODID + ":textures/gui/ancient/stone_mace.png");
	private static final ResourceLocation IRON_MACE_ANCIENT = new ResourceLocation(Reference.MODID + ":textures/gui/ancient/iron_mace.png");
	private static final ResourceLocation ROPE_ANCIENT = new ResourceLocation(Reference.MODID + ":textures/gui/ancient/rope.png");
	private static final ResourceLocation WHEEL_ANCIENT = new ResourceLocation(Reference.MODID + ":textures/gui/ancient/wheel.png");
	private static final ResourceLocation KNIFE_ANCIENT = new ResourceLocation(Reference.MODID + ":textures/gui/ancient/knife.png");
	private static final ResourceLocation FORGE_ANCIENT = new ResourceLocation(Reference.MODID + ":textures/gui/ancient/forge.png");

	private static final ResourceLocation HOSE_CLASSICAL = new ResourceLocation(Reference.MODID + ":textures/gui/classical/hose.png");
	private static final ResourceLocation WATER_TANK_CLASSICAL = new ResourceLocation(Reference.MODID + ":textures/gui/classical/water_tank.png");
	private static final ResourceLocation PIPE_CLASSICAL = new ResourceLocation(Reference.MODID + ":textures/gui/classical/copper_pipe.png");
	private static final ResourceLocation FAUCET_CLASSICAL = new ResourceLocation(Reference.MODID + ":textures/gui/classical/faucet.png");
	private static final ResourceLocation METALTANK_CLASSICAL = new ResourceLocation(Reference.MODID + ":textures/gui/classical/metal_tank.png");
	private static final ResourceLocation TERNI_LAPILLI_CLASSICAL = new ResourceLocation(Reference.MODID + ":textures/gui/classical/terni_lapilli.png");
	private static final ResourceLocation BLACK_ROCK_CLASSICAL = new ResourceLocation(Reference.MODID + ":textures/gui/classical/black_rock.png");
	private static final ResourceLocation WHITE_ROCK_CLASSICAL = new ResourceLocation(Reference.MODID + ":textures/gui/classical/white_rock.png");

	//private static final ResourceLocation CATAPULT_CLASSICAL = new ResourceLocation(Reference.MODID + ":textures/gui/classical/catapult.png");

	private static final ResourceLocation BULLET_MEDIEVAL = new ResourceLocation(Reference.MODID + ":textures/gui/medieval/bullet.png");
	private static final ResourceLocation PISTOL_MEDIEVAL = new ResourceLocation(Reference.MODID + ":textures/gui/medieval/pistol.png");
	private static final ResourceLocation SILKSHEET_MEDIEVAL = new ResourceLocation(Reference.MODID + ":textures/gui/medieval/silk_sheet.png");
	private static final ResourceLocation GUNPOWDER_MEDIEVAL = new ResourceLocation(Reference.MODID + ":textures/gui/medieval/gunpowder.png");
	//private static final ResourceLocation CANNON_MEDIEVAL = new ResourceLocation(Reference.MODID + ":textures/gui/medieval/cannon.png");

	private static final ResourceLocation COFFEE_RENAISSANCE = new ResourceLocation(Reference.MODID + ":textures/gui/renaissance/coffee.png");
	private static final ResourceLocation COFFEE_BEAN_RENAISSANCE = new ResourceLocation(Reference.MODID + ":textures/gui/renaissance/coffee_bean.png");
	private static final ResourceLocation PARACHUTE_RENAISSANCE = new ResourceLocation(Reference.MODID + ":textures/gui/renaissance/parachute.png");
	private static final ResourceLocation SCUBA_HELMET_RENAISSANCE = new ResourceLocation(Reference.MODID + ":textures/gui/renaissance/scuba_helmet.png");
	private static final ResourceLocation SCUBA_CHESTPLATE_RENAISSANCE = new ResourceLocation(Reference.MODID + ":textures/gui/renaissance/scuba_chestplate.png");
	private static final ResourceLocation SCUBA_LEGGINGS_RENAISSANCE = new ResourceLocation(Reference.MODID + ":textures/gui/renaissance/scuba_leggings.png");
	private static final ResourceLocation SCUBA_BOOTS_RENAISSANCE = new ResourceLocation(Reference.MODID + ":textures/gui/renaissance/scuba_boots.png");

	private static final ResourceLocation GRENADE_INDUSTRIAL = new ResourceLocation(Reference.MODID + ":textures/gui/industrial/grenade.png");
	private static final ResourceLocation FLAMETHROWER_INDUSTRIAL = new ResourceLocation(Reference.MODID + ":textures/gui/industrial/flamethrower.png");
	private static final ResourceLocation FLAME_TANK_INDUSTRIAL = new ResourceLocation(Reference.MODID + ":textures/gui/industrial/flame_tank.png");
	private static final ResourceLocation BATTERY_INDUSTRIAL = new ResourceLocation(Reference.MODID + ":textures/gui/industrial/battery.png");
	private static final ResourceLocation FLASHLIGHT_INDUSTRIAL = new ResourceLocation(Reference.MODID + ":textures/gui/industrial/flashlight.png");
	private static final ResourceLocation SYRINGE_INDUSTRIAL = new ResourceLocation(Reference.MODID + ":textures/gui/industrial/syringe.png");
	private static final ResourceLocation LANDMINE_INDUSTRIAL = new ResourceLocation(Reference.MODID + ":textures/gui/industrial/landmine.png");
	private static final ResourceLocation ROCKET_INDUSTRIAL = new ResourceLocation(Reference.MODID + ":textures/gui/industrial/rocket.png");

	//private static final ResourceLocation CONVEYORBELT_INDUSTRIAL = new ResourceLocation(Reference.MODID + ":textures/gui/industrial/conveyorbelt.png");

	private static final ResourceLocation FISSIONBOMB_ATOMIC = new ResourceLocation(Reference.MODID + ":textures/gui/atomic/fissionbomb.png");
	private static final ResourceLocation FUSIONBOMB_ATOMIC = new ResourceLocation(Reference.MODID + ":textures/gui/atomic/fusionbomb.png");
	private static final ResourceLocation FISSION_MISSILE_ATOMIC = new ResourceLocation(Reference.MODID + ":textures/gui/atomic/fission_missile.png");
	private static final ResourceLocation FUSION_MISSILE_ATOMIC = new ResourceLocation(Reference.MODID + ":textures/gui/atomic/fusion_missile.png");
	private static final ResourceLocation MISSILE_ATOMIC = new ResourceLocation(Reference.MODID + ":textures/gui/atomic/missile.png");
	private static final ResourceLocation MISSILE_GUIDANCE_ATOMIC = new ResourceLocation(Reference.MODID + ":textures/gui/atomic/missile_guidance.png");
	private static final ResourceLocation MISSILE_LAUNCHER_ATOMIC = new ResourceLocation(Reference.MODID + ":textures/gui/atomic/missile_launcher.png");
	private static final ResourceLocation RUBBERHELMET_ATOMIC = new ResourceLocation(Reference.MODID + ":textures/gui/atomic/rubberhelmet.png");
	private static final ResourceLocation RUBBERCHESTPLATE_ATOMIC = new ResourceLocation(Reference.MODID + ":textures/gui/atomic/rubberchestplate.png");
	private static final ResourceLocation RUBBERLEGGINGS_ATOMIC = new ResourceLocation(Reference.MODID + ":textures/gui/atomic/rubberleggings.png");
	private static final ResourceLocation RUBBERBOOTS_ATOMIC = new ResourceLocation(Reference.MODID + ":textures/gui/atomic/rubberboots.png");
	private static final ResourceLocation CIRCUIT_ATOMIC = new ResourceLocation(Reference.MODID + ":textures/gui/atomic/circuit.png");

	private static final ResourceLocation THREATGRENADE_MODERN = new ResourceLocation(Reference.MODID + ":textures/gui/modern/threatgrenade.png");
	private static final ResourceLocation GUIDED_MISSILE_MODERN = new ResourceLocation(Reference.MODID + ":textures/gui/modern/guided_missile.png");

	//private static final ResourceLocation COMPUTER_MODERN = new ResourceLocation(Reference.MODID + ":textures/gui/modern/computer.png");

	private static final ResourceLocation ADVANCED_BULLET_FUTURE = new ResourceLocation(Reference.MODID + ":textures/gui/future/advanced_bullet.png");
	private static final ResourceLocation GRAVITY_BOOTS_FUTURE = new ResourceLocation(Reference.MODID + ":textures/gui/future/gravity_boots.png");
	private static final ResourceLocation ROCKET_BOOTS_FUTURE = new ResourceLocation(Reference.MODID + ":textures/gui/future/rocket_boots.png");
	private static final ResourceLocation EXO_CHESTPLATE_FUTURE = new ResourceLocation(Reference.MODID + ":textures/gui/future/exo_chestplate.png");
	private static final ResourceLocation EXO_LEGGINGS_FUTURE = new ResourceLocation(Reference.MODID + ":textures/gui/future/exo_leggings.png");
	private static final ResourceLocation EXO_HOVER_FUTURE = new ResourceLocation(Reference.MODID + ":textures/gui/future/exo_hover.png");

	//private static final ResourceLocation MAGNETGUN_FUTURE = new ResourceLocation(Reference.MODID + ":textures/gui/future/magnetgun.png");


	public TileEntityBookTechnology tileBookTechnology;
	public int currentPage = 1;
	public int pageCount = 7;

	public boolean ancientActive = true;
	public boolean classicalActive = false;
	public boolean medievalActive = false;
	public boolean renaissanceActive = false;
	public boolean industrialActive = false;
	public boolean atomicActive = false;
	public boolean modernActive = false;
	public boolean futureActive = false;

	private int maceCounter = 0;
	private int rubberCounter = 0;
	private int rockCounter = 0;
	private int exoCounter = 0;
	private int scubaCounter = 0;

	public static final int GUI_WIDTH = 255;
	public static final int GUI_HEIGHT = 300;
	protected int xSize = 176;
	protected int ySize = 166;

	public GuiBookTechnology() 
	{
		super(new StringTextComponent("Knowledge of the Gods"));
	}


	@Override
	public void init() 
	{
		addButton(new Button(width/2 - 100, 200, 60, 20, "Left", button -> currentPage--));
		addButton(new Button(width/2 + 100, 200, 60, 20, "Right", button -> currentPage++));

		addButton(new Button(20, 40, 80, 20, "Ancient", button -> ancientTab()));
		addButton(new Button(20, 65, 80, 20, "Classical", button -> classicalTab()));
		addButton(new Button(20, 90, 80, 20, "Medieval", button -> medievalTab()));
		addButton(new Button(20, 115, 80, 20, "Renaissance", button -> renaissanceTab()));

		addButton(new Button(20, 140, 80, 20, "Industrial", button -> industrialTab()));
		addButton(new Button(20, 165, 80, 20, "Atomic", button -> atomicTab()));
		addButton(new Button(20, 190, 80, 20, "Modern", button -> modernTab()));
		addButton(new Button(20, 215, 80, 20, "Future", button -> futureTab()));
	}

	public static void open() {
		Minecraft.getInstance().displayGuiScreen(new GuiBookTechnology());
	}


	private void ancientTab() {
		pageCount = 7;
		currentPage = 1;

		ancientActive = true;

		classicalActive = false;
		medievalActive = false;
		renaissanceActive = false;
		industrialActive = false;
		atomicActive = false;
		modernActive = false;
		futureActive = false;		
	}

	private void classicalTab() {
		pageCount = 7;
		currentPage = 1;

		classicalActive = true;

		medievalActive = false;
		renaissanceActive = false;
		industrialActive = false;
		atomicActive = false;
		modernActive = false;
		futureActive = false;
		ancientActive = false;
	}

	private void medievalTab() {
		pageCount = 4;
		currentPage = 1;

		medievalActive = true;

		renaissanceActive = false;
		industrialActive = false;
		atomicActive = false;
		modernActive = false;
		futureActive = false;
		ancientActive = false;
		classicalActive = false;
	}

	private void renaissanceTab() {
		pageCount = 4;
		currentPage = 1;

		renaissanceActive = true;

		industrialActive = false;
		atomicActive = false;
		modernActive = false;
		futureActive = false;
		ancientActive = false;
		classicalActive = false;
		medievalActive = false;
	}

	private void industrialTab() {
		pageCount = 8;
		currentPage = 1;

		industrialActive = true;

		atomicActive = false;
		modernActive = false;
		futureActive = false;
		ancientActive = false;
		classicalActive = false;
		medievalActive = false;
		renaissanceActive = false;
	}

	private void atomicTab() {
		pageCount = 9;
		currentPage = 1;

		atomicActive = true;

		modernActive = false;
		futureActive = false;
		ancientActive = false;
		classicalActive = false;
		medievalActive = false;
		renaissanceActive = false;
		industrialActive = false;
	}

	private void modernTab() {
		pageCount = 2;
		currentPage = 1;

		modernActive = true;

		futureActive = false;
		ancientActive = false;
		classicalActive = false;
		medievalActive = false;
		renaissanceActive = false;
		industrialActive = false;
		atomicActive = false;
	}

	private void futureTab() {
		pageCount = 5;
		currentPage = 1;

		futureActive = true;

		ancientActive = false;
		classicalActive = false;
		medievalActive = false;
		renaissanceActive = false;
		industrialActive = false;
		atomicActive = false;
		modernActive = false;
	}


	@Override
	public boolean isPauseScreen() {
		return false;
	}


	/*
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		this.renderHoveredToolTip(mouseX, mouseY);

		//LeftButton
		((GuiButton)this.buttons.get(0)).drawButtonForegroundLayer(mouseX, mouseY);

		//RightButton
		((GuiButton)this.buttons.get(1)).drawButtonForegroundLayer(mouseX, mouseY);

		//Tabs
		((GuiButton)this.buttons.get(2)).drawButtonForegroundLayer(mouseX, mouseY);
		((GuiButton)this.buttons.get(3)).drawButtonForegroundLayer(mouseX, mouseY);
		((GuiButton)this.buttons.get(4)).drawButtonForegroundLayer(mouseX, mouseY);
		((GuiButton)this.buttons.get(5)).drawButtonForegroundLayer(mouseX, mouseY);
		((GuiButton)this.buttons.get(6)).drawButtonForegroundLayer(mouseX, mouseY);
		((GuiButton)this.buttons.get(7)).drawButtonForegroundLayer(mouseX, mouseY);
		((GuiButton)this.buttons.get(8)).drawButtonForegroundLayer(mouseX, mouseY);
		((GuiButton)this.buttons.get(9)).drawButtonForegroundLayer(mouseX, mouseY);

		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
	}
	 */

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) 
	{
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

		int i = ((this.width - this.xSize) / 2) - 20;
		int j = ((this.height - this.ySize) / 2) - 40;

		//DrawBackground
		this.minecraft.getTextureManager().bindTexture(BACKGROUND);
		
		//Shift the image container  ;Shift the image within the container;  The size of the image
		this.blit(i, j, 0, 0, GUI_WIDTH, GUI_HEIGHT);//, 355, 180);

		//DrawForeground
		if(ancientActive)
		{
			if(currentPage == 1)
				this.minecraft.getTextureManager().bindTexture(ROCK_ANCIENT);
			else if(currentPage == 2)
				this.minecraft.getTextureManager().bindTexture(SPEAR_ANCIENT);
			else if(currentPage == 3)
			{
				maceCounter++;

				if(maceCounter < 0 || maceCounter >= 100)
					maceCounter = 0;

				if(maceCounter >= 0 && maceCounter < 50)
					this.minecraft.getTextureManager().bindTexture(STONE_MACE_ANCIENT);
				else if(maceCounter >= 50 && maceCounter < 100)
					this.minecraft.getTextureManager().bindTexture(IRON_MACE_ANCIENT);
			}
			else if(currentPage == 4)
				this.minecraft.getTextureManager().bindTexture(ROPE_ANCIENT);
			else if(currentPage == 5)
				this.minecraft.getTextureManager().bindTexture(WHEEL_ANCIENT);
			else if(currentPage == 6)
				this.minecraft.getTextureManager().bindTexture(KNIFE_ANCIENT);
			else if(currentPage == 7)
				this.minecraft.getTextureManager().bindTexture(FORGE_ANCIENT);
		}
		else if(classicalActive)
		{
			if(currentPage == 1)
				this.minecraft.getTextureManager().bindTexture(HOSE_CLASSICAL);
			if(currentPage == 2)
				this.minecraft.getTextureManager().bindTexture(METALTANK_CLASSICAL);
			if(currentPage == 3)
				this.minecraft.getTextureManager().bindTexture(WATER_TANK_CLASSICAL);
			if(currentPage == 4)
				this.minecraft.getTextureManager().bindTexture(PIPE_CLASSICAL);
			if(currentPage == 5)
				this.minecraft.getTextureManager().bindTexture(FAUCET_CLASSICAL);
			if(currentPage == 6)
				this.minecraft.getTextureManager().bindTexture(TERNI_LAPILLI_CLASSICAL);
			if(currentPage == 7)
			{
				rockCounter++;

				if(rockCounter < 0 || rockCounter >= 100)
					rockCounter = 0;

				if(rockCounter >= 0 && rockCounter < 50)
					this.minecraft.getTextureManager().bindTexture(BLACK_ROCK_CLASSICAL);
				else if(rockCounter >= 50 && rockCounter < 100)
					this.minecraft.getTextureManager().bindTexture(WHITE_ROCK_CLASSICAL);
			}
		}
		else if(medievalActive)
		{
			if(currentPage == 1)
				this.minecraft.getTextureManager().bindTexture(PISTOL_MEDIEVAL);
			else if(currentPage == 2)
				this.minecraft.getTextureManager().bindTexture(BULLET_MEDIEVAL);
			else if(currentPage == 3)
				this.minecraft.getTextureManager().bindTexture(SILKSHEET_MEDIEVAL);
			else if(currentPage == 4)
				this.minecraft.getTextureManager().bindTexture(GUNPOWDER_MEDIEVAL);
		}
		else if(renaissanceActive)
		{
			if(currentPage == 1)
				this.minecraft.getTextureManager().bindTexture(COFFEE_RENAISSANCE);
			if(currentPage == 2)
				this.minecraft.getTextureManager().bindTexture(COFFEE_BEAN_RENAISSANCE);
			if(currentPage == 3)
				this.minecraft.getTextureManager().bindTexture(PARACHUTE_RENAISSANCE);
			if(currentPage == 4)
			{
				scubaCounter++;

				if(scubaCounter < 0 || scubaCounter >= 400)
					scubaCounter = 0;

				if(scubaCounter >= 0 && scubaCounter < 100)
					this.minecraft.getTextureManager().bindTexture(SCUBA_HELMET_RENAISSANCE);
				if(scubaCounter >= 100 && scubaCounter < 200)
					this.minecraft.getTextureManager().bindTexture(SCUBA_CHESTPLATE_RENAISSANCE);
				if(scubaCounter >= 200 && scubaCounter < 300)
					this.minecraft.getTextureManager().bindTexture(SCUBA_LEGGINGS_RENAISSANCE);
				if(scubaCounter >= 300 && scubaCounter < 400)
					this.minecraft.getTextureManager().bindTexture(SCUBA_BOOTS_RENAISSANCE);
			}
		}
		else if(industrialActive)
		{
			if(currentPage == 1)
				this.minecraft.getTextureManager().bindTexture(GRENADE_INDUSTRIAL);
			else if(currentPage == 2)
				this.minecraft.getTextureManager().bindTexture(FLAMETHROWER_INDUSTRIAL);
			else if(currentPage == 3)
				this.minecraft.getTextureManager().bindTexture(FLAME_TANK_INDUSTRIAL);
			else if(currentPage == 4)
				this.minecraft.getTextureManager().bindTexture(BATTERY_INDUSTRIAL);
			else if(currentPage == 5)
				this.minecraft.getTextureManager().bindTexture(FLASHLIGHT_INDUSTRIAL);
			else if(currentPage == 6)
				this.minecraft.getTextureManager().bindTexture(SYRINGE_INDUSTRIAL);
			else if(currentPage == 7)
				this.minecraft.getTextureManager().bindTexture(LANDMINE_INDUSTRIAL);
			else if(currentPage == 8)
				this.minecraft.getTextureManager().bindTexture(ROCKET_INDUSTRIAL);
		}
		else if(atomicActive)
		{
			if(currentPage == 1)
				this.minecraft.getTextureManager().bindTexture(FISSIONBOMB_ATOMIC);
			if(currentPage == 2)
				this.minecraft.getTextureManager().bindTexture(FUSIONBOMB_ATOMIC);
			if(currentPage == 3)
				this.minecraft.getTextureManager().bindTexture(FISSION_MISSILE_ATOMIC);
			if(currentPage == 4)
				this.minecraft.getTextureManager().bindTexture(FUSION_MISSILE_ATOMIC);
			if(currentPage == 5)
				this.minecraft.getTextureManager().bindTexture(MISSILE_ATOMIC);
			if(currentPage == 6)
				this.minecraft.getTextureManager().bindTexture(MISSILE_LAUNCHER_ATOMIC);
			if(currentPage == 7)
				this.minecraft.getTextureManager().bindTexture(MISSILE_GUIDANCE_ATOMIC);
			if(currentPage == 8)
			{
				rubberCounter++;

				if(rubberCounter < 0 || rubberCounter >= 400)
					rubberCounter = 0;

				if(rubberCounter >= 0 && rubberCounter < 100)
					this.minecraft.getTextureManager().bindTexture(RUBBERHELMET_ATOMIC);
				if(rubberCounter >= 100 && rubberCounter < 200)
					this.minecraft.getTextureManager().bindTexture(RUBBERCHESTPLATE_ATOMIC);
				if(rubberCounter >= 200 && rubberCounter < 300)
					this.minecraft.getTextureManager().bindTexture(RUBBERLEGGINGS_ATOMIC);
				if(rubberCounter >= 300 && rubberCounter < 400)
					this.minecraft.getTextureManager().bindTexture(RUBBERBOOTS_ATOMIC);
			}
			if(currentPage == 9)
				this.minecraft.getTextureManager().bindTexture(CIRCUIT_ATOMIC);
		}
		else if(modernActive)
		{
			if(currentPage == 1)
				this.minecraft.getTextureManager().bindTexture(THREATGRENADE_MODERN);
			if(currentPage == 2)
				this.minecraft.getTextureManager().bindTexture(GUIDED_MISSILE_MODERN);
		}
		else if(futureActive)
		{
			if(currentPage == 1)
				this.minecraft.getTextureManager().bindTexture(ADVANCED_BULLET_FUTURE);
			if(currentPage == 2)
				this.minecraft.getTextureManager().bindTexture(GRAVITY_BOOTS_FUTURE);
			if(currentPage == 3)
				this.minecraft.getTextureManager().bindTexture(ROCKET_BOOTS_FUTURE);
			if(currentPage == 4)
			{
				exoCounter++;

				if(exoCounter < 0 || exoCounter >= 100)
					exoCounter = 0;

				if(exoCounter >= 0 && exoCounter < 50)
					this.minecraft.getTextureManager().bindTexture(EXO_CHESTPLATE_FUTURE);
				else if(exoCounter >= 50 && exoCounter < 100)
					this.minecraft.getTextureManager().bindTexture(EXO_LEGGINGS_FUTURE);
			}
			if(currentPage == 5)
				this.minecraft.getTextureManager().bindTexture(EXO_HOVER_FUTURE);
		}

		this.blit(i, j, 0, 0, GUI_WIDTH, GUI_HEIGHT);//, 355, 220);



		if(currentPage == 1)
			buttons.get(0).active = false;
		else
			buttons.get(0).active = true;

		if(currentPage == pageCount)
			buttons.get(1).active = false;
		else
			buttons.get(1).active = true;

		/*
		buttonList.get(2).enabled = true;
		buttonList.get(3).enabled = true;
		buttonList.get(4).enabled = true;
		buttonList.get(5).enabled = true;
		buttonList.get(6).enabled = true;
		buttonList.get(7).enabled = true;
		buttonList.get(8).enabled = true;
		buttonList.get(9).enabled = true;

		 */



		if(ancientActive)
			buttons.get(2).active = false;
		else
			buttons.get(2).active = true;

		if(classicalActive)
			buttons.get(3).active = false;
		else
			buttons.get(3).active = true;

		if(medievalActive)
			buttons.get(4).active = false;
		else
			buttons.get(4).active = true;

		if(renaissanceActive)
			buttons.get(5).active = false;
		else
			buttons.get(5).active = true;

		if(industrialActive)
			buttons.get(6).active = false;
		else
			buttons.get(6).active = true;

		if(atomicActive)
			buttons.get(7).active = false;
		else
			buttons.get(7).active = true;

		if(modernActive)
			buttons.get(8).active = false;
		else
			buttons.get(8).active = true;

		if(futureActive)
			buttons.get(9).active = false;
		else
			buttons.get(9).active = true;


		//Bug fix
		if(currentPage > pageCount)
			currentPage = pageCount;
		
		super.render(mouseX, mouseY, partialTicks);
	}


	public boolean hasAchievement(ServerPlayerEntity player, Advancement advancement, String string)
	{
		PlayerAdvancements playeradvancements = player.getAdvancements();
		CriterionProgress criterionprogress = playeradvancements.getProgress(advancement).getCriterionProgress(string);

		if (criterionprogress == null)
		{
			return false;
		}
		else if (criterionprogress.isObtained())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}