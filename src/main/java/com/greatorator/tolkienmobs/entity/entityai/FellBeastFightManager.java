package com.greatorator.tolkienmobs.entity.entityai;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.*;
import com.greatorator.tolkienmobs.entity.boss.EntityFellBeast;
import com.greatorator.tolkienmobs.entity.entityai.phase.TTMPhaseList;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEndPortal;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeEndDecorator;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.WorldGenEndGateway;
import net.minecraft.world.gen.feature.WorldGenEndPodium;
import net.minecraft.world.gen.feature.WorldGenSpikes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.*;

public class FellBeastFightManager {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Predicate<EntityPlayerMP> VALID_PLAYER = Predicates.<EntityPlayerMP>and(EntitySelectors.IS_ALIVE, EntitySelectors.withinRange(0.0D, 128.0D, 0.0D, 192.0D));
    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(new TextComponentTranslation("entity.fellbeast.name", new Object[0]), BossInfo.Color.PINK, BossInfo.Overlay.PROGRESS)).setPlayEndBossMusic(true).setCreateFog(true);
    private final WorldServer world;
    private final List<Integer> gateways = Lists.<Integer>newArrayList();
    private final BlockPattern portalPattern;
    private int ticksSinceFellBeastSeen;
    private int aliveCrystals;
    private int ticksSinceCrystalsScanned;
    private int ticksSinceLastPlayerScan;
    private boolean fellbeastKilled;
    private boolean previouslyKilled;
    private UUID fellbeastUniqueId;
    private boolean scanForLegacyFight = true;
    private BlockPos exitPortalLocation;
    private FellBeastSpawnManager respawnState;
    private int respawnStateTicks;
    private List<EntityEnderCrystal> crystals;

    public FellBeastFightManager(WorldServer worldIn, NBTTagCompound compound)
    {
        this.world = worldIn;

        if (compound.hasKey("FellBeastKilled", 99))
        {
            if (compound.hasUniqueId("FellBeastUUID"))
            {
                this.fellbeastUniqueId = compound.getUniqueId("FellBeastUUID");
            }

            this.fellbeastKilled = compound.getBoolean("FellBeastKilled");
            this.previouslyKilled = compound.getBoolean("PreviouslyKilled");

            if (compound.getBoolean("IsRespawning"))
            {
                this.respawnState = FellBeastSpawnManager.START;
            }

            if (compound.hasKey("ExitPortalLocation", 10))
            {
                this.exitPortalLocation = NBTUtil.getPosFromTag(compound.getCompoundTag("ExitPortalLocation"));
            }
        }
        else
        {
            this.fellbeastKilled = true;
            this.previouslyKilled = true;
        }

        if (compound.hasKey("Gateways", 9))
        {
            NBTTagList nbttaglist = compound.getTagList("Gateways", 3);

            for (int i = 0; i < nbttaglist.tagCount(); ++i)
            {
                this.gateways.add(Integer.valueOf(nbttaglist.getIntAt(i)));
            }
        }
        else
        {
            this.gateways.addAll(ContiguousSet.create(Range.closedOpen(Integer.valueOf(0), Integer.valueOf(20)), DiscreteDomain.integers()));
            Collections.shuffle(this.gateways, new Random(worldIn.getSeed()));
        }

        this.portalPattern = FactoryBlockPattern.start().aisle("       ", "       ", "       ", "   #   ", "       ", "       ", "       ").aisle("       ", "       ", "       ", "   #   ", "       ", "       ", "       ").aisle("       ", "       ", "       ", "   #   ", "       ", "       ", "       ").aisle("  ###  ", " #   # ", "#     #", "#  #  #", "#     #", " #   # ", "  ###  ").aisle("       ", "  ###  ", " ##### ", " ##### ", " ##### ", "  ###  ", "       ").where('#', BlockWorldState.hasState(BlockMatcher.forBlock(Blocks.BEDROCK))).build();
    }

    public NBTTagCompound getCompound()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        if (this.fellbeastUniqueId != null)
        {
            nbttagcompound.setUniqueId("FellBeastUUID", this.fellbeastUniqueId);
        }

        nbttagcompound.setBoolean("FellBeastKilled", this.fellbeastKilled);
        nbttagcompound.setBoolean("PreviouslyKilled", this.previouslyKilled);

        if (this.exitPortalLocation != null)
        {
            nbttagcompound.setTag("ExitPortalLocation", NBTUtil.createPosTag(this.exitPortalLocation));
        }

        NBTTagList nbttaglist = new NBTTagList();
        Iterator iterator = this.gateways.iterator();

        while (iterator.hasNext())
        {
            int i = ((Integer)iterator.next()).intValue();
            nbttaglist.appendTag(new NBTTagInt(i));
        }

        nbttagcompound.setTag("Gateways", nbttaglist);
        return nbttagcompound;
    }

    public void tick()
    {
        this.bossInfo.setVisible(!this.fellbeastKilled);

        if (++this.ticksSinceLastPlayerScan >= 20)
        {
            this.updateplayers();
            this.ticksSinceLastPlayerScan = 0;
        }

        if (!this.bossInfo.getPlayers().isEmpty())
        {
            if (this.scanForLegacyFight)
            {
                LOGGER.info("Scanning for legacy world fellbeast fight...");
                this.loadChunks();
                this.scanForLegacyFight = false;
                boolean flag = this.hasFellBeastBeenKilled();

                if (flag)
                {
                    LOGGER.info("Found that the fellbeast has been killed in this world already.");
                    this.previouslyKilled = true;
                }
                else
                {
                    LOGGER.info("Found that the fellbeast has not yet been killed in this world.");
                    this.previouslyKilled = false;
                    this.generatePortal(false);
                }

                List<EntityFellBeast> list = this.world.getEntities(EntityFellBeast.class, EntitySelectors.IS_ALIVE);

                if (list.isEmpty())
                {
                    this.fellbeastKilled = true;
                }
                else
                {
                    EntityFellBeast entityfellbeast = list.get(0);
                    this.fellbeastUniqueId = entityfellbeast.getUniqueID();
                    LOGGER.info("Found that there's a fellbeast still alive ({})", (Object)entityfellbeast);
                    this.fellbeastKilled = false;

                    if (!flag)
                    {
                        LOGGER.info("But we didn't have a portal, let's remove it.");
                        entityfellbeast.setDead();
                        this.fellbeastUniqueId = null;
                    }
                }

                if (!this.previouslyKilled && this.fellbeastKilled)
                {
                    this.fellbeastKilled = false;
                }
            }

            if (this.respawnState != null)
            {
                if (this.crystals == null)
                {
                    this.respawnState = null;
                    this.respawnFellBeast();
                }

                this.respawnState.process(this.world, this, this.crystals, this.respawnStateTicks++, this.exitPortalLocation);
            }

            if (!this.fellbeastKilled)
            {
                if (this.fellbeastUniqueId == null || ++this.ticksSinceFellBeastSeen >= 1200)
                {
                    this.loadChunks();
                    List<EntityFellBeast> list1 = this.world.getEntities(EntityFellBeast.class, EntitySelectors.IS_ALIVE);

                    if (list1.isEmpty())
                    {
                        LOGGER.debug("Haven't seen the fellbeast, respawning it");
                        this.createNewFellBeast();
                    }
                    else
                    {
                        LOGGER.debug("Haven't seen our fellbeast, but found another one to use.");
                        this.fellbeastUniqueId = ((EntityFellBeast)list1.get(0)).getUniqueID();
                    }

                    this.ticksSinceFellBeastSeen = 0;
                }

                if (++this.ticksSinceCrystalsScanned >= 100)
                {
                    this.findAliveCrystals();
                    this.ticksSinceCrystalsScanned = 0;
                }
            }
        }
    }

    protected void setRespawnState(FellBeastSpawnManager state)
    {
        if (this.respawnState == null)
        {
            throw new IllegalStateException("FellBeast respawn isn't in progress, can't skip ahead in the animation.");
        }
        else
        {
            this.respawnStateTicks = 0;

            if (state == FellBeastSpawnManager.END)
            {
                this.respawnState = null;
                this.fellbeastKilled = false;
                EntityFellBeast entityfellbeast = this.createNewFellBeast();

                for (EntityPlayerMP entityplayermp : this.bossInfo.getPlayers())
                {
                    CriteriaTriggers.SUMMONED_ENTITY.trigger(entityplayermp, entityfellbeast);
                }
            }
            else
            {
                this.respawnState = state;
            }
        }
    }

    private boolean hasFellBeastBeenKilled()
    {
        for (int i = -8; i <= 8; ++i)
        {
            for (int j = -8; j <= 8; ++j)
            {
                Chunk chunk = this.world.getChunkFromChunkCoords(i, j);

                for (TileEntity tileentity : chunk.getTileEntityMap().values())
                {
                    if (tileentity instanceof TileEntityEndPortal)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Nullable
    private BlockPattern.PatternHelper findExitPortal()
    {
        for (int i = -8; i <= 8; ++i)
        {
            for (int j = -8; j <= 8; ++j)
            {
                Chunk chunk = this.world.getChunkFromChunkCoords(i, j);

                for (TileEntity tileentity : chunk.getTileEntityMap().values())
                {
                    if (tileentity instanceof TileEntityEndPortal)
                    {
                        BlockPattern.PatternHelper blockpattern$patternhelper = this.portalPattern.match(this.world, tileentity.getPos());

                        if (blockpattern$patternhelper != null)
                        {
                            BlockPos blockpos = blockpattern$patternhelper.translateOffset(3, 3, 3).getPos();

                            if (this.exitPortalLocation == null && blockpos.getX() == 0 && blockpos.getZ() == 0)
                            {
                                this.exitPortalLocation = blockpos;
                            }

                            return blockpattern$patternhelper;
                        }
                    }
                }
            }
        }

        int k = this.world.getHeight(WorldGenEndPodium.END_PODIUM_LOCATION).getY();

        for (int l = k; l >= 0; --l)
        {
            BlockPattern.PatternHelper blockpattern$patternhelper1 = this.portalPattern.match(this.world, new BlockPos(WorldGenEndPodium.END_PODIUM_LOCATION.getX(), l, WorldGenEndPodium.END_PODIUM_LOCATION.getZ()));

            if (blockpattern$patternhelper1 != null)
            {
                if (this.exitPortalLocation == null)
                {
                    this.exitPortalLocation = blockpattern$patternhelper1.translateOffset(3, 3, 3).getPos();
                }

                return blockpattern$patternhelper1;
            }
        }

        return null;
    }

    private void loadChunks()
    {
        for (int i = -8; i <= 8; ++i)
        {
            for (int j = -8; j <= 8; ++j)
            {
                this.world.getChunkFromChunkCoords(i, j);
            }
        }
    }

    private void updateplayers()
    {
        Set<EntityPlayerMP> set = Sets.<EntityPlayerMP>newHashSet();

        for (EntityPlayerMP entityplayermp : this.world.getPlayers(EntityPlayerMP.class, VALID_PLAYER))
        {
            this.bossInfo.addPlayer(entityplayermp);
            set.add(entityplayermp);
        }

        Set<EntityPlayerMP> set1 = Sets.newHashSet(this.bossInfo.getPlayers());
        set1.removeAll(set);

        for (EntityPlayerMP entityplayermp1 : set1)
        {
            this.bossInfo.removePlayer(entityplayermp1);
        }
    }

    private void findAliveCrystals()
    {
        this.ticksSinceCrystalsScanned = 0;
        this.aliveCrystals = 0;

        for (WorldGenSpikes.EndSpike worldgenspikes$endspike : BiomeEndDecorator.getSpikesForWorld(this.world))
        {
            this.aliveCrystals += this.world.getEntitiesWithinAABB(EntityEnderCrystal.class, worldgenspikes$endspike.getTopBoundingBox()).size();
        }

        LOGGER.debug("Found {} end crystals still alive", (int)this.aliveCrystals);
    }

    public void processFellBeastDeath(EntityFellBeast fellbeast)
    {
        if (fellbeast.getUniqueID().equals(this.fellbeastUniqueId))
        {
            this.bossInfo.setPercent(0.0F);
            this.bossInfo.setVisible(false);
            this.generatePortal(true);
            this.spawnNewGateway();

            if (!this.previouslyKilled)
            {
                this.world.setBlockState(this.world.getHeight(WorldGenEndPodium.END_PODIUM_LOCATION), Blocks.DRAGON_EGG.getDefaultState());
            }

            this.previouslyKilled = true;
            this.fellbeastKilled = true;
        }
    }

    private void spawnNewGateway()
    {
        if (!this.gateways.isEmpty())
        {
            int i = ((Integer)this.gateways.remove(this.gateways.size() - 1)).intValue();
            int j = (int)(96.0D * Math.cos(2.0D * (-Math.PI + 0.15707963267948966D * (double)i)));
            int k = (int)(96.0D * Math.sin(2.0D * (-Math.PI + 0.15707963267948966D * (double)i)));
            this.generateGateway(new BlockPos(j, 75, k));
        }
    }

    private void generateGateway(BlockPos pos)
    {
        this.world.playEvent(3000, pos, 0);
        (new WorldGenEndGateway()).generate(this.world, new Random(), pos);
    }

    private void generatePortal(boolean active)
    {
        WorldGenEndPodium worldgenendpodium = new WorldGenEndPodium(active);

        if (this.exitPortalLocation == null)
        {
            for (this.exitPortalLocation = this.world.getTopSolidOrLiquidBlock(WorldGenEndPodium.END_PODIUM_LOCATION).down(); this.world.getBlockState(this.exitPortalLocation).getBlock() == Blocks.BEDROCK && this.exitPortalLocation.getY() > this.world.getSeaLevel(); this.exitPortalLocation = this.exitPortalLocation.down())
            {
                ;
            }
        }

        worldgenendpodium.generate(this.world, new Random(), this.exitPortalLocation);
    }

    private EntityFellBeast createNewFellBeast()
    {
        this.world.getChunkFromBlockCoords(new BlockPos(0, 128, 0));
        EntityFellBeast entityfellbeast = new EntityFellBeast(this.world);
        entityfellbeast.getFellBeastPhaseManager().setPhase(TTMPhaseList.HOLDING_PATTERN);
        entityfellbeast.setLocationAndAngles(0.0D, 128.0D, 0.0D, this.world.rand.nextFloat() * 360.0F, 0.0F);
        this.world.spawnEntity(entityfellbeast);
        this.fellbeastUniqueId = entityfellbeast.getUniqueID();
        return entityfellbeast;
    }

    public void fellbeastUpdate(EntityFellBeast fellbeastIn)
    {
        if (fellbeastIn.getUniqueID().equals(this.fellbeastUniqueId))
        {
            this.bossInfo.setPercent(fellbeastIn.getHealth() / fellbeastIn.getMaxHealth());
            this.ticksSinceFellBeastSeen = 0;

            if (fellbeastIn.hasCustomName())
            {
                this.bossInfo.setName(fellbeastIn.getDisplayName());
            }
        }
    }

    public int getNumAliveCrystals()
    {
        return this.aliveCrystals;
    }

    public void onCrystalDestroyed(EntityEnderCrystal crystal, DamageSource dmgSrc)
    {
        if (this.respawnState != null && this.crystals.contains(crystal))
        {
            LOGGER.debug("Aborting respawn sequence");
            this.respawnState = null;
            this.respawnStateTicks = 0;
            this.resetSpikeCrystals();
            this.generatePortal(true);
        }
        else
        {
            this.findAliveCrystals();
            Entity entity = this.world.getEntityFromUuid(this.fellbeastUniqueId);

            if (entity instanceof EntityFellBeast)
            {
                ((EntityFellBeast)entity).onCrystalDestroyed(crystal, new BlockPos(crystal), dmgSrc);
            }
        }
    }

    public boolean hasPreviouslyKilledFellBeast()
    {
        return this.previouslyKilled;
    }

    public void respawnFellBeast()
    {
        if (this.fellbeastKilled && this.respawnState == null)
        {
            BlockPos blockpos = this.exitPortalLocation;

            if (blockpos == null)
            {
                LOGGER.debug("Tried to respawn, but need to find the portal first.");
                BlockPattern.PatternHelper blockpattern$patternhelper = this.findExitPortal();

                if (blockpattern$patternhelper == null)
                {
                    LOGGER.debug("Couldn't find a portal, so we made one.");
                    this.generatePortal(true);
                }
                else
                {
                    LOGGER.debug("Found the exit portal & temporarily using it.");
                }

                blockpos = this.exitPortalLocation;
            }

            List<EntityEnderCrystal> list1 = Lists.<EntityEnderCrystal>newArrayList();
            BlockPos blockpos1 = blockpos.up(1);

            for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
            {
                List<EntityEnderCrystal> list = this.world.getEntitiesWithinAABB(EntityEnderCrystal.class, new AxisAlignedBB(blockpos1.offset(enumfacing, 2)));

                if (list.isEmpty())
                {
                    return;
                }

                list1.addAll(list);
            }

            LOGGER.debug("Found all crystals, respawning fellbeast.");
            this.respawnFellBeast(list1);
        }
    }

    private void respawnFellBeast(List<EntityEnderCrystal> crystalsIn)
    {
        if (this.fellbeastKilled && this.respawnState == null)
        {
            for (BlockPattern.PatternHelper blockpattern$patternhelper = this.findExitPortal(); blockpattern$patternhelper != null; blockpattern$patternhelper = this.findExitPortal())
            {
                for (int i = 0; i < this.portalPattern.getPalmLength(); ++i)
                {
                    for (int j = 0; j < this.portalPattern.getThumbLength(); ++j)
                    {
                        for (int k = 0; k < this.portalPattern.getFingerLength(); ++k)
                        {
                            BlockWorldState blockworldstate = blockpattern$patternhelper.translateOffset(i, j, k);

                            if (blockworldstate.getBlockState().getBlock() == Blocks.BEDROCK || blockworldstate.getBlockState().getBlock() == Blocks.END_PORTAL)
                            {
                                this.world.setBlockState(blockworldstate.getPos(), Blocks.END_STONE.getDefaultState());
                            }
                        }
                    }
                }
            }

            this.respawnState = FellBeastSpawnManager.START;
            this.respawnStateTicks = 0;
            this.generatePortal(false);
            this.crystals = crystalsIn;
        }
    }

    public void resetSpikeCrystals()
    {
        for (WorldGenSpikes.EndSpike worldgenspikes$endspike : BiomeEndDecorator.getSpikesForWorld(this.world))
        {
            for (EntityEnderCrystal entityendercrystal : this.world.getEntitiesWithinAABB(EntityEnderCrystal.class, worldgenspikes$endspike.getTopBoundingBox()))
            {
                entityendercrystal.setEntityInvulnerable(false);
                entityendercrystal.setBeamTarget((BlockPos)null);
            }
        }
    }
}
