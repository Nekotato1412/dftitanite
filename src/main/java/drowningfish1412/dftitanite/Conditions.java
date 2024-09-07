package drowningfish1412.dftitanite;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;

public class Conditions {
    public static boolean isBoostable(ItemStack stack) {
        return stack.contains(DataComponentTypes.ATTRIBUTE_MODIFIERS);
    }

    public static boolean isWithinBoostRange(int amount, int boostMin, int boostMax) {
        return amount >= boostMin && amount < boostMax;
    }

    public static boolean isBoostItem(ItemStack stack) {
        return stack.contains(ModComponents.TITANITE_BOOSTER);
    }
}
