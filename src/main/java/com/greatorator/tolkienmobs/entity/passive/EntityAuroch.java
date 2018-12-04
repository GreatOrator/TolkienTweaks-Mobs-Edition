package com.greatorator.tolkienmobs.entity.passive;

import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityAuroch extends EntityCow {
    public static final ResourceLocation LOOT = new ResourceLocation(TolkienMobs.MODID, "entities/auroch");
    public EntityAuroch(World worldIn) {
        super(worldIn);
        this.setSize(1.0F, 1.7F);
    }
    public EntityCow createChild(EntityAgeable ageable)
    {
        return new EntityAuroch(this.world);
    }
}
