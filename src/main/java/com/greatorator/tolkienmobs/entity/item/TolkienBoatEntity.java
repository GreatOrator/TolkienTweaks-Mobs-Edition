package com.greatorator.tolkienmobs.entity.item;

import com.greatorator.tolkienmobs.init.TolkienBlocks;
import com.greatorator.tolkienmobs.init.TolkienEntities;
import com.greatorator.tolkienmobs.init.TolkienItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nonnull;

public class TolkienBoatEntity extends Boat {
    private static final EntityDataAccessor<Integer> WOOD_TYPE = SynchedEntityData.defineId(TolkienBoatEntity.class, EntityDataSerializers.INT);
    public static TolkienBoatEntity.Type type;

    public TolkienBoatEntity(EntityType<? extends Boat> type, Level world) {
        super(type, world);
        this.blocksBuilding = true;
    }

    public TolkienBoatEntity(Level worldIn, double x, double y, double z) {
        this(TolkienEntities.MALLORN_BOAT.get(), worldIn);
        this.setPos(x, y, z);
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
    protected void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("Type", 8)) {
            this.setWoodType(TolkienBoatEntity.Type.byName(compound.getString("Type")));
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
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
                return TolkienItems.MALLORN_BOAT.get();
            case MIRKWOOD:
                return TolkienItems.MIRKWOOD_BOAT.get();
            case CULUMALDA:
                return TolkienItems.CULUMALDA_BOAT.get();
            case LEBETHRON:
                return TolkienItems.LEBETHRON_BOAT.get();
            case DEADWOOD:
                return TolkienItems.DEADWOOD_BOAT.get();
            case FANGORNOAK:
                return TolkienItems.FANGORNOAK_BOAT.get();
        }
    }

    @Override
    public ItemStack getPickResult() {
        return new ItemStack(this.getDropItem());
    }

    public enum Type {
        MALLORN(TolkienBlocks.PLANKS_MALLORN.get(), "mallorn"),
        MIRKWOOD(TolkienBlocks.PLANKS_MIRKWOOD.get(), "mirkwood"),
        CULUMALDA(TolkienBlocks.PLANKS_CULUMALDA.get(), "culumalda"),
        LEBETHRON(TolkienBlocks.PLANKS_LEBETHRON.get(), "lebethron"),
        DEADWOOD(TolkienBlocks.PLANKS_LEBETHRON.get(), "deadwood"),
        FANGORNOAK(TolkienBlocks.PLANKS_LEBETHRON.get(), "fangornoak");

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
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}