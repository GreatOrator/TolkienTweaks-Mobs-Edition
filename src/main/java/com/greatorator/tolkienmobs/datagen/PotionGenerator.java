package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.item.potion.effects.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class PotionGenerator {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, MODID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, MODID);

    //#################################################################
    // Effects
    //#################################################################
    public static final RegistryObject<Effect> ENT_STANCE = EFFECTS.register("ent_draught", () -> new TTMEffectBase(EffectType.BENEFICIAL, 3135135).addAttributesModifier(Attributes.KNOCKBACK_RESISTANCE, "38030aa0-6efa-11eb-9439-0242ac130002", (double)1.0F, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<Effect> ELF_VITALITY = EFFECTS.register("elf_vitality", () -> new TTMEffectBase(EffectType.BENEFICIAL, 14687673).addAttributesModifier(Attributes.MAX_HEALTH, "38031022-6efa-11eb-9439-0242ac130002", (double)1.0F, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<Effect> ELEMENTAL_DROWNING = EFFECTS.register("elemental_drowning", () -> new DrownTTMEffect(EffectType.HARMFUL, 7791097));
    public static final RegistryObject<Effect> ELEMENTAL_LIGHTNING = EFFECTS.register("elemental_lightning", () -> new LightningTTMEffect(EffectType.HARMFUL, 16640281));
    public static final RegistryObject<Effect> ELEMENTAL_FLYING = EFFECTS.register("elemental_flight", () -> new FlyingTTMEffect(EffectType.HARMFUL, 6498056));
    public static final RegistryObject<Effect> ELEMENTAL_BURNING = EFFECTS.register("elemental_burning", () -> new BurningTTMEffect(EffectType.HARMFUL, 15545365));
//    public static final RegistryObject<Effect> ELEMENTAL_TORNADO = EFFECTS.register("elemental_tornado", () -> new TTMEffectBase(EffectType.HARMFUL, 11914084));
    public static final RegistryObject<Effect> INVENTORY_CORROSION = EFFECTS.register("inventory_corrosion", () -> new CorrosionTTMEffect(EffectType.HARMFUL, 7811840));
    public static final RegistryObject<Effect> PERSONAL_BLACKSMITH = EFFECTS.register("personal_blacksmith", () -> new BlacksmithTTMEffect(EffectType.BENEFICIAL, 14370245));
    public static final RegistryObject<Effect> SLEEPNESIA = EFFECTS.register("sleepnesia", () -> new SleepTTMEffect(EffectType.HARMFUL, 2890775));
    public static final RegistryObject<Effect> WATCHER_FEAR = EFFECTS.register("dread_aura", () -> new FearTTMEffect(EffectType.HARMFUL, 4852999));
    public static final RegistryObject<Effect> PARALYSING_FEAR = EFFECTS.register("crippling_terror", () -> new TerrorTTMEffect(EffectType.HARMFUL, 5655556).addAttributesModifier(Attributes.MOVEMENT_SPEED, "380317e8-11eb-6efa-9439-0242ac130002", (double)-10.0F, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<Effect> ELF_NIMBLENESS = EFFECTS.register("elven_nimbleness", () -> new NimbleTTMEffect(EffectType.BENEFICIAL, 16777062));
    public static final RegistryObject<Effect> ERU_BLESSING = EFFECTS.register("blessing_of_eru", () -> new EruTTMEffect(EffectType.BENEFICIAL, 1873869));

    //#################################################################
    // Potions
    //#################################################################
    public static final RegistryObject<Potion> ENT_DRAUGHT = POTIONS.register("ent_draught", () -> new Potion(new EffectInstance(ENT_STANCE.get(), 2400)));
    public static final RegistryObject<Potion> ELVISH_LIFE = POTIONS.register("elf_vitality", () -> new Potion(new EffectInstance(ELF_VITALITY.get(), 4800)));
    public static final RegistryObject<Potion> GOLEM_DROWNING = POTIONS.register("elemental_drowning", () -> new Potion(new EffectInstance(ELEMENTAL_DROWNING.get(), 600)));
    public static final RegistryObject<Potion> GOLEM_LIGHTNING = POTIONS.register("elemental_lightning", () -> new Potion(new EffectInstance(ELEMENTAL_LIGHTNING.get(), 600)));
    public static final RegistryObject<Potion> GOLEM_FLYING = POTIONS.register("elemental_flight", () -> new Potion(new EffectInstance(ELEMENTAL_FLYING.get(), 600)));
    public static final RegistryObject<Potion> GOLEM_BURN = POTIONS.register("elemental_burning", () -> new Potion(new EffectInstance(ELEMENTAL_BURNING.get(), 600)));
//    public static final RegistryObject<Potion> GOLEM_TORNADO = POTIONS.register("elemental_tornado", () -> new Potion(new EffectInstance(ELEMENTAL_TORNADO.get(), 600)));
    public static final RegistryObject<Potion> DECAYING_INVENTORY = POTIONS.register("inventory_corrosion", () -> new Potion(new EffectInstance(INVENTORY_CORROSION.get(), 600)));
    public static final RegistryObject<Potion> PORTABLE_REPAIR = POTIONS.register("personal_blacksmith", () -> new Potion(new EffectInstance(PERSONAL_BLACKSMITH.get(), 600)));
    public static final RegistryObject<Potion> DEEP_SLEEP = POTIONS.register("sleepnesia", () -> new Potion(new EffectInstance(SLEEPNESIA.get(), 600)));
    public static final RegistryObject<Potion> DREAD_AURA = POTIONS.register("dread_aura", () -> new Potion(new EffectInstance(WATCHER_FEAR.get(), 160)));
    public static final RegistryObject<Potion> CRIPPLING_TERROR = POTIONS.register("crippling_terror", () -> new Potion(new EffectInstance(PARALYSING_FEAR.get(), 200)));
    public static final RegistryObject<Potion> ELF_FLEETFOOT = POTIONS.register("elven_nimbleness", () -> new Potion(new EffectInstance(ELF_NIMBLENESS.get(), 300)));
    public static final RegistryObject<Potion> BLESSING_OF_ERU = POTIONS.register("blessing_of_eru", () -> new Potion(new EffectInstance(ERU_BLESSING.get(), 100)));

    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Potions";
    }
}
