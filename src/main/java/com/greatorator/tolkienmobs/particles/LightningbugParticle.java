package com.greatorator.tolkienmobs.particles;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LightningbugParticle extends TextureSheetParticle {
    private final int halfLife;

    LightningbugParticle(ClientLevel level, double xCoord, double yCoord, double zCoord, double xd, double yd, double zd) {
        super(level, xCoord, yCoord, zCoord, xd, yd, zd);
        this.xd *= 2.1;
        this.yd *= 2.1;
        this.zd *= 2.1;
        this.rCol = 0.9F;
        this.gCol = 1.0F;
        this.bCol = 0.0F;
        this.quadSize = 0.2f + (random.nextFloat() * 0.1f);
        this.lifetime = 10 + random.nextInt(21);
        this.halfLife = lifetime / 2;
        hasPhysics = true;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void render(VertexConsumer buffer, Camera entity, float partialTicks) {
        alpha = getGlowBrightness();
        super.render(buffer, entity, partialTicks);
    }

    @Override
    public void tick() {
        if (age++ >= lifetime) {
            remove();
        }
    }

    public float getGlowBrightness() {
        int lifeTime = lifetime - age;
        if (lifeTime <= halfLife) {
            return (float) lifeTime / (float) halfLife;
        } else {
            return Math.max(1.0f - (((float) lifeTime - halfLife) / halfLife), 0);
        }
    }

    @Override
    public int getLightColor(float partialTicks) {
        return 0xF000F0;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet sprite) {
            this.spriteSet = sprite;
        }

        @Override
        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            LightningbugParticle particle = new LightningbugParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.pickSprite(this.spriteSet);
            return particle;
        }
    }
}
