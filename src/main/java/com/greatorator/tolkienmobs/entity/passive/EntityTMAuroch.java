//package com.greatorator.tolkienmobs.entity.passive;
//
//import com.greatorator.tolkienmobs.entity.EntityTMHerds;
//import com.greatorator.tolkienmobs.init.LootInit;
//import net.minecraft.block.Block;
//import net.minecraft.entity.EntityAgeable;
//import net.minecraft.init.SoundEvents;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.SoundEvent;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//
//import javax.annotation.Nullable;
//
//public class EntityTMAuroch extends EntityTMHerds {
//    public EntityTMAuroch(World worldIn) {
//        super(worldIn);
//
//        this.setSize(1.2F, 2.1F);
//        this.setRndMinMax(1,5);
//    }
//
//    @Override
//    protected SoundEvent getAmbientSound()
//    {
//        return SoundEvents.ENTITY_COW_AMBIENT;
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
//    {
//        return SoundEvents.ENTITY_COW_HURT;
//    }
//
//    @Override
//    protected SoundEvent getDeathSound()
//    {
//        return SoundEvents.ENTITY_COW_DEATH;
//    }
//
//    @Override
//    protected void playStepSound(BlockPos pos, Block blockIn)
//    {
//        this.playSound(SoundEvents.ENTITY_COW_STEP, 0.15F, 1.0F);
//    }
//
//    @Override
//    protected float getSoundVolume()
//    {
//        return 0.4F;
//    }
//
//    @Override
//    @Nullable
//    protected ResourceLocation getLootTable() {
//        return LootInit.AUROCH;
//    }
//
//    @Override
//    public int getMaxSpawnedInChunk() {
//        return 1;
//    }
//
//    @Override
//    public EntityTMAuroch createChild(EntityAgeable par1EntityAgeable)
//    {
//        return new EntityTMAuroch(world);
//    }
//}