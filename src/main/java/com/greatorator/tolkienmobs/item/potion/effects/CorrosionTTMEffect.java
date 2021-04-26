package com.greatorator.tolkienmobs.item.potion.effects;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CorrosionTTMEffect extends TTMEffectBase {
    private final Map<Attribute, AttributeModifier> attributeModifierMap = Maps.newHashMap();
    public static CorrosionTTMEffect instance = null;
    public static int damageTime = 20;

    public CorrosionTTMEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
        instance = this;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
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

        equipment.removeIf(stack -> stack.isDamaged() || !stack.getItem().canBeDepleted() || stack.getItem().isValidRepairItem(stack, new ItemStack(Items.GOLD_INGOT)));

        if (!equipment.isEmpty()) {
            equipment.get(entity.level.random.nextInt(equipment.size())). hurtAndBreak(amplifier + 1, entity, null);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % damageTime == 0;
    }
}