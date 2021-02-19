//package com.greatorator.tolkienmobs.entity.hostile;
//
//import com.greatorator.tolkienmobs.entity.EntityTMHostiles;
//import com.greatorator.tolkienmobs.init.LootInit;
//import com.greatorator.tolkienmobs.init.SoundInit;
//import net.minecraft.block.Block;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.item.EntityItem;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.init.Blocks;
//import net.minecraft.init.SoundEvents;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.EntityDamageSourceIndirect;
//import net.minecraft.util.SoundCategory;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//
//public class EntityTMHaradrim extends EntityTMHostiles {
//    protected Block spawnableBlock = Blocks.SAND;
//    private long nextAbilityUse = 0L;
//    private final static long coolDown = 15000L;
//    private LivingEntity mob;
//
//    public EntityTMHaradrim(World worldIn) {
//        super(worldIn);
//        this.setSize(1.0F, 2.0F);
//        this.setRandomWeapon(true);
//        this.setLootTable(LootInit.BRIGAND);
//        this.setMob(this);
//        this.setMobMentality(true, SoundInit.soundAngryGoblin);
//        this.setRndMinMax(1,11);
//    }
//
//    @Override
//    public boolean attackEntityFrom(DamageSource damageSource, float damage) {
//        Entity damageSourceP = damageSource.getTrueSource();
//
//        if (damageSourceP != null && (damageSourceP instanceof PlayerEntity))
//        {
//            PlayerEntity p = (PlayerEntity)damageSourceP;
//            ItemStack w = p.inventory.getStackInSlot(p.inventory.currentItem);
//
//            long time = System.currentTimeMillis();
//            if(w != null) {
//                if (time > nextAbilityUse
//                        && damageSourceP != null
//                        && !(damageSource instanceof EntityDamageSourceIndirect)) {
//                    nextAbilityUse = time + coolDown;
//                    EntityItem drop = p.dropItem(p.inventory.decrStackSize(p.inventory.currentItem, 1), false);
//
//                    if (drop != null) {
//                        drop.setPickupDelay(50);
//                        mob.world.playSound(null, new BlockPos(mob), SoundEvents.ENTITY_SLIME_ATTACK, SoundCategory.HOSTILE, 1.0F + mob.getRNG().nextFloat(), mob.getRNG().nextFloat() * 0.7F + 0.3F);
//                    }
//                }
//            }
//        }
//        return super.attackEntityFrom(damageSource, damage);
//    }
//
//    @Override
//    public double getAttackDamage() {
//        return 6;
//    }
//
//    @Override
//    public double getArmorStrength() {
//        return 3;
//    }
//
//    @Override
//    public double getHealthLevel() {
//        return 26;
//    }
//
//    public void setMob(LivingEntity mob) {
//        this.mob = mob;
//    }
//}