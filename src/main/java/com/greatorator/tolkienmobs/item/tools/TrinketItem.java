package com.greatorator.tolkienmobs.item.tools;

import com.brandon3055.brandonscore.api.TimeKeeper;
import com.brandon3055.brandonscore.utils.ItemNBTHelper;
import com.greatorator.tolkienmobs.TTMConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

/**
 * Created by brandon3055 on 13/10/2021
 */
public class TrinketItem extends Item {

    public TrinketItem(Properties properties) {
        super(properties);
    }

    @Override
    public void fillItemCategory(@Nonnull CreativeModeTab group, @Nonnull NonNullList<ItemStack> items) {
        if (this.allowdedIn(group)) {
            for (MobEffect p : TTMConfig.potionArray) {
                if (p != null) {
                    items.add(getTrinketForEffect(p));
                }
            }
        }
    }

    public ItemStack getTrinketForEffect(MobEffect effect) {
        ItemStack stack = new ItemStack(this);
        PotionUtils.setCustomEffects(stack, Collections.singleton(new MobEffectInstance(effect, 60*20, 0, false, false)));
        return stack;
    }

    @Nonnull
    @Override
    public Component getName(@Nonnull ItemStack stack) {
        List<MobEffectInstance> effects = getEffects(stack);
        StringBuilder trinketName = new StringBuilder();
        if (effects.isEmpty()) {
            return super.getName(stack);
        }

        Component effectName = effects.get(0).getEffect().getDisplayName();
        Component itemName = (TranslatableComponent) super.getName(stack);

        trinketName.append(itemName);
        trinketName.append(" ");
        trinketName.append(effectName);
        return new TextComponent(trinketName.toString());
    }

    public static List<MobEffectInstance> getEffects(ItemStack stack) {
        if (stack.isEmpty()) {
            return Collections.emptyList();
        }
        return PotionUtils.getCustomEffects(stack);
    }

    @Nonnull
    @Override
    public InteractionResultHolder<ItemStack> use(@Nonnull Level worldIn, Player player, @Nonnull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player.isShiftKeyDown() && !getEffects(stack).isEmpty()) {
            toggleEnabled(stack);
            if (!isEnabled(stack)) {
                List<MobEffectInstance> effects = getEffects(stack);
                effects.forEach(effect -> player.removeEffect(effect.getEffect()));
            }
        }
        return super.use(worldIn, player, hand);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean isFoil(@Nonnull ItemStack stack) {
        return isEnabled(stack);
    }

    public static boolean isEnabled(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, "is_active", false);
    }

    public static void toggleEnabled(ItemStack stack) {
        ItemNBTHelper.setBoolean(stack, "is_active", !isEnabled(stack));
    }

    @Override
    public void inventoryTick(@Nonnull ItemStack stack, @Nonnull Level world, @Nonnull Entity entity, int slot, boolean hotbar) {
        updateTrinket(stack, entity);
    }

    private void updateTrinket(ItemStack stack, Entity entity) {
        Player player = (Player) entity;
        if (isEnabled(stack) && TimeKeeper.getServerTick() % 10 == 0) {
            List<MobEffectInstance> effects = getEffects(stack);
            for (MobEffectInstance effect : effects) {
                MobEffectInstance active = player.getEffect(effect.getEffect());
                if (active == null || active.getDuration() < 30 * 20){
                    player.addEffect(effect);
                }
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@Nonnull ItemStack stack, @Nullable Level worldIn, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        PotionUtils.addPotionTooltip(stack, tooltip, 1.0F);
        tooltip.add(new TranslatableComponent("item_info.tolkienmobs.sneak_right_click_activate").withStyle(ChatFormatting.DARK_PURPLE));
    }
}
