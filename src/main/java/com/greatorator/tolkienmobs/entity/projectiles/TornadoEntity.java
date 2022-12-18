package com.greatorator.tolkienmobs.entity.projectiles;

import com.greatorator.tolkienmobs.init.TolkienEntities;
import com.greatorator.tolkienmobs.init.TolkienParticles;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;

import static software.bernie.geckolib3.core.builder.ILoopType.EDefaultLoopTypes.LOOP;

@SuppressWarnings({ "unchecked", "rawtypes", "removal" })
public class TornadoEntity extends Entity implements IAnimatable {
    private static final EntityDataAccessor<Boolean> BLAST = SynchedEntityData.defineId(TornadoEntity.class,
            EntityDataSerializers.BOOLEAN);

    AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public int lifeTime;

    public static EntityDimensions blastDimensions = EntityDimensions.scalable(0.75F, 2.0F);

    public TornadoEntity(Level worldIn) {
        super(TolkienEntities.AMMO_TORNADO.get(), worldIn);
    }

    public TornadoEntity(EntityType<? extends TornadoEntity> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public EntityDimensions getDimensions(Pose p_213305_1_) {
        return this.isBlast() ? blastDimensions : super.getDimensions(p_213305_1_);
    }

    public void refreshDimensions() {
        double d0 = this.getX();
        double d1 = this.getY();
        double d2 = this.getZ();
        super.refreshDimensions();
        this.setPos(d0, d1, d2);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 1, this::predicate));
    }


    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("lift", LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public void baseTick() {
        super.baseTick();

        this.refreshDimensions();

        if (!this.isBlast()) {
            List<Entity> list = this.level.getEntities(this, this.getBoundingBox(), Entity::isAlive);
            if (!list.isEmpty()) {
                for (Entity entity : list) {
                    if(entity instanceof LivingEntity){
                        LivingEntity livingEntity = (LivingEntity)entity;
                        if (this.lifeTime == 15) {
                            livingEntity.push(0, 1.5, 0);
                        }
                    }

                }
            }

            if (this.lifeTime >= 15 && this.lifeTime < 30) {
                if (this.level.isClientSide) {
                    for (int i = 0; i < 3; i++) {
                        this.level.addParticle(TolkienParticles.windParticle, this.getRandomX(0.5D), this.getRandomY() - 2, this.getRandomZ(0.5D), (this.random.nextDouble() - 0.5D) * 1.0D, 5, (this.random.nextDouble() - 0.5D) * 1.0D);
                    }
                }
            }
        }

        this.lifeTime ++;

        int removeTime = this.isBlast() ? 14 : 36;

        if (this.lifeTime >= removeTime && !this.level.isClientSide) {
            this.remove(RemovalReason.DISCARDED);
        }
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(BLAST, false);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag p_70037_1_) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag p_213281_1_) {

    }

    public boolean isBlast() {
        return this.entityData.get(BLAST);
    }

    public void setBlast(boolean attached) {
        this.entityData.set(BLAST, attached);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
