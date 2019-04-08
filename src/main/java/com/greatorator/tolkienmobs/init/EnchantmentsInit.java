package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.enchantments.*;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@Mod.EventBusSubscriber(modid= TolkienMobs.MODID)
public class EnchantmentsInit
{
    public static final Enchantment BALROG_MARK = new EnchantmentBalrogMark(Enchantment.Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_FEET, EntityEquipmentSlot.FEET);
    public static final Enchantment GONDOR_RESOLVE = new EnchantmentGondorianResolve(Enchantment.Rarity.RARE, EnumEnchantmentType.ARMOR_FEET, EntityEquipmentSlot.FEET);
    public static final Enchantment HOBBIT_HARVEST = new EnchantmentHobbitHarvest(Enchantment.Rarity.COMMON, EnumEnchantmentType.DIGGER);
    public static final Enchantment DWARF_ENDURANCE = new EnchantmentDwarvenEndurance(Enchantment.Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_HEAD, EntityEquipmentSlot.HEAD);
    public static final Enchantment ELVEN_LONGEVITY = new EnchantmentElvenLongevity(Enchantment.Rarity.RARE, EnumEnchantmentType.ARMOR_CHEST, EntityEquipmentSlot.CHEST);

    public static void registerEnchants() {
        LogHelperTTM.info("Beseeching Eru Iluvatar for help...");
        initEnchantments(BALROG_MARK,"tolkienmobs.balrogs_mark",TolkienMobs.MODID + ":balrogs_mark");
        initEnchantments(GONDOR_RESOLVE,"tolkienmobs.gondor_resolve",TolkienMobs.MODID + ":gondor_resolve");
        initEnchantments(HOBBIT_HARVEST,"tolkienmobs.hobbit_harvest",TolkienMobs.MODID + ":hobbit_harvest");
        initEnchantments(DWARF_ENDURANCE,"tolkienmobs.dwarven_endurance",TolkienMobs.MODID + ":dwarven_endurance");
        initEnchantments(ELVEN_LONGEVITY,"tolkienmobs.elven_longevity",TolkienMobs.MODID + ":elven_longevity");
        LogHelperTTM.info("Call to Eru Iluvatar answered!");
    }

    public static Enchantment initEnchantments(Enchantment enchant, String name, String regname) {
        enchant.setName(name);
        enchant.setRegistryName(regname);
        ForgeRegistries.ENCHANTMENTS.register(enchant);
        return enchant;
    }
}