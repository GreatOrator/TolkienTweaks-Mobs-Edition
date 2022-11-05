package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.TolkienConfig;
import com.greatorator.tolkienmobs.utils.TTMDamageSource;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;

import java.util.Random;

public class HallowedBlock extends Block
{
    public HallowedBlock(Properties properties)
    {
        super(properties);
    }

    /**
     * Called when the given entity walks on this Block
     */
    @Override
    public void stepOn(Level worldIn, BlockPos pos, BlockState blockState, Entity entityIn)
    {
        if (entityIn instanceof LivingEntity && ((LivingEntity) entityIn).isInvertedHealAndHarm() && entityIn.canChangeDimensions())
        {
            if(!TolkienConfig.disableFakePlayer) {
                FakePlayer fakePlayer = FakePlayerFactory.getMinecraft((ServerLevel) worldIn);
                entityIn.hurt(TTMDamageSource.causeHallowedDamage(fakePlayer), 3.0F);
            }else {
                entityIn.hurt(DamageSource.MAGIC, 2.0F);
            }
        }

        super.stepOn(worldIn, pos, blockState, entityIn);
    }

    public void update(Level worldIn, BlockPos pos, BlockState state, Random rand)
    {
        BlockPos blockpos = pos.above();
        BlockState iblockstate = worldIn.getBlockState(blockpos);

        if (iblockstate.getBlock() == Blocks.WATER)
        {
            worldIn.setBlockAndUpdate(blockpos, Blocks.AIR.defaultBlockState());
            worldIn.playSound((Player)null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (worldIn.random.nextFloat() - worldIn.random.nextFloat()) * 0.8F);

            if (worldIn instanceof ServerLevel)
            {
                ((ServerLevel)worldIn).sendParticles(ParticleTypes.LARGE_SMOKE, (double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.25D, (double)blockpos.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D);
            }
        }
    }

    public boolean canEntitySpawn(BlockState state, Entity entityIn)
    {
        return entityIn.fireImmune();
    }
}