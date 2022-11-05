package com.greatorator.tolkienmobs.item.tools;

import com.greatorator.tolkienmobs.entity.item.BoulderEntity;
import com.greatorator.tolkienmobs.init.TolkienSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import java.util.Random;

public class BoulderItem extends Item {
   protected final Random random = new Random();

   public BoulderItem(Properties properties) {
      super(properties);
   }

   @Override
   public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, @Nonnull InteractionHand hand) {
      ItemStack itemstack = player.getItemInHand(hand);
      worldIn.playSound((Player)null, player.getX(), player.getY(), player.getZ(), TolkienSounds.sound_Boulder_Shoot.get(), SoundSource.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
      if (!worldIn.isClientSide) {
         BoulderEntity boulderEntity = new BoulderEntity(worldIn, player);
         boulderEntity.setItem(itemstack);
         boulderEntity.shootFromRotation(player, player.xRotO, player.yRotO, 0.0F, 1.5F, 1.0F);
         worldIn.addFreshEntity(boulderEntity);
      }

      player.awardStat(Stats.ITEM_USED.get(this));
      if (!player.getAbilities().instabuild) {
         itemstack.shrink(1);
      }

      return InteractionResultHolder.sidedSuccess(itemstack, worldIn.isClientSide());
   }
}