//package com.greatorator.tolkienmobs.entity.entityai;
//
//import net.minecraft.block.material.Material;
//import net.minecraft.entity.EntityLiving;
//import net.minecraft.entity.ai.EntityAIBase;
//import net.minecraft.entity.ai.EntityMoveHelper;
//import net.minecraft.util.math.BlockPos;
//
//import java.util.Random;
//
//public class EntityAITTMSwim extends EntityAIBase {
//    public EntityLiving mob;
//    public double speed;
//    public long time = -1;
//    Random rand = new Random();
//
//    public EntityAITTMSwim(EntityLiving mob, double speed) {
//        this.mob = mob;
//        this.speed = speed;
//        this.setMutexBits(1);
//    }
//
//    @Override
//    public boolean shouldExecute() {
//        return this.mob.getMoveHelper().action == EntityMoveHelper.Action.WAIT;
//    }
//
//    @Override
//    public void updateTask() {
//        if (time == -1 || mob.world.getWorldTime() > time) {
//            time = mob.world.getWorldTime() + rand.nextInt(300);
//            for (int tries = 0; tries < 50; ++tries) {
//                BlockPos pos = this.mob.getPosition().add(new BlockPos(rand.nextInt(40) - 20, rand.nextInt(40) - 20, rand.nextInt(40) - 20));
//                if (mob.world.getBlockState(pos).getMaterial() == Material.WATER) {
//                    mob.getMoveHelper().setMoveTo(pos.getX(), pos.getY(), pos.getZ(), 0.75);
//                    break;
//                }
//            }
//        }
//    }
//}
