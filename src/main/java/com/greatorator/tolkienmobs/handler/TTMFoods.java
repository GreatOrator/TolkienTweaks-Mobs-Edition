package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.datagen.PotionGenerator;
import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class TTMFoods {

    public static final Food DRINK_ENT_DRAUGHT = (new Food.Builder()).hunger(1).saturation(1.0F).setAlwaysEdible().effect(() -> new EffectInstance(PotionGenerator.ENT_STANCE.get(), 600, 0), 0.3F).build();
    public static final Food DRINK_PERSONAL_BLACKSMITH = (new Food.Builder()).hunger(1).saturation(1.0F).setAlwaysEdible().effect(() -> new EffectInstance(PotionGenerator.PERSONAL_BLACKSMITH.get(), 600, 0), 0.3F).build();
    public static final Food DRINK_ELF_NIMBLENESS = (new Food.Builder()).hunger(1).saturation(1.0F).setAlwaysEdible().effect(() -> new EffectInstance(PotionGenerator.ELF_NIMBLENESS.get(), 600, 0), 0.3F).build();
    public static final Food DRINK_ELF_VITALITY = (new Food.Builder()).hunger(1).saturation(1.0F).setAlwaysEdible().effect(() -> new EffectInstance(PotionGenerator.ELF_VITALITY.get(), 600, 0), 0.3F).build();
    public static final Food DRINK_ERU_BLESSING = (new Food.Builder()).hunger(10).saturation(8.0F).setAlwaysEdible().effect(() -> new EffectInstance(PotionGenerator.ERU_BLESSING.get(), 100, 0), 0.3F).build();
    public static final Food MIRUVOR = (new Food.Builder()).hunger(1).saturation(1.0F).setAlwaysEdible().effect(() -> new EffectInstance(Effects.SPEED, 3000, 3), 0.3F).effect(() -> new EffectInstance(Effects.REGENERATION, 600, 3), 0.3F).effect(() -> new EffectInstance(Effects.NAUSEA, 40, 3), 0.3F).build();
    public static final Food GROG = (new Food.Builder()).hunger(10).saturation(8.0F).setAlwaysEdible().effect(() -> new EffectInstance(Effects.SPEED, 1500, 1), 0.3F).effect(() -> new EffectInstance(Effects.REGENERATION, 300, 3), 0.3F).effect(() -> new EffectInstance(Effects.NAUSEA, 100, 3), 0.3F).build();
    public static final Food LEMBAS = (new Food.Builder()).hunger(20).saturation(20.0F).effect(() -> new EffectInstance(Effects.ABSORPTION, 12000, 5), 0.3F).effect(() -> new EffectInstance(Effects.REGENERATION, 100, 5), 0.3F).build();
    public static final Food HONEY_CAKE = (new Food.Builder()).hunger(15).saturation(15.0F).effect(() -> new EffectInstance(Effects.ABSORPTION, 6000, 3), 0.3F).effect(() -> new EffectInstance(Effects.REGENERATION, 50, 3), 0.3F).build();
    public static final Food CRAM = (new Food.Builder()).hunger(10).saturation(10.0F).effect(() -> new EffectInstance(Effects.ABSORPTION, 3000, 1), 0.3F).effect(() -> new EffectInstance(Effects.REGENERATION, 25, 5), 0.3F).build();
    public static final Food MONSTER_FLESH = (new Food.Builder()).hunger(5).saturation(2.0F).effect(() -> new EffectInstance(Effects.HUNGER, 100, 2), 0.3F).build();
    public static final Food INSECT = (new Food.Builder()).hunger(1).saturation(1.0F).effect(() -> new EffectInstance(Effects.HUNGER, 10, 1), 0.3F).build();
    public static final Food GOLDEN_INSECT = (new Food.Builder()).hunger(2).saturation(3.0F).effect(() -> new EffectInstance(Effects.HUNGER, 40, 2), 0.3F).build();
    public static final Food TREE_ACORN = (new Food.Builder()).hunger(1).saturation(1.0F).effect(() -> new EffectInstance(Effects.SPEED, 10, 1), 0.3F).build();
    public static final Food GOLDEN_TREE_ACORN = (new Food.Builder()).hunger(2).saturation(3.0F).effect(() -> new EffectInstance(Effects.SPEED, 40, 2), 0.3F).build();
    public static final Food FOOD_HONEY = (new Food.Builder()).hunger(2).saturation(3.0F).effect(() -> new EffectInstance(Effects.SPEED, 100, 1), 0.3F).build();

}