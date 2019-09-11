package com.greatorator.tolkienmobs.entity.special;

import com.google.common.base.Predicate;
import com.greatorator.tolkienmobs.entity.EntityTMVillagers;
import com.greatorator.tolkienmobs.entity.entityai.EntityAIMithrilDefendVillage;
import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.init.PotionInit;
import com.greatorator.tolkienmobs.init.SoundInit;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.village.Village;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class EntityTMMithrilGolem extends EntityIronGolem {
    /** deincrements, and a distance-to-home check is done at 0 */
    private int homeCheckTimer;
    @Nullable
    Village village;
    private long nextAbilityUse = 0L;
    private final static long coolDown = 15000L;
    private EntityLivingBase mob;

    private static final DataParameter<Integer> ELEMENT_TYPE = EntityDataManager.<Integer>createKey(EntityTMMithrilGolem.class, DataSerializers.VARINT);

    public EntityTMMithrilGolem(World worldIn) {
        super(worldIn);
        this.setSize(1.8F, 3.1F);
        this.setMob(this);
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
        this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIMithrilDefendVillage(this));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLiving.class, 10, false, true, new Predicate<EntityLiving>()
        {
            public boolean apply(@Nullable EntityLiving p_apply_1_)
            {
                return p_apply_1_ != null && IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityCreeper);
            }
        }));
    }

    protected void updateAITasks()
    {
        if (--this.homeCheckTimer <= 0)
        {
            this.homeCheckTimer = 70 + this.rand.nextInt(50);
            this.village = this.world.getVillageCollection().getNearestVillage(new BlockPos(this), 32);

            if (this.village == null)
            {
                this.detachHome();
            }
            else
            {
                BlockPos blockpos = this.village.getCenter();
                this.setHomePosAndDistance(blockpos, (int)((float)this.village.getVillageRadius() * 0.6F));
            }
        }

        super.updateAITasks();
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(30.0D);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(ELEMENT_TYPE, Integer.valueOf(0));
    }

    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage) {
        EntityLiving player = (EntityLiving)damageSource.getTrueSource();

            long time = System.currentTimeMillis();
            if (time > nextAbilityUse && damageSource.getTrueSource() != null && !(damageSource instanceof EntityDamageSourceIndirect)) {
                nextAbilityUse = time + coolDown;

                if (rand.nextInt(10) == 0) {
                    assert player != null;
                    player.addPotionEffect(new PotionEffect(PotionInit.ELEMENTAL_FLYING, 40, 3));
                }
                else {
                    assert player != null;
                    player.addPotionEffect(new PotionEffect(PotionInit.ELEMENTAL_LIGHTNING, 45, 1));
                }
            }
        return super.attackEntityFrom(damageSource, damage);
    }

    public int getElementType()
    {
        return ((Integer)this.dataManager.get(ELEMENT_TYPE)).intValue();
    }

    public void setElementType(int elementTypeId)
    {
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(15.0D);
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));

        if (!this.hasCustomName())
        {
            this.setCustomNameTag(I18n.translateToLocal("entity.elementalgolem6.name"));
        }

        this.dataManager.set(ELEMENT_TYPE, Integer.valueOf(elementTypeId));
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        IEntityLivingData ientitylivingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setEquipmentBasedOnDifficulty(difficulty);
        int i = this.getRandomElementType();

        if (ientitylivingdata instanceof EntityTMMithrilGolem.ElementTypeData)
        {
            i = ((EntityTMMithrilGolem.ElementTypeData)livingdata).typeData;
        }
        else
        {
            ientitylivingdata = new EntityTMMithrilGolem.ElementTypeData(i);
        }

        this.setElementType(i);
        return ientitylivingdata;
    }

    public static class ElementTypeData implements IEntityLivingData
    {
        public int typeData;

        public ElementTypeData(int type)
        {
            this.typeData = type;
        }
    }

    private int getRandomElementType()
    {
        int i = 6;
        return i;
    }

    public Village getVillage()
    {
        return this.village;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundInit.soundDeathGolem;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundInit.soundStepTroll, 0.25F, 1.0F);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("ElementType", this.getElementType());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setElementType(compound.getInteger("ElementType"));
    }

    public void setMob(EntityLivingBase mob) {
        this.mob = mob;
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LootInit.GOLEM_STONE_MITHRIL;
    }

    @Override
    public boolean getCanSpawnHere() {
        int i = MathHelper.floor(this.posX);
        int j = MathHelper.floor(this.getEntityBoundingBox().minY);
        int k = MathHelper.floor(this.posZ);

        BlockPos blockpos = new BlockPos(i, j, k);

        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.world.getLight(blockpos) < 8 && super.getCanSpawnHere();
    }

    public boolean checkEntityCount() {
        boolean monsterSpawn = true;

        List entities = getEntityWorld().getEntitiesWithinAABB(EntityTMVillagers.class,getEntityBoundingBox().expand(32,32,32));
        List entitiesKing = getEntityWorld().getEntitiesWithinAABB(EntityTMMithrilGolem.class,getEntityBoundingBox().expand(32,32,32));

        if (entities.size() > 10 && entitiesKing.size() <= (entities.size() / 10)) {
            monsterSpawn = false;
        }
        return monsterSpawn;
    }

}