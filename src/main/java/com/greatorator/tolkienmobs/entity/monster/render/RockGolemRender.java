package com.greatorator.tolkienmobs.entity.monster.render;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.entity.monster.RockGolemEntity;
import com.greatorator.tolkienmobs.entity.monster.model.RockGolemModel;
import com.greatorator.tolkienmobs.entity.monster.render.layer.WeaponLayer;
import com.greatorator.tolkienmobs.entity.monster.variant.MonsterVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class RockGolemRender extends WeaponLayer<RockGolemEntity> {
    public static final Map<MonsterVariant, ResourceLocation> LOCATION_BY_VARIANT =
        Util.make(Maps.newEnumMap(MonsterVariant.class), (enumMap) -> {
        enumMap.put(MonsterVariant.DEFAULT,
        new ResourceLocation(MODID, "textures/entity/rockgolem/rockgolem1.png"));
        enumMap.put(MonsterVariant.ORANGE,
        new ResourceLocation(MODID, "textures/entity/rockgolem/rockgolem1.png"));
        enumMap.put(MonsterVariant.GREEN,
        new ResourceLocation(MODID, "textures/entity/rockgolem/rockgolem2.png"));
        enumMap.put(MonsterVariant.RED,
        new ResourceLocation(MODID, "textures/entity/rockgolem/rockgolem3.png"));
        enumMap.put(MonsterVariant.YELLOW,
        new ResourceLocation(MODID, "textures/entity/rockgolem/rockgolem4.png"));
        enumMap.put(MonsterVariant.BLUE,
        new ResourceLocation(MODID, "textures/entity/rockgolem/rockgolem5.png"));
        enumMap.put(MonsterVariant.INDIGO,
        new ResourceLocation(MODID, "textures/entity/rockgolem/rockgolem6.png"));
        enumMap.put(MonsterVariant.VIOLET,
        new ResourceLocation(MODID, "textures/entity/rockgolem/rockgolem7.png"));
        enumMap.put(MonsterVariant.MAGENTA,
        new ResourceLocation(MODID, "textures/entity/rockgolem/rockgolem8.png"));
        enumMap.put(MonsterVariant.PINK,
        new ResourceLocation(MODID, "textures/entity/rockgolem/rockgolem9.png"));
        enumMap.put(MonsterVariant.GRAY,
        new ResourceLocation(MODID, "textures/entity/rockgolem/rockgolem10.png"));
        });

    public RockGolemRender(EntityRendererProvider.Context context) {
        super(context, new RockGolemModel());
        this.shadowRadius = 1.0f;
    }

    @Override
    public ResourceLocation getTextureLocation(RockGolemEntity entity) {
        return LOCATION_BY_VARIANT.getOrDefault(entity.getVariant(), LOCATION_BY_VARIANT.get(MonsterVariant.DEFAULT));
    }

    @Override
    protected ItemStack getHeldItemStack() {
        return this.tolkienEntity.getHeldItem();
    }

    @Override
    protected String getArmPartName() {
        return "RightArm";
    }
}
