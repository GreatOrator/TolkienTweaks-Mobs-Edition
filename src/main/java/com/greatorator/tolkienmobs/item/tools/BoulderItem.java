package com.greatorator.tolkienmobs.item.tools;

import com.greatorator.tolkienmobs.datagen.SoundGenerator;
import com.greatorator.tolkienmobs.entity.item.BoulderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class BoulderItem extends Item {
   public BoulderItem(Properties properties) {
      super(properties);
   }

   @Override
   public ActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
      ItemStack itemstack = playerEntity.getItemInHand(hand);
      world.playSound((PlayerEntity)null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundGenerator.sound_Boulder_Shoot.get(), SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
      if (!world.isClientSide) {
         BoulderEntity boulderEntity = new BoulderEntity(world, playerEntity);
         boulderEntity.setItem(itemstack);
         boulderEntity.shootFromRotation(playerEntity, playerEntity.xRot, playerEntity.yRot, 0.0F, 1.5F, 1.0F);
         world.addFreshEntity(boulderEntity);
      }

      playerEntity.awardStat(Stats.ITEM_USED.get(this));
      if (!playerEntity.abilities.instabuild) {
         itemstack.shrink(1);
      }

      return ActionResult.sidedSuccess(itemstack, world.isClientSide());
   }
}