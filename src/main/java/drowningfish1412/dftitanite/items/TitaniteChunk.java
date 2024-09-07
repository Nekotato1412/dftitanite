package drowningfish1412.dftitanite.items;

import drowningfish1412.dftitanite.ModComponents;
import net.minecraft.client.item.TooltipType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class TitaniteChunk extends Item {
    public TitaniteChunk(Item.Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (stack.contains(ModComponents.TITANITE_BOOSTER)) {
            int maxBoosts = stack.get(ModComponents.TITANITE_BOOSTER).maxBoosts();
            tooltip.add(Text.translatable("tooltip.dftitanite.titanite_upgrade", maxBoosts).formatted(Formatting.BLUE));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}
