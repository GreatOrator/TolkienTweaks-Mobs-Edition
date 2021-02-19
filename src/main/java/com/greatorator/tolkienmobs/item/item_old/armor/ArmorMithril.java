//package com.greatorator.tolkienmobs.item.item_old.armor;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.client.render.model.items.ModelMithrilArmor;
//import com.greatorator.tolkienmobs.handler.handler_old.interfaces.TTMHasModel;
//import com.greatorator.tolkienmobs.init.PotionInit;
//import com.greatorator.tolkienmobs.init.TTMFeatures;
//import net.minecraft.client.model.ModelBiped;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.init.MobEffects;
//import net.minecraft.inventory.EntityEquipmentSlot;
//import net.minecraft.item.ItemArmor;
//import net.minecraft.item.ItemStack;
//import net.minecraft.potion.PotionEffect;
//import net.minecraft.world.World;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//public class ArmorMithril extends ItemArmor implements TTMHasModel {
//    public ArmorMithril(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
//        super(materialIn, renderIndexIn, equipmentSlotIn);
//    }
//
//    @Override
//    public void registerModels() {
//        TolkienMobs.proxy.registerModel(this, 0);
//    }
//
//    @SideOnly(Side.CLIENT)
//    public ModelMithrilArmor model;
//
//    @SideOnly(Side.CLIENT)
//    @Override
//    public ModelBiped getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
//        if (model == null) {
//            model = new ModelMithrilArmor();
//            model.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;
//            model.bipedBody.showModel = armorSlot == EntityEquipmentSlot.CHEST;
//            model.bipedRightLeg.showModel = armorSlot == EntityEquipmentSlot.FEET || armorSlot == EntityEquipmentSlot.LEGS;
//            model.bipedLeftLeg.showModel = armorSlot == EntityEquipmentSlot.FEET || armorSlot == EntityEquipmentSlot.LEGS;
//            model.bipedRightLeg.cubeList.clear();
//            model.bipedLeftLeg.cubeList.clear();
//            model.bipedHead.cubeList.clear();
//            model.bipedBody.cubeList.clear();
//            model.bipedLeftArm.cubeList.clear();
//            model.bipedRightArm.cubeList.clear();
//
//            model.MithrilLF.showModel = armorSlot == EntityEquipmentSlot.FEET;
//            model.MithrilLFPart.showModel = armorSlot == EntityEquipmentSlot.FEET;
//            model.MithrilRF.showModel = armorSlot == EntityEquipmentSlot.FEET;
//            model.MithrilRFPart.showModel = armorSlot == EntityEquipmentSlot.FEET;
//
//            model.MithrilLL.showModel = armorSlot == EntityEquipmentSlot.LEGS;
//            model.MithrilLLPart.showModel = armorSlot == EntityEquipmentSlot.LEGS;
//            model.MithrilRL.showModel = armorSlot == EntityEquipmentSlot.LEGS;
//            model.MithrilRLPart.showModel = armorSlot == EntityEquipmentSlot.LEGS;
//        }
//
//        model.isChild = _default.isChild;
//        model.isRiding = _default.isRiding;
//        model.isSneak = _default.isSneak;
//        model.rightArmPose = _default.rightArmPose;
//        model.leftArmPose = _default.leftArmPose;
//
//        return model;
//    }
//
//    @Override
//    public void onArmorTick(World world, PlayerEntity player, ItemStack armor) {
//        if (player.inventory.armorItemInSlot(3).getItem() == TTMFeatures.HELMET_MITHRIL) {
//            player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 40, 2, true, false));
//        }
//        if (player.inventory.armorItemInSlot(2).getItem() == TTMFeatures.CHESTPLATE_MITHRIL) {
//            player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 40, 2, true, false));
//        }
//        if (player.inventory.armorItemInSlot(1).getItem() == TTMFeatures.LEGGINGS_MITHRIL) {
//            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 40, 2, true, false));
//            player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 40, 2, true, false));
//            player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 40, 3, true, false));
//        }
//        if (player.inventory.armorItemInSlot(0).getItem() == TTMFeatures.BOOTS_MITHRIL) {
//            player.addPotionEffect(new PotionEffect(PotionInit.ELF_NIMBLENESS, 40, 1, true, false));
//        }
//    }
//}