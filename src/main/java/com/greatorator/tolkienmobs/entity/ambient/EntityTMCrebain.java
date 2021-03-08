//package com.greatorator.tolkienmobs.entity.ambient;
//
//import com.greatorator.tolkienmobs.init.LootInit;
//import com.greatorator.tolkienmobs.init.SoundInit;
//import com.greatorator.tolkienmobs.init.TTMFeatures;
//import com.greatorator.tolkienmobs.utils.TTMSpawnEvent;
//import net.minecraft.entity.passive.EntityParrot;
//import net.minecraft.item.Item;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.SoundEvent;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.EnumDifficulty;
//import net.minecraft.world.World;
//
//import javax.annotation.Nullable;
//
///** Borrowed from Jabelar https://github.com/jabelar */
//public class EntityTMCrebain extends EntityParrot {
//
//    public EntityTMCrebain(World parWorld)
//    {
//        super(parWorld);
//        this.setSize(0.3F, 0.3F);
//    }
//
//    @Override
//    public Item getDropItem()
//    {
//        return TTMFeatures.CREBAIN_FEATHER;
//    }
//
//    @Override
//    protected void dropFewItems(boolean parRecentlyHitByPlayer, int parLootLevel)
//    {
//        dropItem(TTMFeatures.CREBAIN_FEATHER, parLootLevel+1);
//        return;
//    }
//
//    @Override
//    @Nullable
//    public SoundEvent getAmbientSound()
//    {
//        return SoundInit.soundCallCrebain;
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource parSource)
//    {
//        return SoundInit.soundHurtCrebain;
//    }
//
//    @Override
//    protected SoundEvent getDeathSound()
//    {
//        return SoundInit.soundDeathCrebain;
//    }
//
//    @Override
//    protected float getSoundVolume()
//    {
//        return 0.3F;
//    }
//
//    @Override
//    public int getMaxSpawnedInChunk()
//    {
//        return 1;
//    }
//
//    @Override
//    protected boolean canDespawn()
//    {
//        return true;
//    }
//
//    protected boolean isValidLightLevel() {
//        return true;
//    }
//
//    @Override
//    public boolean getCanSpawnHere() {
//        int willSpawn = TTMSpawnEvent.spawnChance();
//
//        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && this.world.canSeeSky(new BlockPos(this)) && willSpawn <= 10;
//    }
//
//    @Override
//    @Nullable
//    protected ResourceLocation getLootTable() {
//        return LootInit.CREBAIN;
//    }
//}