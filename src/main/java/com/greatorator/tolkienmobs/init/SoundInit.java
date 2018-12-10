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
	
	/** Mumakil */
	@ObjectHolder("mob.mumakil.say")
    public static final SoundEvent soundAmbientMumakil = null;
	@ObjectHolder("mob.mumakil.hurt")
    public static final SoundEvent soundHurtMumakil = null;

	/** Crebain */
	@ObjectHolder("mob.crebain.hurt")
	public static final SoundEvent soundDeathCrebain = null;
	@ObjectHolder("mob.crebain.call")
	public static final SoundEvent soundCallCrebain = null;
	@ObjectHolder("mob.crebain.flapping")
	public static final SoundEvent soundFlappingCrebain = null;
	@ObjectHolder("mob.crebain.flapping")
	public static final SoundEvent soundHurtCrebain = null;

	/** Troll */
	@ObjectHolder("mob.troll.hurt")
	public static final SoundEvent soundHurtTroll = null;
	@ObjectHolder("mob.troll.idle")
	public static final SoundEvent soundIdleTroll = null;
	@ObjectHolder("mob.troll.death")
	public static final SoundEvent soundDeathTroll = null;
	@ObjectHolder("mob.troll.angry")
	public static final SoundEvent soundAngryTroll = null;
	@ObjectHolder("mob.troll.step")
	public static final SoundEvent soundStepTroll = null;

	/** Dwarf */
	@ObjectHolder("mob.dwarf.idle")
	public static final SoundEvent soundIdleDwarf = null;

	/** Goblin */
	@ObjectHolder("mob.goblin.idle")
	public static final SoundEvent soundIdleGoblin = null;

	/** Balrog */
	@ObjectHolder("mob.balrog.death")
	public static final SoundEvent soundDeathBalrog = null;
    @ObjectHolder("mob.balrog.idle")
    public static final SoundEvent soundIdleBalrog = null;
    @ObjectHolder("mob.balrog.hurt")
    public static final SoundEvent soundHurtBalrog = null;
    @ObjectHolder("mob.balrog.step")
    public static final SoundEvent soundStepBalrog = null;

	/** Gollum */
	@ObjectHolder("mob.gollum.death")
	public static final SoundEvent soundDeathGollum = null;
	@ObjectHolder("mob.gollum.idle")
	public static final SoundEvent soundIdleGollum = null;
	@ObjectHolder("mob.gollum.hurt")
	public static final SoundEvent soundHurtGollum = null;
	@ObjectHolder("mob.gollum.step")
	public static final SoundEvent soundStepGollum = null;

    /** Warg */
    @ObjectHolder("mob.warg.death")
    public static final SoundEvent soundDeathWarg = null;
    @ObjectHolder("mob.warg.idle")
    public static final SoundEvent soundIdleWarg = null;
    @ObjectHolder("mob.warg.hurt")
    public static final SoundEvent soundHurtWarg = null;

	/** Miscellaneous */
	@ObjectHolder("entity.boulder.shoot")
	public static final SoundEvent soundBoulderShoot = null;

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
				    /** Mumakil */
					"mob.mumakil.say",
					"mob.mumakil.hurt",

					/** Crebain */
					"mob.crebain.hurt",
					"mob.crebain.call",
					"mob.crebain.death",
					"mob.crebain.flapping",

					/** Troll */
					"mob.troll.hurt",
					"mob.troll.idle",
					"mob.troll.death",
					"mob.troll.angry",
					"mob.troll.step",

					/** Dwarf */
					"mob.dwarf.idle",

					/** Goblin */
					"mob.goblin.idle",

                    /** Balrog */
                    "mob.balrog.death",
                    "mob.balrog.idle",
                    "mob.balrog.hurt",
                    "mob.balrog.step",

					/** Gollum */
					"mob.gollum.death",
					"mob.gollum.idle",
					"mob.gollum.hurt",
					"mob.gollum.step",

                    /** Warg */
                    "mob.warg.death",
                    "mob.warg.idle",
                    "mob.warg.hurt",

					/** Miscellaneous */
					"entity.boulder.shoot"

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