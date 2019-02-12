package com.greatorator.tolkienmobs.entity;

import com.greatorator.tolkienmobs.entity.entityai.EntityAITTMAttack;
import com.greatorator.tolkienmobs.handler.TTMRand;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EntityHostiles extends EntityMob {
    private int texture_index;

    private static final DataParameter<Boolean> SWINGING_ARMS = EntityDataManager.<Boolean>createKey(EntityHostiles.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.<Boolean>createKey(EntityHostiles.class, DataSerializers.BOOLEAN);
    private final EntityAITTMAttack aiAttackOnCollide = new EntityAITTMAttack(this, 1.2D, false)
    {
        public void resetTask()
        {
            super.resetTask();
            EntityHostiles.this.setSwingingArms(false);
        }

        public void startExecuting()
        {
            super.startExecuting();
            EntityHostiles.this.setSwingingArms(true);
        }
    };
    private Item weaponType;
    private Potion ttmEffect;
    private int ttmDuration;
    private int rndMax;
    private int rndMin;
    private boolean burnState;
    private boolean ttmAttack;

    public EntityHostiles(World worldIn) {
        super(worldIn);
    }

    public int getTextureIndex() {
        return this.texture_index;
    }

    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        super.setEquipmentBasedOnDifficulty(difficulty);
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(weaponType));
    }

    public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack)
    {
        super.setItemStackToSlot(slotIn, stack);

        if (!this.world.isRemote && slotIn == EntityEquipmentSlot.MAINHAND)
        {
            this.setCombatTask();
        }
    }

    public void setCombatTask()
    {
        if (this.world != null && !this.world.isRemote)
        {
            this.tasks.removeTask(this.aiAttackOnCollide);
            ItemStack itemstack = this.getHeldItemMainhand();

            if (itemstack.getItem() == weaponType)
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
            }
        }
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
                if(ttmDuration > 0) {
                    ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(ttmEffect, ttmDuration));
                }
            }
            return true;
        }
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(SWINGING_ARMS, Boolean.valueOf(true));
        if(ttmAttack) {
            this.dataManager.register(ATTACKING, Boolean.valueOf(false));
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean isSwingingArms()
    {
        return ((Boolean)this.dataManager.get(SWINGING_ARMS)).booleanValue();
    }

    public void setSwingingArms(boolean swingingArms)
    {
        this.dataManager.set(SWINGING_ARMS, Boolean.valueOf(swingingArms));
    }

    protected void setEnchantmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
    }

    public void onLivingUpdate()
    {
        if (this.world.isDaytime() && !this.world.isRemote && !this.isChild() && this.shouldBurnInDay())
        {
            float f = this.getBrightness();

            if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.canSeeSky(new BlockPos(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ)))
            {
                boolean flag = true;
                ItemStack itemstack = this.getItemStackFromSlot(EntityEquipmentSlot.HEAD);

                if (!itemstack.isEmpty())
                {
                    if (itemstack.isItemStackDamageable())
                    {
                        itemstack.setItemDamage(itemstack.getItemDamage() + this.rand.nextInt(2));

                        if (itemstack.getItemDamage() >= itemstack.getMaxDamage())
                        {
                            this.renderBrokenItemStack(itemstack);
                            this.setItemStackToSlot(EntityEquipmentSlot.HEAD, ItemStack.EMPTY);
                        }
                    }

                    flag = false;
                }

                if (flag)
                {
                    this.setFire(8);
                }
            }
        }

        super.onLivingUpdate();
    }

    protected boolean shouldBurnInDay()
    {
        return burnState;
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        IEntityLivingData ientitylivingdata = super.onInitialSpawn(difficulty, livingdata);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
        this.setEquipmentBasedOnDifficulty(difficulty);
        if (rndMin > 0){
            if (texture_index == 0) {
                texture_index = TTMRand.getRandomInteger(rndMax, rndMin);
            }
        }
        this.setCombatTask();
        return ientitylivingdata;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("texture_index", texture_index);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        texture_index = compound.getInteger("texture_index");
    }

    @Override
    protected boolean isValidLightLevel() {
        return true;
    }

    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeInt(this.texture_index);
    }

    public void readSpawnData(ByteBuf buffer) {
        this.texture_index = buffer.readInt();
    }

    public void setRndMinMax(int rndMin, int rndMax) {
        this.rndMin = rndMin;
        this.rndMax = rndMax;
    }

    public void setBurnState(boolean burnState) {
        this.burnState = burnState;
    }

    public void setWeaponType(Item weaponType) {
        this.weaponType = weaponType;
    }

    public void setTtmEffect(Potion ttmEffect) {
        this.ttmEffect = ttmEffect;
    }

    public void setTtmDuration(int ttmDuration) {
        this.ttmDuration = ttmDuration;
    }

    public void setTtmAttack(boolean ttmAttack) {
        this.ttmAttack = ttmAttack;
    }
}