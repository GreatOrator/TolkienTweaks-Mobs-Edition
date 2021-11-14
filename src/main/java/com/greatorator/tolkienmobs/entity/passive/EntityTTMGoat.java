package com.greatorator.tolkienmobs.entity.passive;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.EntityGenerator;
import com.greatorator.tolkienmobs.datagen.SoundGenerator;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.block.SoundType;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.horse.AbstractChestedHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Map;

public class EntityTTMGoat extends AbstractChestedHorseEntity {
    private static final DataParameter<Boolean> DATA_ID_CHEST = EntityDataManager.defineId(EntityTTMGoat.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> GOAT_TYPE = EntityDataManager.defineId(EntityTTMGoat.class, DataSerializers.INT);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(0, new ResourceLocation(TolkienMobs.MODID, "textures/entity/goat/goat1.png"));
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/goat/goat2.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/goat/goat3.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/goat/goat4.png"));
    });

    public EntityTTMGoat(EntityType<? extends AbstractChestedHorseEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.JUMP_STRENGTH, 2.5D).add(Attributes.ATTACK_DAMAGE, 8.0D).add(Attributes.ARMOR, 4.0D).add(Attributes.KNOCKBACK_RESISTANCE, 1.0D).add(Attributes.MOVEMENT_SPEED, 0.8D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.2D));
        this.goalSelector.addGoal(1, new RunAroundLikeCrazyGoal(this, 1.2D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D, EntityTTMGoat.class));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.7D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.addBehaviourGoals();
    }

    @Override
    public double getPassengersRidingOffset()
    {
        return super.getPassengersRidingOffset() - 0.025D;
    }

    @Override
    protected void playGallopSound(SoundType p_190680_1_)
    {
        super.playGallopSound(p_190680_1_);

        if (this.random.nextInt(10) == 0)
        {
            this.playSound(SoundEvents.HORSE_BREATHE, p_190680_1_.getVolume() * 0.6F, p_190680_1_.getPitch());
        }
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        super.getAmbientSound();
        return SoundGenerator.soundIdleGoat.get();
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        super.getDeathSound();
        return SoundGenerator.soundDeathGoat.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        super.getHurtSound(damageSourceIn);
        return SoundGenerator.soundHurtGoat.get();
    }

    @Override
    protected SoundEvent getAngrySound()
    {
        super.getAngrySound();
        return SoundGenerator.soundAngryGoat.get();
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 4;
    }

    protected boolean isValidLightLevel()
    {
        return true;
    }

    @Override
    public EntityTTMGoat getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return EntityGenerator.ENTITY_TTM_GOAT.get().create(p_241840_1_);
    }

    /** Region for determining random skin */
    public ResourceLocation getGoatTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getGoatType(), TEXTURE_BY_ID.get(1));
    }

    public int getGoatType() {
        return this.entityData.get(GOAT_TYPE);
    }

    public void setGoatType(int type) {
        if (type < 0 || type >= 5) {
            type = this.random.nextInt(4);
        }

        this.entityData.set(GOAT_TYPE, type);
    }

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(1, 4);
        this.setGoatType(job);

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(GOAT_TYPE, 1);
        this.entityData.define(DATA_ID_CHEST, false);
    }

    @Override
    public boolean hasChest() {
        return this.entityData.get(DATA_ID_CHEST);
    }

    @Override
    public void setChest(boolean chested) {
        this.entityData.set(DATA_ID_CHEST, chested);
    }

    @Override
    protected int getInventorySize() {
        return this.hasChest() ? 17 : super.getInventorySize();
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("GoatType", this.getGoatType());
        compound.putBoolean("ChestedGoat", this.hasChest());
        if (this.hasChest()) {
            ListNBT listnbt = new ListNBT();

            for(int i = 2; i < this.inventory.getContainerSize(); ++i) {
                ItemStack itemstack = this.inventory.getItem(i);
                if (!itemstack.isEmpty()) {
                    CompoundNBT compoundnbt = new CompoundNBT();
                    compoundnbt.putByte("Slot", (byte)i);
                    itemstack.save(compoundnbt);
                    listnbt.add(compoundnbt);
                }
            }

            compound.put("Items", listnbt);
        }
        if (!this.inventory.getItem(0).isEmpty()) {
            compound.put("SaddleItem", this.inventory.getItem(0).save(new CompoundNBT()));
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setGoatType(compound.getInt("GoatType"));
        this.setChest(compound.getBoolean("ChestedGoat"));
        if (this.hasChest()) {
            ListNBT listnbt = compound.getList("Items", 10);
            this.createInventory();

            for(int i = 0; i < listnbt.size(); ++i) {
                CompoundNBT compoundnbt = listnbt.getCompound(i);
                int j = compoundnbt.getByte("Slot") & 255;
                if (j >= 2 && j < this.inventory.getContainerSize()) {
                    this.inventory.setItem(j, ItemStack.of(compoundnbt));
                }
            }
        }
        if (compound.contains("SaddleItem", 10)) {
            ItemStack itemstack = ItemStack.of(compound.getCompound("SaddleItem"));
            if (itemstack.getItem() == Items.SADDLE) {
                this.inventory.setItem(0, itemstack);
            }
        }
    }
}