package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
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
public class TTMParticles {

    public static BasicParticleType mirkwood_flame = new BasicParticleType(false);
    public static BasicParticleType culumalda_flame = new BasicParticleType(false);
    public static BasicParticleType lebethron_flame = new BasicParticleType(false);

    @SubscribeEvent
    public static void registerParticles(RegistryEvent.Register<ParticleType<?>> event) {
        event.getRegistry().register(mirkwood_flame.setRegistryName("mirkwood_flame"));
        event.getRegistry().register(culumalda_flame.setRegistryName("culumalda_flame"));
        event.getRegistry().register(lebethron_flame.setRegistryName("lebethron_flame"));
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerFactories(ParticleFactoryRegisterEvent event) {
        ParticleManager manager = Minecraft.getInstance().particleEngine;
        manager.register(mirkwood_flame, FlameParticle.Factory::new);
        manager.register(culumalda_flame, FlameParticle.Factory::new);
        manager.register(lebethron_flame, FlameParticle.Factory::new);
    }
}
