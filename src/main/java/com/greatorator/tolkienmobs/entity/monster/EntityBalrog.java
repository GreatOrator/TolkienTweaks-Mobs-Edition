package com.greatorator.tolkienmobs.entity.monster;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.ammo.EntityAmmo;
import com.greatorator.tolkienmobs.entity.entityai.EntityAITTMAttack;
import com.greatorator.tolkienmobs.entity.passive.EntityHobbit;
import com.greatorator.tolkienmobs.init.SoundInit;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EntityBalrog extends EntityMob implements IRangedAttackMob {
    public static final ResourceLocation LOOT = new ResourceLocation(TolkienMobs.MODID, "entities/balrog");

    private static final DataParameter<Boolean> SWINGING_ARMS = EntityDataManager.<Boolean>createKey(EntityBalrog.class, DataSerializers.BOOLEAN);

    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);

    private final EntityAITTMAttack aiAttackOnCollide = new EntityAITTMAttack(this, 1.2D, false)
    {
        public void resetTask()
        {
            super.resetTask();
            EntityBalrog.this.setSwingingArms(false);
        }

        public void startExecuting()
        {
            super.startExecuting();
            EntityBalrog.this.setSwingingArms(true);
        }
    };

    public EntityBalrog(World worldIn) {
        super(worldIn);
        this.setSize(2.3F, 7.0F);
        this.isImmuneToFire = true;
        this.experienceValue = 150;
    }

    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackRanged(this, 1.25D, 20, 10.0F));
        this.tasks.addTask(2, new EntityAITTMAttack(this, 1.0D, false));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.applyEntityAI();
    }

    private void applyEntityAI() {
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[]{EntityBalrog.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityHobbit.class, true));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(SWINGING_ARMS, Boolean.valueOf(true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        // Here we set various attributes for our mob. Like maximum health, armor, speed, ...
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(12.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(300.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6000000238418579D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);
    }

    public void addTrackingPlayer(EntityPlayerMP player)
    {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    public void removeTrackingPlayer(EntityPlayerMP player)
    {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        super.setEquipmentBasedOnDifficulty(difficulty);
    }

    protected void setEnchantmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        IEntityLivingData ientitylivingdata = super.onInitialSpawn(difficulty, livingdata);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
        this.setEquipmentBasedOnDifficulty(difficulty);
        this.setCombatTask();
        return ientitylivingdata;
    }

    protected void updateAITasks()
    {
        super.updateAITasks();
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }

    public void setCombatTask()
    {
        if (this.world != null && !this.world.isRemote)
        {
            this.tasks.removeTask(this.aiAttackOnCollide);
            //   this.tasks.removeTask(this.aiArrowAttack);
            ItemStack itemstack = this.getHeldItemMainhand();

            if (itemstack.getItem() == TTMFeatures.SWORD_MORGULIRON)
            {
                this.tasks.addTask(4, this.aiAttackOnCollide);
            }
            else
            {
                int i = 20;

                if (this.world.getDifficulty() != EnumDifficulty.HARD)
                {
                    i = 40;
                }

                //   this.aiArrowAttack.setAttackCooldown(i);
                //   this.tasks.addTask(4, this.aiArrowAttack);
            }
        }
    }

    public boolean isNonBoss()
    {
        return false;
    }

    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (!super.attackEntityAsMob(entityIn))
        {
            return false;
        }
        else
        {
            if (entityIn instanceof EntityLivingBase)
            {
                ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 200));
            }

            return true;
        }
    }

    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        if (this.hasCustomName())
        {
            this.bossInfo.setName(this.getDisplayName());
        }
    }

    public void setCustomNameTag(String name)
    {
        super.setCustomNameTag(name);
        this.bossInfo.setName(this.getDisplayName());
    }

    public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack)
    {
        super.setItemStackToSlot(slotIn, stack);

        if (!this.world.isRemote && slotIn == EntityEquipmentSlot.MAINHAND)
        {
            this.setCombatTask();
        }
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundInit.soundIdleBalrog;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundInit.soundHurtBalrog;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundInit.soundDeathBalrog;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundInit.soundStepBalrog, 0.25F, 1.0F);
    }

    @SideOnly(Side.CLIENT)
    public boolean isSwingingArms()
    {
        return ((Boolean)this.dataManager.get(SWINGING_ARMS)).booleanValue();
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        EntityAmmo entityboulder = new EntityAmmo(this.world, this);
        double d0 = target.posY + (double)target.getEyeHeight() - 1.100000023841858D;
        double d1 = target.posX - this.posX;
        double d2 = d0 - entityboulder.posY;
        double d3 = target.posZ - this.posZ;
        float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
        entityboulder.shoot(d1, d2 + (double)f, d3, 1.6F, 12.0F);
        this.playSound(SoundInit.soundBoulderShoot, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(entityboulder);

    }

    public void setSwingingArms(boolean swingingArms)
    {
        this.dataManager.set(SWINGING_ARMS, Boolean.valueOf(swingingArms));
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LOOT;
    }

    @Override
    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }
}
