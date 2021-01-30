//package com.greatorator.tolkienmobs.entity.ammo;
//
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.projectile.EntityThrowable;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.EnumParticleTypes;
//import net.minecraft.util.math.RayTraceResult;
//import net.minecraft.world.World;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//public class EntityBoulder extends EntityThrowable {
//
//    public EntityBoulder(World worldIn)
//    {
//        super(worldIn);
//    }
//
//    public EntityBoulder(World worldIn, LivingEntity throwerIn)
//    {
//        super(worldIn, throwerIn);
//    }
//
//    /**
//     * Handler for {@link World#setEntityState}
//     */
//    @SideOnly(Side.CLIENT)
//    public void handleStatusUpdate(byte id)
//    {
//        if (id == 3)
//        {
//            for (int i = 0; i < 8; ++i)
//            {
//                this.world.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
//            }
//        }
//    }
//
//    /**
//     * Called when this EntityThrowable hits a block or entity.
//     */
//    protected void onImpact(RayTraceResult result)
//    {
//        if (result.entityHit != null)
//        {
//            int i = 10;
//
//            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)i);
//        }
//
//        if (!this.world.isRemote)
//        {
//            this.world.setEntityState(this, (byte)3);
//            this.setDead();
//        }
//    }
//}