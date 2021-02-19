package com.greatorator.tolkienmobs.handler.handler_old.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface IPetList {
    void setPetList(List<UUID> parCapability);
    ArrayList<UUID> getPetList();
}