package potions;

import java.util.List;

import init.ItemInit;
import main.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;

public class ThePlague extends Effect
{
	String name;
	
	public ThePlague(String name, EffectType typeIn, int liquidColorIn)
	{
		super(typeIn, liquidColorIn);
		this.name = "effect." + name;
		this.setRegistryName(new ResourceLocation(Reference.MODID + ":" + name));
	}
	
	
	public boolean hasStatusIcon() 
	{
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation(Reference.MODID + ":textures/gui/potion_effects.png"));
		return true;
	}
	
	
	
	public List<ItemStack> getCurativeItems()
	{
        java.util.ArrayList<net.minecraft.item.ItemStack> ret = new java.util.ArrayList<net.minecraft.item.ItemStack>();
        ret.add(new net.minecraft.item.ItemStack(ItemInit.PENICILLIN));
        return ret;
	}
	
	
	public String getName() 
	{
		return name;
	}
	
	
}