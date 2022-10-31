package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.enchantments.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class TolkienEnchants {
    private static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};

    public static final DeferredRegister<Enchantment> ENCHANTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MODID);

    public static final RegistryObject<Enchantment> BALROG_MARK = ENCHANTS.register("balrogs_mark", () -> new EnchantmentBalrogMark(Enchantment.Rarity.RARE, EquipmentSlot.FEET));
    public static final RegistryObject<Enchantment> ELVEN_LONGEVITY = ENCHANTS.register("elven_longevity", () -> new EnchantmentElvenLongevity(Enchantment.Rarity.RARE, EquipmentSlot.CHEST));
    public static final RegistryObject<Enchantment> GONDOR_RESOLVE = ENCHANTS.register("gondor_resolve", () -> new EnchantmentGondorResolve(Enchantment.Rarity.RARE, EquipmentSlot.LEGS));
    public static final RegistryObject<Enchantment> DWARF_ENDURANCE = ENCHANTS.register("dwarven_endurance", () -> new EnchantmentDwarvenEndurance(Enchantment.Rarity.RARE, EquipmentSlot.HEAD));
    public static final RegistryObject<Enchantment> HOBBIT_PLOW = ENCHANTS.register("hobbit_plow", () -> new EnchantmentHobbitPlow(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));
    public static final RegistryObject<Enchantment> HOBBIT_HARVEST = ENCHANTS.register("hobbit_harvest", () -> new EnchantmentHobbitHarvest(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));

    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Enchantments";
    }
}
