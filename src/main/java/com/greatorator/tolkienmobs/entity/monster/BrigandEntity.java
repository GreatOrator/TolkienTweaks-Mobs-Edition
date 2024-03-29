package com.greatorator.tolkienmobs.entity.monster;

import com.greatorator.tolkienmobs.entity.MonsterEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class BrigandEntity extends MonsterEntity {
    private long nextAbilityUse = 0L;
    private final static long coolDown = 15000L;

    public BrigandEntity(EntityType<? extends MonsterEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 1.75F;
    }

    @Override
    public MobType getMobType() {
        return MobType.ILLAGER;
    }

    /** Special Attack */
    @Override
    public boolean doHurtTarget(Entity entityIn) {
        long time = System.currentTimeMillis();
        if (super.doHurtTarget(entityIn)) {
            if (entityIn instanceof Player) {
                if (time > nextAbilityUse) {
                    nextAbilityUse = time + coolDown;
                    Player player = (Player) entityIn;
                    BlockPos blockpos = player.blockPosition();
                    ItemEntity dropItem = player.drop(player.getInventory().removeItem(player.getInventory().selected, 1), false);
                    if (dropItem != null) {
                        dropItem.setPickUpDelay(50);
                        level.playSound(null, blockpos, SoundEvents.SLIME_ATTACK, SoundSource.HOSTILE, 1.0F + level.random.nextFloat(), level.random.nextFloat() * 0.7F + 0.3F);
                    }
                }
            }
        }
        return true;
    }
}