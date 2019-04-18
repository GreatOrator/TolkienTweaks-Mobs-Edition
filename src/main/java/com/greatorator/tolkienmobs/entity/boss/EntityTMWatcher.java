package com.greatorator.tolkienmobs.entity.boss;

import com.greatorator.tolkienmobs.entity.EntityTMAquatics;
import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.init.PotionInit;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

public class EntityTMWatcher extends EntityTMAquatics {
    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.BLUE, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);

    public EntityTMWatcher(World worldIn) {
        super(worldIn);
        this.setSize(6.7F, 4.5F);
        this.setTtmAttack(true);
        this.setLootTable(LootInit.WATCHER);
        this.setTtmEffect(PotionInit.ELEMENTAL_DROWNING);
        this.setTtmDuration(200);
        this.setMadeBoss(true);
        this.isImmuneToFire = true;
        this.experienceValue = 200;
    }

    @Override
    public double getAttackDamage() {
        return 20.0D;
    }

    @Override
    public double getArmorStrength() {
        return 25.0D;
    }

    @Override
    public double getHealthLevel() {
        return 300.0D;
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

    public void setCustomNameTag(String name)
    {
        super.setCustomNameTag(name);
        this.bossInfo.setName(this.getDisplayName());
    }

    protected void updateAITasks()
    {
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
        super.updateAITasks();
    }

    public void readEntityFromNBT(NBTTagCompound compound){
        if (this.hasCustomName())
        {
            this.bossInfo.setName(this.getDisplayName());
        }
    }
}