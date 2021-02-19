//package com.greatorator.tolkienmobs.entity.passive;
//
//import com.greatorator.tolkienmobs.entity.EntityTMVillagers;
//import com.greatorator.tolkienmobs.init.LootInit;
//import net.minecraft.pathfinding.PathNavigateGround;
//import net.minecraft.world.World;
//
//public class EntityTMHobbit extends EntityTMVillagers {
//
//    public EntityTMHobbit(World worldIn) {
//        super(worldIn);
//        this.setSize(0.9F, 1.5F);
//        this.setLootTable(LootInit.HOBBIT);
//        this.setRndMinMax(1,5);
//        this.setNetID(0);
//        ((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
//    }
//}