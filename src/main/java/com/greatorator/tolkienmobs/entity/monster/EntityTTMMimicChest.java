package com.greatorator.tolkienmobs.entity.monster;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.PotionGenerator;
import com.greatorator.tolkienmobs.entity.EntityTTMMonsters;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Map;

public class EntityTTMMimicChest extends EntityTTMMonsters {
    private static final DataParameter<Integer> MIMIC_TYPE = EntityDataManager.defineId(EntityTTMMimicChest.class, DataSerializers.INT);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/mimicchest/mimicchest1.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/mimicchest/mimicchest2.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/mimicchest/mimicchest1.png"));
        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/mimicchest/mimicchest2.png"));
    });
    private long nextAbilityUse = 0L;
    private final static long coolDown = 15000L;

    public EntityTTMMimicChest(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.ARMOR, 5.0D);
    }

    /** Special Attack */
    public boolean doHurtTarget(Entity entityIn) {
        long time = System.currentTimeMillis();
        if (super.doHurtTarget(entityIn)) {
            if (entityIn instanceof PlayerEntity) {
                if (time > nextAbilityUse) {
                    nextAbilityUse = time + coolDown;
                    PlayerEntity player = (PlayerEntity) entityIn;
                    int strength = 2;
                    player.addEffect(new EffectInstance(PotionGenerator.INVENTORY_CORROSION.get(), strength * 20, 0));
                }
            }
        }
        return true;
    }

    /**
     * Region for determining random skin
     */
    public ResourceLocation getMimicChestTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getMimicChestType(), TEXTURE_BY_ID.get(1));
    }

    public int getMimicChestType() {
        return this.entityData.get(MIMIC_TYPE);
    }

    public void setMimicChestType(int type) {
        if (type < 0 || type >= 5) {
            type = this.random.nextInt(4);
        }

        this.entityData.set(MIMIC_TYPE, type);
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(5, 1);
        this.setMimicChestType(job);
        this.populateDefaultEquipmentSlots(difficultyIn);

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(MIMIC_TYPE, 3);
    }

    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("MimicChestType", this.getMimicChestType());
    }

    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setMimicChestType(compound.getInt("MimicChestType"));
    }
}
//    private static final UUID ATTACK_SPEED_BOOST_MODIFIER_UUID = UUID.fromString("763926b2-45ba-11e9-b210-d663bd873d93");
//    private static final AttributeModifier ATTACK_SPEED_BOOST_MODIFIER = (new AttributeModifier(ATTACK_SPEED_BOOST_MODIFIER_UUID, "Attacking speed boost", 0.05D, 0)).setSaved(false);
//    /** Above zero if this Mimic is Angry. */
//    private int angerLevel;
//    private long nextAbilityUse = 0L;
//    private final static long coolDown = 15000L;
//    /** A random delay until this Mimic next makes a sound. */
//    private int randomSoundDelay;
//    private UUID angerTargetUUID;
//
//    public EntityTMMimicChest(World world)
//    {
//        super(world);
//
//        this.setSize(1.0F, 1.7F);
//        this.setLootTable(LootInit.MIMICCHEST);
//        this.setRndMinMax(1,5);
//    }
//
//    @Override
//    public boolean getCanSpawnHere() {
//        int willSpawn = TTMSpawnEvent.spawnChance();
//
//        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && this.posY < 64.0D && !this.world.canSeeSky(new BlockPos(this)) && willSpawn <= 10;
//    }
//

//
//    @Override
//    protected void initEntityAI()
//    {
//        this.applyEntityAI();
//    }
//
//    @Override
//    protected void applyEntityAttributes()
//    {
//        super.applyEntityAttributes();
//        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(8.0D);
//        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28000000417232513D);
//        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
//        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
//    }
//
//    protected void applyEntityAI()
//    {
//        this.targetTasks.addTask(1, new EntityTMMimicChest.AIHurtByAggressor(this));
//        this.targetTasks.addTask(2, new EntityTMMimicChest.AITargetAggressor(this));
//    }
//

//
//    @Override
//    public double getAttackDamage() {
//        return 3.0D;
//    }
//
//    @Override
//    public double getArmorStrength() {
//        return 0;
//    }
//
//    @Override
//    public double getHealthLevel() {
//        return 40.0D;
//    }
//
//    @Override
//    public void onUpdate()
//    {
//        super.onUpdate();
//    }
//

//
//    @Override
//    protected void playStepSound(BlockPos pos, Block blockIn)
//    {
//        this.playSound(SoundInit.soundStepMimic, 0.25F, 1.0F);
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource source)
//    {
//        return SoundEvents.BLOCK_WOOD_HIT;
//    }
//
//    @Override
//    protected SoundEvent getDeathSound()
//    {
//        return SoundEvents.BLOCK_CHEST_CLOSE;
//    }
//
//    public void writeEntityToNBT(NBTTagCompound compound)
//    {
//        super.writeEntityToNBT(compound);
//        compound.setInteger("SkinType", this.getMobType());
//        compound.setShort("Anger", (short)this.angerLevel);
//
//        if (this.angerTargetUUID != null)
//        {
//            compound.setString("HurtBy", this.angerTargetUUID.toString());
//        }
//        else
//        {
//            compound.setString("HurtBy", "");
//        }
//    }
//
//    public void readEntityFromNBT(NBTTagCompound compound)
//    {
//        super.readEntityFromNBT(compound);
//        this.setMobType(compound.getInteger("SkinType"));
//        this.angerLevel = compound.getShort("Anger");
//        String s = compound.getString("HurtBy");
//
//        if (!s.isEmpty())
//        {
//            this.angerTargetUUID = UUID.fromString(s);
//            PlayerEntity entityplayer = this.world.getPlayerEntityByUUID(this.angerTargetUUID);
//            this.setRevengeTarget(entityplayer);
//
//            if (entityplayer != null)
//            {
//                this.attackingPlayer = entityplayer;
//                this.recentlyHit = this.getRevengeTimer();
//            }
//        }
//    }
//}