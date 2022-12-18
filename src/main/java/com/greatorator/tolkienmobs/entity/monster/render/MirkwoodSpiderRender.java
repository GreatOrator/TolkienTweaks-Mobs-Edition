package com.greatorator.tolkienmobs.entity.monster.render;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.entity.monster.MirkwoodSpiderEntity;
import com.greatorator.tolkienmobs.entity.monster.model.MirkwoodSpiderModel;
import com.greatorator.tolkienmobs.entity.monster.variant.MonsterVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class MirkwoodSpiderRender extends GeoEntityRenderer<MirkwoodSpiderEntity> {
    public static final Map<MonsterVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MonsterVariant.class), (enumMap) -> {
                enumMap.put(MonsterVariant.DEFAULT,
                        new ResourceLocation(MODID, "textures/entity/mirkwoodspider/mirkwoodspider1.png"));
                enumMap.put(MonsterVariant.RED,
                        new ResourceLocation(MODID, "textures/entity/mirkwoodspider/mirkwoodspider1.png"));
                enumMap.put(MonsterVariant.ORANGE,
                        new ResourceLocation(MODID, "textures/entity/mirkwoodspider/mirkwoodspider2.png"));
                enumMap.put(MonsterVariant.YELLOW,
                        new ResourceLocation(MODID, "textures/entity/mirkwoodspider/mirkwoodspider3.png"));
                enumMap.put(MonsterVariant.GREEN,
                        new ResourceLocation(MODID, "textures/entity/mirkwoodspider/mirkwoodspider4.png"));
            });

    public MirkwoodSpiderRender(EntityRendererProvider.Context context) {
        super(context, new MirkwoodSpiderModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(MirkwoodSpiderEntity entity) {
        return LOCATION_BY_VARIANT.getOrDefault(entity.getVariant(), LOCATION_BY_VARIANT.get(MonsterVariant.DEFAULT));
    }
}