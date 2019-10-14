package com.greatorator.tolkienmobs.integration;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Optional;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.fluid.FluidMolten;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.traits.ITrait;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.tools.TinkerTraits;

/**
 * Created by brandon3055 on 5/10/19.
 */
public class TinkersTTM {
    public static Material mithril;
    public static Material morguliron;
    public static Fluid FLUID_MITHRIL;
    public static Fluid FLUID_MORGULIRON;

    @Optional.Method(modid = "tconstruct")
    public static void preInit() {
        mithril = addMaterial(TolkienMobs.MODID + ":dwarven_silver", "DwarvenSilver", 0xe9f1ff, TTMFeatures.TOOL_MITHRIL, TinkerTraits.stonebound);
        morguliron = addMaterial(TolkienMobs.MODID + ":morgul_iron", "MorgulIron", 0x303933, TTMFeatures.TOOL_MORGULIRON, TinkerTraits.heavy);

        FluidRegistry.registerFluid(FLUID_MITHRIL = new FluidMolten(TolkienMobs.MODID + ".molten_mithril", 0xe9f1ff));
        FluidRegistry.addBucketForFluid(FLUID_MITHRIL);

        FluidRegistry.registerFluid(FLUID_MORGULIRON = new FluidMolten(TolkienMobs.MODID + ".molten_morguliron", 0x303933));
        FluidRegistry.addBucketForFluid(FLUID_MORGULIRON);

        mithril.setFluid(FLUID_MITHRIL);
        morguliron.setFluid(FLUID_MORGULIRON);
    }

    @Optional.Method(modid = "tconstruct")
    public static void init() {

        TinkerSmeltery.registerToolpartMeltingCasting(mithril);
        TinkerSmeltery.registerToolpartMeltingCasting(morguliron);

        TinkerRegistry.registerMelting(TTMFeatures.INGOT_MITHRIL, FLUID_MITHRIL, Material.VALUE_Ingot);
        TinkerRegistry.registerMelting(TTMFeatures.BLOCK_MITHRIL, FLUID_MITHRIL, Material.VALUE_Block);

        TinkerRegistry.registerMelting(TTMFeatures.INGOT_MORGULIRON, FLUID_MORGULIRON, Material.VALUE_Ingot);
        TinkerRegistry.registerMelting(TTMFeatures.BLOCK_MORGULIRON, FLUID_MORGULIRON, Material.VALUE_Block);
    }


    public static void registerTcon() {
//        mithril.setFluid(FLUID_MITHRIL);
//        morguliron.setFluid(FLUID_MORGULIRON);
//
//        TinkerRegistry.integrate(mithril, FLUID_MITHRIL, "DwarvenSilver");
//        TinkerRegistry.integrate(morguliron, FLUID_MORGULIRON, "MorgulIron");
//
//        TinkerSmeltery.registerToolpartMeltingCasting(mithril);
//        TinkerSmeltery.registerToolpartMeltingCasting(morguliron);
//
//        TinkerRegistry.registerMelting(TTMFeatures.INGOT_MITHRIL, FLUID_MITHRIL, Material.VALUE_Ingot);
//        TinkerRegistry.registerMelting(TTMFeatures.BLOCK_MITHRIL, FLUID_MITHRIL, Material.VALUE_Block);
//
//        TinkerRegistry.registerMelting(TTMFeatures.INGOT_MORGULIRON, FLUID_MORGULIRON, Material.VALUE_Ingot);
//        TinkerRegistry.registerMelting(TTMFeatures.BLOCK_MORGULIRON, FLUID_MORGULIRON, Material.VALUE_Block);
    }

    @Optional.Method(modid = "tconstruct")
    public static Material addMaterial(String name, String oredictSuffix, int color, Item.ToolMaterial material, ITrait trait) {
        Material mat = new Material(name, color);
        mat.setCraftable(false);
        mat.setCastable(false);
        mat.addCommonItems(oredictSuffix);
//        mat.addItem("ingot" + oredictSuffix, 1, Material.VALUE_Ingot); //addCommonItems does this for you
//        mat.addItem("block" + oredictSuffix, 1, Material.VALUE_Block);
        mat.addTrait(trait);
        mat.addStats(new HeadMaterialStats(material.getMaxUses() / 3 * 2, material.getEfficiency(), material.getAttackDamage() + 2, material.getHarvestLevel()));
        mat.addStats(new HandleMaterialStats(material.getEnchantability() / 15f, material.getMaxUses() / 3));
        mat.addStats(new ExtraMaterialStats(material.getMaxUses() / 3));
        mat.addStats(new ArrowShaftMaterialStats(material.getEnchantability() / 15f, material.getMaxUses() / 100));
        TinkerRegistry.addMaterial(mat/*, trait*/);
        return mat;
    }
}