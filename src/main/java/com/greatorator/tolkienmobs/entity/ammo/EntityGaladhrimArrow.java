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

//    protected void arrowHit(LivingEntity living) {
//        super.arrowHit(living);
//
//        LightningBoltEntity lightningBoltEntity = new LightningBoltEntity(EntityType.LIGHTNING_BOLT,this.getEntityWorld());
//        lightningBoltEntity.setPositionAndUpdate(living.getPosX(),living.getPosY(),living.getPosZ());
//
//        world.addEntity(lightningBoltEntity);
//
//
//    }
//
//    @Override
//    public void tick() {
//        super.tick();
//
//        if (world.isRemote) {
//
//            System.out.println("I exist in the client");
//        }
//        if (this.inGround) {
//            if (this.timeInGround == 0) {
//                LightningBoltEntity lightningBoltEntity = new LightningBoltEntity(EntityType.LIGHTNING_BOLT,this.getEntityWorld());
//                lightningBoltEntity.setPositionAndUpdate(this.getPosX(),this.getPosY(),this.getPosZ());
//
//                //     world.addEntity(lightningBoltEntity);
//
//            }
//        }
//    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(TTMContent.GALADHRIM_ARROW.get());
    }
}
