package com.greatorator.tolkienmobs.entity.merchant.villager;

import com.google.common.collect.ImmutableSet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Set;

public class TTMVillagerUtility {

    public static Set<BlockState> getAllStates(Block block)
    {
        return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
    }

    public static void fixPOITypeBlockStates(PoiType poiType)
    {
        PoiType.registerBlockStates(poiType);
    }

    public static PoiType pointOfInterestType(String p1, Set<BlockState> p2, int p3, int p4)
    {
        return new PoiType(p1, p2, p3, p4);
    }

    public static VillagerProfession villagerProfession(String p1, PoiType p2, ImmutableSet<Item> p3, ImmutableSet<Block> p4, @Nullable SoundEvent p5)
    {
        return new VillagerProfession(p1, p2, p3, p4, p5);
    }
}