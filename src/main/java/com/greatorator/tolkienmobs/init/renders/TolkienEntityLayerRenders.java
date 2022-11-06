package com.greatorator.tolkienmobs.init.renders;

import com.greatorator.tolkienmobs.item.armor.render.BaseArmorRender;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TolkienEntityLayerRenders {

    @SubscribeEvent
    public static void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
        // Armor
        event.registerLayerDefinition(BaseArmorRender.BaseArmorMain.LAYER_LOCATION, BaseArmorRender.BaseArmorMain::createBodyLayer);
        event.registerLayerDefinition(BaseArmorRender.BaseArmorLegs.LAYER_LOCATION, BaseArmorRender.BaseArmorLegs::createBodyLayer);
    }

}
