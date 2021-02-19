//package com.greatorator.tolkienmobs.entity.entityai.phase;
//
//import net.minecraft.entity.MultiPartEntityPart;
//import net.minecraft.entity.item.EntityEnderCrystal;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.Vec3d;
//
//import javax.annotation.Nullable;
//
//public interface ITTMPhase
//{
//    boolean getIsStationary();
//
//    /**
//     * Generates particle effects appropriate to the phase (or sometimes sounds).
//     * Called by fellbeast's onLivingUpdate. Only used when worldObj.isRemote.
//     */
//    void doClientRenderEffects();
//
//    /**
//     * Gives the phase a chance to update its status.
//     * Called by fellbeast's onLivingUpdate. Only used when !worldObj.isRemote.
//     */
//    void doLocalUpdate();
//
//    void onCrystalDestroyed(EntityEnderCrystal crystal, BlockPos pos, DamageSource dmgSrc, @Nullable PlayerEntity plyr);
//
//    /**
//     * Called when this phase is set to active
//     */
//    void initPhase();
//
//    void removeAreaEffect();
//
//    /**
//     * Returns the maximum amount fellbeast may rise or fall during this phase
//     */
//    float getMaxRiseOrFall();
//
//    float getYawFactor();
//
//    TTMPhaseList<? extends ITTMPhase> getType();
//
//    /**
//     * Returns the location the fellbeast is flying toward
//     */
//    @Nullable
//    Vec3d getTargetLocation();
//
//    /**
//     * Normally, just returns damage. If fellbeast is sitting and src is an arrow, arrow is enflamed and zero damage
//     * returned.
//     */
//    float getAdjustedDamage(MultiPartEntityPart pt, DamageSource src, float damage);
//}