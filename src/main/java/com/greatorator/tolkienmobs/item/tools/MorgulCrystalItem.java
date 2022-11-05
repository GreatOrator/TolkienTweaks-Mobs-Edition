package com.greatorator.tolkienmobs.item.tools;

import com.greatorator.tolkienmobs.entity.boss.fellbeast.phase.FellBeastFightManager;
import com.greatorator.tolkienmobs.entity.item.MorgulCrystalEntity;
import com.greatorator.tolkienmobs.world.server.TolkienServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class MorgulCrystalItem extends Item {
   public MorgulCrystalItem(Properties p_i48503_1_) {
      super(p_i48503_1_);
   }

   @Override
   public InteractionResult useOn(UseOnContext context) {
      Level level = context.getLevel();
      BlockPos blockpos = context.getClickedPos();
      BlockState blockstate = level.getBlockState(blockpos);
      if (!blockstate.is(Blocks.OBSIDIAN) && !blockstate.is(Blocks.BEDROCK)) {
         return InteractionResult.FAIL;
      } else {
         BlockPos blockpos1 = blockpos.above();
         if (!level.isEmptyBlock(blockpos1)) {
            return InteractionResult.FAIL;
         } else {
            double d0 = (double)blockpos1.getX();
            double d1 = (double)blockpos1.getY();
            double d2 = (double)blockpos1.getZ();
            List<Entity> list = level.getEntities((Entity)null, new AABB(d0, d1, d2, d0 + 1.0D, d1 + 2.0D, d2 + 1.0D));
            if (!list.isEmpty()) {
               return InteractionResult.FAIL;
            } else {
               if (level instanceof ServerLevel) {
                  MorgulCrystalEntity morgulcrystalentity = new MorgulCrystalEntity(level, d0 + 0.5D, d1, d2 + 0.5D);
                  morgulcrystalentity.setShowBottom(true);
                  level.addFreshEntity(morgulcrystalentity);
                  level.gameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, blockpos1);
                  FellBeastFightManager fellBeastFightManager = ((TolkienServerLevel)level).fellbeastFight();
                  if (fellBeastFightManager != null) {
                     fellBeastFightManager.tryRespawn();
                  }
               }

               context.getItemInHand().shrink(1);
               return InteractionResult.sidedSuccess(level.isClientSide);
            }
         }
      }
   }

   @Override
   public boolean isFoil(ItemStack p_77636_1_) {
      return true;
   }
}