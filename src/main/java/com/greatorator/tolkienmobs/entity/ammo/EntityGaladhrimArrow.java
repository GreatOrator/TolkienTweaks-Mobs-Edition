package com.greatorator.tolkienmobs.entity.ammo;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.datagen.EntityGenerator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class EntityGaladhrimArrow extends AbstractArrowEntity {

    public EntityGaladhrimArrow(World worldIn, double x, double y, double z) {
        super((EntityType<? extends EntityGaladhrimArrow>) EntityGenerator.AMMO_ARROW_GALADHRIM.get(), x, y, z,worldIn);
    }

    public EntityGaladhrimArrow(World worldIn, LivingEntity shooter) {
        super((EntityType<? extends EntityGaladhrimArrow>) EntityGenerator.AMMO_ARROW_GALADHRIM.get(),shooter, worldIn);
    }

    public EntityGaladhrimArrow(EntityType<Entity> entityEntityType, World world) {
        super((EntityType<? extends EntityGaladhrimArrow>) EntityGenerator.AMMO_ARROW_GALADHRIM.get(), world);

    }

    public EntityGaladhrimArrow(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super((EntityType<? extends AbstractArrowEntity>) EntityGenerator.AMMO_ARROW_GALADHRIM.get(), world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(TTMContent.GALADHRIM_ARROW.get());
    }
}
