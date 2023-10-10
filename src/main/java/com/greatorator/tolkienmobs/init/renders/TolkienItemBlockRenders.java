package com.greatorator.tolkienmobs.init.renders;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;

import static com.greatorator.tolkienmobs.TolkienMobs.NAME;
import static com.greatorator.tolkienmobs.init.TolkienBlocks.*;

public class TolkienItemBlockRenders {
        public static void init() {
        RenderType cutout = RenderType.cutout();
        RenderType cutoutMipped = RenderType.cutoutMipped();
        RenderType translucent = RenderType.translucent();

            ItemBlockRenderTypes.setRenderLayer(MUSHROOM_DECAY_BLOOM.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(MUSHROOM_BLOOM_DECAY.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(FLOWER_SIMBELMYNE.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(FLOWER_MIRKWOOD.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(FLOWER_ALFIRIN.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(FLOWER_ATHELAS.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(FLOWER_NIPHREDIL.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(FLOWER_SWAMPMILKWEED.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(FLOWER_LILLYOFTHEVALLEY.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(SAPLING_MALLORN.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(SAPLING_MIRKWOOD.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(SAPLING_CULUMALDA.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(SAPLING_LEBETHRON.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(SAPLING_DEADWOOD.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(SAPLING_FANGORNOAK.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(MITHRIL_BARS.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(MORGULIRON_BARS.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(DOOR_MORGULIRON.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(TRAPDOOR_MALLORN.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(TRAPDOOR_MIRKWOOD.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(TRAPDOOR_CULUMALDA.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(TRAPDOOR_LEBETHRON.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(TRAPDOOR_FANGORNOAK.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(TRAPDOOR_MITHRIL.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(TRAPDOOR_MORGULIRON.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(PRESSURE_PLATE_MALLORN.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(PRESSURE_PLATE_MIRKWOOD.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(PRESSURE_PLATE_CULUMALDA.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(PRESSURE_PLATE_LEBETHRON.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(PRESSURE_PLATE_DEADWOOD.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(PRESSURE_PLATE_FANGORNOAK.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(PRESSURE_PLATE_MITHRIL.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(PRESSURE_PLATE_MORGULIRON.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(TORCH_MALLORN.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(TORCH_MIRKWOOD.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(TORCH_CULUMALDA.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(TORCH_LEBETHRON.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(TORCH_DEADWOOD.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(TORCH_FANGORNOAK.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(WALL_TORCH_MALLORN.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(WALL_TORCH_MIRKWOOD.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(WALL_TORCH_CULUMALDA.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(WALL_TORCH_LEBETHRON.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(WALL_TORCH_DEADWOOD.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(WALL_TORCH_FANGORNOAK.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(LEAFPILE_MALLORN.get(), cutoutMipped);
            ItemBlockRenderTypes.setRenderLayer(LEAFPILE_MIRKWOOD.get(), cutoutMipped);
            ItemBlockRenderTypes.setRenderLayer(LEAFPILE_CULUMALDA.get(), cutoutMipped);
            ItemBlockRenderTypes.setRenderLayer(LEAFPILE_LEBETHRON.get(), cutoutMipped);
            ItemBlockRenderTypes.setRenderLayer(LEAFPILE_FANGORNOAK.get(), cutoutMipped);
            ItemBlockRenderTypes.setRenderLayer(TTMFIREPLACE.get(), cutoutMipped);
            ItemBlockRenderTypes.setRenderLayer(PIGGYBANK.get(), cutoutMipped);
            ItemBlockRenderTypes.setRenderLayer(PIPEWEED.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(PLANKS_DEADWOOD.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(SLAB_DEADWOOD.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(STAIRS_DEADWOOD.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(DEADWOOD_BUTTON.get(), cutoutMipped);
            ItemBlockRenderTypes.setRenderLayer(DOOR_DEADWOOD.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(DOOR_FANGORNOAK.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(TRAPDOOR_DEADWOOD.get(), cutoutMipped);
            ItemBlockRenderTypes.setRenderLayer(FENCE_DEADWOOD.get(), cutoutMipped);
            ItemBlockRenderTypes.setRenderLayer(FENCE_GATE_DEADWOOD.get(), cutoutMipped);
            ItemBlockRenderTypes.setRenderLayer(CHAMELEON_BLOCK.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(KEY_STONE_BLOCK.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(CAMO_GLOWSTONE_BLOCK.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(CAMO_SMOKER_BLOCK.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(CAMO_FLUID_BLOCK.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(CAMO_SPAWNER_BLOCK.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(ROCKPILE.get(), cutoutMipped);
            ItemBlockRenderTypes.setRenderLayer(LIGHTNINGBUG_BLOCK.get(), cutoutMipped);
            ItemBlockRenderTypes.setRenderLayer(MILESTONE_BLOCK.get(), cutoutMipped);
            ItemBlockRenderTypes.setRenderLayer(BLOCK_AMMOLITE.get(), translucent);
            ItemBlockRenderTypes.setRenderLayer(PANE_AMMOLITE.get(), translucent);
            ItemBlockRenderTypes.setRenderLayer(ARDA_PORTAL.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(POTTED_MUSHROOM_DECAY_BLOOM.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(POTTED_MUSHROOM_BLOOM_DECAY.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(POTTED_FLOWER_SIMBELMYNE.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(POTTED_FLOWER_MIRKWOOD.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(POTTED_FLOWER_ALFIRIN.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(POTTED_FLOWER_ATHELAS.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(POTTED_FLOWER_NIPHREDIL.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(POTTED_FLOWER_SWAMPMILKWEED.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(POTTED_FLOWER_LILLYOFTHEVALLEY.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(POTTED_SAPLING_MALLORN.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(POTTED_SAPLING_MIRKWOOD.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(POTTED_SAPLING_CULUMALDA.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(POTTED_SAPLING_LEBETHRON.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(POTTED_SAPLING_DEADWOOD.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(POTTED_SAPLING_FANGORNOAK.get(), cutout);
            ItemBlockRenderTypes.setRenderLayer(ELVEN_LANTERN.get(), TolkienItemBlockRenders::isGlassLanternValidLayer);
            ItemBlockRenderTypes.setRenderLayer(MORGUL_LANTERN.get(), TolkienItemBlockRenders::isGlassLanternValidLayer);
            ItemBlockRenderTypes.setRenderLayer(TRINKET_TABLE.get(), cutoutMipped);
            ItemBlockRenderTypes.setRenderLayer(WELL.get(), cutoutMipped);
        }
    public static boolean isGlassLanternValidLayer(RenderType layerToCheck) {
        return layerToCheck == RenderType.cutout() || layerToCheck == RenderType.translucent();
    }

    public String getName() {
        return NAME + " - Block Renders";
    }
}
