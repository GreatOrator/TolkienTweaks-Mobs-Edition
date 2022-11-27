package com.greatorator.tolkienmobs.init.renders;

import com.greatorator.tolkienmobs.entity.item.TolkienBoatEntity;
import com.greatorator.tolkienmobs.entity.item.model.TolkienBoatModel;
import com.greatorator.tolkienmobs.handler.ModelLayerHandler;
import com.greatorator.tolkienmobs.item.armor.model.BaseArmorModel;
import com.greatorator.tolkienmobs.item.armor.render.BaseArmorRender;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;
import static com.greatorator.tolkienmobs.TolkienMobs.NAME;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TolkienEntityLayerRenders {
    public static final Map<Item, HumanoidModel<LivingEntity>> armorModels = new HashMap<>();

    @SubscribeEvent
    public static void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
        // Armor
        event.registerLayerDefinition(BaseArmorRender.BaseArmorMain.BODY_LAYER_LOCATION, BaseArmorModel.armorBody::createBodyLayer);
        event.registerLayerDefinition(BaseArmorRender.BaseArmorLegs.LEG_LAYER_LOCATION, BaseArmorModel.armorLegs::createLegLayer);
        event.registerLayerDefinition(ModelLayerHandler.createBoatModelName(TolkienBoatEntity.Type.byName("mallorn")), TolkienBoatModel::createBodyModel);
        event.registerLayerDefinition(ModelLayerHandler.createBoatModelName(TolkienBoatEntity.Type.byName("mirkwood")), TolkienBoatModel::createBodyModel);
        event.registerLayerDefinition(ModelLayerHandler.createBoatModelName(TolkienBoatEntity.Type.byName("culumalda")), TolkienBoatModel::createBodyModel);
        event.registerLayerDefinition(ModelLayerHandler.createBoatModelName(TolkienBoatEntity.Type.byName("lebethron")), TolkienBoatModel::createBodyModel);
        event.registerLayerDefinition(ModelLayerHandler.createBoatModelName(TolkienBoatEntity.Type.byName("deadwood")), TolkienBoatModel::createBodyModel);
        event.registerLayerDefinition(ModelLayerHandler.createBoatModelName(TolkienBoatEntity.Type.byName("fangornoak")), TolkienBoatModel::createBodyModel);
    }

//    @SubscribeEvent
//    public static void registerLayers(EntityRenderersEvent.AddLayers event) {
//        EntityRendererProvider.Context context = new EntityRendererProvider.Context(Minecraft.getInstance().getEntityRenderDispatcher(),Minecraft.getInstance().getItemRenderer(), Minecraft.getInstance().getResourceManager(), Minecraft.getInstance().getEntityModels(), Minecraft.getInstance().font);
//    }

    public String getName() {
        return NAME + " - Layer Renders";
    }

}
