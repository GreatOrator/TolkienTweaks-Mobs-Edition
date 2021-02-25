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

    public void performEffect(LivingEntity entity, int amplifier) {
        lastTarget = entity;

        boolean flag = lastTarget instanceof PlayerEntity;
        boolean flag1 = flag && ((PlayerEntity)lastTarget).abilities.disableDamage;

        if (!EffectUtils.canBreatheUnderwater(lastTarget) && !flag1) {
            int a = EnchantmentHelper.getRespirationModifier(entity);
            lastAir = a > 0 && entity.world.rand.nextInt(a + 1) > 0 ? lastAir : lastAir - 1;
            lastTarget.setAir(lastAir);

            if (lastTarget.getAir() < -19) {
                TolkienMobs.LOGGER.info("Help me! I'm Drowning");
                lastAir = 0;

                lastTarget.attackEntityFrom(DamageSource.DROWN, 2.0F * amplifier);
                TolkienMobs.LOGGER.info("Help me! Damage taken...");
            }
        }
        updateAir();
    }

    private void updateAir() {
        lastTarget.setAir(lastAir);
        if (lastTarget instanceof ServerPlayerEntity) {
            TolkienMobs.instance().sendAirPacket((ServerPlayerEntity) lastTarget, lastAir);
            TolkienMobs.instance().getModifiedPlayerTimes().put(lastTarget.getName().getUnformattedComponentText(), System.currentTimeMillis());
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return duration % drownDuration == 0;
    }
}
