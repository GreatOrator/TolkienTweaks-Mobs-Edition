//package com.greatorator.tolkienmobs.utils;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.init.PotionInit;
//import net.minecraft.client.Minecraft;
//import net.minecraft.entity.SharedMonsterAttributes;
//import net.minecraft.entity.ai.attributes.AttributeModifier;
//import net.minecraft.entity.ai.attributes.IAttribute;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.potion.PotionEffect;
//import net.minecraftforge.event.entity.player.PlayerInteractEvent;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//import net.minecraftforge.fml.common.gameevent.TickEvent;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//import java.util.UUID;
//
//@SuppressWarnings("unused")
//@Mod.EventBusSubscriber(modid= TolkienMobs.MODID)
//public class TTMEffectEvents {
//    public static UUID sleepUUID = UUID.fromString("0ea6ce8e-d2e8-11e5-ab30-628725370761");
//
//    @SubscribeEvent
//    public void event(TickEvent.PlayerTickEvent event) {
//        IAttribute speedAttr = SharedMonsterAttributes.MOVEMENT_SPEED;
//        PotionEffect effect = event.player.getActivePotionEffect(PotionInit.SLEEPNESIA);
//
//        if (effect != null) { //If you want to apply your effect
//            if (event.player.getEntityAttribute(speedAttr).getModifier(sleepUUID) == null) {
//                event.player.getEntityAttribute(speedAttr).applyModifier(new AttributeModifier(sleepUUID, speedAttr.getName(), -10, 0));
//            }
//        }
//        else if (event.player.getEntityAttribute(speedAttr).getModifier(sleepUUID) != null) {
//            event.player.getEntityAttribute(speedAttr).removeModifier(sleepUUID);
//        }
//    }
//
//    @SubscribeEvent
//    @SideOnly(Side.CLIENT)
//    public void event(PlayerInteractEvent event) {
//        PlayerEntity player = Minecraft.getMinecraft().player;
//        PotionEffect effect = player.getActivePotionEffect(PotionInit.SLEEPNESIA);
//
//        if (effect != null) {
//            if (event.isCancelable()) {
//                event.setCanceled(true);
//            }
//        }
//    }
//}