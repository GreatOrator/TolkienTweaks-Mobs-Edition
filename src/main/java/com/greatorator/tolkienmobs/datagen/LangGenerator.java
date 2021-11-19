package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.data.LanguageProvider;

/**
 * Created by brandon3055 on 21/5/20.
 */
public class LangGenerator extends LanguageProvider {
    public LangGenerator(DataGenerator gen) {
        super(gen, TolkienMobs.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        PrefixHelper helper = new PrefixHelper(this);
        blocks();
        items();
        quest();
        food();
        potion();
        coinToken();
        enchants();
        records();
        tools();
        entities();
        merchants(helper);
        trinket(helper);
        biomes(helper);
        chatMessages(helper);
        creativeTabGroups();
    }

    private void blocks() {
        // Metals & Gems
        add(TTMContent.ORE_MITHRIL.get(), "Mithril Ore");
        add(TTMContent.ORE_END_MITHRIL.get(), "Ender Mithril Ore");
        add(TTMContent.ORE_NETHER_MITHRIL.get(), "Fiery Mithril Ore");
        add(TTMContent.BLOCK_MITHRIL.get(), "Mithril Block");
        add(TTMContent.MITHRIL_BARS.get(), "Mithril Bars");
        add(TTMContent.DOOR_MITHRIL.get(), "Mithril Door");
        add(TTMContent.MITHRIL_BUCKET.get(), "Molten Mithril Bucket");
        add(TTMContent.ORE_MORGULIRON.get(), "Morgul Iron Ore");
        add(TTMContent.ORE_END_MORGULIRON.get(), "Ender Morgul Iron Ore");
        add(TTMContent.ORE_NETHER_MORGULIRON.get(), "Fiery Morgul Iron Ore");
        add(TTMContent.BLOCK_MORGULIRON.get(), "Morgul Iron Block");
        add(TTMContent.MORGULIRON_BARS.get(), "Morgul Iron Bars");
        add(TTMContent.DOOR_MORGULIRON.get(), "Morgul Iron Door");
        add(TTMContent.MORGULIRON_BUCKET.get(), "Molten Morguliron Bucket");
        add(TTMContent.ORE_AMMOLITE.get(), "Ammolite Ore");
        add(TTMContent.ORE_END_AMMOLITE.get(), "Ender Ammolite Ore");
        add(TTMContent.ORE_NETHER_AMMOLITE.get(), "Firey Ammolite Ore");

        // Wood & Foliage
        add(TTMContent.LOG_MALLORN.get(), "Mallorn Log");
        add(TTMContent.LOG_MIRKWOOD.get(), "Mirkwood Log");
        add(TTMContent.LOG_CULUMALDA.get(), "Culumalda Log");
        add(TTMContent.LOG_LEBETHRON.get(), "Lebethron Log");
        add(TTMContent.LOG_DEADWOOD.get(), "Deadwood Log");
        add(TTMContent.PLANKS_MALLORN.get(), "Mallorn Planks");
        add(TTMContent.PLANKS_MIRKWOOD.get(), "Mirkwood Planks");
        add(TTMContent.PLANKS_CULUMALDA.get(), "Culumalda Planks");
        add(TTMContent.PLANKS_LEBETHRON.get(), "Lebethron Planks");
        add(TTMContent.STAIRS_MALLORN.get(), "Mallorn Stairs");
        add(TTMContent.STAIRS_MIRKWOOD.get(), "Mirkwood Stairs");
        add(TTMContent.STAIRS_CULUMALDA.get(), "Culumalda Stairs");
        add(TTMContent.STAIRS_LEBETHRON.get(), "Lebethron Stairs");
        add(TTMContent.SLAB_MALLORN.get(), "Mallorn Slab");
        add(TTMContent.SLAB_MIRKWOOD.get(), "Mirkwood Slab");
        add(TTMContent.SLAB_LEBETHRON.get(), "Lebethron Slab");
        add(TTMContent.SLAB_CULUMALDA.get(), "Culumalda Slab");
        add(TTMContent.DOOR_MALLORN.get(), "Mallorn Door");
        add(TTMContent.DOOR_MIRKWOOD.get(), "Mirkwood Door");
        add(TTMContent.DOOR_CULUMALDA.get(), "Culumalda Door");
        add(TTMContent.DOOR_LEBETHRON.get(), "Lebethron Door");
        add(TTMContent.FENCE_GATE_MALLORN.get(), "Mallorn Fence Gate");
        add(TTMContent.FENCE_GATE_MIRKWOOD.get(), "Mirkwood Fence Gate");
        add(TTMContent.FENCE_GATE_CULUMALDA.get(), "Culumalda Fence Gate");
        add(TTMContent.FENCE_GATE_LEBETHRON.get(), "Lebethron Fence Gate");
        add(TTMContent.FENCE_MALLORN.get(), "Mallorn Fence");
        add(TTMContent.FENCE_MIRKWOOD.get(), "Mirkwood Fence");
        add(TTMContent.FENCE_CULUMALDA.get(), "Culumalda Fence");
        add(TTMContent.FENCE_LEBETHRON.get(), "Lebethron Fence");
        add(TTMContent.TRAPDOOR_MALLORN.get(), "Mallorn Trapdoor");
        add(TTMContent.TRAPDOOR_MIRKWOOD.get(), "Mirkwood Trapdoor");
        add(TTMContent.TRAPDOOR_CULUMALDA.get(), "Culumalda Trapdoor");
        add(TTMContent.TRAPDOOR_LEBETHRON.get(), "Lebethron Trapdoor");
        add(TTMContent.TORCH_MALLORN.get(), "Mallorn Torch");
        add(TTMContent.TORCH_MIRKWOOD.get(), "Mirkwood Torch");
        add(TTMContent.TORCH_CULUMALDA.get(), "Culumalda Torch");
        add(TTMContent.TORCH_LEBETHRON.get(), "Lebethron Torch");
        add(TTMContent.LEAVES_MALLORN.get(), "Mallorn Leaves");
        add(TTMContent.LEAVES_MIRKWOOD.get(), "Mirkwood Leaves");
        add(TTMContent.LEAVES_CULUMALDA.get(), "Culumalda Leaves");
        add(TTMContent.LEAVES_LEBETHRON.get(), "Lebethron Leaves");
        add(TTMContent.LEAVES_FANGORNOAK.get(), "Fangorn Oak Leaves");
        add(TTMContent.LEAFPILE_MALLORN.get(), "Pile of Mallorn Leaves");
        add(TTMContent.LEAFPILE_MIRKWOOD.get(), "Pile of Mirkwood Leaves");
        add(TTMContent.LEAFPILE_CULUMALDA.get(), "Pile of Culumalda Leaves");
        add(TTMContent.LEAFPILE_LEBETHRON.get(), "Pile of Lebethron Leaves");
        add(TTMContent.LEAFPILE_FANGORNOAK.get(), "Pile of Fangorn Oak Leaves");
        add(TTMContent.SAPLING_MALLORN.get(), "Mallorn Sapling");
        add(TTMContent.SAPLING_MIRKWOOD.get(), "Mirkwood Sapling");
        add(TTMContent.SAPLING_CULUMALDA.get(), "Culumalda Sapling");
        add(TTMContent.SAPLING_LEBETHRON.get(), "Lebethron Sapling");
        add(TTMContent.SAPLING_DEADWOOD.get(), "Deadwood Sapling");
        add(TTMContent.SAPLING_FANGORNOAK.get(), "Fangorn Oak Sapling");

        // Plants & Flowers
        add(TTMContent.MUSHROOM_DECAY_BLOOM.get(), "Bloom of Decay");
        add(TTMContent.MUSHROOM_BLOOM_DECAY.get(), "Bloom of Decay");
        add(TTMContent.BLOCK_DECAY_BLOOM.get(), "Bloom of Decay Block");
        add(TTMContent.BLOCK_BLOOM_DECAY.get(), "Bloom of Decay Block");
        add(TTMContent.FLOWER_SIMBELMYNE.get(), "Simbelmyne");
        add(TTMContent.FLOWER_MIRKWOOD.get(), "Seregon");
        add(TTMContent.FLOWER_ALFIRIN.get(), "Alfirin");
        add(TTMContent.FLOWER_ATHELAS.get(), "Kingsfoil");
        add(TTMContent.FLOWER_NIPHREDIL.get(), "Niphredil");
        add(TTMContent.FLOWER_SWAMPMILKWEED.get(), "Swamp Milkweed");
        add(TTMContent.FLOWER_LILLYOFTHEVALLEY.get(), "Lilly of the Valley");

        //Custom Blocks
        add(TTMContent.BLOCK_HALLOWED.get(), "Hallowed Earth");
        add(TTMContent.STONE_PATH.get(), "Stone Path");
    }

    private void items() {
        //Components
        add(TTMContent.DUST_MITHRIL.get(), "Mithril Dust");
        add(TTMContent.NUGGET_MITHRIL.get(), "Mithril Nugget");
        add(TTMContent.INGOT_MITHRIL.get(), "Mithril Ingot");
        add(TTMContent.DUST_MORGULIRON.get(), "Morgul Iron Dust");
        add(TTMContent.NUGGET_MORGULIRON.get(), "Morgul Iron Nugget");
        add(TTMContent.INGOT_MORGULIRON.get(), "Morgul Iron Ingot");
        add(TTMContent.GEM_AMMOLITE.get(), "Star of Elendil");
        add(TTMContent.CREBAIN_FEATHER.get(), "Crebain Feather");
        add(TTMContent.BIRD_FEATHER.get(), "Bird Feather");
        add(TTMContent.MUMAKIL_LEATHER.get(), "Mumakil Hide");
        add(TTMContent.MONSTER_FUR.get(), "Monster Fur");
        add(TTMContent.BOTTLE_FANCY.get(), "Fancy Bottle");
        add(TTMContent.GOLEM_STONE.get(), "Golem Stone");
        add(TTMContent.GOLEM_STONE_EARTH.get(), TextFormatting.DARK_GREEN + "Earth " + TextFormatting.RESET + "Golem Stone");
        add(TTMContent.GOLEM_STONE_AIR.get(), TextFormatting.YELLOW + "Air " + TextFormatting.RESET + "Golem Stone");
        add(TTMContent.GOLEM_STONE_FIRE.get(), TextFormatting.DARK_RED + "Fire " + TextFormatting.RESET + "Golem Stone");
        add(TTMContent.GOLEM_STONE_WATER.get(), TextFormatting.BLUE + "Water " + TextFormatting.RESET + "Golem Stone");
        add(TTMContent.GOLEM_STONE_SUMMON.get(), "Summon Legendary " + TextFormatting.AQUA + "Mithril " + TextFormatting.RESET + "Golem");
        addLore(TTMContent.GOLEM_STONE_SUMMON.get(), "Ancient artifact to summon a Legendary Golem");


        add("item_info.tolkienmobs.sneak_right_click_activate", "Shift right-click to activate/deactivate effect");
    }

    private void quest() {
        add(TTMContent.ITEM_BERYL.get(), TextFormatting.DARK_AQUA + "Glorfindel's Beryl");
        addLore(TTMContent.ITEM_BERYL.get(), "Magical gem loaned to you to help on your search");
        add(TTMContent.ITEM_FORTRESSMAP.get(), TextFormatting.DARK_AQUA + "Fortress Plans");
        addLore(TTMContent.ITEM_FORTRESSMAP.get(), "Seemingly fragile, surprising it survived so long");
        add(TTMContent.ITEM_WATCHERHEART.get(), TextFormatting.DARK_PURPLE + "Stone Heart");
        addLore(TTMContent.ITEM_WATCHERHEART.get(), "This heart has a disturbing and fearful energy");
        add(TTMContent.ITEM_WATCHERHEART_CRACKED.get(), TextFormatting.DARK_PURPLE + "Watcher's Heart");
        addLore(TTMContent.ITEM_WATCHERHEART_CRACKED.get(), "This heart looks like it has seen better days");
        add(TTMContent.ITEM_KEYSTONE.get(), TextFormatting.DARK_AQUA + "Key-Stone");
        addLore(TTMContent.ITEM_KEYSTONE.get(), "Almost forgotten, this is the \"Key\" to opening a secret entrance");
        add(TTMContent.ITEM_DARKSADDLE.get(), TextFormatting.BLUE + "Black Steed's Bridle");
        addLore(TTMContent.ITEM_DARKSADDLE.get(), "Taken from the corpse of an evil Horse");
        add(TTMContent.ITEM_ARTIFACT.get(), TextFormatting.GOLD + "Lost Artifact");
        addLore(TTMContent.ITEM_ARTIFACT.get(), "This objects emits a dark aura");
        add(TTMContent.ITEM_BLANKPAPER.get(), TextFormatting.DARK_PURPLE + "Manuscript page");
        addLore(TTMContent.ITEM_BLANKPAPER.get(), "Lost page from a manuscript");
        add(TTMContent.ITEM_FANCYARMOR.get(), TextFormatting.DARK_PURPLE + "Cromhes' Armour " + TextFormatting.DARK_GREEN + "(Repaired)");
        addLore(TTMContent.ITEM_FANCYARMOR.get(), "After repairs Cromhes' armour looks amazing");
        add(TTMContent.ITEM_FANCYCLOTH.get(), TextFormatting.DARK_GREEN + "Fine Cloth");
        addLore(TTMContent.ITEM_FANCYCLOTH.get(), "Finely woven cloth for decoration");
        add(TTMContent.ITEM_FANCYHAMMER.get(), TextFormatting.BLUE + "Thror's Hammer");
        addLore(TTMContent.ITEM_FANCYHAMMER.get(), "A hammer especially designed for shaping mithril");
        add(TTMContent.ITEM_FANCYKEY.get(), TextFormatting.DARK_AQUA + "Dungeon Key");
        addLore(TTMContent.ITEM_FANCYKEY.get(), "Key made by Elssuli from the items you collected");
        add(TTMContent.ITEM_FANCYSHIELD2.get(), TextFormatting.DARK_PURPLE + "Cromhes' Shield " + TextFormatting.DARK_GREEN + "(Repaired)");
        addLore(TTMContent.ITEM_FANCYSHIELD2.get(), "This shield hails from an unknown origin though it belongs to Cromhes");
        add(TTMContent.ITEM_FANCYSWORD.get(), TextFormatting.DARK_PURPLE + "Cromhes' Sword " + TextFormatting.DARK_GREEN + "(Repaired)");
        addLore(TTMContent.ITEM_FANCYSWORD.get(), "The smith has done an amazing job on this sword");
        add(TTMContent.ITEM_FANCYSWORD2.get(), TextFormatting.DARK_RED + "Apostle");
        addLore(TTMContent.ITEM_FANCYSWORD2.get(), "Hope of Vengeance");
        add(TTMContent.ITEM_LETTER.get(), TextFormatting.GOLD + "Thurdan's Letter");
        addLore(TTMContent.ITEM_LETTER.get(), "Thurdan's letter about Dreulhara");
        add(TTMContent.ITEM_SCROLL.get(), TextFormatting.DARK_PURPLE + "Ashen Scroll");
        addLore(TTMContent.ITEM_SCROLL.get(), "A tattered scroll, covered in small runes");
        add(TTMContent.ITEM_SCROLL2.get(), TextFormatting.DARK_PURPLE + "Translated Scroll");
        addLore(TTMContent.ITEM_SCROLL2.get(), "A scroll translated by Elrond with instructions to get into the dungeons of Moria");
        add(TTMContent.ITEM_SPECIALFLOWER.get(), TextFormatting.DARK_GREEN + "Hag Hops");
        addLore(TTMContent.ITEM_SPECIALFLOWER.get(), "Especially potent flower brewers love to use");
        add(TTMContent.ITEM_STORYBOOK.get(), TextFormatting.DARK_AQUA + "Hobbit Story");
        addLore(TTMContent.ITEM_STORYBOOK.get(), "Story told by a hobbit after Bilbo disappeared on his birthday");
        add(TTMContent.ITEM_STORYBOOK2.get(), TextFormatting.GOLD + "Bilbo's Story");
        addLore(TTMContent.ITEM_STORYBOOK2.get(), "Gift Created by Gaffer Gamgee for Bilbo's Birthday");
        add(TTMContent.ITEM_STORYBOOK3.get(), TextFormatting.DARK_AQUA + "Petunia's Manuscript");
        addLore(TTMContent.ITEM_STORYBOOK3.get(), "Journal of the adventures a young Hobbit has");
        add(TTMContent.ITEM_STORYBOOK4.get(), TextFormatting.DARK_GREEN + "Ancient Tome");
        addLore(TTMContent.ITEM_STORYBOOK4.get(), "This book looks like it has seen better days");
        add(TTMContent.ITEM_WORNARMOR.get(), TextFormatting.DARK_PURPLE + "Cromhes' Armour");
        addLore(TTMContent.ITEM_WORNARMOR.get(), "Item stolen from Cromhes while he was held captive");
        add(TTMContent.ITEM_WORNHELM.get(), TextFormatting.DARK_PURPLE + "Broken Helm");
        addLore(TTMContent.ITEM_WORNHELM.get(), "Item Thonrum wanted repaired");
        add(TTMContent.ITEM_WORNKEY.get(), TextFormatting.DARK_PURPLE + "Bandit's Key");
        addLore(TTMContent.ITEM_WORNKEY.get(), "Ruined key found on the body of a bandit captain");
        add(TTMContent.ITEM_WORNPICK.get(), TextFormatting.DARK_PURPLE + "Worn-out Pickaxe");
        addLore(TTMContent.ITEM_WORNPICK.get(), "Item Thonrum wanted repaired");
        add(TTMContent.ITEM_WORNSHIELD.get(), TextFormatting.DARK_PURPLE + "Damaged Shield");
        addLore(TTMContent.ITEM_WORNSHIELD.get(), "Item Thonrum wanted repaired");
        add(TTMContent.ITEM_WORNSHIELD2.get(), TextFormatting.DARK_PURPLE + "Cromhes' Shield");
        addLore(TTMContent.ITEM_WORNSHIELD2.get(), "Item stolen from Cromhes while he was held captive");
        add(TTMContent.ITEM_WORNSWORD.get(), TextFormatting.DARK_PURPLE + "Cromhes' Sword");
        addLore(TTMContent.ITEM_WORNSWORD.get(), "Item stolen from Cromhes while he was held captive");
        add(TTMContent.ITEM_WOVENBASKET.get(), TextFormatting.DARK_GREEN + "Woven Basket");
        addLore(TTMContent.ITEM_WOVENBASKET.get(), "Baskets woven to pay tribute");
        add(TTMContent.ITEM_WRITTENPAPER.get(), TextFormatting.DARK_PURPLE + "Statue Rubbing");
        addLore(TTMContent.ITEM_WRITTENPAPER.get(), "Charcoal rubbing of the runes on the Watchers near Khazad-dum");
        add(TTMContent.ITEM_PUNGENTHERB.get(), TextFormatting.DARK_RED + "Pungent Herb");
        addLore(TTMContent.ITEM_PUNGENTHERB.get(), "Herb used in powerful potions");
        add(TTMContent.ITEM_LOCKPICK.get(), "Lock-Pick");
        addLore(TTMContent.ITEM_LOCKPICK.get(), "Item used to open doors");
        add(TTMContent.ITEM_BROKENSWORD.get(), TextFormatting.DARK_PURPLE + "Sword Hilt");
        addLore(TTMContent.ITEM_BROKENSWORD.get(), "All that was left of the Dwarven sword");
        add(TTMContent.ITEM_REFORGEDSWORD.get(), "Reforged Sword");
        addLore(TTMContent.ITEM_REFORGEDSWORD.get(), "Remarkably good Dwarven work");
        add(TTMContent.ITEM_MAGIC_CLOTH.get(), TextFormatting.DARK_PURPLE + "Special Magic Cloth");
        addLore(TTMContent.ITEM_MAGIC_CLOTH.get(), "Fine Elven cloth made into a bag");
        add(TTMContent.ITEM_MITHRILNUGGET.get(), TextFormatting.DARK_PURPLE + "Bilbo's Nugget");
        addLore(TTMContent.ITEM_MITHRILNUGGET.get(), "Token Bilbo took from his adventures in the Lonely Mountain");
        add(TTMContent.ITEM_REMAINS.get(), TextFormatting.DARK_GREEN + "Pile of Bones");
        addLore(TTMContent.ITEM_REMAINS.get(), "Remains of some poor unfortunate soul");
        add(TTMContent.ITEM_RUNE_STONE.get(), TextFormatting.DARK_GREEN + "Ancient Rune Stone");
        addLore(TTMContent.ITEM_RUNE_STONE.get(), "This stone houses very powerful dark magic");
        add(TTMContent.ITEM_FANCYHELM.get(), "Fancy Helm");
        addLore(TTMContent.ITEM_FANCYHELM.get(), "");
        add(TTMContent.ITEM_KEYFRAGMENT.get(), "Key Fragment");
        addLore(TTMContent.ITEM_KEYFRAGMENT.get(), "");
        add(TTMContent.ITEM_OILYKEY.get(), "Oil-covered Key");
        addLore(TTMContent.ITEM_OILYKEY.get(), "");
        add(TTMContent.ITEM_FANCYPICK.get(), "Fancy Pick");
        addLore(TTMContent.ITEM_FANCYPICK.get(), "");
        add(TTMContent.ITEM_FANCYSHIELD.get(), "Fancy Shield");
        addLore(TTMContent.ITEM_FANCYSHIELD.get(), "");
    }

    private void coinToken() {
        add(TTMContent.ITEM_COIN_BRONZE.get(), "Bronze Coin");
        addLore(TTMContent.ITEM_COIN_BRONZE.get(), "64 Can be traded for 1 Silver coin");
        add(TTMContent.ITEM_COIN_SILVER.get(), "Silver Coin");
        addLore(TTMContent.ITEM_COIN_SILVER.get(), "64 Can be traded for 1 Gold coin");
        add(TTMContent.ITEM_COIN_GOLD.get(), "Gold Coin");
        addLore(TTMContent.ITEM_COIN_GOLD.get(), "64 Can be traded for 1 Mithril coin");
        add(TTMContent.ITEM_COIN_MITHRIL.get(), "Mithril Coin");
        addLore(TTMContent.ITEM_COIN_MITHRIL.get(), "Very rare and valuable coin");
        add(TTMContent.ITEM_DARKSIGIL.get(), TextFormatting.DARK_PURPLE + "Dark Sigil");
        addLore(TTMContent.ITEM_DARKSIGIL.get(), "Dark symbol dropped by a minion");
        add(TTMContent.ITEM_FACTIONCOIN.get(), TextFormatting.BLUE + "Faction Token");
        addLore(TTMContent.ITEM_FACTIONCOIN.get(), "Use this to raise faction standing");
        add(TTMContent.ITEM_FACTIONTOKEN.get(), TextFormatting.DARK_AQUA + "Faction Coin");
        addLore(TTMContent.ITEM_FACTIONTOKEN.get(), "This can be traded for unique items");
        add(TTMContent.ITEM_CAVECOMPLETE.get(), TextFormatting.DARK_AQUA + "Cave completion Token");
        addLore(TTMContent.ITEM_CAVECOMPLETE.get(), "Proof of killing the troll");
        add(TTMContent.ITEM_WATCHERCOMPLETE.get(), TextFormatting.DARK_AQUA + "Watcher Token");
        addLore(TTMContent.ITEM_WATCHERCOMPLETE.get(), "Proof of defeating the Watcher");
    }

    private void creativeTabGroups() {
        add("itemGroup.tolkienmobs.tools", "TolkienTweaks Tools");
        add("itemGroup.tolkienmobs.mats", "TolkienTweaks Materials & World Gen");
        add("itemGroup.tolkienmobs.spawn", "TolkienTweaks Mobs");
        add("itemGroup.tolkienmobs.food", "TolkienTweaks Food");
        add("itemGroup.tolkienmobs.quest", "TolkienTweaks Quest Items");
        add("itemGroup.tolkienmobs.signs", "TolkienTweaks Signs");
    }

    private void potion() {
        add("effect.tolkienmobs.ent_draught", "Ent Draught");
        add("item.minecraft.potion.effect.ent_draught", "Potion of Ent Draught");
        add("item.minecraft.splash_potion.effect.ent_draught", "Splash Potion of Ent Draught");
        add("item.minecraft.lingering_potion.effect.ent_draught", "Lingering Potion of Ent Draught");
        add("item.minecraft.tipped_arrow.effect.ent_draught", "Arrow of Ent Draught");
        add("effect.tolkienmobs.elf_vitality", "Elvish Life-span");
        add("item.minecraft.potion.effect.elf_vitality", "Potion of Elvish Life-span");
        add("item.minecraft.splash_potion.effect.elf_vitality", "Splash Potion of Elvish Life-span");
        add("item.minecraft.lingering_potion.effect.elf_vitality", "Lingering Potion of Elvish Life-span");
        add("item.minecraft.tipped_arrow.effect.elf_vitality", "Arrow of Elvish Life-span");
        add("effect.tolkienmobs.inventory_corrosion", "Corrosion");
        add("item.minecraft.potion.effect.inventory_corrosion", "Potion of Corrosion");
        add("item.minecraft.splash_potion.effect.inventory_corrosion", "Splash Potion of Corrosion");
        add("item.minecraft.lingering_potion.effect.inventory_corrosion", "Lingering Potion of Corrosion");
        add("item.minecraft.tipped_arrow.effect.inventory_corrosion", "Arrow of Corrosion");
        add("effect.tolkienmobs.personal_blacksmith", "Portable Blacksmith");
        add("item.minecraft.potion.effect.personal_blacksmith", "Potion of Portable Blacksmith");
        add("item.minecraft.splash_potion.effect.personal_blacksmith", "Splash Potion of Portable Blacksmith");
        add("item.minecraft.lingering_potion.effect.personal_blacksmith", "Lingering Potion of Portable Blacksmith");
        add("item.minecraft.tipped_arrow.effect.personal_blacksmith", "Arrow of Portable Blacksmith");
        add("effect.tolkienmobs.dread_aura", "Great Dread");
        add("item.minecraft.potion.effect.dread_aura", "Potion of Great Dread");
        add("item.minecraft.splash_potion.effect.dread_aura", "Splash Potion of Great Dread");
        add("item.minecraft.lingering_potion.effect.dread_aura", "Lingering Potion of Great Dread");
        add("item.minecraft.tipped_arrow.effect.dread_aura", "Arrow of Great Dread");
        add("effect.tolkienmobs.elven_nimbleness", "Elvish Nimbleness");
        add("item.minecraft.potion.effect.elven_nimbleness", "Potion of Elvish Nimbleness");
        add("item.minecraft.splash_potion.effect.elven_nimbleness", "Splash Potion of Elvish Nimbleness");
        add("item.minecraft.lingering_potion.effect.elven_nimbleness", "Lingering Potion of Elvish Nimbleness");
        add("item.minecraft.tipped_arrow.effect.elven_nimbleness", "Arrow of Elvish Nimbleness");
        add("effect.tolkienmobs.sleepnesia", "Sleepnesia");
        add("item.minecraft.potion.effect.sleepnesia", "Potion of Sleepnesia");
        add("item.minecraft.splash_potion.effect.sleepnesia", "Splash Potion of Sleepnesia");
        add("item.minecraft.lingering_potion.effect.sleepnesia", "Lingering Potion of Sleepnesia");
        add("item.minecraft.tipped_arrow.effect.sleepnesia", "Arrow of Sleepnesia");
        add("effect.tolkienmobs.crippling_terror", "Paralysing Fear");
        add("item.minecraft.potion.effect.crippling_terror", "Potion of Paralysing Fear");
        add("item.minecraft.splash_potion.effect.crippling_terror", "Splash Potion of Paralysing Fear");
        add("item.minecraft.lingering_potion.effect.crippling_terror", "Lingering Potion of Paralysing Fear");
        add("item.minecraft.tipped_arrow.effect.crippling_terror", "Arrow of Paralysing Fear");
        add("effect.tolkienmobs.blessing_of_eru", "Eru's Blessing");
        add("item.minecraft.potion.effect.blessing_of_eru", "Potion of Eru's Blessing");
        add("item.minecraft.splash_potion.effect.blessing_of_eru", "Splash Potion of Eru's Blessing");
        add("item.minecraft.lingering_potion.effect.blessing_of_eru", "Lingering Potion of Eru's Blessing");
        add("item.minecraft.tipped_arrow.effect.blessing_of_eru", "Arrow of Eru's Blessing");
        add("effect.tolkienmobs.elemental_drowning", "Elemental Drowning");
        add("item.minecraft.potion.effect.elemental_drowning", "Potion of Elemental Drowning");
        add("item.minecraft.splash_potion.effect.elemental_drowning", "Splash Potion of Elemental Drowning");
        add("item.minecraft.lingering_potion.effect.elemental_drowning", "Lingering Potion of Elemental Drowning");
        add("item.minecraft.tipped_arrow.effect.elemental_drowning", "Arrow of Elemental Drowning");
        add("effect.tolkienmobs.elemental_tornado",                          "Elemental Tornado");
        add("item.minecraft.potion.effect.elemental_tornado",                "Potion of Elemental Tornado");
        add("item.minecraft.splash_potion.effect.elemental_tornado",         "Splash Potion of Elemental Tornado");
        add("item.minecraft.lingering_potion.effect.elemental_tornado",      "Lingering Potion of Elemental Tornado");
        add("item.minecraft.tipped_arrow.effect.elemental_tornado",          "Arrow of Elemental Tornado");
        add("effect.tolkienmobs.elemental_lightning", "Elemental Lightning");
        add("item.minecraft.potion.effect.elemental_lightning", "Potion of Elemental Lightning");
        add("item.minecraft.splash_potion.effect.elemental_lightning", "Splash Potion of Elemental Lightning");
        add("item.minecraft.lingering_potion.effect.elemental_lightning", "Lingering Potion of Elemental Lightning");
        add("item.minecraft.tipped_arrow.effect.elemental_lightning", "Arrow of Elemental Lightning");
        add("effect.tolkienmobs.elemental_flight", "Elemental Flying");
        add("item.minecraft.potion.effect.elemental_flight", "Potion of Elemental Flying");
        add("item.minecraft.splash_potion.effect.elemental_flight", "Splash Potion of Elemental Flying");
        add("item.minecraft.lingering_potion.effect.elemental_flight", "Lingering Potion of Elemental Flying");
        add("item.minecraft.tipped_arrow.effect.elemental_flight", "Arrow of Elemental Flying");
        add("effect.tolkienmobs.elemental_burning", "Elemental Burning");
        add("item.minecraft.potion.effect.elemental_burning", "Potion of Elemental Burning");
        add("item.minecraft.splash_potion.effect.elemental_burning", "Splash Potion of Elemental Burning");
        add("item.minecraft.lingering_potion.effect.elemental_burning", "Lingering Potion of Elemental Burning");
        add("item.minecraft.tipped_arrow.effect.elemental_burning", "Arrow of Elemental Burning");
    }

    private void enchants() {
        add("enchantment.tolkienmobs.balrogs_mark", "Balrog's Mark");
        add("enchantment.tolkienmobs.elven_longevity", "Elven Longevity");
        add("enchantment.tolkienmobs.gondor_resolve", "Gondorian Resolve");
        add("enchantment.tolkienmobs.dwarven_endurance", "Dwarven Endurance");
        add("enchantment.tolkienmobs.hobbit_plow",                "Hobbit Plowing");
        add("enchantment.tolkienmobs.hobbit_harvest",                "Hobbit Harvest");
    }

    private void food() {
        add(TTMContent.DRINK_ENT_DRAUGHT.get(), "Ent Draught");
        add(TTMContent.DRINK_PERSONAL_BLACKSMITH.get(), "Portable Blacksmith");
        add(TTMContent.DRINK_ELF_FLEETFOOT.get(), "Blessing of the Elves");
        add(TTMContent.DRINK_ELF_VITALITY.get(), "Vitality");
        add(TTMContent.DRINK_ERU_BLESSING.get(), "Blessing of Eru Iluvatar");
        add(TTMContent.MIRUVOR.get(), "Miruvor");
        add(TTMContent.GROG.get(), "Grog");
        add(TTMContent.LEMBAS.get(), "Lembas");
        add(TTMContent.HONEY_CAKE.get(), "Honey Cake");
        add(TTMContent.CRAM.get(), "Cram");
        add(TTMContent.MONSTER_FLESH.get(), "Monster Flesh");
        add(TTMContent.INSECT.get(), "Frog Bait");
        add(TTMContent.GOLDEN_INSECT.get(), "Fancy Frog Bait");
        add(TTMContent.TREE_ACORN.get(), "Acorn");
        add(TTMContent.GOLDEN_TREE_ACORN.get(), "Golden Acorn");
        add(TTMContent.FOOD_HONEY.get(), "Honey");
    }

    private void records() {
        add(TTMContent.RECORD_RIVENDELL.get(), "Travelling Music");
        add("item.tolkienmobs." + TTMContent.RECORD_RIVENDELL.get() + ".desc", "Riders of Rivendell");
        add(TTMContent.RECORD_LOTHLORIEN.get(), "Travelling Music");
        add("item.tolkienmobs." + TTMContent.RECORD_LOTHLORIEN.get() + ".desc", "The Light of Lothlorien");
        add(TTMContent.RECORD_EREBOR.get(), "Travelling Music");
        add("item.tolkienmobs." + TTMContent.RECORD_EREBOR.get() + ".desc", "All That Glitters in Erebor");
        add(TTMContent.RECORD_WILLOW.get(), "Travelling Music");
        add("item.tolkienmobs." + TTMContent.RECORD_WILLOW.get() + ".desc", "Willow Song");
        add(TTMContent.RECORD_MINASTIRITH.get(), "Travelling Music");
        add("item.tolkienmobs." + TTMContent.RECORD_MINASTIRITH.get() + ".desc", "White Tree of Gondor");
        add(TTMContent.RECORD_EDORAS.get(), "Travelling Music");
        add("item.tolkienmobs." + TTMContent.RECORD_EDORAS.get() + ".desc", "Wake of Edoras");
        add(TTMContent.RECORD_WBATTLE.get(), "Travelling Music");
        add("item.tolkienmobs." + TTMContent.RECORD_WBATTLE.get() + ".desc", "Fly My Pretties!");
        add(TTMContent.RECORD_MURDERFROG.get(), "Travelling Music");
        add("item.tolkienmobs." + TTMContent.RECORD_MURDERFROG.get() + ".desc", "Ballad of Murder-Frog");
        add(TTMContent.RECORD_REDER.get(), "Travelling Music");
        add("item.tolkienmobs." + TTMContent.RECORD_REDER.get() + ".desc", "Evil Incarnate");
        add(TTMContent.RECORD_FUMBLE.get(), "Travelling Music");
        add("item.tolkienmobs." + TTMContent.RECORD_FUMBLE.get() + ".desc", "Bumbling Oaf");
        add(TTMContent.RECORD_BOMBADIL.get(), "Travelling Music");
        add("item.tolkienmobs." + TTMContent.RECORD_BOMBADIL.get() + ".desc", "Mystery of Tom Bombadil");
        add(TTMContent.RECORD_HOBBITS.get(), "Travelling Music");
        add("item.tolkienmobs." + TTMContent.RECORD_HOBBITS.get() + ".desc", "Concerning Hobbits - Remix");
    }

    private void tools() {
        add(TTMContent.AXE_MITHRIL.get(), "Mithril Axe");
        add(TTMContent.SWORD_MITHRIL.get(), "Mithril Sword");
        add(TTMContent.HOE_MITHRIL.get(), "Mithril Hoe");
        add(TTMContent.SHOVEL_MITHRIL.get(), "Mithril Shovel");
        add(TTMContent.PICKAXE_MITHRIL.get(), "Mithril Pickaxe");
        add(TTMContent.AXE_MORGULIRON.get(), "Morgul Iron Axe");
        add(TTMContent.SWORD_MORGULIRON.get(), "Morgul Iron Sword");
        add(TTMContent.HOE_MORGULIRON.get(), "Morgul Iron Hoe");
        add(TTMContent.SHOVEL_MORGULIRON.get(), "Morgul Iron Shovel");
        add(TTMContent.PICKAXE_MORGULIRON.get(), "Morgul Iron Pickaxe");
        add(TTMContent.SWORD_WITCHKING.get(), TextFormatting.DARK_RED + "Sword of the Witch-king");
        addLore(TTMContent.SWORD_WITCHKING.get(), "Forged in fear, the powerful weapon of the Witch-king");
        add(TTMContent.SWORD_URUK.get(), "Uruk Sword");
        add(TTMContent.WHIP_FIRE.get(), TextFormatting.DARK_RED + "Whip of Fire");
        addLore(TTMContent.WHIP_FIRE.get(), "Primary weapon of the fearsome Balrog");
        add(TTMContent.CLUB_WOODEN.get(), "Troll Club");
        addLore(TTMContent.CLUB_WOODEN.get(), "Deadly weapon favoured by Trolls");
        add(TTMContent.GALADHRIM_ARROW.get(), "Galadhrim Arrows");
        add(TTMContent.FELLBEAST_FIREBALL.get(), "Fell Beast Fireball");
        add(TTMContent.BOULDER.get(), "Boulder");
    }

    private void trinket(PrefixHelper helper) {
        add(TTMContent.TRINKET_AMULET.get(), "Magical Amulet of ");
        add(TTMContent.TRINKET_BELT.get(), "Magical Belt of ");
        add(TTMContent.TRINKET_CHARM.get(), "Magical Charm of ");
        add(TTMContent.TRINKET_RING.get(), "Magical Ring of ");
    }

    private void biomes(PrefixHelper helper) {
        helper.setPrefix("biome.tolkienmobs.");
        helper.add("biome_lorinand", "Lothlorien");
        helper.add("biome_mirkwood", "Mirkwood");
        helper.add("biome_marshes", "Nindalf Marsh");
        helper.add("biome_mordor", "Mordor");
        helper.add("biome_barrowdowns", "Tyrn Gorthad");
        helper.add("biome_dagorlad", "The Brown Lands");
        helper.add("biome_shire", "The Shire");
        helper.add("biome_fangorn", "Fangorn Forest");
        helper.add("biome_haradwaith", "Haradwaith");
        helper.add("biome_oldforest", "Old Forest");
        helper.add("biome_gladden", "Gladden Fields");
        helper.add("biome_firien", "Firien Wood");
        helper.add("biome_hithaeglir", "Misty Mountains");
        helper.add("biome_ironhills", "Iron Hills");
    }

    private void merchants(PrefixHelper helper) {
        helper.setPrefix("entity.tolkienmobs.entityttmhuman.tolkienmobs.");
        helper.add("coin_trader", "Coin Trader");
        helper.add("grocery_store", "Grocery Store");
        helper.add("pet_merchant", "Pet Merchant");
        helper.add("junk_trader", "Junk Trader");
        helper.setPrefix("entity.tolkienmobs.entityttmdwarf.tolkienmobs.");
        helper.add("coin_trader", "Coin Trader");
        helper.add("grocery_store", "Grocery Store");
        helper.add("pet_merchant", "Pet Merchant");
        helper.add("junk_trader", "Junk Trader");
        helper.setPrefix("entity.tolkienmobs.entityttmelves.tolkienmobs.");
        helper.add("coin_trader", "Coin Trader");
        helper.add("grocery_store", "Grocery Store");
        helper.add("pet_merchant", "Pet Merchant");
        helper.add("junk_trader", "Junk Trader");
        helper.setPrefix("entity.tolkienmobs.entityttmhobbit.tolkienmobs.");
        helper.add("coin_trader", "Coin Trader");
        helper.add("grocery_store", "Grocery Store");
        helper.add("pet_merchant", "Pet Merchant");
        helper.add("junk_trader", "Junk Trader");

    }

    private void entities() {
        // Ambient
        add(EntityGenerator.ENTITY_TTM_RAT.get(), "Rat");
        add(EntityGenerator.EGG_TTMRAT.get(), "Rat Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_SQUIRREL.get(), "Squirrel");
        add(EntityGenerator.EGG_TTMSQUIRREL.get(), "Squirrel Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_FROG.get(), "Frog");
        add(EntityGenerator.EGG_TTMFROG.get(), "Frog Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_SWARM.get(), "Midge Flies");
        add(EntityGenerator.EGG_TTMSWARM.get(), "Midge Flies Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_THRUSH.get(), "Thrush");
        add(EntityGenerator.EGG_TTMTHRUSH.get(), "Thrush Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_CREBAIN.get(), "Crebain");
        add(EntityGenerator.EGG_TTMCREBAIN.get(), "Crebain Spawn Egg");

        // Merchants
        add(EntityGenerator.ENTITY_TTM_HUMAN.get(), "Human");
        add(EntityGenerator.EGG_TTMHUMAN.get(), "Human Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_DWARF.get(), "Dwarf");
        add(EntityGenerator.EGG_TTMDWARF.get(), "Dwarf Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_ELVES.get(), "Elf");
        add(EntityGenerator.EGG_TTMELVES.get(), "Elf Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_HOBBIT.get(), "Hobbit");
        add(EntityGenerator.EGG_TTMHOBBIT.get(), "Hobbit Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_DESERTDWELLER.get(), "Wandering Villager");
        add(EntityGenerator.EGG_TTMDESERTDWELLER.get(), "Wandering Villager Spawn Egg");

        // Monster
        add(EntityGenerator.ENTITY_TTM_GOBLIN.get(), "Goblin");
        add(EntityGenerator.EGG_TTMGOBLIN.get(), "Goblin Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_BARROW.get(), "Barrow Wight");
        add(EntityGenerator.EGG_TTMBARROW.get(), "Barrow Wight Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_BRIGAND.get(), "Brigand");
        add(EntityGenerator.EGG_TTMBRIGAND.get(), "Brigand Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_DEEPCLAW.get(), "Deepclaw");
        add(EntityGenerator.EGG_TTMDEEPCLAW.get(), "Deepclaw Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_TREEENT.get(), "Tree Ent");
        add(EntityGenerator.EGG_TTMTREEENT.get(), "Tree Ent Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_DUERGAR.get(), "Duergar");
        add(EntityGenerator.EGG_TTMDUERGAR.get(), "Duergar Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_FELLSPIRIT.get(), "Fell Spirit");
        add(EntityGenerator.EGG_TTMFELLSPIRIT.get(), "Fell Spirit Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_SWAMPHAG.get(), "Swamp Hag");
        add(EntityGenerator.EGG_TTMSWAMPHAG.get(), "Swamp Hag Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_MIRKWOODSPIDER.get(), "Mirkwood Spider");
        add(EntityGenerator.EGG_TTMMIRKWOODSPIDER.get(), "Mirkwood Spider Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_HARADRIM.get(), "Haradrim");
        add(EntityGenerator.EGG_TTMHARADRIM.get(), "Haradrim Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_TROLL.get(), "Cave Troll");
        add(EntityGenerator.EGG_TTMTROLL.get(), "Cave Troll Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_WARG.get(), "Warg");
        add(EntityGenerator.EGG_TTMWARG.get(), "Warg Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_MORDORORC.get(), "Mordor Orc");
        add(EntityGenerator.EGG_TTMMORDORORC.get(), "Mordor Orc Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_HURON.get(), "Huron");
        add(EntityGenerator.EGG_TTMHURON.get(), "Huron Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_OATHBREAKER.get(), "Oath Breaker");
        add(EntityGenerator.EGG_TTMOATHBREAKER.get(), "Oath Breaker Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_ROMIEWALKER.get(), "Romie Walker");
        add(EntityGenerator.EGG_TTMROMIEWALKER.get(), "Romie Walker Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_URUKHAI.get(), "Uruk Hai");
        add(EntityGenerator.EGG_TTMURUKHAI.get(), "Uruk Hai Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_ELEMENTALGOLEM.get(), "Elemental Golem");
        add(EntityGenerator.EGG_TTMELEMENTALGOLEM.get(), "Elemental Golem Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_MINOTAUR.get(), "Minotaur");
        add(EntityGenerator.EGG_TTMMINOTAUR.get(), "Minotaur Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_MIMICCHEST.get(), "Mimic");
        add(EntityGenerator.EGG_TTMMIMICCHEST.get(), "Mimic Spawn Egg");

        // Boss
        add(EntityGenerator.ENTITY_TTM_GOBLINKING.get(), "Goblin King");
        add(EntityGenerator.EGG_TTMGOBLINKING.get(), "Goblin King Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_MITHRILGOLEM.get(), "Mithril Golem");
        add(EntityGenerator.EGG_TTMMITHRILGOLEM.get(), "Mithril Golem Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_MORGULIRONGOLEM.get(), "Morgul Iron Golem");
        add(EntityGenerator.EGG_TTMMORGULIRONGOLEM.get(), "Morgul Iron Golem Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_WITCHKING.get(), "Witch King of Angmar");
        add(EntityGenerator.EGG_TTMWITCHKING.get(), "Witch King Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_SHELOB.get(), "Shelob");
        add(EntityGenerator.EGG_TTMSHELOB.get(), "Shelob Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_BALROG.get(), "Balrog");
        add(EntityGenerator.EGG_TTMBALROG.get(), "Balrog Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_WATCHER.get(), "Watcher of the Water");
        add(EntityGenerator.EGG_TTMWATCHER.get(), "Watcher Spawn Egg");

        // Passive
        add(EntityGenerator.ENTITY_TTM_AUROCH.get(), "Auroch");
        add(EntityGenerator.EGG_TTMAUROCH.get(), "Auroch Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_MUMAKIL.get(), "Mumakil");
        add(EntityGenerator.EGG_TTMMUMAKIL.get(), "Mumakil Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_GOAT.get(), "Goat");
        add(EntityGenerator.EGG_TTMGOAT.get(), "Goat Spawn Egg");

        // Special
        add(EntityGenerator.ENTITY_TTM_SHADOWFAX.get(), "Shadowfax");
        add(EntityGenerator.EGG_TTMSHADOWFAX.get(), "Shadowfax Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_GOLLUM.get(), "Gollum");
        add(EntityGenerator.EGG_TTMGOLLUM.get(), "Gollum Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_NAZGUL.get(), "Nazgul");
        add(EntityGenerator.EGG_TTMNAZGUL.get(), "Nazgul Spawn Egg");
        add(EntityGenerator.ENTITY_TTM_NAZGULSTEED.get(), "Nazgul Steed");
        add(EntityGenerator.EGG_TTMNAZGULSTEED.get(), "Nazgul Steed Spawn Egg");
    }

    private void chatMessages(PrefixHelper helper) {
        add("tolkienmobs.msg.helpcomming", "Goblin King is attempting to call for help...Reinforcements have arrived!");
        add("tolkienmobs.msg.nohelp", "Goblin King is attempting to call for help, but no help came.");
        add("tolkienmobs.msg.nodrown", "Goblin King doesn't want to drown.");
        add("tolkienmobs.msg.onfire", "Goblin King is protecting himself from fire.");
        add("tolkienmobs.msg.healself", "Goblin King is hurt and is healing.");
        add("tolkienmobs.msg.speedup", "Goblin King is attempting to match your speed.");
    }



        //region Helpers

    @Override
    public void add(Block key, String name) {
        if (key != null) super.add(key, name);
    }

    @Override
    public void add(Item key, String name) {
        if (key != null) super.add(key, name);
    }

    public void addLore(Item key, String lore) {
        add(key.getDescriptionId() + ".lore", lore);
    }

    public static class PrefixHelper {
        private LangGenerator generator;
        private String prefix;

        public PrefixHelper(LangGenerator generator) {
            this.generator = generator;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public void add(String translationKey, String translation) {
            generator.add(prefix + translationKey, translation);
        }
    }

    //endregion

    @Override
    public String getName() {
        return "Tolkien Tweaks - Mobs Edition English Translation";
    }
}
