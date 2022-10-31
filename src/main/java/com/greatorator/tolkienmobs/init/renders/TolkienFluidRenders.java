package com.greatorator.tolkienmobs.init.renders;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;

import static com.greatorator.tolkienmobs.init.TolkienFluids.*;

public class TolkienFluidRenders {
    public static void init() {
        RenderType cutout = RenderType.cutout();
        RenderType cutoutMipped = RenderType.cutoutMipped();
        RenderType translucent = RenderType.translucent();

        ItemBlockRenderTypes.canRenderInLayer(MITHRIL_FLUID.get().defaultFluidState(), translucent);
        ItemBlockRenderTypes.canRenderInLayer(MITHRIL_FLUID_BLOCK.get().defaultBlockState(), translucent);
        ItemBlockRenderTypes.canRenderInLayer(MITHRIL_FLOWING.get().defaultFluidState(), translucent);
        ItemBlockRenderTypes.canRenderInLayer(MORGULIRON_FLUID.get().defaultFluidState(), translucent);
        ItemBlockRenderTypes.canRenderInLayer(MORGULIRON_FLUID_BLOCK.get().defaultBlockState(), translucent);
        ItemBlockRenderTypes.canRenderInLayer(MORGULIRON_FLOWING.get().defaultFluidState(), translucent);
    }
}
