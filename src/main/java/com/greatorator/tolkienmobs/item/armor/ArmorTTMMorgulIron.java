package com.greatorator.tolkienmobs.item.armor;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.client.model.tools.ModelTTMMorgulIronArmor;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class ArmorTTMMorgulIron extends ArmorItem {
    @OnlyIn(Dist.CLIENT)
    private BipedModel model;
    private final String texture;

    public ArmorTTMMorgulIron(IArmorMaterial material, EquipmentSlotType slot, Properties properties)
    {
        this("", material, slot, properties);
    }

    public ArmorTTMMorgulIron(String texture, IArmorMaterial material, EquipmentSlotType slot, Properties properties)
    {
        super(material, slot, properties);
        this.texture = texture;
    }

    @OnlyIn(Dist.CLIENT)
    public void setArmorModel(BipedModel model)
    {
        this.model = model;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type)
    {
        return MODID + ":textures/models/armor/morguliron_layer_" + (slot == EquipmentSlotType.LEGS ? 2 : 1) + ".png";
    }

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack,
                                                     EquipmentSlotType armorSlot, A _default) {
        ModelTTMMorgulIronArmor model = new ModelTTMMorgulIronArmor(1.0F);

        model.HelmMorgulIron.visible = armorSlot == EquipmentSlotType.HEAD;
        model.ChestMorgulIron.visible = armorSlot == EquipmentSlotType.CHEST;
        model.MorgulIronRA.visible = armorSlot == EquipmentSlotType.CHEST;
        model.MorgulIronLA.visible = armorSlot == EquipmentSlotType.CHEST;
        model.MorgulIronRL.visible = armorSlot == EquipmentSlotType.LEGS;
        model.MorgulIronLL.visible = armorSlot == EquipmentSlotType.LEGS;
        model.MorgulIronRF.visible = armorSlot == EquipmentSlotType.FEET;
        model.MorgulIronLF.visible = armorSlot == EquipmentSlotType.FEET;

        model.young = _default.young;
        model.riding = _default.riding;
        model.crouching = _default.crouching;
        model.rightArmPose = _default.rightArmPose;
        model.leftArmPose = _default.leftArmPose;

        return (A) model;
    }

    public void tick(World world, PlayerEntity player, ItemStack armor) {
        if(!world.isClientSide()) {
            if (player.inventory.getArmor(3).getItem() == TTMContent.HELMET_MORGULIRON.get()) {
                player.addEffect(new EffectInstance(Effects.ABSORPTION, 40, 3, true, false));
                player.addEffect(new EffectInstance(Effects.SATURATION, 40, 2, true, false));
            }
            if (player.inventory.getArmor(2).getItem() == TTMContent.CHESTPLATE_MORGULIRON.get()) {
                player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 40, 3, true, false));
            }
            if (player.inventory.getArmor(1).getItem() == TTMContent.LEGGINGS_MORGULIRON.get()) {
                player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 40, 2, true, false));
                player.addEffect(new EffectInstance(Effects.DIG_SPEED, 40, 2, true, false));
            }
            if (player.inventory.getArmor(0).getItem() == TTMContent.BOOTS_MORGULIRON.get()) {
                player.addEffect(new EffectInstance(Effects.REGENERATION, 40, 1, true, false));
            }
        }
    }
}