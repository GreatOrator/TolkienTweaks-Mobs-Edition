package com.greatorator.tolkienmobs.entity;

import com.greatorator.tolkienmobs.entity.entityai.EntityAITTMAttack;
import com.greatorator.tolkienmobs.entity.passive.EntityTMDwarf;
import com.greatorator.tolkienmobs.entity.passive.EntityTMElves;
import com.greatorator.tolkienmobs.entity.passive.EntityTMHobbit;
import com.greatorator.tolkienmobs.entity.passive.EntityTMHuman;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityTMAquatics extends EntityTMHostiles {
    public float watcherPitch;
    public float prevWatcherPitch;
    public float watcherYaw;
    public float prevWatcherYaw;
    public float watcherRotation;
    public float prevWatcherRotation;
    private float randomMotionSpeed;
    private float rotationVelocity;
    private float rotateSpeed;
    private float randomMotionVecX;
    private float randomMotionVecY;
    private float randomMotionVecZ;
    private boolean hostileWater;

    public EntityTMAquatics(World worldIn) {
        super(worldIn);
        this.rand.setSeed((long)(1 + this.getEntityId()));
        this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityTMAquatics.AIMoveRandom(this));
        this.tasks.addTask(1, new EntityAITTMAttack(this, 0.75D, false));
        if (hostileWater) {
            this.applyEntityAI();
        }
    }

    private void applyEntityAI() {
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityTMHobbit.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityTMHuman.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityTMDwarf.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityTMElves.class, true));
    }

    public boolean canBreatheUnderwater()
    {
        return true;
    }

    public boolean isNotColliding()
    {
        return this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this);
    }

    public void onEntityUpdate()
    {
        int i = this.getAir();
        super.onEntityUpdate();

        if (this.isEntityAlive() && !this.isInWater())
        {
            --i;
            this.setAir(i);

            if (this.getAir() == -20)
            {
                this.setAir(0);
                this.attackEntityFrom(DamageSource.DROWN, 2.0F);
            }
        }
        else
        {
            this.setAir(300);
        }
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        this.prevWatcherPitch = this.watcherPitch;
        this.prevWatcherYaw = this.watcherYaw;
        this.prevWatcherRotation = this.watcherRotation;
        this.watcherRotation += this.rotationVelocity;

        if ((double)this.watcherRotation > (Math.PI * 2D))
        {
            if (this.world.isRemote)
            {
                this.watcherRotation = ((float)Math.PI * 2F);
            }
            else
            {
                this.watcherRotation = (float)((double)this.watcherRotation - (Math.PI * 2D));

                if (this.rand.nextInt(10) == 0)
                {
                    this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
                }

                this.world.setEntityState(this, (byte)19);
            }
        }

        if (this.inWater)
        {
            if (this.watcherRotation < (float)Math.PI)
            {
                float f = this.watcherRotation / (float)Math.PI;

                if ((double)f > 0.75D)
                {
                    this.randomMotionSpeed = 1.0F;
                    this.rotateSpeed = 1.0F;
                }
                else
                {
                    this.rotateSpeed *= 0.8F;
                }
            }
            else
            {
                this.randomMotionSpeed *= 0.9F;
                this.rotateSpeed *= 0.99F;
            }

            if (!this.world.isRemote)
            {
                this.motionX = (double)(this.randomMotionVecX * this.randomMotionSpeed);
                this.motionY = (double)(this.randomMotionVecY * this.randomMotionSpeed);
                this.motionZ = (double)(this.randomMotionVecZ * this.randomMotionSpeed);
            }

            float f1 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.renderYawOffset += (-((float)MathHelper.atan2(this.motionX, this.motionZ)) * (180F / (float)Math.PI) - this.renderYawOffset) * 0.1F;
            this.rotationYaw = this.renderYawOffset;
            this.watcherYaw = (float)((double)this.watcherYaw + Math.PI * (double)this.rotateSpeed * 1.5D);
            this.watcherPitch += (-((float)MathHelper.atan2((double)f1, this.motionY)) * (180F / (float)Math.PI) - this.watcherPitch) * 0.1F;
        }
        else
        {
            if (!this.world.isRemote)
            {
                this.motionX = 0.0D;
                this.motionZ = 0.0D;

                if (this.isPotionActive(MobEffects.LEVITATION))
                {
                    this.motionY += 0.05D * (double)(this.getActivePotionEffect(MobEffects.LEVITATION).getAmplifier() + 1) - this.motionY;
                }
                else if (!this.hasNoGravity())
                {
                    this.motionY -= 0.08D;
                }

                this.motionY *= 0.9800000190734863D;
            }

            this.watcherPitch = (float)((double)this.watcherPitch + (double)(-90.0F - this.watcherPitch) * 0.02D);
        }
    }

    public void travel(float strafe, float vertical, float forward)
    {
        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
    }

    public boolean getCanSpawnHere()
    {
        return this.posY > 45.0D && this.posY < (double)this.world.getSeaLevel() && super.getCanSpawnHere();
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 19)
        {
            this.watcherRotation = 0.0F;
        }
        else
        {
            super.handleStatusUpdate(id);
        }
    }

    public void setMovementVector(float randomMotionVecXIn, float randomMotionVecYIn, float randomMotionVecZIn)
    {
        this.randomMotionVecX = randomMotionVecXIn;
        this.randomMotionVecY = randomMotionVecYIn;
        this.randomMotionVecZ = randomMotionVecZIn;
    }

    public boolean hasMovementVector()
    {
        return this.randomMotionVecX != 0.0F || this.randomMotionVecY != 0.0F || this.randomMotionVecZ != 0.0F;
    }

    static class AIMoveRandom extends EntityAIBase
    {
        private final EntityTMAquatics watcher;

        public AIMoveRandom(EntityTMAquatics p_i45859_1_)
        {
            this.watcher = p_i45859_1_;
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            return true;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask()
        {
            int i = this.watcher.getIdleTime();

            if (i > 100)
            {
                this.watcher.setMovementVector(0.0F, 0.0F, 0.0F);
            }
            else if (this.watcher.getRNG().nextInt(50) == 0 || !this.watcher.inWater || !this.watcher.hasMovementVector())
            {
                float f = this.watcher.getRNG().nextFloat() * ((float)Math.PI * 2F);
                float f1 = MathHelper.cos(f) * 0.2F;
                float f2 = -0.1F + this.watcher.getRNG().nextFloat() * 0.2F;
                float f3 = MathHelper.sin(f) * 0.2F;
                this.watcher.setMovementVector(f1, f2, f3);
            }
        }
    }

    protected boolean canTriggerWalking()
    {
        return false;
    }

    public boolean isPushedByWater()
    {
        return false;
    }

    @Override
    public double getAttackDamage() {
        return 0;
    }

    @Override
    public double getArmorStrength() {
        return 0;
    }

    @Override
    public double getHealthLevel() {
        return 0;
    }

    public void setHostileWater(boolean hostileWater) {
        this.hostileWater = hostileWater;
    }
}