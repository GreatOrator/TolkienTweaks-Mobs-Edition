//package com.greatorator.tolkienmobs.handler;
//
//import com.greatorator.tolkienmobs.init.TTMFeatures;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.item.EnumAction;
//import net.minecraft.item.ItemFood;
//import net.minecraft.item.ItemStack;
//import net.minecraft.potion.PotionEffect;
//import net.minecraft.world.World;
//
//import javax.annotation.Nonnull;
//
//public class TTMFood extends ItemFood {
//    private PotionEffect[] effects;
//    public boolean hasEffectOverride = false;
//    public boolean hasDrinkAction = false;
//
//    public TTMFood(int amount, int stackSize, PotionEffect...potionEffects) {
//        super(amount, false);
//        this.effects = potionEffects;
//        this.setMaxStackSize(stackSize);
//    }
//
//    public TTMFood(int amount, int stackSize, float saturation, PotionEffect...potionEffects) {
//        super(amount, saturation, false);
//        this.effects = potionEffects;
//        this.setMaxStackSize(stackSize);
//    }
//
//    public TTMFood setEffectOverride(boolean hasEffectOverride) {
//        this.hasEffectOverride = hasEffectOverride;
//        return this;
//    }
//
//    public TTMFood setItemUseAction(boolean getItemUseAction) {
//        this.hasDrinkAction = getItemUseAction;
//        return this;
//    }
//
//    @Nonnull
//    public EnumAction getItemUseAction(ItemStack stack) {
//        if(hasDrinkAction) {
//            return EnumAction.DRINK;
//        }
//        return EnumAction.EAT;
//    }
//
//    public ItemStack onDrinkPotion(ItemStack stack, LivingEntity entityLiving)
//    {
//        PlayerEntity entityplayer = entityLiving instanceof PlayerEntity ? (PlayerEntity)entityLiving : null;
//        if ((entityplayer == null || !entityplayer.capabilities.isCreativeMode) && hasDrinkAction)
//        {
//            if (stack.isEmpty())
//            {
//                return new ItemStack(TTMFeatures.BOTTLE_FANCY);
//            }
//
//            if (entityplayer != null)
//            {
//                entityplayer.inventory.addItemStackToInventory(new ItemStack(TTMFeatures.BOTTLE_FANCY));
//            }
//        }
//        return stack;
//    }
//
//    @Override
//    public boolean hasEffect(ItemStack stack) {
//        return hasEffectOverride || super.hasEffect(stack);
//    }
//
//    @Override
//    protected void onFoodEaten(ItemStack stack, World worldIn, PlayerEntity player){
//        this.onDrinkPotion(stack,player);
//        for(PotionEffect effect : effects){
//            player.addPotionEffect(effect);
//        }
//    }
//}