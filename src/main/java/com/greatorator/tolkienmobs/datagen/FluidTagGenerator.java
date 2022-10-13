package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.init.TTMTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FluidTagGenerator extends FluidTagsProvider {
    public FluidTagGenerator(DataGenerator generatorIn, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(TTMTags.fluids.FLUIDS).add(TTMContent.MITHRIL_FLUID.get(), TTMContent.MITHRIL_FLOWING.get(), TTMContent.MORGULIRON_FLUID.get(), TTMContent.MORGULIRON_FLOWING.get());

    }
    @Nonnull
    @Override
    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Fluid Tags";
    }
}