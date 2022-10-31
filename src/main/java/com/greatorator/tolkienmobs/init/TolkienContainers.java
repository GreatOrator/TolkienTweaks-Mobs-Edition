package com.greatorator.tolkienmobs.init;

import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.greatorator.tolkienmobs.container.*;
import com.greatorator.tolkienmobs.entity.tile.*;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class TolkienContainers {
    public static final DeferredRegister<MenuType<?>> CONTAINER = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);

    public static MenuType<ContainerBCTile<FireplaceTile>> TMFIREPLACE_CONTAINER;
    public static MenuType<ContainerBCTile<PiggyBankTile>> PIGGYBANK_CONTAINER;
    public static MenuType<ContainerBCTile<MithrilBarrelTile>> BARREL_MITHRIL_CONTAINER;
    public static MenuType<ContainerBCTile<MorgulironBarrelTile>> BARREL_MORGULIRON_CONTAINER;
    public static MenuType<BackpackContainer> BACKPACK_CONTAINER;
    public static MenuType<CoinPouchContainer> COIN_POUCH_CONTAINER;
    public static MenuType<KeyRingContainer> KEY_RING_CONTAINER;
    public static MenuType<ContainerBCTile<MilestoneTile>> MILESTONE_CONTAINER;
    public static MenuType<ContainerBCTile<CamoKeyStoneTile>> KEY_STONE_CONTAINER;
    public static MenuType<CamoChestContainer> CAMO_CHEST_CONTAINER;
    public static MenuType<CamoFluidContainer> CAMO_FLUID_CONTAINER;
    public static MenuType<ContainerBCTile<CamoSpawnerTile>> CAMO_SPAWNER_CONTAINER;
    public static MenuType<LockableChestContainer> LOCKABLE_CHEST_CONTAINER;
    public static MenuType<LockableTreasureChestContainer> LOCKABLE_TREASURE_CHEST_CONTAINER;
    public static MenuType<LockableDoubleChestContainer> LOCKABLE_DOUBLE_CHEST_CONTAINER;
    public static MenuType<LockableDoubleTreasureChestContainer> LOCKABLE_DOUBLE_TREASURE_CHEST_CONTAINER;

    public static void registerContainers(RegistryEvent.Register<MenuType<?>> event) {
        event.getRegistry().register(TMFIREPLACE_CONTAINER = (MenuType<ContainerBCTile<FireplaceTile>>) IForgeMenuType.create((id, playerInv, extraData) -> new ContainerBCTile<>(TMFIREPLACE_CONTAINER, id, playerInv, extraData, FireplaceTile.SLOT_LAYOUT)).setRegistryName("tmfireplace_container"));
        event.getRegistry().register(PIGGYBANK_CONTAINER = (MenuType<ContainerBCTile<PiggyBankTile>>) IForgeMenuType.create((id, playerInv, extraData) -> new ContainerBCTile<>(PIGGYBANK_CONTAINER, id, playerInv, extraData, PiggyBankTile.SLOT_LAYOUT)).setRegistryName("piggybank_container"));
        event.getRegistry().register(BARREL_MITHRIL_CONTAINER = (MenuType<ContainerBCTile<MithrilBarrelTile>>) IForgeMenuType.create((id, playerInv, extraData) -> new ContainerBCTile<>(BARREL_MITHRIL_CONTAINER, id, playerInv, extraData, MithrilBarrelTile.SLOT_LAYOUT)).setRegistryName("barrel_mithril_container"));
        event.getRegistry().register(BARREL_MORGULIRON_CONTAINER = (MenuType<ContainerBCTile<MorgulironBarrelTile>>) IForgeMenuType.create((id, playerInv, extraData) -> new ContainerBCTile<>(BARREL_MORGULIRON_CONTAINER, id, playerInv, extraData, MorgulironBarrelTile.SLOT_LAYOUT)).setRegistryName("barrel_morguliron_container"));
        event.getRegistry().register(BACKPACK_CONTAINER = (MenuType<BackpackContainer>) IForgeMenuType.create(BackpackContainer::new).setRegistryName("backpack_container"));
        event.getRegistry().register(COIN_POUCH_CONTAINER = (MenuType<CoinPouchContainer>) IForgeMenuType.create(CoinPouchContainer::new).setRegistryName("coin_pouch_container"));
        event.getRegistry().register(KEY_RING_CONTAINER = (MenuType<KeyRingContainer>) IForgeMenuType.create(KeyRingContainer::new).setRegistryName("key_ring_container"));
        event.getRegistry().register(MILESTONE_CONTAINER = ((MenuType<ContainerBCTile<MilestoneTile>>) IForgeMenuType.create((id, playerInv, extraData) -> new ContainerBCTile<>(MILESTONE_CONTAINER, id, playerInv, extraData)).setRegistryName("milestone_container")));
        event.getRegistry().register(KEY_STONE_CONTAINER = ((MenuType<ContainerBCTile<CamoKeyStoneTile>>) IForgeMenuType.create((id, playerInv, extraData) -> new ContainerBCTile<>(KEY_STONE_CONTAINER, id, playerInv, extraData)).setRegistryName("key_stone_container")));
        event.getRegistry().register(CAMO_SPAWNER_CONTAINER = ((MenuType<ContainerBCTile<CamoSpawnerTile>>) IForgeMenuType.create((id, playerInv, extraData) -> new ContainerBCTile<>(CAMO_SPAWNER_CONTAINER, id, playerInv, extraData)).setRegistryName("camo_spawner_container")));
        event.getRegistry().register(CAMO_CHEST_CONTAINER = (MenuType<CamoChestContainer>) IForgeMenuType.create(CamoChestContainer::new).setRegistryName("camo_chest_container"));
        event.getRegistry().register(CAMO_FLUID_CONTAINER = (MenuType<CamoFluidContainer>) IForgeMenuType.create(CamoFluidContainer::new).setRegistryName("camo_fluid_container"));
        event.getRegistry().register(LOCKABLE_CHEST_CONTAINER = (MenuType<LockableChestContainer>) IForgeMenuType.create(LockableChestContainer::new).setRegistryName("lockable_chest_container"));
        event.getRegistry().register(LOCKABLE_TREASURE_CHEST_CONTAINER = (MenuType<LockableTreasureChestContainer>) IForgeMenuType.create(LockableTreasureChestContainer::new).setRegistryName("lockable_treasure_chest_container"));
        event.getRegistry().register(LOCKABLE_DOUBLE_CHEST_CONTAINER = (MenuType<LockableDoubleChestContainer>) IForgeMenuType.create(LockableDoubleChestContainer::new).setRegistryName("lockable_double_chest_container"));
        event.getRegistry().register(LOCKABLE_DOUBLE_TREASURE_CHEST_CONTAINER = (MenuType<LockableDoubleTreasureChestContainer>) IForgeMenuType.create(LockableDoubleTreasureChestContainer::new).setRegistryName("lockable_double_treasure_chest_container"));
    }
    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Menus";
    }
}
