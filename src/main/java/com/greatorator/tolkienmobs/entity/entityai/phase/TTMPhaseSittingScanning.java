//package com.greatorator.tolkienmobs.entity.entityai.phase;
//
//import net.minecraft.entity.LivingEntity;
//import com.greatorator.tolkienmobs.entity.boss.EntityTMFellBeast;
//import net.minecraft.util.math.MathHelper;
//import net.minecraft.util.math.Vec3d;
//
//public class TTMPhaseSittingScanning extends TTMPhaseSittingBase
//{
//    private int scanningTime;
//
//    public TTMPhaseSittingScanning(EntityTMFellBeast fellbeastIn)
//    {
//        super(fellbeastIn);
//    }
//
//    /**
//     * Gives the phase a chance to update its status.
//     * Called by fellbeast's onLivingUpdate. Only used when !worldObj.isRemote.
//     */
//    public void doLocalUpdate()
//    {
//        ++this.scanningTime;
//        LivingEntity LivingEntity = this.fellbeast.world.getNearestAttackablePlayer(this.fellbeast, 20.0D, 10.0D);
//
//        if (LivingEntity != null)
//        {
//            if (this.scanningTime > 25)
//            {
//                this.fellbeast.getFellBeastPhaseManager().setPhase(TTMPhaseList.SITTING_ATTACKING);
//            }
//            else
//            {
//                Vec3d vec3d = (new Vec3d(LivingEntity.posX - this.fellbeast.posX, 0.0D, LivingEntity.posZ - this.fellbeast.posZ)).normalize();
//                Vec3d vec3d1 = (new Vec3d((double)MathHelper.sin(this.fellbeast.rotationYaw * 0.017453292F), 0.0D, (double)(-MathHelper.cos(this.fellbeast.rotationYaw * 0.017453292F)))).normalize();
//                float f = (float)vec3d1.dotProduct(vec3d);
//                float f1 = (float)(Math.acos((double)f) * (180D / Math.PI)) + 0.5F;
//
//                if (f1 < 0.0F || f1 > 10.0F)
//                {
//                    double d0 = LivingEntity.posX - this.fellbeast.fellbeastPartHead.posX;
//                    double d1 = LivingEntity.posZ - this.fellbeast.fellbeastPartHead.posZ;
//                    double d2 = MathHelper.clamp(MathHelper.wrapDegrees(180.0D - MathHelper.atan2(d0, d1) * (180D / Math.PI) - (double)this.fellbeast.rotationYaw), -100.0D, 100.0D);
//                    this.fellbeast.randomYawVelocity *= 0.8F;
//                    float f2 = MathHelper.sqrt(d0 * d0 + d1 * d1) + 1.0F;
//                    float f3 = f2;
//
//                    if (f2 > 40.0F)
//                    {
//                        f2 = 40.0F;
//                    }
//
//                    this.fellbeast.randomYawVelocity = (float)((double)this.fellbeast.randomYawVelocity + d2 * (double)(0.7F / f2 / f3));
//                    this.fellbeast.rotationYaw += this.fellbeast.randomYawVelocity;
//                }
//            }
//        }
//        else if (this.scanningTime >= 100)
//        {
//            LivingEntity = this.fellbeast.world.getNearestAttackablePlayer(this.fellbeast, 150.0D, 150.0D);
//            this.fellbeast.getFellBeastPhaseManager().setPhase(TTMPhaseList.TAKEOFF);
//
//            if (LivingEntity != null)
//            {
//                this.fellbeast.getFellBeastPhaseManager().setPhase(TTMPhaseList.CHARGING_PLAYER);
//                ((TTMPhaseChargingPlayer)this.fellbeast.getFellBeastPhaseManager().getPhase(TTMPhaseList.CHARGING_PLAYER)).setTarget(new Vec3d(LivingEntity.posX, LivingEntity.posY, LivingEntity.posZ));
//            }
//        }
//    }
//
//    /**
//     * Called when this phase is set to active
//     */
//    public void initPhase()
//    {
//        this.scanningTime = 0;
//    }
//
//    public TTMPhaseList<TTMPhaseSittingScanning> getType()
//    {
//        return TTMPhaseList.SITTING_SCANNING;
//    }
//}