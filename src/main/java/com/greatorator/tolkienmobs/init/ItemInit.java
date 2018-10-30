package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.objects.armor.ArmorBase;
import com.greatorator.tolkienmobs.objects.items.ItemBase;
import com.greatorator.tolkienmobs.objects.tools.*;
import com.greatorator.tolkienmobs.util.Reference;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

public class ItemInit {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    //Material
    public static final Item.ToolMaterial TOOL_MITHRIL = EnumHelper.addToolMaterial("tool_mithril", 2, 180, 5.0F, 1.5F, 5);
    public static final ItemArmor.ArmorMaterial ARMOR_MITHRIL = EnumHelper.addArmorMaterial("armour_mithril", Reference.MODID + ":mithril", 20, new int[]{6, 7, 7, 6}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F);
    public static final Item.ToolMaterial TOOL_MORGULIRON = EnumHelper.addToolMaterial("tool_morguliron", 2, 180, 5.0F, 1.5F, 5);
    public static final ItemArmor.ArmorMaterial ARMOR_MORGULIRON = EnumHelper.addArmorMaterial("armour_morguliron", Reference.MODID + ":morguliron", 20, new int[]{6, 7, 7, 6}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F);

    //Items
    public static final Item INGOT_MITHRIL = new ItemBase("ingot_mithril");
    public static final Item NUGGET_MITHRIL = new ItemBase("nugget_mithril");
    public static final Item DUST_MITHRIL = new ItemBase("dust_mithril");
    public static final Item INGOT_MORGULIRON = new ItemBase("ingot_morguliron");
    public static final Item NUGGET_MORGULIRON = new ItemBase("nugget_morguliron");
    public static final Item DUST_MORGULIRON = new ItemBase("dust_morguliron");

    //Tools
    public static final Item AXE_MITHRIL = new ToolAxe("axe_mithril", TOOL_MITHRIL);
    public static final Item HOE_MITHRIL = new ToolHoe("hoe_mithril", TOOL_MITHRIL);
    public static final Item PICKAXE_MITHRIL = new ToolPickaxe("pickaxe_mithril", TOOL_MITHRIL);
    public static final Item SHOVEL_MITHRIL = new ToolShovel("shovel_mithril", TOOL_MITHRIL);
    public static final Item SWORD_MITHRIL = new ToolSword("sword_mithril", TOOL_MITHRIL);
    public static final Item AXE_MORGULIRON = new ToolAxe("axe_morguliron", TOOL_MORGULIRON);
    public static final Item HOE_MORGULIRON = new ToolHoe("hoe_morguliron", TOOL_MORGULIRON);
    public static final Item PICKAXE_MORGULIRON = new ToolPickaxe("pickaxe_morguliron", TOOL_MORGULIRON);
    public static final Item SHOVEL_MORGULIRON = new ToolShovel("shovel_morguliron", TOOL_MORGULIRON);
    public static final Item SWORD_MORGULIRON = new ToolSword("sword_morguliron", TOOL_MORGULIRON);

    //Armor
    public static final Item HELMET_MITHRIL = new ArmorBase("helmet_mithril", ARMOR_MITHRIL, 1, EntityEquipmentSlot.HEAD);
    public static final Item CHESTPLATE_MITHRIL = new ArmorBase("chestplate_mithril", ARMOR_MITHRIL, 1, EntityEquipmentSlot.CHEST);
    public static final Item LEGGINGS_MITHRIL = new ArmorBase("leggings_mithril", ARMOR_MITHRIL, 2, EntityEquipmentSlot.LEGS);
    public static final Item BOOTS_MITHRIL = new ArmorBase("boots_mithril", ARMOR_MITHRIL, 1, EntityEquipmentSlot.FEET);
    public static final Item HELMET_MORGULIRON = new ArmorBase("helmet_morguliron", ARMOR_MORGULIRON, 1, EntityEquipmentSlot.HEAD);
    public static final Item CHESTPLATE_MORGULIRON = new ArmorBase("chestplate_morguliron", ARMOR_MORGULIRON, 1, EntityEquipmentSlot.CHEST);
    public static final Item LEGGINGS_MORGULIRON = new ArmorBase("leggings_morguliron", ARMOR_MORGULIRON, 2, EntityEquipmentSlot.LEGS);
    public static final Item BOOTS_MORGULIRON = new ArmorBase("boots_morguliron", ARMOR_MORGULIRON, 1, EntityEquipmentSlot.FEET);
}
