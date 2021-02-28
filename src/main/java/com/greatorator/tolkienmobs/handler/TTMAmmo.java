package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.datagen.SoundGenerator;
import com.greatorator.tolkienmobs.entity.ammo.EntityBoulder;
import com.greatorator.tolkienmobs.entity.ammo.EntityFellBeastFireball;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class TTMAmmo extends Item {
    public TTMAmmo(Properties properties) {
        super(properties);
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (!worldIn.isRemote && itemstack.getItem() == TTMContent.BOULDER.get()) {
            worldIn.playSound((PlayerEntity)null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundGenerator.sound_Boulder_Shoot.get(), SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

            EntityBoulder entityboulder = new EntityBoulder(worldIn, playerIn);
            entityboulder.setItem(itemstack);
            entityboulder.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.addEntity(entityboulder);
        }

        if (!worldIn.isRemote && itemstack.getItem() == TTMContent.FELLBEAST_FIREBALL.get()) {
            worldIn.playSound((PlayerEntity)null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_FIREWORK_ROCKET_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
            EntityFellBeastFireball entityfbfb = new EntityFellBeastFireball(worldIn, playerIn);
            entityfbfb.setItem(itemstack);
            entityfbfb.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.addEntity(entityfbfb);
        }

        playerIn.addStat(Stats.ITEM_USED.get(this));
        if (!playerIn.abilities.isCreativeMode) {
            itemstack.shrink(1);
        }

        return ActionResult.func_233538_a_(itemstack, worldIn.isRemote());
    }
}