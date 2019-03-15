package com.greatorator.tolkienmobs.entity.ambient;

import com.greatorator.tolkienmobs.entity.EntityTMBirds;
import com.greatorator.tolkienmobs.init.LootInit;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityTMThrush extends EntityTMBirds {

    public EntityTMThrush(World parWorld)
    {
        super(parWorld);
        this.setSize(0.3F, 0.3F);
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LootInit.THRUSH;
    }
}
