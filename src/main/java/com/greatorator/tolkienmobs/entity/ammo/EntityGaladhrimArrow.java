package com.greatorator.tolkienmobs.entity.ammo;

import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityGaladhrimArrow extends AbstractArrowEntity {
    private double damage = 2.0D;

    public EntityGaladhrimArrow(EntityType<? extends EntityGaladhrimArrow> type, World worldIn) {
        super(type, worldIn);
    }

    public EntityGaladhrimArrow(EntityType<? extends EntityGaladhrimArrow> type, double x, double y, double z, World worldIn) {
        this(type, worldIn);
        this.setPosition(x, y, z);
    }

    public EntityGaladhrimArrow(EntityType<? extends EntityGaladhrimArrow> type, LivingEntity shooter, World worldIn) {
        this(type, shooter.getPosX(), shooter.getPosYEye() - (double)0.1F, shooter.getPosZ(), worldIn);
        this.setShooter(shooter);
        this.damage = 8.0D;
        if (shooter instanceof PlayerEntity) {
            this.pickupStatus = EntityGaladhrimArrow.PickupStatus.ALLOWED;
        }

    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(TTMContent.GALADHRIM_ARROW.get());
    }
}
