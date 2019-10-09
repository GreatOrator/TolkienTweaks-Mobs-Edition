package com.greatorator.tolkienmobs.entity.ammo;

import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityGaladhrimArrow extends EntityArrow
{
    private double damage;
    public EntityGaladhrimArrow(World worldIn) {
        super(worldIn);
    }

    public EntityGaladhrimArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityGaladhrimArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
        this.damage = 6.0D;
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(TTMFeatures.GALADHRIM_ARROW);
    }
}