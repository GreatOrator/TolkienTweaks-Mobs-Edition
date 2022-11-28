package com.greatorator.tolkienmobs.init;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Registry;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;
import static com.greatorator.tolkienmobs.TolkienMobs.NAME;

public class TolkienProfessions {
    private static final Supplier<Set<PoiType>> WORKSTATIONS = Suppliers.memoize(() -> {
        return Registry.VILLAGER_PROFESSION.stream().map(VillagerProfession::getJobPoiType).collect(Collectors.toSet());
    });

    public static final Predicate<PoiType> ANY_VILLAGER_WORKSTATION = (type) -> {
        return WORKSTATIONS.get().contains(type);
    };

    public static final DeferredRegister<VillagerProfession> PROFESSION = DeferredRegister.create(ForgeRegistries.PROFESSIONS, MODID);
    public static final DeferredRegister<PoiType> POIT = DeferredRegister.create(ForgeRegistries.POI_TYPES, MODID);

    //#################################################################
    // POIT
    //#################################################################
    //Points of Interest Types
    public static final RegistryObject<PoiType> SLACKER = POIT.register("unemployed", ()-> new PoiType("unemployed", ImmutableSet.of(), 1,ANY_VILLAGER_WORKSTATION, 1));
    public static final RegistryObject<PoiType> COIN_TRADER = POIT.register("coin_trader", ()-> new PoiType("coin_trader", PoiType.getBlockStates(TolkienBlocks.PIGGYBANK.get()), 1, 1));
    public static final RegistryObject<PoiType> GROCERY_STORE = POIT.register("grocery_store", ()-> new PoiType("grocery_store", PoiType.getBlockStates(TolkienBlocks.TTMFIREPLACE.get()), 1, 1));
    public static final RegistryObject<PoiType> PET_MERCHANT = POIT.register("pet_merchant", ()-> new PoiType("pet_merchant", PoiType.getBlockStates(Blocks.HAY_BLOCK), 1, 1));
    public static final RegistryObject<PoiType> JUNK_TRADER = POIT.register("junk_trader", ()-> new PoiType("junk_trader", PoiType.getBlockStates(TolkienBlocks.BARREL_MITHRIL.get()), 1, 1));
    public static final RegistryObject<PoiType> TRINKET_SMITH = POIT.register("trinket_smith", ()-> new PoiType("trinket_smith", PoiType.getBlockStates(TolkienBlocks.BLOCK_HALLOWED.get()), 1, 1));
    public static final RegistryObject<PoiType> TRINKET_TAILOR = POIT.register("trinket_tailor", ()-> new PoiType("trinket_tailor", PoiType.getBlockStates(TolkienBlocks.STONE_PATH.get()), 1, 1));
    public static final RegistryObject<PoiType> ARDA_PORTAL = POIT.register("arda_portal", () -> new PoiType("arda_portal", PoiType.getBlockStates(TolkienBlocks.ARDA_PORTAL.get()), 0, 1));

    public static void registerBanker() {
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class, "registerBlockStates", PoiType.class).invoke(null, COIN_TRADER.get());
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void registerGrocer() {
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class, "registerBlockStates", PoiType.class).invoke(null, GROCERY_STORE.get());
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void registerPet() {
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class, "registerBlockStates", PoiType.class).invoke(null, PET_MERCHANT.get());
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void registerJunk() {
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class, "registerBlockStates", PoiType.class).invoke(null, JUNK_TRADER.get());
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void registerSmith() {
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class, "registerBlockStates", PoiType.class).invoke(null, TRINKET_SMITH.get());
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void registerTailor() {
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class, "registerBlockStates", PoiType.class).invoke(null, TRINKET_TAILOR.get());
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //#################################################################
    // Profession Registry
    //#################################################################
    // Profession
    public static final RegistryObject<VillagerProfession> UNEMPLOYED_PROFESSION = PROFESSION.register("unemployed", () -> new VillagerProfession("unemployed", SLACKER.get(), ImmutableSet.of(), ImmutableSet.of(), (SoundEvent)null));
    public static final RegistryObject<VillagerProfession> COIN_TRADER_PROFESSION = PROFESSION.register("coin_trader", () -> new VillagerProfession("coin_trader", COIN_TRADER.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_CARTOGRAPHER));
    public static final RegistryObject<VillagerProfession> GROCERY_STORE_PROFESSION = PROFESSION.register("grocery_store", () -> new VillagerProfession("grocery_store", GROCERY_STORE.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_BUTCHER));
    public static final RegistryObject<VillagerProfession> PET_MERCHANT_PROFESSION = PROFESSION.register("pet_merchant", () -> new VillagerProfession("pet_merchant", PET_MERCHANT.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_LIBRARIAN));
    public static final RegistryObject<VillagerProfession> JUNK_TRADER_PROFESSION = PROFESSION.register("junk_trader", () -> new VillagerProfession("junk_trader", JUNK_TRADER.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.WANDERING_TRADER_TRADE));
    public static final RegistryObject<VillagerProfession> TRINKET_SMITH_PROFESSION = PROFESSION.register("trinket_smith", () -> new VillagerProfession("trinket_smith", TRINKET_SMITH.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_ARMORER));
    public static final RegistryObject<VillagerProfession> TRINKET_TAILOR_PROFESSION = PROFESSION.register("trinket_tailor", () -> new VillagerProfession("trinket_tailor", TRINKET_TAILOR.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_LEATHERWORKER));

    public String getName() {
        return NAME + " - Professions & POIT";
    }
}
