package com.greatorator.tolkienmobs.item.potion.effects;

import com.google.common.collect.Lists;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectType;

import java.util.ArrayList;
import java.util.List;

public class BlacksmithTTMEffect extends TTMEffectBase {
    public static BlacksmithTTMEffect instance = null;
    public static int damageTime = 20;

    public BlacksmithTTMEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
        instance = this;
    }

    @Override
    public void performEffect(LivingEntity entity, int amplifier) {
        if(entity.world.isRemote)
            return;

        List<ItemStack> equipment;
        if (entity instanceof PlayerEntity) {
            equipment = new ArrayList<>();
            PlayerEntity player = (PlayerEntity) entity;
            for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                equipment.add(player.inventory.getStackInSlot(i));
            }
        } else {
            equipment = Lists.newArrayList(entity.getEquipmentAndArmor());
        }

        equipment.removeIf(stack -> stack.isEmpty() || !stack.isDamaged());

        if (equipment.isEmpty())
            return;

        ItemStack stack = equipment.get(entity.world.rand.nextInt(equipment.size()));

        if (stack.getDamage() - (amplifier + 1) < 0) {
            stack.setDamage(0);
        } else {
            stack.setDamage(stack.getDamage() - (amplifier + 1));
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return duration % damageTime == 0;
    }
}