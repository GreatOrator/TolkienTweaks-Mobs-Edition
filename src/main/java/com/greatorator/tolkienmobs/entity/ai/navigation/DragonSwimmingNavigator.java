package com.greatorator.tolkienmobs.entity.ai.navigation;

import com.greatorator.tolkienmobs.entity.DragonEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.SwimNodeEvaluator;

public class DragonSwimmingNavigator extends WaterBoundPathNavigation{

    public DragonSwimmingNavigator(DragonEntity entity){
        super(entity,entity.level);
    }

    @Override
    protected PathFinder createPathFinder(int range){
        return new PathFinder(nodeEvaluator=new SwimNodeEvaluator(true),range);
    }

    @Override
    public boolean isStableDestination(BlockPos pos){
        return !level.getBlockState(pos.below()).isAir();
    }

    @Override
    protected boolean canUpdatePath(){
        return true;
    }
}