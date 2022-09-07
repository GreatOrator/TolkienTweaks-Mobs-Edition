package com.greatorator.tolkienmobs.client;

import codechicken.lib.render.buffer.TransformingVertexBuilder;
import com.brandon3055.brandonscore.client.BCSprites;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ColorHandlerEvent;

import java.util.function.Supplier;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/**
 * Created by brandon3055 on 09/12/2021
 * This all goes through BCSprites which means these are also registered to the BC GUI Texture sheet.
 * This means these sprites can be used anywhere BCSprites can be used without having to worry about texture sheets.
 */
public class TTMSprites {

    public static void initialize(ColorHandlerEvent.Block event) {
        register(MODID, "fireplace/fire_empty");
        register(MODID, "fireplace/fire_full");
        register(MODID, "item_coin");
        register(MODID, "fireplace/craft_empty");
        register(MODID, "fireplace/craft_full");

        register(MODID, "backpack/crafting_table");
        register(MODID, "backpack/craft_arrow");
        register(MODID, "backpack/tank_slots");
        register(MODID, "backpack/tank_arrows");

        register(MODID, "backpack/bed");
        register(MODID, "backpack/campfire");
        register(MODID, "backpack/tank");
        register(MODID, "backpack/tank_overlay");
    }

    //region register

    public static void registerThemed(String modid, String location) {
        BCSprites.registerThemed(modid, location);
    }

    public static void register(String modid, String location) {
        BCSprites.register(modid, location);
    }

    public static void register(ResourceLocation location) {
        BCSprites.register(location);
    }

    //endregion
    public static RenderMaterial getThemed(String modid, String location) {
        return BCSprites.getThemed(modid, location);
    }

    public static RenderMaterial getThemed(String location) {
        return BCSprites.getThemed(MODID, location);
    }

    public static RenderMaterial get(String modid, String location) {
        return BCSprites.get(modid, location);
    }

    public static RenderMaterial get(String location) {
        return BCSprites.get(MODID, location);
    }

    public static TextureAtlasSprite getSprite(String location) {
        return get(location).sprite();
    }

    public static TextureAtlasSprite getSprite(String modid, String location) {
        return get(modid, location).sprite();
    }

    public static Supplier<RenderMaterial> themedGetter(String modid, String location) {
        return BCSprites.themedGetter(modid, location);
    }

    public static Supplier<RenderMaterial> themedGetter(String location) {
        return BCSprites.themedGetter(MODID, location);
    }

    public static Supplier<RenderMaterial> getter(String modid, String location) {
        return BCSprites.getter(modid, location);
    }

    public static Supplier<RenderMaterial> getter(String location) {
        return BCSprites.getter(MODID, location);
    }

    public static IVertexBuilder builder(IRenderTypeBuffer getter, MatrixStack mStack) {
        return new TransformingVertexBuilder(getter.getBuffer(BCSprites.GUI_TYPE), mStack);
    }

    public static IVertexBuilder builder(IRenderTypeBuffer getter) {
        return getter.getBuffer(BCSprites.GUI_TYPE);
    }
}
