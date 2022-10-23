package com.greatorator.tolkienmobs.lib;

import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LeafParticle extends SpriteTexturedParticle {
    protected static final float TAU = (float) (2 * Math.PI); // 1 rotation

    protected static final int FADE_DURATION = 16; // ticks
    protected static final double WATER_FRICTION = 0.05;

    protected final float windCoefficient; // to emulate drag/lift

    protected final float maxRotateSpeed; // rotations / tick
    protected final int maxRotateTime;
    protected int rotateTime = 0;

    protected LeafParticle(ClientWorld clientWorld, double x, double y, double z, double r, double g, double b, IAnimatedSprite provider) {
        super(clientWorld, x, y, z, 0, 0, 0);
        this.pickSprite(provider);


        this.gravity = 0.08f + random.nextFloat() * 0.04f;
        this.windCoefficient = 0.6f + random.nextFloat() * 0.4f;

        // the Particle constructor adds random noise to the velocity which we don't want
        this.xd = 0.0;
        this.yd = 0.0;
        this.zd = 0.0;

        this.hasPhysics = true;
        this.lifetime = TTMRand.getRandomInteger(200, 100);

        this.rCol = (float) r;
        this.gCol = (float) g;
        this.bCol = (float) b;
        // accelerate over 3-7 seconds to at most 2.5 rotations per second
        this.maxRotateTime = (3 + random.nextInt(4 + 1)) * 20;
        this.maxRotateSpeed = (random.nextBoolean() ? -1 : 1) * (0.1f + 2.4f * random.nextFloat()) * TAU / 20f;

        this.roll = this.oRoll = random.nextFloat() * TAU;

        this.quadSize = TTMRand.getRandomInteger(4, 1) / 50f;
    }

    @Override
    public void tick() {
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.oRoll = this.roll;

        age++;

        // fade-out animation
        if (age >= lifetime + 1 - FADE_DURATION) {
            this.alpha -= 1F / FADE_DURATION;
        }

        if (age >= lifetime) {
            this.remove();
            return;
        }

        if (this.level.getFluidState(new BlockPos(x, y, z)).is(FluidTags.WATER)) {
            // float on water
            yd = 0.0;
            rotateTime = 0;

            xd *= (1 - WATER_FRICTION);
            zd *= (1 - WATER_FRICTION);
        } else {
            // apply gravity
            yd -= 0.04 * gravity;

            if (!onGround) {
                // spin when in the air
                rotateTime = Math.min(rotateTime + 1, maxRotateTime);
                this.roll += (rotateTime / (float) maxRotateTime) * maxRotateSpeed;
            } else {
                rotateTime = 0;
            }

            // approach the target wind velocity over time via vel += (target - vel) * f, where f is in (0, 1)
            // after n ticks, the distance closes to a factor of 1 - (1 - f)^n.
            // for f = 1 / 2, it would only take 4 ticks to close the distance by 90%
            // for f = 1 / 60, it takes ~2 seconds to halve the distance, ~5 seconds to reach 80%
            //
            // the wind coefficient is just another factor in (0, 1) to add some variance between leaves.
            // this implementation lags behind the actual wind speed and will never reach it fully,
            // so wind speeds needs to be adjusted accordingly
            xd += (Breeze.windX - xd) * windCoefficient / 60.0f;
            zd += (Breeze.windZ - zd) * windCoefficient / 60.0f;
        }

        move(xd, yd, zd);
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class DefaultFactory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite provider;

        public DefaultFactory(IAnimatedSprite provider) {
            this.provider = provider;
        }

        @Override
        public Particle createParticle(BasicParticleType parameters, ClientWorld world, double x, double y, double z, double r, double g, double b) {
            return new LeafParticle(world, x, y, z, r, g, b, this.provider);
        }
    }
}
