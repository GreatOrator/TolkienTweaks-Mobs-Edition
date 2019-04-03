package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.enchantments.EnchantmentBalrogMark;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@Mod.EventBusSubscriber(modid= TolkienMobs.MODID)
public class EnchantmentsInit
{
    public static final Enchantment BALROG_MARK = new EnchantmentBalrogMark(Enchantment.Rarity.RARE, EnumEnchantmentType.ARMOR_FEET, EntityEquipmentSlot.FEET);

    public static void registerEnchants() {
        LogHelperTTM.info("Beseeching Eru Iluvatar for help...");
        initEnchantments(BALROG_MARK,"tolkienmobs.balrogs_mark",TolkienMobs.MODID + ":balrogs_mark");
        LogHelperTTM.info("Call to Eru Iluvatar answered!");
    }

    public static Enchantment initEnchantments(Enchantment enchant, String name, String regname) {
        enchant.setName(name);
        enchant.setRegistryName(regname);
        ForgeRegistries.ENCHANTMENTS.register(enchant);
        return enchant;
    }
}