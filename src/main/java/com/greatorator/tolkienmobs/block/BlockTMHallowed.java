package com.greatorator.tolkienmobs.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;

import java.util.Random;

public class BlockTMHallowed extends Block
{
    private static final String hallowedDamageName = "hallowed";
    public static final DamageSource hallowed = new DamageSource(hallowedDamageName);

    public BlockTMHallowed(Properties properties)
    {
        super(properties);
    }

    /**
     * Called when the given entity walks on this Block
     */
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
        if (entityIn instanceof LivingEntity && ((LivingEntity) entityIn).isEntityUndead() && entityIn.isNonBoss())
        {
                FakePlayer fakePlayer = FakePlayerFactory.getMinecraft((ServerWorld) worldIn);
                entityIn.attackEntityFrom(new EntityDamageSource(hallowedDamageName, fakePlayer), 2.0F);
        }

        super.onEntityWalk(worldIn, pos, entityIn);
    }

    public void update(World worldIn, BlockPos pos, BlockState state, Random rand)
    {
        BlockPos blockpos = pos.up();
        BlockState iblockstate = worldIn.getBlockState(blockpos);

        if (iblockstate.getBlock() == Blocks.WATER)
        {
            worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState());
            worldIn.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);

            if (worldIn instanceof ServerWorld)
            {
                ((ServerWorld)worldIn).spawnParticle(ParticleTypes.LARGE_SMOKE, (double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.25D, (double)blockpos.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D);
            }
        }
    }

    public boolean canEntitySpawn(BlockState state, Entity entityIn)
    {
        return entityIn.isImmuneToFire();
    }
}