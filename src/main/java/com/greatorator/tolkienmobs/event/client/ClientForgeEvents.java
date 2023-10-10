package com.greatorator.tolkienmobs.event.client;

import com.greatorator.tolkienmobs.init.TolkienKeys;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public final class ClientForgeEvents {
    private ClientForgeEvents() {
    }

    public static Minecraft getClient() {
        return Minecraft.getInstance();
    }

    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event) {
        final var player = getClient().player;
        if (TolkienKeys.keyMapping.isDown()) {
            player.displayClientMessage(new TextComponent("The key worked!"), false);
        }
    }
}
