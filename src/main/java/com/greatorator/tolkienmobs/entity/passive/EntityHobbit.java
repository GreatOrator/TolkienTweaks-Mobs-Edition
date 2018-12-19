package com.greatorator.tolkienmobs.entity.passive;

import com.greatorator.tolkienmobs.entity.monster.*;
import com.greatorator.tolkienmobs.handler.TTMRand;
import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.init.ProfessionInit;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import javax.annotation.Nullable;

public class EntityHobbit extends EntityVillager implements IEntityAdditionalSpawnData {
    private int texture_index;

    public EntityHobbit(World worldIn) {
        super(worldIn);
        this.setSize(0.9F, 1.5F);
        ((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
    }

    public int getTextureIndex() {
        return this.texture_index;
    }

    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityTroll.class, 8.0F, 0.8D, 0.8D));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityMordorOrc.class, 8.0F, 0.8D, 0.8D));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityUrukHai.class, 8.0F, 0.8D, 0.8D));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityGoblin.class, 12.0F, 0.8D, 0.8D));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityWarg.class, 8.0F, 0.8D, 0.8D));
        this.tasks.addTask(2, new EntityAIMoveIndoors(this));
        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.6D));
        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(9, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        IEntityLivingData ientitylivingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setEquipmentBasedOnDifficulty(difficulty);
        if (texture_index != 0){
            texture_index = texture_index;
        }
        else {
            this.texture_index = TTMRand.getRandomInteger(5, 1);
        }
        return ientitylivingdata;
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeInt(this.texture_index);
    }

    @Override
    public void readSpawnData(ByteBuf buffer) {
        this.texture_index = buffer.readInt();
    }

    /** Let's try to decide which entity will do what work */
    public void setProfession(VillagerRegistry.VillagerProfession profession) {
        switch (texture_index) {
            case 0:
                break;
            case 1:
                profession = VillagerRegistry.getById(0);
                break;

            case 2:
                profession = ProfessionInit.getCoinBanker();
                break;

            case 3:
                profession = ProfessionInit.getGroceryStore();
                break;

            case 4:
                profession = VillagerRegistry.getById(4);

        }
        this.prof = profession;
        this.setProfession(net.minecraftforge.fml.common.registry.VillagerRegistry.getId(prof));
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LootInit.HOBBIT;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 2;
    }

    private net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession prof;
    public net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession getProfessionForge()
    {
        if (this.prof == null)
        {
            this.prof = net.minecraftforge.fml.common.registry.VillagerRegistry.getById(this.getProfession());
            if (this.prof == null)
                return net.minecraftforge.fml.common.registry.VillagerRegistry.getById(0); //Farmer
        }
        return this.prof;
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

    public EntityHobbit createChild(EntityAgeable ageable)
    {
        EntityHobbit entityhobbit = new EntityHobbit(this.world);
        entityhobbit.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(entityhobbit)), (IEntityLivingData)null);
        return entityhobbit;
    }

    protected boolean canDespawn()
    {
        return false;
    }
}
