package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootInit {

    /** Entities */
    /** Boss */
    public static final ResourceLocation BALROG = register("entities/balrog");
    public static final ResourceLocation FELLBEAST = register("entities/fellbeast");
    public static final ResourceLocation WITCHKING = register("entities/witchking");

    /** Hostile */
    public static final ResourceLocation BWIGHT = register("entities/barrowwight");
    public static final ResourceLocation CREBAIN = register("entities/crebain");
    public static final ResourceLocation GOBLIN = register("entities/goblin");
    public static final ResourceLocation GOLLUM = register("entities/gollum");
    public static final ResourceLocation HURON = register("entities/huron");
    public static final ResourceLocation MSPIDER = register("entities/mirkwoodspider");
    public static final ResourceLocation MORC = register("entities/mordororc");
    public static final ResourceLocation MUMAKIL = register("entities/mumakil");
    public static final ResourceLocation TREEENT = register("entities/treeent");
    public static final ResourceLocation TROLL = register("entities/cavetroll");
    public static final ResourceLocation URUK = register("entities/urukhai");
    public static final ResourceLocation WARG = register("entities/warg");

    /** Passive */
    public static final ResourceLocation HUMAN = register("entities/human");
    public static final ResourceLocation ELVES = register("entities/elves");
    public static final ResourceLocation DWARVES = register("entities/dwarf");
    public static final ResourceLocation HOBBIT = register("entities/hobbit");
    public static final ResourceLocation AUROCH = register("entities/auroch");
    public static final ResourceLocation GOAT = register("entities/goat");
    public static final ResourceLocation TMFROG = register("entities/tmfrog");

    /** Structures */
    /** Chests */
    public static final ResourceLocation BARROW_CHEST = register("chests/barrow_chest");
    public static final ResourceLocation BARROW_GRAVE = register("chests/barrow_grave");

    private static ResourceLocation register(String id)
    {
        return LootTableList.register(new ResourceLocation(TolkienMobs.MODID, id));
    }
}
