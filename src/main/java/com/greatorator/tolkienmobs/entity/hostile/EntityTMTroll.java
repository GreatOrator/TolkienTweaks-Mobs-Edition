//package com.greatorator.tolkienmobs.entity.hostile;
//
//import com.greatorator.tolkienmobs.entity.EntityTMHostiles;
//import com.greatorator.tolkienmobs.init.LootInit;
//import com.greatorator.tolkienmobs.init.SoundInit;
//import com.greatorator.tolkienmobs.init.TTMFeatures;
//import net.minecraft.block.Block;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.SoundEvent;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//
//public class EntityTMTroll extends EntityTMHostiles {
//    public EntityTMTroll(World worldIn) {
//        super(worldIn);
//        this.setSize(3.4F, 4.6F);
//        this.setWeaponType(TTMFeatures.CLUB_WOODEN);
//        this.setLootTable(LootInit.TROLL);
//        this.setRndMinMax(1,5);
//    }
//
//    @Override
//    public double getAttackDamage() {
//        return 11.0D;
//    }
//
//    @Override
//    public double getArmorStrength() {
//        return 9.0D;
//    }
//
//    @Override
//    public double getHealthLevel() {
//        return 30.0D;
//    }
//
//    @Override
//    protected SoundEvent getAmbientSound()
//    {
//        return SoundInit.soundIdleTroll;
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource parDamageSource)
//    {
//        return SoundInit.soundHurtTroll;
//    }
//
//    @Override
//    protected SoundEvent getDeathSound()
//    {
//        return SoundInit.soundDeathTroll;
//    }
//
//    @Override
//    protected void playStepSound(BlockPos pos, Block blockIn)
//    {
//        this.playSound(SoundInit.soundStepTroll, 0.25F, 1.0F);
//    }
//}