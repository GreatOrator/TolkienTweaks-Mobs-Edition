package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.model.items.ModelCustomArmor;
import com.greatorator.tolkienmobs.handler.interfaces.TTMHasModel;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ArmorInit extends ItemArmor implements TTMHasModel {
    public ArmorInit(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
    }

    @Override
    public void registerModels()
    {
        TolkienMobs.proxy.registerModel(this, 0);
    }

    @Override
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default)
    {
        if(itemStack != ItemStack.EMPTY)
        {
            if(itemStack.getItem() instanceof ItemArmor)
            {
                ModelCustomArmor model = new ModelCustomArmor();

                model.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;
                model.bipedBody.showModel = armorSlot == EntityEquipmentSlot.CHEST;
                model.bipedBody.showModel = armorSlot == EntityEquipmentSlot.LEGS;
                model.bipedRightLeg.showModel = armorSlot == EntityEquipmentSlot.FEET;
                model.bipedLeftLeg.showModel = armorSlot == EntityEquipmentSlot.FEET;

                model.isChild = _default.isChild;
                model.isRiding = _default.isRiding;
                model.isSneak = _default.isSneak;
                model.rightArmPose = _default.rightArmPose;
                model.leftArmPose = _default.leftArmPose;

                return model;
            }
        }
        return null;
    }
}