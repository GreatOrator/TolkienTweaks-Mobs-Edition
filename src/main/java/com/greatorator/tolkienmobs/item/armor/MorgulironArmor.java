package com.greatorator.tolkienmobs.item.armor;

import com.google.common.collect.ImmutableMap;
import com.greatorator.tolkienmobs.handler.enums.TolkienArmorMaterials;
import com.greatorator.tolkienmobs.init.TolkienItems;
import com.greatorator.tolkienmobs.item.armor.model.MorgulIronArmorModel;
import net.minecraft.client.model.Model;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.checkerframework.checker.units.qual.A;

import javax.annotation.Nullable;
import java.util.Map;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class MorgulironArmor extends ArmorItem {
    private static final Map<ArmorMaterial, MobEffect> MATERIAL_TO_EFFECT_MAP =
            new ImmutableMap.Builder<ArmorMaterial, MobEffect>()
                    .put(TolkienArmorMaterials.MORGULIRON, MobEffects.INVISIBILITY)
                    .build();
    @OnlyIn(Dist.CLIENT)
    private Model model;
    private final String texture;

    public MorgulironArmor(ArmorMaterial material, EquipmentSlot slot, Properties properties)
    {
        this("", material, slot, properties);
    }

    public MorgulironArmor(String texture, ArmorMaterial material, EquipmentSlot slot, Properties properties)
    {
        super(material, slot, properties);
        this.texture = texture;
    }

    @OnlyIn(Dist.CLIENT)
    public void setArmorModel(Model model)
    {
        this.model = model;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type)
    {
        return MODID + ":textures/models/armor/morguliron_layer_" + (slot == EquipmentSlot.LEGS ? 2 : 1) + ".png";
    }

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public static Model getArmorModel(LivingEntity entityLiving, ItemStack itemStack,
                                                EquipmentSlot armorSlot, A _default) {
        MorgulIronArmorModel model = new MorgulIronArmorModel(1.0F);

        model.HelmMorgulIron.visible = armorSlot == EquipmentSlot.HEAD;
        model.ChestMorgulIron.visible = armorSlot == EquipmentSlot.CHEST;
        model.MorgulIronRA.visible = armorSlot == EquipmentSlot.CHEST;
        model.MorgulIronLA.visible = armorSlot == EquipmentSlot.CHEST;
        model.MorgulIronRL.visible = armorSlot == EquipmentSlot.LEGS;
        model.MorgulIronLL.visible = armorSlot == EquipmentSlot.LEGS;
        model.MorgulIronRF.visible = armorSlot == EquipmentSlot.FEET;
        model.MorgulIronLF.visible = armorSlot == EquipmentSlot.FEET;

        model.young = _default.young;
        model.riding = _default.riding;
        model.crouching = _default.crouching;
        model.rightArmPose = _default.rightArmPose;
        model.leftArmPose = _default.leftArmPose;

        return model;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if(!world.isClientSide() && player != null) {
            if (player.getInventory().getArmor(3).getItem() == TolkienItems.HELMET_MORGULIRON.get()) {
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 40, 3, true, false));
                player.addEffect(new MobEffectInstance(MobEffects.SATURATION, 40, 2, true, false));
            }
            if (player.getInventory().getArmor(2).getItem() == TolkienItems.CHESTPLATE_MORGULIRON.get()) {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 40, 3, true, false));
            }
            if (player.getInventory().getArmor(1).getItem() == TolkienItems.LEGGINGS_MORGULIRON.get()) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 2, true, false));
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, 2, true, false));
            }
            if (player.getInventory().getArmor(0).getItem() == TolkienItems.BOOTS_MORGULIRON.get()) {
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 40, 1, true, false));
            }
        }
        super.onArmorTick(stack, world, player);
    }

    public void tick(Level world, Player player, ItemStack armor) {

    }
}