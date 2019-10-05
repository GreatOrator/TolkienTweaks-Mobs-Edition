package com.greatorator.tolkienmobs.integration;

import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;

/**
 * Created by brandon3055 on 5/10/19.
 */
public class TinkersTTM {

    public static void init() {
        if (Loader.isModLoaded("tconstruct")) {
            registerTTM();
        }
    }

    @Optional.Method(modid = "tconstruct")
    private static void registerTTM() {
        // Create the tool material
        Material mithrilMat = new Material("ttm.mithril", 0xFFFFFF); // <- material colour

        //Add the ore dictionary ingot if you have one
//        mithrilMat.addItemIngot("oreName");

        //Register an item and specify its material value. An ingot is 144, a shard is 72 and a nugget is 16
        mithrilMat.addItem(TTMFeatures.INGOT_MITHRIL, 1, 144);
        mithrilMat.addItem(TTMFeatures.NUGGET_MITHRIL, 1, 16);
//        mithrilMat.addItem(TTMFeatures.SHARD_MITHRIL, 1, 72); //You may need to add a shard
//        mithrilMat.setShard(TTMFeatures.SHARD_MITHRIL);
        mithrilMat.setCraftable(true); //Enables bart builder recipes
//        mithrilMat.setCastable(true); //Enables casting recipes but i think you need to create and somehow register a fluid for this material

        TinkerRegistry.addMaterial(mithrilMat);

        //Add Head stats
        TinkerRegistry.addMaterialStats(mithrilMat, new HeadMaterialStats(1000, 5, 10, 3));

        //Add Handle Stats
        TinkerRegistry.addMaterialStats(mithrilMat, new HandleMaterialStats(3, 400));

        //Other Stats: ArrowShaftMaterialStats, BowMaterialStats, ExtraMaterialStats, FletchingMaterialStats, ProjectileMaterialStats, BowStringMaterialStats


    }

}
