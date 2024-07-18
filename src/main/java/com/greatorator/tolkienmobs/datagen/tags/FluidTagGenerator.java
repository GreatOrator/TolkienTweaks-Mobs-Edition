package com.greatorator.tolkienmobs.datagen.tags;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.TolkienFluids;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

import static com.greatorator.tolkienmobs.TolkienMobs.NAME;
import static com.greatorator.tolkienmobs.init.TolkienTags.fluids.LAVA;

public class FluidTagGenerator extends FluidTagsProvider {
    public FluidTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, TolkienMobs.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(LAVA).add(TolkienFluids.MITHRIL_FLOWING.get(),TolkienFluids.MITHRIL_FLUID.get() ,TolkienFluids.MORGULIRON_FLOWING.get(), TolkienFluids.MORGULIRON_FLUID.get());
    }

    @Nonnull
    @Override
    public String getName() {
        return NAME + " - Fluid Tags";
    }
}
