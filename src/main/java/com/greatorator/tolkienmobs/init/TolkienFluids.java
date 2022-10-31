package com.greatorator.tolkienmobs.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;
import static com.greatorator.tolkienmobs.init.TolkienBlocks.BLOCKS;
import static com.greatorator.tolkienmobs.init.TolkienItems.ITEMS;
import static com.greatorator.tolkienmobs.init.TolkienTabs.matsGroup;

public class TolkienFluids {
    public static final ResourceLocation FLUID_STILL_RL = new ResourceLocation(MODID, "block/fluid_still");
    public static final ResourceLocation FLUID_FLOWING_RL = new ResourceLocation(MODID, "block/fluid_flow");
    public static final ResourceLocation FLUID_OVERLAY_RL = new ResourceLocation(MODID, "block/fluid_overlay");


    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, MODID);

    public static RegistryObject<FlowingFluid> MITHRIL_FLUID = FLUIDS.register("mithril_fluid", () -> new ForgeFlowingFluid.Source(TolkienFluids.MITHRIL_PROPERTIES));
    public static RegistryObject<FlowingFluid> MITHRIL_FLOWING = FLUIDS.register("mithril_flowing", () -> new ForgeFlowingFluid.Flowing(TolkienFluids.MITHRIL_PROPERTIES));
    public static final ForgeFlowingFluid.Properties MITHRIL_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> MITHRIL_FLUID.get(), () -> MITHRIL_FLOWING.get(), FluidAttributes.builder(FLUID_STILL_RL, FLUID_FLOWING_RL)
            .density(15).luminosity(2).viscosity(5).sound(SoundEvents.LAVA_AMBIENT).overlay(FLUID_OVERLAY_RL)
            .color(0xAAA9ADD0)).slopeFindDistance(2).levelDecreasePerBlock(2)
            .block(() -> TolkienFluids.MITHRIL_FLUID_BLOCK.get()).bucket(() -> TolkienFluids.MITHRIL_FLUID_BUCKET.get());
    public static final RegistryObject<LiquidBlock> MITHRIL_FLUID_BLOCK = BLOCKS.register("mithril_fluid_source", () -> new LiquidBlock(() -> MITHRIL_FLUID.get(), BlockBehaviour.Properties.of(Material.LAVA).noOcclusion().strength(100f).noDrops()));
    public static RegistryObject<Item> MITHRIL_FLUID_BUCKET = ITEMS.register("mithril_fluid_bucket", () -> new BucketItem(() -> MITHRIL_FLUID.get(), new Item.Properties().stacksTo(1).tab(matsGroup)));

    public static RegistryObject<FlowingFluid> MORGULIRON_FLUID = FLUIDS.register("morguliron_fluid", () -> new ForgeFlowingFluid.Source(TolkienFluids.MORGULIRON_PROPERTIES));
    public static RegistryObject<FlowingFluid> MORGULIRON_FLOWING = FLUIDS.register("morguliron_flowing", () -> new ForgeFlowingFluid.Flowing(TolkienFluids.MORGULIRON_PROPERTIES));
    public static final ForgeFlowingFluid.Properties MORGULIRON_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> MORGULIRON_FLUID.get(), () -> MORGULIRON_FLOWING.get(), FluidAttributes.builder(FLUID_STILL_RL, FLUID_FLOWING_RL)
            .density(15).luminosity(2).viscosity(5).sound(SoundEvents.LAVA_AMBIENT).overlay(FLUID_OVERLAY_RL)
            .color(0x353F2AD0)).slopeFindDistance(2).levelDecreasePerBlock(2)
            .block(() -> TolkienFluids.MORGULIRON_FLUID_BLOCK.get()).bucket(() -> TolkienFluids.MORGULIRON_FLUID_BUCKET.get());
    public static final RegistryObject<LiquidBlock> MORGULIRON_FLUID_BLOCK = BLOCKS.register("morguliron_fluid_source", () -> new LiquidBlock(() -> MITHRIL_FLUID.get(), BlockBehaviour.Properties.of(Material.LAVA).noOcclusion().strength(100f).noDrops()));
    public static RegistryObject<Item> MORGULIRON_FLUID_BUCKET = ITEMS.register("morguliron_fluid_bucket", () -> new BucketItem(() -> MITHRIL_FLUID.get(), new Item.Properties().stacksTo(1).tab(matsGroup)));

    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Fluids";
    }
}