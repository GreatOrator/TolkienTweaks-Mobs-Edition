package com.greatorator.tolkienmobs.item.potion.effects;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.common.MobModify;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

public class DrownTTMEffect extends TTMEffectBase {
    public static DrownTTMEffect instance = null;
    public static int drownDuration = 10;
    private final int RESET_AIR_VALUE = -999;
    private LivingEntity lastTarget;
    private int lastAir;

    public DrownTTMEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    public void performEffect(LivingEntity entity, int amplifier) {
        if (MobModify.getMobTarget() != lastTarget) {
            lastAir = RESET_AIR_VALUE;
            if (lastTarget != null) {
                updateAir();
            }
            lastTarget = MobModify.getMobTarget();
        }

        if (lastTarget != null) {
            if (entity.canEntityBeSeen(lastTarget)) {
                if (lastAir == RESET_AIR_VALUE) {
                    lastAir = lastTarget.getAir();
                } else {
                    lastAir = Math.min(lastAir, lastTarget.getAir());
                }

                if (!(lastTarget instanceof PlayerEntity && ((PlayerEntity) lastTarget).abilities.disableDamage)) {
                    lastAir--;
                    if (lastAir < -19) {
                        lastAir = 0;
                        lastTarget.attackEntityFrom(DamageSource.DROWN, 2.0F * amplifier);
                    }

                    updateAir();
                }
            }
        }
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
