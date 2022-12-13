package com.greatorator.tolkienmobs.entity.ai.goal.dragon;

import com.greatorator.tolkienmobs.entity.DragonEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;

public class RunWhenLosingGoal extends AvoidEntityGoal<LivingEntity> {

    private final TargetingConditions runAwayTargeting;
    private final float healthPercent;
    private final DragonEntity dragon;
    private final float chanceToRun;
    private final double walkSpeedModifier;

    public RunWhenLosingGoal(DragonEntity pDragon, float healthPercent, float chanceToRun, float pMaxDistance, double pWalkSpeedModifier, double pSprintSpeedModifier) {
        super(pDragon, LivingEntity.class, pMaxDistance, pWalkSpeedModifier, pSprintSpeedModifier);
        this.healthPercent = healthPercent;
        this.chanceToRun = chanceToRun;
        this.dragon = pDragon;
        this.walkSpeedModifier = pWalkSpeedModifier;
        this.runAwayTargeting = TargetingConditions.forCombat().range(pMaxDistance).selector(this.predicateOnAvoidEntity.and(avoidPredicate));
    }

    @Override
    public void start() {
        dragon.getNavigation().moveTo(this.path, this.walkSpeedModifier);
    }

    @Override
    public boolean canUse() {
        if (this.dragon.getHealth()/this.dragon.getMaxHealth() > healthPercent) return false;
        if (dragon.getRandom().nextFloat() > chanceToRun) return false;

        this.toAvoid = dragon.getLastHurtByMob() != null? dragon.getLastHurtByMob() : this.dragon.level.getNearestEntity(this.dragon.level.getEntitiesOfClass(this.avoidClass, this.dragon.getBoundingBox().inflate(this.maxDist, 3.0D, this.maxDist), (p_148078_) -> {
            return true;
        }), this.runAwayTargeting, this.dragon, this.dragon.getX(), this.dragon.getY(), this.dragon.getZ());;
        System.out.println(toAvoid);

        if (this.toAvoid == null) return false;

        Vec3 vec3 = DefaultRandomPos.getPosAway(this.dragon, 16, 7, this.toAvoid.position());
        if (vec3 == null) {
            return false;
        } else if (this.toAvoid.distanceToSqr(vec3.x, vec3.y, vec3.z) < this.toAvoid.distanceToSqr(this.dragon)) {
            return false;
        } else {
            this.path = dragon.getNavigation().createPath(vec3.x, vec3.y, vec3.z, 0);
            System.out.println(this.path != null);
            return this.path != null;
        }
    }
}