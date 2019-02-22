package com.greatorator.tolkienmobs.item.armor;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.model.items.ModelMorgulIronArmor;
import com.greatorator.tolkienmobs.handler.interfaces.TTMHasModel;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ArmorMorgulIron extends ItemArmor implements TTMHasModel {
    public ArmorMorgulIron(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
    }

    @Override
    public void registerModels() {
        TolkienMobs.proxy.registerModel(this, 0);
    }

    @SideOnly(Side.CLIENT)
    public ModelMorgulIronArmor model;

    @SideOnly(Side.CLIENT)
    @Override
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        if (model == null) {
            model = new ModelMorgulIronArmor();
            model.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;
            model.bipedBody.showModel = armorSlot == EntityEquipmentSlot.CHEST;
            model.bipedRightLeg.showModel = armorSlot == EntityEquipmentSlot.FEET || armorSlot == EntityEquipmentSlot.LEGS;
            model.bipedLeftLeg.showModel = armorSlot == EntityEquipmentSlot.FEET || armorSlot == EntityEquipmentSlot.LEGS;
            model.bipedRightLeg.cubeList.clear();
            model.bipedLeftLeg.cubeList.clear();
            model.bipedHead.cubeList.clear();
            model.bipedBody.cubeList.clear();
            model.bipedLeftArm.cubeList.clear();
            model.bipedRightArm.cubeList.clear();

            model.MorgulIronLF.showModel = armorSlot == EntityEquipmentSlot.FEET;
            model.MorgulIronLFPart.showModel = armorSlot == EntityEquipmentSlot.FEET;
            model.MorgulIronRF.showModel = armorSlot == EntityEquipmentSlot.FEET;
            model.MorgulIronRFPart.showModel = armorSlot == EntityEquipmentSlot.FEET;

            model.MorgulIronLL.showModel = armorSlot == EntityEquipmentSlot.LEGS;
            model.MorgulIronLLPart.showModel = armorSlot == EntityEquipmentSlot.LEGS;
            model.MorgulIronRL.showModel = armorSlot == EntityEquipmentSlot.LEGS;
            model.MorgulIronRLPart.showModel = armorSlot == EntityEquipmentSlot.LEGS;
        }

        model.isChild = _default.isChild;
        model.isRiding = _default.isRiding;
        model.isSneak = _default.isSneak;
        model.rightArmPose = _default.rightArmPose;
        model.leftArmPose = _default.leftArmPose;

        return model;
    }
}