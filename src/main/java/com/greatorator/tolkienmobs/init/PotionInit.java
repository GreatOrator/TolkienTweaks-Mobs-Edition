package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.handler.TTMPotion;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class PotionInit {
    /** Initialize Potion Types */
    public static final Potion ENT_STANCE = new TTMPotion("ent_draught", false, 3135135, 0).registerPotionAttributeModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, MathHelper.getRandomUUID().toString(),1.0D,2);
    public static final Potion ELF_VITALITY = new TTMPotion("elf_vitality", false, 14687673, 1).registerPotionAttributeModifier(SharedMonsterAttributes.MAX_HEALTH, MathHelper.getRandomUUID().toString(),1.0D,2);

    /** Initialize Potion Versions */
    public static final PotionType ENT_DRAUGHT = new PotionType("ent_draught", new PotionEffect[] {new PotionEffect(ENT_STANCE, 2400)}).setRegistryName("ent_draught");
    public static final PotionType LONG_ENT_DRAUGHT = new PotionType("ent_draught", new PotionEffect[] {new PotionEffect(ENT_STANCE, 4800)}).setRegistryName("long_ent_draught");
    public static final PotionType ELVISH_LIFE = new PotionType("elf_vitality", new PotionEffect[] {new PotionEffect(ELF_VITALITY, 24000)}).setRegistryName("elf_vitality");
    public static final PotionType LONG_ELVISH_LIFE = new PotionType("elf_vitality", new PotionEffect[] {new PotionEffect(ELF_VITALITY, 48000)}).setRegistryName("long_elf_vitality");

    public static void registerPotions(){
        registerPotion(ENT_DRAUGHT, LONG_ENT_DRAUGHT, ENT_STANCE);
        registerPotion(ELVISH_LIFE, LONG_ELVISH_LIFE, ELF_VITALITY);

    }

    private static void registerPotion(PotionType defaultPotion, PotionType longPotion, Potion effect){
        ForgeRegistries.POTIONS.register(effect);
        ForgeRegistries.POTION_TYPES.register(defaultPotion);
        ForgeRegistries.POTION_TYPES.register(longPotion);

        registerPotionMixes();
    }

    private static void registerPotionMixes(){
        PotionHelper.addMix(PotionTypes.AWKWARD, TTMFeatures.CRAM, ENT_DRAUGHT);
        PotionHelper.addMix(PotionTypes.AWKWARD, TTMFeatures.LEMBAS, ELVISH_LIFE);
        PotionHelper.addMix(ENT_DRAUGHT, TTMFeatures.GEM_AMMOLITE,LONG_ENT_DRAUGHT);
        PotionHelper.addMix(ELVISH_LIFE, TTMFeatures.GEM_AMMOLITE,LONG_ELVISH_LIFE);
    }
}