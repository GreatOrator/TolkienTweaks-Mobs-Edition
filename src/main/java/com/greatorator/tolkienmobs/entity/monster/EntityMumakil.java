package com.greatorator.tolkienmobs.entity.monster;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.EntityHerds;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/** Borrowed from Jabelar https://github.com/jabelar */
public class EntityMumakil extends EntityHerds {
    public static final ResourceLocation LOOT = new ResourceLocation(TolkienMobs.MODID, "entities/mumakil");

    public EntityMumakil(World par1World)
    {
        super(par1World);

        setScaleFactor(2.0F);
        setSize(width*getScaleFactor(), height*getScaleFactor());
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(8.0D);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(16.0D);
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

    @Override
    public EntityMumakil createChild(EntityAgeable par1EntityAgeable)
    {
        return new EntityMumakil(world);
    }
 }
