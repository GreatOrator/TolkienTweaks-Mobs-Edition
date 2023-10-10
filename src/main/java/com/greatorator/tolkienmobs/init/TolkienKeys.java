package com.greatorator.tolkienmobs.init;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ClientRegistry;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

@OnlyIn(Dist.CLIENT)
public final class TolkienKeys {
    public static final String KEY_CATEGORY = "key.category.tolkienmobs";
    public static KeyMapping keyMapping;

    private TolkienKeys() {
    }

    public static void init() {
        keyMapping = registerKey("backpack_key", KEY_CATEGORY, InputConstants.KEY_B);
    }

    private static KeyMapping registerKey(String name, String category, int keycode) {
        final var key = new KeyMapping("key." + MODID + "." + name, keycode, category);
        ClientRegistry.registerKeyBinding(key);
        return key;
    }
}