package com.greatorator.tolkienmobs.item.potion;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.PotionGenerator;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid= TolkienMobs.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventsTTMEffect {
    public static final Logger LOGGER = LogManager.getLogger("TolkienMobs");

//    /** Sleepnesia **/
//    @OnlyIn(Dist.CLIENT)
//    @SubscribeEvent
//    public static void event(RenderPlayerEvent.Pre event) {
//        LOGGER.info("Good night fair player");
//        PlayerEntity player = Minecraft.getInstance().player;
//        EffectInstance effect = player.getActivePotionEffect(PotionGenerator.SLEEPNESIA.get());
//
//        if (effect != null) {
//            RenderSystem.pushMatrix();
//            RenderSystem.rotatef(90, 0.0F, 1.0F, 0.0F);
//            RenderSystem.rotatef(90, 0.0F, 0.0F, 1.0F);
//            RenderSystem.rotatef(270.0F, 0.0F, 1.0F, 0.0F);
//        }
//    }
//
//    @OnlyIn(Dist.CLIENT)
//    @SubscribeEvent
//    public static void event(RenderPlayerEvent.Post event) {
//        LOGGER.info("Sleep Tight");
//        PlayerEntity player = Minecraft.getInstance().player;
//        EffectInstance effect = player.getActivePotionEffect(PotionGenerator.SLEEPNESIA.get());
//
//        if (effect != null) {
//            RenderSystem.popMatrix();
//        }
//    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void event(EntityViewRenderEvent.CameraSetup event) {
        PlayerEntity player = Minecraft.getInstance().player;
        EffectInstance effect = player.getActivePotionEffect(PotionGenerator.SLEEPNESIA.get());

        if (effect != null) {
            Entity entity = Minecraft.getInstance().getRenderViewEntity();
            if (entity != null) {
                float partialTicks = Minecraft.getInstance().getRenderPartialTicks();

                RenderSystem.translatef(0.0F, 1.2F, 0.0F);
                RenderSystem.rotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks + 180.0F, 0.0F, -1.0F, 0.0F);
                RenderSystem.rotatef(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, -1.0F, 0.0F, 0.0F);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void event(RenderHandEvent event) {
        PlayerEntity player = Minecraft.getInstance().player;
        EffectInstance effect = player.getActivePotionEffect(PotionGenerator.SLEEPNESIA.get());

        if (effect != null) {
            event.setCanceled(true);
        }
    }
}