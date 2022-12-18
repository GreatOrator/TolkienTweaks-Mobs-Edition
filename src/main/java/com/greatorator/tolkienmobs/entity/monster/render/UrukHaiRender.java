package com.greatorator.tolkienmobs.entity.monster.render;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.entity.monster.UrukHaiEntity;
import com.greatorator.tolkienmobs.entity.monster.model.UrukHaiModel;
import com.greatorator.tolkienmobs.entity.monster.render.layer.WeaponLayer;
import com.greatorator.tolkienmobs.entity.monster.variant.MonsterVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class UrukHaiRender extends WeaponLayer<UrukHaiEntity> {
    public static final Map<MonsterVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MonsterVariant.class), (enumMap) -> {
                enumMap.put(MonsterVariant.DEFAULT,
                        new ResourceLocation(MODID, "textures/entity/urukhai/urukhai1.png"));
                enumMap.put(MonsterVariant.RED,
                        new ResourceLocation(MODID, "textures/entity/urukhai/urukhai1.png"));
                enumMap.put(MonsterVariant.ORANGE,
                        new ResourceLocation(MODID, "textures/entity/urukhai/urukhai2.png"));
                enumMap.put(MonsterVariant.YELLOW,
                        new ResourceLocation(MODID, "textures/entity/urukhai/urukhai3.png"));
                enumMap.put(MonsterVariant.GREEN,
                        new ResourceLocation(MODID, "textures/entity/urukhai/urukhai4.png"));
            });

    public UrukHaiRender(EntityRendererProvider.Context context) {
        super(context, new UrukHaiModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    protected ItemStack getHeldItemStack() {
        return this.tolkienEntity.getHeldItem();
    }

    @Override
    protected String getArmPartName() {
        return "rightArm";
    }

    @Override
    public ResourceLocation getTextureLocation(UrukHaiEntity entity) {
        return LOCATION_BY_VARIANT.getOrDefault(entity.getVariant(), LOCATION_BY_VARIANT.get(MonsterVariant.DEFAULT));
    }
}