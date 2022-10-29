package com.greatorator.tolkienmobs.entity.boss.fellbeast.phase;

import com.google.common.collect.*;
import com.greatorator.tolkienmobs.datagen.EntityGenerator;
import com.greatorator.tolkienmobs.entity.boss.fellbeast.FellBeastEntity;
import com.greatorator.tolkienmobs.entity.item.MorgulCrystalEntity;
import com.greatorator.tolkienmobs.world.server.TTMServerWorld;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.EndPortalTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.EndPodiumFeature;
import net.minecraft.world.gen.feature.EndSpikeFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.server.ChunkHolder;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.server.TicketType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Predicate;

public class FellBeastFightManager {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final Predicate<Entity> VALID_PLAYER = EntityPredicates.ENTITY_STILL_ALIVE.and(EntityPredicates.withinDistance(0.0D, 128.0D, 0.0D, 192.0D));
   private final ServerBossInfo fellbeastEvent = (ServerBossInfo)(new ServerBossInfo(new TranslationTextComponent("entity.tolkienmobs.entityttmfellbeast"), BossInfo.Color.PINK, BossInfo.Overlay.PROGRESS)).setPlayBossMusic(true).setCreateWorldFog(true);
   private final TTMServerWorld level;
   private final List<Integer> gateways = Lists.newArrayList();
   private final BlockPattern exitPortalPattern;
   private int ticksSinceFellBeastSeen;
   private int crystalsAlive;
   private int ticksSinceCrystalsScanned;
   private int ticksSinceLastPlayerScan;
   private boolean fellbeastKilled;
   private boolean previouslyKilled;
   private UUID fellbeastUUID;
   private boolean needsStateScanning = true;
   private BlockPos portalLocation;
   private FellBeastSpawnState respawnStage;
   private int respawnTime;
   private List<MorgulCrystalEntity> respawnCrystals;

   public FellBeastFightManager(TTMServerWorld p_i231901_1_, long p_i231901_2_, CompoundNBT p_i231901_4_) {
      this.level = p_i231901_1_;
      if (p_i231901_4_.contains("FellBeastKilled", 99)) {
         if (p_i231901_4_.hasUUID("FellBeast")) {
            this.fellbeastUUID = p_i231901_4_.getUUID("FellBeast");
         }

         this.fellbeastKilled = p_i231901_4_.getBoolean("FellBeastKilled");
         this.previouslyKilled = p_i231901_4_.getBoolean("PreviouslyKilled");
         this.needsStateScanning = !p_i231901_4_.getBoolean("LegacyScanPerformed"); // Forge: fix MC-105080
         if (p_i231901_4_.getBoolean("IsRespawning")) {
            this.respawnStage = FellBeastSpawnState.START;
         }

         if (p_i231901_4_.contains("ExitPortalLocation", 10)) {
            this.portalLocation = NBTUtil.readBlockPos(p_i231901_4_.getCompound("ExitPortalLocation"));
         }
      } else {
         this.fellbeastKilled = true;
         this.previouslyKilled = true;
      }

      if (p_i231901_4_.contains("Gateways", 9)) {
         ListNBT listnbt = p_i231901_4_.getList("Gateways", 3);

         for(int i = 0; i < listnbt.size(); ++i) {
            this.gateways.add(listnbt.getInt(i));
         }
      } else {
         this.gateways.addAll(ContiguousSet.create(Range.closedOpen(0, 20), DiscreteDomain.integers()));
         Collections.shuffle(this.gateways, new Random(p_i231901_2_));
      }

      this.exitPortalPattern = BlockPatternBuilder.start().aisle("       ", "       ", "       ", "   #   ", "       ", "       ", "       ").aisle("       ", "       ", "       ", "   #   ", "       ", "       ", "       ").aisle("       ", "       ", "       ", "   #   ", "       ", "       ", "       ").aisle("  ###  ", " #   # ", "#     #", "#  #  #", "#     #", " #   # ", "  ###  ").aisle("       ", "  ###  ", " ##### ", " ##### ", " ##### ", "  ###  ", "       ").where('#', CachedBlockInfo.hasState(BlockMatcher.forBlock(Blocks.BEDROCK))).build();
   }

   public CompoundNBT saveData() {
      CompoundNBT compoundnbt = new CompoundNBT();
      if (this.fellbeastUUID != null) {
         compoundnbt.putUUID("FellBeast", this.fellbeastUUID);
      }

      compoundnbt.putBoolean("FellBeastKilled", this.fellbeastKilled);
      compoundnbt.putBoolean("PreviouslyKilled", this.previouslyKilled);
      compoundnbt.putBoolean("LegacyScanPerformed", !this.needsStateScanning); // Forge: fix MC-105080
      if (this.portalLocation != null) {
         compoundnbt.put("ExitPortalLocation", NBTUtil.writeBlockPos(this.portalLocation));
      }

      ListNBT listnbt = new ListNBT();

      for(int i : this.gateways) {
         listnbt.add(IntNBT.valueOf(i));
      }

      compoundnbt.put("Gateways", listnbt);
      return compoundnbt;
   }

   public void tick() {
      this.fellbeastEvent.setVisible(!this.fellbeastKilled);
      if (++this.ticksSinceLastPlayerScan >= 20) {
         this.updatePlayers();
         this.ticksSinceLastPlayerScan = 0;
      }

      if (!this.fellbeastEvent.getPlayers().isEmpty()) {
         this.level.getChunkSource().addRegionTicket(TicketType.DRAGON, new ChunkPos(0, 0), 9, Unit.INSTANCE);
         boolean flag = this.isArenaLoaded();
         if (this.needsStateScanning && flag) {
            this.scanState();
            this.needsStateScanning = false;
         }

         if (this.respawnStage != null) {
            if (this.respawnCrystals == null && flag) {
               this.respawnStage = null;
               this.tryRespawn();
            }

            this.respawnStage.tick(this.level, this, this.respawnCrystals, this.respawnTime++, this.portalLocation);
         }

         if (!this.fellbeastKilled) {
            if ((this.fellbeastUUID == null || ++this.ticksSinceFellBeastSeen >= 1200) && flag) {
               this.findOrCreateFellBeast();
               this.ticksSinceFellBeastSeen = 0;
            }

            if (++this.ticksSinceCrystalsScanned >= 100 && flag) {
               this.updateCrystalCount();
               this.ticksSinceCrystalsScanned = 0;
            }
         }
      } else {
         this.level.getChunkSource().removeRegionTicket(TicketType.DRAGON, new ChunkPos(0, 0), 9, Unit.INSTANCE);
      }

   }

   private void scanState() {
      LOGGER.info("Scanning for legacy world fellbeast fight...");
      boolean flag = this.hasActiveExitPortal();
      if (flag) {
         LOGGER.info("Found that the fellbeast has been killed in this world already.");
         this.previouslyKilled = true;
      } else {
         LOGGER.info("Found that the fellbeast has not yet been killed in this world.");
         this.previouslyKilled = false;
         if (this.findExitPortal() == null) {
            this.spawnExitPortal(false);
         }
      }

      List<FellBeastEntity> list = this.level.getFellBeasts();
      if (list.isEmpty()) {
         this.fellbeastKilled = true;
      } else {
         FellBeastEntity fellbeastentity = list.get(0);
         this.fellbeastUUID = fellbeastentity.getUUID();
         LOGGER.info("Found that there's a fellbeast still alive ({})", (Object)fellbeastentity);
         this.fellbeastKilled = false;
         if (!flag) {
            LOGGER.info("But we didn't have a portal, let's remove it.");
            fellbeastentity.remove();
            this.fellbeastUUID = null;
         }
      }

      if (!this.previouslyKilled && this.fellbeastKilled) {
         this.fellbeastKilled = false;
      }

   }

   private void findOrCreateFellBeast() {
      List<FellBeastEntity> list = this.level.getFellBeasts();
      if (list.isEmpty()) {
         LOGGER.debug("Haven't seen the fellbeast, respawning it");
         this.createNewFellBeast();
      } else {
         LOGGER.debug("Haven't seen our fellbeast, but found another one to use.");
         this.fellbeastUUID = list.get(0).getUUID();
      }

   }

   protected void setRespawnStage(FellBeastSpawnState p_186095_1_) {
      if (this.respawnStage == null) {
         throw new IllegalStateException("FellBeast respawn isn't in progress, can't skip ahead in the animation.");
      } else {
         this.respawnTime = 0;
         if (p_186095_1_ == FellBeastSpawnState.END) {
            this.respawnStage = null;
            this.fellbeastKilled = false;
            FellBeastEntity fellbeastentity = this.createNewFellBeast();

            for(ServerPlayerEntity serverplayerentity : this.fellbeastEvent.getPlayers()) {
               CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayerentity, fellbeastentity);
            }
         } else {
            this.respawnStage = p_186095_1_;
         }

      }
   }

   private boolean hasActiveExitPortal() {
      for(int i = -8; i <= 8; ++i) {
         for(int j = -8; j <= 8; ++j) {
            Chunk chunk = this.level.getChunk(i, j);

            for(TileEntity tileentity : chunk.getBlockEntities().values()) {
               if (tileentity instanceof EndPortalTileEntity) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   @Nullable
   private BlockPattern.PatternHelper findExitPortal() {
      for(int i = -8; i <= 8; ++i) {
         for(int j = -8; j <= 8; ++j) {
            Chunk chunk = this.level.getChunk(i, j);

            for(TileEntity tileentity : chunk.getBlockEntities().values()) {
               if (tileentity instanceof EndPortalTileEntity) {
                  BlockPattern.PatternHelper blockpattern$patternhelper = this.exitPortalPattern.find(this.level, tileentity.getBlockPos());
                  if (blockpattern$patternhelper != null) {
                     BlockPos blockpos = blockpattern$patternhelper.getBlock(3, 3, 3).getPos();
                     if (this.portalLocation == null && blockpos.getX() == 0 && blockpos.getZ() == 0) {
                        this.portalLocation = blockpos;
                     }

                     return blockpattern$patternhelper;
                  }
               }
            }
         }
      }

      int k = this.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING, EndPodiumFeature.END_PODIUM_LOCATION).getY();

      for(int l = k; l >= 0; --l) {
         BlockPattern.PatternHelper blockpattern$patternhelper1 = this.exitPortalPattern.find(this.level, new BlockPos(EndPodiumFeature.END_PODIUM_LOCATION.getX(), l, EndPodiumFeature.END_PODIUM_LOCATION.getZ()));
         if (blockpattern$patternhelper1 != null) {
            if (this.portalLocation == null) {
               this.portalLocation = blockpattern$patternhelper1.getBlock(3, 3, 3).getPos();
            }

            return blockpattern$patternhelper1;
         }
      }

      return null;
   }

   private boolean isArenaLoaded() {
      for(int i = -8; i <= 8; ++i) {
         for(int j = 8; j <= 8; ++j) {
            IChunk ichunk = this.level.getChunk(i, j, ChunkStatus.FULL, false);
            if (!(ichunk instanceof Chunk)) {
               return false;
            }

            ChunkHolder.LocationType chunkholder$locationtype = ((Chunk)ichunk).getFullStatus();
            if (!chunkholder$locationtype.isOrAfter(ChunkHolder.LocationType.TICKING)) {
               return false;
            }
         }
      }

      return true;
   }

   private void updatePlayers() {
      Set<ServerPlayerEntity> set = Sets.newHashSet();

      for(ServerPlayerEntity serverplayerentity : this.level.getPlayers(VALID_PLAYER)) {
         this.fellbeastEvent.addPlayer(serverplayerentity);
         set.add(serverplayerentity);
      }

      Set<ServerPlayerEntity> set1 = Sets.newHashSet(this.fellbeastEvent.getPlayers());
      set1.removeAll(set);

      for(ServerPlayerEntity serverplayerentity1 : set1) {
         this.fellbeastEvent.removePlayer(serverplayerentity1);
      }

   }

   private void updateCrystalCount() {
      this.ticksSinceCrystalsScanned = 0;
      this.crystalsAlive = 0;

      for(EndSpikeFeature.EndSpike endspikefeature$endspike : EndSpikeFeature.getSpikesForLevel(this.level)) {
         this.crystalsAlive += this.level.getEntitiesOfClass(MorgulCrystalEntity.class, endspikefeature$endspike.getTopBoundingBox()).size();
      }

      LOGGER.debug("Found {} end crystals still alive", (int)this.crystalsAlive);
   }

   public void setFellBeastKilled(FellBeastEntity p_186096_1_) {
      if (p_186096_1_.getUUID().equals(this.fellbeastUUID)) {
         this.fellbeastEvent.setPercent(0.0F);
         this.fellbeastEvent.setVisible(false);
         this.spawnExitPortal(true);
         this.spawnNewGateway();
         if (!this.previouslyKilled) {
            this.level.setBlockAndUpdate(this.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING, EndPodiumFeature.END_PODIUM_LOCATION), Blocks.DRAGON_EGG.defaultBlockState());
         }

         this.previouslyKilled = true;
         this.fellbeastKilled = true;
      }

   }

   private void spawnNewGateway() {
      if (!this.gateways.isEmpty()) {
         int i = this.gateways.remove(this.gateways.size() - 1);
         int j = MathHelper.floor(96.0D * Math.cos(2.0D * (-Math.PI + 0.15707963267948966D * (double)i)));
         int k = MathHelper.floor(96.0D * Math.sin(2.0D * (-Math.PI + 0.15707963267948966D * (double)i)));
         this.spawnNewGateway(new BlockPos(j, 75, k));
      }
   }

   private void spawnNewGateway(BlockPos p_186089_1_) {
      this.level.levelEvent(3000, p_186089_1_, 0);
      Features.END_GATEWAY_DELAYED.place(this.level, this.level.getChunkSource().getGenerator(), new Random(), p_186089_1_);
   }

   private void spawnExitPortal(boolean p_186094_1_) {
      EndPodiumFeature endpodiumfeature = new EndPodiumFeature(p_186094_1_);
      if (this.portalLocation == null) {
         for(this.portalLocation = this.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EndPodiumFeature.END_PODIUM_LOCATION).below(); this.level.getBlockState(this.portalLocation).is(Blocks.BEDROCK) && this.portalLocation.getY() > this.level.getSeaLevel(); this.portalLocation = this.portalLocation.below()) {
         }
      }

      endpodiumfeature.configured(IFeatureConfig.NONE).place(this.level, this.level.getChunkSource().getGenerator(), new Random(), this.portalLocation);
   }

   private FellBeastEntity createNewFellBeast() {
      this.level.getChunkAt(new BlockPos(0, 128, 0));
      FellBeastEntity fellbeastentity = EntityGenerator.ENTITY_FELL_BEAST.get().create(this.level);
      fellbeastentity.getFellBeastPhaseManager().setPhase(FellBeastPhaseType.HOLDING_PATTERN);
      fellbeastentity.moveTo(0.0D, 128.0D, 0.0D, this.level.random.nextFloat() * 360.0F, 0.0F);
      this.level.addFreshEntity(fellbeastentity);
      this.fellbeastUUID = fellbeastentity.getUUID();
      return fellbeastentity;
   }

   public void updateFellBeast(FellBeastEntity p_186099_1_) {
      if (p_186099_1_.getUUID().equals(this.fellbeastUUID)) {
         this.fellbeastEvent.setPercent(p_186099_1_.getHealth() / p_186099_1_.getMaxHealth());
         this.ticksSinceFellBeastSeen = 0;
         if (p_186099_1_.hasCustomName()) {
            this.fellbeastEvent.setName(p_186099_1_.getDisplayName());
         }
      }

   }

   public int getCrystalsAlive() {
      return this.crystalsAlive;
   }

   public void onCrystalDestroyed(MorgulCrystalEntity p_186090_1_, DamageSource p_186090_2_) {
      if (this.respawnStage != null && this.respawnCrystals.contains(p_186090_1_)) {
         LOGGER.debug("Aborting respawn sequence");
         this.respawnStage = null;
         this.respawnTime = 0;
         this.resetSpikeCrystals();
         this.spawnExitPortal(true);
      } else {
         this.updateCrystalCount();
         Entity entity = this.level.getEntity(this.fellbeastUUID);
         if (entity instanceof FellBeastEntity) {
            ((FellBeastEntity)entity).onCrystalDestroyed(p_186090_1_, p_186090_1_.blockPosition(), p_186090_2_);
         }
      }

   }

   public boolean hasPreviouslyKilledFellBeast() {
      return this.previouslyKilled;
   }

   public void tryRespawn() {
      if (this.fellbeastKilled && this.respawnStage == null) {
         BlockPos blockpos = this.portalLocation;
         if (blockpos == null) {
            LOGGER.debug("Tried to respawn, but need to find the portal first.");
            BlockPattern.PatternHelper blockpattern$patternhelper = this.findExitPortal();
            if (blockpattern$patternhelper == null) {
               LOGGER.debug("Couldn't find a portal, so we made one.");
               this.spawnExitPortal(true);
            } else {
               LOGGER.debug("Found the exit portal & temporarily using it.");
            }

            blockpos = this.portalLocation;
         }

         List<MorgulCrystalEntity> list1 = Lists.newArrayList();
         BlockPos blockpos1 = blockpos.above(1);

         for(Direction direction : Direction.Plane.HORIZONTAL) {
            List<MorgulCrystalEntity> list = this.level.getEntitiesOfClass(MorgulCrystalEntity.class, new AxisAlignedBB(blockpos1.relative(direction, 2)));
            if (list.isEmpty()) {
               return;
            }

            list1.addAll(list);
         }

         LOGGER.debug("Found all crystals, respawning fellbeast.");
         this.respawnFellBeast(list1);
      }

   }

   private void respawnFellBeast(List<MorgulCrystalEntity> p_186093_1_) {
      if (this.fellbeastKilled && this.respawnStage == null) {
         for(BlockPattern.PatternHelper blockpattern$patternhelper = this.findExitPortal(); blockpattern$patternhelper != null; blockpattern$patternhelper = this.findExitPortal()) {
            for(int i = 0; i < this.exitPortalPattern.getWidth(); ++i) {
               for(int j = 0; j < this.exitPortalPattern.getHeight(); ++j) {
                  for(int k = 0; k < this.exitPortalPattern.getDepth(); ++k) {
                     CachedBlockInfo cachedblockinfo = blockpattern$patternhelper.getBlock(i, j, k);
                     if (cachedblockinfo.getState().is(Blocks.BEDROCK) || cachedblockinfo.getState().is(Blocks.END_PORTAL)) {
                        this.level.setBlockAndUpdate(cachedblockinfo.getPos(), Blocks.END_STONE.defaultBlockState());
                     }
                  }
               }
            }
         }

         this.respawnStage = FellBeastSpawnState.START;
         this.respawnTime = 0;
         this.spawnExitPortal(false);
         this.respawnCrystals = p_186093_1_;
      }

   }

   public void resetSpikeCrystals() {
      for(EndSpikeFeature.EndSpike endspikefeature$endspike : EndSpikeFeature.getSpikesForLevel(this.level)) {
         for(MorgulCrystalEntity endercrystalentity : this.level.getEntitiesOfClass(MorgulCrystalEntity.class, endspikefeature$endspike.getTopBoundingBox())) {
            endercrystalentity.setInvulnerable(false);
            endercrystalentity.setBeamTarget((BlockPos)null);
         }
      }
   }

   public void addPlayer(ServerPlayerEntity player) {
      this.fellbeastEvent.addPlayer(player);
   }

   public void removePlayer(ServerPlayerEntity player) {
      this.fellbeastEvent.removePlayer(player);
   }
}
