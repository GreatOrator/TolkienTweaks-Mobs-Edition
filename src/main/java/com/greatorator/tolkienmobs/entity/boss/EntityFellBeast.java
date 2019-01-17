package com.greatorator.tolkienmobs.entity.boss;

import com.greatorator.tolkienmobs.init.LootInit;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.dragon.phase.PhaseList;
import net.minecraft.entity.boss.dragon.phase.PhaseManager;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.pathfinding.PathHeap;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.end.DragonFightManager;

import javax.annotation.Nullable;

public class EntityFellBeast extends EntityDragon {
    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);

    private final DragonFightManager fightManager;

    private final PhaseManager phaseManager;
    private int growlTime = 200;
    private int sittingDamageReceived;

    private final PathPoint[] pathPoints = new PathPoint[24];

    private final int[] neighbors = new int[24];
    private final PathHeap pathFindQueue = new PathHeap();

    public EntityFellBeast(World worldIn) {
        super(worldIn);
        this.dragonPartArray = new MultiPartEntityPart[] {this.dragonPartHead, this.dragonPartNeck, this.dragonPartBody, this.dragonPartTail1, this.dragonPartTail2, this.dragonPartTail3, this.dragonPartWing1, this.dragonPartWing2};
        this.setHealth(this.getMaxHealth());
        this.setSize(16.0F, 8.0F);
        this.noClip = true;
        this.isImmuneToFire = true;
        this.growlTime = 100;
        this.ignoreFrustumCheck = true;

        if (!worldIn.isRemote && worldIn.provider instanceof WorldProviderEnd)
        {
            this.fightManager = ((WorldProviderEnd)worldIn.provider).getDragonFightManager();
        }
        else
        {
            this.fightManager = null;
        }

        this.phaseManager = new PhaseManager(this);
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootInit.FELLBEAST;
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

    protected void updateAITasks()
    {
        super.updateAITasks();
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }

    public boolean isNonBoss()
    {
        return false;
    }
}
