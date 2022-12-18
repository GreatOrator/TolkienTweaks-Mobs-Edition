package com.greatorator.tolkienmobs.entity.projectiles;

import com.greatorator.tolkienmobs.entity.item.SimpleTrapEntity;
import com.greatorator.tolkienmobs.init.TolkienEntities;
import com.greatorator.tolkienmobs.init.TolkienSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import static software.bernie.geckolib3.core.builder.ILoopType.EDefaultLoopTypes.LOOP;

@SuppressWarnings({ "unchecked", "removal", "rawtypes" })
public class CobwebProjectileEntity extends Projectile implements IAnimatable {
    AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public boolean delayedSpawnParticles;

    public CobwebProjectileEntity(EntityType<? extends CobwebProjectileEntity> entityType, Level level) {
        super(entityType, level);
    }

    public CobwebProjectileEntity(Level level, LivingEntity livingEntity) {
        this(TolkienEntities.AMMO_COBWEB.get(), level);
        super.setOwner(livingEntity);
        this.setPos(livingEntity.getX() - (double)(livingEntity.getBbWidth() + 1.0F) * 0.5D * (double) Mth.sin(livingEntity.yBodyRot * ((float)Math.PI / 180F)), livingEntity.getEyeY() - (double)0.1F, livingEntity.getZ() + (double)(livingEntity.getBbWidth() + 1.0F) * 0.5D * (double)Mth.cos(livingEntity.yBodyRot * ((float)Math.PI / 180F)));
    }

    @OnlyIn(Dist.CLIENT)
    public CobwebProjectileEntity(Level level, double x, double y, double z, double p_i47274_8_, double p_i47274_10_, double p_i47274_12_) {
        this(TolkienEntities.AMMO_COBWEB.get(), level);
        this.setPos(x, y, z);

        for(int i = 0; i < 7; ++i) {
            double d0 = 0.4D + 0.1D * (double)i;
            level.addParticle(ParticleTypes.SPIT, x, y, z, p_i47274_8_ * d0, p_i47274_10_, p_i47274_12_ * d0);
        }

        this.setDeltaMovement(p_i47274_8_, p_i47274_10_, p_i47274_12_);
    }

    public void tick() {
        super.tick();

        if (this.delayedSpawnParticles) {
            this.delayedSpawnParticles = false;
            this.createSpawnParticles();
        }

        Vec3 vector3d = this.getDeltaMovement();
        HitResult raytraceresult = ProjectileUtil.getHitResult(this, this::canHitEntity);
        if (raytraceresult != null && raytraceresult.getType() != HitResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
            this.onHit(raytraceresult);
        }

        double d0 = this.getX() + vector3d.x;
        double d1 = this.getY() + vector3d.y;
        double d2 = this.getZ() + vector3d.z;
        this.updateRotation();
        float f = 0.99F;
        float f1 = 0.06F;
        if (this.level.getBlockStates(this.getBoundingBox()).noneMatch(BlockBehaviour.BlockStateBase::isAir)) {
            this.remove(RemovalReason.DISCARDED);
        } else if (this.isInWaterOrBubble()) {
            this.remove(RemovalReason.DISCARDED);
        } else {
            this.setDeltaMovement(vector3d.scale((double)f));
            if (!this.isNoGravity()) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, (double)-f1, 0.0D));
            }

            this.setPos(d0, d1, d2);
        }
    }

    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = this.getOwner();
        if (result.getEntity() instanceof LivingEntity && ((LivingEntity)result.getEntity()).getMobType() == MobType.ARTHROPOD) {

        } else {
            if (entity instanceof LivingEntity) {
                result.getEntity().hurt(DamageSource.indirectMobAttack(this, (LivingEntity)entity).setProjectile(), 1.0F);
            }

            if (!this.level.isClientSide) {
                this.spawnTrap(result.getEntity().getX(), result.getEntity().getY(), result.getEntity().getZ());

                this.remove(RemovalReason.DISCARDED);
            }
        }
    }

    public void createSpawnParticles() {
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte) 1);
        } else {
            for (int i = 0; i < 5; i++) {
                this.level.addParticle(ParticleTypes.POOF, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public void handleEntityEvent(byte p_70103_1_) {
        if (p_70103_1_ == 1) {
            for (int i = 0; i < 5; i++) {
                this.level.addParticle(ParticleTypes.POOF, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        } else {
            super.handleEntityEvent(p_70103_1_);
        }
    }

    protected void onHitBlock(BlockHitResult p_230299_1_) {
        super.onHitBlock(p_230299_1_);
        if (!this.level.isClientSide) {
            this.spawnTrap(this.getX(), this.getY(), this.getZ());

            this.remove(RemovalReason.DISCARDED);
        }

    }

    public void spawnTrap(double x, double y, double z) {
        SimpleTrapEntity trap = TolkienEntities.TRAP_SIMPLE.get().create(this.level);
        trap.moveTo(x, y, z);
        trap.owner = this.getOwner();

        this.level.addFreshEntity(trap);

        this.playSound(TolkienSounds.sound_Web_Shoot.get(), 1.0F, 1.0F);
    }

    protected void defineSynchedData() {

    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 2, this::predicate));
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("web_projectile_idle", LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
