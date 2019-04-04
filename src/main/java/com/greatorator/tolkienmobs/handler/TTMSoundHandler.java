package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class TTMSoundHandler {
    private static List<SoundEvent> sounds = new ArrayList<SoundEvent>();

    public static void registerSound(SoundEvent sound, String name)
    {
        sound.setRegistryName(TolkienMobs.MODID + ":" + name);
        sounds.add(sound);
    }

    @SubscribeEvent
    public static void registerSounds(final RegistryEvent.Register<SoundEvent> event)
    {
        event.getRegistry().registerAll(sounds.toArray(new SoundEvent[sounds.size()]));
    }
}
