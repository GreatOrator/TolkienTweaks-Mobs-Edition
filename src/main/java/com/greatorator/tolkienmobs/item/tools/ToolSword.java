package com.greatorator.tolkienmobs.item.tools;

import codechicken.lib.model.ModelRegistryHelper;
import com.brandon3055.brandonscore.registry.Feature;
import com.brandon3055.brandonscore.registry.IRenderOverride;
import com.greatorator.tolkienmobs.client.render.tools.TTMObjItemRender;
import com.greatorator.tolkienmobs.handler.TTMSword;

public class ToolSword extends TTMSword implements IRenderOverride {
    private String toolRender;
    private String toolTexture;

    public ToolSword(ToolMaterial material, String toolRender, String toolTexture) {
        super(material);
        this.toolRender = toolRender;
        this.toolTexture = toolTexture;
        setHasSubtypes(true);
        setMaxDamage(-1);
    }

    public void registerRenderer(Feature feature) {
        ModelRegistryHelper.registerItemRenderer(this, new TTMObjItemRender(toolRender, toolTexture));
    }

    public boolean registerNormal(Feature feature) {
        return false;
    }
}