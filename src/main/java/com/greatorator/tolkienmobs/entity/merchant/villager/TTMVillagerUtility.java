package com.greatorator.tolkienmobs.entity.merchant.villager;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.village.PointOfInterestType;

import javax.annotation.Nullable;
import java.util.Set;

public class TTMVillagerUtility {

    public static Set<BlockState> getAllStates(Block block)
    {
        return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
    }

    public static void fixPOITypeBlockStates(PointOfInterestType poiType)
    {
        PointOfInterestType.registerBlockStates(poiType);
    }

    public static PointOfInterestType pointOfInterestType(String p1, Set<BlockState> p2, int p3, int p4)
    {
        return new PointOfInterestType(p1, p2, p3, p4);
    }

    public static VillagerProfession villagerProfession(String p1, PointOfInterestType p2, ImmutableSet<Item> p3, ImmutableSet<Block> p4, @Nullable SoundEvent p5)
    {
        return new VillagerProfession(p1, p2, p3, p4, p5);
    }
}