package com.greatorator.tolkienmobs.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;
import static com.greatorator.tolkienmobs.TolkienMobs.NAME;

public class TolkienSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MODID);

    // Sounds - Music
    public static final RegistryObject<SoundEvent> ridersofrivendell = addSound("music.ridersofrivendell");
    public static final RegistryObject<SoundEvent> thelightoflothlorien = addSound("music.thelightoflothlorien");
    public static final RegistryObject<SoundEvent> allthatglittersinerebor = addSound("music.allthatglittersinerebor");
    public static final RegistryObject<SoundEvent> willowsong = addSound("music.willowsong");
    public static final RegistryObject<SoundEvent> minastirith = addSound("music.minastirith");
    public static final RegistryObject<SoundEvent> wakeofedoras = addSound("music.wakeofedoras");
    public static final RegistryObject<SoundEvent> witchbattle = addSound("music.witchbattle");
    public static final RegistryObject<SoundEvent> murderfrog = addSound("music.murderfrog");
    public static final RegistryObject<SoundEvent> rederssong = addSound("music.rederssong");
    public static final RegistryObject<SoundEvent> trollfumble = addSound("music.trollfumble");
    public static final RegistryObject<SoundEvent> mysteryoftombombadil = addSound("music.mysteryoftombombadil");
    public static final RegistryObject<SoundEvent> concerninghobbits = addSound("music.concerninghobbits");

    //Sounds - Blocks, Miscellaneous & Ambient
    public static final RegistryObject<SoundEvent> sound_Boulder_Shoot = addSound("entity.boulder.shoot");
    public static final RegistryObject<SoundEvent> sound_demonic_laughter = addSound("mob.demonlaugh");
    public static final RegistryObject<SoundEvent> sound_evil_witch_laugh = addSound("mob.evilwitchlaugh");
    public static final RegistryObject<SoundEvent> sound_ghostly_lament = addSound("mob.ghostlylament");
    public static final RegistryObject<SoundEvent> block_cathedralbell = addSound("block.cathedralbell");
    public static final RegistryObject<SoundEvent> khazaddum = addSound("ambient.khazaddum");
    public static final RegistryObject<SoundEvent> waterworks = addSound("ambient.waterworks");
    public static final RegistryObject<SoundEvent> medieval_city = addSound("ambient.medievalcity");
    public static final RegistryObject<SoundEvent> medieval_city_market = addSound("ambient.medievalcitymarket");
    public static final RegistryObject<SoundEvent> medieval_city_tavern = addSound("ambient.medievaltavern");
    public static final RegistryObject<SoundEvent> medieval_city_seaport = addSound("ambient.medievalseaport");
    public static final RegistryObject<SoundEvent> paths_of_the_dead = addSound("ambient.pathsofthedead");
    public static final RegistryObject<SoundEvent> underworld = addSound("ambient.underworld");
    public static final RegistryObject<SoundEvent> hype_horn = addSound("hype_horn");
    public static final RegistryObject<SoundEvent> lightningbug_ambient = addSound("lightningbug_ambient");
    public static final RegistryObject<SoundEvent> arda_portal = addSound("arda_portal");

    //Sounds - Mumakil
    public static final RegistryObject<SoundEvent> soundAmbientMumakil = addSound("mob.mumakil.say");
    public static final RegistryObject<SoundEvent> soundHurtMumakil = addSound("mob.mumakil.hurt");


    //Sounds - Witch King
    public static final RegistryObject<SoundEvent> soundStepWitchKing = addSound("mob.witchking.step");
    public static final RegistryObject<SoundEvent> soundAngryWitchKing = addSound("mob.witchking.angry");
    public static final RegistryObject<SoundEvent> soundDeathWitchKing = addSound("mob.witchking.death");
    public static final RegistryObject<SoundEvent> soundIdleWitchKing = addSound("mob.witchking.idle");
    public static final RegistryObject<SoundEvent> soundHurtWitchKing = addSound("mob.witchking.hurt");

    //Sounds - Fell Beast
    public static final RegistryObject<SoundEvent> soundIdleFellBeast = addSound("mob.fellbeast.idle");
    public static final RegistryObject<SoundEvent> soundFlapFellBeast = addSound("mob.fellbeast.flap");


    //Sounds - Barrow Wight
    public static final RegistryObject<SoundEvent> soundIdleBarrowWight = addSound("mob.barrowwight.idle");
    public static final RegistryObject<SoundEvent> soundHurtBarrowWight = addSound("mob.barrowwight.hurt");

    //Sounds - Oath Breaker
    public static final RegistryObject<SoundEvent> soundIdleOathbreaker = addSound("mob.oathbreaker.idle");

    //Sounds - Romie Walker
    public static final RegistryObject<SoundEvent> soundIdleRomieWalker = addSound("mob.romiewalker.idle");
    public static final RegistryObject<SoundEvent> soundHurtRomieWalker = addSound("mob.romiewalker.hurt");
    public static final RegistryObject<SoundEvent> soundAngryRomieWalker = addSound("mob.romiewalker.angry");
    public static final RegistryObject<SoundEvent> soundDeathRomieWalker = addSound("mob.romiewalker.death");

    //Sounds - Mordor Orc & Uruk-hai
    public static final RegistryObject<SoundEvent> soundIdleOrc = addSound("mob.orc.idle");

    //Sounds - Watcher of the Water
    public static final RegistryObject<SoundEvent> soundIdleWatcher = addSound("mob.watcher.idle");
    public static final RegistryObject<SoundEvent> soundDeathWatcher = addSound("mob.watcher.death");

    //Sounds - Elemental Golem
    public static final RegistryObject<SoundEvent> soundDeathGolem = addSound("mob.elementalgolem.death");

    //Sounds - Crebain
    public static final RegistryObject<SoundEvent> soundDeathCrebain = addSound("mob.crebain.death");
    public static final RegistryObject<SoundEvent> soundCallCrebain = addSound("mob.crebain.call");
    public static final RegistryObject<SoundEvent> soundFlappingCrebain = addSound("mob.crebain.flapping");
    public static final RegistryObject<SoundEvent> soundHurtCrebain = addSound("mob.crebain.hurt");

    //Sounds - Great Eagle
    public static final RegistryObject<SoundEvent> soundDeathTMGreatEagle = addSound("mob.tmgreateagle.death");
    public static final RegistryObject<SoundEvent> soundCallTMGreatEagle = addSound("mob.tmgreateagle.call");
    public static final RegistryObject<SoundEvent> soundFlappingTMGreatEagle = addSound("mob.tmgreateagle.flapping");
    public static final RegistryObject<SoundEvent> soundHurtTMGreatEagle = addSound("mob.tmgreateagle.hurt");
    public static final RegistryObject<SoundEvent> soundAttackTMGreatEagle = addSound("mob.tmgreateagle.attack");

    //Sounds - Troll
    public static final RegistryObject<SoundEvent> soundHurtTroll = addSound("mob.troll.hurt");
    public static final RegistryObject<SoundEvent> soundIdleTroll = addSound("mob.troll.idle");
    public static final RegistryObject<SoundEvent> soundDeathTroll = addSound("mob.troll.death");
    public static final RegistryObject<SoundEvent> soundStepTroll = addSound("mob.troll.step");

    //Sounds - Dwarf
    public static final RegistryObject<SoundEvent> soundIdleDwarf = addSound("mob.dwarf.idle");
    public static final RegistryObject<SoundEvent> soundAngryDwarf = addSound("mob.dwarf.angry");
    public static final RegistryObject<SoundEvent> soundHurtDwarf = addSound("mob.dwarf.hurt");
    public static final RegistryObject<SoundEvent> soundDeathDwarf = addSound("mob.dwarf.death");

    //Sounds - Midge Fly
    public static final RegistryObject<SoundEvent> soundIdleMidgeFly = addSound("mob.midgefly.idle");

    //Sounds - Goblin
    public static final RegistryObject<SoundEvent> soundIdleGoblin = addSound("mob.goblin.idle");
    public static final RegistryObject<SoundEvent> soundHurtGoblin = addSound("mob.goblin.hurt");
    public static final RegistryObject<SoundEvent> soundDeathGoblin = addSound("mob.goblin.death");
    public static final RegistryObject<SoundEvent> soundAngryGoblin = addSound("mob.goblin.angry");

    //Sounds - Rat
    public static final RegistryObject<SoundEvent> soundIdleTMRat = addSound("mob.entityttmrat.idle");
    public static final RegistryObject<SoundEvent> soundHurtTMRat = addSound("mob.entityttmrat.hurt");
    public static final RegistryObject<SoundEvent> soundDeathTMRat = addSound("mob.entityttmrat.death");

    //Sounds - Thrush
    public static final RegistryObject<SoundEvent> soundIdleTMThrush = addSound("mob.tmthrush.idle");
    public static final RegistryObject<SoundEvent> soundHurtTMThrush = addSound("mob.tmthrush.hurt");
    public static final RegistryObject<SoundEvent> soundDeathTMThrush = addSound("mob.tmthrush.death");

    //Sounds - Balrog
    public static final RegistryObject<SoundEvent> soundDeathBalrog = addSound("mob.balrog.death");
    public static final RegistryObject<SoundEvent> soundIdleBalrog = addSound("mob.balrog.idle");
    public static final RegistryObject<SoundEvent> soundHurtBalrog = addSound("mob.balrog.hurt");
    public static final RegistryObject<SoundEvent> soundStepBalrog = addSound("mob.balrog.step");

    //Sounds - Minotaur
    public static final RegistryObject<SoundEvent> soundDeathMinotaur = addSound("mob.minotaur.death");
    public static final RegistryObject<SoundEvent> soundIdleMinotaur = addSound("mob.minotaur.idle");
    public static final RegistryObject<SoundEvent> soundHurtMinotaur = addSound("mob.minotaur.hurt");
    public static final RegistryObject<SoundEvent> soundStepMinotaur = addSound("mob.minotaur.step");

    //Sounds - Gollum
    public static final RegistryObject<SoundEvent> soundDeathGollum = addSound("mob.gollum.death");
    public static final RegistryObject<SoundEvent> soundIdleGollum = addSound("mob.gollum.idle");
    public static final RegistryObject<SoundEvent> soundHurtGollum = addSound("mob.gollum.hurt");

    //Sounds - Warg
    public static final RegistryObject<SoundEvent> soundDeathWarg = addSound("mob.warg.death");
    public static final RegistryObject<SoundEvent> soundIdleWarg = addSound("mob.warg.idle");
    public static final RegistryObject<SoundEvent> soundHurtWarg = addSound("mob.warg.hurt");

    //Sounds - Mimic
    public static final RegistryObject<SoundEvent> soundStepMimic = addSound("mob.mimic.step");
    public static final RegistryObject<SoundEvent> soundAngryMimic = addSound("mob.mimic.angry");

    //Sounds - Goat
    public static final RegistryObject<SoundEvent> soundDeathGoat = addSound("mob.goat.death");
    public static final RegistryObject<SoundEvent> soundIdleGoat = addSound("mob.goat.idle");
    public static final RegistryObject<SoundEvent> soundHurtGoat = addSound("mob.goat.hurt");
    public static final RegistryObject<SoundEvent> soundAngryGoat = addSound("mob.goat.angry");

    //Sounds - Toaddle
    public static final RegistryObject<SoundEvent> soundDeathToaddle = addSound("mob.toaddle.death");
    public static final RegistryObject<SoundEvent> soundIdleToaddle = addSound("mob.toaddle.idle");
    public static final RegistryObject<SoundEvent> soundHurtToaddle = addSound("mob.toaddle.hurt");
    public static final RegistryObject<SoundEvent> soundStepToaddle = addSound("mob.toaddle.step");
    public static final RegistryObject<SoundEvent> soundAngryToaddle = addSound("mob.toaddle.angry");

    //Sounds - SOSSquirrel
    public static final RegistryObject<SoundEvent> soundDeathSOSquirrel = addSound("mob.sosquirrel.death");
    public static final RegistryObject<SoundEvent> soundIdleSOSquirrel = addSound("mob.sosquirrel.idle");
    public static final RegistryObject<SoundEvent> soundHurtSOSquirrel = addSound("mob.sosquirrel.hurt");
    public static final RegistryObject<SoundEvent> soundStepSOSquirrel = addSound("mob.sosquirrel.step");
    public static final RegistryObject<SoundEvent> soundAngrySOSquirrel = addSound("mob.sosquirrel.angry");

    static RegistryObject<SoundEvent> addSound(String name)
    {
        SoundEvent event = new SoundEvent(new ResourceLocation(MODID, name));
        return SOUND_EVENTS.register(name, () -> event);
    }

    public String getName() {
        return NAME + " - Sounds";
    }
}
