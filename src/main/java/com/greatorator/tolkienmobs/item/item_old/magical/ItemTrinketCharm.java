//package com.greatorator.tolkienmobs.item.item_old.magical;
//
//import baubles.api.BaubleType;
//import baubles.api.IBauble;
//import com.brandon3055.brandonscore.items.ItemBCore;
//import com.brandon3055.brandonscore.utils.ItemNBTHelper;
//import com.greatorator.tolkienmobs.TTMConfig_Old;
//import com.greatorator.tolkienmobs.init.TTMFeatures;
//import com.greatorator.tolkienmobs.utils.TTMTranslator;
//import net.minecraft.client.util.ITooltipFlag;
//import net.minecraft.creativetab.CreativeTabs;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.item.ItemStack;
//import net.minecraft.potion.Potion;
//import net.minecraft.potion.PotionEffect;
//import net.minecraft.util.ActionResult;
//import net.minecraft.util.EnumHand;
//import net.minecraft.util.NonNullList;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.text.TextFormatting;
//import net.minecraft.util.text.translation.I18n;
//import net.minecraft.world.World;
//import net.minecraftforge.fml.common.Optional;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//import java.util.List;
//
//@Optional.Interface(iface = "baubles.api.IBauble", modid = "baubles")
//public class ItemTrinketCharm extends ItemBCore implements IBauble {
//    private static final String TAG_POTION_EFFECT = "effect";
//
//    public ItemTrinketCharm() {
//        this.setMaxStackSize(1);
//        this.setHasSubtypes(true);
//    }
//
//    @SideOnly(Side.CLIENT)
//    @Override
//    public boolean hasEffect(ItemStack stack) {
//        return isEnabled(stack);
//    }
//
//    @Override
//    @SideOnly(Side.CLIENT)
//    public void addInformation(ItemStack stack, World worldIn, List<String> list, ITooltipFlag flagIn) {
//        super.addInformation(stack, worldIn, list, flagIn);
//        list.add(TextFormatting.DARK_PURPLE + TTMTranslator.translateToLocalFormatted("lore." + getUnlocalizedName()));
//    }
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean hotbar) {
//        updateTrinket(stack, entity);
//    }
//
//    private void updateTrinket(ItemStack stack, Entity entity) {
//        PlayerEntity player = (PlayerEntity) entity;
//        Potion pe = getPotion(stack);
//
//        if(isEnabled(stack)){
//            //World world = entity.getEntityWorld();
//            //boolean flag = false;
//            player.addPotionEffect(new PotionEffect(pe,2400,3,false,false));
//            //LogHelperTTM.info("Ring has been enabled");
//        }else {
//            player.removePotionEffect(pe);
//        }
//    }
//
//    @Override
//    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, EnumHand hand) {
//        ItemStack stack = player.getHeldItem(hand);
//        if (player.isSneaking() && (getPotion(stack) != null)) {
//            toggleEnabled(stack);
//        }
//        return super.onItemRightClick(world, player, hand);
//    }
//
//    public static boolean isEnabled(ItemStack stack) {
//        return ItemNBTHelper.getBoolean(stack, "IsActive", false);
//    }
//
//    public static void toggleEnabled(ItemStack stack) {
//        ItemNBTHelper.setBoolean(stack, "IsActive", !isEnabled(stack));
//    }
//
//    @Override
//    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
//        if(tab == getCreativeTab()) {
//            subItems.add(new ItemStack(this));
//            for(Potion p : TTMConfig_Old.potionArray)
//                subItems.add(getTrinketForPotion(p));
//        }
//    }
//
//    @Override
//    public String getItemStackDisplayName(ItemStack stack) {
//        String name = super.getItemStackDisplayName(stack);
//        Potion p = getPotion(stack);
//        String potionName = "Nothingness";
//        if(p != null)
//            potionName = I18n.translateToLocal(p.getName());
//
//        return String.format(name, potionName);
//    }
//
//    public static ItemStack getTrinketForPotion(Potion potion) {
//        String id = potion.getRegistryName().toString();
//        ItemStack stack = new ItemStack(TTMFeatures.TRINKET_CHARM);
//        ItemNBTHelper.setString(stack, TAG_POTION_EFFECT, id);
//        return stack;
//    }
//
//    public static Potion getPotion(ItemStack stack) {
//        if(stack == null)
//            return null;
//
//        String effect = ItemNBTHelper.getString(stack, TAG_POTION_EFFECT, "");
//        if(effect.isEmpty())
//            return null;
//
//        return Potion.REGISTRY.getObject(new ResourceLocation(effect));
//    }
//
//    @Optional.Method(modid = "baubles")
//    @Override
//    public BaubleType getBaubleType(ItemStack itemstack) {
//        return BaubleType.CHARM;
//    }
//
//    @Override
//    @Optional.Method(modid = "baubles")
//    public void onWornTick(ItemStack itemstack, LivingEntity player) {
//        if (!(player instanceof PlayerEntity)) return;
//        updateTrinket(itemstack, player);
//    }
//}