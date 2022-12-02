package com.greatorator.tolkienmobs.item.client.render;

import com.greatorator.tolkienmobs.item.basic.CatalystItem;
import com.greatorator.tolkienmobs.item.client.model.CatalystModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class CatalystRender extends GeoItemRenderer<CatalystItem> {
    public CatalystRender() {
        super(new CatalystModel());
    }
}