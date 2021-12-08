package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.utils.TTMDamageSource;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;

import java.util.Random;

public class BlockTTMHallowed extends Block
{
    public BlockTTMHallowed(Properties properties)
    {
        super(properties);
    }

    /**
     * Called when the given entity walks on this Block
     */
    @Override
    public void stepOn(World worldIn, BlockPos pos, Entity entityIn)
    {
        if (entityIn instanceof LivingEntity && ((LivingEntity) entityIn).isInvertedHealAndHarm() && entityIn.canChangeDimensions())
        {
            if(!TTMConfig.disableFakePlayer) {
                FakePlayer fakePlayer = FakePlayerFactory.getMinecraft((ServerWorld) worldIn);
                entityIn.hurt(TTMDamageSource.causeHallowedDamage(fakePlayer), 3.0F);
            }else {
                entityIn.hurt(DamageSource.MAGIC, 2.0F);
            }
        }

        super.stepOn(worldIn, pos, entityIn);
    }

    public void update(World worldIn, BlockPos pos, BlockState state, Random rand)
    {
        BlockPos blockpos = pos.above();
        BlockState iblockstate = worldIn.getBlockState(blockpos);

        if (iblockstate.getBlock() == Blocks.WATER)
        {
            worldIn.setBlockAndUpdate(blockpos, Blocks.AIR.defaultBlockState());
            worldIn.playSound((PlayerEntity)null, pos, SoundEvents.FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.random.nextFloat() - worldIn.random.nextFloat()) * 0.8F);

            if (worldIn instanceof ServerWorld)
            {
                ((ServerWorld)worldIn).sendParticles(ParticleTypes.LARGE_SMOKE, (double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.25D, (double)blockpos.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D);
            }
        }
    }

    public boolean canEntitySpawn(BlockState state, Entity entityIn)
    {
        return entityIn.fireImmune();
    }
}