package com.greatorator.tolkienmobs.item.potion;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CorrosionEffect extends PotionBaseEffect {
    private final Map<Attribute, AttributeModifier> attributeModifierMap = Maps.newHashMap();
    public static CorrosionEffect instance = null;
    public static int damageTime = 20;

    public CorrosionEffect(MobEffectCategory typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
        instance = this;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
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