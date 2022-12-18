package com.greatorator.tolkienmobs.entity.projectiles;

import com.greatorator.tolkienmobs.init.TolkienEntities;
import com.greatorator.tolkienmobs.init.TolkienItems;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nonnull;

public class UtumnoArrowEntity extends Arrow {
    private double baseDamage = 5.0D;

    public UtumnoArrowEntity(EntityType<? extends UtumnoArrowEntity> entityType, Level world, double x, double y, double z) {
        super(entityType, world);
    }

    public UtumnoArrowEntity(EntityType<? extends UtumnoArrowEntity> entity, Level world) {
        super(entity, world);
        this.setPierceLevel((byte) 5);
    }

    public UtumnoArrowEntity(LivingEntity entity, Level world) {
        super(world, entity);
    }

    @Override
    public void setBaseDamage(double damage) {
        this.baseDamage = damage;
    }

    @Override
    public double getBaseDamage() {
        return this.baseDamage;
    }

    @Override
    @Nonnull
    public EntityType<?> getType() {
        return TolkienEntities.AMMO_ARROW_UTUMNO.get();
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void setNoPhysics(boolean value) {
        this.noPhysics = true;
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(TolkienItems.UTUMNO_ARROW.get());
    }
}