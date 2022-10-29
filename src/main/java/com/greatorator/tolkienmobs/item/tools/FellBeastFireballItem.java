package com.greatorator.tolkienmobs.item.tools;

import com.greatorator.tolkienmobs.entity.item.FellBeastFireballEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class FellBeastFireballItem extends Item {
   public FellBeastFireballItem(Properties p_i48499_1_) {
      super(p_i48499_1_);
   }

   @Override
   public ActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
      ItemStack itemstack = playerEntity.getItemInHand(hand);
      Vector3d pos = playerEntity.getLookAngle();
      world.playSound((PlayerEntity)null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.FIREWORK_ROCKET_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
      if (!world.isClientSide) {
         FellBeastFireballEntity fellBeastFireballEntity = new FellBeastFireballEntity(world, playerEntity, pos.x, pos.y, pos.z);
         fellBeastFireballEntity.setItem(itemstack);
         fellBeastFireballEntity.shootFromRotation(playerEntity, playerEntity.xRot, playerEntity.yRot, 0.0F, 1.5F, 1.0F);
         world.addFreshEntity(fellBeastFireballEntity);
      }

      playerEntity.awardStat(Stats.ITEM_USED.get(this));
      if (!playerEntity.abilities.instabuild) {
         itemstack.shrink(1);
      }

      return ActionResult.sidedSuccess(itemstack, world.isClientSide());
   }
}