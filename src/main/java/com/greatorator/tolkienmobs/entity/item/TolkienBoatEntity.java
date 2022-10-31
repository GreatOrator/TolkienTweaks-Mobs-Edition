package com.greatorator.tolkienmobs.entity.item;

import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class TolkienBoatEntity extends BoatEntity {
    private static final DataParameter<Integer> WOOD_TYPE = EntityDataManager.defineId(TolkienBoatEntity.class, DataSerializers.INT);

    public TolkienBoatEntity(EntityType<? extends BoatEntity> type, World world) {
        super(type, world);
        this.blocksBuilding = true;
    }

    public TolkienBoatEntity(World worldIn, double x, double y, double z) {
        this(EntityGenerator.MALLORN_BOAT.get(), worldIn);
        this.setPos(x, y, z);
        this.setDeltaMovement(Vector3d.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(WOOD_TYPE, TolkienBoatEntity.Type.MALLORN.ordinal());
    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("Type", 8)) {
            this.setWoodType(TolkienBoatEntity.Type.byName(compound.getString("Type")));
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putString("Type", this.getWoodType().getName());
    }

    public TolkienBoatEntity.Type getWoodType() {
        return TolkienBoatEntity.Type.byId(this.entityData.get(WOOD_TYPE));
    }

    public void setWoodType(TolkienBoatEntity.Type type) {
        this.entityData.set(WOOD_TYPE, type.ordinal());
    }

    @Nonnull
    @Override
    public Item getDropItem() {
        switch(this.getWoodType()) {
            case MALLORN:
            default:
                return TTMContent.MALLORN_BOAT.get();
            case MIRKWOOD:
                return TTMContent.MIRKWOOD_BOAT.get();
            case CULUMALDA:
                return TTMContent.CULUMALDA_BOAT.get();
            case LEBETHRON:
                return TTMContent.LEBETHRON_BOAT.get();
        }
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(ForgeRegistries.ITEMS.getValue(
                new ResourceLocation(MODID, "boat_" + this.getWoodType().getName())));
    }

    public static enum Type {
        MALLORN(TTMContent.PLANKS_MALLORN.get(), "mallorn"),
        MIRKWOOD(TTMContent.PLANKS_MIRKWOOD.get(), "mirkwood"),
        CULUMALDA(TTMContent.PLANKS_CULUMALDA.get(), "culumalda"),
        LEBETHRON(TTMContent.PLANKS_LEBETHRON.get(), "lebethron");

        private final String name;
        private final Block planks;

        private Type(Block block, String type) {
            this.name = type;
            this.planks = block;
        }

        public String getName() {
            return this.name;
        }

        public Block getPlanks() {
            return this.planks;
        }

        @Override
        public String toString() {
            return this.name;
        }

        public static TolkienBoatEntity.Type byId(int typeId) {
            TolkienBoatEntity.Type[] tolkienboatentity$type = values();
            if (typeId < 0 || typeId >= tolkienboatentity$type.length) {
                typeId = 0;
            }
            return tolkienboatentity$type[typeId];
        }

        public static TolkienBoatEntity.Type byName(String typeName) {
            TolkienBoatEntity.Type[] tolkienboatentity$type = values();

            for(int i = 0; i < tolkienboatentity$type.length; ++i) {
                if (tolkienboatentity$type[i].getName().equals(typeName)) {
                    return tolkienboatentity$type[i];
                }
            }
            return tolkienboatentity$type[0];
        }
    }

    @Nonnull
    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}