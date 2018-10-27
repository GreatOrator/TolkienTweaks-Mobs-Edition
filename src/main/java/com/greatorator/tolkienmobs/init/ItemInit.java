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
    public static final Item.ToolMaterial TOOL_DAEMONIUM = EnumHelper.addToolMaterial("tool_daemonium", 2, 180, 5.0F, 1.5F, 5);
    public static final ItemArmor.ArmorMaterial ARMOR_DAEMONIUM = EnumHelper.addArmorMaterial("armour_daemonium", Reference.MODID + ":daemonium", 20, new int[]{6, 7, 7, 6}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F);

    //Items
    public static final Item INGOT_DAEMONIUM = new ItemBase("ingot_daemonium");
    public static final Item NUGGET_DAEMONIUM = new ItemBase("nugget_daemonium");
    public static final Item DUST_DAEMONIUM = new ItemBase("dust_daemonium");

    //Tools
    public static final Item AXE_DAEMONIUM = new ToolAxe("axe_daemonium", TOOL_DAEMONIUM);
    public static final Item HOE_DAEMONIUM = new ToolHoe("hoe_daemonium", TOOL_DAEMONIUM);
    public static final Item PICKAXE_DAEMONIUM = new ToolPickaxe("pickaxe_daemonium", TOOL_DAEMONIUM);
    public static final Item SHOVEL_DAEMONIUM = new ToolShovel("shovel_daemonium", TOOL_DAEMONIUM);
    public static final Item SWORD_DAEMONIUM = new ToolSword("sword_daemonium", TOOL_DAEMONIUM);

    //Armor
    public static final Item HELMET_DAEMONIUM = new ArmorBase("helmet_daemonium", ARMOR_DAEMONIUM, 1, EntityEquipmentSlot.HEAD);
    public static final Item CHESTPLATE_DAEMONIUM = new ArmorBase("chestplate_daemonium", ARMOR_DAEMONIUM, 1, EntityEquipmentSlot.CHEST);
    public static final Item LEGGINGS_DAEMONIUM = new ArmorBase("leggings_daemonium", ARMOR_DAEMONIUM, 2, EntityEquipmentSlot.LEGS);
    public static final Item BOOTS_DAEMONIUM = new ArmorBase("boots_daemonium", ARMOR_DAEMONIUM, 1, EntityEquipmentSlot.FEET);
}
