package com.greatorator.tolkienmobs.entity.monster;

import com.greatorator.tolkienmobs.entity.EntityBirds;
import com.greatorator.tolkienmobs.init.LootInit;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/** Borrowed from Jabelar https://github.com/jabelar */
public class EntityCrebain extends EntityBirds {

    public EntityCrebain(World parWorld)
    {
        super(parWorld);
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LootInit.CREBAIN;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 2;
    }
}