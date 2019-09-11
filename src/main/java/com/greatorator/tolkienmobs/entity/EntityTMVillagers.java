package com.greatorator.tolkienmobs.entity;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.entity.hostile.*;
import com.greatorator.tolkienmobs.entity.passive.EntityTMDwarf;
import com.greatorator.tolkienmobs.init.ProfessionInit;
import com.greatorator.tolkienmobs.utils.TTMRand;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import javax.annotation.Nullable;

public class EntityTMVillagers extends EntityVillager implements IEntityAdditionalSpawnData {
    private VillagerRegistry.VillagerProfession prof;
    private int texture_index;
    private int rndMax;
    private int rndMin;
    private int netID;
    private boolean isFighter;
    private ResourceLocation lootTable;

    public EntityTMVillagers(World worldIn) {
        super(worldIn);
    }

    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIMoveIndoors(this));
        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.65D));
        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(9, new EntityAIWanderAvoidWater(this, 0.65D));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
        if(this instanceof EntityTMDwarf) {
            this.applyEntityAI();
        }
        else {
            this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityTMTroll.class, 8.0F, 0.8D, 0.8D));
            this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityTMMordorOrc.class, 8.0F, 0.8D, 0.8D));
            this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityTMUrukHai.class, 8.0F, 0.8D, 0.8D));
            this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityTMGoblin.class, 12.0F, 0.8D, 0.8D));
            this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityTMWarg.class, 8.0F, 0.8D, 0.8D));
        }
    }

    private void applyEntityAI() {
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[]{EntityTMVillagers.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityTMMordorOrc.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityTMUrukHai.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityTMGoblin.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityTMTroll.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityTMWarg.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.55D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
    }

    public int getTextureIndex() {
        return this.texture_index;
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        this.setEquipmentBasedOnDifficulty(difficulty);
        return super.onInitialSpawn(difficulty, livingdata);
    }

    @Override
    public IEntityLivingData finalizeMobSpawn(DifficultyInstance p_190672_1_, @Nullable IEntityLivingData p_190672_2_, boolean initialSpawn)
    {
        if (initialSpawn)
        {
            this.texture_index = TTMRand.getRandomInteger(rndMax, rndMin);
            switch (texture_index) {
                case 0:
                    break;

                case 1:
                    prof = VillagerRegistry.getById(netID);
                    break;

                case 2:
                    prof = ProfessionInit.getCoinBanker();
                    break;

                case 3:
                    prof = ProfessionInit.getGroceryStore();
                    break;

                case 4:
                    prof = ProfessionInit.getPetSupplies();
                    break;

                case 5:
                    prof = VillagerRegistry.getById(netID);
                    break;

                case 6:
                    prof = ProfessionInit.getCoinBanker();
                    break;

                case 7:
                    prof = ProfessionInit.getGroceryStore();
                    break;

                case 8:
                    prof = ProfessionInit.getJunkSeller();
                    break;

                case 9:
                    prof = VillagerRegistry.getById(netID);
                    break;

                case 10:
                    prof = ProfessionInit.getCoinBanker();
                    break;

                case 11:
                    prof = ProfessionInit.getGroceryStore();
                    break;

                case 12:
                    prof = ProfessionInit.getPetSupplies();

                case 13:
                    prof = VillagerRegistry.getById(netID);
                    break;

                case 14:
                    prof = ProfessionInit.getCoinBanker();
                    break;

                case 15:
                    prof = ProfessionInit.getGroceryStore();
                    break;

                case 16:
                    prof = ProfessionInit.getJunkSeller();

            }
            this.setProfession(VillagerRegistry.getId(prof));
        }
        return super.finalizeMobSpawn(p_190672_1_, p_190672_2_, false);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return lootTable;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 2;
    }

    @Override
    public boolean getCanSpawnHere()
    {
        int i = MathHelper.floor(this.posX);
        int j = MathHelper.floor(this.getEntityBoundingBox().minY);
        int k = MathHelper.floor(this.posZ);
        int willSpawn = this.spawnChance();
        BlockPos blockpos = new BlockPos(i, j, k);

        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.world.getLight(blockpos) > 8 && willSpawn <= 10 && super.getCanSpawnHere();
    }

    private int spawnChance()
    {
        int i = TTMRand.getRandomInteger(TTMConfig.mobSpawnChance, 1);
        return i;
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeInt(this.texture_index);
    }

    @Override
    public void readSpawnData(ByteBuf buffer) {
        this.texture_index = buffer.readInt();
    }
    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("texture_index", texture_index);
        compound.setInteger("Profession", this.getProfession());
        compound.setString("ProfessionName", this.getProfessionForge().getRegistryName().toString());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        texture_index = compound.getInteger("texture_index");
        this.setProfession(compound.getInteger("Profession"));
        if (compound.hasKey("ProfessionName"))
        {
            net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession p =
                    net.minecraftforge.fml.common.registry.ForgeRegistries.VILLAGER_PROFESSIONS.getValue(new net.minecraft.util.ResourceLocation(compound.getString("ProfessionName")));
            if (p == null)
                p = net.minecraftforge.fml.common.registry.ForgeRegistries.VILLAGER_PROFESSIONS.getValue(new net.minecraft.util.ResourceLocation("minecraft:farmer"));
            this.setProfession(p);
        }
    }

    @Override
    public EntityTMVillagers createChild(EntityAgeable ageable)
    {
        EntityTMVillagers entityTMVillagers = new EntityTMVillagers(this.world);
        entityTMVillagers.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(entityTMVillagers)), (IEntityLivingData)null);
        return entityTMVillagers;
    }

    protected boolean canDespawn()
    {
        return true;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        if(isFighter) {
            if (!super.attackEntityAsMob(entityIn)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public void setRndMinMax(int rndMin, int rndMax) {
        this.rndMin = rndMin;
        this.rndMax = rndMax;
    }

    public void setLootTable(ResourceLocation lootTable) {
        this.lootTable = lootTable;
    }

    public void setNetID(int netID) {
        this.netID = netID;
    }

    public void setFighter(boolean fighter) {
        this.isFighter = fighter;
    }

    public int getTalkInterval()
    {
        return 160;
    }
}