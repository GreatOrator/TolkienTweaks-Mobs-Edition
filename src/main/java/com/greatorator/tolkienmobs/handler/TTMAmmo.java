package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.datagen.SoundGenerator;
import com.greatorator.tolkienmobs.entity.ammo.EntityBoulder;
import com.greatorator.tolkienmobs.entity.ammo.EntityFellBeastFireball;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;


public class TTMAmmo extends Item {
    public TTMAmmo(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (!worldIn.isClientSide && itemstack.getItem() == TTMContent.BOULDER.get()) {
            worldIn.playSound((PlayerEntity)null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundGenerator.sound_Boulder_Shoot.get(), SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

            EntityBoulder entityboulder = new EntityBoulder(worldIn, playerIn);
            entityboulder.setItem(itemstack);
            entityboulder.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, 1.5F, 1.0F);
            worldIn.addFreshEntity(entityboulder);
        }

        if (!worldIn.isClientSide && itemstack.getItem() == TTMContent.FELLBEAST_FIREBALL.get()) {
            worldIn.playSound((PlayerEntity)null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.FIREWORK_ROCKET_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
            EntityFellBeastFireball entityfbfb = new EntityFellBeastFireball(worldIn, playerIn);
            entityfbfb.setItem(itemstack);
            entityfbfb.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, 1.5F, 1.0F);
            worldIn.addFreshEntity(entityfbfb);
        }

        playerIn.awardStat(Stats.ITEM_USED.get(this));
        if (!playerIn.abilities.instabuild) {
            itemstack.shrink(1);
        }

        return ActionResult.sidedSuccess(itemstack, worldIn.isClientSide());
    }
}