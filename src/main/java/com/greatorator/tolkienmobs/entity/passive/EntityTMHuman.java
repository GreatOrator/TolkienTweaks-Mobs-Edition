package com.greatorator.tolkienmobs.entity.passive;

import com.greatorator.tolkienmobs.entity.EntityTMVillagers;
import com.greatorator.tolkienmobs.init.LootInit;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.world.World;

public class EntityTMHuman extends EntityTMVillagers {

    public EntityTMHuman(World worldIn) {
        super(worldIn);
        this.setSize(0.9F, 2.0F);
        this.setLootTable(LootInit.HUMAN);
        this.setRndMinMax(1,16);
        this.setNetID(4);
        ((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
    }
}