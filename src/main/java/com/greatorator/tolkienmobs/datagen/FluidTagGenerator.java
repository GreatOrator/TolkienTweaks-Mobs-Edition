package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.init.TolkienFluids;
import com.greatorator.tolkienmobs.init.TolkienTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FluidTagGenerator extends FluidTagsProvider {
    public FluidTagGenerator(DataGenerator generatorIn, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(TolkienTags.fluids.FLUIDS).add(TolkienFluids.MITHRIL_FLUID.get(), TolkienFluids.MITHRIL_FLOWING.get(), TolkienFluids.MORGULIRON_FLUID.get(), TolkienFluids.MORGULIRON_FLOWING.get());

    }
    @Nonnull
    @Override
    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Fluid Tags";
    }
}