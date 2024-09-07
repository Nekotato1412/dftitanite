package drowningfish1412.dftitanite.componentdata;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record TitaniteBoosterData(int minBoosts, int maxBoosts, double amount) {
    public static final Codec<TitaniteBoosterData> CODEC = RecordCodecBuilder.create(builder -> {
        return builder.group(
                Codec.INT.fieldOf("minBoosts").forGetter(TitaniteBoosterData::minBoosts),
                Codec.INT.fieldOf("maxBoosts").forGetter(TitaniteBoosterData::maxBoosts),
                Codec.DOUBLE.fieldOf("amount").forGetter(TitaniteBoosterData::amount)
        ).apply(builder, TitaniteBoosterData::new);
    });
}
