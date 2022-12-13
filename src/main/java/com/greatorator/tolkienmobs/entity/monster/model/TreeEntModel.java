package com.greatorator.tolkienmobs.entity.monster.model;

import com.greatorator.tolkienmobs.entity.monster.TreeEntEntity;
import com.greatorator.tolkienmobs.entity.monster.render.TreeEntRender;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import java.util.List;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/** Tree Ent - GreatOrator */
@SuppressWarnings({ "unchecked", "removal" })
public class TreeEntModel extends AnimatedGeoModel<TreeEntEntity> {
    @Override
    public ResourceLocation getModelLocation(TreeEntEntity object) {
        return new ResourceLocation(MODID, "geo/monster/treeent.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TreeEntEntity object) {
        return TreeEntRender.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TreeEntEntity animatable) {
        return new ResourceLocation(MODID, "animations/monster/treeent.animation.json");
    }

    @Override
    public void setLivingAnimations(TreeEntEntity treeEnt, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(treeEnt, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);
        IBone head = this.getAnimationProcessor().getBone("head");
        IBone ranged = this.getAnimationProcessor().getBone("boulder");

        ranged.setHidden(!treeEnt.getRanged());

        head.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD);
    }
}