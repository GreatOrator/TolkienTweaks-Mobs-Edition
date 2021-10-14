package com.greatorator.tolkienmobs.client;

import codechicken.lib.util.SneakyUtils;
import com.brandon3055.brandonscore.api.render.GuiHelper;
import com.greatorator.tolkienmobs.datagen.EnchantmentGenerator;
import com.greatorator.tolkienmobs.datagen.PotionGenerator;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.border.WorldBorder;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import org.lwjgl.opengl.GL11;

import java.util.*;

import static net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType.VIGNETTE;

/**
 * Created by brandon3055 on 20/02/19.
 */
public class TTMClientEvents {

    private static final ResourceLocation VIGNETTE_LOCATION = new ResourceLocation("textures/misc/vignette.png");
    public static Set<UUID> playersWithUphillStep = new HashSet<>();
    public static Set<UUID> playersWithExtraHealth = new HashSet<>();
    public static Set<UUID> playersWithHardStance = new HashSet<>();
    public static Set<UUID> playersCoweringInFear = new HashSet<>();
    public static Set<UUID> playersWayTooTired = new HashSet<>();

    private static AttributeModifier gondorResolve = new AttributeModifier(UUID.randomUUID(), "GondorResolve", 0.25D, AttributeModifier.Operation.ADDITION);
    private static AttributeModifier sleepTight = new AttributeModifier(UUID.randomUUID(), "SleepyTime", -10F, AttributeModifier.Operation.ADDITION);

    public static void livingUpdate(LivingEvent.LivingUpdateEvent event) {
        LivingEntity entity = event.getEntityLiving();
        PlayerEntity player = Minecraft.getInstance().player;
        Entity render = Minecraft.getInstance().getCameraEntity();

        //region/*---------------- Elvish Nimbleness -----------------*/
        if (entity.level.isClientSide) {
            boolean highStepListed = playersWithUphillStep.contains(entity.getUUID()) && entity.maxUpStep >= 1f;
            boolean hasHighStep = entity.getEffect(PotionGenerator.ELF_NIMBLENESS.get()) != null;

            if (hasHighStep && !highStepListed) {
                playersWithUphillStep.add(entity.getUUID());
                entity.maxUpStep = 1.0625f;
            }

            if (!hasHighStep && highStepListed) {
                playersWithUphillStep.remove(entity.getUUID());
                entity.maxUpStep = 0.6F;
            }
        }
        //endregion
        //region/*---------------- Elvish Longevity -----------------*/
        if (entity.level.isClientSide) {
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentGenerator.ELVEN_LONGEVITY.get(), entity);
            float absorb = (float) (10 * level);
            boolean extraHealthListed = playersWithExtraHealth.contains(entity.getUUID());
            boolean hasExtraHealth = level > 0;

            //TODO this will be broken now because level is not accounted for

            if (hasExtraHealth && entity.getAbsorptionAmount() == 0 || !extraHealthListed) {
                playersWithExtraHealth.add(entity.getUUID());
                entity.setAbsorptionAmount(entity.getAbsorptionAmount() + (absorb));
            }

            if (!hasExtraHealth && extraHealthListed) {
                playersWithExtraHealth.add(entity.getUUID());
                entity.setAbsorptionAmount(entity.getAbsorptionAmount() - (absorb));
            }
        }
        //endregion
        //region/*---------------- Gondor Resolve -----------------*/
        if (entity.level.isClientSide) {
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentGenerator.GONDOR_RESOLVE.get(), entity);
            ModifiableAttributeInstance battleResolve = entity.getAttribute(Attributes.KNOCKBACK_RESISTANCE);
            boolean hardStanceListed = playersWithHardStance.contains(entity.getUUID());
            boolean hasHardStance = level > 0;

            if (hasHardStance && !hardStanceListed) {
                playersWithHardStance.add(entity.getUUID());
                assert battleResolve != null;
                battleResolve.addPermanentModifier(gondorResolve);
            }

            if (!hasHardStance && hardStanceListed) {
                playersWithHardStance.remove(entity.getUUID());
                assert battleResolve != null;
                battleResolve.removeModifier(gondorResolve);
            }
        }
        //endregion
        //region/*---------------- Dwarven Endurance -----------------*/
        if (entity.level.isClientSide) {
            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentGenerator.DWARF_ENDURANCE.get(), entity);

            if (entity instanceof PlayerEntity && level != 0) {
                ((PlayerEntity) entity).getFoodData().eat(level + 1, 1.0F);
            }
        }
        //endregion
        //region/*---------------- Sleepnesia -----------------*/
        boolean goneToSleepListed = playersWayTooTired.contains(entity.getUUID());
        boolean hasGoneToSleep = entity.getEffect(PotionGenerator.SLEEPNESIA.get()) != null;
        ModifiableAttributeInstance nightyNight = entity.getAttribute(Attributes.MOVEMENT_SPEED);

        if (entity.level.isClientSide) {
            if (player != null) {
                if (hasGoneToSleep && !goneToSleepListed) {
                    playersWayTooTired.add(entity.getUUID());
                    player.setForcedPose(Pose.SLEEPING);
                    nightyNight.addPermanentModifier(sleepTight);
                }

                if (!hasGoneToSleep && goneToSleepListed) {
                    playersWayTooTired.remove(entity.getUUID());
                    player.setForcedPose(null);
                    nightyNight.removeModifier(sleepTight);
                }
            }
            //endregion
        }
    }


    public static void renderOverlayEvent(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            PlayerEntity player = Minecraft.getInstance().player;
            boolean sleepEffectActive = player.getEffect(PotionGenerator.SLEEPNESIA.get()) != null;
            boolean terrorEffectActive = player.getEffect(PotionGenerator.PARALYSING_FEAR.get()) != null;
            boolean effectActive = sleepEffectActive || terrorEffectActive;

            float darkness = player.getPersistentData().getFloat("ttm_darkness_effect");
            if (effectActive && darkness < 1) {
                darkness = Math.min(darkness + 0.05F, 1F);
            } else if (!effectActive && darkness > 0) {
                darkness = Math.max(darkness - 0.05F, 0F);
            }
            player.getPersistentData().putFloat("ttm_darkness_effect", darkness);

            int darkValue = 200; //255 == completely black

            if (darkness > 0) {
                MatrixStack matrixStack = event.getMatrixStack();
                int darkHex = (int) ((double) darkValue * darkness);
                int screenHeight = event.getWindow().getScreenHeight();
                int screenWidth = event.getWindow().getScreenWidth();
                int effectColour = 0x000000;
                IRenderTypeBuffer.Impl getter = Minecraft.getInstance().renderBuffers().bufferSource();
                GuiHelper.drawRect(getter, matrixStack, 0, 0, screenWidth, screenHeight, effectColour | darkHex << 24);
                getter.endBatch();
            }
        }
    }
}