package com.greatorator.tolkienmobs.datagen.tags;

import com.greatorator.tolkienmobs.init.TolkienBiomes;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.greatorator.tolkienmobs.init.TolkienTags.biomes.IS_FOREST;

public class BiomeTagGenerator extends BiomeTagsProvider {
    public BiomeTagGenerator(DataGenerator generatorIn, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(IS_FOREST).add(TolkienBiomes.BIOME_FANGORN);

    }

    @Nonnull
    @Override
    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Biome Tags";
    }
}
