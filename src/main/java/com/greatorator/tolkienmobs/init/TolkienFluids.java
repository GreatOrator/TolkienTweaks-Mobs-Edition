package com.greatorator.tolkienmobs.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
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

public class TolkienFluids {
    public static final ResourceLocation FLUID_STILL_RL = new ResourceLocation(MODID,"block/fluid_still");
    public static final ResourceLocation FLUID_FLOWING_RL = new ResourceLocation(MODID,"block/fluid_flow");
    public static final ResourceLocation FLUID_OVERLAY_RL = new ResourceLocation(MODID,"block/fluid_overlay");

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, MODID);

    public static final RegistryObject<FlowingFluid> MITHRIL_FLUID
            = FLUIDS.register("mithril_fluid", () -> new ForgeFlowingFluid.Source(TolkienFluids.MITHRIL_PROPERTIES));

    public static final RegistryObject<FlowingFluid> MITHRIL_FLOWING
            = FLUIDS.register("mithril_flowing", () -> new ForgeFlowingFluid.Flowing(TolkienFluids.MITHRIL_PROPERTIES));


    public static final ForgeFlowingFluid.Properties MITHRIL_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> MITHRIL_FLUID.get(), () -> MITHRIL_FLOWING.get(), FluidAttributes.builder(new ResourceLocation(MODID,"block/mithril_still"), new ResourceLocation(MODID,"block/mithril_flow"))
            .density(15).luminosity(2).viscosity(5).sound(SoundEvents.LAVA_AMBIENT).overlay(FLUID_OVERLAY_RL)
            .color(0xbfcbd2e8)).slopeFindDistance(2).levelDecreasePerBlock(2)
            .block(() -> TolkienFluids.MITHRIL_BLOCK.get()).bucket(() -> TolkienItems.MITHRIL_FLUID_BUCKET.get());

    public static final RegistryObject<LiquidBlock> MITHRIL_BLOCK = TolkienBlocks.BLOCKS.register("mithril",
            () -> new LiquidBlock(() -> TolkienFluids.MITHRIL_FLUID.get(), BlockBehaviour.Properties.of(Material.LAVA)
                    .noCollission().strength(100f).noDrops()));

    public static final RegistryObject<FlowingFluid> MORGULIRON_FLUID
            = FLUIDS.register("morguliron_fluid", () -> new ForgeFlowingFluid.Source(TolkienFluids.MORGULIRON_PROPERTIES));

    public static final RegistryObject<FlowingFluid> MORGULIRON_FLOWING
            = FLUIDS.register("morguliron_flowing", () -> new ForgeFlowingFluid.Flowing(TolkienFluids.MORGULIRON_PROPERTIES));


    public static final ForgeFlowingFluid.Properties MORGULIRON_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> MORGULIRON_FLUID.get(), () -> MORGULIRON_FLOWING.get(), FluidAttributes.builder(new ResourceLocation(MODID,"block/morguliron_still"), new ResourceLocation(MODID,"block/morguliron_flow"))
            .density(15).luminosity(2).viscosity(5).sound(SoundEvents.LAVA_AMBIENT).overlay(FLUID_OVERLAY_RL)
            .color(0xbf232b27)).slopeFindDistance(2).levelDecreasePerBlock(2)
            .block(() -> TolkienFluids.MORGULIRON_BLOCK.get()).bucket(() -> TolkienItems.MORGULIRON_FLUID_BUCKET.get());

    public static final RegistryObject<LiquidBlock> MORGULIRON_BLOCK = TolkienBlocks.BLOCKS.register("morguliron",
            () -> new LiquidBlock(() -> TolkienFluids.MORGULIRON_FLUID.get(), BlockBehaviour.Properties.of(Material.LAVA)
                    .noCollission().strength(100f).noDrops()));
}
