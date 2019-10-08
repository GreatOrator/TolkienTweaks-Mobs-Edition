package com.greatorator.tolkienmobs.entity.ambient;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.init.SoundInit;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import com.greatorator.tolkienmobs.utils.TTMRand;
import com.greatorator.tolkienmobs.world.biomes.BiomeHaradwaith;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBeetroot;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.*;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EntityTMToad extends EntityAnimal {
    private static final DataParameter<Integer> TOAD_TYPE = EntityDataManager.<Integer>createKey(EntityTMToad.class, DataSerializers.VARINT);
    private int jumpTicks;
    private int jumpDuration;
    private boolean wasOnGround;
    private int currentMoveTypeDuration;
    private int insectTicks;

    public EntityTMToad(World worldIn)
    {
        super(worldIn);
        this.setSize(0.3F, 0.3F);
        this.jumpHelper = new EntityTMToad.ToaddleJumpHelper(this);
        this.moveHelper = new EntityTMToad.ToaddleMoveHelper(this);
        this.setMovementSpeed(0.0D);
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityTMToad.AIPanic(this, 2.2D));
        this.tasks.addTask(2, new EntityAIMate(this, 0.8D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.0D, TTMFeatures.INSECT, false));
        this.tasks.addTask(3, new EntityAITempt(this, 1.0D, TTMFeatures.GOLDEN_INSECT, false));
        this.tasks.addTask(4, new EntityTMToad.AIAvoidEntity(this, EntityPlayer.class, 8.0F, 2.2D, 2.2D));
        this.tasks.addTask(4, new EntityTMToad.AIAvoidEntity(this, EntityWolf.class, 10.0F, 2.2D, 2.2D));
        this.tasks.addTask(4, new EntityTMToad.AIAvoidEntity(this, EntityMob.class, 4.0F, 2.2D, 2.2D));
        this.tasks.addTask(5, new EntityTMToad.AIRaidFarm(this));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
    }

    protected float getJumpUpwardsMotion()
    {
        if (!this.collidedHorizontally && (!this.moveHelper.isUpdating() || this.moveHelper.getY() <= this.posY + 0.5D))
        {
            Path path = this.navigator.getPath();

            if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength())
            {
                Vec3d vec3d = path.getPosition(this);

                if (vec3d.y > this.posY + 0.5D)
                {
                    return 0.5F;
                }
            }

            return this.moveHelper.getSpeed() <= 0.6D ? 0.2F : 0.3F;
        }
        else
        {
            return 0.5F;
        }
    }

    /**
     * Causes this entity to do an upwards motion (jumping).
     */
    protected void jump()
    {
        super.jump();
        double d0 = this.moveHelper.getSpeed();

        if (d0 > 0.0D)
        {
            double d1 = this.motionX * this.motionX + this.motionZ * this.motionZ;

            if (d1 < 0.010000000000000002D)
            {
                this.moveRelative(0.0F, 0.0F, 1.0F, 0.1F);
            }
        }

        if (!this.world.isRemote)
        {
            this.world.setEntityState(this, (byte)1);
        }
    }

    @SideOnly(Side.CLIENT)
    public float setJumpCompletion(float p_175521_1_)
    {
        return this.jumpDuration == 0 ? 0.0F : ((float)this.jumpTicks + p_175521_1_) / (float)this.jumpDuration;
    }

    public void setMovementSpeed(double newSpeed)
    {
        this.getNavigator().setSpeed(newSpeed);
        this.moveHelper.setMoveTo(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ(), newSpeed);
    }

    public void setJumping(boolean jumping)
    {
        super.setJumping(jumping);

        if (jumping)
        {
            this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
        }
    }

    public void startJumping()
    {
        this.setJumping(true);
        this.jumpDuration = 10;
        this.jumpTicks = 0;
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(TOAD_TYPE, Integer.valueOf(0));
    }

    public void updateAITasks()
    {
        if (this.currentMoveTypeDuration > 0)
        {
            --this.currentMoveTypeDuration;
        }

        if (this.insectTicks > 0)
        {
            this.insectTicks -= this.rand.nextInt(3);

            if (this.insectTicks < 0)
            {
                this.insectTicks = 0;
            }
        }

        if (this.onGround)
        {
            if (!this.wasOnGround)
            {
                this.setJumping(false);
                this.checkLandingDelay();
            }

            if (this.getToaddleType() == 99 && this.currentMoveTypeDuration == 0)
            {
                EntityLivingBase entitylivingbase = this.getAttackTarget();

                if (entitylivingbase != null && this.getDistanceSq(entitylivingbase) < 16.0D)
                {
                    this.calculateRotationYaw(entitylivingbase.posX, entitylivingbase.posZ);
                    this.moveHelper.setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, this.moveHelper.getSpeed());
                    this.startJumping();
                    this.wasOnGround = true;
                }
            }

            EntityTMToad.ToaddleJumpHelper entitytoad$toadjumphelper = (EntityTMToad.ToaddleJumpHelper)this.jumpHelper;

            if (!entitytoad$toadjumphelper.getIsJumping())
            {
                if (this.moveHelper.isUpdating() && this.currentMoveTypeDuration == 0)
                {
                    Path path = this.navigator.getPath();
                    Vec3d vec3d = new Vec3d(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ());

                    if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength())
                    {
                        vec3d = path.getPosition(this);
                    }

                    this.calculateRotationYaw(vec3d.x, vec3d.z);
                    this.startJumping();
                }
            }
            else if (!entitytoad$toadjumphelper.canJump())
            {
                this.enableJumpControl();
            }
        }

        this.wasOnGround = this.onGround;
    }

    /**
     * Attempts to create sprinting particles if the entity is sprinting and not in water.
     */
    public void spawnRunningParticles()
    {
    }

    private void calculateRotationYaw(double x, double z)
    {
        this.rotationYaw = (float)(MathHelper.atan2(z - this.posZ, x - this.posX) * (180D / Math.PI)) - 90.0F;
    }

    private void enableJumpControl()
    {
        ((EntityTMToad.ToaddleJumpHelper)this.jumpHelper).setCanJump(true);
    }

    private void disableJumpControl()
    {
        ((EntityTMToad.ToaddleJumpHelper)this.jumpHelper).setCanJump(false);
    }

    private void updateMoveTypeDuration()
    {
        if (this.moveHelper.getSpeed() < 2.2D)
        {
            this.currentMoveTypeDuration = 10;
        }
        else
        {
            this.currentMoveTypeDuration = 1;
        }
    }

    private void checkLandingDelay()
    {
        this.updateMoveTypeDuration();
        this.disableJumpControl();
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (this.jumpTicks != this.jumpDuration)
        {
            ++this.jumpTicks;
        }
        else if (this.jumpDuration != 0)
        {
            this.jumpTicks = 0;
            this.jumpDuration = 0;
            this.setJumping(false);
        }
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
    }

    public static void registerFixesToaddle(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityTMToad.class);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("ToaddleType", this.getToaddleType());
        compound.setInteger("MoreInsectTicks", this.insectTicks);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setToaddleType(compound.getInteger("ToaddleType"));
        this.insectTicks = compound.getInteger("MoreInsectTicks");
    }

    protected SoundEvent getJumpSound()
    {
        return SoundInit.soundStepToaddle;
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundInit.soundIdleToaddle;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundInit.soundHurtToaddle;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundInit.soundDeathToaddle;
    }

    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (this.getToaddleType() == 99)
        {
            this.playSound(SoundInit.soundAngryToaddle, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 8.0F);
        }
        else
        {
            return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
        }
    }

    public SoundCategory getSoundCategory()
    {
        return this.getToaddleType() == 99 ? SoundCategory.HOSTILE : SoundCategory.NEUTRAL;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        return this.isEntityInvulnerable(source) ? false : super.attackEntityFrom(source, amount);
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LootInit.TMFROG;
    }

    private boolean isToaddleBreedingItem(Item itemIn)
    {
        return itemIn == TTMFeatures.INSECT || itemIn == TTMFeatures.GOLDEN_INSECT;
    }

    public EntityTMToad createChild(EntityAgeable ageable)
    {
        EntityTMToad entitytoad = new EntityTMToad(this.world);
        int i = this.getRandomToaddleType();

        if (this.rand.nextInt(20) != 0)
        {
            if (ageable instanceof EntityTMToad && this.rand.nextBoolean())
            {
                i = ((EntityTMToad)ageable).getToaddleType();
            }
            else
            {
                i = this.getToaddleType();
            }
        }

        entitytoad.setToaddleType(i);
        return entitytoad;
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, insects or seeds depending on
     * the animal type)
     */
    public boolean isBreedingItem(ItemStack stack)
    {
        return this.isToaddleBreedingItem(stack.getItem());
    }

    public int getToaddleType()
    {
        return ((Integer)this.dataManager.get(TOAD_TYPE)).intValue();
    }

    public void setToaddleType(int toadTypeId)
    {
        if (toadTypeId == 99)
        {
            this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(8.0D);
            this.tasks.addTask(4, new EntityTMToad.AIEvilAttack(this));
            this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
            this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
            this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityWolf.class, true));

            if (!this.hasCustomName())
            {
                this.setCustomNameTag(I18n.translateToLocal("entity.MurderFrog.name"));
            }
        }

        this.dataManager.set(TOAD_TYPE, Integer.valueOf(toadTypeId));
    }

    /**
     * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
     * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
     */
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        int i = this.getRandomToaddleType();
        boolean flag = false;

        if (livingdata instanceof EntityTMToad.ToaddleTypeData)
        {
            i = ((EntityTMToad.ToaddleTypeData)livingdata).typeData;
            flag = true;
        }
        else
        {
            livingdata = new EntityTMToad.ToaddleTypeData(i);
        }

        this.setToaddleType(i);

        if (flag)
        {
            this.setGrowingAge(-24000);
        }

        return livingdata;
    }

    private int getRandomToaddleType()
    {
        Biome biome = this.world.getBiome(new BlockPos(this));
        int i = this.rand.nextInt(100);

        if (biome.isSnowyBiome())
        {
            return i < 80 ? 1 : 3;
        }
        else if (biome instanceof BiomeHaradwaith)
        {
            return 4;
        }
        else
        {
            return i < 50 ? 0 : (i < 90 ? 5 : 2);
        }
    }

    private boolean isInsectEaten()
    {
        return this.insectTicks == 0;
    }

    protected void createEatingParticles()
    {
        BlockBeetroot blockinsect = (BlockBeetroot)Blocks.BEETROOTS;
        IBlockState iblockstate = blockinsect.withAge(blockinsect.getMaxAge());
        this.world.spawnParticle(EnumParticleTypes.BLOCK_DUST, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, 0.0D, 0.0D, 0.0D, Block.getStateId(iblockstate));
        this.insectTicks = 40;
    }

    /**
     * Handler for {@link World#setEntityState}
     */
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 1)
        {
            this.createRunningParticles();
            this.jumpDuration = 10;
            this.jumpTicks = 0;
        }
        else
        {
            super.handleStatusUpdate(id);
        }
    }

    static class AIAvoidEntity<T extends Entity> extends EntityAIAvoidEntity<T>
    {
        private final EntityTMToad toad;

        public AIAvoidEntity(EntityTMToad toad, Class<T> p_i46403_2_, float p_i46403_3_, double p_i46403_4_, double p_i46403_6_)
        {
            super(toad, p_i46403_2_, p_i46403_3_, p_i46403_4_, p_i46403_6_);
            this.toad = toad;
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            return this.toad.getToaddleType() != 99 && super.shouldExecute();
        }
    }

    static class AIEvilAttack extends EntityAIAttackMelee
    {
        public AIEvilAttack(EntityTMToad toad)
        {
            super(toad, 1.4D, true);
        }

        protected double getAttackReachSqr(EntityLivingBase attackTarget)
        {
            return (double)(4.0F + attackTarget.width);
        }
    }

    static class AIPanic extends EntityAIPanic
    {
        private final EntityTMToad toad;

        public AIPanic(EntityTMToad toad, double speedIn)
        {
            super(toad, speedIn);
            this.toad = toad;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask()
        {
            super.updateTask();
            this.toad.setMovementSpeed(this.speed);
        }
    }

    static class AIRaidFarm extends EntityAIMoveToBlock
    {
        private final EntityTMToad toad;
        private boolean wantsToRaid;
        private boolean canRaid;

        public AIRaidFarm(EntityTMToad toadIn)
        {
            super(toadIn, 0.699999988079071D, 16);
            this.toad = toadIn;
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            if (this.runDelay <= 0)
            {
                if (!this.toad.world.getGameRules().getBoolean("mobGriefing"))
                {
                    return false;
                }

                this.canRaid = false;
                this.wantsToRaid = this.toad.isInsectEaten();
                this.wantsToRaid = true;
            }

            return super.shouldExecute();
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting()
        {
            return this.canRaid && super.shouldContinueExecuting();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask()
        {
            super.updateTask();
            this.toad.getLookHelper().setLookPosition((double)this.destinationBlock.getX() + 0.5D, (double)(this.destinationBlock.getY() + 1), (double)this.destinationBlock.getZ() + 0.5D, 10.0F, (float)this.toad.getVerticalFaceSpeed());
        }

        /**
         * Return true to set given position as destination
         */
        protected boolean shouldMoveTo(World worldIn, BlockPos pos)
        {
            Block block = worldIn.getBlockState(pos).getBlock();

            if (block == Blocks.FARMLAND && this.wantsToRaid && !this.canRaid)
            {
                pos = pos.up();
                IBlockState iblockstate = worldIn.getBlockState(pos);
                block = iblockstate.getBlock();

                if (block instanceof BlockBeetroot && ((BlockBeetroot)block).isMaxAge(iblockstate))
                {
                    this.canRaid = true;
                    return true;
                }
            }

            return false;
        }
    }

    public class ToaddleJumpHelper extends EntityJumpHelper
    {
        private final EntityTMToad toad;
        private boolean canJump;

        public ToaddleJumpHelper(EntityTMToad toad)
        {
            super(toad);
            this.toad = toad;
        }

        public boolean getIsJumping()
        {
            return this.isJumping;
        }

        public boolean canJump()
        {
            return this.canJump;
        }

        public void setCanJump(boolean canJumpIn)
        {
            this.canJump = canJumpIn;
        }

        /**
         * Called to actually make the entity jump if isJumping is true.
         */
        public void doJump()
        {
            if (this.isJumping)
            {
                this.toad.startJumping();
                this.isJumping = false;
            }
        }
    }

    static class ToaddleMoveHelper extends EntityMoveHelper
    {
        private final EntityTMToad toad;
        private double nextJumpSpeed;

        public ToaddleMoveHelper(EntityTMToad toad)
        {
            super(toad);
            this.toad = toad;
        }

        public void onUpdateMoveHelper()
        {
            if (this.toad.onGround && !this.toad.isJumping && !((EntityTMToad.ToaddleJumpHelper)this.toad.jumpHelper).getIsJumping())
            {
                this.toad.setMovementSpeed(0.0D);
            }
            else if (this.isUpdating())
            {
                this.toad.setMovementSpeed(this.nextJumpSpeed);
            }

            super.onUpdateMoveHelper();
        }

        /**
         * Sets the speed and location to move to
         */
        public void setMoveTo(double x, double y, double z, double speedIn)
        {
            if (this.toad.isInWater())
            {
                speedIn = 1.5D;
            }

            super.setMoveTo(x, y, z, speedIn);

            if (speedIn > 0.0D)
            {
                this.nextJumpSpeed = speedIn;
            }
        }
    }

    public static class ToaddleTypeData implements IEntityLivingData
    {
        public int typeData;

        public ToaddleTypeData(int type)
        {
            this.typeData = type;
        }
    }

    @Override
    public boolean getCanSpawnHere() {
        boolean nearWater = false;
        int i = (int) Math.floor(posX);
        int j = (int) Math.floor(posY);
        int k = (int) Math.floor(posZ);

        int willSpawn = this.spawnChance();

        for (int i1 = i - 16; i1 <= i + 16; i1++) {
            for (int j1 = j - 6; j1 <= j + 6; j1++) {
                for (int k1 = k - 16; k1 <= k + 16; k1++) {
                    BlockPos pos = new BlockPos(i1, j1, k1);
                    if (world.getBlockState(pos).getMaterial() == Material.WATER && this.world.canSeeSky(new BlockPos(this)) && this.posY > 36.0D) {
                        if (willSpawn == 10) {
                            nearWater = true;
                        }
                    }
                }
            }
        }
        return nearWater;
    }

    private int spawnChance()
    {
        int i = TTMRand.getRandomInteger(TTMConfig.mobSpawnChance, 1);
        return i;
    }
}