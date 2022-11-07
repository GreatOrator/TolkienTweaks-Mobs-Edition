package com.greatorator.tolkienmobs.integration.tcon;

import com.greatorator.tolkienmobs.integration.tcon.traits.GuldurilModifier;
import com.greatorator.tolkienmobs.integration.tcon.traits.VingiloteModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.registration.ModelFluidAttributes;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class TConMaterialManager {
    public static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(MODID);
    public static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(MODID);

    // Fluids
    public static final FluidObject<ForgeFlowingFluid> MOLTEN_MITHRIL = FLUIDS.register("molten_mithril", ModelFluidAttributes.builder().temperature(1000).luminosity(15).sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA), Material.LAVA, 15);
    public static final FluidObject<ForgeFlowingFluid> MOLTEN_MORGULIRON = FLUIDS.register("molten_morguliron", ModelFluidAttributes.builder().temperature(1000).luminosity(15).sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA), Material.LAVA, 15);

    // Modifiers
    public static final StaticModifier<VingiloteModifier> VINGILOTE = MODIFIERS.register("vingilote", VingiloteModifier::new);
    public static final StaticModifier<GuldurilModifier> GULDURIL = MODIFIERS.register("gulduril", GuldurilModifier::new);

    // Materials
    public static final MaterialId SHINY = new MaterialId(new ResourceLocation(MODID,"shiny"));
    public static final MaterialId URUK = new MaterialId(new ResourceLocation(MODID,"uruk"));
}
