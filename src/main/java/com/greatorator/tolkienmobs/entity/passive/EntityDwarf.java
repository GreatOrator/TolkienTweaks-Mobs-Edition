package com.greatorator.tolkienmobs.entity.passive;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.monster.*;
import com.greatorator.tolkienmobs.init.SoundInit;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import javax.annotation.Nullable;

public class EntityDwarf extends EntityVillager implements IEntityAdditionalSpawnData {
    private int texture_index;
    public static final ResourceLocation LOOT = new ResourceLocation(TolkienMobs.MODID, "entities/dwarf");

    public EntityDwarf(World worldIn) {
        super(worldIn);
        this.setSize(1.0F, 1.7F);
        ((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
        this.texture_index = rand.nextInt(4);
    }

    public int getTextureIndex() {
        return this.texture_index;
    }

    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIMoveIndoors(this));
        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.6D));
        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(9, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
        this.applyEntityAI();
    }

    private void applyEntityAI() {
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[]{EntityDwarf.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMordorOrc.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityGoblin.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityTroll.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityWarg.class, true));
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
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(9.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(26.0D);
    }

    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        super.setEquipmentBasedOnDifficulty(difficulty);
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(TTMFeatures.AXE_MITHRIL));
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        IEntityLivingData ientitylivingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setEquipmentBasedOnDifficulty(difficulty);
        return ientitylivingdata;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundInit.soundIdleDwarf;
    }

    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (!super.attackEntityAsMob(entityIn))
        {
            return false;
        }
        else
        {
            if (entityIn instanceof EntityLivingBase)
            {
                ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.POISON, 0));
            }

            return true;
        }
    }

    /** Let's try to decide which entity will do what work */
//    public void setProfession(VillagerRegistry.VillagerProfession profession) {
//        if (!profession.getRegistryName().equals(ProfessionInit.getCoinBanker().getRegistryName()) ){
//            profession = VillagerRegistry.instance().getRegistry().getValue(new ResourceLocation(TolkienMobs.MODID + ":coin_trader"));
//        }
//        super.setProfession(profession);
//    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LOOT;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 2;
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeInt(this.texture_index);
    }

    @Override
    public void readSpawnData(ByteBuf buffer) {
        this.texture_index = buffer.readInt();
    }

    public EntityDwarf createChild(EntityAgeable ageable)
    {
        EntityDwarf entitydwarf = new EntityDwarf(this.world);
        entitydwarf.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(entitydwarf)), (IEntityLivingData)null);
        return entitydwarf;
    }
}