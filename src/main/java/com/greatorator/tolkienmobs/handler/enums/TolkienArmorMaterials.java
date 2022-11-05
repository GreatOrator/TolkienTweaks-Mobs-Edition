package com.greatorator.tolkienmobs.handler.enums;

import com.greatorator.tolkienmobs.init.TolkienItems;
import net.covers1624.quack.util.LazyValue;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum TolkienArmorMaterials implements ArmorMaterial {
    MITHRIL("mithril", 41, new int[]{5, 8, 10, 5}, 30, SoundEvents.ARMOR_EQUIP_NETHERITE, 4.0F, 0.4F, () -> {
        return Ingredient.of(TolkienItems.INGOT_MITHRIL.get());
    }),
    MORGULIRON("morguliron", 39, new int[]{4, 7, 9, 4}, 22, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.5F, 0.2F, () -> {
        return Ingredient.of(TolkienItems.INGOT_MORGULIRON.get());
    });

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairIngredient;

    private TolkienArmorMaterials(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyValue<>(repairIngredient);
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlot slotIn) {
        return HEALTH_PER_SLOT[slotIn.getIndex()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot slotIn) {
        return this.slotProtections[slotIn.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.sound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    /**
     * Gets the percentage of knockback resistance provided by armor of the material.
     */
    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
