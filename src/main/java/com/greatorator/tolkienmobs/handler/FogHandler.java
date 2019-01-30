package com.greatorator.tolkienmobs.handler;


import codechicken.lib.colour.Colour;
import codechicken.lib.math.MathHelper;
import codechicken.lib.util.ClientUtils;
import com.greatorator.tolkienmobs.world.biomes.IFogyBiome;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.nio.FloatBuffer;

//@EventBusSubscriber //Don't need to do this AND manually register to the event bus. Just do one or the other. (Prefer EVENT_BUS.register)
public class FogHandler {
    private final FloatBuffer fogBuffer = GLAllocation.createDirectFloatBuffer(16);

    private int tick = 0;
    private IFogyBiome activeBiome = null;

    private float targetDensity = 0;
    private float density = 0;
    private int[] colour = {0, 0, 0, 0}; //ARGB we don't need A but we get it when we unpack the hex colour.


    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START || !ClientUtils.inWorld()) return;

        //We want as little code as possible running in the RenderFog event to reduce performance impact.
        //All code in that event will be run every single render frame.
        //We can use this method to pre-calculate as much stuff as possible to improve efficiency.

        Minecraft mc = Minecraft.getMinecraft();

        //Detect if the player is in a fogy biome
        if (tick++ % 10 == 0) {//Only check what biome the player is in every half second. Does not need to be any faster than that.
            EntityPlayer player = mc.player;
            Biome biome = player.world.getBiome(player.getPosition());
            if (biome instanceof IFogyBiome) {
                activeBiome = (IFogyBiome) biome;
            }
            else {
                activeBiome = null;
            }
        }


        //Update the fog
        if (activeBiome != null || density > 0) {
            if (activeBiome != null) {
                //Fog is being applied for the first time so we can just set the colour.
                int[] newColour = Colour.unpack(activeBiome.getFogColour(mc.player));

                if (density == 0) {
                    //If there was previously no fog applied we can just set the colour strait up.
                    colour = newColour;
                }
                else {
                    //Otherwise we want to update the existing colour.
                    transition(colour, newColour);
                }

                targetDensity = activeBiome.getFogDensity(mc.player);
            }
            else {
                targetDensity = 0;
            }

            //This provides a slow transition from the current density to the target density.
            density = MathHelper.approachLinear(density, targetDensity, 0.005F); //Adjust the last value in this call to change how quickly the density transitions.
        }
    }

    //Provides a smooth transition between colour to avoid a sudden jarring colour change when going from one foggy biome to another.
    private void transition(int[] colour, int[] targetColour) {
        for (int i = 0; i < colour.length; i++) {
            if (colour[i] == targetColour[i]) continue;
            colour[i] = MathHelper.clip((int) MathHelper.approachLinear(colour[i], targetColour[i], 5), 0, 255);
        }
    }


    //  @SideOnly(Side.CLIENT) //Not needed as this is only registered client side so there is no chance it will ba called server side.
    @SubscribeEvent
    public void onRenderFog(EntityViewRenderEvent.RenderFogEvent event) {
        if (density <= 0) return;

        GlStateManager.setFog(GlStateManager.FogMode.EXP);
        GlStateManager.setFogDensity(density);
        GlStateManager.setFogStart(1F);
        GlStateManager.setFogEnd(16F);
        GlStateManager.glFog(2918, this.setFogColorBuffer(colour[1] / 255F, colour[2] / 255F, colour[3] / 255F, 1.0F));
    }

    private FloatBuffer setFogColorBuffer(float red, float green, float blue, float alpha) {
        this.fogBuffer.clear();
        this.fogBuffer.put(red).put(green).put(blue).put(alpha);
        this.fogBuffer.flip();
        return this.fogBuffer;
    }
}