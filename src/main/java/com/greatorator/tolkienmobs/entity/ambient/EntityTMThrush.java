package com.greatorator.tolkienmobs.entity.ambient;

import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityTMThrush extends EntityParrot {

    public EntityTMThrush(World parWorld)
    {
        super(parWorld);
        this.setSize(0.3F, 0.3F);
    }

    @Override
    public Item getDropItem()
    {
        return TTMFeatures.BIRD_FEATHER;
    }

    @Override
    protected void dropFewItems(boolean parRecentlyHitByPlayer, int parLootLevel)
    {
        dropItem(TTMFeatures.BIRD_FEATHER, parLootLevel+1);
        return;
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LootInit.THRUSH;
    }
}
