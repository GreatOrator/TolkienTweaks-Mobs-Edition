package com.greatorator.tolkienmobs.init.renders;

import com.greatorator.tolkienmobs.container.gui.*;
import net.minecraft.client.gui.screens.MenuScreens;

import static com.greatorator.tolkienmobs.TolkienMobs.NAME;
import static com.greatorator.tolkienmobs.init.TolkienContainers.*;

public class TolkienGuiRenders {
    public static void init() {
        MenuScreens.register(TMFIREPLACE_CONTAINER, FireplaceScreen::new);
        MenuScreens.register(PIGGYBANK_CONTAINER, PiggyBankScreen::new);
        MenuScreens.register(BARREL_MITHRIL_CONTAINER, MithrilBarrelScreen::new);
        MenuScreens.register(BARREL_MORGULIRON_CONTAINER, MorgulironBarrelScreen::new);
        MenuScreens.register(BACKPACK_CONTAINER, BackpackScreen::new);
        MenuScreens.register(COIN_POUCH_CONTAINER, CoinPouchScreen::new);
        MenuScreens.register(KEY_RING_CONTAINER, KeyRingScreen::new);
        MenuScreens.register(MILESTONE_CONTAINER, MilestoneScreen::new);
        MenuScreens.register(KEY_STONE_CONTAINER, CamoKeyStoneScreen::new);
        MenuScreens.register(CAMO_SPAWNER_CONTAINER, CamoSpawnerScreen::new);
        MenuScreens.register(CAMO_CHEST_CONTAINER, CamoChestScreen::new);
        MenuScreens.register(CAMO_FLUID_CONTAINER, CamoFluidScreen::new);
        MenuScreens.register(LOCKABLE_CHEST_CONTAINER, LockableChestScreen::new);
        MenuScreens.register(LOCKABLE_TREASURE_CHEST_CONTAINER, LockableTreasureChestScreen::new);
        MenuScreens.register(LOCKABLE_DOUBLE_CHEST_CONTAINER, LockableDoubleChestScreen::new);
        MenuScreens.register(LOCKABLE_DOUBLE_TREASURE_CHEST_CONTAINER, LockableDoubleTreasureChestScreen::new);
    }

    public String getName() {
        return NAME + " - GUI Renders";
    }
}
