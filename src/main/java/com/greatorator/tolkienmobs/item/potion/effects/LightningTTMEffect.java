package com.greatorator.tolkienmobs.item.potion.effects;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class LightningTTMEffect extends TTMEffectBase {
    public static LightningTTMEffect instance = null;
    public static float lightningDuration = 10;

    public LightningTTMEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public boolean isInstantenous() {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        World world = entity.getCommandSenderWorld();
        BlockPos blockpos = entity.blockPosition();

        LightningBoltEntity lightningboltentity = EntityType.LIGHTNING_BOLT.create(world);
        lightningboltentity.moveTo(Vector3d.atBottomCenterOf(blockpos));
        lightningboltentity.setCause(null);
        world.addFreshEntity(lightningboltentity);
        world.playSound((PlayerEntity) null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.TRIDENT_THUNDER, SoundCategory.WEATHER, 5.0f, 1.0f);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % lightningDuration == 0;
    }
}
