package com.greatorator.tolkienmobs.entity.hostile;

import com.greatorator.tolkienmobs.entity.EntityTMHostiles;
import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.init.PotionInit;
import com.greatorator.tolkienmobs.init.SoundInit;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityTMElementalGolem extends EntityTMHostiles {
    private long nextAbilityUse = 0L;
    private final static long coolDown = 15000L;
    private EntityLivingBase mob;

    private static final DataParameter<Integer> ELEMENT_TYPE = EntityDataManager.<Integer>createKey(EntityTMElementalGolem.class, DataSerializers.VARINT);

    public EntityTMElementalGolem(World worldIn) {
        super(worldIn);
        this.setSize(1.8F, 3.1F);
        this.setMob(this);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(ELEMENT_TYPE, Integer.valueOf(0));
    }

    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage) {
        EntityPlayer player = (EntityPlayer)damageSource.getTrueSource();

        if (damageSource.getImmediateSource() instanceof EntityPlayer)
        {
            if(getElementType() == 1) {
                long time = System.currentTimeMillis();
                if (time > nextAbilityUse && damageSource.getTrueSource() != null && !(damageSource instanceof EntityDamageSourceIndirect)) {
                    nextAbilityUse = time + coolDown;

                    if (rand.nextInt(10) == 0){
                        assert player != null;
                        player.addPotionEffect(new PotionEffect(PotionInit.ELEMENTAL_TORNADO, 120, 3));}
                    else {
                        assert player != null;
                        player.addPotionEffect(new PotionEffect(PotionInit.ELEMENTAL_LIGHTNING, 45, 1));
                    }
                }
            }
            else if(getElementType() == 2) {
                long time = System.currentTimeMillis();
                if (time > nextAbilityUse && damageSource.getTrueSource() != null && !(damageSource instanceof EntityDamageSourceIndirect)) {
                    nextAbilityUse = time + coolDown;

                    if (rand.nextInt(10) == 0){
                        assert player != null;
                        player.addPotionEffect(new PotionEffect(PotionInit.ELEMENTAL_FLYING,40, 3));
                    }
                    else {
                        assert player != null;
                        player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200, 3));
                    }
                }
            }
            else if(getElementType() == 3) {
                long time = System.currentTimeMillis();
                if (time > nextAbilityUse && damageSource.getTrueSource() != null && !(damageSource instanceof EntityDamageSourceIndirect)) {
                    nextAbilityUse = time + coolDown;

                    if (rand.nextInt(10) == 0){
                        assert player != null;
                        player.addPotionEffect(new PotionEffect(PotionInit.ELEMENTAL_BURNING, 200, 3));
                    }
                    else {
                        assert player != null;
                        player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 200, 3));
                    }
                }
            }
            else if(getElementType() == 4) {
                long time = System.currentTimeMillis();
                if (time > nextAbilityUse && damageSource.getTrueSource() != null && !(damageSource instanceof EntityDamageSourceIndirect)) {
                    nextAbilityUse = time + coolDown;

                    if (rand.nextInt(10) == 0){
                        assert player != null;
                        player.addPotionEffect(new PotionEffect(PotionInit.ELEMENTAL_DROWNING, 600));
                    }
                    else {
                        assert player != null;
                        player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 200, 3));
                    }
                }
            }
        }
        return super.attackEntityFrom(damageSource, damage);
    }

    public int getElementType()
    {
        return ((Integer)this.dataManager.get(ELEMENT_TYPE)).intValue();
    }

    public void setElementType(int elementTypeId)
    {
        if (elementTypeId == 1)
        {
            this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(15.0D);
            this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));

            if (!this.hasCustomName())
            {
                this.setCustomNameTag(I18n.translateToLocal("entity.elementalgolem1.name"));
            }
        }
        else if (elementTypeId == 2)
        {
            this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(15.0D);
            this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));

            if (!this.hasCustomName())
            {
                this.setCustomNameTag(I18n.translateToLocal("entity.elementalgolem2.name"));
            }
        }
        else if (elementTypeId == 3)
        {
            this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(15.0D);
            this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));

            if (!this.hasCustomName())
            {
                this.setCustomNameTag(I18n.translateToLocal("entity.elementalgolem3.name"));
            }
        }
        else if (elementTypeId == 4)
        {
            this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(15.0D);
            this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));

            if (!this.hasCustomName())
            {
                this.setCustomNameTag(I18n.translateToLocal("entity.elementalgolem4.name"));
            }
        }
        else
        {
            this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(15.0D);
            this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));

            if (!this.hasCustomName())
            {
                this.setCustomNameTag(I18n.translateToLocal("entity.elementalgolem5.name"));
            }
        }

        this.dataManager.set(ELEMENT_TYPE, Integer.valueOf(elementTypeId));
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        IEntityLivingData ientitylivingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setEquipmentBasedOnDifficulty(difficulty);
        int i = this.getRandomElementType();

        if (ientitylivingdata instanceof EntityTMElementalGolem.ElementTypeData)
        {
            i = ((EntityTMElementalGolem.ElementTypeData)livingdata).typeData;
        }
        else
        {
            ientitylivingdata = new EntityTMElementalGolem.ElementTypeData(i);
        }

        this.setElementType(i);
        this.setCombatTask();
        return ientitylivingdata;
    }

    public static class ElementTypeData implements IEntityLivingData
    {
        public int typeData;

        public ElementTypeData(int type)
        {
            this.typeData = type;
        }
    }

    private int getRandomElementType()
    {
        int i = TTMRand.getRandomInteger(6, 1);
        return i;
    }

    @Override
    public double getAttackDamage() {
        return 15;
    }

    @Override
    public double getArmorStrength() {
        return 20;
    }

    @Override
    public double getHealthLevel() {
        return 100;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundInit.soundDeathGolem;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundInit.soundStepTroll, 0.25F, 1.0F);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("ElementType", this.getElementType());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setElementType(compound.getInteger("ElementType"));
    }

    public void setMob(EntityLivingBase mob) {
        this.mob = mob;
    }

    @Override
    public boolean getCanSpawnHere() {
        boolean nearMaterial = false;
        int i = (int) Math.floor(posX);
        int j = (int) Math.floor(posY);
        int k = (int) Math.floor(posZ);
        int willSpawn = this.spawnChance();
        BlockPos blockpos = new BlockPos(i, j, k);
        Block block = this.world.getBlockState(blockpos.down()).getBlock();

        if (getElementType() == 1)
        {
            if(this.posY > 145.0D && this.world.canSeeSky(new BlockPos(this))) {
                if (willSpawn <= 10) {
                    nearMaterial = true;
                }
            }
            return super.getCanSpawnHere() && nearMaterial;
        }
        else if (getElementType() == 2)
        {
            for (int i1 = i - 16; i1 <= i + 16; i1++) {
                for (int j1 = j - 6; j1 <= j + 6; j1++) {
                    for (int k1 = k - 16; k1 <= k + 16; k1++) {
                        BlockPos pos = new BlockPos(i1, j1, k1);
                        if (block instanceof BlockDirt && this.world.canSeeSky(new BlockPos(this)) && this.posY > 36.0D) {
                            if (willSpawn <= 10) {
                                nearMaterial = true;
                            }
                        }
                    }
                }
            }
            return super.getCanSpawnHere() && nearMaterial;
        }
        else if (getElementType() == 3)
        {
            for (int i1 = i - 16; i1 <= i + 16; i1++) {
                for (int j1 = j - 6; j1 <= j + 6; j1++) {
                    for (int k1 = k - 16; k1 <= k + 16; k1++) {
                        BlockPos pos = new BlockPos(i1, j1, k1);
                        if (world.getBlockState(pos).getMaterial() == Material.LAVA && this.world.canSeeSky(new BlockPos(this)) && this.posY > 36.0D) {
                            if (willSpawn <= 10) {
                                nearMaterial = true;
                            }
                        }
                    }
                }
            }
            return super.getCanSpawnHere() && nearMaterial;
        }
        else if (getElementType() == 4)
        {
            for (int i1 = i - 16; i1 <= i + 16; i1++) {
                for (int j1 = j - 6; j1 <= j + 6; j1++) {
                    for (int k1 = k - 16; k1 <= k + 16; k1++) {
                        BlockPos pos = new BlockPos(i1, j1, k1);
                        if (world.getBlockState(pos).getMaterial() == Material.WATER && this.world.canSeeSky(new BlockPos(this)) && this.posY > 36.0D) {
                            if (willSpawn <= 10) {
                                nearMaterial = true;
                            }
                        }
                    }
                }
            }
            return super.getCanSpawnHere() && nearMaterial;
        }
        else
        {
            if(this.posY > 36.0D && this.world.canSeeSky(new BlockPos(this))) {
                if (willSpawn <= 10) {
                    nearMaterial = true;
                }
            }
            return super.getCanSpawnHere() && nearMaterial;
        }
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        if (getElementType() == 1){
            return LootInit.GOLEM_STONE_AIR;
        }
        else if (getElementType() == 2){
            return LootInit.GOLEM_STONE_EARTH;
        }
        else if (getElementType() == 3){
            return LootInit.GOLEM_STONE_FIRE;
        }
        else if (getElementType() == 4){
            return LootInit.GOLEM_STONE_WATER;
        }
        else {
            return LootInit.GOLEM_STONE;
        }
    }
}
