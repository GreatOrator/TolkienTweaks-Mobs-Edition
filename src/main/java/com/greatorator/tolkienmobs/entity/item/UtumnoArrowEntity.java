package com.greatorator.tolkienmobs.entity.item;

import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;

public class UtumnoArrowEntity extends ArrowEntity {
    private double baseDamage = 5.0D;

    public UtumnoArrowEntity(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public UtumnoArrowEntity(EntityType<UtumnoArrowEntity> entityType, World world) {
        super(entityType, world);
        this.setPierceLevel((byte) 5);
    }

    public UtumnoArrowEntity(World world, LivingEntity entity) {
        super(world, entity);
        this.setPierceLevel((byte) 5);
    }

    public UtumnoArrowEntity(FMLPlayMessages.SpawnEntity spawnPacket, World world) {
        super(world, 0, 0, 0);
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
        return EntityGenerator.AMMO_ARROW_UTUMNO.get();
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void setNoPhysics(boolean value) {
        this.noPhysics = true;
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(TTMContent.UTUMNO_ARROW.get());
    }
}