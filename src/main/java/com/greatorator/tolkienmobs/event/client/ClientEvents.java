package com.greatorator.tolkienmobs.event.client;

import com.brandon3055.brandonscore.api.render.GuiHelper;
import com.greatorator.tolkienmobs.block.SleepingBagBlock;
import com.greatorator.tolkienmobs.block.function.ChameleonBakedModel;
import com.greatorator.tolkienmobs.init.TolkienBlocks;
import com.greatorator.tolkienmobs.init.TolkienPotions;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
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
    public static Set<UUID> dragonRiders = new HashSet<>();

    private static AttributeModifier sleepTight = new AttributeModifier(UUID.randomUUID(), "SleepyTime", -10F, AttributeModifier.Operation.ADDITION);

    public static void onModelBakeEvent(ModelBakeEvent event) {
        for (BlockState blockState : TolkienBlocks.CHAMELEON_BLOCK.get().getStateDefinition().getPossibleStates()) {
            ModelResourceLocation variantMRL = BlockModelShaper.stateToModelLocation(blockState);
            BakedModel existingModel = event.getModelRegistry().get(variantMRL);
            if (existingModel == null) {
                LOGGER.warn("Did not find the expected vanilla baked model(s) for blockCamouflage in registry");
            } else if (existingModel instanceof ChameleonBakedModel) {
                LOGGER.warn("Tried to replace CamouflagedBakedModel twice");
            } else {
                ChameleonBakedModel customModel = new ChameleonBakedModel(existingModel);
                event.getModelRegistry().put(variantMRL, customModel);
            }
        }

        for (BlockState blockState : TolkienBlocks.KEY_STONE_BLOCK.get().getStateDefinition().getPossibleStates()) {
            ModelResourceLocation variantMRL = BlockModelShaper.stateToModelLocation(blockState);
            BakedModel existingModel = event.getModelRegistry().get(variantMRL);
            if (existingModel == null) {
                LOGGER.warn("Did not find the expected vanilla baked model(s) for blockCamouflage in registry");
            } else if (existingModel instanceof ChameleonBakedModel) {
                LOGGER.warn("Tried to replace CamouflagedBakedModel twice");
            } else {
                ChameleonBakedModel customModel = new ChameleonBakedModel(existingModel);
                event.getModelRegistry().put(variantMRL, customModel);
            }
        }

        for (BlockState blockState : TolkienBlocks.CAMO_GLOWSTONE_BLOCK.get().getStateDefinition().getPossibleStates()) {
            ModelResourceLocation variantMRL = BlockModelShaper.stateToModelLocation(blockState);
            BakedModel existingModel = event.getModelRegistry().get(variantMRL);
            if (existingModel == null) {
                LOGGER.warn("Did not find the expected vanilla baked model(s) for blockCamouflage in registry");
            } else if (existingModel instanceof ChameleonBakedModel) {
                LOGGER.warn("Tried to replace CamouflagedBakedModel twice");
            } else {
                ChameleonBakedModel customModel = new ChameleonBakedModel(existingModel);
                event.getModelRegistry().put(variantMRL, customModel);
            }
        }

        for (BlockState blockState : TolkienBlocks.CAMO_SMOKER_BLOCK.get().getStateDefinition().getPossibleStates()) {
            ModelResourceLocation variantMRL = BlockModelShaper.stateToModelLocation(blockState);
            BakedModel existingModel = event.getModelRegistry().get(variantMRL);
            if (existingModel == null) {
                LOGGER.warn("Did not find the expected vanilla baked model(s) for blockCamouflage in registry");
            } else if (existingModel instanceof ChameleonBakedModel) {
                LOGGER.warn("Tried to replace CamouflagedBakedModel twice");
            } else {
                ChameleonBakedModel customModel = new ChameleonBakedModel(existingModel);
                event.getModelRegistry().put(variantMRL, customModel);
            }
        }

        for (BlockState blockState : TolkienBlocks.CAMO_FLUID_BLOCK.get().getStateDefinition().getPossibleStates()) {
            ModelResourceLocation variantMRL = BlockModelShaper.stateToModelLocation(blockState);
            BakedModel existingModel = event.getModelRegistry().get(variantMRL);
            if (existingModel == null) {
                LOGGER.warn("Did not find the expected vanilla baked model(s) for blockCamouflage in registry");
            } else if (existingModel instanceof ChameleonBakedModel) {
                LOGGER.warn("Tried to replace CamouflagedBakedModel twice");
            } else {
                ChameleonBakedModel customModel = new ChameleonBakedModel(existingModel);
                event.getModelRegistry().put(variantMRL, customModel);
            }
        }

        for (BlockState blockState : TolkienBlocks.CAMO_CHEST_BLOCK.get().getStateDefinition().getPossibleStates()) {
            ModelResourceLocation variantMRL = BlockModelShaper.stateToModelLocation(blockState);
            BakedModel existingModel = event.getModelRegistry().get(variantMRL);
            if (existingModel == null) {
                LOGGER.warn("Did not find the expected vanilla baked model(s) for blockCamouflage in registry");
            } else if (existingModel instanceof ChameleonBakedModel) {
                LOGGER.warn("Tried to replace CamouflagedBakedModel twice");
            } else {
                ChameleonBakedModel customModel = new ChameleonBakedModel(existingModel);
                event.getModelRegistry().put(variantMRL, customModel);
            }
        }

        for (BlockState blockState : TolkienBlocks.CAMO_SPAWNER_BLOCK.get().getStateDefinition().getPossibleStates()) {
            ModelResourceLocation variantMRL = BlockModelShaper.stateToModelLocation(blockState);
            BakedModel existingModel = event.getModelRegistry().get(variantMRL);
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
        Player player = Minecraft.getInstance().player;
        Entity render = Minecraft.getInstance().getCameraEntity();

        //region/*---------------- Elvish Nimbleness -----------------*/
        if (entity.level.isClientSide) {
            boolean highStepListed = playersWithUphillStep.contains(entity.getUUID()) && entity.maxUpStep >= 1f;
            boolean hasHighStep = entity.getEffect(TolkienPotions.ELF_NIMBLENESS.get()) != null;

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
        boolean hasGoneToSleep = entity.getEffect(TolkienPotions.SLEEPNESIA.get()) != null;
        AttributeInstance nightyNight = entity.getAttribute(Attributes.MOVEMENT_SPEED);

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
            Player player = Minecraft.getInstance().player;
            boolean sleepEffectActive = player.getEffect(TolkienPotions.SLEEPNESIA.get()) != null;
            boolean terrorEffectActive = player.getEffect(TolkienPotions.PARALYSING_FEAR.get()) != null;
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
                PoseStack matrixStack = event.getMatrixStack();
                int darkHex = (int) ((double) darkValue * darkness);
                int screenHeight = event.getWindow().getScreenHeight();
                int screenWidth = event.getWindow().getScreenWidth();
                int effectColour = 0x000000;
                MultiBufferSource.BufferSource getter = Minecraft.getInstance().renderBuffers().bufferSource();
                GuiHelper.drawRect(getter, matrixStack, 0, 0, screenWidth, screenHeight, effectColour | darkHex << 24);
                getter.endBatch();
            }
        }
    }

    @SubscribeEvent
    public void onPlayerRenderPost(RenderPlayerEvent.Post evt) {
        final Player player = evt.getPlayer();

        if (player instanceof ClientGamePacketListener && player.getPose() == Pose.SLEEPING) {
            player.getSleepingPos().ifPresent(bedPos -> {
                PoseStack matrixStack = evt.getPoseStack();
                Block bed = player.level.getBlockState(bedPos).getBlock();
                if (bed instanceof SleepingBagBlock) {
                    matrixStack.translate(0.0f, 0.375F, 0.0f);
                }
            });
        }
    }

    public static double getViewCollision(double wanted, Entity entity) {

        Camera info = getClient().gameRenderer.getMainCamera();
        Vec3 position = info.getPosition();
        Vector3f forwards = info.getLookVector();
        for (int i = 0; i < 8; ++i)
        {
            float f = (float) ((i & 1) * 2 - 1);
            float f1 = (float) ((i >> 1 & 1) * 2 - 1);
            float f2 = (float) ((i >> 2 & 1) * 2 - 1);
            f = f * 0.1F;
            f1 = f1 * 0.1F;
            f2 = f2 * 0.1F;
            Vec3 vector3d = position.add(f, f1, f2);
            Vec3 vector3d1 = new Vec3(position.x - forwards.x() * wanted + f + f2, position.y - forwards.y() * wanted + f1, position.z - forwards.z() * wanted + f2);
            HitResult rtr = entity.level.clip(new ClipContext(vector3d, vector3d1, ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, entity));
            if (rtr.getType() != HitResult.Type.MISS)
            {
                double distance = rtr.getLocation().distanceTo(position);
                if (distance < wanted) wanted = distance;
            }
        }
        return wanted;
    }

    public static Minecraft getClient() {
        return Minecraft.getInstance();
    }
}
