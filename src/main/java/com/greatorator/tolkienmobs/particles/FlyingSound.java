package com.greatorator.tolkienmobs.particles;

import com.greatorator.tolkienmobs.entity.DragonEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

public class FlyingSound extends AbstractTickableSoundInstance
{
    private final DragonEntity entity;
    private int time;

    public FlyingSound(DragonEntity entity)
    {
        super(SoundEvents.ELYTRA_FLYING, SoundSource.PLAYERS);
        this.entity = entity;
        this.looping = true;
        this.delay = 0;
        this.volume = 0.1f;
    }

    public void tick()
    {
        if (++time < 20)
        {
            volume = 0;
            pitch = 1;
            return;
        }
        if (entity.isAlive() && entity.isUsingFlyingNavigator())
        {
            x = (float) entity.getX();
            y = (float) entity.getY();
            z = (float) entity.getZ();
            double x = entity.getX() - entity.xOld;
            double z = entity.getZ() - entity.zOld;
            double length = x * x + z * z;
            volume = Math.min((float) length * 2f, 0.75f);
            if (volume > 0.4f) pitch = 1f + (volume - 0.6f);
            else pitch = 1f;
        }
        else stop();
    }

    public static void play(DragonEntity dragon)
    {
        Minecraft.getInstance().getSoundManager().play(new FlyingSound(dragon));
    }
}
