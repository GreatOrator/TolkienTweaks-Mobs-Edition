package com.greatorator.tolkienmobs.handler.interfaces.providers;

import com.greatorator.tolkienmobs.handler.interfaces.IPetList;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/** Borrowed from Jabelar https:/**github.com/jabelar */
public class PetList implements IPetList
{
    private List<UUID> petList = new ArrayList<UUID>();

    @Override
    public void setPetList(List<UUID> parPetList)
    {
        petList = parPetList;
    }

    @Override
    public ArrayList<UUID> getPetList()
    {
        return (ArrayList<UUID>) petList;
    }

}