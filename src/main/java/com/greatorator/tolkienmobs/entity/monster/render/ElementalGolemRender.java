package com.greatorator.tolkienmobs.entity.monster.render;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.entity.monster.ElementalGolemEntity;
import com.greatorator.tolkienmobs.entity.monster.model.ElementalGolemModel;
import com.greatorator.tolkienmobs.entity.monster.variant.MonsterVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class ElementalGolemRender extends BaseMonsterRender<ElementalGolemEntity> {
    public static final Map<MonsterVariant, ResourceLocation> LOCATION_BY_VARIANT =
        Util.make(Maps.newEnumMap(MonsterVariant.class), (enumMap) -> {
        enumMap.put(MonsterVariant.DEFAULT,
        new ResourceLocation(MODID, "textures/entity/elementalgolem/elemental_golem_none.png"));
        enumMap.put(MonsterVariant.ORANGE,
        new ResourceLocation(MODID, "textures/entity/elementalgolem/elemental_golem_none.png"));
        enumMap.put(MonsterVariant.GREEN,
        new ResourceLocation(MODID, "textures/entity/elementalgolem/elemental_golem_earth.png"));
        enumMap.put(MonsterVariant.RED,
        new ResourceLocation(MODID, "textures/entity/elementalgolem/elemental_golem_fire.png"));
        enumMap.put(MonsterVariant.YELLOW,
        new ResourceLocation(MODID, "textures/entity/elementalgolem/elemental_golem_air.png"));
        enumMap.put(MonsterVariant.BLUE,
        new ResourceLocation(MODID, "textures/entity/elementalgolem/elemental_golem_water.png"));
        });

    public ElementalGolemRender(EntityRendererProvider.Context context) {
        super(context, new ElementalGolemModel());
        this.shadowRadius = 1.0f;
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
    public ResourceLocation getTextureLocation(ElementalGolemEntity entity) {
        return LOCATION_BY_VARIANT.getOrDefault(entity.getVariant(), LOCATION_BY_VARIANT.get(MonsterVariant.DEFAULT));
    }
}
