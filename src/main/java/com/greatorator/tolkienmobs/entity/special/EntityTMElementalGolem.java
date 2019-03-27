package com.greatorator.tolkienmobs.entity.special;

import com.greatorator.tolkienmobs.entity.EntityTMHostiles;
import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.init.SoundInit;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityTMElementalGolem extends EntityTMHostiles {
    private long nextAbilityUse = 0L;
    private final static long coolDown = 15000L;
    private EntityLivingBase mob;

    private static final DataParameter<Integer> ELEMENT_TYPE = EntityDataManager.<Integer>createKey(EntityTMElementalGolem.class, DataSerializers.VARINT);

    public EntityTMElementalGolem(World worldIn) {
        super(worldIn);
        this.setSize(1.8F, 3.1F);
        this.setLootTable(LootInit.MORC);
        this.setMob(this);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(ELEMENT_TYPE, Integer.valueOf(0));
    }

    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage) {
        EntityPlayer player = (EntityPlayer)damageSource.getTrueSource();

        if (damageSource.getImmediateSource() instanceof EntityPlayer)
        {
            if(getElementType() == 1) {
                long time = System.currentTimeMillis();
                if (time > nextAbilityUse && damageSource.getTrueSource() != null && !(damageSource instanceof EntityDamageSourceIndirect)) {
                    nextAbilityUse = time + coolDown;

                    if (rand.nextInt(10) == 0){
                        assert player != null;
                        mob.world.addWeatherEffect(new EntityLightningBolt(mob.world, player.posX, player.posY-1, player.posZ, false));
                    }
                    else {
                        assert player != null;
                        player.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 45, 0));
                    }
                }
            }
            else if(getElementType() == 2) {
                long time = System.currentTimeMillis();
                if (time > nextAbilityUse && damageSource.getTrueSource() != null && !(damageSource instanceof EntityDamageSourceIndirect)) {
                    nextAbilityUse = time + coolDown;

                    if (rand.nextInt(10) == 0){
                        assert player != null;
                        player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 45, 0));
                    }
                    else {
                        assert player != null;
                        player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 45, 0));
                    }
                }
            }
            else if(getElementType() == 3) {
                long time = System.currentTimeMillis();
                if (time > nextAbilityUse && damageSource.getTrueSource() != null && !(damageSource instanceof EntityDamageSourceIndirect)) {
                    nextAbilityUse = time + coolDown;

                    if (rand.nextInt(10) == 0){
                        damageSource.getTrueSource().setFire(3);
                    }
                    else {
                        assert player != null;
                        player.addPotionEffect(new PotionEffect(MobEffects.WITHER, 45, 0));
                    }
                }
            }
            else if(getElementType() == 4) {
                long time = System.currentTimeMillis();
                if (time > nextAbilityUse && damageSource.getTrueSource() != null && !(damageSource instanceof EntityDamageSourceIndirect)) {
                    nextAbilityUse = time + coolDown;

                    if (rand.nextInt(10) == 0){
                        assert player != null;
                        player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 45, 0));
                    }
                    else {
                        assert player != null;
                        player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 45, 0));
                    }
                }
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
        if (elementTypeId == 1)
        {
            this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(15.0D);
            this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));

            if (!this.hasCustomName())
            {
                this.setCustomNameTag(I18n.translateToLocal("entity.elementalgolem1.name"));
            }
        }
        else if (elementTypeId == 2)
        {
            this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(15.0D);
            this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));

            if (!this.hasCustomName())
            {
                this.setCustomNameTag(I18n.translateToLocal("entity.elementalgolem2.name"));
            }
        }
        else if (elementTypeId == 3)
        {
            this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(15.0D);
            this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));

            if (!this.hasCustomName())
            {
                this.setCustomNameTag(I18n.translateToLocal("entity.elementalgolem3.name"));
            }
        }
        else if (elementTypeId == 4)
        {
            this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(15.0D);
            this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));

            if (!this.hasCustomName())
            {
                this.setCustomNameTag(I18n.translateToLocal("entity.elementalgolem4.name"));
            }
        }
        else
        {
            this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(15.0D);
            this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));

            if (!this.hasCustomName())
            {
                this.setCustomNameTag(I18n.translateToLocal("entity.elementalgolem5.name"));
            }
        }

        this.dataManager.set(ELEMENT_TYPE, Integer.valueOf(elementTypeId));
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        IEntityLivingData ientitylivingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setEquipmentBasedOnDifficulty(difficulty);
        int i = this.getRandomElementType();

        if (ientitylivingdata instanceof EntityTMElementalGolem.ElementTypeData)
        {
            i = ((EntityTMElementalGolem.ElementTypeData)livingdata).typeData;
        }
        else
        {
            ientitylivingdata = new EntityTMElementalGolem.ElementTypeData(i);
        }

        this.setElementType(i);
        this.setCombatTask();
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
        int i = TTMRand.getRandomInteger(6, 1);
        return i;
    }

    @Override
    public double getAttackDamage() {
        return 15;
    }

    @Override
    public double getArmorStrength() {
        return 20;
    }

    @Override
    public double getHealthLevel() {
        return 100;
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
}
