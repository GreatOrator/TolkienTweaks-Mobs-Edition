package com.greatorator.tolkienmobs.entity.ai.target;

import com.greatorator.tolkienmobs.entity.MonsterEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class SwampHagNearestHealableRaiderTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
   private static final int DEFAULT_COOLDOWN = 200;
   private int cooldown = 0;

   public SwampHagNearestHealableRaiderTargetGoal(MonsterEntity raider, Class<T> entity, boolean heal, @Nullable Predicate<LivingEntity> predicate) {
      super(raider, entity, 500, heal, false, predicate);
   }

   public int getCooldown() {
      return this.cooldown;
   }

   public void decrementCooldown() {
      --this.cooldown;
   }

   public boolean canUse() {
      if (this.cooldown <= 0 && this.mob.getRandom().nextBoolean()) {
         this.findTarget();
         return this.target != null;
      } else {
         return false;
      }
   }

   public void start() {
      this.cooldown = reducedTickDelay(200);
      super.start();
   }
}