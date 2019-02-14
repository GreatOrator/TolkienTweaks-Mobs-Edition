package com.greatorator.tolkienmobs.entity.monster;

import com.greatorator.tolkienmobs.entity.EntityHostiles;
import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.init.SoundInit;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityGoblin extends EntityHostiles {

    public EntityGoblin(World worldIn) {
        super(worldIn);
        this.setSize(0.9F, 0.8F);
        this.setWeaponType(TTMFeatures.SWORD_MORGULIRON);
        this.setLootTable(LootInit.GOBLIN);
        this.setMobAttributes(20.0D, 2.0D, 3.0D);
        this.setMobMentality(true, SoundInit.soundAngryGoblin);
        this.setRndMinMax(1,5);
        this.setCombatTask();
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundInit.soundIdleGoblin;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundInit.soundHurtGoblin;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundInit.soundDeathGoblin;
    }
}