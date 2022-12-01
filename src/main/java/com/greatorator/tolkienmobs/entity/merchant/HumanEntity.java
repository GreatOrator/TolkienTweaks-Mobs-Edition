package com.greatorator.tolkienmobs.entity.merchant;

import com.greatorator.tolkienmobs.entity.VillagerEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class HumanEntity extends VillagerEntity {
    public HumanEntity(EntityType<? extends VillagerEntity> type, Level level) {
        super(type, level);
    }
}