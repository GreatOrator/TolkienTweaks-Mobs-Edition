package com.greatorator.tolkienmobs.entity.hostile;

import com.greatorator.tolkienmobs.entity.EntityTMHostiles;
import com.greatorator.tolkienmobs.init.LootInit;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityTMDeepClaw extends EntityTMHostiles {
    public EntityTMDeepClaw(World worldIn) {
        super(worldIn);
        this.setSize(1.1F, 2.0F);
        this.setLootTable(LootInit.DEEPCLAW);
        this.setRndMinMax(1,5);
    }

    @Override
    public boolean getCanSpawnHere() {
        int willSpawn = this.spawnChance();

        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && super.getCanSpawnHere() && this.posY < 128.0D && !this.world.canSeeSky(new BlockPos(this)) && willSpawn <= 10;
    }

    @Override
    public double getAttackDamage() {
        return 8;
    }

    @Override
    public double getArmorStrength() {
        return 10;
    }

    @Override
    public double getHealthLevel() {
        return 22;
    }
}
