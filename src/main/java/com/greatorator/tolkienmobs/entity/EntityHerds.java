package com.greatorator.tolkienmobs.entity;

import com.greatorator.tolkienmobs.entity.entityai.IModEntity;
import com.greatorator.tolkienmobs.init.SoundInit;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Borrowed from Jabelar https://github.com/jabelar */
public class EntityHerds extends EntityAnimal implements IModEntity
{

    protected static final DataParameter<Float> SCALE_FACTOR = EntityDataManager.<Float>createKey(EntityHerds.class, DataSerializers.FLOAT);
    protected static final DataParameter<Integer> REARING_COUNTER = EntityDataManager.<Integer>createKey(EntityHerds.class, DataSerializers.VARINT);

    protected boolean isHitWithoutResistance = false ;

    public EntityHerds(World par1World)
    {
        super(par1World);

        setSize(2.2F, 2.7F);
    }

    @Override
    public void entityInit()
    {
        super.entityInit();
        dataManager.register(SCALE_FACTOR, 1.0F);
        dataManager.register(REARING_COUNTER, 0);
    }

    @Override
    public void initEntityAI()
    {
        clearAITasks();
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(2, new EntityAILeapAtTarget(this, 0.4F));
        tasks.addTask(3, new EntityAIAttackMelee(this, 1.0D, true));
        tasks.addTask(5, new EntityAIMate(this, 1.0D));
        tasks.addTask(6, new EntityAITempt(this, 1.25D, Items.WHEAT, false));
        tasks.addTask(8, new EntityAIWander(this, 1.0D));
        tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        tasks.addTask(10, new EntityAILookIdle(this));
        targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
    }

    @Override
    public void clearAITasks()
    {
        tasks.taskEntries.clear();
        targetTasks.taskEntries.clear();
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.8D);
        getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundInit.soundAmbientMumakil;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource parDamageSource)
    {
        return SoundInit.soundHurtMumakil;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundInit.soundHurtMumakil;
    }

    @Override
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (itemstack.getItem() == Items.BUCKET && !player.capabilities.isCreativeMode && !this.isChild())
        {
            player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
            itemstack.shrink(1);

            if (itemstack.isEmpty())
            {
                player.setHeldItem(hand, new ItemStack(Items.MILK_BUCKET));
            }
            else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.MILK_BUCKET)))
            {
                player.dropItem(new ItemStack(Items.MILK_BUCKET), false);
            }

            return true;
        }
        else
        {
            return super.processInteract(player, hand);
        }
    }

    @Override
    public EntityHerds createChild(EntityAgeable par1EntityAgeable)
    {
        return null;
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float parDamageAmount)
    {
        return super.attackEntityFrom(par1DamageSource, parDamageAmount);
    }

    @SideOnly(Side.CLIENT)
    protected <T> boolean canRenderName(T entity)
    {
        return false;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), ((int)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));

        if (flag)
        {
            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }

    @Override
    public void setScaleFactor(float parScaleFactor)
    {
        dataManager.set(SCALE_FACTOR, Math.abs(parScaleFactor));
    }

    @Override
    public float getScaleFactor()
    {
        return dataManager.get(SCALE_FACTOR);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setFloat("scaleFactor", getScaleFactor());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        setScaleFactor(compound.getFloat("scaleFactor"));
    }
}