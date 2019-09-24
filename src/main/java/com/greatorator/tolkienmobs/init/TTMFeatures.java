package com.greatorator.tolkienmobs.init;

import com.brandon3055.brandonscore.blocks.BlockBCore;
import com.brandon3055.brandonscore.blocks.ItemBlockBCore;
import com.brandon3055.brandonscore.blocks.NoItemBlock;
import com.brandon3055.brandonscore.items.ItemBCore;
import com.brandon3055.brandonscore.registry.Feature;
import com.brandon3055.brandonscore.registry.IModFeatures;
import com.brandon3055.brandonscore.registry.ModFeature;
import com.brandon3055.brandonscore.registry.ModFeatures;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.block.*;
import com.greatorator.tolkienmobs.block.itemblock.BlockTMFireplace;
import com.greatorator.tolkienmobs.block.itemblock.BlockTMHallowed;
import com.greatorator.tolkienmobs.block.itemblock.ItemBlockSlabs;
import com.greatorator.tolkienmobs.handler.*;
import com.greatorator.tolkienmobs.item.armor.ArmorMithril;
import com.greatorator.tolkienmobs.item.armor.ArmorMorgulIron;
import com.greatorator.tolkienmobs.item.magical.ItemTrinketAmulet;
import com.greatorator.tolkienmobs.item.magical.ItemTrinketBelt;
import com.greatorator.tolkienmobs.item.magical.ItemTrinketCharm;
import com.greatorator.tolkienmobs.item.magical.ItemTrinketRing;
import com.greatorator.tolkienmobs.item.tools.ToolAxe;
import com.greatorator.tolkienmobs.item.tools.ToolSword;
import com.greatorator.tolkienmobs.tile.TileSign;
import com.greatorator.tolkienmobs.tile.TileTMFireplace;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;

/**
 * Created by brandon3055 on 31/10/18.
 */
@GameRegistry.ObjectHolder(TolkienMobs.MODID)
@ModFeatures(modid = TolkienMobs.MODID) //This is what allows Brandon's Core to find this class and load all of its features
public class TTMFeatures implements IModFeatures {

    private CreativeTabs[] tabs = new CreativeTabs[]{TolkienMobs.tabToolsArmor, TolkienMobs.tabWorldMats, TolkienMobs.tabMobsSpawn, TolkienMobs.tabFoodItems, TolkienMobs.tabQuestItems, TolkienMobs.tabSignItems};

    @Nullable
    @Override
    public CreativeTabs getCreativeTab(Feature feature) {
        return feature.creativeTab() == -1 ? null : tabs[feature.creativeTab()];
    }

    /* Material */ //I don't like the fact that this exists here but... for now this is fine.
    public static Item.ToolMaterial TOOL_MITHRIL = EnumHelper.addToolMaterial("tool_mithril", 5, 1800, 12.0F, 5.5F, 45);
    public static ItemArmor.ArmorMaterial ARMOR_MITHRIL = EnumHelper.addArmorMaterial("armour_mithril", TolkienMobs.MODID + ":mithril", 50, new int[]{6, 12, 16, 6}, 35, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
    public static Item.ToolMaterial TOOL_MORGULIRON = EnumHelper.addToolMaterial("tool_morguliron", 3, 1600, 9.0F, 3.5F, 5);
    public static ItemArmor.ArmorMaterial ARMOR_MORGULIRON = EnumHelper.addArmorMaterial("armour_morguliron", TolkienMobs.MODID + ":morguliron", 35, new int[]{6, 10, 10, 6}, 5, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F);

    /* Simple Items */
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
    @ModFeature(name = "gem_ammolite", stateOverride = "simple_items#type=gem_ammolite", cTab = 1)
    public static Item GEM_AMMOLITE = new TTMLore(16).setEffectOverride(true).setItemHasUse(false);
    /* End Region */

    /* Quest Items */
    @ModFeature(name = "item_beryl", stateOverride = "simple_items#type=item_beryl", cTab = 4)
    public static Item ITEM_BERYL = new TTMLore(1).setEffectOverride(true).setItemHasUse(false);
    @ModFeature(name = "item_watcherheart", stateOverride = "simple_items#type=item_watcherheart", cTab = 4)
    public static Item ITEM_WATCHERHEART = new TTMLore(1).setEffectOverride(true).setItemHasUse(false);
    @ModFeature(name = "item_watcherheart_cracked", stateOverride = "simple_items#type=item_watcherheart_cracked", cTab = 4)
    public static Item ITEM_WATCHERHEART_CRACKED = new TTMLore(1).setItemHasUse(false);
    @ModFeature(name = "item_darksigil", stateOverride = "simple_items#type=item_darksigil", cTab = 4)
    public static Item ITEM_DARKSIGIL = new TTMLore(64).setItemHasUse(false);
    @ModFeature(name = "item_fortressmap", stateOverride = "simple_items#type=item_fortressmap", cTab = 4)
    public static Item ITEM_FORTRESSMAP = new TTMLore(1).setItemHasUse(false);
    @ModFeature(name = "item_keystone", stateOverride = "simple_items#type=item_keystone", cTab = 4)
    public static Item ITEM_KEYSTONE = new TTMLore(1).setItemHasUse(false);
    @ModFeature(name = "item_darksaddle", stateOverride = "simple_items#type=item_darksaddle", cTab = 4)
    public static Item ITEM_DARKSADDLE = new TTMLore(1).setItemHasUse(false);
    @ModFeature(name = "item_cavecomplete", stateOverride = "simple_items#type=item_cavecomplete", cTab = 4)
    public static Item ITEM_CAVECOMPLETE = new TTMLore(3).setItemHasUse(false);
    @ModFeature(name = "item_watchercomplete", stateOverride = "simple_items#type=item_watchercomplete", cTab = 4)
    public static Item ITEM_WATCHERCOMPLETE = new TTMLore(3).setItemHasUse(false);
    @ModFeature(name = "item_artifact", stateOverride = "simple_items#type=item_artifact", cTab = 4)
    public static Item ITEM_ARTIFACT = new TTMLore(1).setEffectOverride(true).setItemHasUse(false);
    @ModFeature(name = "item_blankpaper", stateOverride = "simple_items#type=item_blankpaper", cTab = 4)
    public static Item ITEM_BLANKPAPER = new TTMLore(1).setItemHasUse(false);
    @ModFeature(name = "item_fancyarmor", stateOverride = "simple_items#type=item_fancyarmor", cTab = 4)
    public static Item ITEM_FANCYARMOR = new TTMLore(1).setEffectOverride(true).setItemHasUse(false);
    @ModFeature(name = "item_fancycloth", stateOverride = "simple_items#type=item_fancycloth", cTab = 4)
    public static Item ITEM_FANCYCLOTH = new TTMLore(3).setItemHasUse(false);
    @ModFeature(name = "item_fancyhammer", stateOverride = "simple_items#type=item_fancyhammer", cTab = 4)
    public static Item ITEM_FANCYHAMMER = new TTMLore(1).setEffectOverride(true).setItemHasUse(false);
    @ModFeature(name = "item_fancyhelm", stateOverride = "simple_items#type=item_fancyhelm", cTab = 4)
    public static Item ITEM_FANCYHELM = new TTMLore(1).setItemHasUse(false);
    @ModFeature(name = "item_fancykey", stateOverride = "simple_items#type=item_fancykey", cTab = 4)
    public static Item ITEM_FANCYKEY = new TTMLore(1).setItemHasUse(false);
    @ModFeature(name = "item_fancypick", stateOverride = "simple_items#type=item_fancypick", cTab = 4)
    public static Item ITEM_FANCYPICK = new TTMLore(1).setItemHasUse(false);
    @ModFeature(name = "item_fancyshield", stateOverride = "simple_items#type=item_fancyshield", cTab = 4)
    public static Item ITEM_FANCYSHIELD = new TTMLore(1).setItemHasUse(false);
    @ModFeature(name = "item_fancyshield2", stateOverride = "simple_items#type=item_fancyshield2", cTab = 4)
    public static Item ITEM_FANCYSHIELD2 = new TTMLore(1).setEffectOverride(true).setItemHasUse(false);
    @ModFeature(name = "item_fancysword", stateOverride = "simple_items#type=item_fancysword", cTab = 4)
    public static Item ITEM_FANCYSWORD = new TTMLore(1).setItemHasUse(false);
    @ModFeature(name = "item_fancysword2", stateOverride = "simple_items#type=item_fancysword2", cTab = 4)
    public static Item ITEM_FANCYSWORD2 = new TTMLore(1).setEffectOverride(true).setItemHasUse(false);
    @ModFeature(name = "item_letter", stateOverride = "simple_items#type=item_letter", cTab = 4)
    public static Item ITEM_LETTER = new TTMLore(1).setItemHasUse(false);
    @ModFeature(name = "item_scroll", stateOverride = "simple_items#type=item_scroll", cTab = 4)
    public static Item ITEM_SCROLL = new TTMLore(1).setItemHasUse(false);
    @ModFeature(name = "item_scroll2", stateOverride = "simple_items#type=item_scroll2", cTab = 4)
    public static Item ITEM_SCROLL2 = new TTMLore(1).setEffectOverride(true).setItemHasUse(false);
    @ModFeature(name = "item_specialflower", stateOverride = "simple_items#type=item_specialflower", cTab = 4)
    public static Item ITEM_SPECIALFLOWER = new TTMLore(12).setEffectOverride(true).setItemHasUse(false);
    @ModFeature(name = "item_storybook", stateOverride = "simple_items#type=item_storybook", cTab = 4)
    public static Item ITEM_STORYBOOK = new TTMLore(12).setItemHasUse(false);
    @ModFeature(name = "item_storybook2", stateOverride = "simple_items#type=item_storybook2", cTab = 4)
    public static Item ITEM_STORYBOOK2 = new TTMLore(1).setEffectOverride(true).setItemHasUse(false);
    @ModFeature(name = "item_wornarmor", stateOverride = "simple_items#type=item_wornarmor", cTab = 4)
    public static Item ITEM_WORNARMOR = new TTMLore(1).setItemHasUse(false);
    @ModFeature(name = "item_wornhelm", stateOverride = "simple_items#type=item_wornhelm", cTab = 4)
    public static Item ITEM_WORNHELM = new TTMLore(1).setItemHasUse(false);
    @ModFeature(name = "item_wornkey", stateOverride = "simple_items#type=item_wornkey", cTab = 4)
    public static Item ITEM_WORNKEY = new TTMLore(1).setItemHasUse(false);
    @ModFeature(name = "item_wornpick", stateOverride = "simple_items#type=item_wornpick", cTab = 4)
    public static Item ITEM_WORNPICK = new TTMLore(1).setItemHasUse(false);
    @ModFeature(name = "item_wornshield", stateOverride = "simple_items#type=item_wornshield", cTab = 4)
    public static Item ITEM_WORNSHIELD = new TTMLore(1).setItemHasUse(false);
    @ModFeature(name = "item_wornshield2", stateOverride = "simple_items#type=item_wornshield2", cTab = 4)
    public static Item ITEM_WORNSHIELD2 = new TTMLore(1).setItemHasUse(false);
    @ModFeature(name = "item_wornsword", stateOverride = "simple_items#type=item_wornsword", cTab = 4)
    public static Item ITEM_WORNSWORD = new TTMLore(1).setItemHasUse(false);
    @ModFeature(name = "item_wovenbasket", stateOverride = "simple_items#type=item_wovenbasket", cTab = 4)
    public static Item ITEM_WOVENBASKET = new TTMLore(3).setItemHasUse(false);
    @ModFeature(name = "item_writtenpaper", stateOverride = "simple_items#type=item_writtenpaper", cTab = 4)
    public static Item ITEM_WRITTENPAPER = new TTMLore(1).setEffectOverride(true).setItemHasUse(false);
    @ModFeature(name = "item_coin1", stateOverride = "simple_items#type=item_coin1", cTab = 4)
    public static Item ITEM_FACTIONCOIN = new TTMLore(64).setItemHasUse(false);
    @ModFeature(name = "item_coin2", stateOverride = "simple_items#type=item_coin2", cTab = 4)
    public static Item ITEM_FACTIONTOKEN = new TTMLore(64).setItemHasUse(false);
    @ModFeature(name = "item_coin_bronze", stateOverride = "simple_items#type=item_coin_bronze", cTab = 4)
    public static Item ITEM_COIN_BRONZE = new TTMLore(64).setItemHasUse(false);
    @ModFeature(name = "item_coin_silver", stateOverride = "simple_items#type=item_coin_silver", cTab = 4)
    public static Item ITEM_COIN_SILVER = new TTMLore(64).setItemHasUse(false);
    @ModFeature(name = "item_coin_gold", stateOverride = "simple_items#type=item_coin_gold", cTab = 4)
    public static Item ITEM_COIN_GOLD = new TTMLore(64).setItemHasUse(false);
    @ModFeature(name = "item_coin_mithril", stateOverride = "simple_items#type=item_coin_mithril", cTab = 4)
    public static Item ITEM_COIN_MITHRIL = new TTMLore(64).setItemHasUse(false);
    @ModFeature(name = "item_pungentherb", stateOverride = "simple_items#type=item_pungentherb", cTab = 4)
    public static Item ITEM_PUNGENTHERB = new TTMLore(3).setEffectOverride(true).setItemHasUse(false);
    @ModFeature(name = "item_lockpick", stateOverride = "simple_items#type=item_lockpick", cTab = 4)
    public static Item ITEM_LOCKPICK = new TTMLore(16).setItemHasUse(false);
    @ModFeature(name = "item_brokensword", stateOverride = "simple_items#type=item_brokensword", cTab = 4)
    public static Item ITEM_BROKENSWORD = new TTMLore(1).setItemHasUse(false);
    @ModFeature(name = "item_reforgedsword", stateOverride = "simple_items#type=item_reforgedsword", cTab = 4)
    public static Item ITEM_REFORGEDSWORD = new TTMLore(1).setEffectOverride(true).setItemHasUse(false);
    @ModFeature(name = "item_magic_cloth", stateOverride = "simple_items#type=item_magic_cloth", cTab = 4)
    public static Item ITEM_MAGIC_CLOTH = new TTMLore(1).setEffectOverride(true).setItemHasUse(false);
    @ModFeature(name = "item_keyfragment", stateOverride = "simple_items#type=item_keyfragment", cTab = 4)
    public static Item ITEM_KEYFRAGMENT = new TTMLore(2).setItemHasUse(false);
    @ModFeature(name = "item_oilykey", stateOverride = "simple_items#type=item_oilykey", cTab = 4)
    public static Item ITEM_OILYKEY = new TTMLore(1).setItemHasUse(false);
    @ModFeature(name = "item_mithrilnugget", stateOverride = "simple_items#type=item_mithrilnugget", cTab = 4)
    public static Item ITEM_MITHRILNUGGET = new TTMLore(1).setItemHasUse(false);
    /* End Region */

    /* Tools */
    @ModFeature(name = "axe_mithril", stateOverride = "tools#type=axe_mithril")
    public static Item AXE_MITHRIL = new ToolAxe(TOOL_MITHRIL, TOOL_MITHRIL.getAttackDamage(), -1.5F);
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
    @ModFeature(name = "whip_fire", stateOverride = "tools#type=whip_fire")
    public static Item WHIP_FIRE = new ToolSword(TOOL_MITHRIL,"whip_fire.obj","whip_fire.png").setEffectOverride(true);
    @ModFeature(name = "club_wooden", stateOverride = "tools#type=club_wooden")
    public static Item CLUB_WOODEN = new ToolSword(TOOL_MITHRIL,"club_wooden.obj","club_wooden.png").setEffectOverride(true);
    @ModFeature(name = "sword_witchking", stateOverride = "tools#type=sword_witchking")
    public static Item SWORD_WITCHKING = new TTMSword(TOOL_MITHRIL).setEffectOverride(true);
    @ModFeature(name = "trinket_ring", stateOverride = "tools#type=trinket_ring")
    public static Item TRINKET_RING = new ItemTrinketRing();
    @ModFeature(name = "trinket_amulet", stateOverride = "tools#type=trinket_amulet")
    public static Item TRINKET_AMULET = new ItemTrinketAmulet();
    @ModFeature(name = "trinket_belt", stateOverride = "tools#type=trinket_belt")
    public static Item TRINKET_BELT = new ItemTrinketBelt();
    @ModFeature(name = "trinket_charm", stateOverride = "tools#type=trinket_charm")
    public static Item TRINKET_CHARM = new ItemTrinketCharm();
    /* End Region */

    /* Armor */
    @ModFeature(name = "helmet_mithril", stateOverride = "armor#type=helmet_mithril")
    public static Item HELMET_MITHRIL = new ArmorMithril(ARMOR_MITHRIL, 1, EntityEquipmentSlot.HEAD);
    @ModFeature(name = "chestplate_mithril", stateOverride = "armor#type=chestplate_mithril")
    public static Item CHESTPLATE_MITHRIL = new ArmorMithril(ARMOR_MITHRIL, 1, EntityEquipmentSlot.CHEST);
    @ModFeature(name = "leggings_mithril", stateOverride = "armor#type=leggings_mithril")
    public static Item LEGGINGS_MITHRIL = new ArmorMithril(ARMOR_MITHRIL, 2, EntityEquipmentSlot.LEGS);
    @ModFeature(name = "boots_mithril", stateOverride = "armor#type=boots_mithril")
    public static Item BOOTS_MITHRIL = new ArmorMithril(ARMOR_MITHRIL, 1, EntityEquipmentSlot.FEET);
    @ModFeature(name = "helmet_morguliron", stateOverride = "armor#type=helmet_morguliron")
    public static Item HELMET_MORGULIRON = new ArmorMorgulIron(ARMOR_MORGULIRON, 1, EntityEquipmentSlot.HEAD);
    @ModFeature(name = "chestplate_morguliron", stateOverride = "armor#type=chestplate_morguliron")
    public static Item CHESTPLATE_MORGULIRON = new ArmorMorgulIron(ARMOR_MORGULIRON, 1, EntityEquipmentSlot.CHEST);
    @ModFeature(name = "leggings_morguliron", stateOverride = "armor#type=leggings_morguliron")
    public static Item LEGGINGS_MORGULIRON = new ArmorMorgulIron(ARMOR_MORGULIRON, 2, EntityEquipmentSlot.LEGS);
    @ModFeature(name = "boots_morguliron", stateOverride = "armor#type=boots_morguliron")
    public static Item BOOTS_MORGULIRON = new ArmorMorgulIron(ARMOR_MORGULIRON, 1, EntityEquipmentSlot.FEET);
    /* End Region */

    /* Simple Blocks */
    @ModFeature(name = "block_mithril", cTab = 1)
    public static final Block BLOCK_MITHRIL = new BlockBCore(Material.IRON).setHardness(1.5F).setResistance(10F);
    @ModFeature(name = "block_morguliron", cTab = 1)
    public static final Block BLOCK_MORGULIRON = new BlockBCore(Material.IRON).setHardness(1.5F).setResistance(10F);
    @ModFeature(name = "ore", variantMap = {"0:type=mithril", "1:type=morguliron", "2:type=nether_mithril", "3:type=nether_morguliron", "4:type=ender_mithril", "5:type=ender_morguliron", "6:type=ammolite", "7:type=nether_ammolite", "8:type=ender_ammolite"}, itemBlock = ItemBlockBCore.class, cTab = 1)
    public static final Block ORE = new BlockOres();
    @ModFeature(name = "planks", variantMap = {"0:variant=mallorn", "1:variant=mirkwood", "2:variant=culumalda", "3:variant=lebethron"}, itemBlock = ItemBlockBCore.class, cTab = 1)
    public static final Block PLANKS = new BlockPlank();
    @ModFeature(name = "log", variantMap = {"0:axis=y,variant=mallorn", "1:axis=y,variant=mirkwood", "2:axis=y,variant=culumalda", "3:axis=y,variant=lebethron"}, itemBlock = ItemBlockBCore.class, cTab = 1)
    public static final Block LOGS = new BlockLogs();
    @ModFeature(name = "slab", variantMap = {"0:half=bottom,variant=mallorn", "1:half=bottom,variant=mirkwood", "2:half=bottom,variant=culumalda", "3:half=bottom,variant=lebethron"}, itemBlock = ItemBlockSlabs.class, cTab = 1)
    public static final BlockSlabs HALF_SLAB = new BlockSlabs();
    @ModFeature(name = "double_slab", itemBlock = NoItemBlock.class)
    public static final BlockSlabs DOUBLE_SLAB = new BlockDoubleSlabs();
    @ModFeature(name = "stairs_mallorn", cTab = 1, stateOverride = "stairs_mallorn#facing=east,half=bottom,shape=straight")
    public static final BlockStairs STAIRS_MALLORN = new BlockTMStairs(PLANKS.getDefaultState().withProperty(BlockPlank.VARIANT, BlockLogs.EnumType.MALLORN));
    @ModFeature(name = "stairs_mirkwood", cTab = 1, stateOverride = "stairs_mirkwood#facing=east,half=bottom,shape=straight")
    public static final BlockStairs STAIRS_MIRKWOOD = new BlockTMStairs(PLANKS.getDefaultState().withProperty(BlockPlank.VARIANT, BlockLogs.EnumType.MIRKWOOD));
    @ModFeature(name = "stairs_culumalda", cTab = 1, stateOverride = "stairs_culumalda#facing=east,half=bottom,shape=straight")
    public static final BlockStairs STAIRS_CULUMALDA = new BlockTMStairs(PLANKS.getDefaultState().withProperty(BlockPlank.VARIANT, BlockLogs.EnumType.CULUMALDA));
    @ModFeature(name = "stairs_lebethron", cTab = 1, stateOverride = "stairs_lebethron#facing=east,half=bottom,shape=straight")
    public static final BlockStairs STAIRS_LEBETHRON = new BlockTMStairs(PLANKS.getDefaultState().withProperty(BlockPlank.VARIANT, BlockLogs.EnumType.LEBETHRON));
    /* End Region */

    /* Plants */                                             //TODO add a way to avoid this mess in 1.13...
    @ModFeature(name = "leaves", variantMap = {"0:check_decay=false,decayable=false,variant=mallorn", "1:check_decay=false,decayable=false,variant=mirkwood", "2:check_decay=false,decayable=false,variant=culumalda", "3:check_decay=false,decayable=false,variant=lebethron"}, itemBlock = ItemBlockBCore.class, cTab = 1)
    public static final Block LEAVES = new BlockLeaf();
    @ModFeature(name = "sapling", variantMap = {"0:stage=0,variant=mallorn", "1:stage=0,variant=mirkwood", "2:stage=0,variant=culumalda", "3:stage=0,variant=lebethron"}, itemBlock = ItemBlockBCore.class, cTab = 1)
    public static final Block SAPLINGS = new BlockSaplings();
    @ModFeature(name = "flower", variantMap = {"0:variant=simbelmyne", "1:variant=mirkwood", "2:variant=alfirin", "3:variant=athelas", "4:variant=niphredil", "5:variant=swamp_milkweed","6:variant=valley_lilly"}, itemBlock = ItemBlockBCore.class, cTab = 1)
    public static final BlockFlowers FLOWERS = new BlockFlowers();
    /* End Region */

    /* Basic Items */
    @ModFeature(name = "feather_crebain", stateOverride = "simple_items#type=feather_crebain", cTab = 4)
    public static Item CREBAIN_FEATHER = new ItemBCore();
    @ModFeature(name = "feather_bird", stateOverride = "simple_items#type=feather_bird", cTab = 4)
    public static Item BIRD_FEATHER = new ItemBCore();
    @ModFeature(name = "leather_mumakil", stateOverride = "simple_items#type=leather_mumakil", cTab = 4)
    public static Item MUMAKIL_LEATHER = new ItemBCore();
    @ModFeature(name = "monster_fur", stateOverride = "simple_items#type=monster_fur", cTab = 4)
    public static Item MONSTER_FUR = new ItemBCore();
    @ModFeature(name = "bottle_fancy", stateOverride = "simple_items#type=bottle_fancy", cTab = 4)
    public static Item BOTTLE_FANCY = new ItemBCore();
    @ModFeature(name = "item_golem_stone", stateOverride = "simple_items#type=item_golem_stone", cTab = 4)
    public static Item GOLEM_STONE = new TTMLore(16).setItemHasUse(false);
    @ModFeature(name = "item_golem_stone_earth", stateOverride = "simple_items#type=item_golem_stone_earth", cTab = 4)
    public static Item GOLEM_STONE_EARTH = new TTMLore(16).setItemHasUse(false);
    @ModFeature(name = "item_golem_stone_air", stateOverride = "simple_items#type=item_golem_stone_air", cTab = 4)
    public static Item GOLEM_STONE_AIR = new TTMLore(16).setItemHasUse(false);
    @ModFeature(name = "item_golem_stone_fire", stateOverride = "simple_items#type=item_golem_stone_fire", cTab = 4)
    public static Item GOLEM_STONE_FIRE = new TTMLore(16).setItemHasUse(false);
    @ModFeature(name = "item_golem_stone_water", stateOverride = "simple_items#type=item_golem_stone_water", cTab = 4)
    public static Item GOLEM_STONE_WATER = new TTMLore(16).setItemHasUse(false);
    @ModFeature(name = "item_golem_stone_summon", stateOverride = "simple_items#type=item_golem_stone_summon", cTab = 4)
    public static Item GOLEM_STONE_SUMMON = new TTMLore(16).setEffectOverride(true).setSpawnInfo(true);
    /* End Region */

    /* Food */
    @ModFeature(name = "food_lembas", stateOverride = "simple_items#type=food_lembas", cTab = 3)
    public static Item LEMBAS = new TTMFood(20, 64, 20, new PotionEffect(MobEffects.ABSORPTION,12000,5), new PotionEffect(MobEffects.REGENERATION,100,5)).setEffectOverride(true);
    @ModFeature(name = "food_honeycake", stateOverride = "simple_items#type=food_honeycake", cTab = 3)
    public static Item HONEY_CAKE = new TTMFood(15, 64, 15, new PotionEffect(MobEffects.ABSORPTION,6000,3), new PotionEffect(MobEffects.REGENERATION,50,5));
    @ModFeature(name = "food_cram", stateOverride = "simple_items#type=food_cram", cTab = 3)
    public static Item CRAM = new TTMFood(10, 64, 10, new PotionEffect(MobEffects.ABSORPTION,3000,1), new PotionEffect(MobEffects.REGENERATION,25,5));
    @ModFeature(name = "potion_entdraught", stateOverride = "simple_items#type=potion_entdraught", cTab = 3)
    public static Item ENT_DRAUGHT = new TTMFood(1, 1, 1, new PotionEffect(PotionInit.ENT_STANCE,6000,2)).setEffectOverride(true).setItemUseAction(true);
    @ModFeature(name = "potion_miruvor", stateOverride = "simple_items#type=potion_miruvor", cTab = 3)
    public static Item MIRUVOR = new TTMFood(1, 1, 1, new PotionEffect(MobEffects.SPEED,3000,3), new PotionEffect(MobEffects.REGENERATION, 600, 3), new PotionEffect(MobEffects.NAUSEA, 40, 3)).setItemUseAction(true);
    @ModFeature(name = "potion_grog", stateOverride = "simple_items#type=potion_grog", cTab = 3)
    public static Item GROG = new TTMFood(1, 1, 1, new PotionEffect(MobEffects.SPEED,1500,3), new PotionEffect(MobEffects.REGENERATION, 300, 3), new PotionEffect(MobEffects.NAUSEA, 100, 3)).setItemUseAction(true);
    @ModFeature(name = "potion_elvishhealth", stateOverride = "simple_items#type=potion_elvishhealth", cTab = 3)
    public static Item ELVISH_HEALTH = new TTMFood(1, 1, 1, new PotionEffect(PotionInit.ELF_VITALITY,15000,3)).setItemUseAction(true);
    @ModFeature(name = "potion_portableblacksmith", stateOverride = "simple_items#type=potion_portableblacksmith", cTab = 3)
    public static Item WANDERING_BLACKSMITH = new TTMFood(1, 1, 1, new PotionEffect(PotionInit.PERSONAL_BLACKSMITH,15000,2)).setItemUseAction(true);
    @ModFeature(name = "monster_flesh", stateOverride = "simple_items#type=monster_flesh", cTab = 3)
    public static Item MONSTER_FLESH = new TTMFood(5, 64, 2, new PotionEffect(MobEffects.HUNGER,100,2));
    @ModFeature(name = "food_insect", stateOverride = "simple_items#type=food_insect", cTab = 3)
    public static Item INSECT = new TTMFood(1, 64, 1, new PotionEffect(MobEffects.HUNGER,10,1));
    @ModFeature(name = "food_golden_insect", stateOverride = "simple_items#type=food_golden_insect", cTab = 3)
    public static Item GOLDEN_INSECT = new TTMFood(2, 64, 3, new PotionEffect(MobEffects.HUNGER,40,2)).setEffectOverride(true);
    @ModFeature(name = "food_tree_acorn", stateOverride = "simple_items#type=food_tree_acorn", cTab = 3)
    public static Item TREE_ACORN = new TTMFood(1, 64, 1, new PotionEffect(MobEffects.SPEED,10,1));
    @ModFeature(name = "food_golden_tree_acorn", stateOverride = "simple_items#type=food_golden_tree_acorn", cTab = 3)
    public static Item GOLDEN_TREE_ACORN = new TTMFood(2, 64, 3, new PotionEffect(MobEffects.SPEED,40,2)).setEffectOverride(true);
    @ModFeature(name = "food_honey", stateOverride = "simple_items#type=food_honey", cTab = 3)
    public static Item FOOD_HONEY = new TTMFood(2, 64, 3, new PotionEffect(MobEffects.SPEED,100,1)).setItemUseAction(true);
    /* End Region */
    
    /* Custom Blocks */
    @ModFeature(name = "sign", itemBlock = ItemBlockBCore.class, tileEntity = TileSign.class, cTab = 5)
    public static final BlockSigns SIGNS = new BlockSigns();
    @ModFeature(name = "block_hallowed", cTab = 1)
    public static final Block BLOCK_HALLOWED = new BlockTMHallowed();
    @ModFeature(name = "block_tmfireplace", cTab = 1, tileEntity = TileTMFireplace.class)
    public static final Block BLOCK_TMFIREPLACE = new BlockTMFireplace().setHardness(1.5F).setResistance(10F);
    /* End Region */

    /* Records */
    @ModFeature(name = "record_rivendell", stateOverride = "simple_items#type=record_rivendell", cTab = 4)
    public static Item RECORD_RIVENDELL = new TTMRecord("music.ridersofrivendell", SoundInit.ridersofrivendell);
    @ModFeature(name = "record_lothlorien", stateOverride = "simple_items#type=record_lothlorien", cTab = 4)
    public static Item RECORD_LOTHLORIEN = new TTMRecord("music.thelightoflothlorien", SoundInit.thelightoflothlorien);
    @ModFeature(name = "record_erebor", stateOverride = "simple_items#type=record_erebor", cTab = 4)
    public static Item RECORD_EREBOR = new TTMRecord("music.allthatglittersinerebor", SoundInit.allthatglittersinerebor);
    @ModFeature(name = "record_willow", stateOverride = "simple_items#type=record_willow", cTab = 4)
    public static Item RECORD_WILLOW = new TTMRecord("music.willowsong", SoundInit.willowsong);
    @ModFeature(name = "record_minastirith", stateOverride = "simple_items#type=record_minastirith", cTab = 4)
    public static Item RECORD_MINASTIRITH = new TTMRecord("music.minastirith", SoundInit.minastirith);
    @ModFeature(name = "record_edoras", stateOverride = "simple_items#type=record_edoras", cTab = 4)
    public static Item RECORD_EDORAS = new TTMRecord("music.wakeofedoras", SoundInit.wakeofedoras);
    @ModFeature(name = "record_wbattle", stateOverride = "simple_items#type=record_wbattle", cTab = 4)
    public static Item RECORD_WBATTLE = new TTMRecord("music.witchbattle", SoundInit.witchbattle);
    @ModFeature(name = "record_murderfrog", stateOverride = "simple_items#type=record_murderfrog", cTab = 4)
    public static Item RECORD_MURDERFROG = new TTMRecord("music.murderfrog", SoundInit.murderfrog);
    @ModFeature(name = "record_reder", stateOverride = "simple_items#type=record_reder", cTab = 4)
    public static Item RECORD_REDER = new TTMRecord("music.rederssong", SoundInit.rederssong);
    @ModFeature(name = "record_fumble", stateOverride = "simple_items#type=record_fumble", cTab = 4)
    public static Item RECORD_FUMBLE = new TTMRecord("music.trollfumble", SoundInit.trollfumble);
    @ModFeature(name = "record_bombadil", stateOverride = "simple_items#type=record_bombadil", cTab = 4)
    public static Item RECORD_BOMBADIL = new TTMRecord("music.mysteryoftombombadil", SoundInit.mysteryoftombombadil);
    @ModFeature(name = "record_hobbits", stateOverride = "simple_items#type=record_hobbits", cTab = 4)
    public static Item RECORD_HOBBITS = new TTMRecord("music.concerninghobbits", SoundInit.concerninghobbits);

    /* End Region */

    /* Ammo */
    @ModFeature(name = "ammo_boulder", stateOverride = "simple_items#type=ammo_boulder")
    public static Item AMMO_BOULDER = new TTMAmmo(16);
    @ModFeature(name = "fellbeast_fireball", stateOverride = "simple_items#type=fellbeast_fireball")
    public static Item FELLBEAST_FIREBALL = new TTMAmmo(16);
    /* End Region */
}