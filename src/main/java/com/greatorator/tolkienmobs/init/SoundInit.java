package com.greatorator.tolkienmobs.init;

import com.google.common.collect.Lists;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.handler.TTMSoundHandler;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

import java.util.List;

public class SoundInit {
    /** Mumakil */
    public static final SoundEvent soundAmbientMumakil = create("mob.mumakil.say");
    public static final SoundEvent soundHurtMumakil = create("mob.mumakil.hurt");

    /** Witch-king */
    public static final SoundEvent soundStepWitchKing = create("mob.witchking.step");
    public static final SoundEvent soundAngryWitchKing = create("mob.witchking.angry");
    public static final SoundEvent soundDeathWitchKing = create("mob.witchking.death");
    public static final SoundEvent soundIdleWitchKing = create("mob.witchking.idle");
    public static final SoundEvent soundHurtWitchKing = create("mob.witchking.hurt");

    /** Fell-beast */
    public static final SoundEvent soundIdleFellBeast = create("mob.fellbeast.idle");
    public static final SoundEvent soundFlapFellBeast = create("mob.fellbeast.flap");

    /** Barrow Wight */
    public static final SoundEvent soundIdleBarrowWight = create("mob.barrowwight.idle");
    public static final SoundEvent soundHurtBarrowWight = create("mob.barrowwight.hurt");

    /** Oath Breaker */
    public static final SoundEvent soundIdleOathbreaker = create("mob.oathbreaker.idle");

    /** Romie Walker */
    public static final SoundEvent soundIdleRomieWalker = create("mob.romiewalker.idle");
    public static final SoundEvent soundHurtRomieWalker = create("mob.romiewalker.hurt");
    public static final SoundEvent soundAngryRomieWalker = create("mob.romiewalker.angry");
    public static final SoundEvent soundDeathRomieWalker = create("mob.romiewalker.death");

    /** Mordor Orc & Uruk-hai */
    public static final SoundEvent soundIdleOrc = create("mob.orc.idle");

    /** Barrow Wight */
    public static final SoundEvent soundIdleWatcher = create("mob.watcher.idle");
    public static final SoundEvent soundDeathWatcher = create("mob.watcher.death");

    /** Elemental Golem */
    public static final SoundEvent soundDeathGolem = create("mob.elementalgolem.death");

    /** Crebain */
    public static final SoundEvent soundDeathCrebain = create("mob.crebain.death");
    public static final SoundEvent soundCallCrebain = create("mob.crebain.call");
    public static final SoundEvent soundFlappingCrebain = create("mob.crebain.flapping");
    public static final SoundEvent soundHurtCrebain = create("mob.crebain.hurt");

    /** Great Eagle */
    public static final SoundEvent soundDeathTMGreatEagle = create("mob.tmgreateagle.death");
    public static final SoundEvent soundCallTMGreatEagle = create("mob.tmgreateagle.call");
    public static final SoundEvent soundFlappingTMGreatEagle = create("mob.tmgreateagle.flapping");
    public static final SoundEvent soundHurtTMGreatEagle = create("mob.tmgreateagle.hurt");

    /** Troll */
    public static final SoundEvent soundHurtTroll = create("mob.troll.hurt");
    public static final SoundEvent soundIdleTroll = create("mob.troll.idle");
    public static final SoundEvent soundDeathTroll = create("mob.troll.death");
    public static final SoundEvent soundStepTroll = create("mob.troll.step");

    /** Dwarf */
    public static final SoundEvent soundIdleDwarf = create("mob.dwarf.idle");
    public static final SoundEvent soundAngryDwarf = create("mob.dwarf.angry");
    public static final SoundEvent soundHurtDwarf = create("mob.dwarf.hurt");
    public static final SoundEvent soundDeathDwarf = create("mob.dwarf.death");

    /** Midge Fly */
    public static final SoundEvent soundIdleMidgeFly = create("mob.midgefly.idle");

    /** Goblin */
    public static final SoundEvent soundIdleGoblin = create("mob.goblin.idle");
    public static final SoundEvent soundHurtGoblin = create("mob.goblin.hurt");
    public static final SoundEvent soundDeathGoblin = create("mob.goblin.death");
    public static final SoundEvent soundAngryGoblin = create("mob.goblin.angry");

    /** Rat */
    public static final SoundEvent soundIdleTMRat = create("mob.tmrat.idle");
    public static final SoundEvent soundHurtTMRat = create("mob.tmrat.hurt");
    public static final SoundEvent soundDeathTMRat = create("mob.tmrat.death");

    /** Rat */
    public static final SoundEvent soundIdleTMThrush = create("mob.tmthrush.idle");
    public static final SoundEvent soundHurtTMThrush = create("mob.tmthrush.hurt");
    public static final SoundEvent soundDeathTMThrush = create("mob.tmthrush.death");

    /** Balrog */
    public static final SoundEvent soundDeathBalrog = create("mob.balrog.death");
    public static final SoundEvent soundIdleBalrog = create("mob.balrog.idle");
    public static final SoundEvent soundHurtBalrog = create("mob.balrog.hurt");
    public static final SoundEvent soundStepBalrog = create("mob.balrog.step");

    /** Minotaur */
    public static final SoundEvent soundDeathMinotaur = create("mob.minotaur.death");
    public static final SoundEvent soundIdleMinotaur = create("mob.minotaur.idle");
    public static final SoundEvent soundHurtMinotaur = create("mob.minotaur.hurt");
    public static final SoundEvent soundStepMinotaur = create("mob.minotaur.step");

    /** Gollum */
    public static final SoundEvent soundDeathGollum = create("mob.gollum.death");
    public static final SoundEvent soundIdleGollum = create("mob.gollum.idle");
    public static final SoundEvent soundHurtGollum = create("mob.gollum.hurt");

    /** Warg */
    public static final SoundEvent soundDeathWarg = create("mob.warg.death");
    public static final SoundEvent soundIdleWarg = create("mob.warg.idle");
    public static final SoundEvent soundHurtWarg = create("mob.warg.hurt");

    /** Mimic */
    public static final SoundEvent soundStepMimic = create("mob.mimic.step");
    public static final SoundEvent soundAngryMimic = create("mob.mimic.angry");

    /** Goat */
    public static final SoundEvent soundDeathGoat = create("mob.goat.death");
    public static final SoundEvent soundIdleGoat = create("mob.goat.idle");
    public static final SoundEvent soundHurtGoat = create("mob.goat.hurt");
    public static final SoundEvent soundAngryGoat = create("mob.goat.angry");

    /** Toaddle */
    public static final SoundEvent soundDeathToaddle = create("mob.toaddle.death");
    public static final SoundEvent soundIdleToaddle = create("mob.toaddle.idle");
    public static final SoundEvent soundHurtToaddle = create("mob.toaddle.hurt");
    public static final SoundEvent soundStepToaddle = create("mob.toaddle.step");
    public static final SoundEvent soundAngryToaddle = create("mob.toaddle.angry");

    /** SOSquirrel */
    public static final SoundEvent soundDeathSOSquirrel = create("mob.sosquirrel.death");
    public static final SoundEvent soundIdleSOSquirrel = create("mob.sosquirrel.idle");
    public static final SoundEvent soundHurtSOSquirrel = create("mob.sosquirrel.hurt");
    public static final SoundEvent soundStepSOSquirrel = create("mob.sosquirrel.step");
    public static final SoundEvent soundAngrySOSquirrel = create("mob.sosquirrel.angry");

    /** Miscellaneous */
    public static final SoundEvent soundBoulderShoot = create("entity.boulder.shoot");
    public static final SoundEvent sounddemoniclaughter = create("mob.demonlaugh");
    public static final SoundEvent soundevilwitchlaugh = create("mob.evilwitchlaugh");
    public static final SoundEvent soundghostlylament = create("mob.ghostlylament");

    /** Music */
    static final SoundEvent ridersofrivendell = create("music.ridersofrivendell");
    static final SoundEvent thelightoflothlorien = create("music.thelightoflothlorien");
    static final SoundEvent allthatglittersinerebor = create("music.allthatglittersinerebor");
    static final SoundEvent willowsong = create("music.willowsong");
    static final SoundEvent minastirith = create("music.minastirith");
    static final SoundEvent wakeofedoras = create("music.wakeofedoras");
    static final SoundEvent witchbattle = create("music.witchbattle");
    static final SoundEvent murderfrog = create("music.murderfrog");
    static final SoundEvent rederssong = create("music.rederssong");
    static final SoundEvent trollfumble = create("music.trollfumble");
    static final SoundEvent mysteryoftombombadil = create("music.mysteryoftombombadil");
    static final SoundEvent concerninghobbits = create("music.concerninghobbits");

    /** Ambient */
    public static final SoundEvent khazaddum = create("ambient.khazaddum");
    public static final SoundEvent waterworks = create("ambient.waterworks");
    public static final SoundEvent medievalcity = create("ambient.medievalcity");
    public static final SoundEvent medievalcitymarket = create("ambient.medievalcitymarket");
    public static final SoundEvent medievaltavern = create("ambient.medievaltavern");
    public static final SoundEvent medievalseaport = create("ambient.medievalseaport");
    public static final SoundEvent pathsofthedead = create("ambient.pathsofthedead");
    public static final SoundEvent underworld = create("ambient.underworld");

    /** Blocks */
    public static final SoundEvent cathedralbell = create("block.cathedralbell");

    private static List<SoundEvent> sounds = Lists.newArrayList();

    /**
     * Initialize this mod's {@link Block}s with any post-registration data.
     */
    public static SoundEvent create(String soundName) {
        SoundEvent sound = new SoundEvent(new ResourceLocation(TolkienMobs.MODID, soundName));
        TTMSoundHandler.registerSound(sound, soundName);
        return sound;
    }

    public static SoundEvent[] createArray(String... soundNames) {
        SoundEvent[] sounds = new SoundEvent[soundNames.length];
        for(int i = 0; i < soundNames.length; i++) {
            SoundEvent sound = new SoundEvent(new ResourceLocation(TolkienMobs.MODID, soundNames[i]));
            TTMSoundHandler.registerSound(sound, soundNames[i]);
            sounds[i] = sound;
        }

        return sounds;
    }

    public static List<SoundEvent> getSounds()
    {
        return sounds;
    }
}