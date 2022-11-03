package com.greatorator.tolkienmobs.item.armor;

import com.greatorator.tolkienmobs.init.TolkienItems;
import com.greatorator.tolkienmobs.init.TolkienPotions;
import com.greatorator.tolkienmobs.item.armor.model.MithrilArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
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

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class MithrilArmor extends ArmorItem {
    @OnlyIn(Dist.CLIENT)
    private Model model;
    private final String texture;

    public MithrilArmor(ArmorMaterial material, EquipmentSlot slot, Properties properties)
    {
        this("", material, slot, properties);
    }

    public MithrilArmor(String texture, ArmorMaterial material, EquipmentSlot slot, Properties properties)
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
        return MODID + ":textures/models/armor/mithril_layer_" + (slot == EquipmentSlot.LEGS ? 2 : 1) + ".png";
    }

    public static Model getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot slot, HumanoidModel<?> _default)
        MithrilArmorModel model = new MithrilArmorModel(1.0F);

        model.HelmMithril.visible = armorSlot == EquipmentSlot.HEAD;
        model.ChestMithril.visible = armorSlot == EquipmentSlot.CHEST;
        model.MithrilRA.visible = armorSlot == EquipmentSlot.CHEST;
        model.MithrilLA.visible = armorSlot == EquipmentSlot.CHEST;
        model.MithrilRL.visible = armorSlot == EquipmentSlot.LEGS;
        model.MithrilLL.visible = armorSlot == EquipmentSlot.LEGS;
        model.MithrilRF.visible = armorSlot == EquipmentSlot.FEET;
        model.MithrilLF.visible = armorSlot == EquipmentSlot.FEET;

        model.young = _default.young;
        model.riding = _default.riding;
        model.crouching = _default.crouching;
        model.rightArmPose = _default.rightArmPose;
        model.leftArmPose = _default.leftArmPose;

        return (A) model;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if(!world.isClientSide() && player != null) {
            if (player.getInventory().getArmor(3).getItem() == TolkienItems.HELMET_MITHRIL.get()) {
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 40, 2, true, false));
            }
            if (player.getInventory().getArmor(2).getItem() == TolkienItems.CHESTPLATE_MITHRIL.get()) {
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, 2, true, false));
            }
            if (player.getInventory().getArmor(1).getItem() == TolkienItems.LEGGINGS_MITHRIL.get()) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 2, true, false));
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 2, true, false));
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, 3, true, false));
            }
            if (player.getInventory().getArmor(0).getItem() == TolkienItems.BOOTS_MITHRIL.get()) {
                player.addEffect(new MobEffectInstance(TolkienPotions.ELF_NIMBLENESS.get(), 40, 1, true, false));
            }
        }
        super.onArmorTick(stack, world, player);
    }

    public void tick(Level world, Player player, ItemStack armor) {
    }
}