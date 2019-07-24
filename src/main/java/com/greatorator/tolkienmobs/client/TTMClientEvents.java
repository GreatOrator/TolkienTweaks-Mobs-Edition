package com.greatorator.tolkienmobs.client;

import com.greatorator.tolkienmobs.init.BiomeInit;
import com.greatorator.tolkienmobs.init.PotionInit;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import com.greatorator.tolkienmobs.item.magical.ItemTrinketRing;
import com.greatorator.tolkienmobs.item.potiontypes.PotionTTMDrowning;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Created by brandon3055 on 20/02/19. */
public class TTMClientEvents {

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void itemColourEvent(ColorHandlerEvent.Item event) {

        event.getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            if (tintIndex < 1) {
                return -1;
            }
            else {
                Potion potion = ItemTrinketRing.getPotion(stack);
                if (potion != null) {
                    return potion.getLiquidColor();
                }
            }
            return -1;
        }, TTMFeatures.TRINKET_RING, TTMFeatures.TRINKET_CHARM, TTMFeatures.TRINKET_BELT, TTMFeatures.TRINKET_AMULET);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void renderAir(RenderGameOverlayEvent event)
    {
        Minecraft mc = Minecraft.getMinecraft();
        if(event.getType() == RenderGameOverlayEvent.ElementType.AIR) {

            EntityLivingBase player = (EntityLivingBase)mc.getRenderViewEntity();
            String drown = PotionTTMDrowning.NAME;

            if (player != null && drown != null && PotionInit.ELEMENTAL_DROWNING != null && player.isPotionActive(PotionInit.ELEMENTAL_DROWNING)) {
                event.setCanceled(true);

                if(!player.isInsideOfMaterial(Material.WATER)) {
                    int air = player.getEntityData().getInteger(PotionTTMDrowning.TAG_NAME);

                    mc.mcProfiler.startSection("air");
                    GlStateManager.enableBlend();
                    int width = event.getResolution().getScaledWidth();
                    int height = event.getResolution().getScaledHeight();
                    int left = width / 2 + 91;
                    int top = height - GuiIngameForge.right_height;

                    int full = MathHelper.ceil((double)(air - 2) * 10.0D / 300.0D);
                    int partial = MathHelper.ceil((double)air * 10.0D / 300.0D) - full;

                    for (int i = 0; i < full + partial; ++i)
                    {
                        mc.ingameGUI.drawTexturedModalRect(left - i * 8 - 9, top, (i < full ? 16 : 25), 18, 9, 9);
                    }
                    GuiIngameForge.right_height += 10;


                    GlStateManager.disableBlend();
                    mc.mcProfiler.endSection();
                }
            }
        }
    }

    @SubscribeEvent
    public void onEntityLiving(LivingEvent.LivingUpdateEvent event){
        EntityLivingBase player = event.getEntityLiving();

        if(player.world.isRemote || !player.isEntityAlive()) return;

        if(player.isInsideOfMaterial(Material.WATER) && player.ticksExisted % 20 == 0 && player instanceof EntityPlayerMP) {
            Biome biome = player.world.getBiome(player.getPosition());

            if(biome == BiomeInit.MIRKWOOD) {

                player.addPotionEffect(new PotionEffect(PotionInit.SLEEPNESIA, 600, 8));
            }
        }
    }

    @SubscribeEvent
    public void event(RenderPlayerEvent.Pre event) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        PotionEffect effect = player.getActivePotionEffect(PotionInit.SLEEPNESIA);

        if (effect != null) {
            GlStateManager.pushMatrix();
            GlStateManager.rotate(90, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(90, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
        }
    }

    @SubscribeEvent
    public void event(RenderPlayerEvent.Post event) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        PotionEffect effect = player.getActivePotionEffect(PotionInit.SLEEPNESIA);

        if (effect != null) {
            GlStateManager.popMatrix();
        }
    }

    @SubscribeEvent
    public void event(EntityViewRenderEvent.CameraSetup event) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        PotionEffect effect = player.getActivePotionEffect(PotionInit.SLEEPNESIA);

        if (effect != null) {
            Entity entity = Minecraft.getMinecraft().getRenderViewEntity();
            if (entity != null) {
                float partialTicks = Minecraft.getMinecraft().getRenderPartialTicks();

                GlStateManager.translate(0.0F, 1.2F, 0.0F);
                GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks + 180.0F, 0.0F, -1.0F, 0.0F);
                GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, -1.0F, 0.0F, 0.0F);
            }
        }
    }

    @SubscribeEvent
    public void event(RenderHandEvent event) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        PotionEffect effect = player.getActivePotionEffect(PotionInit.SLEEPNESIA);

        if (effect != null) {
            event.setCanceled(true);
        }
    }
}