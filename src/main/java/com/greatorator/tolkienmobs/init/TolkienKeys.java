package com.greatorator.tolkienmobs.init;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
public final class TolkienKeys {
    public static final String KEY_CATEGORY = "key.category.tolkienmobs";
    public static final String KEY_OPEN_BACKPACK = "key.tolkienmobs.backpack_key";

    public static final KeyMapping KEY_BACKPACK = new KeyMapping(KEY_OPEN_BACKPACK, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_B, KEY_CATEGORY);


    public static void init() {
        ClientRegistry.registerKeyBinding(TolkienKeys.KEY_BACKPACK);
    }
}