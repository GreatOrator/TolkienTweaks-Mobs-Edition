package com.greatorator.tolkienmobs.entity.monster;

import com.greatorator.tolkienmobs.entity.EntityTMBirds;
import com.greatorator.tolkienmobs.init.LootInit;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/** Borrowed from Jabelar https://github.com/jabelar */
public class EntityTMCrebain extends EntityTMBirds {

    public EntityTMCrebain(World parWorld)
    {
        super(parWorld);
        this.setSize(0.3F, 0.3F);
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LootInit.CREBAIN;
    }
}