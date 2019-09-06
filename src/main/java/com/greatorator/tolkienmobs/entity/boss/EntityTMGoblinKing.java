package com.greatorator.tolkienmobs.entity.boss;

import com.greatorator.tolkienmobs.entity.EntityTMHostiles;
import com.greatorator.tolkienmobs.entity.hostile.EntityTMGoblin;
import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.init.SoundInit;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import java.util.List;

public class EntityTMGoblinKing extends EntityTMHostiles {
    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.WHITE, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);

    public EntityTMGoblinKing(World worldIn) {
        super(worldIn);
        this.setSize(0.9F, 1.3F);
        this.setWeaponType(TTMFeatures.CLUB_WOODEN);
        this.setLootTable(LootInit.GOBLIN);
        this.setMobMentality(true, SoundInit.soundAngryGoblin);
        this.setTtmEffect(MobEffects.WEAKNESS);
        this.setMadeBoss(true);
        this.setCombatTask();
        this.experienceValue = 200;
    }

    @Override
    public double getAttackDamage() {
        return 12;
    }

    @Override
    public double getArmorStrength() {
        return 12;
    }

    @Override
    public double getHealthLevel() {
        return 125;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundInit.soundIdleGoblin;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundInit.soundHurtGoblin;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundInit.soundDeathGoblin;
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

    @Override
    public boolean getCanSpawnHere() {
        int i = MathHelper.floor(this.posX);
        int j = MathHelper.floor(this.getEntityBoundingBox().minY);
        int k = MathHelper.floor(this.posZ);
        boolean monsterSpawn = false;

        List entities = getEntityWorld().getEntitiesWithinAABB(EntityTMGoblin.class,getEntityBoundingBox().expand(32,32,32));
        List entitiesKing = getEntityWorld().getEntitiesWithinAABB(EntityTMGoblinKing.class,getEntityBoundingBox().expand(32,32,32));

        BlockPos blockpos = new BlockPos(i, j, k);

        if (this.world.getDifficulty() != EnumDifficulty.PEACEFUL && entities.size() > 16 && entitiesKing.size() <= 1 && this.world.getLight(blockpos) < 8 && super.getCanSpawnHere() && !this.world.canSeeSky(new BlockPos(this)) && this.posY < 128.0D) {
                monsterSpawn = true;
            }
        return super.getCanSpawnHere() && monsterSpawn;
    }
}
