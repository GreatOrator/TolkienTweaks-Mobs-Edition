package com.greatorator.tolkienmobs.entity.hostile;

import com.greatorator.tolkienmobs.entity.EntityTMHostiles;
import com.greatorator.tolkienmobs.entity.entityai.EntityAITTMAttack;
import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.init.SoundInit;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.UUID;

public class EntityTMMimicChest extends EntityTMHostiles {
    private static final UUID ATTACK_SPEED_BOOST_MODIFIER_UUID = UUID.fromString("763926b2-45ba-11e9-b210-d663bd873d93");
    private static final AttributeModifier ATTACK_SPEED_BOOST_MODIFIER = (new AttributeModifier(ATTACK_SPEED_BOOST_MODIFIER_UUID, "Attacking speed boost", 0.05D, 0)).setSaved(false);
    /** Above zero if this Mimic is Angry. */
    private int angerLevel;
    /** A random delay until this Mimic next makes a sound. */
    private int randomSoundDelay;
    private UUID angerTargetUUID;

    public EntityTMMimicChest(World world)
    {
        super(world);

        this.setSize(1.0F, 1.7F);
        this.setLootTable(LootInit.MIMICCHEST);
    }

    public void setRevengeTarget(@Nullable EntityLivingBase livingBase)
    {
        super.setRevengeTarget(livingBase);

        if (livingBase != null)
        {
            this.angerTargetUUID = livingBase.getUniqueID();
        }
    }

    @Override
    protected void initEntityAI()
    {
        this.applyEntityAI();
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
    }

    protected void applyEntityAI()
    {
        this.targetTasks.addTask(1, new EntityTMMimicChest.AIHurtByAggressor(this));
        this.targetTasks.addTask(2, new EntityTMMimicChest.AITargetAggressor(this));
    }

    public void updateAITasks()
    {
        IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);

        if (this.isAngry())
        {
            --this.angerLevel;
        }
        else if (iattributeinstance.hasModifier(ATTACK_SPEED_BOOST_MODIFIER))
        {
            iattributeinstance.removeModifier(ATTACK_SPEED_BOOST_MODIFIER);
        }

        if (this.randomSoundDelay > 0 && --this.randomSoundDelay == 0)
        {
            this.playSound(SoundInit.soundAngryMimic, this.getSoundVolume() * 2.0F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
        }

        if (this.angerLevel > 0 && this.angerTargetUUID != null && this.getRevengeTarget() == null)
        {
            EntityPlayer entityplayer = this.world.getPlayerEntityByUUID(this.angerTargetUUID);
            this.setRevengeTarget(entityplayer);
            this.attackingPlayer = entityplayer;
            this.recentlyHit = this.getRevengeTimer();
            this.tasks.addTask(2, new EntityAITTMAttack(this, 1.2D, false));
            this.tasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        }
        super.updateAITasks();
    }

    @Override
    public double getAttackDamage() {
        return 3.0D;
    }

    @Override
    public double getArmorStrength() {
        return 0;
    }

    @Override
    public double getHealthLevel() {
        return 40.0D;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
    }

    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage)
    {
        if (damageSource.getImmediateSource() instanceof EntityPlayer)
        {
            Entity entity = damageSource.getTrueSource();

            if (entity instanceof EntityPlayer)
            {
                this.becomeAngryAt(entity);
            }

            EntityPlayer player = (EntityPlayer) damageSource.getImmediateSource();

            if (player.getActiveItemStack().getItem() instanceof ItemAxe)
            {
                damage *= 1.25F;
            }
        }

        return super.attackEntityFrom(damageSource, damage);
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        boolean flag = itemstack.getItem() == Items.NAME_TAG;

        if (flag)
        {
            itemstack.interactWithEntity(player, this, hand);
            return true;
        }
        else if (!isAngry())
        {
            this.becomeAngryAt(player);
        }
            return super.processInteract(player, hand);
    }

    private void becomeAngryAt(Entity p_70835_1_)
    {
        this.angerLevel = 400 + this.rand.nextInt(400);

        if (p_70835_1_ instanceof EntityLivingBase)
        {
            this.setRevengeTarget((EntityLivingBase)p_70835_1_);
        }
    }

    public boolean isAngry()
    {
        return this.angerLevel > 0;
    }

    static class AIHurtByAggressor extends EntityAIHurtByTarget
    {
        public AIHurtByAggressor(EntityTMMimicChest p_i45828_1_)
        {
            super(p_i45828_1_, true);
        }

        protected void setEntityAttackTarget(EntityCreature creatureIn, EntityLivingBase entityLivingBaseIn)
        {
            super.setEntityAttackTarget(creatureIn, entityLivingBaseIn);

            if (creatureIn instanceof EntityTMMimicChest)
            {
                ((EntityTMMimicChest)creatureIn).becomeAngryAt(entityLivingBaseIn);
            }
        }
    }

    static class AITargetAggressor extends EntityAINearestAttackableTarget<EntityPlayer>
    {
        public AITargetAggressor(EntityTMMimicChest p_i45829_1_)
        {
            super(p_i45829_1_, EntityPlayer.class, true);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            return ((EntityTMMimicChest)this.taskOwner).isAngry() && super.shouldExecute();
        }
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundInit.soundStepMimic, 0.25F, 1.0F);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source)
    {
        return SoundEvents.BLOCK_WOOD_HIT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.BLOCK_CHEST_CLOSE;
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setShort("Anger", (short)this.angerLevel);

        if (this.angerTargetUUID != null)
        {
            compound.setString("HurtBy", this.angerTargetUUID.toString());
        }
        else
        {
            compound.setString("HurtBy", "");
        }
    }

    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.angerLevel = compound.getShort("Anger");
        String s = compound.getString("HurtBy");

        if (!s.isEmpty())
        {
            this.angerTargetUUID = UUID.fromString(s);
            EntityPlayer entityplayer = this.world.getPlayerEntityByUUID(this.angerTargetUUID);
            this.setRevengeTarget(entityplayer);

            if (entityplayer != null)
            {
                this.attackingPlayer = entityplayer;
                this.recentlyHit = this.getRevengeTimer();
            }
        }
    }
}