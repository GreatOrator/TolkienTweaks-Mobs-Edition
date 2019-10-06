package com.greatorator.tolkienmobs.integration;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
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
    public Material mithril;
    public Material morguliron;
    public Fluid FLUID_MITHRIL = new FluidMolten(TolkienMobs.MODID + ".molten_mithril", 0xe9f1ff);
    public Fluid FLUID_MORGULIRON = new FluidMolten(TolkienMobs.MODID + ".molten_morguliron", 0x303933);

    public TinkersTTM() {
        mithril = addMaterial("mithril", 0xe9f1ff, TTMFeatures.TOOL_MITHRIL, TinkerTraits.stonebound);
        morguliron = addMaterial("morguliron", 0x303933, TTMFeatures.TOOL_MORGULIRON, TinkerTraits.heavy);
    }

    public void register() {
        TinkerRegistry.integrate(mithril);
        TinkerRegistry.integrate(morguliron);
        FluidRegistry.registerFluid(FLUID_MITHRIL);
        FluidRegistry.addBucketForFluid(FLUID_MITHRIL);
        FluidRegistry.registerFluid(FLUID_MORGULIRON);
        FluidRegistry.addBucketForFluid(FLUID_MORGULIRON);
        mithril.setFluid(FLUID_MITHRIL);
        morguliron.setFluid(FLUID_MORGULIRON);
        TinkerSmeltery.registerToolpartMeltingCasting(mithril);
        TinkerSmeltery.registerToolpartMeltingCasting(morguliron);
        TinkerRegistry.registerMelting(TTMFeatures.INGOT_MITHRIL, FLUID_MITHRIL, Material.VALUE_Ingot);
        TinkerRegistry.registerMelting(TTMFeatures.BLOCK_MITHRIL, FLUID_MITHRIL, Material.VALUE_Block);
    }

    public Material addMaterial(String name, int color, Item.ToolMaterial material, ITrait trait) {
        Material mat = new Material(name, color);
        mat.setCraftable(true);
        mat.setCastable(false);
        mat.addCommonItems(name);
        mat.addItem("ingot" + name.substring(0,1).toUpperCase() + name.substring(1), 1, Material.VALUE_Ingot);
        mat.addItem("block" + name.substring(0,1).toUpperCase() + name.substring(1), 1, Material.VALUE_Block);
        mat.addTrait(trait);
        mat.addStats(new HeadMaterialStats(
                material.getMaxUses()/3*2,
                material.getEfficiency(),
                material.getAttackDamage() + 2,
                material.getHarvestLevel()
        ));
        mat.addStats(new HandleMaterialStats(
                material.getEnchantability()/15f,
                material.getMaxUses()/3
        ));
        mat.addStats(new ExtraMaterialStats(material.getMaxUses()/3));
        mat.addStats(new ArrowShaftMaterialStats(
                material.getEnchantability()/15f,
                material.getMaxUses()/100
        ));
        return mat;
    }
}