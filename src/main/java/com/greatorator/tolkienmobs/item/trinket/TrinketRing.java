package com.greatorator.tolkienmobs.item.trinket;

import com.brandon3055.brandonscore.utils.ItemNBTHelper;
import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

import static com.greatorator.tolkienmobs.TolkienMobs.LOGGER;

public class TrinketRing extends Item {
    private static final String TAG_POTION_EFFECT = "effect";
    private boolean hasLore = false;

    public TrinketRing(Properties properties) {
        super(properties);
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {

        if (this.isInGroup(group)) {
            for(Effect p : TTMConfig.potionArray) {
                if (p != null) {
                    items.add(getTrinketForPotion(p));
                }
            }
        }
    }

    public static ItemStack getTrinketForPotion(Effect effect) {
        String id = effect.getRegistryName().toString();
        ItemStack stack = new ItemStack(TTMContent.TRINKET_RING.get());
        ItemNBTHelper.setString(stack, TAG_POTION_EFFECT, id);
        return stack;
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        Potion potion = TrinketRing.getPotion(stack);
        CompoundNBT compoundnbt = stack.getTag();
        String s = compoundnbt.getString("effect");

        if (compoundnbt.contains("effect")){
            return new TranslationTextComponent(this.getTranslationKey() + ".effect." + s);
        }else {
            return new TranslationTextComponent(this.getTranslationKey() + ".effect.empty");
        }
    }

    public static Potion getPotion(ItemStack stack) {
        if(stack == null)
            return null;

        String effect = ItemNBTHelper.getString(stack, TAG_POTION_EFFECT, "");
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
            LOGGER.info("Hey you guys!");
            LOGGER.info(pe);
            player.addPotionEffect(new EffectInstance(new EffectInstance(pe,2400,3,false,false)));
        }else {
            player.removePotionEffect(pe);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (hasLore) {
            tooltip.add(new TranslationTextComponent(getTranslationKey() + ".lore").mergeStyle(TextFormatting.DARK_PURPLE));
        }
    }

    public TrinketRing setHasLore() {
        this.hasLore = true;
        return this;
    }
}