//package com.greatorator.tolkienmobs.entity.boss;
//
//import com.greatorator.tolkienmobs.entity.EntityTMAquatics;
//import com.greatorator.tolkienmobs.init.LootInit;
//import com.greatorator.tolkienmobs.init.PotionInit;
//import com.greatorator.tolkienmobs.init.SoundInit;
//import net.minecraft.entity.player.ServerPlayerEntity;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.util.SoundEvent;
//import net.minecraft.world.BossInfo;
//import net.minecraft.world.BossInfoServer;
//import net.minecraft.world.World;
//
//public class EntityTMWatcher extends EntityTMAquatics {
//    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.BLUE, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);
//
//    public EntityTMWatcher(World worldIn) {
//        super(worldIn);
//        this.setSize(6.7F, 4.5F);
//        this.setLootTable(LootInit.WATCHER);
//        this.setHostileWater(true);
//        this.setTtmEffect(PotionInit.ELEMENTAL_DROWNING);
//        this.setTtmDuration(600);
//        this.isImmuneToFire = true;
//        this.experienceValue = 200;
//    }
//
//    @Override
//    public double getHealthLevel() {
//        return 300.0D;
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
//    @Override
//    protected SoundEvent getAmbientSound()
//    {
//        return SoundInit.soundIdleWatcher;
//    }
//
//    @Override
//    protected SoundEvent getDeathSound()
//    {
//        return SoundInit.soundDeathWatcher;
//    }
//}