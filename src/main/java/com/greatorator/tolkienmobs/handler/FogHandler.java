package com.greatorator.tolkienmobs.handler;


import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.nio.FloatBuffer;

@EventBusSubscriber
public class FogHandler {
    private final FloatBuffer fogBuffer = GLAllocation.createDirectFloatBuffer(16);

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onRenderFog(EntityViewRenderEvent.RenderFogEvent event){
        System.out.println("PING");

        //Will need to toy around with value, but need to set as a variable as well
        float density = 10F;

        //Colors in rgb by dividing each RGB value by 255, needs to be a variable
        float r = 0.3568627450980392F;
        float g = 0.3725490196078431F;
        float b = 0.3803921568627451F;

        GlStateManager.glFog(2918, this.setFogColorBuffer(r, g, b, 1.0F));
        GlStateManager.setFog(GlStateManager.FogMode.LINEAR);
        GlStateManager.setFogDensity(density);
        GlStateManager.setFogStart(1F);
        GlStateManager.setFogEnd(16F);
    }

    private FloatBuffer setFogColorBuffer(float red, float green, float blue, float alpha){
        this.fogBuffer.clear();
        this.fogBuffer.put(red).put(green).put(blue).put(alpha);
        this.fogBuffer.flip();
        return this.fogBuffer;
    }
}
