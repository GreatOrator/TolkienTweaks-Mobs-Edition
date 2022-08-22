package com.greatorator.tolkienmobs.datagen;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableSet;
import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.block.Blocks;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class ProfessionGenerator {
   private static final Supplier<Set<PointOfInterestType>> WORKSTATIONS = Suppliers.memoize(() -> {
      return Registry.VILLAGER_PROFESSION.stream().map(VillagerProfession::getJobPoiType).collect(Collectors.toSet());
   });

   public static final Predicate<PointOfInterestType> ANY_VILLAGER_WORKSTATION = (type) -> {
      return WORKSTATIONS.get().contains(type);
   };

   public static final DeferredRegister<VillagerProfession> PROFESSION = DeferredRegister.create(ForgeRegistries.PROFESSIONS, MODID);
   public static final DeferredRegister<PointOfInterestType> POIT = DeferredRegister.create(ForgeRegistries.POI_TYPES, MODID);

   //#################################################################
   // POIT
   //#################################################################
   //Points of Interest Types
   public static final RegistryObject<PointOfInterestType> SLACKER = POIT.register("unemployed", ()-> new PointOfInterestType("unemployed", ImmutableSet.of(), 1,ANY_VILLAGER_WORKSTATION, 1));
   public static final RegistryObject<PointOfInterestType> COIN_TRADER = POIT.register("coin_trader", ()-> new PointOfInterestType("coin_trader", PointOfInterestType.getBlockStates(TTMContent.PIGGYBANK.get()), 1, 1));
   public static final RegistryObject<PointOfInterestType> GROCERY_STORE = POIT.register("grocery_store", ()-> new PointOfInterestType("grocery_store", PointOfInterestType.getBlockStates(TTMContent.TTMFIREPLACE.get()), 1, 1));
   public static final RegistryObject<PointOfInterestType> PET_MERCHANT = POIT.register("pet_merchant", ()-> new PointOfInterestType("pet_merchant", PointOfInterestType.getBlockStates(Blocks.HAY_BLOCK), 1, 1));
   public static final RegistryObject<PointOfInterestType> JUNK_TRADER = POIT.register("junk_trader", ()-> new PointOfInterestType("junk_trader", PointOfInterestType.getBlockStates(TTMContent.BARREL_MITHRIL.get()), 1, 1));
   public static final RegistryObject<PointOfInterestType> TRINKET_SMITH = POIT.register("trinket_smith", ()-> new PointOfInterestType("trinket_smith", PointOfInterestType.getBlockStates(TTMContent.BLOCK_HALLOWED.get()), 1, 1));
   public static final RegistryObject<PointOfInterestType> TRINKET_TAILOR = POIT.register("trinket_tailor", ()-> new PointOfInterestType("trinket_tailor", PointOfInterestType.getBlockStates(TTMContent.STONE_PATH.get()), 1, 1));

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
      return "Tolkien Tweaks - Mobs Edition Villager Professions";
   }
}
