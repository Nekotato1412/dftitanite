package drowningfish1412.dftitanite;

import drowningfish1412.dftitanite.componentdata.TitaniteBoosterData;
import drowningfish1412.dftitanite.items.TitaniteChunk;
import drowningfish1412.dftitanite.items.TitaniteShard;
import drowningfish1412.dftitanite.items.TitaniteSlab;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static Item TITANITE_SHARD;
    public static Item TITANITE_CHUNK;
    public static Item TITANITE_SLAB;
    public static Item register(Item item, String id) {
        Identifier itemID = Identifier.of(Dftitanite.MOD_ID, id);
        Item registeredItem = Registry.register(Registries.ITEM, itemID, item);
        return registeredItem;
    }

    public static void initialize() {
        // Register items
        TITANITE_SHARD = register(new TitaniteShard(new Item.Settings().component(ModComponents.TITANITE_BOOSTER, new TitaniteBoosterData(0, 3, 0.5))), "titanite_shard");
        TITANITE_CHUNK = register(new TitaniteChunk(new Item.Settings().component(ModComponents.TITANITE_BOOSTER, new TitaniteBoosterData(3, 9, 0.7))), "titanite_chunk");
        TITANITE_SLAB = register(new TitaniteSlab(new Item.Settings().component(ModComponents.TITANITE_BOOSTER, new TitaniteBoosterData(9, 10, 4.3))), "titanite_slab");

        // Add items to tabs
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register((itemGroup) -> {
                   itemGroup.add(TITANITE_SHARD);
                   itemGroup.add(TITANITE_CHUNK);
                   itemGroup.add(TITANITE_SLAB);
                });
    }
}