package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.particles.FellBeastBreathParticle;
import com.greatorator.tolkienmobs.particles.LeafParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

/**
 * Borrowed from brandon3055 on 23/4/2016.
 * A list of all of Tolkienmob's particles
 */
@Mod.EventBusSubscriber(modid = TolkienMobs.MODID, bus = MOD)
@ObjectHolder(TolkienMobs.MODID)
public class TolkienParticles {

    public static SimpleParticleType mirkwood_flame = new SimpleParticleType(false);
    public static SimpleParticleType culumalda_flame = new SimpleParticleType(false);
    public static SimpleParticleType lebethron_flame = new SimpleParticleType(false);
    public static SimpleParticleType deadwood_flame = new SimpleParticleType(false);
    public static SimpleParticleType fangornoak_flame = new SimpleParticleType(false);
    public static SimpleParticleType falling_leaves = new SimpleParticleType(false);
    public static SimpleParticleType fell_beast_breath = new SimpleParticleType(false);

    @SubscribeEvent
    public static void registerParticles(RegistryEvent.Register<ParticleType<?>> event) {
        event.getRegistry().register(mirkwood_flame.setRegistryName("mirkwood_flame"));
        event.getRegistry().register(culumalda_flame.setRegistryName("culumalda_flame"));
        event.getRegistry().register(lebethron_flame.setRegistryName("lebethron_flame"));
        event.getRegistry().register(deadwood_flame.setRegistryName("deadwood_flame"));
        event.getRegistry().register(fangornoak_flame.setRegistryName("fangornoak_flame"));
        event.getRegistry().register(falling_leaves.setRegistryName("falling_leaves"));
        event.getRegistry().register(fell_beast_breath.setRegistryName("fell_beast_breath"));
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerFactories(ParticleFactoryRegisterEvent event) {
        ParticleEngine manager = Minecraft.getInstance().particleEngine;
        manager.register(mirkwood_flame, FlameParticle.Provider::new);
        manager.register(culumalda_flame, FlameParticle.Provider::new);
        manager.register(lebethron_flame, FlameParticle.Provider::new);
        manager.register(deadwood_flame, FlameParticle.Provider::new);
        manager.register(fangornoak_flame, FlameParticle.Provider::new);
        manager.register(falling_leaves, LeafParticle.Provider::new);
        manager.register(fell_beast_breath, FellBeastBreathParticle.Provider::new);
    }
}
