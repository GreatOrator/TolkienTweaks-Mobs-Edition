package com.greatorator.tolkienmobs.entity.passive;

import com.greatorator.tolkienmobs.entity.EntityPassives;
import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.init.SoundInit;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityDwarf extends EntityPassives {

    public EntityDwarf(World worldIn) {
        super(worldIn);
        this.setSize(1.0F, 1.7F);
        this.setLootTable(LootInit.DWARVES);
        this.setRndMinMax(1,5);
        this.setNetID(3);
        this.setFighter(true);
        ((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundInit.soundIdleDwarf;
    }
}