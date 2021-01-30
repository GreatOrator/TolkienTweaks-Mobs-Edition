//package com.greatorator.tolkienmobs.entity.hostile;
//
//import com.greatorator.tolkienmobs.entity.EntityTMHostiles;
//import com.greatorator.tolkienmobs.init.LootInit;
//import com.greatorator.tolkienmobs.init.SoundInit;
//import net.minecraft.entity.EnumCreatureAttribute;
//import net.minecraft.init.MobEffects;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.SoundCategory;
//import net.minecraft.util.SoundEvent;
//import net.minecraft.world.World;
//
//public class EntityTMOathbreaker extends EntityTMHostiles {
//
//    public EntityTMOathbreaker(World worldIn) {
//        super(worldIn);
//        this.setSize(1.0F, 2.0F);
//        this.setRandomWeapon(true);
//        this.setLootTable(LootInit.OATHBREAKER);
//        this.setTtmEffect(MobEffects.POISON);
//        this.setTtmDuration(1);
//        this.setRndMinMax(1,5);
//    }
//
//    @Override
//    public double getAttackDamage() {
//        return 3.0D;
//    }
//
//    @Override
//    public double getArmorStrength() {
//        return 5.0D;
//    }
//
//    @Override
//    public double getHealthLevel() {
//        return 16.0D;
//    }
//
//    public EnumCreatureAttribute getCreatureAttribute()
//    {
//        return EnumCreatureAttribute.UNDEAD;
//    }
//
//    @Override
//    public SoundCategory getSoundCategory()
//    {
//        return SoundCategory.HOSTILE;
//    }
//
//    @Override
//    protected SoundEvent getAmbientSound()
//    {
//        return SoundInit.soundIdleOathbreaker;
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
//    {
//        return SoundInit.soundHurtBarrowWight;
//    }
//
//    @Override
//    protected SoundEvent getDeathSound()
//    {
//        return SoundInit.soundHurtBarrowWight;
//    }
//}