package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootInit {

    /* Entities */
    /** Boss */
    public static final ResourceLocation BALROG = register("entities/balrog");
    public static final ResourceLocation FELLBEAST = register("entities/fellbeast");
    public static final ResourceLocation WITCHKING = register("entities/witchking");
    public static final ResourceLocation GWAIHIR = register("entities/gwaihir");
    public static final ResourceLocation WATCHER = register("entities/tmwatcher");

    /** Hostile */
    public static final ResourceLocation BWIGHT = register("entities/barrowwight");
    public static final ResourceLocation GOBLIN = register("entities/goblin");
    public static final ResourceLocation HURON = register("entities/huron");
    public static final ResourceLocation MSPIDER = register("entities/mirkwoodspider");
    public static final ResourceLocation MORC = register("entities/mordororc");
    public static final ResourceLocation TREEENT = register("entities/treeent");
    public static final ResourceLocation TROLL = register("entities/cavetroll");
    public static final ResourceLocation URUK = register("entities/urukhai");
    public static final ResourceLocation WARG = register("entities/warg");
    public static final ResourceLocation OATHBREAKER = register("entities/oathbreaker");
    public static final ResourceLocation MIMICCHEST = register("entities/mimicchest");
    public static final ResourceLocation MINOTAUR = register("entities/minotaur");
    public static final ResourceLocation BRIGAND = register("entities/tmbrigand");

    /** Special */
    public static final ResourceLocation GOLLUM = register("entities/gollum");
    public static final ResourceLocation NAZGUL = register("entities/nazgul");
    public static final ResourceLocation GOLEM_STONE = register("entities/golem_stone");
    public static final ResourceLocation GOLEM_STONE_EARTH = register("entities/golem_stone_earth");
    public static final ResourceLocation GOLEM_STONE_AIR = register("entities/golem_stone_air");
    public static final ResourceLocation GOLEM_STONE_FIRE = register("entities/golem_stone_fire");
    public static final ResourceLocation GOLEM_STONE_WATER = register("entities/golem_stone_water");
    public static final ResourceLocation GOLEM_STONE_MITHRIL = register("entities/golem_stone_mithril");
    public static final ResourceLocation GOLEM_STONE_MORGUL = register("entities/golem_stone_morgul");

    /** Passive */
    public static final ResourceLocation AUROCH = register("entities/auroch");
    public static final ResourceLocation DWARVES = register("entities/dwarf");
    public static final ResourceLocation ELVES = register("entities/elves");
    public static final ResourceLocation GOAT = register("entities/goat");
    public static final ResourceLocation HOBBIT = register("entities/hobbit");
    public static final ResourceLocation HUMAN = register("entities/human");
    public static final ResourceLocation MUMAKIL = register("entities/mumakil");

    /** Ambient */
    public static final ResourceLocation TMFROG = register("entities/toaddle");
    public static final ResourceLocation SOSQUIRREL = register("entities/sosquirrel");
    public static final ResourceLocation THRUSH = register("entities/thrush");
    public static final ResourceLocation MIDGEFLY = register("entities/midgefly");
    public static final ResourceLocation CREBAIN = register("entities/crebain");
    public static final ResourceLocation TMRAT = register("entities/tmrat");

    /* Structures */
    /** Chests */
    public static final ResourceLocation BARROW_CHEST = register("chests/barrow_chest");
    public static final ResourceLocation BARROW_GRAVE = register("chests/barrow_grave");
    public static final ResourceLocation HOBBIT_GROCER = register("chests/hobbit_grocer");
    public static final ResourceLocation HOBBIT_HOUSE = register("chests/hobbit_house");
    public static final ResourceLocation REMOTE_RUINS = register("chests/desolate_ruins");

    private static ResourceLocation register(String id)
    {
        return LootTableList.register(new ResourceLocation(TolkienMobs.MODID, id));
    }
}
