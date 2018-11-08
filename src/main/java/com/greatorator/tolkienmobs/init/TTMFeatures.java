package com.greatorator.tolkienmobs.init;

import com.brandon3055.brandonscore.blocks.BlockBCore;
import com.brandon3055.brandonscore.blocks.ItemBlockBCore;
import com.brandon3055.brandonscore.items.ItemBCore;
import com.brandon3055.brandonscore.registry.Feature;
import com.brandon3055.brandonscore.registry.IModFeatures;
import com.brandon3055.brandonscore.registry.ModFeature;
import com.brandon3055.brandonscore.registry.ModFeatures;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.block.*;
import com.greatorator.tolkienmobs.item.tools.ToolAxe;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;

/**
 * Created by brandon3055 on 31/10/18.
 */
@GameRegistry.ObjectHolder(TolkienMobs.MODID)
@ModFeatures(modid = TolkienMobs.MODID) //This is what allows Brandon's Core to find this class and load all of its features
public class TTMFeatures implements IModFeatures {

    private CreativeTabs[] tabs = new CreativeTabs[]{TolkienMobs.tabToolsArmor, TolkienMobs.tabWorldMats, TolkienMobs.tabMobsSpawn};

    @Nullable
    @Override
    public CreativeTabs getCreativeTab(Feature feature) {
        return feature.creativeTab() == -1 ? null : tabs[feature.creativeTab()];
    }

    //Material //I don't like the fact that this exists here but... for now this is fine.
    public static Item.ToolMaterial TOOL_MITHRIL = EnumHelper.addToolMaterial("tool_mithril", 2, 180, 5.0F, 1.5F, 5);
    public static ItemArmor.ArmorMaterial ARMOR_MITHRIL = EnumHelper.addArmorMaterial("armour_mithril", TolkienMobs.MODID + ":mithril", 20, new int[]{6, 7, 7, 6}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F);
    public static Item.ToolMaterial TOOL_MORGULIRON = EnumHelper.addToolMaterial("tool_morguliron", 2, 180, 5.0F, 1.5F, 5);
    public static ItemArmor.ArmorMaterial ARMOR_MORGULIRON = EnumHelper.addArmorMaterial("armour_morguliron", TolkienMobs.MODID + ":morguliron", 20, new int[]{6, 7, 7, 6}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F);

    //region Simple Items
    @ModFeature(name = "ingot_mithril", stateOverride = "simple_items#type=ingot_mithril", cTab = 1)
    public static Item INGOT_MITHRIL = new ItemBCore();

    @ModFeature(name = "ingot_morguliron", stateOverride = "simple_items#type=ingot_morguliron", cTab = 1)
    public static Item INGOT_MORGULIRON = new ItemBCore();

    @ModFeature(name = "nugget_mithril", stateOverride = "simple_items#type=nugget_mithril", cTab = 1)
    public static Item NUGGET_MITHRIL = new ItemBCore();

    @ModFeature(name = "nugget_morguliron", stateOverride = "simple_items#type=nugget_morguliron", cTab = 1)
    public static Item NUGGET_MORGULIRON = new ItemBCore();

    @ModFeature(name = "dust_mithril", stateOverride = "simple_items#type=dust_mithril", cTab = 1)
    public static Item DUST_MITHRIL = new ItemBCore();

    @ModFeature(name = "dust_morguliron", stateOverride = "simple_items#type=dust_morguliron", cTab = 1)
    public static Item DUST_MORGULIRON = new ItemBCore();

    //endregion

    //region Tools
    @ModFeature(name = "axe_mithril", stateOverride = "tools#type=axe_mithril")
    public static Item AXE_MITHRIL = new ToolAxe(TOOL_MITHRIL, TOOL_MITHRIL.getAttackDamage(), -3.0F);

    @ModFeature(name = "hoe_mithril", stateOverride = "tools#type=hoe_mithril")
    public static Item HOE_MITHRIL = new ItemHoe(TOOL_MITHRIL);

    @ModFeature(name = "pickaxe_mithril", stateOverride = "tools#type=pickaxe_mithril")
    public static Item PICKAXE_MITHRIL = new ItemPickaxe(TOOL_MITHRIL);

    @ModFeature(name = "shovel_mithril", stateOverride = "tools#type=shovel_mithril")
    public static Item SHOVEL_MITHRIL = new ItemSpade(TOOL_MITHRIL);

    @ModFeature(name = "sword_mithril", stateOverride = "tools#type=sword_mithril")
    public static Item SWORD_MITHRIL = new ItemSword(TOOL_MITHRIL);

    @ModFeature(name = "axe_morguliron", stateOverride = "tools#type=axe_morguliron")
    public static Item AXE_MORGULIRON = new ToolAxe(TOOL_MORGULIRON, TOOL_MORGULIRON.getAttackDamage(), -3.0F);

    @ModFeature(name = "hoe_morguliron", stateOverride = "tools#type=hoe_morguliron")
    public static Item HOE_MORGULIRON = new ItemHoe(TOOL_MORGULIRON);

    @ModFeature(name = "pickaxe_morguliron", stateOverride = "tools#type=pickaxe_morguliron")
    public static Item PICKAXE_MORGULIRON = new ItemPickaxe(TOOL_MORGULIRON);

    @ModFeature(name = "shovel_morguliron", stateOverride = "tools#type=shovel_morguliron")
    public static Item SHOVEL_MORGULIRON = new ItemSpade(TOOL_MORGULIRON);

    @ModFeature(name = "sword_morguliron", stateOverride = "tools#type=sword_morguliron")
    public static Item SWORD_MORGULIRON = new ItemSword(TOOL_MORGULIRON);
    //endregion

    //region Armor
    @ModFeature(name = "helmet_mithril", stateOverride = "armor#type=helmet_mithril")
    public static Item HELMET_MITHRIL = new ItemArmor(ARMOR_MITHRIL, 1, EntityEquipmentSlot.HEAD);

    @ModFeature(name = "chestplate_mithril", stateOverride = "armor#type=chestplate_mithril")
    public static Item CHESTPLATE_MITHRIL = new ItemArmor(ARMOR_MITHRIL, 1, EntityEquipmentSlot.CHEST);

    @ModFeature(name = "leggings_mithril", stateOverride = "armor#type=leggings_mithril")
    public static Item LEGGINGS_MITHRIL = new ItemArmor(ARMOR_MITHRIL, 2, EntityEquipmentSlot.LEGS);

    @ModFeature(name = "boots_mithril", stateOverride = "armor#type=boots_mithril")
    public static Item BOOTS_MITHRIL = new ItemArmor(ARMOR_MITHRIL, 1, EntityEquipmentSlot.FEET);

    @ModFeature(name = "helmet_morguliron", stateOverride = "armor#type=helmet_morguliron")
    public static Item HELMET_MORGULIRON = new ItemArmor(ARMOR_MORGULIRON, 1, EntityEquipmentSlot.HEAD);

    @ModFeature(name = "chestplate_morguliron", stateOverride = "armor#type=chestplate_morguliron")
    public static Item CHESTPLATE_MORGULIRON = new ItemArmor(ARMOR_MORGULIRON, 1, EntityEquipmentSlot.CHEST);

    @ModFeature(name = "leggings_morguliron", stateOverride = "armor#type=leggings_morguliron")
    public static Item LEGGINGS_MORGULIRON = new ItemArmor(ARMOR_MORGULIRON, 2, EntityEquipmentSlot.LEGS);

    @ModFeature(name = "boots_morguliron", stateOverride = "armor#type=boots_morguliron")
    public static Item BOOTS_MORGULIRON = new ItemArmor(ARMOR_MORGULIRON, 1, EntityEquipmentSlot.FEET);
    //endregion

    //region Simple Blocks

    @ModFeature(name = "block_mithril", cTab = 1)
    public static final Block BLOCK_MITHRIL = new BlockBCore(Material.IRON).setHardness(1.5F).setResistance(10F);

    @ModFeature(name = "block_morguliron", cTab = 1)
    public static final Block BLOCK_MORGULIRON = new BlockBCore(Material.IRON).setHardness(1.5F).setResistance(10F);

    @ModFeature(name = "ore", variantMap = {"0:type=mithril", "1:type=morguliron", "2:type=nether_mithril", "3:type=nether_morguliron", "4:type=ender_mithril", "5:type=ender_morguliron"}, itemBlock = ItemBlockBCore.class, cTab = 1)
    public static final Block ORE = new BlockOres();

    @ModFeature(name = "planks", variantMap = {"0:variant=mallorn", "1:variant=mirkwood"}, itemBlock = ItemBlockBCore.class, cTab = 1)
    public static final Block PLANKS = new BlockPlank();

    @ModFeature(name = "log", variantMap = {"0:axis=y,variant=mallorn", "1:axis=y,variant=mirkwood"}, itemBlock = ItemBlockBCore.class, cTab = 1)
    public static final Block LOGS = new BlockLogs();

    //@ModFeature(name = "slab", variantMap = {"0:variant=mallorn", "1:variant=mirkwood"}, itemBlock = ItemBlockBCore.class, cTab = 1)
    //public static final Block SLAB = new BlockSlabs();

    //                                             //TODO add a way to avoid this mess in 1.13...
    @ModFeature(name = "leaves", variantMap = {"0:check_decay=false,decayable=false,variant=mallorn", "1:check_decay=false,decayable=false,variant=mirkwood"}, itemBlock = ItemBlockBCore.class, cTab = 1)
    public static final Block LEAVES = new BlockLeaf();

    @ModFeature(name = "sapling", variantMap = {"0:stage=0,variant=mallorn", "1:stage=0,variant=mirkwood"}, itemBlock = ItemBlockBCore.class, cTab = 1)
    public static final Block SAPLINGS = new BlockSaplings();

    @ModFeature(name = "flower", variantMap = {"0:variant=simbelmyne", "1:variant=mirkwood"}, itemBlock = ItemBlockBCore.class, cTab = 1)
    public static final Block FLOWERS = new BlockFlowers();

    //endregion

    //region Tile Entities
    //    //Maybe latter
    //    //endregion

    //region Mobs

    //endregion
}
