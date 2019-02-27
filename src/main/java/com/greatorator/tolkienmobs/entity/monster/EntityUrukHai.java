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
        this.setRndMinMax(1,5);
    }

    @Override
    public double getAttackDamage() {
        return 5.0D;
    }

    @Override
    public double getArmorStrength() {
        return 8.0D;
    }

    @Override
    public double getHealthLevel() {
        return 34.0D;
    }
}