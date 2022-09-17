package com.greatorator.tolkienmobs.item.tools;

import com.brandon3055.brandonscore.items.ItemBCore;
import com.brandon3055.brandonscore.utils.ItemNBTHelper;
import com.greatorator.tolkienmobs.client.gui.GoldKeyAccessScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class GoldKeyItem extends ItemBCore {
    public GoldKeyItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player.isCreative() && player.isShiftKeyDown() && world.isClientSide) {
            openGui(stack, player);
            return ActionResult.consume(stack);
        }
        else if (!world.isClientSide && player.isCreative() && player.isCrouching()) {
//            stack.getTag().remove("playerUUID");
        }

        return super.use(world, player, hand);
    }

    private void openGui(ItemStack stack, PlayerEntity player) {
        Minecraft.getInstance().setScreen(new GoldKeyAccessScreen(player, stack.getHoverName(), null, getKey(stack)));
    }
//
//    @OnlyIn(Dist.CLIENT)
//    @Override
//    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
//        PlayerEntity player = TolkienTweaks.proxy.getPlayer();
//        if (stack.getItemDamage() != 1) {
//            if (getShown(stack)) {
//                if (player.isCreative()) {
//                    tooltip.add(TextFormatting.GREEN + "Key visible to non-creative players");
//                }
//                tooltip.add(TextFormatting.GOLD + getKey(stack));
//            }
//            else if (player != null && player.isCreative()) {
//                tooltip.add(TextFormatting.RED + "Key hidden from non-creative players");
//                tooltip.add(TextFormatting.RED + getKey(stack));
//            }
//            if (ItemNBTUtils.hasKey(stack, "playerUUID") && player != null && player.isCreative()) {
//                tooltip.add(TextFormatting.GOLD + "OwnerID: " + ItemNBTUtils.getString(stack, "playerUUID"));
//            }
//        }
//        if (stack.getItemDamage() == 1) {
//            tooltip.add(TextFormatting.GOLD + "Master Key");
//        }
//
//
//        super.addInformation(stack, worldIn, tooltip, flagIn);
//    }
//
    public void setKey(ItemStack stack, String key) {
        ItemNBTHelper.setString(stack, "KeyCode", key);
    }

    public String getKey(ItemStack stack) {
        return ItemNBTHelper.getString(stack, "KeyCode", "");
    }

    public void setShown(ItemStack stack, boolean show) {
        ItemNBTHelper.setBoolean(stack, "ShowCode", show);
    }

    public boolean getShown(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, "ShowCode", false);
    }
}
