package com.greatorator.tolkienmobs.entity.monster;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.SoundGenerator;
import com.greatorator.tolkienmobs.entity.MonsterEntity;
import com.greatorator.tolkienmobs.entity.ammo.EntityBoulder;
import com.greatorator.tolkienmobs.entity.boss.GoblinKingEntity;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class TreeEntEntity extends MonsterEntity implements IAngerable{
    private static final DataParameter<Integer> TREEENT_TYPE = EntityDataManager.defineId(TreeEntEntity.class, DataSerializers.INT);
    private static final RangedInteger PERSISTENT_ANGER_TIME = TickRangeConverter.rangeOfSeconds(20, 39);
    private int attackAnimationTick;
    private UUID persistentAngerTarget;
    private int remainingPersistentAngerTime;
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/treeent/treeent1.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/treeent/treeent2.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/treeent/treeent3.png"));
        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/treeent/treeent4.png"));
    });

    public TreeEntEntity(EntityType<? extends net.minecraft.entity.monster.MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        this.maxUpStep = 1.0F;
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return net.minecraft.entity.monster.MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 45.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.ARMOR, 9.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, GoblinEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, MordorOrcEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, GoblinKingEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, UrukHaiEntity.class, true));
        this.targetSelector.addGoal(4, new ResetAngerGoal<>(this, false));
    }

    public static boolean checkTreeEntSpawn(EntityType<TreeEntEntity> type, IWorld world, SpawnReason reason, BlockPos pos, Random random) {
        int chance = 200; //1 in x
        return random.nextInt(chance) == 0 && checkMobSpawnRules(type, world, reason, pos, random);
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        EntityBoulder entityboulder = new EntityBoulder(this.level, this);
        if (!this.isSilent()) {
            this.level.levelEvent((PlayerEntity)null, 1024, this.blockPosition(), 0);
        }

        double d0 = target.position().y + (double)target.getEyeHeight() - 1.100000023841858D;
        double d1 = target.position().x - this.position().x;
        double d2 = d0 - entityboulder.position().y;
        double d3 = target.position().z - this.position().z;
        float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
        entityboulder.shoot(d1, d2 + (double)f, d3, 1.6F, 12.0F);
        entityboulder.setOwner(this);

        entityboulder.setPosRaw(d0, d1, d2);
        this.playSound(SoundGenerator.sound_Boulder_Shoot.get(), 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(entityboulder);
    }

    @Override
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

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.randomValue(this.random));
    }

    @Override
    public void setRemainingPersistentAngerTime(int p_230260_1_) {
        this.remainingPersistentAngerTime = p_230260_1_;
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return this.remainingPersistentAngerTime;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID p_230259_1_) {
        this.persistentAngerTarget = p_230259_1_;
    }

    @Override
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    private float getAttackDamage() {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    @Override
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

        this.playSound(SoundGenerator.sound_Boulder_Shoot.get(), 1.0F, 1.0F);
        return flag;
    }

    @Nullable
    @Override
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    @Override
    public boolean canBeControlledByRider() {
        Entity entity = this.getControllingPassenger();
        if (!(entity instanceof PlayerEntity)) {
            return false;
        } else {
            PlayerEntity playerentity = (PlayerEntity)entity;
            return playerentity.getMainHandItem().getItem() == Items.CARROT_ON_A_STICK || playerentity.getOffhandItem().getItem() == Items.CARROT_ON_A_STICK;
        }
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        if (!this.level.isClientSide) {
            p_230254_1_.startRiding(this);
        }

        return ActionResultType.sidedSuccess(this.level.isClientSide);
    }

    @Override
    public double getPassengersRidingOffset()
    {
        return super.getPassengersRidingOffset() + 1.0D;
    }

    @Override
    public Vector3d getDismountLocationForPassenger(LivingEntity p_230268_1_) {
        Direction direction = this.getMotionDirection();
        if (direction.getAxis() == Direction.Axis.Y) {
            return super.getDismountLocationForPassenger(p_230268_1_);
        } else {
            int[][] aint = TransportationHelper.offsetsForDirection(direction);
            BlockPos blockpos = this.blockPosition();
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

            for(Pose pose : p_230268_1_.getDismountPoses()) {
                AxisAlignedBB axisalignedbb = p_230268_1_.getLocalBoundsForPose(pose);

                for(int[] aint1 : aint) {
                    blockpos$mutable.set(blockpos.getX() + aint1[0], blockpos.getY(), blockpos.getZ() + aint1[1]);
                    double d0 = this.level.getBlockFloorHeight(blockpos$mutable);
                    if (TransportationHelper.isBlockFloorValid(d0)) {
                        Vector3d vector3d = Vector3d.upFromBottomCenterOf(blockpos$mutable, d0);
                        if (TransportationHelper.canDismountTo(this.level, p_230268_1_, axisalignedbb.move(vector3d))) {
                            p_230268_1_.setPose(pose);
                            return vector3d;
                        }
                    }
                }
            }

            return super.getDismountLocationForPassenger(p_230268_1_);
        }
    }


    @OnlyIn(Dist.CLIENT)
    @Override
    public void handleEntityEvent(byte p_70103_1_) {
        if (p_70103_1_ == 4) {
            this.attackAnimationTick = 10;
            this.playSound(SoundGenerator.sound_Boulder_Shoot.get(), 1.0F, 1.0F);
        } else {
            super.handleEntityEvent(p_70103_1_);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public int getAttackAnimationTick() {
        return this.attackAnimationTick;
    }

    /**
     * Region for determining random skin
     */
    public ResourceLocation getTreeEntTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getTreeEntType(), TEXTURE_BY_ID.get(1));
    }

    public int getTreeEntType() {
        return this.entityData.get(TREEENT_TYPE);
    }

    public void setTreeEntType(int type) {
        if (type < 0 || type >= 5) {
            type = this.random.nextInt(4);
        }

        this.entityData.set(TREEENT_TYPE, type);
    }

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(5, 1);
        this.setTreeEntType(job);

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TREEENT_TYPE, 3);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("TreeEntType", this.getTreeEntType());
        this.addPersistentAngerSaveData(compound);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setTreeEntType(compound.getInt("TreeEntType"));
        this.readPersistentAngerSaveData((ServerWorld)this.level, compound);
    }
}