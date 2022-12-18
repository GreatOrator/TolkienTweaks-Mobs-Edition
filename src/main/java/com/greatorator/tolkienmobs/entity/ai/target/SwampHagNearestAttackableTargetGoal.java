package com.greatorator.tolkienmobs.entity.ai.target;

import com.greatorator.tolkienmobs.entity.MonsterEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class SwampHagNearestAttackableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
   private boolean canAttack = true;

   public SwampHagNearestAttackableTargetGoal(MonsterEntity raider, Class<T> target, int interval, boolean p_26079_, boolean p_26080_, @Nullable Predicate<LivingEntity> predicate) {
      super(raider, target, interval, p_26079_, p_26080_, predicate);
   }

   public void setCanAttack(boolean p_26084_) {
      this.canAttack = p_26084_;
   }

   public boolean canUse() {
      return this.canAttack && super.canUse();
   }
}