package com.greatorator.tolkienmobs.datagen.tags;

import com.greatorator.tolkienmobs.init.TolkienEntities;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.greatorator.tolkienmobs.init.TolkienTags.entities.ARROWS;

public class EntityTagGenerator extends EntityTypeTagsProvider {
    public EntityTagGenerator(DataGenerator generatorIn, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(ARROWS).add(TolkienEntities.AMMO_ARROW_GALADHRIM.get(), TolkienEntities.AMMO_ARROW_UTUMNO.get());

    }

    @Nonnull
    @Override
    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Entity Tags";
    }
}
