//package com.greatorator.tolkienmobs.entity.boss;
//
//import com.greatorator.tolkienmobs.entity.EntityTMHostiles;
//import com.greatorator.tolkienmobs.init.BiomeInit;
//import com.greatorator.tolkienmobs.init.LootInit;
//import com.greatorator.tolkienmobs.init.PotionInit;
//import com.greatorator.tolkienmobs.init.SoundInit;
//import net.minecraft.block.Block;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.IEntityLivingData;
//import net.minecraft.entity.SharedMonsterAttributes;
//import net.minecraft.entity.ai.EntityAIHurtByTarget;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.entity.player.ServerPlayerEntity;
//import net.minecraft.init.MobEffects;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.network.datasync.DataParameter;
//import net.minecraft.network.datasync.DataSerializers;
//import net.minecraft.network.datasync.EntityDataManager;
//import net.minecraft.potion.PotionEffect;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.EntityDamageSourceIndirect;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.SoundEvent;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.text.translation.I18n;
//import net.minecraft.world.BossInfo;
//import net.minecraft.world.BossInfoServer;
//import net.minecraft.world.DifficultyInstance;
//import net.minecraft.world.World;
//
//import javax.annotation.Nullable;
//
//public class EntityTMMorgulGolem extends EntityTMHostiles {
//    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.GREEN, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);
//    private long nextAbilityUse = 0L;
//    private final static long coolDown = 15000L;
//    private LivingEntity mob;
//
//    private static final DataParameter<Integer> ELEMENT_TYPE = EntityDataManager.<Integer>createKey(EntityTMMorgulGolem.class, DataSerializers.VARINT);
//
//    public EntityTMMorgulGolem(World worldIn) {
//        super(worldIn);
//        this.setSize(1.8F, 3.1F);
//        this.setTtmEffect(MobEffects.WITHER);
//        this.setTtmDuration(200);
//        this.experienceValue = 200;
//        this.setTtmAttack(true);
//        this.setMadeBoss(true);
//        this.setMob(this);
//    }
//
//    protected void entityInit()
//    {
//        super.entityInit();
//        this.dataManager.register(ELEMENT_TYPE, Integer.valueOf(0));
//    }
//
//    public void addTrackingPlayer(ServerPlayerEntity player)
//    {
//        super.addTrackingPlayer(player);
//        this.bossInfo.addPlayer(player);
//    }
//
//    public void removeTrackingPlayer(ServerPlayerEntity player)
//    {
//        super.removeTrackingPlayer(player);
//        this.bossInfo.removePlayer(player);
//    }
//
//    public void setCustomNameTag(String name)
//    {
//        super.setCustomNameTag(name);
//        this.bossInfo.setName(this.getDisplayName());
//    }
//
//    protected void updateAITasks()
//    {
//        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
//        super.updateAITasks();
//    }
//
//
//    @Override
//    public boolean attackEntityFrom(DamageSource damageSource, float damage) {
//        Entity player = damageSource.getTrueSource();
//
//        if (player != null && (player instanceof PlayerEntity)) {
//            PlayerEntity p = (PlayerEntity)player;
//
//            long time = System.currentTimeMillis();
//            if (time > nextAbilityUse && damageSource.getTrueSource() != null && !(damageSource instanceof EntityDamageSourceIndirect)) {
//                nextAbilityUse = time + coolDown;
//
//                if (rand.nextInt(10) == 0){
//                    assert player != null;
//                    p.addPotionEffect(new PotionEffect(PotionInit.ELEMENTAL_BURNING, 200, 3));}
//                else {
//                    assert player != null;
//                    p.addPotionEffect(new PotionEffect(PotionInit.ELEMENTAL_LIGHTNING, 45, 1));
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
//        if (elementTypeId == 7)
//        {
//            this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(15.0D);
//            this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
//
//            if (!this.hasCustomName())
//            {
//                this.setCustomNameTag(I18n.translateToLocal("entity.elementalgolem7.name"));
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
//        if (ientitylivingdata instanceof EntityTMMorgulGolem.ElementTypeData)
//        {
//            i = ((EntityTMMorgulGolem.ElementTypeData)livingdata).typeData;
//        }
//        else
//        {
//            ientitylivingdata = new EntityTMMorgulGolem.ElementTypeData(i);
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
//        int i = 7;
//        return i;
//    }
//
//    @Override
//    public double getAttackDamage() {
//        return 18;
//    }
//
//    @Override
//    public double getArmorStrength() {
//        return 30;
//    }
//
//    @Override
//    public double getHealthLevel() {
//        return 200;
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
//        if (this.hasCustomName())
//        {
//            this.bossInfo.setName(this.getDisplayName());
//        }
//    }
//
//    public void setMob(LivingEntity mob) {
//        this.mob = mob;
//    }
//
//    @Override
//    public boolean getCanSpawnHere() {
//        if (getElementType() == 7 && world.getBiome(new BlockPos(this)) == BiomeInit.MORDOR)
//        {
//            return super.getCanSpawnHere() && this.posY > 145.0D && this.world.canSeeSky(new BlockPos(this));
//        }
//        return getCanSpawnHere();
//    }
//
//    @Override
//    @Nullable
//    protected ResourceLocation getLootTable() {
//            return LootInit.GOLEM_STONE_MORGUL;
//    }
//}
