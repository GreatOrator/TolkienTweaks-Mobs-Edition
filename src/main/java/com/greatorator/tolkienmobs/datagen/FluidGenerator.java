package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class FluidGenerator {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, MODID);

    public static final ResourceLocation MOLTEN_STILL = new ResourceLocation(MODID,
            "block/fluids/molten_metal_still");
    public static final ResourceLocation MOLTEN_FLOW = new ResourceLocation(MODID,
            "block/fluids/molten_metal_flowing");

    public static final RegistryObject<ForgeFlowingFluid.Source> MITHRIL = FLUIDS.register("molten_mithril",
            () -> new ForgeFlowingFluid.Source(makeMoltenMithrilProperties()));
    public static final RegistryObject<ForgeFlowingFluid.Flowing> flowing_MITHRIL = FLUIDS.register("flowing_molten_mithril",
            () -> new ForgeFlowingFluid.Flowing(makeMoltenMithrilProperties()));
    public static final RegistryObject<ForgeFlowingFluid.Source> MORGULIRON = FLUIDS.register("molten_morguliron",
            () -> new ForgeFlowingFluid.Source(makeMoltenMorgulironProperties()));
    public static final RegistryObject<ForgeFlowingFluid.Flowing> flowing_MORGULIRON = FLUIDS.register("flowing_molten_morguliron",
            () -> new ForgeFlowingFluid.Flowing(makeMoltenMorgulironProperties()));

    private static ForgeFlowingFluid.Properties makeMoltenMithrilProperties() {
        return new ForgeFlowingFluid.Properties(MITHRIL, flowing_MITHRIL,
                FluidAttributes.builder(MOLTEN_STILL, MOLTEN_FLOW).overlay(MOLTEN_STILL).color(0xAAA9AD)
                        .luminosity(15).density(3000).viscosity(6000).temperature(1000).sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA))
                .bucket(TTMContent.MITHRIL_BUCKET).block(TTMContent.MOLTEN_MITHRIL).explosionResistance(1000F).tickRate(9);
    }

        private static ForgeFlowingFluid.Properties makeMoltenMorgulironProperties() {
            return new ForgeFlowingFluid.Properties(MORGULIRON, flowing_MORGULIRON,
                    FluidAttributes.builder(MOLTEN_STILL, MOLTEN_FLOW).overlay(MOLTEN_STILL).color(0x353F2A)
                            .luminosity(15).density(3000).viscosity(6000).temperature(1000).sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA))
                    .bucket(TTMContent.MORGULIRON_BUCKET).block(TTMContent.MOLTEN_MORGULIRON).explosionResistance(1000F).tickRate(9);
    }
}
