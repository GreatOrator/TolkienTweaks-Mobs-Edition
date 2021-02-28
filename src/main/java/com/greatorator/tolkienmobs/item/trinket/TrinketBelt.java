package com.greatorator.tolkienmobs.item.trinket;

import com.brandon3055.brandonscore.utils.ItemNBTHelper;
import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TrinketBelt extends PotionItem {
    private static final String TAG_POTION_EFFECT = "effect";

    public TrinketBelt(Properties properties) {
        super(properties);
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.isInGroup(group)) {
            for(Effect p : TTMConfig.effectArray) {
                if (p != null) {
                    items.add(getTrinketForPotion(p));
                }
            }
        }
    }

    public static ItemStack getTrinketForPotion(Effect potion) {
        String id = potion.getRegistryName().toString();
        ItemStack stack = new ItemStack(TTMContent.TRINKET_BELT.get());
        ItemNBTHelper.setString(stack, TAG_POTION_EFFECT, id);
        return stack;
    }

    public static Potion getPotion(ItemStack stack) {
        if(stack == null)
            return null;

        String effect = ItemNBTHelper.getString(stack, TAG_POTION_EFFECT, "Nothingness");
        if(effect.isEmpty())
            return null;

        return Potion.getPotionTypeForName(effect);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (player.isSneaking() && (getPotion(stack) != null)) {
            toggleEnabled(stack);
        }
        return super.onItemRightClick(world, player, hand);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean hasEffect(ItemStack stack) {
        return isEnabled(stack);
    }

    public static boolean isEnabled(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, "IsActive", false);
    }

    public static void toggleEnabled(ItemStack stack) {
        ItemNBTHelper.setBoolean(stack, "IsActive", !isEnabled(stack));
    }

    @SuppressWarnings("unchecked")
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean hotbar) {
        updateTrinket(stack, entity);
    }

    private void updateTrinket(ItemStack stack, Entity entity) {
        PlayerEntity player = (PlayerEntity) entity;
        Effect pe = (Effect) PotionUtils.getEffectsFromStack(stack);

        if(isEnabled(stack)){
            player.addPotionEffect(new EffectInstance(new EffectInstance(pe,2400,3,false,false)));
        }else {
            player.removePotionEffect(pe);
        }
    }
}