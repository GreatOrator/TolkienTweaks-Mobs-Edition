package com.greatorator.tolkienmobs.event.entity;

import com.greatorator.tolkienmobs.entity.boss.GoblinKingEntity;
import com.greatorator.tolkienmobs.entity.monster.GoblinEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.eventbus.api.Event;

public class GoblinEvent extends EntityEvent {

    public GoblinEvent(GoblinKingEntity entity)
    {
        super(entity);
    }

    public GoblinEntity getSummoner()
    {
        return (GoblinEntity) getEntity();
    }

    @Event.HasResult
    public static class SummonAidEvent extends GoblinEvent {
        private GoblinEntity customSummonedAid;

        private final Level world;
        private final int x;
        private final int y;
        private final int z;
        private final LivingEntity attacker;
        private final double summonChance;

        public SummonAidEvent(GoblinKingEntity entity, Level world, int x, int y, int z, LivingEntity attacker, double summonChance)
        {
            super(entity);
            this.world = world;
            this.x = x;
            this.y = y;
            this.z = z;
            this.attacker = attacker;
            this.summonChance = summonChance;
        }

        /**
         * Populate this field to have a custom zombie instead of a normal zombie summoned
         */
        public GoblinEntity getCustomSummonedAid() { return customSummonedAid; }
        public void setCustomSummonedAid(GoblinEntity customSummonedAid) { this.customSummonedAid = customSummonedAid; }
        public Level getWorld() { return world; }
        public int getX() { return x; }
        public int getY() { return y; }
        public int getZ() { return z; }
        public LivingEntity getAttacker() { return attacker; }
        public double getSummonChance() { return summonChance; }
    }
}