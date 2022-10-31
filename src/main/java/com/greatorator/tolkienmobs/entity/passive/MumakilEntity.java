package com.greatorator.tolkienmobs.entity.passive;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.HerdEntity;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;

/** Borrowed from Jabelar https://github.com/jabelar */
public class MumakilEntity extends HerdEntity {
    private static final DataParameter<Integer> MUMAKIL_TYPE = EntityDataManager.defineId(MumakilEntity.class, DataSerializers.INT);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(0, new ResourceLocation(TolkienMobs.MODID, "textures/entity/mumakil/mumakil1.png"));
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/mumakil/mumakil2.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/mumakil/mumakil3.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/mumakil/mumakil4.png"));
    });
    protected Block spawnableBlock = Blocks.SAND;

    public MumakilEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 50.0D).add(Attributes.ATTACK_DAMAGE, 16.0D).add(Attributes.ARMOR, 8.0D).add(Attributes.KNOCKBACK_RESISTANCE, 1.0D).add(Attributes.MOVEMENT_SPEED, 0.30000001192092896D);
    }

    public static int spawnChance()
    {
        int i = TTMRand.getRandomInteger(100, 1);
        return i;
    }

    protected boolean isValidLightLevel() {
        return true;
    }

    public boolean getCanSpawnHere() {
        int i = MathHelper.floor(this.getX());
        int j = MathHelper.floor(this.getBoundingBox().minY);
        int k = MathHelper.floor(this.getZ());
        BlockPos blockpos = new BlockPos(i, j, k);

        return this.level.getDifficulty() != Difficulty.PEACEFUL && this.level.getBlockState(blockpos.below()).getBlock() == this.spawnableBlock && this.level.getMaxLocalRawBrightness(blockpos) > 8;
    }

    public static boolean checkMumakilSpawn(EntityType<MumakilEntity> type, IWorld world, SpawnReason reason, BlockPos pos, Random random) {
        int chance = 200; //1 in x
        return random.nextInt(chance) == 0 && checkMobSpawnRules(type, world, reason, pos, random);
    }


    @Override
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    @Override
    public MumakilEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return EntityGenerator.ENTITY_TTM_MUMAKIL.get().create(p_241840_1_);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundGenerator.soundAmbientMumakil.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundGenerator.soundHurtMumakil.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundGenerator.soundHurtMumakil.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.COW_STEP, 0.15F, 1.0F);
    }

    /** Region for determining random skin */
    public ResourceLocation getMumakilTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getMumakilType(), TEXTURE_BY_ID.get(1));
    }

    public int getMumakilType() {
        return this.entityData.get(MUMAKIL_TYPE);
    }

    public void setMumakilType(int type) {
        if (type < 0 || type >= 5) {
            type = this.random.nextInt(4);
        }

        this.entityData.set(MUMAKIL_TYPE, type);
    }

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(1, 4);
        this.setMumakilType(job);

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(MUMAKIL_TYPE, 1);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("MumakilType", this.getMumakilType());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setMumakilType(compound.getInt("MumakilType"));
    }
}