//package com.greatorator.tolkienmobs.entity.special;
//
//import com.greatorator.tolkienmobs.entity.EntityTMHostiles;
//import com.greatorator.tolkienmobs.init.LootInit;
//import com.greatorator.tolkienmobs.init.PotionInit;
//import com.greatorator.tolkienmobs.init.SoundInit;
//import com.greatorator.tolkienmobs.init.TTMFeatures;
//import net.minecraft.block.Block;
//import net.minecraft.entity.EnumCreatureAttribute;
//import net.minecraft.entity.player.ServerPlayerEntity;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.SoundEvent;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.BossInfo;
//import net.minecraft.world.BossInfoServer;
//import net.minecraft.world.World;
//
//public class EntityTMNazgul extends EntityTMHostiles {
//    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.YELLOW, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);
//
//    public EntityTMNazgul(World worldIn) {
//        super(worldIn);
//        this.setSize(1.7F, 3.0F);
//        this.setTtmAttack(true);
//        this.setWeaponType(TTMFeatures.SWORD_MORGULIRON);
//        this.setLootTable(LootInit.NAZGUL);
//        this.setTtmEffect(PotionInit.PARALYSING_FEAR);
//        this.setTtmDuration(200);
//        this.setMobMentality(true, SoundInit.soundAngryWitchKing);
//        this.setMadeBoss(true);
//        this.experienceValue = 100;
//    }
//
//    @Override
//    public double getAttackDamage() {
//        return 20.0D;
//    }
//
//    @Override
//    public double getArmorStrength() {
//        return 25.0D;
//    }
//
//    @Override
//    public double getHealthLevel() {
//        return 150.0D;
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
//    protected void updateAITasks()
//    {
//        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
//        super.updateAITasks();
//    }
//
//    public void readEntityFromNBT(NBTTagCompound compound){
//        if (this.hasCustomName())
//        {
//            this.bossInfo.setName(this.getDisplayName());
//        }
//    }
//
//    public EnumCreatureAttribute getCreatureAttribute()
//    {
//        return EnumCreatureAttribute.UNDEAD;
//    }
//
//    @Override
//    protected SoundEvent getAmbientSound()
//    {
//        return SoundInit.soundIdleWitchKing;
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
//    {
//        return SoundInit.soundHurtWitchKing;
//    }
//
//    @Override
//    protected SoundEvent getDeathSound()
//    {
//        return SoundInit.soundHurtWitchKing;
//    }
//
//    @Override
//    protected void playStepSound(BlockPos pos, Block blockIn)
//    {
//        this.playSound(SoundInit.soundStepWitchKing, 0.25F, 1.0F);
//    }
//}