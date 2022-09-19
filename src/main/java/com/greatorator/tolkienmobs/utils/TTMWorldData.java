package com.greatorator.tolkienmobs.utils;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.storage.WorldSavedData;

import java.util.HashMap;
import java.util.Map;

public class TTMWorldData extends WorldSavedData {
    public static final String DATA_TAG = "TMobsWorldData";
    protected Map<String, MilestoneMarker> markers = new HashMap<String, MilestoneMarker>();

    public TTMWorldData(String nameIn) {
        super(nameIn);
    }

    @Override
    public void load(CompoundNBT p_76184_1_) {

    }

    @Override
    public CompoundNBT save(CompoundNBT p_189551_1_) {
        return null;
    }

    public static class MilestoneMarker {
        public String username;
        public int x;
        public int y;
        public int z;
        public int dimension;

        public MilestoneMarker(){}

        public MilestoneMarker(String username, int x, int y, int z, int dimension){
            this.username = username;
            this.x = x;
            this.y = y;
            this.z = z;
            this.dimension = dimension;
        }

        public CompoundNBT save(){
            CompoundNBT compound = new CompoundNBT();
            compound.putInt("X", x);
            compound.putInt("Y", y);
            compound.putInt("Z", z);
            compound.putInt("Dim", dimension);
            compound.putString("User", username);
            return compound;
        }

        public MilestoneMarker load(CompoundNBT compound){
            x = compound.getInt("X");
            y = compound.getInt("Y");
            z = compound.getInt("Z");
            dimension = compound.getInt("Dim");
            username = compound.getString("User");
            return this;
        }
    }
}
