package com.greatorator.tolkienmobs.entity.monster;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.EntityBirds;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/** Borrowed from Jabelar https://github.com/jabelar */
public class EntityCrebain extends EntityBirds {
    public static final ResourceLocation LOOT = new ResourceLocation(TolkienMobs.MODID, "entities/crebain");

    public EntityCrebain(World parWorld)
    {
        super(parWorld);
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LOOT;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 2;
    }
}