//package com.greatorator.tolkienmobs.entity.boss;
//
//import com.greatorator.tolkienmobs.entity.EntityTMBirds;
//import com.greatorator.tolkienmobs.entity.entityai.AIStates;
//import com.greatorator.tolkienmobs.init.LootInit;
//import com.greatorator.tolkienmobs.init.SoundInit;
//import net.minecraft.entity.SharedMonsterAttributes;
//import net.minecraft.entity.player.ServerPlayerEntity;
//import net.minecraft.item.EnumDyeColor;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.SoundEvent;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.BossInfo;
//import net.minecraft.world.BossInfoServer;
//import net.minecraft.world.World;
//
//import javax.annotation.Nullable;
//import java.util.UUID;
//
//public class EntityTMGwaihir extends EntityTMBirds {
//    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.GREEN, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);
//
//    public EntityTMGwaihir(World parWorld)
//    {
//        super(parWorld);
//        this.setSize(1.5F, 1.8F);
//    }
//
//    @Override
//    protected void applyEntityAttributes()
//    {
//        super.applyEntityAttributes();
//        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
//        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(150.0D);
//        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(12.0D);
//        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(12.0D);
//    }
//
//    @Override
//    protected void updateAITasks()
//    {
//        super.updateAITasks();
//
//        aiProcessState.updateAITick();
//        aiUpdateState.updateAIState();
//
//        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
//
//    }
//
//    @Override
//    public void writeEntityToNBT(NBTTagCompound compound)
//    {
//        super.writeEntityToNBT(compound);
//        compound.setFloat("scaleFactor", getScaleFactor());
//        compound.setInteger("state", getState());
//        compound.setBoolean("soaringClockwise", getSoarClockwise());
//        compound.setFloat("soarHeight", getSoarHeight());
//        compound.setDouble("anchorX", getAnchor().getX());
//        compound.setDouble("anchorY", getAnchor().getY());
//        compound.setDouble("anchorZ", getAnchor().getZ());
//        if (this.getOwnerId() == null)
//        {
//            compound.setString("OwnerUUID", "");
//        }
//        else
//        {
//            compound.setString("OwnerUUID", this.getOwnerId().toString());
//        }
//        compound.setInteger("legBandColor", getLegBandColor().getDyeDamage());
//    }
//
//    @Override
//    public void readEntityFromNBT(NBTTagCompound compound)
//    {
//        super.readEntityFromNBT(compound);
//        setScaleFactor(compound.getFloat("scaleFactor"));
//        setState(compound.getInteger("state"));
//        setSoarClockwise(compound.getBoolean("soaringClockwise"));
//        setSoarHeight(compound.getFloat("soarHeight"));
//        setAnchor(new BlockPos(
//                compound.getDouble("anchorX"),
//                compound.getDouble("anchorY"),
//                compound.getDouble("anchorZ")
//        ));
//        String s = compound.getString("OwnerUUID");
//        if (!s.isEmpty())
//        {
//            try
//            {
//                setOwnerId(UUID.fromString(s));
//            }
//            catch (Throwable var4)
//            {
//                setOwnerId(null);
//            }
//        }
//        else
//        {
//            setOwnerId(null);
//        }
//
//        if (this.hasCustomName())
//        {
//            this.bossInfo.setName(this.getDisplayName());
//        }
//        setLegBandColor(EnumDyeColor.byDyeDamage(compound.getInteger("legBandColor")));
//    }
//
//    public void addTrackingPlayer(ServerPlayerEntity player)
//    {
//        super.addTrackingPlayer(player);
//        this.bossInfo.addPlayer(player);
//    }
//
//    public void removeTrackingPlayer(ServerPlayerEntity player)
//    {
//        super.removeTrackingPlayer(player);
//        this.bossInfo.removePlayer(player);
//    }
//
//    public void setCustomNameTag(String name)
//    {
//        super.setCustomNameTag(name);
//        this.bossInfo.setName(this.getDisplayName());
//    }
//
//    @Override
//    @Nullable
//    protected ResourceLocation getLootTable() {
//        return LootInit.GWAIHIR;
//    }
//
//    @Override
//    protected SoundEvent getAmbientSound()
//    {
//        if (getState() == AIStates.STATE_TAKING_OFF || getState() == AIStates.STATE_TRAVELLING)
//        {
//            return SoundInit.soundFlappingTMGreatEagle;
//        }
//        else
//        {
//            return SoundInit.soundCallTMGreatEagle;
//        }
//    }
//
//    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
//    {
//        return SoundInit.soundHurtTMGreatEagle;
//    }
//
//    protected SoundEvent getDeathSound()
//    {
//        return SoundInit.soundDeathTMGreatEagle;
//    }
//}