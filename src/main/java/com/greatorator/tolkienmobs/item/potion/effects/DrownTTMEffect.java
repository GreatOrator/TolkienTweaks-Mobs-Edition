package com.greatorator.tolkienmobs.item.potion.effects;

import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.EffectUtils;
import net.minecraft.util.DamageSource;

public class DrownTTMEffect extends TTMEffectBase {
    public static DrownTTMEffect instance = null;
    public static int drownDuration = 10;
    private LivingEntity lastTarget;
    private int lastAir;

    public DrownTTMEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        lastTarget = entity;

        boolean flag = lastTarget instanceof PlayerEntity;
        boolean flag1 = flag && ((PlayerEntity)lastTarget).abilities.invulnerable;

        if (!EffectUtils.hasWaterBreathing(lastTarget) && !flag1) {
            int a = EnchantmentHelper.getRespiration(entity);
            lastAir = a > 0 && entity.level.random.nextInt(a + 1) > 0 ? lastAir : lastAir - 1;
            lastTarget.setAirSupply(lastAir);

            if (lastTarget.getAirSupply() < -19) {
                TolkienMobs.LOGGER.info("Help me! I'm Drowning");
                lastAir = 0;

                lastTarget.hurt(DamageSource.DROWN, 2.0F * amplifier);
                TolkienMobs.LOGGER.info("Help me! Damage taken...");
            }
        }
        updateAir();
    }

    private void updateAir() {
        lastTarget.setAirSupply(lastAir);
        if (lastTarget instanceof ServerPlayerEntity) {
            TolkienMobs.instance().sendAirPacket((ServerPlayerEntity) lastTarget, lastAir);
            TolkienMobs.instance().getModifiedPlayerTimes().put(lastTarget.getName().getContents(), System.currentTimeMillis());
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % drownDuration == 0;
    }
}
