package com.greatorator.tolkienmobs.entity.monster;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.EntityTTMMonsters;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Map;

public class EntityTTMBrigand extends EntityTTMMonsters {
    private static final DataParameter<Integer> BRIGAND_TYPE = EntityDataManager.defineId(EntityTTMBrigand.class, DataSerializers.INT);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmbrigand/brigand0.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmbrigand/brigand1.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmbrigand/brigand2.png"));
        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmbrigand/brigand3.png"));
        option.put(5, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmbrigand/brigand4.png"));
        option.put(6, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmbrigand/brigand5.png"));
        option.put(7, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmbrigand/brigand6.png"));
        option.put(8, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmbrigand/brigand7.png"));
        option.put(9, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmbrigand/brigand8.png"));
        option.put(10, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmbrigand/brigand9.png"));
    });

    /** Set up using weapons **/
    private final MeleeAttackGoal meleeGoal = new MeleeAttackGoal(this, 1.2D, false) {
        public void stop() {
            super.stop();
            EntityTTMBrigand.this.setAggressive(false);
        }

        public void start() {
            super.start();
            EntityTTMBrigand.this.setAggressive(true);
        }
    };
    /** End Region **/

    private long nextAbilityUse = 0L;
    private final static long coolDown = 15000L;

    public EntityTTMBrigand(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.ARMOR, 5.0D);
    }

    /** Set up using weapons **/
    @Override
    protected void populateDefaultEquipmentSlots(DifficultyInstance p_180481_1_) {
        super.populateDefaultEquipmentSlots(p_180481_1_);
        this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_SWORD));
    }

    @Override
    public void reassessWeaponGoal() {
        if (this.level != null && !this.level.isClientSide) {
            this.goalSelector.removeGoal(this.meleeGoal);
            ItemStack itemstack = this.getItemInHand(ProjectileHelper.getWeaponHoldingHand(this, Items.IRON_SWORD));
            if (itemstack.getItem() == Items.IRON_SWORD) {
                this.goalSelector.addGoal(4, this.meleeGoal);
            }
        }
    }

    @Override
    public void setItemSlot(EquipmentSlotType p_184201_1_, ItemStack p_184201_2_) {
        super.setItemSlot(p_184201_1_, p_184201_2_);
        if (!this.level.isClientSide) {
            this.reassessWeaponGoal();
        }

    }
    /** End Region **/

    @Override
    public CreatureAttribute getMobType()
    {
        return CreatureAttribute.ILLAGER;
    }

    /**
     * Region for determining random skin
     */
    public ResourceLocation getBrigandTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getBrigandType(), TEXTURE_BY_ID.get(0));
    }

    public int getBrigandType() {
        return this.entityData.get(BRIGAND_TYPE);
    }

    public void setBrigandType(int type) {
        if (type < 0 || type >= 11) {
            type = this.random.nextInt(10);
        }

        this.entityData.set(BRIGAND_TYPE, type);
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(10, 1);
        this.setBrigandType(job);
        this.populateDefaultEquipmentSlots(difficultyIn);
        this.reassessWeaponGoal();

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BRIGAND_TYPE, 3);
    }

    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("BrigandType", this.getBrigandType());
    }

    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setBrigandType(compound.getInt("BrigandType"));
        this.reassessWeaponGoal();
    }

//    @Override
//    public boolean doHurtTarget(Entity entiyIn) {
//        if (!super.doHurtTarget(entiyIn)) {
//            return false;
//        } else {
//            if (entiyIn instanceof PlayerEntity) {
//                long time = System.currentTimeMillis();
//                nextAbilityUse = time + coolDown;
//                ItemEntity drop = entiyIn.dropItem(entiyIn.inventory.decrStackSize(entiyIn.inventory.currentItem, 1), false);
//
//                if (time > nextAbilityUse) {
//                    if (drop != null) {
//                        drop.setPickupDelay(50);
//                        entiyIn.world.playSound(null, entiyIn.getPosition(), SoundEvents.SLIME_ATTACK, SoundCategory.HOSTILE, 1.0F + entiyIn.getRNG().nextFloat(), entiyIn.getRNG().nextFloat() * 0.7F + 0.3F);
//                    }
//                }
//            }
//
//            return true;
//        }
//    }
//
//    @Override
//    public boolean attackEntityFrom(DamageSource damageSource, float damage) {
//        Entity damageSourceP = damageSource.getTrueSource();
//
//        if (damageSourceP != null && (damageSourceP instanceof PlayerEntity))
//        {
//            PlayerEntity p = (PlayerEntity)damageSourceP;
//            ItemStack w = p.inventory.getStackInSlot(p.inventory.currentItem);
//
//            long time = System.currentTimeMillis();
//            if(w != null) {
//                if (time > nextAbilityUse
//                        && damageSourceP != null
//                        && !(damageSource instanceof EntityDamageSourceIndirect)) {
//                    nextAbilityUse = time + coolDown;
//                    EntityItem drop = p.dropItem(p.inventory.decrStackSize(p.inventory.currentItem, 1), false);
//
//                    if (drop != null) {
//                        drop.setPickupDelay(50);
//                        mob.world.playSound(null, new BlockPos(mob), SoundEvents.ENTITY_SLIME_ATTACK, SoundCategory.HOSTILE, 1.0F + mob.getRNG().nextFloat(), mob.getRNG().nextFloat() * 0.7F + 0.3F);
//                    }
//                }
//            }
//        }
//        return super.attackEntityFrom(damageSource, damage);
//    }
}