package com.greatorator.tolkienmobs.event.client;

import com.brandon3055.brandonscore.api.render.GuiHelper;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.block.SleepingBagBlock;
import com.greatorator.tolkienmobs.block.models.ChameleonBakedModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.RemoteClientPlayerEntity;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ClientEvents {
    private static final Logger LOGGER = LogManager.getLogger();
    public static Set<UUID> playersWithUphillStep = new HashSet<>();
    public static Set<UUID> playersWayTooTired = new HashSet<>();

    private static AttributeModifier sleepTight = new AttributeModifier(UUID.randomUUID(), "SleepyTime", -10F, AttributeModifier.Operation.ADDITION);

    public static void onModelBakeEvent(ModelBakeEvent event) {
        for (BlockState blockState : TTMContent.CHAMELEON_BLOCK.get().getStateDefinition().getPossibleStates()) {
            ModelResourceLocation variantMRL = BlockModelShapes.stateToModelLocation(blockState);
            IBakedModel existingModel = event.getModelRegistry().get(variantMRL);
            if (existingModel == null) {
                LOGGER.warn("Did not find the expected vanilla baked model(s) for blockCamouflage in registry");
            } else if (existingModel instanceof ChameleonBakedModel) {
                LOGGER.warn("Tried to replace CamouflagedBakedModel twice");
            } else {
                ChameleonBakedModel customModel = new ChameleonBakedModel(existingModel);
                event.getModelRegistry().put(variantMRL, customModel);
            }
        }

        for (BlockState blockState : TTMContent.KEY_STONE_BLOCK.get().getStateDefinition().getPossibleStates()) {
            ModelResourceLocation variantMRL = BlockModelShapes.stateToModelLocation(blockState);
            IBakedModel existingModel = event.getModelRegistry().get(variantMRL);
            if (existingModel == null) {
                LOGGER.warn("Did not find the expected vanilla baked model(s) for blockCamouflage in registry");
            } else if (existingModel instanceof ChameleonBakedModel) {
                LOGGER.warn("Tried to replace CamouflagedBakedModel twice");
            } else {
                ChameleonBakedModel customModel = new ChameleonBakedModel(existingModel);
                event.getModelRegistry().put(variantMRL, customModel);
            }
        }

        for (BlockState blockState : TTMContent.CAMO_GLOWSTONE_BLOCK.get().getStateDefinition().getPossibleStates()) {
            ModelResourceLocation variantMRL = BlockModelShapes.stateToModelLocation(blockState);
            IBakedModel existingModel = event.getModelRegistry().get(variantMRL);
            if (existingModel == null) {
                LOGGER.warn("Did not find the expected vanilla baked model(s) for blockCamouflage in registry");
            } else if (existingModel instanceof ChameleonBakedModel) {
                LOGGER.warn("Tried to replace CamouflagedBakedModel twice");
            } else {
                ChameleonBakedModel customModel = new ChameleonBakedModel(existingModel);
                event.getModelRegistry().put(variantMRL, customModel);
            }
        }

        for (BlockState blockState : TTMContent.CAMO_SMOKER_BLOCK.get().getStateDefinition().getPossibleStates()) {
            ModelResourceLocation variantMRL = BlockModelShapes.stateToModelLocation(blockState);
            IBakedModel existingModel = event.getModelRegistry().get(variantMRL);
            if (existingModel == null) {
                LOGGER.warn("Did not find the expected vanilla baked model(s) for blockCamouflage in registry");
            } else if (existingModel instanceof ChameleonBakedModel) {
                LOGGER.warn("Tried to replace CamouflagedBakedModel twice");
            } else {
                ChameleonBakedModel customModel = new ChameleonBakedModel(existingModel);
                event.getModelRegistry().put(variantMRL, customModel);
            }
        }

        for (BlockState blockState : TTMContent.CAMO_FLUID_BLOCK.get().getStateDefinition().getPossibleStates()) {
            ModelResourceLocation variantMRL = BlockModelShapes.stateToModelLocation(blockState);
            IBakedModel existingModel = event.getModelRegistry().get(variantMRL);
            if (existingModel == null) {
                LOGGER.warn("Did not find the expected vanilla baked model(s) for blockCamouflage in registry");
            } else if (existingModel instanceof ChameleonBakedModel) {
                LOGGER.warn("Tried to replace CamouflagedBakedModel twice");
            } else {
                ChameleonBakedModel customModel = new ChameleonBakedModel(existingModel);
                event.getModelRegistry().put(variantMRL, customModel);
            }
        }

        for (BlockState blockState : TTMContent.CAMO_CHEST_BLOCK.get().getStateDefinition().getPossibleStates()) {
            ModelResourceLocation variantMRL = BlockModelShapes.stateToModelLocation(blockState);
            IBakedModel existingModel = event.getModelRegistry().get(variantMRL);
            if (existingModel == null) {
                LOGGER.warn("Did not find the expected vanilla baked model(s) for blockCamouflage in registry");
            } else if (existingModel instanceof ChameleonBakedModel) {
                LOGGER.warn("Tried to replace CamouflagedBakedModel twice");
            } else {
                ChameleonBakedModel customModel = new ChameleonBakedModel(existingModel);
                event.getModelRegistry().put(variantMRL, customModel);
            }
        }
    }
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
        }
        //endregion
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

    @SubscribeEvent
    public void onPlayerRenderPost(RenderPlayerEvent.Post evt) {
        final PlayerEntity player = evt.getPlayer();

        if (player instanceof RemoteClientPlayerEntity && player.getPose() == Pose.SLEEPING) {
            player.getSleepingPos().ifPresent(bedPos -> {
                MatrixStack matrixStack = evt.getMatrixStack();
                Block bed = player.level.getBlockState(bedPos).getBlock();
                if (bed instanceof SleepingBagBlock) {
                    matrixStack.translate(0.0f, 0.375F, 0.0f);
                }
            });
        }
    }
}
