package com.greatorator.tolkienmobs.handler;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.Arrays;

public class PlayerHandler {

    public static boolean hasBreakPermission(ServerPlayer player, BlockPos pos) {
        return hasEditPermission(player, pos) && ForgeHooks.onBlockBreakEvent(player.getCommandSenderWorld(), player.gameMode.getGameModeForPlayer(), player, pos) != -1;
    }

    public static boolean hasEditPermission(ServerPlayer player, BlockPos pos) {
        if (ServerLifecycleHooks.getCurrentServer().isUnderSpawnProtection((ServerLevel) player.getCommandSenderWorld(), pos, player)) {
            return false;
        }
        return Arrays.stream(Direction.values()).allMatch(e -> player.mayUseItemAt(pos, e, ItemStack.EMPTY));
    }
}
