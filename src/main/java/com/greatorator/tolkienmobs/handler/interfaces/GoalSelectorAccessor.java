package com.greatorator.tolkienmobs.handler.interfaces;

import net.minecraft.world.entity.ai.goal.WrappedGoal;

import java.util.Set;

public interface GoalSelectorAccessor {
    Set<WrappedGoal> getAvailableGoals();
}
