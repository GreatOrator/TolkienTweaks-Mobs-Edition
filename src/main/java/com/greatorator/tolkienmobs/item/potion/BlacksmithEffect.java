package com.greatorator.tolkienmobs.item.potion;

import com.google.common.collect.Lists;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectType;

import java.util.ArrayList;
import java.util.List;

public class BlacksmithEffect extends PotionBaseEffect {
    public static BlacksmithEffect instance = null;
    public static int damageTime = 20;

    public BlacksmithEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
        instance = this;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if(entity.level.isClientSide)
            return;

        List<ItemStack> equipment;
        if (entity instanceof PlayerEntity) {
            equipment = new ArrayList<>();
            PlayerEntity player = (PlayerEntity) entity;
            for (int i = 0; i < player.inventory.getContainerSize(); i++) {
                equipment.add(player.inventory.getItem(i));
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