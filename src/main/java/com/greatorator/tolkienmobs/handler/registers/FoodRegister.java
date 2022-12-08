package com.greatorator.tolkienmobs.handler.registers;

import com.greatorator.tolkienmobs.init.TolkienPotions;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class FoodRegister {

    public static final FoodProperties DRINK_ENT_DRAUGHT = (new FoodProperties.Builder()).nutrition(1).saturationMod(1.0F).alwaysEat().effect(() -> new MobEffectInstance(TolkienPotions.ENT_STANCE.get(), 600, 0), 0.3F).build();
    public static final FoodProperties DRINK_PERSONAL_BLACKSMITH = (new FoodProperties.Builder()).nutrition(1).saturationMod(1.0F).alwaysEat().effect(() -> new MobEffectInstance(TolkienPotions.PERSONAL_BLACKSMITH.get(), 600, 0), 0.3F).build();
    public static final FoodProperties DRINK_ELF_NIMBLENESS = (new FoodProperties.Builder()).nutrition(1).saturationMod(1.0F).alwaysEat().effect(() -> new MobEffectInstance(TolkienPotions.ELF_NIMBLENESS.get(), 600, 0), 0.3F).build();
    public static final FoodProperties DRINK_ELF_VITALITY = (new FoodProperties.Builder()).nutrition(1).saturationMod(1.0F).alwaysEat().effect(() -> new MobEffectInstance(TolkienPotions.ELF_VITALITY.get(), 600, 0), 0.3F).build();
    public static final FoodProperties DRINK_ERU_BLESSING = (new FoodProperties.Builder()).nutrition(10).saturationMod(8.0F).alwaysEat().effect(() -> new MobEffectInstance(TolkienPotions.ERU_BLESSING.get(), 100, 0), 0.3F).build();
    public static final FoodProperties MIRUVOR = (new FoodProperties.Builder()).nutrition(1).saturationMod(1.0F).alwaysEat().effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 3000, 3), 0.3F).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600, 3), 0.3F).effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 40, 3), 0.3F).build();
    public static final FoodProperties GROG = (new FoodProperties.Builder()).nutrition(10).saturationMod(8.0F).alwaysEat().effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1500, 1), 0.3F).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 300, 3), 0.3F).effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 100, 3), 0.3F).build();
    public static final FoodProperties LEMBAS = (new FoodProperties.Builder()).nutrition(20).saturationMod(20.0F).effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 12000, 5), 0.3F).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 5), 0.3F).build();
    public static final FoodProperties HONEY_CAKE = (new FoodProperties.Builder()).nutrition(15).saturationMod(15.0F).effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 6000, 3), 0.3F).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 50, 3), 0.3F).build();
    public static final FoodProperties CRAM = (new FoodProperties.Builder()).nutrition(10).saturationMod(10.0F).effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 3000, 1), 0.3F).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 25, 5), 0.3F).build();
    public static final FoodProperties MONSTER_FLESH = (new FoodProperties.Builder()).nutrition(5).saturationMod(2.0F).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 100, 2), 0.3F).build();
    public static final FoodProperties INSECT = (new FoodProperties.Builder()).nutrition(1).saturationMod(1.0F).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 10, 1), 0.3F).build();
    public static final FoodProperties GOLDEN_INSECT = (new FoodProperties.Builder()).nutrition(2).saturationMod(3.0F).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 40, 2), 0.3F).build();
    public static final FoodProperties TREE_ACORN = (new FoodProperties.Builder()).nutrition(1).saturationMod(1.0F).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 10, 1), 0.3F).build();
    public static final FoodProperties GOLDEN_TREE_ACORN = (new FoodProperties.Builder()).nutrition(2).saturationMod(3.0F).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 2), 0.3F).build();
    public static final FoodProperties FOOD_HONEY = (new FoodProperties.Builder()).nutrition(2).saturationMod(3.0F).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 1), 0.3F).build();

}