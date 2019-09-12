package com.greatorator.tolkienmobs.entity;

import com.google.common.base.Predicate;
import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.init.SoundInit;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.block.material.Material;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityTMAquatics extends EntityMob implements IMob {
    private static final DataParameter<Integer> SKIN_TYPE = EntityDataManager.<Integer>createKey(EntityTMAquatics.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> MOVING = EntityDataManager.<Boolean>createKey(EntityTMAquatics.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> TARGET_ENTITY = EntityDataManager.<Integer>createKey(EntityTMAquatics.class, DataSerializers.VARINT);
    private EntityLivingBase targetedEntity;
    private int clientSideAttackTime;
    private boolean clientSideTouchedGround;
    protected EntityAIWander wander;
    private ResourceLocation lootTable;
    private int rndMax;
    private int rndMin;
    private static long nextAbilityUse = 0L;
    private final static long coolDown = 15000L;
    private static Potion ttmEffect;
    private static int ttmDuration;
    private static boolean hostileWater;

    public EntityTMAquatics(World worldIn) {
        super(worldIn);
        this.moveHelper = new EntityTMAquatics.TMAquaticsMoveHelper(this);
    }

    protected void initEntityAI()
    {
        EntityAIMoveTowardsRestriction entityaimovetowardsrestriction = new EntityAIMoveTowardsRestriction(this, 1.0D);
        this.wander = new EntityAIWander(this, 1.0D, 80);
        this.tasks.addTask(4, new EntityTMAquatics.AITMAquaticsAttack(this));
        this.tasks.addTask(5, entityaimovetowardsrestriction);
        this.tasks.addTask(7, this.wander);
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityTMAquatics.class, 12.0F, 0.01F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.wander.setMutexBits(3);
        entityaimovetowardsrestriction.setMutexBits(3);
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 10, true, false, new EntityTMAquatics.TMAquaticsTargetSelector(this)));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(12.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(17.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.55D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getHealthLevel());
    }

    public static void registerFixesTMAquatics(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityTMAquatics.class);
    }

    protected PathNavigate createNavigator(World worldIn)
    {
        return new PathNavigateSwimmer(this, worldIn);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(MOVING, Boolean.valueOf(false));
        this.dataManager.register(TARGET_ENTITY, Integer.valueOf(0));
        this.dataManager.register(SKIN_TYPE, Integer.valueOf(0));
    }

    public boolean isMoving()
    {
        return ((Boolean)this.dataManager.get(MOVING)).booleanValue();
    }

    private void setMoving(boolean moving)
    {
        this.dataManager.set(MOVING, Boolean.valueOf(moving));
    }

    public int getAttackDuration()
    {
        return 80;
    }

    private void setTargetedEntity(int entityId)
    {
        this.dataManager.set(TARGET_ENTITY, Integer.valueOf(entityId));
    }

    public boolean hasTargetedEntity()
    {
        return ((Integer)this.dataManager.get(TARGET_ENTITY)).intValue() != 0;
    }

    @Nullable
    public EntityLivingBase getTargetedEntity()
    {
        if (!this.hasTargetedEntity())
        {
            return null;
        }
        else if (this.world.isRemote)
        {
            if (this.targetedEntity != null)
            {
                return this.targetedEntity;
            }
            else
            {
                Entity entity = this.world.getEntityByID(((Integer)this.dataManager.get(TARGET_ENTITY)).intValue());

                if (entity instanceof EntityLivingBase)
                {
                    this.targetedEntity = (EntityLivingBase)entity;
                    return this.targetedEntity;
                }
                else
                {
                    return null;
                }
            }
        }
        else
        {
            return this.getAttackTarget();
        }
    }

    public void notifyDataManagerChange(DataParameter<?> key)
    {
        super.notifyDataManagerChange(key);

        if (TARGET_ENTITY.equals(key))
        {
            this.clientSideAttackTime = 0;
            this.targetedEntity = null;
        }
    }

    public int getTalkInterval()
    {
        return 160;
    }

    protected SoundEvent getAmbientSound()
    {
        return this.isInWater() ? SoundInit.soundIdleWatcher : SoundInit.soundIdleWatcher;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return this.isInWater() ? SoundEvents.ENTITY_GUARDIAN_HURT : SoundEvents.ENTITY_GUARDIAN_HURT_LAND;
    }

    protected SoundEvent getDeathSound()
    {
        return this.isInWater() ? SoundEvents.ENTITY_GUARDIAN_DEATH : SoundEvents.ENTITY_GUARDIAN_DEATH_LAND;
    }

    protected boolean canTriggerWalking()
    {
        return false;
    }

    public float getEyeHeight()
    {
        return this.height * 0.5F;
    }

    public float getBlockPathWeight(BlockPos pos)
    {
        return this.world.getBlockState(pos).getMaterial() == Material.WATER ? 10.0F + this.world.getLightBrightness(pos) - 0.5F : super.getBlockPathWeight(pos);
    }

    public void onLivingUpdate()
    {
        if (this.world.isRemote)
        {
            if (!this.isInWater())
            {
                if (this.motionY > 0.0D && this.clientSideTouchedGround && !this.isSilent())
                {
                    this.world.playSound(this.posX, this.posY, this.posZ, this.getFlopSound(), this.getSoundCategory(), 1.0F, 1.0F, false);
                }

                this.clientSideTouchedGround = this.motionY < 0.0D && this.world.isBlockNormalCube((new BlockPos(this)).down(), false);
            }

            if (this.isMoving() && this.isInWater())
            {
                Vec3d vec3d = this.getLook(0.0F);

                for (int i = 0; i < 2; ++i)
                {
                    this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width - vec3d.x * 1.5D, this.posY + this.rand.nextDouble() * (double)this.height - vec3d.y * 1.5D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width - vec3d.z * 1.5D, 0.0D, 0.0D, 0.0D);
                }
            }

            if (this.hasTargetedEntity())
            {
                if (this.clientSideAttackTime < this.getAttackDuration())
                {
                    ++this.clientSideAttackTime;
                }

                EntityLivingBase entitylivingbase = this.getTargetedEntity();

                if (entitylivingbase != null)
                {
                    this.getLookHelper().setLookPositionWithEntity(entitylivingbase, 90.0F, 90.0F);
                    this.getLookHelper().onUpdateLook();
                    double d5 = (double)this.getAttackAnimationScale(0.0F);
                    double d0 = entitylivingbase.posX - this.posX;
                    double d1 = entitylivingbase.posY + (double)(entitylivingbase.height * 0.5F) - (this.posY + (double)this.getEyeHeight());
                    double d2 = entitylivingbase.posZ - this.posZ;
                    double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                    d0 = d0 / d3;
                    d1 = d1 / d3;
                    d2 = d2 / d3;
                    double d4 = this.rand.nextDouble();

                    while (d4 < d3)
                    {
                        d4 += 1.8D - d5 + this.rand.nextDouble() * (1.7D - d5);
                        this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX + d0 * d4, this.posY + d1 * d4 + (double)this.getEyeHeight(), this.posZ + d2 * d4, 0.0D, 0.0D, 0.0D);
                    }
                }
            }
        }

        if (this.inWater)
        {
            this.setAir(300);
        }
        else if (this.onGround)
        {
            this.motionY += 0.5D;
            this.motionX += (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 0.4F);
            this.motionZ += (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 0.4F);
            this.rotationYaw = this.rand.nextFloat() * 360.0F;
            this.onGround = false;
            this.isAirBorne = true;
        }

        if (this.hasTargetedEntity())
        {
            this.rotationYaw = this.rotationYawHead;
        }

        super.onLivingUpdate();
    }

    protected SoundEvent getFlopSound()
    {
        return SoundEvents.ENTITY_GUARDIAN_FLOP;
    }

    public float getAttackAnimationScale(float p_175477_1_)
    {
        return ((float)this.clientSideAttackTime + p_175477_1_) / (float)this.getAttackDuration();
    }

    /**
     * Checks to make sure the light is not too bright where the mob is spawning
     */
    protected boolean isValidLightLevel()
    {
        return true;
    }

    /**
     * Checks that the entity is not colliding with any blocks / liquids
     */
    public boolean isNotColliding()
    {
        return this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this) && this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty();
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (!this.isMoving() && !source.isMagicDamage() && source.getImmediateSource() instanceof EntityLivingBase)
        {
            EntityLivingBase entitylivingbase = (EntityLivingBase)source.getImmediateSource();

            if (!source.isExplosion())
            {
                entitylivingbase.attackEntityFrom(DamageSource.causeThornsDamage(this), 2.0F);
            }
        }

        if (this.wander != null)
        {
            this.wander.makeUpdate();
        }

        return super.attackEntityFrom(source, amount);
    }

    public int getVerticalFaceSpeed()
    {
        return 180;
    }

    public void travel(float strafe, float vertical, float forward)
    {
        if (this.isServerWorld() && this.isInWater())
        {
            this.moveRelative(strafe, vertical, forward, 0.1F);
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.8999999761581421D;
            this.motionY *= 0.8999999761581421D;
            this.motionZ *= 0.8999999761581421D;

            if (!this.isMoving() && this.getAttackTarget() == null)
            {
                this.motionY -= 0.005D;
            }
        }
        else
        {
            super.travel(strafe, vertical, forward);
        }
    }

    static class AITMAquaticsAttack extends EntityAIBase
    {
        private final EntityTMAquatics tmaquatics;
        private int tickCounter;
        private final boolean isElder;

        public AITMAquaticsAttack(EntityTMAquatics tmaquatics)
        {
            this.tmaquatics = tmaquatics;
            this.isElder = tmaquatics != null;
            this.setMutexBits(3);
        }

        public boolean shouldExecute()
        {
            EntityLivingBase entitylivingbase = this.tmaquatics.getAttackTarget();
            return entitylivingbase != null && entitylivingbase.isEntityAlive();
        }

        public boolean shouldContinueExecuting()
        {
            return super.shouldContinueExecuting() && (this.isElder || this.tmaquatics.getDistanceSq(this.tmaquatics.getAttackTarget()) > 9.0D);
        }

        public void startExecuting()
        {
            this.tickCounter = -10;
            this.tmaquatics.getNavigator().clearPath();
            this.tmaquatics.getLookHelper().setLookPositionWithEntity(this.tmaquatics.getAttackTarget(), 90.0F, 90.0F);
            this.tmaquatics.isAirBorne = true;
        }

        public void resetTask()
        {
            this.tmaquatics.setTargetedEntity(0);
            this.tmaquatics.setAttackTarget((EntityLivingBase)null);
            this.tmaquatics.wander.makeUpdate();
        }

        public void updateTask()
        {
            EntityLivingBase entitylivingbase = this.tmaquatics.getAttackTarget();
            this.tmaquatics.getNavigator().clearPath();
            this.tmaquatics.getLookHelper().setLookPositionWithEntity(entitylivingbase, 90.0F, 90.0F);

            if (!this.tmaquatics.canEntityBeSeen(entitylivingbase) || !hostileWater)
            {
                this.tmaquatics.setAttackTarget((EntityLivingBase)null);
            }
            else
            {
                ++this.tickCounter;

                if (this.tickCounter == 0)
                {
                    this.tmaquatics.setTargetedEntity(this.tmaquatics.getAttackTarget().getEntityId());
                    this.tmaquatics.world.setEntityState(this.tmaquatics, (byte)21);
                }
                else if (this.tickCounter >= this.tmaquatics.getAttackDuration())
                {
                    float f = 1.0F;

                    if (this.tmaquatics.world.getDifficulty() == EnumDifficulty.HARD)
                    {
                        f += 2.0F;
                    }

                    if (this.isElder)
                    {
                        f += 2.0F;
                    }

                    long time = System.currentTimeMillis();
                    if (time > nextAbilityUse) {
                        nextAbilityUse = time + coolDown;

                        if (TTMRand.getRandomInteger(10,0) == 0){
                            entitylivingbase.addPotionEffect(new PotionEffect(ttmEffect, ttmDuration, 3));
                        }
                        else {
                            entitylivingbase.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this.tmaquatics, this.tmaquatics), f);
                            entitylivingbase.attackEntityFrom(DamageSource.causeMobDamage(this.tmaquatics), (float)this.tmaquatics.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
                        }
                    }
                    this.tmaquatics.setAttackTarget((EntityLivingBase)null);
                }

                super.updateTask();
            }
        }
    }

    static class TMAquaticsMoveHelper extends EntityMoveHelper
    {
        private final EntityTMAquatics entityTMAquatics;

        public TMAquaticsMoveHelper(EntityTMAquatics tmaquatics)
        {
            super(tmaquatics);
            this.entityTMAquatics = tmaquatics;
        }

        public void onUpdateMoveHelper()
        {
            if (this.action == EntityMoveHelper.Action.MOVE_TO && !this.entityTMAquatics.getNavigator().noPath())
            {
                double d0 = this.posX - this.entityTMAquatics.posX;
                double d1 = this.posY - this.entityTMAquatics.posY;
                double d2 = this.posZ - this.entityTMAquatics.posZ;
                double d3 = (double) MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float)(MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
                this.entityTMAquatics.rotationYaw = this.limitAngle(this.entityTMAquatics.rotationYaw, f, 90.0F);
                this.entityTMAquatics.renderYawOffset = this.entityTMAquatics.rotationYaw;
                float f1 = (float)(this.speed * this.entityTMAquatics.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
                this.entityTMAquatics.setAIMoveSpeed(this.entityTMAquatics.getAIMoveSpeed() + (f1 - this.entityTMAquatics.getAIMoveSpeed()) * 0.125F);
                double d4 = Math.sin((double)(this.entityTMAquatics.ticksExisted + this.entityTMAquatics.getEntityId()) * 0.5D) * 0.05D;
                double d5 = Math.cos((double)(this.entityTMAquatics.rotationYaw * 0.017453292F));
                double d6 = Math.sin((double)(this.entityTMAquatics.rotationYaw * 0.017453292F));
                this.entityTMAquatics.motionX += d4 * d5;
                this.entityTMAquatics.motionZ += d4 * d6;
                d4 = Math.sin((double)(this.entityTMAquatics.ticksExisted + this.entityTMAquatics.getEntityId()) * 0.75D) * 0.05D;
                this.entityTMAquatics.motionY += d4 * (d6 + d5) * 0.25D;
                this.entityTMAquatics.motionY += (double)this.entityTMAquatics.getAIMoveSpeed() * d1 * 0.1D;
                EntityLookHelper entitylookhelper = this.entityTMAquatics.getLookHelper();
                double d7 = this.entityTMAquatics.posX + d0 / d3 * 2.0D;
                double d8 = (double)this.entityTMAquatics.getEyeHeight() + this.entityTMAquatics.posY + d1 / d3;
                double d9 = this.entityTMAquatics.posZ + d2 / d3 * 2.0D;
                double d10 = entitylookhelper.getLookPosX();
                double d11 = entitylookhelper.getLookPosY();
                double d12 = entitylookhelper.getLookPosZ();

                if (!entitylookhelper.getIsLooking())
                {
                    d10 = d7;
                    d11 = d8;
                    d12 = d9;
                }

                this.entityTMAquatics.getLookHelper().setLookPosition(d10 + (d7 - d10) * 0.125D, d11 + (d8 - d11) * 0.125D, d12 + (d9 - d12) * 0.125D, 10.0F, 40.0F);
                this.entityTMAquatics.setMoving(true);
            }
            else
            {
                this.entityTMAquatics.setAIMoveSpeed(0.0F);
                this.entityTMAquatics.setMoving(false);
            }
        }
    }

    static class TMAquaticsTargetSelector implements Predicate<EntityLivingBase>
    {
        private final EntityTMAquatics parentEntity;

        public TMAquaticsTargetSelector(EntityTMAquatics tmaquatics)
        {
            this.parentEntity = tmaquatics;
        }

        public boolean apply(@Nullable EntityLivingBase p_apply_1_)
        {
            return (p_apply_1_ instanceof EntityPlayer || p_apply_1_ instanceof EntitySquid) && p_apply_1_.getDistanceSq(this.parentEntity) > 9.0D;
        }
    }

    @Nullable
    @Override
    public EntityLivingBase getAttackTarget() {
        return super.getAttackTarget();
    }

    public void setTtmEffect(Potion ttmEffect) {
        this.ttmEffect = ttmEffect;
    }

    public void setTtmDuration(int ttmDuration) {
        this.ttmDuration = ttmDuration;
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        IEntityLivingData ientitylivingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setEquipmentBasedOnDifficulty(difficulty);
        int i = this.getRandomMobType();

        if (ientitylivingdata instanceof EntityTMAquatics.MobTypeData)
        {
            i = ((EntityTMAquatics.MobTypeData)livingdata).typeData;
        }
        else
        {
            ientitylivingdata = new EntityTMAquatics.MobTypeData(i);
        }

        this.setMobType(i);
        return ientitylivingdata;
    }

    public static class MobTypeData implements IEntityLivingData
    {
        public int typeData;

        public MobTypeData(int type)
        {
            this.typeData = type;
        }
    }

    public boolean canBreatheUnderwater()
    {
        return true;
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return lootTable;
    }

    @Override
    public boolean getCanSpawnHere()
    {
        int willSpawn = this.spawnChance();

        return this.posY > 45.0D && this.posY < (double)this.world.getSeaLevel() && super.getCanSpawnHere() && willSpawn <= 10;
    }

    protected int spawnChance()
    {
        int i = TTMRand.getRandomInteger(TTMConfig.mobSpawnChance, 1);
        return i;
    }

    public double getHealthLevel() {
        return 0;
    }

    public int getMobType()
    {
        return ((Integer)this.dataManager.get(SKIN_TYPE)).intValue();
    }

    public void setMobType(int mobTypeId){
        this.dataManager.set(SKIN_TYPE, Integer.valueOf(mobTypeId));
    }

    private int getRandomMobType()
    {
        int i = TTMRand.getRandomInteger(rndMax, rndMin);
        return i;
    }

    public void setHostileWater(boolean hostileWater) {
        this.hostileWater = hostileWater;
    }

    public void setLootTable(ResourceLocation lootTable) {
        this.lootTable = lootTable;
    }

    public void setRndMinMax(int rndMin, int rndMax) {
        this.rndMin = rndMin;
        this.rndMax = rndMax;
    }
}