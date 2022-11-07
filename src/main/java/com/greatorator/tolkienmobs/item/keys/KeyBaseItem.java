package com.greatorator.tolkienmobs.item.keys;

import com.brandon3055.brandonscore.blocks.BlockBCore;
import com.brandon3055.brandonscore.items.ItemBCore;
import com.brandon3055.brandonscore.utils.ItemNBTHelper;
import com.greatorator.tolkienmobs.container.gui.KeyBaseAccessScreen;
import com.greatorator.tolkienmobs.handler.interfaces.IKeyBase;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class KeyBaseItem extends ItemBCore implements IKeyBase {
    private final int keyUses;

    public KeyBaseItem(Properties properties, int uses) {
        super(properties);
        this.keyUses = uses;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        BlockPos blockpos = new BlockPos(player.getX(), player.getY(), player.getZ());
        BlockState blockstate = world.getBlockState(blockpos);
        Block block = blockstate.getBlock();

        if (player.isCreative() && player.isCrouching() && world.isClientSide) {
            openGui(stack, player);
            return InteractionResultHolder.consume(stack);
        }
        else if (!world.isClientSide && (!Objects.equals(getCode(stack), "")) || (!Objects.equals(getCode(stack), null)) && block instanceof BlockBCore) {
            if (player != null && getUses(stack) >= 0) {
                int uses = getUses(stack);
                world.sendBlockUpdated(blockpos, blockstate, blockstate, 3);
                if (uses == 0) {
                    stack.shrink(1);
                    world.playSound((Player) null, blockpos, SoundEvents.ITEM_BREAK, SoundSource.BLOCKS, 0.3F, 0.6F);
                    player.sendMessage(new TranslatableComponent(MODID + ".msg.key_used").withStyle(ChatFormatting.RED), Util.NIL_UUID);
                }
                uses--;
                setUses(stack, uses);
            }
        }
        return super.use(world, player, hand);
    }

    public void openGui(ItemStack stack, Player player) {
        Minecraft.getInstance().setScreen(new KeyBaseAccessScreen(player, stack.getHoverName(), null, getCode(stack), getUses(stack)));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        if (getUses(stack) > 0) {
            tooltip.add(new TranslatableComponent(getDescriptionId() + ".lore").withStyle(ChatFormatting.DARK_AQUA).append(String.valueOf(getUses(stack))));
        }
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return isEnabled(stack);
    }

    public static boolean isEnabled(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, "KeyEffect", false);
    }

    public static String getCode(ItemStack stack) {
        return ItemNBTHelper.getString(stack, "KeyCode", "");
    }

    public static int getUses(ItemStack stack) {
        return ItemNBTHelper.getInteger(stack, "KeyUses", -1);
    }

    public static void toggleEnabled(ItemStack stack) {
        ItemNBTHelper.setBoolean(stack, "KeyEffect", !isEnabled(stack));
    }

    public static void setUses(ItemStack stack, int uses) {
        ItemNBTHelper.setInteger(stack, "KeyUses", uses);
    }

    public static void setCode(ItemStack stack, String key) {
        ItemNBTHelper.setString(stack, "KeyCode", key);
        if (!Objects.equals(getCode(stack), "") || getCode(stack) != null) {
            toggleEnabled(stack);
        }
    }

    @Override
    public void setName(ItemStack stack, String key) {
    }
}