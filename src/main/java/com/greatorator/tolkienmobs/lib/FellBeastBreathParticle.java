package com.greatorator.tolkienmobs.lib;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FellBeastBreathParticle extends SpriteTexturedParticle {
    private boolean hasHitGround;
    private final IAnimatedSprite sprites;

    private FellBeastBreathParticle(ClientWorld p_i232359_1_, double p_i232359_2_, double p_i232359_4_, double p_i232359_6_, double p_i232359_8_, double p_i232359_10_, double p_i232359_12_, IAnimatedSprite p_i232359_14_) {
        super(p_i232359_1_, p_i232359_2_, p_i232359_4_, p_i232359_6_);
        this.xd = p_i232359_8_;
        this.yd = p_i232359_10_;
        this.zd = p_i232359_12_;
        this.rCol = MathHelper.nextFloat(this.random, 0.1176470F, 0.4078431F);
        this.gCol = MathHelper.nextFloat(this.random, 0.6196078F, 0.7686274F);
        this.bCol = MathHelper.nextFloat(this.random, 0.3019607F, 0.3803921F);
        this.quadSize *= 0.75F;
        this.lifetime = (int)(20.0D / ((double)this.random.nextFloat() * 0.8D + 0.2D));
        this.hasHitGround = false;
        this.hasPhysics = false;
        this.sprites = p_i232359_14_;
        this.setSpriteFromAge(p_i232359_14_);
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.setSpriteFromAge(this.sprites);
            if (this.onGround) {
                this.yd = 0.0D;
                this.hasHitGround = true;
            }

            if (this.hasHitGround) {
                this.yd += 0.002D;
            }

            this.move(this.xd, this.yd, this.zd);
            if (this.y == this.yo) {
                this.xd *= 1.1D;
                this.zd *= 1.1D;
            }

            this.xd *= (double)0.96F;
            this.zd *= (double)0.96F;
            if (this.hasHitGround) {
                this.yd *= (double)0.96F;
            }

        }
    }

    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public float getQuadSize(float p_217561_1_) {
        return this.quadSize * MathHelper.clamp(((float)this.age + p_217561_1_) / (float)this.lifetime * 32.0F, 0.0F, 1.0F);
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite sprites;

        public Factory(IAnimatedSprite p_i50559_1_) {
            this.sprites = p_i50559_1_;
        }

        public Particle createParticle(BasicParticleType p_199234_1_, ClientWorld p_199234_2_, double p_199234_3_, double p_199234_5_, double p_199234_7_, double p_199234_9_, double p_199234_11_, double p_199234_13_) {
            return new FellBeastBreathParticle(p_199234_2_, p_199234_3_, p_199234_5_, p_199234_7_, p_199234_9_, p_199234_11_, p_199234_13_, this.sprites);
        }
    }
}
