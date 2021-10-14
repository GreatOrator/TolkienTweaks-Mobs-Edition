package com.greatorator.tolkienmobs.item.potion.effects;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.EffectUtils;
import net.minecraft.util.DamageSource;

public class DrownTTMEffect extends TTMEffectBase {

    public DrownTTMEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        boolean isPlayer = entity instanceof PlayerEntity;
        boolean isCreative = isPlayer && ((PlayerEntity) entity).abilities.invulnerable;

        if (!EffectUtils.hasWaterBreathing(entity) && !isCreative && !entity.canBreatheUnderwater()) {
            int a = EnchantmentHelper.getRespiration(entity);
            int newAir = entity.getAirSupply();
            if (!entity.isUnderWater()) {
                newAir -= 4; //Cancel out passive air regen
            }

            newAir = a > 0 && entity.level.random.nextInt(a + 1) > 0 ? newAir : newAir - 1;

            if (newAir < -19) {
                newAir = 0;
                entity.hurt(DamageSource.DROWN, 2.0F * (amplifier + 1));
            }
            entity.setAirSupply(newAir);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
