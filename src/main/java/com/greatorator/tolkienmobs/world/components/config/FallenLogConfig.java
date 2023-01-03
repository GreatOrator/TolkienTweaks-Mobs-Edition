package com.greatorator.tolkienmobs.world.components.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import javax.annotation.Nullable;

public record FallenLogConfig (BlockState normal, BlockState hollow) implements FeatureConfiguration {
    public FallenLogConfig(BlockState normal, @Nullable BlockState hollow) {
        this.normal = normal;
        this.hollow = hollow;
    }

    public static final Codec<FallenLogConfig> CODEC =
            RecordCodecBuilder.create((p_67632_) -> p_67632_.group(
                            BlockState.CODEC.fieldOf("normal").forGetter((p_160757_) -> p_160757_.normal),
                            BlockState.CODEC.fieldOf("hollow").forGetter((p_160751_) -> p_160751_.hollow))
                    .apply(p_67632_, FallenLogConfig::new));
}