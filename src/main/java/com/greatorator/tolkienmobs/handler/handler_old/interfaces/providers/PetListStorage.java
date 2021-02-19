//package com.greatorator.tolkienmobs.handler.handler_old.interfaces.providers;
//
//import com.greatorator.tolkienmobs.handler.handler_old.interfaces.IPetList;
//import net.minecraft.nbt.NBTBase;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.util.EnumFacing;
//import net.minecraftforge.common.capabilities.Capability;
//import net.minecraftforge.common.capabilities.Capability.IStorage;
//
//import java.util.List;
//import java.util.UUID;
//
///** Borrowed from Jabelar https:/**github.com/jabelar */
//public class PetListStorage implements IStorage<IPetList>
//{
//    private String keyBase = "petUUID";
//
//    @Override
//    public NBTTagCompound writeNBT(
//            Capability<IPetList> capability,
//            IPetList instance,
//            EnumFacing side)
//    {
//        List<UUID> petList = instance.getPetList();
//        NBTTagCompound compound = new NBTTagCompound();
//        for (int i = 0; i < petList.size(); i++)
//        {
//            compound.setUniqueId(keyBase+i, petList.get(i));
//        }
//
//        return compound;
//    }
//
//    @Override
//    public void readNBT(
//            Capability<IPetList> capability,
//            IPetList instance,
//            EnumFacing side,
//            NBTBase nbt)
//    {
//        NBTTagCompound compound = (NBTTagCompound)nbt;
//        instance.getPetList().clear();
//
//        for (int i=0; i < compound.getSize(); i++)
//        {
//            instance.getPetList().add(compound.getUniqueId(keyBase+i));
//        }
//    }
//}