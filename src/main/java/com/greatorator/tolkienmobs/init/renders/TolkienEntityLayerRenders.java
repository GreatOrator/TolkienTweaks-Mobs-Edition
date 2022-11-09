package com.greatorator.tolkienmobs.init.renders;

import com.greatorator.tolkienmobs.entity.item.TolkienBoatEntity;
import com.greatorator.tolkienmobs.entity.item.model.TolkienBoatModel;
import com.greatorator.tolkienmobs.handler.TolkienModelLayers;
import com.greatorator.tolkienmobs.item.armor.render.BaseArmorRender;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;
import static com.greatorator.tolkienmobs.TolkienMobs.NAME;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TolkienEntityLayerRenders {

    @SubscribeEvent
    public static void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
        // Armor
        event.registerLayerDefinition(BaseArmorRender.BaseArmorMain.LAYER_LOCATION, BaseArmorRender.BaseArmorMain::createBodyLayer);
        event.registerLayerDefinition(BaseArmorRender.BaseArmorLegs.LAYER_LOCATION, BaseArmorRender.BaseArmorLegs::createBodyLayer);
        event.registerLayerDefinition(TolkienModelLayers.createBoatModelName(TolkienBoatEntity.Type.byName("mallorn")), TolkienBoatModel::createBodyModel);
        event.registerLayerDefinition(TolkienModelLayers.createBoatModelName(TolkienBoatEntity.Type.byName("mirkwood")), TolkienBoatModel::createBodyModel);
        event.registerLayerDefinition(TolkienModelLayers.createBoatModelName(TolkienBoatEntity.Type.byName("culumalda")), TolkienBoatModel::createBodyModel);
        event.registerLayerDefinition(TolkienModelLayers.createBoatModelName(TolkienBoatEntity.Type.byName("lebethron")), TolkienBoatModel::createBodyModel);
        event.registerLayerDefinition(TolkienModelLayers.createBoatModelName(TolkienBoatEntity.Type.byName("deadwood")), TolkienBoatModel::createBodyModel);
        event.registerLayerDefinition(TolkienModelLayers.createBoatModelName(TolkienBoatEntity.Type.byName("fangornoak")), TolkienBoatModel::createBodyModel);
    }

    public String getName() {
        return NAME + " - Layer Renders";
    }

}
