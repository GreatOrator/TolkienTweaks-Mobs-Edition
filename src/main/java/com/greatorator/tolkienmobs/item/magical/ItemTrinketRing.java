package com.greatorator.tolkienmobs.item.magical;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.brandon3055.brandonscore.items.ItemBCore;
import com.brandon3055.brandonscore.utils.ItemNBTHelper;
import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Optional.Interface(iface = "baubles.api.IBauble", modid = "baubles")
public class ItemTrinketRing extends ItemBCore implements IBauble {
    private static final String TAG_POTION_EFFECT = "effect";

    public ItemTrinketRing() {
        this.setMaxStackSize(1);
        this.setHasSubtypes(true);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(ItemStack stack) {
        return isEnabled(stack);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean hotbar) {
        updateTrinket(stack, entity);
    }

    private void updateTrinket(ItemStack stack, Entity entity) {
        EntityPlayer player = (EntityPlayer) entity;
        Potion pe = getPotion(stack);

        if(isEnabled(stack)){
            //World world = entity.getEntityWorld();
            //boolean flag = false;
            player.addPotionEffect(new PotionEffect(pe,2400,3,false,false));
            //LogHelperTTM.info("Ring has been enabled");
        }else {
            player.removePotionEffect(pe);
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (player.isSneaking() && (getPotion(stack) != null)) {
            toggleEnabled(stack);
        }
        return super.onItemRightClick(world, player, hand);
    }

    public static boolean isEnabled(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, "IsActive", false);
    }

    public static void toggleEnabled(ItemStack stack) {
        ItemNBTHelper.setBoolean(stack, "IsActive", !isEnabled(stack));
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if(tab == getCreativeTab()) {
            subItems.add(new ItemStack(this));
            for(Potion p : TTMConfig.potionArray)
                subItems.add(getTrinketForPotion(p));
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        String name = super.getItemStackDisplayName(stack);
        Potion p = getPotion(stack);
        String potionName = "Nothingness";
        if(p != null)
            potionName = I18n.translateToLocal(p.getName());

        return String.format(name, potionName);
    }

    public static ItemStack getTrinketForPotion(Potion potion) {
        return addPotionToTrinket(new ItemStack(TTMFeatures.TRINKET_RING), potion);
    }

    public static ItemStack addPotionToTrinket(ItemStack stack, Potion potion) {
        String id = potion.getRegistryName().toString();
        ItemNBTHelper.setString(stack, TAG_POTION_EFFECT, id);
        return stack;
    }

    public static Potion getPotion(ItemStack stack) {
        if(stack == null)
            return null;

        String effect = ItemNBTHelper.getString(stack, TAG_POTION_EFFECT, "");
        if(effect.isEmpty())
            return null;

        return Potion.REGISTRY.getObject(new ResourceLocation(effect));
    }

    @Optional.Method(modid = "baubles")
    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.RING;
    }

    @Override
    @Optional.Method(modid = "baubles")
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if (!(player instanceof EntityPlayer)) return;
        updateTrinket(itemstack, player);
    }
}