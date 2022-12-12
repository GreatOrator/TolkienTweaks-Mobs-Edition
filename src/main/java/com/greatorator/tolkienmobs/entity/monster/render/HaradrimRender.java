package com.greatorator.tolkienmobs.entity.monster.render;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.entity.monster.HaradrimEntity;
import com.greatorator.tolkienmobs.entity.monster.model.HaradrimModel;
import com.greatorator.tolkienmobs.entity.monster.variant.MonsterVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class HaradrimRender extends BaseMonsterRender<HaradrimEntity> {
    public static final Map<MonsterVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MonsterVariant.class), (enumMap) -> {
                enumMap.put(MonsterVariant.DEFAULT,
                        new ResourceLocation(MODID, "textures/entity/tmharadrim/haradrim1.png"));
                enumMap.put(MonsterVariant.RED,
                        new ResourceLocation(MODID, "textures/entity/tmharadrim/haradrim1.png"));
                enumMap.put(MonsterVariant.ORANGE,
                        new ResourceLocation(MODID, "textures/entity/tmharadrim/haradrim2.png"));
                enumMap.put(MonsterVariant.YELLOW,
                        new ResourceLocation(MODID, "textures/entity/tmharadrim/haradrim3.png"));
                enumMap.put(MonsterVariant.GREEN,
                        new ResourceLocation(MODID, "textures/entity/tmharadrim/haradrim4.png"));
                enumMap.put(MonsterVariant.BLUE,
                        new ResourceLocation(MODID, "textures/entity/tmharadrim/haradrim5.png"));
                enumMap.put(MonsterVariant.INDIGO,
                        new ResourceLocation(MODID, "textures/entity/tmharadrim/haradrim6.png"));
                enumMap.put(MonsterVariant.VIOLET,
                        new ResourceLocation(MODID, "textures/entity/tmharadrim/haradrim7.png"));
                enumMap.put(MonsterVariant.MAGENTA,
                        new ResourceLocation(MODID, "textures/entity/tmharadrim/haradrim8.png"));
                enumMap.put(MonsterVariant.PINK,
                        new ResourceLocation(MODID, "textures/entity/tmharadrim/haradrim9.png"));
                enumMap.put(MonsterVariant.GRAY,
                        new ResourceLocation(MODID, "textures/entity/tmharadrim/haradrim10.png"));
            });

    public HaradrimRender(EntityRendererProvider.Context context) {
        super(context, new HaradrimModel());
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
    public ResourceLocation getTextureLocation(HaradrimEntity entity) {
        return LOCATION_BY_VARIANT.getOrDefault(entity.getVariant(), LOCATION_BY_VARIANT.get(MonsterVariant.DEFAULT));
    }
}