package com.greatorator.tolkienmobs.entity.monster;

import com.greatorator.tolkienmobs.entity.EntityHostiles;
import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.world.World;

public class EntityMordorOrc extends EntityHostiles {
    public EntityMordorOrc(World worldIn) {
        super(worldIn);
        this.setSize(1.0F, 1.8F);
        this.setWeaponType(TTMFeatures.SWORD_MORGULIRON);
        this.setLootTable(LootInit.MORC);
        this.setMobAttributes(25.0D, 5.0D, 3.0D);
        this.setRndMinMax(1,5);
    }
}