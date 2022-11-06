package com.greatorator.tolkienmobs.event.server;

import com.greatorator.tolkienmobs.block.SleepingBagBlock;
import com.greatorator.tolkienmobs.event.EventTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ServerEvents {

//    public static GoblinEvent.SummonAidEvent fireGoblinSummonAid(GoblinEntity goblin, Level world, int x, int y, int z, LivingEntity attacker, double summonChance)
//    {
//        GoblinEvent.SummonAidEvent summonEvent = new GoblinEvent.SummonAidEvent(goblin, world, x, y, z, attacker, summonChance);
//        MinecraftForge.EVENT_BUS.post(summonEvent);
//        return summonEvent;
//    }
//
//    public static SpiderEvent.SummonAidEvent fireSpiderSummonAid(ShelobEntity spider, Level world, int x, int y, int z, LivingEntity attacker, double summonChance)
//    {
//        SpiderEvent.SummonAidEvent summonEvent = new SpiderEvent.SummonAidEvent(spider, world, x, y, z, attacker, summonChance);
//        MinecraftForge.EVENT_BUS.post(summonEvent);
//        return summonEvent;
//    }

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
