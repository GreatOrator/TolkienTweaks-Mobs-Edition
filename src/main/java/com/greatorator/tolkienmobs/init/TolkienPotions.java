package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.effect.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;
import static com.greatorator.tolkienmobs.TolkienMobs.NAME;

public class TolkienPotions {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MODID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, MODID);

    //#################################################################
    // Effects
    //#################################################################
    public static final RegistryObject<MobEffect> ENT_STANCE = EFFECTS.register("ent_draught", () -> new BasePotionEffect(MobEffectCategory.BENEFICIAL, 3135135).addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, "38030aa0-6efa-11eb-9439-0242ac130002", (double)1.0F, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<MobEffect> ELF_VITALITY = EFFECTS.register("elf_vitality", () -> new ElvenLifeEffect(MobEffectCategory.BENEFICIAL, 14687673));
    public static final RegistryObject<MobEffect> ELEMENTAL_DROWNING = EFFECTS.register("elemental_drowning", () -> new DrownEffect(MobEffectCategory.HARMFUL, 7791097));
    public static final RegistryObject<MobEffect> ELEMENTAL_LIGHTNING = EFFECTS.register("elemental_lightning", () -> new LightningEffect(MobEffectCategory.HARMFUL, 16640281));
    public static final RegistryObject<MobEffect> ELEMENTAL_FLYING = EFFECTS.register("elemental_flight", () -> new FlyingEffect(MobEffectCategory.HARMFUL, 6498056));
    public static final RegistryObject<MobEffect> ELEMENTAL_BURNING = EFFECTS.register("elemental_burning", () -> new BurningEffect(MobEffectCategory.HARMFUL, 15545365));
    public static final RegistryObject<MobEffect> ELEMENTAL_TORNADO = EFFECTS.register("elemental_tornado", () -> new TornadoEffect(MobEffectCategory.HARMFUL, 11914084));
    public static final RegistryObject<MobEffect> INVENTORY_CORROSION = EFFECTS.register("inventory_corrosion", () -> new CorrosionEffect(MobEffectCategory.HARMFUL, 7811840));
    public static final RegistryObject<MobEffect> PERSONAL_BLACKSMITH = EFFECTS.register("personal_blacksmith", () -> new BlacksmithEffect(MobEffectCategory.BENEFICIAL, 14370245));
    public static final RegistryObject<MobEffect> SLEEPNESIA = EFFECTS.register("sleepnesia", () -> new SleepEffect(MobEffectCategory.HARMFUL, 2890775));
    public static final RegistryObject<MobEffect> WATCHER_FEAR = EFFECTS.register("dread_aura", () -> new FearEffect(MobEffectCategory.HARMFUL, 4852999));
    public static final RegistryObject<MobEffect> PARALYSING_FEAR = EFFECTS.register("crippling_terror", () -> new TerrorEffect(MobEffectCategory.HARMFUL, 5655556).addAttributeModifier(Attributes.MOVEMENT_SPEED, "380317e8-11eb-6efa-9439-0242ac130002", (double)-10.0F, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<MobEffect> ELF_NIMBLENESS = EFFECTS.register("elven_nimbleness", () -> new NimbleEffect(MobEffectCategory.BENEFICIAL, 16777062));
    public static final RegistryObject<MobEffect> ERU_BLESSING = EFFECTS.register("blessing_of_eru", () -> new EruEffect(MobEffectCategory.BENEFICIAL, 1873869));

    //#################################################################
    // Potions
    //#################################################################
    public static final RegistryObject<Potion> ENT_DRAUGHT = POTIONS.register("ent_draught", () -> new Potion(new MobEffectInstance(ENT_STANCE.get(), 2400)));
    public static final RegistryObject<Potion> ELVISH_LIFE = POTIONS.register("elf_vitality", () -> new Potion(new MobEffectInstance(ELF_VITALITY.get(), 4800)));
    public static final RegistryObject<Potion> GOLEM_DROWNING = POTIONS.register("elemental_drowning", () -> new Potion(new MobEffectInstance(ELEMENTAL_DROWNING.get(), 600)));
    public static final RegistryObject<Potion> GOLEM_LIGHTNING = POTIONS.register("elemental_lightning", () -> new Potion(new MobEffectInstance(ELEMENTAL_LIGHTNING.get(), 600)));
    public static final RegistryObject<Potion> GOLEM_FLYING = POTIONS.register("elemental_flight", () -> new Potion(new MobEffectInstance(ELEMENTAL_FLYING.get(), 600)));
    public static final RegistryObject<Potion> GOLEM_BURN = POTIONS.register("elemental_burning", () -> new Potion(new MobEffectInstance(ELEMENTAL_BURNING.get(), 600)));
    public static final RegistryObject<Potion> GOLEM_TORNADO = POTIONS.register("elemental_tornado", () -> new Potion(new MobEffectInstance(ELEMENTAL_TORNADO.get(), 600)));
    public static final RegistryObject<Potion> DECAYING_INVENTORY = POTIONS.register("inventory_corrosion", () -> new Potion(new MobEffectInstance(INVENTORY_CORROSION.get(), 600)));
    public static final RegistryObject<Potion> PORTABLE_REPAIR = POTIONS.register("personal_blacksmith", () -> new Potion(new MobEffectInstance(PERSONAL_BLACKSMITH.get(), 600)));
    public static final RegistryObject<Potion> DEEP_SLEEP = POTIONS.register("sleepnesia", () -> new Potion(new MobEffectInstance(SLEEPNESIA.get(), 600)));
    public static final RegistryObject<Potion> DREAD_AURA = POTIONS.register("dread_aura", () -> new Potion(new MobEffectInstance(WATCHER_FEAR.get(), 160)));
    public static final RegistryObject<Potion> CRIPPLING_TERROR = POTIONS.register("crippling_terror", () -> new Potion(new MobEffectInstance(PARALYSING_FEAR.get(), 200)));
    public static final RegistryObject<Potion> ELF_FLEETFOOT = POTIONS.register("elven_nimbleness", () -> new Potion(new MobEffectInstance(ELF_NIMBLENESS.get(), 300)));
    public static final RegistryObject<Potion> BLESSING_OF_ERU = POTIONS.register("blessing_of_eru", () -> new Potion(new MobEffectInstance(ERU_BLESSING.get(), 100)));
    public static final RegistryObject<Potion> ISTARI = POTIONS.register("istari", Potion::new);

    public String getName() {
        return NAME + " - Potions";
    }
}