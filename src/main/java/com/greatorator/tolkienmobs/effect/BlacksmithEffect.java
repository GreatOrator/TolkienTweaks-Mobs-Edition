package com.greatorator.tolkienmobs.effect;

import com.google.common.collect.Lists;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BlacksmithEffect extends BasePotionEffect {
    public static BlacksmithEffect instance = null;
    public static int damageTime = 20;

    public BlacksmithEffect(MobEffectCategory typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
        instance = this;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if(entity.level.isClientSide)
            return;

        List<ItemStack> equipment;
        if (entity instanceof Player) {
            equipment = new ArrayList<>();
            Player player = (Player) entity;
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                equipment.add(player.getInventory().getItem(i));
            }
        } else {
            equipment = Lists.newArrayList(entity.getAllSlots());
        }

        equipment.removeIf(stack -> stack.isEmpty() || !stack.isDamaged());

        if (equipment.isEmpty())
            return;

        ItemStack stack = equipment.get(entity.level.random.nextInt(equipment.size()));

        if (stack.getDamageValue() - (amplifier + 1) < 0) {
            stack.setDamageValue(0);
        } else {
            stack.setDamageValue(stack.getDamageValue() - (amplifier + 1));
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % damageTime == 0;
    }
}