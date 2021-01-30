//package com.greatorator.tolkienmobs.entity.hostile;
//
//import com.greatorator.tolkienmobs.entity.EntityTMHostiles;
//import com.greatorator.tolkienmobs.init.LootInit;
//import com.greatorator.tolkienmobs.init.SoundInit;
//import com.greatorator.tolkienmobs.init.TTMFeatures;
//import net.minecraft.util.SoundEvent;
//import net.minecraft.world.World;
//
//public class EntityTMUrukHai extends EntityTMHostiles {
//    public EntityTMUrukHai(World worldIn) {
//        super(worldIn);
//        this.setSize(1.0F, 2.6F);
//        this.setWeaponType(TTMFeatures.SWORD_MORGULIRON);
//        this.setLootTable(LootInit.URUK);
//        this.setRndMinMax(1,5);
//    }
//
//    @Override
//    public double getAttackDamage() {
//        return 5.0D;
//    }
//
//    @Override
//    public double getArmorStrength() {
//        return 8.0D;
//    }
//
//    @Override
//    public double getHealthLevel() {
//        return 34.0D;
//    }
//
//    @Override
//    protected SoundEvent getAmbientSound()
//    {
//        return SoundInit.soundIdleOrc;
//    }
//}