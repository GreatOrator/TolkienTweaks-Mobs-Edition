package com.greatorator.tolkienmobs.entity.monster.render;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.entity.monster.BrigandEntity;
import com.greatorator.tolkienmobs.entity.monster.model.BrigandModel;
import com.greatorator.tolkienmobs.entity.monster.render.layer.WeaponLayer;
import com.greatorator.tolkienmobs.entity.monster.variant.MonsterVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class BrigandRender extends WeaponLayer<BrigandEntity> {
    public static final Map<MonsterVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MonsterVariant.class), (enumMap) -> {
                enumMap.put(MonsterVariant.DEFAULT,
                        new ResourceLocation(MODID, "textures/entity/tmbrigand/brigand1.png"));
                enumMap.put(MonsterVariant.RED,
                        new ResourceLocation(MODID, "textures/entity/tmbrigand/brigand1.png"));
                enumMap.put(MonsterVariant.ORANGE,
                        new ResourceLocation(MODID, "textures/entity/tmbrigand/brigand2.png"));
                enumMap.put(MonsterVariant.YELLOW,
                        new ResourceLocation(MODID, "textures/entity/tmbrigand/brigand3.png"));
                enumMap.put(MonsterVariant.GREEN,
                        new ResourceLocation(MODID, "textures/entity/tmbrigand/brigand4.png"));
                enumMap.put(MonsterVariant.BLUE,
                        new ResourceLocation(MODID, "textures/entity/tmbrigand/brigand5.png"));
                enumMap.put(MonsterVariant.INDIGO,
                        new ResourceLocation(MODID, "textures/entity/tmbrigand/brigand6.png"));
                enumMap.put(MonsterVariant.VIOLET,
                        new ResourceLocation(MODID, "textures/entity/tmbrigand/brigand7.png"));
                enumMap.put(MonsterVariant.MAGENTA,
                        new ResourceLocation(MODID, "textures/entity/tmbrigand/brigand8.png"));
                enumMap.put(MonsterVariant.PINK,
                        new ResourceLocation(MODID, "textures/entity/tmbrigand/brigand9.png"));
                enumMap.put(MonsterVariant.GRAY,
                        new ResourceLocation(MODID, "textures/entity/tmbrigand/brigand10.png"));
            });

    public BrigandRender(EntityRendererProvider.Context context) {
        super(context, new BrigandModel());
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
    public ResourceLocation getTextureLocation(BrigandEntity entity) {
        return LOCATION_BY_VARIANT.getOrDefault(entity.getVariant(), LOCATION_BY_VARIANT.get(MonsterVariant.DEFAULT));
    }
}