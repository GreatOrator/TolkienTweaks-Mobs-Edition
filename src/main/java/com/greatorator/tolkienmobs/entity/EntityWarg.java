package com.greatorator.tolkienmobs.entity;

import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EntityWarg extends EntityWolf {
    private int texture_index;
    public static final ResourceLocation LOOT = new ResourceLocation(TolkienMobs.MODID, "entities/warg");
    /** Float used to smooth the rotation of the wolf head */
    private float headRotationCourse;
    private float headRotationCourseOld;
    /** true is the wolf is wet else false */
    private boolean isWet;
    /** True if the wolf is shaking else False */
    private boolean isShaking;
    /** This time increases while wolf is shaking and emitting water particles. */
    private float timeWolfIsShaking;
    private float prevTimeWolfIsShaking;

    public EntityWarg(World worldIn) {
        super(worldIn);
        this.setSize(1.1F, 1.1F);
        this.setTamed(false);
        this.texture_index = rand.nextInt(4);
    }

    protected void initEntityAI(){
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(4, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(5, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(8, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityMordorOrc.class, 8.0F));
        this.tasks.addTask(10, new EntityAILookIdle(this));
        this.tasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[]{EntityWarg.class}));

        this.tasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
        this.tasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySheep.class, false));
        this.tasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityRabbit.class, false));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        // Here we set various attributes for our mob. Like maximum health, armor, speed, ...
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
    }

    public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn)
    {
        super.setAttackTarget(entitylivingbaseIn);

        if (entitylivingbaseIn == null)
        {
            this.setAngry(false);
        }
        else if (!this.isTamed())
        {
            this.setAngry(true);
        }
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_WOLF_GROWL;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_WOLF_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_WOLF_DEATH;
    }

    protected float getSoundVolume()
    {
        return 0.4F;
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (!this.world.isRemote && this.isWet && !this.isShaking && !this.hasPath() && this.onGround)
        {
            this.isShaking = true;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
            this.world.setEntityState(this, (byte)8);
        }

        if (!this.world.isRemote && this.getAttackTarget() == null && this.isAngry())
        {
            this.setAngry(false);
        }
    }

    public void onUpdate()
    {
        super.onUpdate();
        this.headRotationCourseOld = this.headRotationCourse;

            this.headRotationCourse += (0.0F - this.headRotationCourse) * 0.4F;

        if (this.isWet())
        {
            this.isWet = true;
            this.isShaking = false;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
        }
        else if ((this.isWet || this.isShaking) && this.isShaking)
        {
            if (this.timeWolfIsShaking == 0.0F)
            {
                this.playSound(SoundEvents.ENTITY_WOLF_SHAKE, this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            }

            this.prevTimeWolfIsShaking = this.timeWolfIsShaking;
            this.timeWolfIsShaking += 0.05F;

            if (this.prevTimeWolfIsShaking >= 2.0F)
            {
                this.isWet = false;
                this.isShaking = false;
                this.prevTimeWolfIsShaking = 0.0F;
                this.timeWolfIsShaking = 0.0F;
            }

            if (this.timeWolfIsShaking > 0.4F)
            {
                float f = (float)this.getEntityBoundingBox().minY;
                int i = (int)(MathHelper.sin((this.timeWolfIsShaking - 0.4F) * (float)Math.PI) * 7.0F);

                for (int j = 0; j < i; ++j)
                {
                    float f1 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
                    float f2 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
                    this.world.spawnParticle(EnumParticleTypes.WATER_SPLASH, this.posX + (double)f1, (double)(f + 0.8F), this.posZ + (double)f2, this.motionX, this.motionY, this.motionZ);
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean isWolfWet()
    {
        return this.isWet;
    }

    @SideOnly(Side.CLIENT)
    public float getShadingWhileWet(float p_70915_1_)
    {
        return 0.75F + (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * p_70915_1_) / 2.0F * 0.25F;
    }

    @SideOnly(Side.CLIENT)
    public float getShakeAngle(float p_70923_1_, float p_70923_2_)
    {
        float f = (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * p_70923_1_ + p_70923_2_) / 1.8F;

        if (f < 0.0F)
        {
            f = 0.0F;
        }
        else if (f > 1.0F)
        {
            f = 1.0F;
        }

        return MathHelper.sin(f * (float)Math.PI) * MathHelper.sin(f * (float)Math.PI * 11.0F) * 0.15F * (float)Math.PI;
    }

    @SideOnly(Side.CLIENT)
    public float getInterestedAngle(float p_70917_1_)
    {
        return (this.headRotationCourseOld + (this.headRotationCourse - this.headRotationCourseOld) * p_70917_1_) * 0.15F * (float)Math.PI;
    }

    public float getEyeHeight()
    {
        return this.height * 0.8F;
    }

    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else
        {
            Entity entity = source.getTrueSource();

            if (this.aiSit != null)
            {
                this.aiSit.setSitting(false);
            }

            if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow))
            {
                amount = (amount + 1.0F) / 2.0F;
            }

            return super.attackEntityFrom(source, amount);
        }
    }

    public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)((int)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));

        if (flag)
        {
            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 8)
        {
            this.isShaking = true;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
        }
        else
        {
            super.handleStatusUpdate(id);
        }
    }

    @SideOnly(Side.CLIENT)
    public float getTailRotation()
    {
        return 1.5393804F;
    }

    public int getTextureIndex() {
        return this.texture_index;
    }
}
