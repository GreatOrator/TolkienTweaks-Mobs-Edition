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
	@ObjectHolder("mob.demoniclaughter")
	public static final SoundEvent sounddemoniclaughter = null;
	@ObjectHolder("mob.evilwitchlaugh")
	public static final SoundEvent soundevilwitchlaugh = null;
	@ObjectHolder("mob.ghostlylament")
	public static final SoundEvent soundghostlylament = null;

	/** Goat */
	@ObjectHolder("mob.goat.death")
	public static final SoundEvent soundDeathGoat = null;
	@ObjectHolder("mob.goat.idle")
	public static final SoundEvent soundIdleGoat = null;
	@ObjectHolder("mob.goat.hurt")
	public static final SoundEvent soundHurtGoat = null;
	@ObjectHolder("mob.goat.angry")
	public static final SoundEvent soundAngryGoat = null;

	/** TMFrog */
	@ObjectHolder("mob.tmfrog.death")
	public static final SoundEvent soundDeathTMFrog = null;
	@ObjectHolder("mob.tmfrog.idle")
	public static final SoundEvent soundIdleTMFrog = null;
	@ObjectHolder("mob.tmfrog.hurt")
	public static final SoundEvent soundHurtTMFrog = null;
	@ObjectHolder("mob.tmfrog.step")
	public static final SoundEvent soundStepTMFrog = null;

	/** Music */
	@ObjectHolder("music.allthatglittersinerebor")
	public static final SoundEvent soundallthatglittersinerebor = null;
	@ObjectHolder("music.willowsong")
	public static final SoundEvent soundwillowsong = null;
	@ObjectHolder("music.minastirith")
	public static final SoundEvent soundminastirith = null;
	@ObjectHolder("music.ridersofrivendell")
	public static final SoundEvent soundridersofrivendell = null;
	@ObjectHolder("music.thelightoflothlorien")
	public static final SoundEvent soundthelightoflothlorien = null;
	@ObjectHolder("music.wakeofedoras")
	public static final SoundEvent soundwakeofedoras = null;
	@ObjectHolder("music.witchbattle")
	public static final SoundEvent soundwitchbattle = null;
	@ObjectHolder("music.murderfrog")
	public static final SoundEvent soundmurderfrog = null;
	@ObjectHolder("music.rederssong")
	public static final SoundEvent soundrederssong = null;
	@ObjectHolder("music.trollfumble")
	public static final SoundEvent soundtrollfumble = null;
	@ObjectHolder("music.mysteryoftombombadil")
	public static final SoundEvent soundmysteryoftombombadil = null;
	@ObjectHolder("music.concerninghobbits")
	public static final SoundEvent soundconcerninghobbits = null;

	/** Ambient */
	@ObjectHolder("ambient.khazaddum")
	public static final SoundEvent soundkhazaddum = null;
	@ObjectHolder("ambient.waterworks")
	public static final SoundEvent soundwaterworks = null;
	@ObjectHolder("ambient.medievalcity")
	public static final SoundEvent soundmedievalcity = null;
	@ObjectHolder("ambient.medievalcitymarket")
	public static final SoundEvent soundmedievalcitymarket = null;
	@ObjectHolder("ambient.medievaltavern")
	public static final SoundEvent soundmedievaltavern = null;
	@ObjectHolder("ambient.medievalseaport")
	public static final SoundEvent soundmedievalseaport = null;
	@ObjectHolder("ambient.pathsofthedead")
	public static final SoundEvent soundpathsofthedead = null;
	@ObjectHolder("ambient.underworld")
	public static final SoundEvent soundunderworld = null;

	/** Blocks */
	@ObjectHolder("block.cathedralbell")
	public static final SoundEvent soundcathedralbell = null;

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

					/** Goat */
					"mob.goat.death",
					"mob.goat.idle",
					"mob.goat.hurt",
					"mob.goat.angry",
					"mob.goat.step",

					/** TMFrog */
					"mob.tmfrog.death",
					"mob.tmfrog.idle",
					"mob.tmfrog.hurt",
					"mob.tmfrog.step",

					/** Gollum */
					"mob.gollum.death",
					"mob.gollum.idle",
					"mob.gollum.hurt",

                    /** Warg */
                    "mob.warg.death",
                    "mob.warg.idle",
                    "mob.warg.hurt",

					/** Miscellaneous */
					"entity.boulder.shoot",
					"mob.demonlaugh",
					"mob.evilwitchlaugh",
					"mob.ghostlylament",


					/** Music */
					"music.allthatglittersinerebor",
					"music.willowsong",
					"music.minastirith",
					"music.ridersofrivendell",
					"music.thelightoflothlorien",
					"music.wakeofedoras",
					"music.witchbattle",
					"music.murderfrog",
					"music.rederssong",
					"music.trollfumble",
					"music.mysteryoftombombadil",
					"music.concerninghobbits",

					/** Ambient */
					"ambient.khazaddum",
					"ambient.waterworks",
					"ambient.medievalcity",
					"ambient.medievalcitymarket",
					"ambient.medievaltavern",
					"ambient.medievalseaport",
					"ambient.pathsofthedead",
					"ambient.underworld",

					/** Blocks */
					"block.cathedralbell"

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