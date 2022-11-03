package com.greatorator.tolkienmobs.item.potion;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class LightningEffect extends PotionBaseEffect {
    public static LightningEffect instance = null;
    public static float lightningDuration = 10;

    public LightningEffect(MobEffectCategory typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public boolean isInstantenous() {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        Level world = entity.getCommandSenderWorld();
        BlockPos blockpos = entity.blockPosition();

        LightningBolt lightningboltentity = EntityType.LIGHTNING_BOLT.create(world);
        lightningboltentity.moveTo(Vec3.atBottomCenterOf(blockpos));
        lightningboltentity.setCause(null);
        world.addFreshEntity(lightningboltentity);
        world.playSound((Player) null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.TRIDENT_THUNDER, SoundSource.WEATHER, 5.0f, 1.0f);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % lightningDuration == 0;
    }
}
