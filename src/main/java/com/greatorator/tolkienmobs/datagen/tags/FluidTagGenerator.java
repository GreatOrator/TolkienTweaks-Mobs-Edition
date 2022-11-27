package com.greatorator.tolkienmobs.datagen.tags;

import com.greatorator.tolkienmobs.init.TolkienFluids;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.greatorator.tolkienmobs.TolkienMobs.NAME;
import static com.greatorator.tolkienmobs.init.TolkienTags.fluids.LAVA;

public class FluidTagGenerator extends FluidTagsProvider {
    public FluidTagGenerator(DataGenerator generatorIn, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(LAVA).add(TolkienFluids.MITHRIL_FLOWING.get(),TolkienFluids.MITHRIL_FLUID.get() ,TolkienFluids.MORGULIRON_FLOWING.get(), TolkienFluids.MORGULIRON_FLUID.get());
    }

    @Nonnull
    @Override
    public String getName() {
        return NAME + " - Fluid Tags";
    }
}
