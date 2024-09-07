package drowningfish1412.dftitanite.componentdata;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record TitaniteBoostData (int boosts, double bonus_damage) {
    public static final Codec<TitaniteBoostData> CODEC = RecordCodecBuilder.create(builder -> {
        return builder.group(
                Codec.INT.fieldOf("boosts").forGetter(TitaniteBoostData::boosts),
                Codec.DOUBLE.fieldOf("bonus_damage").forGetter(TitaniteBoostData::bonus_damage)
        ).apply(builder, TitaniteBoostData::new);
    });
}
