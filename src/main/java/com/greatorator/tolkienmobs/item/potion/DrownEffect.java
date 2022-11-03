package com.greatorator.tolkienmobs.item.potion;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class DrownEffect extends PotionBaseEffect {

    public DrownEffect(MobEffectCategory typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        boolean isPlayer = entity instanceof Player;
        boolean isCreative = isPlayer && ((Player) entity).getAbilities().invulnerable;

        if (!MobEffectUtil.hasWaterBreathing(entity) && !isCreative && !entity.canBreatheUnderwater()) {
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
