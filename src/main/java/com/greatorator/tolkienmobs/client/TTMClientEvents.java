package com.greatorator.tolkienmobs.client;

import com.greatorator.tolkienmobs.datagen.EnchantmentGenerator;
import com.greatorator.tolkienmobs.datagen.PotionGenerator;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by brandon3055 on 20/02/19.
 */
public class TTMClientEvents {

    public static List<UUID> playersWithUphillStep = new ArrayList<>();
    public static List<UUID> playersWithExtraHealth = new ArrayList<>();
    public static List<UUID> playersWithHardStance = new ArrayList<>();
    public static List<UUID> playersCoweringInFear = new ArrayList<>();

    public static void livingUpdate(LivingEvent.LivingUpdateEvent event) {
        LivingEntity entity = event.getEntityLiving();
        PlayerEntity player = Minecraft.getInstance().player;
        Entity render = Minecraft.getInstance().getRenderViewEntity();
//        EffectInstance effect = player.getActivePotionEffect(PotionGenerator.SLEEPNESIA.get());
        //region/*---------------- Sleepnesia -----------------*/
//        if (effect != null) {
//            RenderSystem.pushMatrix();
//            RenderSystem.rotatef(90, 0.0F, 1.0F, 0.0F);
//            RenderSystem.rotatef(90, 0.0F, 0.0F, 1.0F);
//            RenderSystem.rotatef(270.0F, 0.0F, 1.0F, 0.0F);
//        }
//
//        PlayerEntity player = Minecraft.getInstance().player;
//        EffectInstance effect = player.getActivePotionEffect(PotionGenerator.SLEEPNESIA.get());
//
//        if (effect != null) {
//            RenderSystem.popMatrix();
//        }

//        if (effect != null) {
//            if (entity != null) {
//                float partialTicks = Minecraft.getInstance().getRenderPartialTicks();
//
//                RenderSystem.translatef(0.0F, 1.2F, 0.0F);
//                RenderSystem.rotatef(render.prevRotationYaw + (render.rotationYaw - render.prevRotationYaw) * partialTicks + 180.0F, 0.0F, -1.0F, 0.0F);
//                RenderSystem.rotatef(render.prevRotationPitch + (render.rotationPitch - render.prevRotationPitch) * partialTicks, -1.0F, 0.0F, 0.0F);
//            }
//        }

//        if (effect != null) {
//            event.setCanceled(true);
//        }
        //endregion
        //region/*---------------- Elvish Nimbleness -----------------*/
        if (entity.world.isRemote) {
            boolean highStepListed = playersWithUphillStep.contains(entity.getUniqueID()) && entity.stepHeight >= 1f;
            boolean hasHighStep = entity.getActivePotionEffect(PotionGenerator.ELF_NIMBLENESS.get()) != null;

            if (hasHighStep && !highStepListed) {
                playersWithUphillStep.add(entity.getUniqueID());
                entity.stepHeight = 1.0625f;
            }

            if (!hasHighStep && highStepListed) {
                playersWithUphillStep.remove(entity.getUniqueID());
                entity.stepHeight = 0.6F;
            }
        }
        //endregion
        //region/*---------------- Elvish Longevity -----------------*/
        if (entity.world.isRemote) {
            int level = EnchantmentHelper.getMaxEnchantmentLevel(EnchantmentGenerator.ELVEN_LONGEVITY.get(), entity);
            boolean extraHealthListed = playersWithExtraHealth.contains(entity.getUniqueID());
            boolean hasExtraHealth = level > 0;

            if (hasExtraHealth && entity.getAbsorptionAmount() == 0 || !extraHealthListed) {
                playersWithExtraHealth.add(entity.getUniqueID());
                entity.setAbsorptionAmount(entity.getAbsorptionAmount() + (float) (5 * (level + 1)));
            }

            if (!hasExtraHealth && extraHealthListed) {
                playersWithExtraHealth.add(entity.getUniqueID());
                entity.setAbsorptionAmount(entity.getAbsorptionAmount() - (float) (5 * (level + 1)));
            }
        }
        //endregion
        //region/*---------------- Gondor Resolve -----------------*/
        if (entity.world.isRemote) {
            int level = EnchantmentHelper.getMaxEnchantmentLevel(EnchantmentGenerator.GONDOR_RESOLVE.get(), entity);
            ModifiableAttributeInstance battleResolve = entity.getAttribute(Attributes.KNOCKBACK_RESISTANCE);
            AttributeModifier gondorResolve = new AttributeModifier(UUID.randomUUID(), "GondorResolve", 0.25D * level, AttributeModifier.Operation.ADDITION);
            boolean hardStanceListed = playersWithHardStance.contains(entity.getUniqueID());
            boolean hasHardStance = level > 0;

            if (hasHardStance && !hardStanceListed) {
                playersWithHardStance.add(entity.getUniqueID());
                assert battleResolve != null;
                battleResolve.applyPersistentModifier(gondorResolve);
            }

            if (!hasHardStance && hardStanceListed) {
                playersWithHardStance.add(entity.getUniqueID());
                assert battleResolve != null;
                battleResolve.removePersistentModifier(UUID.fromString("GondorResolve"));
            }
        }
        //endregion
        //region/*---------------- Crippling Terror -----------------*/
//        if (entity.world.isRemote) {
//            boolean terrorListed = playersCoweringInFear.contains(entity.getUniqueID()) && entity.getAttributeValue(Attributes.MOVEMENT_SPEED) >= 1f;
//            boolean hasTerror = entity.getActivePotionEffect(PotionGenerator.PARALYSING_FEAR.get()) != null;
//
//            if (hasTerror && !terrorListed) {
//                playersCoweringInFear.add(entity.getUniqueID());
//                LOGGER.info("The terror sets in...");
//                entity.setVelocity(-10D, -10D, -10D);
//                entity.velocityChanged = true;
//                entity.moveVertical = -10;
//                entity.moveStrafing = -10;
//                entity.setVelocity(0D, 0D, 0D);
//            }
//
//            if (!hasTerror && terrorListed) {
//                playersCoweringInFear.remove(entity.getUniqueID());
//                LOGGER.info("The terror wanes");
//                entity.setVelocity(10D, 10D, 10D);
//                entity.velocityChanged = true;
//                entity.moveVertical = 10;
//                entity.moveStrafing = 10;
//            }
//        }
//        //endregion
    }

//    @SubscribeEvent
//    @SideOnly(Side.CLIENT)
//    public void itemColourEvent(ColorHandlerEvent.Item event) {
//
//        event.getItemColors().registerItemColorHandler((stack, tintIndex) -> {
//            if (tintIndex < 1) {
//                return -1;
//            }
//            else {
//                Potion potion = ItemTrinketRing.getPotion(stack);
//                if (potion != null) {
//                    return potion.getLiquidColor();
//                }
//            }
//            return -1;
//        }, TTMFeatures.TRINKET_RING, TTMFeatures.TRINKET_CHARM, TTMFeatures.TRINKET_BELT, TTMFeatures.TRINKET_AMULET);
//    }
//
//    @SubscribeEvent(priority = EventPriority.HIGHEST)
//    @SideOnly(Side.CLIENT)
//    public void renderAir(RenderGameOverlayEvent event)
//    {
//        Minecraft mc = Minecraft.getMinecraft();
//        if(event.getType() == RenderGameOverlayEvent.ElementType.AIR) {
//
//            LivingEntity player = (LivingEntity)mc.getRenderViewEntity();
//            String drown = PotionTTMDrowning.NAME;
//
//            if (player != null && drown != null && PotionInit.ELEMENTAL_DROWNING != null && player.isPotionActive(PotionInit.ELEMENTAL_DROWNING)) {
//                event.setCanceled(true);
//
//                if(!player.isInsideOfMaterial(Material.WATER)) {
//                    int air = player.getEntityData().getInteger(PotionTTMDrowning.TAG_NAME);
//
//                    mc.mcProfiler.startSection("air");
//                    GlStateManager.enableBlend();
//                    int width = event.getResolution().getScaledWidth();
//                    int height = event.getResolution().getScaledHeight();
//                    int left = width / 2 + 91;
//                    int top = height - GuiIngameForge.right_height;
//
//                    int full = MathHelper.ceil((double)(air - 2) * 10.0D / 300.0D);
//                    int partial = MathHelper.ceil((double)air * 10.0D / 300.0D) - full;
//
//                    for (int i = 0; i < full + partial; ++i)
//                    {
//                        mc.ingameGUI.drawTexturedModalRect(left - i * 8 - 9, top, (i < full ? 16 : 25), 18, 9, 9);
//                    }
//                    GuiIngameForge.right_height += 10;
//
//
//                    GlStateManager.disableBlend();
//                    mc.mcProfiler.endSection();
//                }
//            }
//        }
//    }
//
////    @SubscribeEvent
////    @SideOnly(Side.CLIENT)
////    public void stepAssistEvent(LivingEvent.LivingUpdateEvent event) {
////        LivingEntity player = event.getEntityLiving();
////
////        if(player.world.isRemote || !player.isEntityAlive()) return;
////
////        if (!player.isPotionActive(PotionInit.ELF_NIMBLENESS)) {
////            player.stepHeight = 0.6F;
////        }
////    }
//
//    @SubscribeEvent
//    public void onEntityLiving(LivingEvent.LivingUpdateEvent event){
//        LivingEntity player = event.getEntityLiving();
//
//        if(player.world.isRemote || !player.isEntityAlive()) return;
//
//        if(player.isInsideOfMaterial(Material.WATER) && player.ticksExisted % 20 == 0 && player instanceof ServerPlayerEntity) {
//            Biome biome = player.world.getBiome(player.getPosition());
//
//            if(biome == BiomeInit.MIRKWOOD) {
//
//                player.addPotionEffect(new PotionEffect(PotionInit.SLEEPNESIA, 600, 8));
//            }
//        }
//    }
//
//    @SubscribeEvent
//    @SideOnly(Side.CLIENT)
//    public void event(RenderPlayerEvent.Pre event) {
//        PlayerEntity player = Minecraft.getMinecraft().player;
//        PotionEffect effect = player.getActivePotionEffect(PotionInit.SLEEPNESIA);
//
//        if (effect != null) {
//            GlStateManager.pushMatrix();
//            GlStateManager.rotate(90, 0.0F, 1.0F, 0.0F);
//            GlStateManager.rotate(90, 0.0F, 0.0F, 1.0F);
//            GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
//        }
//    }
//
//    @SubscribeEvent
//    @SideOnly(Side.CLIENT)
//    public void event(RenderPlayerEvent.Post event) {
//        PlayerEntity player = Minecraft.getMinecraft().player;
//        PotionEffect effect = player.getActivePotionEffect(PotionInit.SLEEPNESIA);
//
//        if (effect != null) {
//            GlStateManager.popMatrix();
//        }
//    }
//
//    @SubscribeEvent
//    @SideOnly(Side.CLIENT)
//    public void event(EntityViewRenderEvent.CameraSetup event) {
//        PlayerEntity player = Minecraft.getMinecraft().player;
//        PotionEffect effect = player.getActivePotionEffect(PotionInit.SLEEPNESIA);
//
//        if (effect != null) {
//            Entity entity = Minecraft.getMinecraft().getRenderViewEntity();
//            if (entity != null) {
//                float partialTicks = Minecraft.getMinecraft().getRenderPartialTicks();
//
//                GlStateManager.translate(0.0F, 1.2F, 0.0F);
//                GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks + 180.0F, 0.0F, -1.0F, 0.0F);
//                GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, -1.0F, 0.0F, 0.0F);
//            }
//        }
//    }
//
//    @SubscribeEvent
//    @SideOnly(Side.CLIENT)
//    public void event(RenderHandEvent event) {
//        PlayerEntity player = Minecraft.getMinecraft().player;
//        PotionEffect effect = player.getActivePotionEffect(PotionInit.SLEEPNESIA);
//
//        if (effect != null) {
//            event.setCanceled(true);
//        }
//    }
}