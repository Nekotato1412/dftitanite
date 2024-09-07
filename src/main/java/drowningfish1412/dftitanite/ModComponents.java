package drowningfish1412.dftitanite;

import com.mojang.serialization.Codec;
import drowningfish1412.dftitanite.componentdata.TitaniteBoostData;
import drowningfish1412.dftitanite.componentdata.TitaniteBoosterData;
import net.minecraft.component.DataComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModComponents {
    public static DataComponentType<TitaniteBoostData> TITANITE_BOOSTED;
    public static DataComponentType<TitaniteBoosterData> TITANITE_BOOSTER;
    public static void initialize() {
     TITANITE_BOOSTED = Registry.register(
                Registries.DATA_COMPONENT_TYPE,
                Identifier.of(Dftitanite.MOD_ID, "titanite_boosts"),
                DataComponentType.<TitaniteBoostData>builder().codec(TitaniteBoostData.CODEC).build()
        );
     TITANITE_BOOSTER = Registry.register(
             Registries.DATA_COMPONENT_TYPE,
             Identifier.of(Dftitanite.MOD_ID, "titanite_booster"),
             DataComponentType.<TitaniteBoosterData>builder().codec(TitaniteBoosterData.CODEC).build()
     );
    }
}
