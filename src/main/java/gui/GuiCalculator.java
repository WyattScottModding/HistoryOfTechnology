package gui;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.mojang.blaze3d.platform.GlStateManager;

import main.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class GuiCalculator extends Screen
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID, "textures/gui/modern/calculator.png");

	private TextFieldWidget textFieldInput;
	private TextFieldWidget textFieldOutput;
	protected int xSize = 176;
	protected int ySize = 166;

	private boolean more = false;
	String text = this.textFieldInput.getText();


	public GuiCalculator() 
	{
		super(new StringTextComponent("CalculatorGUI"));
	}

	public static void open() {
		Minecraft.getInstance().displayGuiScreen(new GuiCalculator());
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	@Override
	public void init()
	{
		this.textFieldInput = new TextFieldWidget(this.font, 140, 50, 140, 10, "input");
		this.textFieldInput.setText("");
		this.textFieldInput.setTextColor(1303812);
		this.textFieldInput.setEnabled(true);
		this.textFieldInput.setFocused2(false);

		this.textFieldOutput = new TextFieldWidget(this.font, 140, 180, 140, 10, "output");
		this.textFieldOutput.setText("");
		this.textFieldOutput.setTextColor(1303812);
		this.textFieldOutput.setEnabled(true);
		this.textFieldOutput.setFocused2(false);

		addButton(new Button(260, 65, 20, 20, "+", button -> buttonPressed("+")));
		addButton(new Button(260, 85, 20, 20, "-", button -> buttonPressed("-")));
		addButton(new Button(260, 105, 20, 20, "*", button -> buttonPressed("*")));
		addButton(new Button(260, 125, 20, 20, "/", button -> buttonPressed("/")));
		addButton(new Button(240, 145, 40, 30, "Enter", button -> enter()));
		addButton(new Button(180, 65, 20, 20, "0", button -> buttonPressed("0")));
		addButton(new Button(200, 65, 20, 20, "1", button -> buttonPressed("1")));
		addButton(new Button(220, 65, 20, 20, "2", button -> buttonPressed("2")));
		addButton(new Button(180, 85, 20, 20, "3", button -> buttonPressed("3")));
		addButton(new Button(200, 85, 20, 20, "4", button -> buttonPressed("4")));
		addButton(new Button(220, 85, 20, 20, "5", button -> buttonPressed("5")));
		addButton(new Button(180, 105, 20, 20, "6", button -> buttonPressed("6")));
		addButton(new Button(200, 105, 20, 20, "7", button -> buttonPressed("7")));
		addButton(new Button(220, 105, 20, 20, "8", button -> buttonPressed("8")));
		addButton(new Button(200, 125, 20, 20, "9", button -> buttonPressed("9")));
		addButton(new Button(190, 145, 40, 30, "Delete", button -> delete()));
		addButton(new Button(140, 65, 20, 20, "e", button -> buttonPressed("e")));
		addButton(new Button(140, 85, 20, 20, "pi", button -> buttonPressed("PI")));
		addButton(new Button(140, 145, 40, 30, "Clear", button -> clear()));
		addButton(new Button(140, 105, 20, 20, "(", button -> buttonPressed("(")));
		addButton(new Button(140, 125, 20, 20, ")", button -> buttonPressed(")")));
		addButton(new Button(180, 65, 60, 20, "sine", button -> buttonPressed("sin(")));
		addButton(new Button(180, 85, 60, 20, "cosine", button -> buttonPressed("cos(")));
		addButton(new Button(180, 105, 60, 20, "tangent", button -> buttonPressed("tan(")));
		addButton(new Button(220, 125, 20, 20, "->", button -> more()));
		addButton(new Button(180, 125, 20, 20, ".", button -> buttonPressed(".")));

		for(int i = 21; i <= 23; i++)
		{
			buttons.get(i).visible = false;
			buttons.get(i).active = false;
		}
		for(int i = 5; i <= 14; i++)
		{
			buttons.get(i).visible = true;
			buttons.get(i).active = true;
		}

		super.init();
	}



	@Override
	public void tick()
	{
		text = this.textFieldInput.getText();

		if(more)
		{
			for(int i = 21; i <= 23; i++)
			{
				buttons.get(i).visible = true;
				buttons.get(i).active = true;
			}
			for(int i = 5; i <= 14; i++)
			{
				buttons.get(i).visible = false;
				buttons.get(i).active = false;
			}
		}
		else
		{
			for(int i = 21; i <= 23; i++)
			{
				buttons.get(i).visible = false;
				buttons.get(i).active = false;
			}
			for(int i = 5; i <= 14; i++)
			{
				buttons.get(i).visible = true;
				buttons.get(i).active = true;
			}
		}


		super.tick();
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) 
	{
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		
		this.minecraft.getTextureManager().bindTexture(TEXTURES);
		this.blit(i, j, 0, 0, this.xSize, this.ySize);
		
		super.render(mouseX, mouseY, partialTicks);
	}

	private void buttonPressed(String operation) {
		this.textFieldInput.setText(text + operation);
	}

	private void enter() {
		String answer = calculate(text);
		this.textFieldOutput.setText(answer);
	}

	private void delete() {
		if(text.length() > 0)
			this.textFieldInput.setText(text.substring(0, text.length() - 1));
	}

	private void clear() {
		this.textFieldInput.setText("");
		this.textFieldOutput.setText("");
	}

	private void more() {
		if(this.more)
			this.more = false;
		else
			this.more = true;
	}


	private String calculate(String text)
	{
		text = text.replaceAll("e", "(Math.E)");
		text = text.replaceAll("PI", "(Math.PI)");

		text = text.replaceAll("sin", "Math.sin");
		text = text.replaceAll("cos", "Math.cos");
		text = text.replaceAll("tan", "Math.tan");



		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		String answer = "";
		try {
			answer = engine.eval(text).toString();
		} catch (ScriptException e) {
			//e.printStackTrace();
			answer = "Invaild Input";
		}

		return answer;
	}

}