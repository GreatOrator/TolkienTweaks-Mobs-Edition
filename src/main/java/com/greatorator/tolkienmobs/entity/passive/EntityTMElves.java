//package com.greatorator.tolkienmobs.entity.passive;
//
//import com.greatorator.tolkienmobs.entity.EntityTMVillagers;
//import com.greatorator.tolkienmobs.init.LootInit;
//import net.minecraft.pathfinding.PathNavigateGround;
//import net.minecraft.world.World;
//
//public class EntityTMElves extends EntityTMVillagers {
//
//    public EntityTMElves(World worldIn) {
//        super(worldIn);
//        this.setSize(0.9F, 2.0F);
//        this.setLootTable(LootInit.ELVES);
//        this.setRndMinMax(1,16);
//        this.setNetID(1);
//        ((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
//    }
//}