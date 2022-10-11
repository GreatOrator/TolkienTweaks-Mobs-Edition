package com.greatorator.tolkienmobs.entity.monster;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.MonsterEntity;
import com.greatorator.tolkienmobs.entity.ai.goal.TTMSwitchCombat;
import com.greatorator.tolkienmobs.entity.ai.goal.TTMThrowandAttack;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShootableItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Map;

public class HaradrimEntity extends MonsterEntity {
    private final RangedBowAttackGoal<HaradrimEntity> bowGoal = new RangedBowAttackGoal<>(this, 1.0D, 20, 15.0F);
    private final MeleeAttackGoal meleeGoal = new HaradrimEntity.HaradrimAttackGoal(this, 1.0D, 20, 15.0F, false);
    private boolean scheduleWeaponGoalUpdate = true;

    private static final DataParameter<Integer> HARADRIM_TYPE = EntityDataManager.defineId(HaradrimEntity.class, DataSerializers.INT);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmharadrim/haradrim0.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmharadrim/haradrim1.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmharadrim/haradrim2.png"));
        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmharadrim/haradrim3.png"));
        option.put(5, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmharadrim/haradrim4.png"));
        option.put(6, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmharadrim/haradrim5.png"));
        option.put(7, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmharadrim/haradrim6.png"));
        option.put(8, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmharadrim/haradrim7.png"));
        option.put(9, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmharadrim/haradrim8.png"));
        option.put(10, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmharadrim/haradrim9.png"));
    });

    private long nextAbilityUse = 0L;
    private final static long coolDown = 15000L;

    public HaradrimEntity(EntityType<? extends net.minecraft.entity.monster.MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        reassessWeaponGoal();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(3, new TTMSwitchCombat(this, 6.0D, 6.0D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, VillagerEntity.class, true));
        super.registerGoals();
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return net.minecraft.entity.monster.MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 26.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.ARMOR, 6.0D);
    }

    /** Special Attack */
    @Override
    public boolean doHurtTarget(Entity entityIn) {
        long time = System.currentTimeMillis();
        if (super.doHurtTarget(entityIn)) {
            if (entityIn instanceof PlayerEntity) {
                if (time > nextAbilityUse) {
                    nextAbilityUse = time + coolDown;
                    PlayerEntity player = (PlayerEntity) entityIn;
                    BlockPos blockpos = player.blockPosition();
                    ItemEntity dropItem = player.drop(player.inventory.removeItem(player.inventory.selected, 1), false);
                    if (dropItem != null) {
                        dropItem.setPickUpDelay(50);
                        level.playSound(null, blockpos, SoundEvents.SLIME_ATTACK, SoundCategory.HOSTILE, 1.0F + level.random.nextFloat(), level.random.nextFloat() * 0.7F + 0.3F);
                    }
                }
            }
        }
        return true;
    }

    /** Set up using weapons **/
    @Override
    protected void populateDefaultEquipmentSlots(DifficultyInstance p_180481_1_) {
        super.populateDefaultEquipmentSlots(p_180481_1_);
        this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_SWORD));
        this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.BOW));
    }

    @Override
    public boolean canFireProjectileWeapon(ShootableItem p_230280_1_) {
        return true;
    }

    @Override
    public void performRangedAttack(LivingEntity p_82196_1_, float p_82196_2_) {
        ItemStack itemstack = this.getProjectile(this.getItemInHand(ProjectileHelper.getWeaponHoldingHand(this, Items.BOW)));
        AbstractArrowEntity abstractarrowentity = this.getArrow(itemstack, p_82196_2_);
        if (this.getMainHandItem().getItem() instanceof net.minecraft.item.BowItem)
            abstractarrowentity = ((net.minecraft.item.BowItem)this.getMainHandItem().getItem()).customArrow(abstractarrowentity);
        double d0 = p_82196_1_.getX() - this.getX();
        double d1 = p_82196_1_.getY(0.3333333333333333D) - abstractarrowentity.getY();
        double d2 = p_82196_1_.getZ() - this.getZ();
        double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
        abstractarrowentity.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.6F, (float)(14 - this.level.getDifficulty().getId() * 4));
        this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(abstractarrowentity);
    }

    static class HaradrimAttackGoal extends TTMThrowandAttack {
        private final HaradrimEntity haradrim;
        private int raiseArmTicks;

        HaradrimAttackGoal(HaradrimEntity haradrimEntity, double speedAmplifier, int attackInterval, float maxDistance, boolean useLongMemory) {
            super(haradrimEntity, speedAmplifier, attackInterval, maxDistance, useLongMemory);
            this.haradrim = haradrimEntity;
        }

        @Override
        public void start() {
            super.start();
            this.raiseArmTicks = 0;
        }

        @Override
        public void stop() {
            super.stop();
            this.haradrim.setAggressive(false);
        }

        @Override
        public void tick() {
            super.tick();
            ++this.raiseArmTicks;
            if (this.raiseArmTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2) {
                this.haradrim.setAggressive(true);
            } else {
                this.haradrim.setAggressive(false);
            }

        }
    }

    protected AbstractArrowEntity getArrow(ItemStack p_213624_1_, float p_213624_2_) {
        return ProjectileHelper.getMobArrow(this, p_213624_1_, p_213624_2_);
    }
    /** End Region **/

    /**
     * Region for determining random skin
     */
    public ResourceLocation getHaradrimTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getHaradrimType(), TEXTURE_BY_ID.get(1));
    }

    public int getHaradrimType() {
        return this.entityData.get(HARADRIM_TYPE);
    }

    public void setHaradrimType(int type) {
        if (type < 0 || type >= 11) {
            type = this.random.nextInt(10);
        }

        this.entityData.set(HARADRIM_TYPE, type);
    }

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(10, 1);
        this.setHaradrimType(job);
        this.populateDefaultEquipmentSlots(difficultyIn);
        this.reassessWeaponGoal();

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HARADRIM_TYPE, 3);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("HaradrimType", this.getHaradrimType());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setHaradrimType(compound.getInt("HaradrimType"));
        reassessWeaponGoal();
    }

    public void reassessWeaponGoal() {
        if (this.level != null && !this.level.isClientSide) {
            scheduleWeaponGoalUpdate = true;
        }
    }

    @Override
    public void tick() {
        if (scheduleWeaponGoalUpdate) {
            updateWeaponGoal();
        }
        super.tick();
    }

    protected void updateWeaponGoal() {
        scheduleWeaponGoalUpdate = false;
        this.goalSelector.removeGoal(this.meleeGoal);
        this.goalSelector.removeGoal(this.bowGoal);
        ItemStack itemstack = this.getMainHandItem();
        if (itemstack.getItem() == Items.BOW) {
            int i = 20;
            if (this.level.getDifficulty() != Difficulty.HARD) {
                i = 40;
            }

            this.bowGoal.setMinAttackInterval(i);
            this.goalSelector.addGoal(4, this.bowGoal);
        } else {
            this.goalSelector.addGoal(4, this.meleeGoal);
        }
    }
}