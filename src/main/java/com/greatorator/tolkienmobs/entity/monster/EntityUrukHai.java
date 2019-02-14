package com.greatorator.tolkienmobs.entity.monster;

import com.greatorator.tolkienmobs.entity.EntityHostiles;
import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.world.World;

public class EntityUrukHai extends EntityHostiles {
    public EntityUrukHai(World worldIn) {
        super(worldIn);
        this.setSize(1.0F, 2.6F);
        this.setWeaponType(TTMFeatures.SWORD_MORGULIRON);
        this.setLootTable(LootInit.URUK);
        this.setMobAttributes(34.0D, 8.0D, 3.0D);
        this.setRndMinMax(1,5);
    }
}