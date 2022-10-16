package com.greatorator.tolkienmobs.entity.ammo;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.datagen.EntityGenerator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import javax.annotation.Nonnull;

public class GaladhrimArrowEntity extends ArrowEntity {

    public GaladhrimArrowEntity(EntityType<GaladhrimArrowEntity> entityEntityType, World world) {
        super((EntityType<? extends GaladhrimArrowEntity>) EntityGenerator.AMMO_ARROW_GALADHRIM.get(), world);

    }

    public GaladhrimArrowEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super((EntityType<? extends ArrowEntity>) EntityGenerator.AMMO_ARROW_GALADHRIM.get(), world);
    }

    public GaladhrimArrowEntity(World worldIn, LivingEntity entityIn) {
        super((EntityType<? extends ArrowEntity>) EntityGenerator.AMMO_ARROW_GALADHRIM.get(), worldIn);
    }

    public GaladhrimArrowEntity(EntityType<GaladhrimArrowEntity> galadhrimArrowEntityEntityType, LivingEntity shooter, World world) {
        super((EntityType<? extends GaladhrimArrowEntity>) EntityGenerator.AMMO_ARROW_GALADHRIM.get(), world);
    }

    @Nonnull
    @Override
    public IPacket<?> getAddEntityPacket() {
        Entity entity = this.getOwner();
        return new SSpawnObjectPacket(this, entity == null ? 0 : entity.getId());
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
