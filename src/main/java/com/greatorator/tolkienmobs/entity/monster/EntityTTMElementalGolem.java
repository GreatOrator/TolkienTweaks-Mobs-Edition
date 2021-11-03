package com.greatorator.tolkienmobs.entity.monster;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.EntityTTMMonsters;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.UUID;

public class EntityTTMElementalGolem extends EntityTTMMonsters implements IAngerable {
    private static final DataParameter<String> ELEMENT_TYPE = EntityDataManager.defineId(EntityTTMElementalGolem.class, DataSerializers.STRING);
    private static final DataParameter<Integer> GOLEM_TYPE = EntityDataManager.defineId(EntityTTMElementalGolem.class, DataSerializers.INT);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/elementalgolem/elemental_golem_earth.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/elementalgolem/elemental_golem_air.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/elementalgolem/elemental_golem_fire.png"));
        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/elementalgolem/elemental_golem_water.png"));
        option.put(5, new ResourceLocation(TolkienMobs.MODID, "textures/entity/elementalgolem/elemental_golem_none.png"));
        option.put(6, new ResourceLocation(TolkienMobs.MODID, "textures/entity/elementalgolem/elemental_golem_mithril.png"));
        option.put(7, new ResourceLocation(TolkienMobs.MODID, "textures/entity/elementalgolem/elemental_golem_morgul.png"));
    });
    private static final RangedInteger PERSISTENT_ANGER_TIME = TickRangeConverter.rangeOfSeconds(20, 39);
    private static int attackAnimationTick;
    private int remainingPersistentAngerTime;
    private UUID persistentAngerTarget;
    private long nextAbilityUse = 0L;
    private final static long coolDown = 15000L;

    public EntityTTMElementalGolem(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.addGoal(2, new ReturnToVillageGoal(this, 0.6D, false));
        this.goalSelector.addGoal(4, new PatrolVillageGoal(this, 0.6D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MobEntity.class, 5, false, false, (p_234199_0_) -> {
            return p_234199_0_ instanceof IMob && !(p_234199_0_ instanceof CreeperEntity);
        }));
        this.targetSelector.addGoal(4, new ResetAngerGoal<>(this, false));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.ATTACK_DAMAGE, 15.0D);
    }

    public void aiStep() {
        super.aiStep();
        if (this.attackAnimationTick > 0) {
            --this.attackAnimationTick;
        }

        if (getHorizontalDistanceSqr(this.getDeltaMovement()) > (double)2.5000003E-7F && this.random.nextInt(5) == 0) {
            int i = MathHelper.floor(this.getX());
            int j = MathHelper.floor(this.getY() - (double)0.2F);
            int k = MathHelper.floor(this.getZ());
            BlockPos pos = new BlockPos(i, j, k);
            BlockState blockstate = this.level.getBlockState(pos);
            if (!blockstate.isAir(this.level, pos)) {
                this.level.addParticle(new BlockParticleData(ParticleTypes.BLOCK, blockstate).setPos(pos), this.getX() + ((double)this.random.nextFloat() - 0.5D) * (double)this.getBbWidth(), this.getY() + 0.1D, this.getZ() + ((double)this.random.nextFloat() - 0.5D) * (double)this.getBbWidth(), 4.0D * ((double)this.random.nextFloat() - 0.5D), 0.5D, ((double)this.random.nextFloat() - 0.5D) * 4.0D);
            }
        }

        if (!this.level.isClientSide) {
            this.updatePersistentAnger((ServerWorld)this.level, true);
        }

    }

    private float getAttackDamage() {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    @OnlyIn(Dist.CLIENT)
    public static int getAttackAnimationTick() {
        return EntityTTMElementalGolem.attackAnimationTick;
    }

    public boolean doHurtTarget(Entity p_70652_1_) {
        this.attackAnimationTick = 10;
        this.level.broadcastEntityEvent(this, (byte)4);
        float f = this.getAttackDamage();
        float f1 = (int)f > 0 ? f / 2.0F + (float)this.random.nextInt((int)f) : f;
        boolean flag = p_70652_1_.hurt(DamageSource.mobAttack(this), f1);
        if (flag) {
            p_70652_1_.setDeltaMovement(p_70652_1_.getDeltaMovement().add(0.0D, (double)0.4F, 0.0D));
            this.doEnchantDamageEffects(this, p_70652_1_);
        }

        this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        return flag;
    }

    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte p_70103_1_) {
        if (p_70103_1_ == 4)
            this.attackAnimationTick = 10;
            this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return this.remainingPersistentAngerTime;
    }

    @Override
    public void setRemainingPersistentAngerTime(int i) {
        this.remainingPersistentAngerTime = i;
    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID uuid) {
        this.persistentAngerTarget = uuid;
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.randomValue(this.random));
    }

    /**
     * Region for determining random skin
     */
    public ResourceLocation getElementalGolemTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getElementalGolemType(), TEXTURE_BY_ID.get(0));
    }

    public int getElementalGolemType() {
        return this.entityData.get(GOLEM_TYPE);
    }

    public void setElementalGolemType(int type) {
        if (type < 0 || type >= 8) {
            type = this.random.nextInt(8);
        }

        this.entityData.set(GOLEM_TYPE, type);
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(7, 1);
        this.setElementalGolemType(job);
        this.reassessWeaponGoal();

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(GOLEM_TYPE, 3);
    }

    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("ElementalGolemType", this.getElementalGolemType());
    }

    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setElementalGolemType(compound.getInt("ElementalGolemType"));
    }
//    private long nextAbilityUse = 0L;
//    private final static long coolDown = 15000L;
//    private LivingEntity mob;
//
//    private static final DataParameter<Integer> ELEMENT_TYPE = EntityDataManager.<Integer>createKey(EntityTMElementalGolem.class, DataSerializers.VARIANT);
//
//    public EntityTMElementalGolem(World worldIn) {
//        super(worldIn);
//        this.setSize(1.8F, 3.1F);
//        this.setMob(this);
//    }
//
//    protected void entityInit()
//    {
//        super.entityInit();
//        this.dataManager.register(ELEMENT_TYPE, Integer.valueOf(0));
//    }
//
//    @Override
//    public boolean attackEntityFrom(DamageSource damageSource, float damage) {
//        PlayerEntity player = (PlayerEntity)damageSource.getTrueSource();
//
//        if (damageSource.getImmediateSource() instanceof PlayerEntity)
//        {
//            if(getElementType() == 1) {
//                long time = System.currentTimeMillis();
//                if (time > nextAbilityUse && damageSource.getTrueSource() != null && !(damageSource instanceof EntityDamageSourceIndirect)) {
//                    nextAbilityUse = time + coolDown;
//
//                    if (rand.nextInt(10) == 0){
//                        assert player != null;
//                        player.addPotionEffect(new PotionEffect(PotionInit.ELEMENTAL_TORNADO, 120, 3));}
//                    else {
//                        assert player != null;
//                        player.addPotionEffect(new PotionEffect(PotionInit.ELEMENTAL_LIGHTNING, 45, 1));
//                    }
//                }
//            }
//            else if(getElementType() == 2) {
//                long time = System.currentTimeMillis();
//                if (time > nextAbilityUse && damageSource.getTrueSource() != null && !(damageSource instanceof EntityDamageSourceIndirect)) {
//                    nextAbilityUse = time + coolDown;
//
//                    if (rand.nextInt(10) == 0){
//                        assert player != null;
//                        player.addPotionEffect(new PotionEffect(PotionInit.ELEMENTAL_FLYING,40, 3));
//                    }
//                    else {
//                        assert player != null;
//                        player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200, 3));
//                    }
//                }
//            }
//            else if(getElementType() == 3) {
//                long time = System.currentTimeMillis();
//                if (time > nextAbilityUse && damageSource.getTrueSource() != null && !(damageSource instanceof EntityDamageSourceIndirect)) {
//                    nextAbilityUse = time + coolDown;
//
//                    if (rand.nextInt(10) == 0){
//                        assert player != null;
//                        player.addPotionEffect(new PotionEffect(PotionInit.ELEMENTAL_BURNING, 200, 3));
//                    }
//                    else {
//                        assert player != null;
//                        player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 200, 3));
//                    }
//                }
//            }
//            else if(getElementType() == 4) {
//                long time = System.currentTimeMillis();
//                if (time > nextAbilityUse && damageSource.getTrueSource() != null && !(damageSource instanceof EntityDamageSourceIndirect)) {
//                    nextAbilityUse = time + coolDown;
//
//                    if (rand.nextInt(10) == 0){
//                        assert player != null;
//                        player.addPotionEffect(new PotionEffect(PotionInit.ELEMENTAL_DROWNING, 600));
//                    }
//                    else {
//                        assert player != null;
//                        player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 200, 3));
//                    }
//                }
//            }
//        }
//        return super.attackEntityFrom(damageSource, damage);
//    }
//
//    public int getElementType()
//    {
//        return ((Integer)this.dataManager.get(ELEMENT_TYPE)).intValue();
//    }
//
//    public void setElementType(int elementTypeId)
//    {
//        if (elementTypeId == 1)
//        {
//            this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(15.0D);
//            this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
//
//            if (!this.hasCustomName())
//            {
//                this.setCustomNameTag(I18n.translateToLocal("entity.elementalgolem1.name"));
//            }
//        }
//        else if (elementTypeId == 2)
//        {
//            this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(15.0D);
//            this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
//
//            if (!this.hasCustomName())
//            {
//                this.setCustomNameTag(I18n.translateToLocal("entity.elementalgolem2.name"));
//            }
//        }
//        else if (elementTypeId == 3)
//        {
//            this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(15.0D);
//            this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
//
//            if (!this.hasCustomName())
//            {
//                this.setCustomNameTag(I18n.translateToLocal("entity.elementalgolem3.name"));
//            }
//        }
//        else if (elementTypeId == 4)
//        {
//            this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(15.0D);
//            this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
//
//            if (!this.hasCustomName())
//            {
//                this.setCustomNameTag(I18n.translateToLocal("entity.elementalgolem4.name"));
//            }
//        }
//        else
//        {
//            this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(15.0D);
//            this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
//
//            if (!this.hasCustomName())
//            {
//                this.setCustomNameTag(I18n.translateToLocal("entity.elementalgolem5.name"));
//            }
//        }
//
//        this.dataManager.set(ELEMENT_TYPE, Integer.valueOf(elementTypeId));
//    }
//
//    @Nullable
//    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
//        IEntityLivingData ientitylivingdata = super.onInitialSpawn(difficulty, livingdata);
//        this.setEquipmentBasedOnDifficulty(difficulty);
//        int i = this.getRandomElementType();
//
//        if (ientitylivingdata instanceof EntityTMElementalGolem.ElementTypeData)
//        {
//            i = ((EntityTMElementalGolem.ElementTypeData)livingdata).typeData;
//        }
//        else
//        {
//            ientitylivingdata = new EntityTMElementalGolem.ElementTypeData(i);
//        }
//
//        this.setElementType(i);
//        this.setCombatTask();
//        return ientitylivingdata;
//    }
//
//    public static class ElementTypeData implements IEntityLivingData
//    {
//        public int typeData;
//
//        public ElementTypeData(int type)
//        {
//            this.typeData = type;
//        }
//    }
//
//    private int getRandomElementType()
//    {
//        int i = TTMRand.getRandomInteger(6, 1);
//        return i;
//    }
//
//    @Override
//    public double getAttackDamage() {
//        return 15;
//    }
//
//    @Override
//    public double getArmorStrength() {
//        return 20;
//    }
//
//    @Override
//    public double getHealthLevel() {
//        return 100;
//    }
//
//    @Override
//    protected SoundEvent getDeathSound()
//    {
//        return SoundInit.soundDeathGolem;
//    }
//
//    @Override
//    protected void playStepSound(BlockPos pos, Block blockIn)
//    {
//        this.playSound(SoundInit.soundStepTroll, 0.25F, 1.0F);
//    }
//
//    @Override
//    public void writeEntityToNBT(NBTTagCompound compound)
//    {
//        super.writeEntityToNBT(compound);
//        compound.setInteger("ElementType", this.getElementType());
//    }
//
//    @Override
//    public void readEntityFromNBT(NBTTagCompound compound)
//    {
//        super.readEntityFromNBT(compound);
//        this.setElementType(compound.getInteger("ElementType"));
//    }
//
//    public void setMob(LivingEntity mob) {
//        this.mob = mob;
//    }
//
//    @Override
//    public boolean getCanSpawnHere() {
//        boolean nearMaterial = false;
//        int i = (int) Math.floor(posX);
//        int j = (int) Math.floor(posY);
//        int k = (int) Math.floor(posZ);
//        int willSpawn = TTMSpawnEvent.spawnChance();
//        BlockPos blockpos = new BlockPos(i, j, k);
//        Block block = this.world.getBlockState(blockpos.down()).getBlock();
//
//        if (getElementType() == 1)
//        {
//            if(this.posY > 145.0D && this.world.canSeeSky(new BlockPos(this))) {
//                if (willSpawn <= 10) {
//                    nearMaterial = true;
//                }
//            }
//            return super.getCanSpawnHere() && nearMaterial;
//        }
//        else if (getElementType() == 2)
//        {
//            for (int i1 = i - 16; i1 <= i + 16; i1++) {
//                for (int j1 = j - 6; j1 <= j + 6; j1++) {
//                    for (int k1 = k - 16; k1 <= k + 16; k1++) {
//                        BlockPos pos = new BlockPos(i1, j1, k1);
//                        IBlockState iblockstate = world.getBlockState(blockpos);
//
//                        if (iblockstate.getValue(BlockDirt.VARIANT) == BlockDirt.DirtType.PODZOL && this.world.canSeeSky(new BlockPos(this)) && this.posY > 36.0D) {
//                            if (willSpawn <= 10) {
//                                nearMaterial = true;
//                            }
//                        }
//                    }
//                }
//            }
//            return super.getCanSpawnHere() && nearMaterial;
//        }
//        else if (getElementType() == 3)
//        {
//            for (int i1 = i - 16; i1 <= i + 16; i1++) {
//                for (int j1 = j - 6; j1 <= j + 6; j1++) {
//                    for (int k1 = k - 16; k1 <= k + 16; k1++) {
//                        BlockPos pos = new BlockPos(i1, j1, k1);
//                        if (world.getBlockState(pos).getMaterial() == Material.LAVA && this.world.canSeeSky(new BlockPos(this)) && this.posY > 36.0D) {
//                            if (willSpawn <= 10) {
//                                nearMaterial = true;
//                            }
//                        }
//                    }
//                }
//            }
//            return super.getCanSpawnHere() && nearMaterial;
//        }
//        else if (getElementType() == 4)
//        {
//            for (int i1 = i - 16; i1 <= i + 16; i1++) {
//                for (int j1 = j - 6; j1 <= j + 6; j1++) {
//                    for (int k1 = k - 16; k1 <= k + 16; k1++) {
//                        BlockPos pos = new BlockPos(i1, j1, k1);
//                        if (world.getBlockState(pos).getMaterial() == Material.WATER && this.world.canSeeSky(new BlockPos(this)) && this.posY > 36.0D) {
//                            if (willSpawn <= 10) {
//                                nearMaterial = true;
//                            }
//                        }
//                    }
//                }
//            }
//            return super.getCanSpawnHere() && nearMaterial;
//        }
//        else
//        {
//            if(this.posY > 36.0D && this.world.canSeeSky(new BlockPos(this))) {
//                if (willSpawn <= 10) {
//                    nearMaterial = true;
//                }
//            }
//            return super.getCanSpawnHere() && nearMaterial;
//        }
//    }
//
//    @Override
//    @Nullable
//    protected ResourceLocation getLootTable() {
//        if (getElementType() == 1){
//            return LootInit.GOLEM_STONE_AIR;
//        }
//        else if (getElementType() == 2){
//            return LootInit.GOLEM_STONE_EARTH;
//        }
//        else if (getElementType() == 3){
//            return LootInit.GOLEM_STONE_FIRE;
//        }
//        else if (getElementType() == 4){
//            return LootInit.GOLEM_STONE_WATER;
//        }
//        else {
//            return LootInit.GOLEM_STONE;
//        }
//    }
}
