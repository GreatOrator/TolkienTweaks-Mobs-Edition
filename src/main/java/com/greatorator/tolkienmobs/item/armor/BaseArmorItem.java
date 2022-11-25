package com.greatorator.tolkienmobs.item.armor;

import com.google.common.collect.ImmutableMap;
import com.greatorator.tolkienmobs.init.TolkienPotions;
import com.greatorator.tolkienmobs.item.armor.model.BaseArmorModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
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
import net.minecraftforge.client.IItemRenderProperties;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class BaseArmorItem {
    public static class Helmet extends ArmorItem {
        public String armorType;

        public Helmet(ArmorMaterial material, EquipmentSlot slot, Properties properties, String type) {
            super(material, slot, properties);
            this.armorType = type;
        }

        @Override
        public void initializeClient(Consumer<IItemRenderProperties> consumer) {
            consumer.accept(new IItemRenderProperties() {

                @Override
                public HumanoidModel getBaseArmorModel(LivingEntity living, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> defaultModel) {

                    Map<String, ModelPart> map = ImmutableMap.of(
                            "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "hat", new BaseArmorModel.armorBody(Minecraft.getInstance().getEntityModels().bakeLayer(BaseArmorModel.armorBody.BODY_LAYER_LOCATION)).head,
                            "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())
                    );

                    ModelPart modelPart = new ModelPart(Collections.emptyList(), map);
                    BaseArmorModel.armorBody armorModel = new BaseArmorModel.armorBody(modelPart);

                    armorModel.entity = living;

                    return armorModel;
                }
            });
        }

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return MODID + ":textures/armor/" + armorType + "_armor_model.png";
        }

        @Override
        public void onArmorTick(ItemStack stack, Level world, Player player) {
            if(!world.isClientSide() && player != null) {
                getArmorEffects(armorType, player);
            }
        }

        private void getArmorEffects(String armorType, Player player) {
            switch(armorType) {
                case "mithril":
                default:
                    player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 40, 2, true, false));
                case "morguliron":
                    player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 40, 3, true, false));
                    player.addEffect(new MobEffectInstance(MobEffects.SATURATION, 40, 2, true, false));
            }
        }
    }

    public static class Chest extends ArmorItem {
        public String armorType;

        public Chest(ArmorMaterial material, EquipmentSlot slot, Properties properties, String type) {
            super(material, slot, properties);
            this.armorType = type;
        }

        @Override
        public void initializeClient(Consumer<IItemRenderProperties> consumer) {
            consumer.accept(new IItemRenderProperties() {

                @Override
                public HumanoidModel getBaseArmorModel(LivingEntity living, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> defaultModel) {

                    Map<String, ModelPart> map = ImmutableMap.of(
                            "body", new BaseArmorModel.armorBody(Minecraft.getInstance().getEntityModels().bakeLayer(BaseArmorModel.armorBody.BODY_LAYER_LOCATION)).body,
                            "right_arm", new BaseArmorModel.armorBody(Minecraft.getInstance().getEntityModels().bakeLayer(BaseArmorModel.armorBody.BODY_LAYER_LOCATION)).rightArm,
                            "left_arm", new BaseArmorModel.armorBody(Minecraft.getInstance().getEntityModels().bakeLayer(BaseArmorModel.armorBody.BODY_LAYER_LOCATION)).leftArm,
                            "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap())
                    );

                    ModelPart modelPart = new ModelPart(Collections.emptyList(), map);
                    BaseArmorModel.armorBody armorModel = new BaseArmorModel.armorBody(modelPart);

                    armorModel.entity = living;

                    return armorModel;
                }
            });
        }

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return MODID + ":textures/armor/" + armorType + "_armor_model.png";
        }

        @Override
        public void onArmorTick(ItemStack stack, Level world, Player player) {
            if(!world.isClientSide() && player != null) {
                getArmorEffects(armorType, player);
            }
        }

        private void getArmorEffects(String armorType, Player player) {
            switch(armorType) {
                case "mithril":
                default:
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, 2, true, false));
                case "morguliron":
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 40, 3, true, false));
            }
        }
    }

    public static class Leggings extends ArmorItem {
        public String armorType;

        public Leggings(ArmorMaterial material, EquipmentSlot slot, Properties properties, String type) {
            super(material, slot, properties);
            this.armorType = type;
        }

        @Override
        public void initializeClient(Consumer<IItemRenderProperties> consumer) {
            consumer.accept(new IItemRenderProperties() {

                @Override
                public HumanoidModel getBaseArmorModel(LivingEntity living, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> defaultModel) {

                    Map<String, ModelPart> map = ImmutableMap.of(
                            "right_leg", new BaseArmorModel.armorLegs(Minecraft.getInstance().getEntityModels().bakeLayer(BaseArmorModel.armorLegs.LEG_LAYER_LOCATION)).rightLeg,
                            "left_leg", new BaseArmorModel.armorLegs(Minecraft.getInstance().getEntityModels().bakeLayer(BaseArmorModel.armorLegs.LEG_LAYER_LOCATION)).leftLeg,
                            "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()));

                    ModelPart modelPart = new ModelPart(Collections.emptyList(), map);
                    BaseArmorModel.armorLegs armorModel = new BaseArmorModel.armorLegs(modelPart);

                    armorModel.entity = living;

                    return armorModel;
                }
            });
        }

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return MODID + ":textures/armor/" + armorType + "_armor_model.png";
        }

        @Override
        public void onArmorTick(ItemStack stack, Level world, Player player) {
            if(!world.isClientSide() && player != null) {
                getArmorEffects(armorType, player);
            }
        }

        private void getArmorEffects(String armorType, Player player) {
            switch(armorType) {
                case "mithril":
                default:
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 2, true, false));
                    player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 2, true, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, 3, true, false));
                case "morguliron":
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 2, true, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, 2, true, false));
            }
        }
    }

    public static class Boots extends ArmorItem {
        public String armorType;

        public Boots(ArmorMaterial material, EquipmentSlot slot, Properties properties, String type) {
            super(material, slot, properties);
            this.armorType = type;
        }

        @Override
        public void initializeClient(Consumer<IItemRenderProperties> consumer) {
            consumer.accept(new IItemRenderProperties() {

                @Override
                public HumanoidModel getBaseArmorModel(LivingEntity living, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> defaultModel) {

                    Map<String, ModelPart> map = ImmutableMap.of(
                            "right_leg", new BaseArmorModel.armorBody(Minecraft.getInstance().getEntityModels().bakeLayer(BaseArmorModel.armorBody.BODY_LAYER_LOCATION)).rightLeg,
                            "left_leg", new BaseArmorModel.armorBody(Minecraft.getInstance().getEntityModels().bakeLayer(BaseArmorModel.armorBody.BODY_LAYER_LOCATION)).leftLeg,
                            "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                            "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap())

                    );

                    ModelPart modelPart = new ModelPart(Collections.emptyList(), map);
                    BaseArmorModel.armorBody armorModel = new BaseArmorModel.armorBody(modelPart);

                    armorModel.entity = living;

                    return armorModel;
                }
            });
        }

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return MODID + ":textures/armor/" + armorType + "_armor_model.png";
        }

        @Override
        public void onArmorTick(ItemStack stack, Level world, Player player) {
            if(!world.isClientSide() && player != null) {
                getArmorEffects(armorType, player);
            }
        }

        private void getArmorEffects(String armorType, Player player) {
            switch(armorType) {
                case "mithril":
                default:
                    player.addEffect(new MobEffectInstance(TolkienPotions.ELF_NIMBLENESS.get(), 40, 1, true, false));
                case "morguliron":
                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 40, 1, true, false));
            }
        }
    }

}