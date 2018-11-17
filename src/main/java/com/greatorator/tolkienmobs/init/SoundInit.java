package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

@ObjectHolder(TolkienMobs.MODID)
public class SoundInit 
{
    // instantiate SoundEvents
	
// elephant
	@ObjectHolder("mob.elephant.say")
    public static final SoundEvent soundAmbientElephant = null;
	@ObjectHolder("mob.elephant.hurt")
    public static final SoundEvent soundHurtElephant = null;
//	@ObjectHolder("mob.elephant.hurt")
//    public static final SoundEvent soundDeathElephant = new SoundEvent(new ResourceLocation("wildanimals:mob.elephant.hurt"));
       
	/**
	 * Initialize this mod's {@link Block}s with any post-registration data.
	 */
	private static void initialize() 
	{
	}

	@Mod.EventBusSubscriber(modid = MainMod.MODID)
	public static class RegistrationHandler 
	{
		/**
		 * Register this mod's {@link SoundEvent}s.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void onEvent(final RegistryEvent.Register<SoundEvent> event) 
		{
			final String[] arraySoundEvents = {				    
				    // elephant
					"mob.elephant.say",
					"mob.elephant.hurt"
			};

			final IForgeRegistry<SoundEvent> registry = event.getRegistry();

	        System.out.println("Registering sound events");

	        for (final String soundName : arraySoundEvents) 
	        {
				registry.register(new SoundEvent(new ResourceLocation(MainMod.MODID, soundName)).setRegistryName(soundName));
			}

			initialize();
		}
	}
}
