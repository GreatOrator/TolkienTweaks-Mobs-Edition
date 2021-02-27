package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.enchantments.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class EnchantmentGenerator {
    private static final EquipmentSlotType[] ARMOR_SLOTS = new EquipmentSlotType[]{EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET};

    public static final DeferredRegister<Enchantment> ENCHANTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MODID);

    public static final RegistryObject<Enchantment> BALROG_MARK = ENCHANTS.register("balrogs_mark", () -> new EnchantmentBalrogMark(Enchantment.Rarity.RARE, EquipmentSlotType.FEET));
    public static final RegistryObject<Enchantment> ELVEN_LONGEVITY = ENCHANTS.register("elven_longevity", () -> new EnchantmentElvenLongevity(Enchantment.Rarity.RARE, EquipmentSlotType.CHEST));
    public static final RegistryObject<Enchantment> GONDOR_RESOLVE = ENCHANTS.register("gondor_resolve", () -> new EnchantmentGondorResolve(Enchantment.Rarity.RARE, EquipmentSlotType.LEGS));
    public static final RegistryObject<Enchantment> DWARF_ENDURANCE = ENCHANTS.register("dwarven_endurance", () -> new EnchantmentDwarvenEndurance(Enchantment.Rarity.RARE, EquipmentSlotType.HEAD));
    public static final RegistryObject<Enchantment> HOBBIT_PLOW = ENCHANTS.register("hobbit_plow", () -> new EnchantmentHobbitPlow(Enchantment.Rarity.RARE, EquipmentSlotType.MAINHAND));
//    public static final RegistryObject<Enchantment> HOBBIT_HARVEST = ENCHANTS.register("hobbit_harvest", () -> new EnchantmentHobbitHarvest(Enchantment.Rarity.RARE, EquipmentSlotType.MAINHAND));


    private static Enchantment register(String key, Enchantment enchantment) {
        return Registry.register(Registry.ENCHANTMENT, key, enchantment);
    }

    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Enchantments";
    }
}
