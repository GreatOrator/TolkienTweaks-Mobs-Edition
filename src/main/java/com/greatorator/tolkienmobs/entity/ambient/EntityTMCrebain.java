package com.greatorator.tolkienmobs.entity.ambient;

import com.greatorator.tolkienmobs.entity.EntityTMBirds;
import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/** Borrowed from Jabelar https://github.com/jabelar */
public class EntityTMCrebain extends EntityTMBirds {
    @SuppressWarnings("rawtypes")
    private Class[] preyArray = new Class[] {EntityPlayer.class, EntityChicken.class, EntityBat.class, EntityRabbit.class, EntityParrot.class, EntityTMToad.class, EntityTMSquirrel.class, EntityTMRat.class};

    public EntityTMCrebain(World parWorld)
    {
        super(parWorld);
        this.setSize(0.3F, 0.3F);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Class[] getPreyArray()
    {
        return preyArray;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public void setPreyArray(Class[] parPreyArray)
    {
        preyArray = parPreyArray;
    }

    @Override
    public Item getDropItem()
    {
        return TTMFeatures.CREBAIN_FEATHER;
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LootInit.CREBAIN;
    }
}