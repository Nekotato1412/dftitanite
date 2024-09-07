package drowningfish1412.dftitanite;

import drowningfish1412.dftitanite.componentdata.TitaniteBoostData;
import drowningfish1412.dftitanite.componentdata.TitaniteBoosterData;
import drowningfish1412.dftitanite.events.AnvilUpdateCallback;
import drowningfish1412.dftitanite.mixin.AnvilScreenHandlerAccessor;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

public class ModEventListeners {
    public static void initialize() {
        AnvilUpdateCallback.EVENT.register(anvil -> {
            ItemStack left_item = anvil.getSlot(0).getStack();
            ItemStack right_item = anvil.getSlot(1).getStack();
            if (Conditions.isBoostable(left_item) && Conditions.isBoostItem(right_item)) {
                ItemStack result = left_item.copy();
                int numBoosts = right_item.getCount();
                int cost = 1;
                int currentBoosts = 0;
                double currentBonusDamage = 0.0;

                // Set the current values
                if (left_item.contains(ModComponents.TITANITE_BOOSTED)) {
                    TitaniteBoostData boost_component = left_item.get(ModComponents.TITANITE_BOOSTED);
                    currentBoosts += boost_component.boosts();
                    currentBonusDamage += boost_component.bonus_damage();
                }

                // Check the current values
                TitaniteBoosterData boost_data = right_item.get(ModComponents.TITANITE_BOOSTER);
                if (currentBoosts >= boost_data.minBoosts() && currentBoosts + numBoosts  <= boost_data.maxBoosts()) {

                    // EXP Cost increases by 1 for every previous boost
                    cost += currentBoosts;
                    cost *= numBoosts;

                    // Apply the proper number of boosts according to the amount of items in the stack
                    currentBoosts += numBoosts;

                    // Set the name to name+boosts unless it is already custom named.
                    Text itemName = left_item.getItem().getName();
                    if (!result.contains(DataComponentTypes.CUSTOM_NAME)) {
                        result.set(DataComponentTypes.ITEM_NAME, itemName.copy().append(Text.of("+".concat(String.valueOf(currentBoosts)))));
                    }

                    // Calculate the damage boost
                    double defaultDamage = ComponentHelper.getDefaultAttackDamage(left_item);
                    currentBonusDamage += (boost_data.amount() * numBoosts);
                    double damage = defaultDamage + currentBonusDamage;

                    // Attach the component to the result stack
                    result.set(ModComponents.TITANITE_BOOSTED, new TitaniteBoostData(currentBoosts, currentBonusDamage));

                    // Apply the damage boost
                    result = ComponentHelper.setAttackDamage(result, damage);

                    // Set the result item to the output
                    anvil.getSlot(anvil.getResultSlotIndex()).setStack(result);
                    ((AnvilScreenHandlerAccessor) anvil).getLevelCostProperty().set(cost);
                    // Cancel default behavior
                    return ActionResult.FAIL;
                }
            }
            return ActionResult.PASS;
        });
    }
}
