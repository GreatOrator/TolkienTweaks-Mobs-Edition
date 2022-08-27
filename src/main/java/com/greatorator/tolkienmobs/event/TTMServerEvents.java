package com.greatorator.tolkienmobs.event;

import com.greatorator.tolkienmobs.block.BlockTTMSleepingBag;
import com.greatorator.tolkienmobs.entity.monster.EntityTTMGoblin;
import com.greatorator.tolkienmobs.event.entity.living.TTMGoblinEvent;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TTMServerEvents {

    public static TTMGoblinEvent.SummonAidEvent fireGoblinSummonAid(EntityTTMGoblin goblin, World world, int x, int y, int z, LivingEntity attacker, double summonChance)
    {
        TTMGoblinEvent.SummonAidEvent summonEvent = new TTMGoblinEvent.SummonAidEvent(goblin, world, x, y, z, attacker, summonChance);
        MinecraftForge.EVENT_BUS.post(summonEvent);
        return summonEvent;
    }

    @SubscribeEvent
    public static void onPlayerSleep(PlayerSleepInBedEvent event) {
        PlayerEntity player = event.getPlayer();
        BlockState state = player.getCommandSenderWorld().getBlockState(event.getPos());
        if (event.getResultStatus() == null && state.getBlock() instanceof BlockTTMSleepingBag) {
            if (player instanceof ServerPlayerEntity && player.isAlive()) {
                ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
                if (!player.getCommandSenderWorld().isClientSide()) {
                    TTMEventTriggers.SLEEP_IN_BAG.trigger(serverPlayer);
                }
            }
        }
    }
}
