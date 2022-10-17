package com.greatorator.tolkienmobs.entity.item;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.datagen.EntityGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;

public class GaladhrimArrowEntity extends ArrowEntity {
    private double baseDamage = 8.0D;

    public GaladhrimArrowEntity(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public GaladhrimArrowEntity(EntityType<GaladhrimArrowEntity> entityType, World world) {
        super(entityType, world);
        this.setPierceLevel((byte) 10);
    }

    public GaladhrimArrowEntity(World world, LivingEntity entity) {
        super(world, entity);
        this.setPierceLevel((byte) 10);
    }

    public GaladhrimArrowEntity(FMLPlayMessages.SpawnEntity spawnPacket, World world) {
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
        return EntityGenerator.AMMO_ARROW_GALADHRIM.get();
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
        return new ItemStack(TTMContent.GALADHRIM_ARROW.get());
    }
}