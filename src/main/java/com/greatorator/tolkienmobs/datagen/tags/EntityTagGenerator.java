package com.greatorator.tolkienmobs.datagen.tags;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.TolkienEntities;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

import static com.greatorator.tolkienmobs.TolkienMobs.NAME;
import static com.greatorator.tolkienmobs.init.TolkienTags.entities.*;

public class EntityTagGenerator extends EntityTypeTagsProvider {
    public EntityTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper helper) {
        super(output, provider, TolkienMobs.MODID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ARROWS).add(TolkienEntities.AMMO_ARROW_GALADHRIM.get(), TolkienEntities.AMMO_ARROW_UTUMNO.get());
        tag(IMPACT_PROJECTILES).add(TolkienEntities.AMMO_ARROW_GALADHRIM.get(), TolkienEntities.AMMO_ARROW_UTUMNO.get(), TolkienEntities.AMMO_BOULDER.get(), TolkienEntities.AMMO_FELLBEAST_FIREBALL.get());
        tag(WEB_IMMUNE).add(TolkienEntities.ENTITY_TTM_SHELOB.get(), TolkienEntities.ENTITY_TTM_MIRKWOODSPIDER.get());

    }

    @Nonnull
    @Override
    public String getName() {
        return NAME + " - Entity Tags";
    }
}
