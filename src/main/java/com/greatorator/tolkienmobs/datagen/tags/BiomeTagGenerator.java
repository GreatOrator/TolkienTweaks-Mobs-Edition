package com.greatorator.tolkienmobs.datagen.tags;

import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.greatorator.tolkienmobs.TolkienMobs.NAME;

public class BiomeTagGenerator extends BiomeTagsProvider {
    public BiomeTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super(output, provider, TolkienMobs.MODID, helper);
    }


        @Override
    protected void addTags(HolderLookup.Provider provider) {
//        tag(IS_FOREST).add(TolkienBiomes.BIOME_FANGORN);

    }

    @Override
    public String getName() {
        return NAME + " - Biome Tags";
    }
}