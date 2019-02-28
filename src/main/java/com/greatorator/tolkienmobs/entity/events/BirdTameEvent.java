package com.greatorator.tolkienmobs.entity.events;

import com.greatorator.tolkienmobs.entity.EntityTMBirds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class BirdTameEvent extends LivingEvent {
    private final EntityTMBirds theBird;
    private final EntityPlayer tamer;

    /**
     * Instantiates a new bird tame event.
     *
     * @param parBird the par bird
     * @param parTamer the par tamer
     */
    public BirdTameEvent(EntityTMBirds parBird, EntityPlayer parTamer)
    {
        super(parBird);
        theBird = parBird;
        tamer = parTamer;
    }

    /**
     * Gets the animal.
     *
     * @return the animal
     */
    public EntityTMBirds getAnimal()
    {
        return theBird;
    }

    /**
     * Gets the tamer.
     *
     * @return the tamer
     */
    public EntityPlayer getTamer()
    {
        return tamer;
    }
}