package com.greatorator.tolkienmobs.item.trinket;

import com.brandon3055.brandonscore.api.TimeKeeper;
import com.brandon3055.brandonscore.utils.ItemNBTHelper;
import com.greatorator.tolkienmobs.TTMConfig;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

/**
 * Created by brandon3055 on 13/10/2021
 */
public class Trinket extends Item {

    public Trinket(Properties properties) {
        super(properties);
    }

    @Override
    public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.allowdedIn(group)) {
            for (Effect p : TTMConfig.potionArray) {
                if (p != null) {
                    items.add(getTrinketForEffect(p));
                }
            }
        }
    }

    public ItemStack getTrinketForEffect(Effect effect) {
        ItemStack stack = new ItemStack(this);
        PotionUtils.setCustomEffects(stack, Collections.singleton(new EffectInstance(effect, 415, 0, false, false)));
        return stack;
    }

    @Override
    public ITextComponent getName(ItemStack stack) {
        List<EffectInstance> effects = getEffects(stack);
        if (effects.isEmpty()) {
            return super.getName(stack);
        }

        ITextComponent effectName = effects.get(0).getEffect().getDisplayName();
        TextComponent itemName = (TextComponent) super.getName(stack);

        return itemName.append(effectName);
    }

    public static List<EffectInstance> getEffects(ItemStack stack) {
        if (stack.isEmpty()) {
            return Collections.emptyList();
        }
        return PotionUtils.getCustomEffects(stack);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player.isShiftKeyDown() && !getEffects(stack).isEmpty()) {
            toggleEnabled(stack);
            if (!isEnabled(stack)) {
                List<EffectInstance> effects = getEffects(stack);
                effects.forEach(effect -> player.removeEffect(effect.getEffect()));
            }
        }
        return super.use(world, player, hand);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean isFoil(ItemStack stack) {
        return isEnabled(stack);
    }

    public static boolean isEnabled(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, "is_active", false);
    }

    public static void toggleEnabled(ItemStack stack) {
        ItemNBTHelper.setBoolean(stack, "is_active", !isEnabled(stack));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean hotbar) {
        updateTrinket(stack, entity);
    }

    private void updateTrinket(ItemStack stack, Entity entity) {
        PlayerEntity player = (PlayerEntity) entity;
        if (isEnabled(stack) && TimeKeeper.getServerTick() % 10 == 0) {
            List<EffectInstance> effects = getEffects(stack);
            effects.forEach(player::addEffect);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        PotionUtils.addPotionTooltip(stack, tooltip, 1.0F);
        tooltip.add(new TranslationTextComponent("item_info.tolkienmobs.sneak_right_click_activate").withStyle(TextFormatting.DARK_PURPLE));
    }
}
