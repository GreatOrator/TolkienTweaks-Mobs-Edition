package com.greatorator.tolkienmobs.integration.tcon.traits;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class GuldurilModifier extends Modifier {
    private static MobEffectInstance makeDecayEffect(int level) {
        return new MobEffectInstance(MobEffects.WITHER, 20 * (5 + (RANDOM.nextInt(level * 3))), level - 1);
    }

    private static MobEffectInstance makePoisonEffect(int level) {
        return new MobEffectInstance(MobEffects.POISON, 20 * (5 + (RANDOM.nextInt(level * 3))), level - 1);
    }

    @Override
    public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
        if (context.isFullyCharged()) {
            if (RANDOM.nextInt(3) == 0) {
                context.getAttacker().addEffect(makeDecayEffect(level));
            }

            LivingEntity target = context.getLivingTarget();
            if (target != null && target.isAlive()) {
                target.addEffect(makeDecayEffect(level));
                target.addEffect(makePoisonEffect(level));
            }
        }
        return 0;
    }

}
