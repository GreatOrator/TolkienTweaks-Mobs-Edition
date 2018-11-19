package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
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
	@ObjectHolder("mob.mumakil.say")
    public static final SoundEvent soundAmbientMumakil = null;
	@ObjectHolder("mob.mumakil.hurt")
    public static final SoundEvent soundHurtMumakil = null;
       
	/**
	 * Initialize this mod's {@link Block}s with any post-registration data.
	 */
	private static void initialize() 
	{
	}

	@Mod.EventBusSubscriber(modid = TolkienMobs.MODID)
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
					"mob.mumakil.say",
					"mob.mumakil.hurt"
			};

			final IForgeRegistry<SoundEvent> registry = event.getRegistry();

			LogHelperTTM.info("Composing the sounds of the Aiur...");

	        for (final String soundName : arraySoundEvents) 
	        {
				registry.register(new SoundEvent(new ResourceLocation(TolkienMobs.MODID, soundName)).setRegistryName(soundName));
			}

			initialize();
		}
	}
}
