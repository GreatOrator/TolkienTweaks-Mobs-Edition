package com.greatorator.tolkienmobs.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class GeneralUtility {
    public static final boolean DECK_THE_HALLS;

    static {
        Calendar calendar = Calendar.getInstance();
        DECK_THE_HALLS = calendar.get(Calendar.MONTH) == Calendar.DECEMBER && calendar.get(Calendar.DAY_OF_MONTH) >= 19;
    }

    public static boolean isClient() {
        return FMLEnvironment.dist == Dist.CLIENT;
    }

    @SafeVarargs
    public static <T> boolean contains(T comparator, T... contents) {
        for (T t : contents) if (comparator.equals(t)) return true;
        return false;
    }

    public static <T extends IForgeRegistryEntry<T>> Set<T> getRegistryEntries(DeferredRegister<T> registry) {
        return registry.getEntries().stream().map(RegistryObject::get).collect(Collectors.toSet());
    }

    @Nullable
    public static ItemStack getHeldStack(Player player, Item item) {
        ItemStack main = player.getMainHandItem();
        ItemStack off = player.getOffhandItem();
        return item == main.getItem()? main : item == off.getItem()? off : null;
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public static <T extends Entity> EntityType<T> getEntityTypeByKey(@Nonnull String key) {
        return (EntityType<T>) EntityType.byString(key).orElse(null);
    }

    public static void playLocalSound(Level level, BlockPos pos, SoundEvent sound, float volume, float pitch) {
        playLocalSound(level, pos, sound, SoundSource.NEUTRAL, volume, pitch);
    }

    public static void playLocalSound(Level level, BlockPos pos, SoundEvent sound, SoundSource category, float volume, float pitch) {
        level.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), sound, category, volume, pitch, false);
    }

    public static Iterable<BlockPos> eachPositionIn(AABB aabb) {
        return BlockPos.betweenClosed(
                Mth.floor(aabb.minX),
                Mth.floor(aabb.minY),
                Mth.floor(aabb.minZ),
                Mth.ceil(aabb.maxX),
                Mth.ceil(aabb.maxY),
                Mth.ceil(aabb.maxZ));
    }

    public static <F, T> T getCapability(Capability<F> cap, CapabilityProvider<?> item) {
        return item.getCapability(cap).<T>cast().orElseThrow(NoSuchElementException::new);
    }

    public static <T> void runAndClear(Collection<T> c, Consumer<T> run) {
        Iterator<T> itr = c.iterator();
        while (itr.hasNext()) {
            run.accept(itr.next());
            itr.remove();
        }
    }
}