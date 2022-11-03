package com.greatorator.tolkienmobs.event.entity;

import com.greatorator.tolkienmobs.entity.boss.ShelobEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityEvent;

public class SpiderEvent extends EntityEvent {

    public SpiderEvent(ShelobEntity entity)
    {
        super(entity);
    }

    public CaveSpider getSummoner()
    {
        return (CaveSpider) getEntity();
    }

    @HasResult
    public static class SummonAidEvent extends SpiderEvent {
        private CaveSpider customSummonedAid;
        
        private final Level world;
        private final int x;
        private final int y;
        private final int z;
        private final LivingEntity attacker;
        private final double summonChance;
        
        public SummonAidEvent(ShelobEntity entity, Level world, int x, int y, int z, LivingEntity attacker, double summonChance)
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
        public CaveSpider getCustomSummonedAid() { return customSummonedAid; }
        public void setCustomSummonedAid(CaveSpider customSummonedAid) { this.customSummonedAid = customSummonedAid; }
        public Level getWorld() { return world; }
        public int getX() { return x; }
        public int getY() { return y; }
        public int getZ() { return z; }
        public LivingEntity getAttacker() { return attacker; }
        public double getSummonChance() { return summonChance; }
    }
}
