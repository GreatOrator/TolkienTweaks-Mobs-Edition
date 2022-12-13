package com.greatorator.tolkienmobs.entity.ai.goal.dragon;

import com.greatorator.tolkienmobs.entity.DragonEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class AnimatedGoal extends Goal {
    public DragonEntity entity;
    public String animationName;
    public int animationType;
    public int animationTime;
    public boolean isMovingAnimation;
    public int elapsedTime;

    public AnimatedGoal(DragonEntity entity){
        this.entity = entity;
        this.elapsedTime = 0;
    }

    public AnimatedGoal(DragonEntity entity, String animationName, int animationType, int animationTime){
        this.entity = entity;
        this.animationName = animationName;
        this.animationType = animationType;
        this.animationTime = animationTime;
        this.elapsedTime = 0;
    }


    @Override
    public boolean canUse(){
        if (entity.getManualAnimationCall()) {
            return !animationName.equals("base");
        }
        return false;
    }


    @Override
    public boolean canContinueToUse() {
        if (elapsedTime > animationTime) {
            return false;
        }
        return true;
    }


    @Override
    public void start(){
    }

    public void start(String animationName, int animationType, int animationTime, boolean isMovingAnimation){
        this.entity.setAnimation(this.animationName = animationName);
        this.entity.setAnimationType(this.animationType = animationType);
        this.entity.setAnimationTime(this.animationTime = animationTime);
        this.entity.setIsMovingAnimation(this.isMovingAnimation = isMovingAnimation);
    }

    @Override
    public void tick() {
        elapsedTime++;
    }

    @Override
    public void stop(){
        this.elapsedTime = 0;
        this.entity.setAnimation("base");
        this.entity.setAnimationType(1);
        this.entity.setAnimationTime(0);
        this.entity.setManualAnimationCall(false);
        this.entity.setIsMovingAnimation(false);
    }
}