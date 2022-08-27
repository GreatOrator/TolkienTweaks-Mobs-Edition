package com.greatorator.tolkienmobs.integration.curios;

import com.brandon3055.brandonscore.BrandonsCore;
import com.brandon3055.brandonscore.capability.MultiCapabilityProvider;
import com.brandon3055.brandonscore.lib.IEquipmentManager;
import com.google.common.collect.ImmutableList;
import com.greatorator.tolkienmobs.handler.interfaces.ITTMEquip;
import com.greatorator.tolkienmobs.integration.TTMHelper;
import com.greatorator.tolkienmobs.lib.FlamingBalrog;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/** Borrowed from Draconic Evolution */
public abstract class TTMEquipMgr implements IEquipmentManager {
    private static boolean curiosLoaded;
    private static TTMEquipMgr instance = null;

    public static void initialize() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        curiosLoaded = TTMHelper.isCuriosInstalled;

        if (curiosLoaded) {
            modBus.addListener(TTMCurios::sendIMC);
            instance = new TTMCurios();
        }

        BrandonsCore.equipmentManager = instance;
    }

    public static boolean equipModLoaded() {
        return instance != null;
    }

    public static String equipModID() {
        if (curiosLoaded) return "curios";
        return "";
    }

    public static void addCaps(ItemStack stack, MultiCapabilityProvider provider) {
        if (instance != null) {
            if (!(stack.getItem() instanceof ITTMEquip)) {
                throw new IllegalStateException("\"Equipment items\" must implement IDEEquipment");
            }
            instance.addEquipCaps(stack, provider);
        }
    }

    public static LazyOptional<IItemHandlerModifiable> getEquipmentInventory(LivingEntity entity) {
        if (instance != null) {
            return instance.getInventory(entity);
        }
        return LazyOptional.empty();
    }

    public static ItemStack findItem(Item item, LivingEntity entity) {
        if (instance != null) {
            return instance.findMatchingItem(item, entity);
        }
        return ItemStack.EMPTY;
    }

    public static List<ItemStack> findItems(Item item, LivingEntity entity) {
        return findItems(stack -> stack.getItem() == item, entity);
    }

    public static ItemStack findItem(Predicate<ItemStack> predicate, LivingEntity entity) {
        if (instance != null) {
            return instance.findMatchingItem(predicate, entity);
        }
        return ItemStack.EMPTY;
    }

    public static List<ItemStack> findItems(Predicate<ItemStack> predicate, LivingEntity entity) {
        if (instance != null) {
            LazyOptional<IItemHandlerModifiable> optionalHandler = instance.getInventory(entity);
            if (optionalHandler.isPresent()) {
                IItemHandlerModifiable handler = optionalHandler.orElseThrow(() -> new FlamingBalrog("This should not happen"));
                List<ItemStack> list = new ArrayList<>();
                for (int i = 0; i < handler.getSlots(); i++) {
                    ItemStack stack = handler.getStackInSlot(i);
                    if (predicate.test(stack)) {
                        list.add(stack);
                    }
                }
                return ImmutableList.copyOf(list);
            }
        }
        return Collections.emptyList();
    }

    public static List<ItemStack> getAllItems(LivingEntity entity) {
        return findItems(stack -> !stack.isEmpty(), entity);
    }

}
