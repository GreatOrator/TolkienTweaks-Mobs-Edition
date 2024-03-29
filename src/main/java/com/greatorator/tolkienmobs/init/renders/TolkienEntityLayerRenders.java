package com.greatorator.tolkienmobs.init.renders;

import com.greatorator.tolkienmobs.entity.ambient.model.SwarmModel;
import com.greatorator.tolkienmobs.entity.ambient.render.layer.SwarmLayer;
import com.greatorator.tolkienmobs.entity.item.TolkienBoatEntity;
import com.greatorator.tolkienmobs.entity.item.model.TolkienBoatModel;
import com.greatorator.tolkienmobs.handler.BoatLayerHandler;
import com.greatorator.tolkienmobs.item.armor.MithrilArmorItem;
import com.greatorator.tolkienmobs.item.armor.MorgulironArmorItem;
import com.greatorator.tolkienmobs.item.client.render.MithrilArmorRender;
import com.greatorator.tolkienmobs.item.client.render.MorgulironArmorRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

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
        event.registerLayerDefinition(BoatLayerHandler.createBoatModelName(TolkienBoatEntity.Type.byName("mallorn")), TolkienBoatModel::createBodyModel);
        event.registerLayerDefinition(BoatLayerHandler.createBoatModelName(TolkienBoatEntity.Type.byName("mirkwood")), TolkienBoatModel::createBodyModel);
        event.registerLayerDefinition(BoatLayerHandler.createBoatModelName(TolkienBoatEntity.Type.byName("culumalda")), TolkienBoatModel::createBodyModel);
        event.registerLayerDefinition(BoatLayerHandler.createBoatModelName(TolkienBoatEntity.Type.byName("lebethron")), TolkienBoatModel::createBodyModel);
        event.registerLayerDefinition(BoatLayerHandler.createBoatModelName(TolkienBoatEntity.Type.byName("deadwood")), TolkienBoatModel::createBodyModel);
        event.registerLayerDefinition(BoatLayerHandler.createBoatModelName(TolkienBoatEntity.Type.byName("fangornoak")), TolkienBoatModel::createBodyModel);

        // Ambient
        event.registerLayerDefinition(SwarmLayer.ENTITY_TTM_SWARM, SwarmModel::create);

        // Merchants

        // Monster

        // Boss

        // Passive

        // Special
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.AddLayers event) {
        EntityRendererProvider.Context context = new EntityRendererProvider.Context(Minecraft.getInstance().getEntityRenderDispatcher(),Minecraft.getInstance().getItemRenderer(), Minecraft.getInstance().getResourceManager(), Minecraft.getInstance().getEntityModels(), Minecraft.getInstance().font);
    }

    @SuppressWarnings({"removal" })
    @SubscribeEvent
    public static void registerArmorRenderers(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(MithrilArmorItem.class, new MithrilArmorRender());
        GeoArmorRenderer.registerArmorRenderer(MorgulironArmorItem.class, new MorgulironArmorRender());
    }

    public String getName() {
        return NAME + " - Layer Renders";
    }

}
