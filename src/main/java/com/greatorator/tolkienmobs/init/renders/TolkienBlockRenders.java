package com.greatorator.tolkienmobs.init.renders;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;

import static com.greatorator.tolkienmobs.init.TolkienBlocks.*;

public class TolkienBlockRenders {
    public static void init() {
        RenderType cutout = RenderType.cutout();
        RenderType cutoutMipped = RenderType.cutoutMipped();
        RenderType translucent = RenderType.translucent();

        ItemBlockRenderTypes.canRenderInLayer(MUSHROOM_DECAY_BLOOM.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(MUSHROOM_BLOOM_DECAY.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(FLOWER_SIMBELMYNE.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(FLOWER_MIRKWOOD.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(FLOWER_ALFIRIN.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(FLOWER_ATHELAS.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(FLOWER_NIPHREDIL.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(FLOWER_SWAMPMILKWEED.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(FLOWER_LILLYOFTHEVALLEY.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(SAPLING_MALLORN.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(SAPLING_MIRKWOOD.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(SAPLING_CULUMALDA.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(SAPLING_LEBETHRON.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(SAPLING_DEADWOOD.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(SAPLING_FANGORNOAK.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(MITHRIL_BARS.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(MORGULIRON_BARS.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(DOOR_MORGULIRON.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(TRAPDOOR_MALLORN.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(TRAPDOOR_MIRKWOOD.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(TRAPDOOR_CULUMALDA.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(TRAPDOOR_LEBETHRON.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(TRAPDOOR_MITHRIL.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(TRAPDOOR_MORGULIRON.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(PRESSURE_PLATE_MALLORN.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(PRESSURE_PLATE_MIRKWOOD.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(PRESSURE_PLATE_CULUMALDA.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(PRESSURE_PLATE_LEBETHRON.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(PRESSURE_PLATE_MITHRIL.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(PRESSURE_PLATE_MORGULIRON.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(TORCH_MALLORN.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(TORCH_MIRKWOOD.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(TORCH_CULUMALDA.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(TORCH_LEBETHRON.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(WALL_TORCH_MALLORN.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(WALL_TORCH_MIRKWOOD.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(WALL_TORCH_CULUMALDA.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(WALL_TORCH_LEBETHRON.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(LEAFPILE_MALLORN.get().defaultBlockState(), cutoutMipped);
        ItemBlockRenderTypes.canRenderInLayer(LEAFPILE_MIRKWOOD.get().defaultBlockState(), cutoutMipped);
        ItemBlockRenderTypes.canRenderInLayer(LEAFPILE_CULUMALDA.get().defaultBlockState(), cutoutMipped);
        ItemBlockRenderTypes.canRenderInLayer(LEAFPILE_LEBETHRON.get().defaultBlockState(), cutoutMipped);
        ItemBlockRenderTypes.canRenderInLayer(LEAFPILE_FANGORNOAK.get().defaultBlockState(), cutoutMipped);
        ItemBlockRenderTypes.canRenderInLayer(TTMFIREPLACE.get().defaultBlockState(), cutoutMipped);
        ItemBlockRenderTypes.canRenderInLayer(PIGGYBANK.get().defaultBlockState(), cutoutMipped);
        ItemBlockRenderTypes.canRenderInLayer(PIPEWEED.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(PLACARD.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(CHAMELEON_BLOCK.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(KEY_STONE_BLOCK.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(CAMO_GLOWSTONE_BLOCK.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(CAMO_SMOKER_BLOCK.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(CAMO_FLUID_BLOCK.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(CAMO_SPAWNER_BLOCK.get().defaultBlockState(), cutout);
        ItemBlockRenderTypes.canRenderInLayer(ROCKPILE.get().defaultBlockState(), cutoutMipped);
        ItemBlockRenderTypes.canRenderInLayer(MILESTONE_BLOCK.get().defaultBlockState(), cutoutMipped);
        ItemBlockRenderTypes.canRenderInLayer(BLOCK_AMMOLITE.get().defaultBlockState(), translucent);

    }
}
