package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.handler.TTMPotion;
import com.greatorator.tolkienmobs.item.potiontypes.*;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
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
    public static final Potion ELEMENTAL_DROWNING = new PotionTTMDrowning();
    public static final Potion ELEMENTAL_LIGHTNING = new PotionTTMLightning();
    public static final Potion ELEMENTAL_FLYING = new PotionTTMFlying();
    public static final Potion ELEMENTAL_BURNING = new PotionTTMBurning();
    public static final Potion ELEMENTAL_TORNADO = new PotionTTMTornado();
    public static final Potion INVENTORY_CORROSION = new PotionTTMCorrosion("inventory_corrosion", true, 7811840, 7);
    public static final Potion PERSONAL_BLACKSMITH = new PotionTTMBlacksmith("personal_blacksmith", false, 14370245, 8);
    public static final Potion SLEEPNESIA = new PotionTTMSleep("sleepnesia", true, 2890775, 9).registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, MathHelper.getRandomUUID().toString(), -1D, 1);
    public static final Potion WATCHER_FEAR = new PotionTTMFear("dread_aura", true, 4852999, 10);
    public static final Potion PARALYSING_FEAR = new PotionTTMTerror("crippling_terror", true, 5655556, 11).registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, MathHelper.getRandomUUID().toString(), -1D, 1);
    public static final Potion ELF_NIMBLENESS = new PotionTTMUphill("elven_nimbleness", false, 16777062, 12);
    public static final Potion ERU_BLESSING = new PotionTMAntidote("blessing_of_eru", false, 1873869, 13);

    /** Initialize Potion Versions */
    public static final PotionType ENT_DRAUGHT = new PotionType("ent_draught", new PotionEffect[] {new PotionEffect(ENT_STANCE, 2400)}).setRegistryName("ent_draught");
    public static final PotionType LONG_ENT_DRAUGHT = new PotionType("ent_draught", new PotionEffect[] {new PotionEffect(ENT_STANCE, 4800)}).setRegistryName("long_ent_draught");
    public static final PotionType ELVISH_LIFE = new PotionType("elf_vitality", new PotionEffect[] {new PotionEffect(ELF_VITALITY, 24000)}).setRegistryName("elf_vitality");
    public static final PotionType LONG_ELVISH_LIFE = new PotionType("elf_vitality", new PotionEffect[] {new PotionEffect(ELF_VITALITY, 48000)}).setRegistryName("long_elf_vitality");
    public static final PotionType GOLEM_DROWNING = new PotionType("elemental_drowning", new PotionEffect[] {new PotionEffect(ELEMENTAL_DROWNING, 600)}).setRegistryName("elemental_drowning");
    public static final PotionType LONG_GOLEM_DROWNING = new PotionType("elemental_drowning", new PotionEffect[] {new PotionEffect(ELEMENTAL_DROWNING, 1200)}).setRegistryName("long_elemental_drowning");
    public static final PotionType GOLEM_LIGHTNING = new PotionType("elemental_lightning", new PotionEffect[] {new PotionEffect(ELEMENTAL_LIGHTNING, 600)}).setRegistryName("elemental_lightning");
    public static final PotionType LONG_GOLEM_LIGHTNING = new PotionType("elemental_lightning", new PotionEffect[] {new PotionEffect(ELEMENTAL_LIGHTNING, 1200)}).setRegistryName("long_elemental_lightning");
    public static final PotionType GOLEM_FLYING = new PotionType("elemental_flight", new PotionEffect[] {new PotionEffect(ELEMENTAL_FLYING, 600)}).setRegistryName("elemental_flight");
    public static final PotionType LONG_GOLEM_FLYING = new PotionType("elemental_flight", new PotionEffect[] {new PotionEffect(ELEMENTAL_FLYING, 1200)}).setRegistryName("long_elemental_flight");
    public static final PotionType GOLEM_BURN = new PotionType("elemental_burning", new PotionEffect[] {new PotionEffect(ELEMENTAL_BURNING, 600)}).setRegistryName("elemental_burning");
    public static final PotionType LONG_GOLEM_BURN = new PotionType("elemental_burning", new PotionEffect[] {new PotionEffect(ELEMENTAL_BURNING, 1200)}).setRegistryName("long_elemental_burning");
    public static final PotionType GOLEM_TORNADO = new PotionType("elemental_tornado", new PotionEffect[] {new PotionEffect(ELEMENTAL_TORNADO, 600)}).setRegistryName("elemental_tornado");
    public static final PotionType LONG_GOLEM_TORNADO = new PotionType("elemental_tornado", new PotionEffect[] {new PotionEffect(ELEMENTAL_TORNADO, 1200)}).setRegistryName("long_elemental_tornado");
    public static final PotionType DECAYING_INVENTORY = new PotionType("inventory_corrosion", new PotionEffect[] {new PotionEffect(INVENTORY_CORROSION, 600)}).setRegistryName("inventory_corrosion");
    public static final PotionType LONG_DECAYING_INVENTORY = new PotionType("inventory_corrosion", new PotionEffect[] {new PotionEffect(INVENTORY_CORROSION, 1200)}).setRegistryName("long_inventory_corrosion");
    public static final PotionType PORTABLE_REPAIR = new PotionType("personal_blacksmith", new PotionEffect[] {new PotionEffect(PERSONAL_BLACKSMITH, 600)}).setRegistryName("personal_blacksmith");
    public static final PotionType LONG_PORTABLE_REPAIR = new PotionType("personal_blacksmith", new PotionEffect[] {new PotionEffect(PERSONAL_BLACKSMITH, 1200)}).setRegistryName("long_personal_blacksmith");
    public static final PotionType DEEP_SLEEP = new PotionType("sleepnesia", new PotionEffect[] {new PotionEffect(SLEEPNESIA, 600)}).setRegistryName("sleepnesia");
    public static final PotionType LONG_DEEP_SLEEP = new PotionType("sleepnesia", new PotionEffect[] {new PotionEffect(SLEEPNESIA, 1200)}).setRegistryName("long_sleepnesia");
    public static final PotionType DREAD_AURA = new PotionType("dread_aura", new PotionEffect[] {new PotionEffect(WATCHER_FEAR, 300)}).setRegistryName("dread_aura");
    public static final PotionType LONG_DREAD_AURA = new PotionType("dread_aura", new PotionEffect[] {new PotionEffect(WATCHER_FEAR, 600)}).setRegistryName("long_dread_aura");
    public static final PotionType CRIPPLING_TERROR = new PotionType("crippling_terror", new PotionEffect[] {new PotionEffect(PARALYSING_FEAR, 200)}).setRegistryName("crippling_terror");
    public static final PotionType LONG_CRIPPLING_TERROR = new PotionType("crippling_terror", new PotionEffect[] {new PotionEffect(PARALYSING_FEAR, 600)}).setRegistryName("long_crippling_terror");
    public static final PotionType ELF_FLEETFOOT = new PotionType("elven_nimbleness", new PotionEffect[] {new PotionEffect(ELF_NIMBLENESS, 300)}).setRegistryName("elven_nimbleness");
    public static final PotionType LONG_ELF_FLEETFOOT = new PotionType("elven_nimbleness", new PotionEffect[] {new PotionEffect(ELF_NIMBLENESS, 600)}).setRegistryName("long_elven_nimbleness");
    public static final PotionType BLESSING_OF_ERU = new PotionType("blessing_of_eru", new PotionEffect[] {new PotionEffect(ERU_BLESSING, 300)}).setRegistryName("blessing_of_eru");
    public static final PotionType LONG_BLESSING_OF_ERU = new PotionType("blessing_of_eru", new PotionEffect[] {new PotionEffect(ERU_BLESSING, 600)}).setRegistryName("long_blessing_of_eru");

    public static void registerPotions(){
        registerPotion(ENT_DRAUGHT, LONG_ENT_DRAUGHT, ENT_STANCE);
        registerPotion(ELVISH_LIFE, LONG_ELVISH_LIFE, ELF_VITALITY);
        registerPotion(GOLEM_DROWNING, LONG_GOLEM_DROWNING, ELEMENTAL_DROWNING);
        registerPotion(GOLEM_LIGHTNING, LONG_GOLEM_LIGHTNING, ELEMENTAL_LIGHTNING);
        registerPotion(GOLEM_FLYING, LONG_GOLEM_FLYING, ELEMENTAL_FLYING);
        registerPotion(GOLEM_BURN, LONG_GOLEM_BURN, ELEMENTAL_BURNING);
        registerPotion(GOLEM_TORNADO, LONG_GOLEM_TORNADO, ELEMENTAL_TORNADO);
        registerPotion(DECAYING_INVENTORY, LONG_DECAYING_INVENTORY, INVENTORY_CORROSION);
        registerPotion(PORTABLE_REPAIR, LONG_PORTABLE_REPAIR, PERSONAL_BLACKSMITH);
        registerPotion(DEEP_SLEEP, LONG_DEEP_SLEEP, SLEEPNESIA);
        registerPotion(DREAD_AURA, LONG_DREAD_AURA, WATCHER_FEAR);
        registerPotion(CRIPPLING_TERROR, LONG_CRIPPLING_TERROR, PARALYSING_FEAR);
        registerPotion(ELF_FLEETFOOT, LONG_ELF_FLEETFOOT, ELF_NIMBLENESS);
        registerPotion(BLESSING_OF_ERU, LONG_BLESSING_OF_ERU, ERU_BLESSING);

    }

    private static void registerPotion(PotionType defaultPotion, PotionType longPotion, Potion effect){
        ForgeRegistries.POTIONS.register(effect);
        ForgeRegistries.POTION_TYPES.register(defaultPotion);
        ForgeRegistries.POTION_TYPES.register(longPotion);

        registerPotionMixes();
    }

    private static void registerPotionMixes(){
        PotionHelper.addMix(PotionTypes.AWKWARD, TTMFeatures.CRAM, ENT_DRAUGHT);
        PotionHelper.addMix(ENT_DRAUGHT, TTMFeatures.GEM_AMMOLITE,LONG_ENT_DRAUGHT);
        PotionHelper.addMix(PotionTypes.AWKWARD, TTMFeatures.LEMBAS, ELVISH_LIFE);
        PotionHelper.addMix(ELVISH_LIFE, TTMFeatures.GEM_AMMOLITE,LONG_ELVISH_LIFE);
        PotionHelper.addMix(PotionTypes.AWKWARD, TTMFeatures.NUGGET_MITHRIL, PotionTypes.MUNDANE);
        PotionHelper.addMix(PotionTypes.MUNDANE, TTMFeatures.GEM_AMMOLITE, PotionTypes.THICK);
        PotionHelper.addMix(PotionTypes.THICK, Item.getItemFromBlock(TTMFeatures.TM_MUSHROOM),BLESSING_OF_ERU);
        PotionHelper.addMix(BLESSING_OF_ERU, TTMFeatures.GEM_AMMOLITE,LONG_BLESSING_OF_ERU);
        PotionHelper.addMix(PotionTypes.AWKWARD, TTMFeatures.GOLDEN_INSECT, ELF_FLEETFOOT);
        PotionHelper.addMix(ELF_FLEETFOOT, TTMFeatures.GEM_AMMOLITE,LONG_ELF_FLEETFOOT);
        PotionHelper.addMix(PotionTypes.AWKWARD, Item.getItemFromBlock(TTMFeatures.BLOCK_MITHRIL), PORTABLE_REPAIR);
        PotionHelper.addMix(PORTABLE_REPAIR, TTMFeatures.GEM_AMMOLITE,LONG_PORTABLE_REPAIR);
    }
}