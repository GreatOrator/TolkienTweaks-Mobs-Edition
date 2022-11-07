package com.greatorator.tolkienmobs.handler.registers;

import com.brandon3055.brandonscore.client.BCGuiSprites;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ColorHandlerEvent;

import java.util.function.Supplier;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/**
 * Created by brandon3055 on 09/12/2021
 * This all goes through BCSprites which means these are also registered to the BC GUI Texture sheet.
 * This means these sprites can be used anywhere BCSprites can be used without having to worry about texture sheets.
 */
public class SpritesRegister {

    public static void initialize(ColorHandlerEvent.Block event) {
        register(MODID, "fireplace/fire_empty");
        register(MODID, "fireplace/fire_full");
        register(MODID, "fireplace/craft_empty");
        register(MODID, "fireplace/craft_full");

        register(MODID, "backpack/crafting_table");
        register(MODID, "backpack/craft_arrow");
        register(MODID, "backpack/tank_slots");
        register(MODID, "backpack/tank_arrows");
        register(MODID, "backpack/bed");
        register(MODID, "backpack/bed_deployed");
        register(MODID, "backpack/campfire");
        register(MODID, "backpack/campfire_deployed");
        register(MODID, "backpack/upgrade");
        register(MODID, "backpack/close_upgrade");
        register(MODID, "backpack/tank");
        register(MODID, "backpack/tank_overlay");

        register(MODID, "keys/key_consume");
        register(MODID, "keys/key_keep");
        register(MODID, "keys/redstone_always");
        register(MODID, "keys/redstone_pulse");
        register(MODID, "keys/redstone_delay");
        register(MODID, "keys/redstone_always_active");
        register(MODID, "keys/redstone_pulse_active");
        register(MODID, "keys/redstone_delay_active");

        register(MODID, "spawner/spawn_requirements");
        register(MODID, "spawner/player");
        register(MODID, "spawner/particles");
        register(MODID, "spawner/ignore_spawn_requirements");
        register(MODID, "spawner/ignore_player");
        register(MODID, "spawner/ignore_particles");

    }

    //region register

    public static void registerThemed(String modid, String location) {
        BCGuiSprites.registerThemed(modid, location);
    }

    public static void register(String modid, String location) {
        BCGuiSprites.register(modid, location);
    }

    public static void register(ResourceLocation location) {
        BCGuiSprites.register(location);
    }

    //endregion
    public static net.minecraft.client.resources.model.Material getThemed(String modid, String location) {
        return BCGuiSprites.getThemed(modid, location);
    }

    public static net.minecraft.client.resources.model.Material getThemed(String location) {
        return BCGuiSprites.getThemed(MODID, location);
    }

    public static net.minecraft.client.resources.model.Material get(String modid, String location) {
        return BCGuiSprites.get(modid, location);
    }

    public static Material get(String location) {
        return BCGuiSprites.get(MODID, location);
    }

    public static Supplier<net.minecraft.client.resources.model.Material> themedGetter(String modid, String location) {
        return BCGuiSprites.themedGetter(modid, location);
    }

    public static Supplier<net.minecraft.client.resources.model.Material> themedGetter(String location) {
        return BCGuiSprites.themedGetter(MODID, location);
    }

    public static Supplier<net.minecraft.client.resources.model.Material> getter(String modid, String location) {
        return BCGuiSprites.getter(modid, location);
    }

    public static Supplier<net.minecraft.client.resources.model.Material> getter(String location) {
        return BCGuiSprites.getter(MODID, location);
    }
}
