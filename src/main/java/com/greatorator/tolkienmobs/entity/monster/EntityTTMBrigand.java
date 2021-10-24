package com.greatorator.tolkienmobs.entity.monster;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.EntityTTMMonsters;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
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

    @Override
    protected void populateDefaultEquipmentSlots(DifficultyInstance p_180481_1_) {
        this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(TTMContent.SWORD_MORGULIRON.get()));
    }

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
        if (type < 0 || type >= 5) {
            type = this.random.nextInt(4);
        }

        this.entityData.set(BRIGAND_TYPE, type);
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(1, 10);
        this.setBrigandType(job);
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