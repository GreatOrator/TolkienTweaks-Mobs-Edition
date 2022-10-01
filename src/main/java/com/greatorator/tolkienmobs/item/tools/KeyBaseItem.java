package com.greatorator.tolkienmobs.item.tools;

import com.brandon3055.brandonscore.items.ItemBCore;
import com.brandon3055.brandonscore.utils.ItemNBTHelper;
import com.greatorator.tolkienmobs.client.gui.KeyBaseAccessScreen;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

public class KeyBaseItem extends ItemBCore {
    public KeyBaseItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        BlockPos blockpos = new BlockPos(player.getX(), player.getY(), player.getZ());
        BlockState blockstate = world.getBlockState(blockpos);

        if (player.isCreative() && player.isCrouching() && world.isClientSide) {
            openGui(stack, player);
            return ActionResult.consume(stack);
        }
        else if (!world.isClientSide && (!Objects.equals(getKey(stack), "")) || (!Objects.equals(getKey(stack), null))) {
            world.sendBlockUpdated(blockpos, blockstate, blockstate, 3);
        }

        return super.use(world, player, hand);
    }

    public void openGui(ItemStack stack, PlayerEntity player) {
        Minecraft.getInstance().setScreen(new KeyBaseAccessScreen(player, stack.getHoverName(), null, getKey(stack)));
    }

    public static boolean isEnabled(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, "KeyEffect", false);    }

    public static void toggleEnabled(ItemStack stack) {
        ItemNBTHelper.setBoolean(stack, "KeyEffect", !isEnabled(stack));
    }

    public void setKey(ItemStack stack, String key) {
        ItemNBTHelper.setString(stack, "KeyCode", key);
        if (!Objects.equals(getKey(stack), "") || getKey(stack) != null) {
            toggleEnabled(stack);
        }
    }

    public static String getKey(ItemStack stack) {
        return ItemNBTHelper.getString(stack, "KeyCode", "");
    }

    public void setShown(ItemStack stack, boolean show) {
        ItemNBTHelper.setBoolean(stack, "ShowCode", show);
    }

    public boolean getShown(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, "ShowCode", false);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return isEnabled(stack);
    }
}