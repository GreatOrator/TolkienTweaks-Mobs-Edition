package com.greatorator.tolkienmobs.integration.curios;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import java.util.List;

/** Borrowed from Draconic Evolution */
public class CurioWrapper implements ICurio {
    private final ItemStack stack;
    private ITolkienEquipment item;

    public CurioWrapper(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity) {
        this.item = (ITolkienEquipment) stack.getItem();
        item.equipmentTick(stack, livingEntity);
    }

    @Override
    public ItemStack getStack()
    {
        return this.stack;
    }

    @Nonnull
    @Override
    public ICurio.DropRule getDropRule(LivingEntity livingEntity)
    {
        return livingEntity.level.getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY) ? DropRule.ALWAYS_KEEP : DropRule.DESTROY;
    }

    @Override
    public boolean canEquip(String identifier, LivingEntity livingEntity) {
        return item.canEquip(livingEntity);
    }

    @Override
    public List<Component> getTagsTooltip(List<Component> tagTooltips) {
    	if (item != null && stack != null) {
    		return item.getTagsTooltip(stack, tagTooltips);
    	}
    	else if (tagTooltips != null) {
    		return tagTooltips;
    	}
    	return null;
    }

    @Override
    public boolean canRightClickEquip() {
        return item.canRightClickEquip(stack);
    }
}
