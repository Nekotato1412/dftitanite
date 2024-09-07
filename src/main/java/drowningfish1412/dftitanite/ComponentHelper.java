package drowningfish1412.dftitanite;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.lang.reflect.Array;
import java.util.List;

public class ComponentHelper {
    public static double getDefaultAttackDamage(ItemStack stack) {
        List<AttributeModifiersComponent.Entry> modifiers = stack.getDefaultComponents().get(DataComponentTypes.ATTRIBUTE_MODIFIERS).modifiers();
        for (AttributeModifiersComponent.Entry entry : modifiers) {
            if (entry.attribute().matches(EntityAttributes.GENERIC_ATTACK_DAMAGE)) {
                return entry.modifier().value();
            }
        }
        return 0.0;
    }

    public static ItemStack setAttackDamage(ItemStack stack, double damage) {
        ItemStack newStack = stack.copy();
        AttributeModifiersComponent modifiersComponent = newStack.get(DataComponentTypes.ATTRIBUTE_MODIFIERS);
        List<AttributeModifiersComponent.Entry> otherModifiers = modifiersComponent.modifiers().stream().filter(e -> !e.attribute().matches(EntityAttributes.GENERIC_ATTACK_DAMAGE)).toList();
        List<AttributeModifiersComponent.Entry> attackModifiers = new java.util.ArrayList<>(List.of(new AttributeModifiersComponent.Entry(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(Item.ATTACK_DAMAGE_MODIFIER_ID, "generic.attack_damage", damage, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)));
        attackModifiers.addAll(otherModifiers);
        AttributeModifiersComponent newModifierComponent = new AttributeModifiersComponent(attackModifiers, modifiersComponent.showInTooltip());
        newStack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, newModifierComponent);
        return newStack;
    }
}
