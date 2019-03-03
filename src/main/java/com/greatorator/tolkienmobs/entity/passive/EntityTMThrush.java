package com.greatorator.tolkienmobs.entity.passive;

public class EntityTMThrush extends EntityTMBirds {

    public EntityTMThrush(World parWorld)
    {
        super(parWorld);
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LootInit.THRUSH;
    }
}
