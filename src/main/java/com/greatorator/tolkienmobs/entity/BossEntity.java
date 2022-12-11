package com.greatorator.tolkienmobs.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

import static net.minecraft.world.BossEvent.BossBarOverlay.PROGRESS;

public abstract class BossEntity extends MonsterEntity {
    private final ServerBossEvent bossEvent;

    protected BossEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.bossEvent = ((ServerBossEvent)new ServerBossEvent(this.getDisplayName(), getBossNameColour(), PROGRESS).setDarkenScreen(false));
    }

    public abstract BossEvent.BossBarColor getBossNameColour();

//    public int getInvulnerableTicks() {
//        return this.entityData.get(DATA_ID_INV);
//    }

//    public void setInvulnerableTicks(int invulnerableTicks) {
//        this.entityData.set(DATA_ID_INV, invulnerableTicks);
//    }

    public void addAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
//        tag.putInt("Invul", this.getInvulnerableTicks());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
//        this.setInvulnerableTicks(tag.getInt("Invul"));
        if (this.hasCustomName()) {
            this.bossEvent.setName(this.getDisplayName());
        }
    }

    @Override
    public void setCustomName(@org.jetbrains.annotations.Nullable net.minecraft.network.chat.Component pName) {
        super.setCustomName(pName);
        this.bossEvent.setName(this.getDisplayName());
    }

    @Override
    public void startSeenByPlayer(ServerPlayer pServerPlayer) {
        super.startSeenByPlayer(pServerPlayer);
        this.bossEvent.addPlayer(pServerPlayer);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer pServerPlayer) {
        super.stopSeenByPlayer(pServerPlayer);
        this.bossEvent.removePlayer(pServerPlayer);
    }

//    public void makeInvulnerable() {
//        this.setInvulnerableTicks(220);
//        this.balrogEvent.setProgress(0.0F);
//        this.setHealth(this.getMaxHealth() / 3.0F);
//    }

    @Override
    public void tick() {
//        if (this.getInvulnerableTicks() > 0) {
//            int k1 = this.getInvulnerableTicks() - 1;
//            this.balrogEvent.setProgress(1.0F - (float) k1 / 220.0F);
//        }

        super.tick();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }
}
