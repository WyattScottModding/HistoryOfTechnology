package items.industrial;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CustomRecords extends MusicDiscItem
{
	private static final Map<SoundEvent, MusicDiscItem> RECORDS = Maps.<SoundEvent, MusicDiscItem>newHashMap();
	private final SoundEvent sound;

	public CustomRecords(String name, SoundEvent soundIn, Item.Properties builder) 
	{
		super(1, soundIn, builder);
		//this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.sound = soundIn;
		//=this.displayName = "item.record." + name + ".desc";
		RECORDS.put(this.sound, this);
	}
}