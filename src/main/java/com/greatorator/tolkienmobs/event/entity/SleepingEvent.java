package com.greatorator.tolkienmobs.event.entity;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.block.SleepingBagBlock;
import com.greatorator.tolkienmobs.event.EventTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TolkienMobs.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SleepingEvent {
    @SubscribeEvent
    public void onPlayerSetSpawn(PlayerSetSpawnEvent evt) {
        Player player = evt.getPlayer();
        Level world = player.getCommandSenderWorld();
        BlockPos pos = evt.getNewSpawn();

        if (pos != null && !world.isClientSide()) {
            Block block = world.getBlockState(pos).getBlock();

            if (block instanceof SleepingBagBlock) {
                evt.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerSleep(PlayerSleepInBedEvent event) {
        Player player = event.getPlayer();
        BlockState state = player.getCommandSenderWorld().getBlockState(event.getPos());
        if (event.getResultStatus() == null && state.getBlock() instanceof SleepingBagBlock) {
            if (player instanceof ServerPlayer && player.isAlive()) {
                ServerPlayer serverPlayer = (ServerPlayer) player;
                if (!player.getCommandSenderWorld().isClientSide()) {
                    EventTriggers.SLEEP_IN_BAG.trigger(serverPlayer);
                }
            }
        }
    }
}
