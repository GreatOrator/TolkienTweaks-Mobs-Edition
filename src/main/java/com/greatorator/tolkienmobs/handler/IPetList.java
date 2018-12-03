package com.greatorator.tolkienmobs.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface IPetList {
    void setPetList(List<UUID> parCapability);
    ArrayList<UUID> getPetList();
}
