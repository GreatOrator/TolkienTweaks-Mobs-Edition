package com.greatorator.tolkienmobs.datagen.tags;

import com.greatorator.tolkienmobs.init.TolkienEntities;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.greatorator.tolkienmobs.TolkienMobs.NAME;
import static com.greatorator.tolkienmobs.init.TolkienTags.entities.ARROWS;
import static com.greatorator.tolkienmobs.init.TolkienTags.entities.IMPACT_PROJECTILES;

public class EntityTagGenerator extends EntityTypeTagsProvider {
    public EntityTagGenerator(DataGenerator generatorIn, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(ARROWS).add(TolkienEntities.AMMO_ARROW_GALADHRIM.get(), TolkienEntities.AMMO_ARROW_UTUMNO.get());
        tag(IMPACT_PROJECTILES).add(TolkienEntities.AMMO_ARROW_GALADHRIM.get(), TolkienEntities.AMMO_ARROW_UTUMNO.get(), TolkienEntities.AMMO_BOULDER.get(), TolkienEntities.AMMO_FELLBEAST_FIREBALL.get());

    }

    @Nonnull
    @Override
    public String getName() {
        return NAME + " - Entity Tags";
    }
}
