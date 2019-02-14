package com.greatorator.tolkienmobs.entity.monster;

import com.greatorator.tolkienmobs.entity.EntityHostiles;
import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.init.SoundInit;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.block.Block;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityTroll extends EntityHostiles {
    public EntityTroll(World worldIn) {
        super(worldIn);
        this.setSize(3.4F, 4.6F);
        this.setWeaponType(TTMFeatures.AXE_MORGULIRON);
        this.setLootTable(LootInit.TROLL);
        this.setMobAttributes(30.0D, 9.0D, 11.0D);
        this.setRndMinMax(1,5);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundInit.soundIdleTroll;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource parDamageSource)
    {
        return SoundInit.soundHurtTroll;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundInit.soundDeathTroll;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundInit.soundStepTroll, 0.25F, 1.0F);
    }
}