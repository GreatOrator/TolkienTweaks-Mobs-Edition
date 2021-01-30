//package com.greatorator.tolkienmobs.item.potiontypes;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.handler.TTMPotion;
//import com.greatorator.tolkienmobs.network.TTMStoCMessage;
//import com.greatorator.tolkienmobs.network.TTMPacketClient;
//import io.netty.buffer.Unpooled;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.player.ServerPlayerEntity;
//import net.minecraft.network.PacketBuffer;
//
//public class PotionTTMFlying extends TTMPotion {
//    public static final String NAME = "elemental_flight";
//    public static PotionTTMFlying instance = null;
//
//    public static double launchSpeed = 1.0;
//
//    public PotionTTMFlying() {
//        super(NAME, true, 6498056, 4);
//        instance = this;
//    }
//
//    @Override
//    public boolean isInstant() {
//        return true;
//    }
//
//    @Override
//    public void performEffect(LivingEntity entity, int amplifier) {
//
//        entity.motionY = (amplifier+1) * launchSpeed;
//
//        if(entity instanceof ServerPlayerEntity) {
//            PacketBuffer out = new PacketBuffer(Unpooled.buffer());
//
//            out.writeInt(TTMPacketClient.LIFT_UP);
//            out.writeInt(amplifier+1);
//
//            TTMStoCMessage packet = new TTMStoCMessage(out);
//            TolkienMobs.networkWrapper.sendTo(packet, (ServerPlayerEntity) entity);
//        }
//    }
//}
