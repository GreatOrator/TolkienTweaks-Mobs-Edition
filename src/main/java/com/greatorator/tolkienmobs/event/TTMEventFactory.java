package com.greatorator.tolkienmobs.event;

import com.greatorator.tolkienmobs.entity.monster.EntityTTMGoblin;
import com.greatorator.tolkienmobs.event.entity.living.TTMGoblinEvent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class TTMEventFactory {

    public static TTMGoblinEvent.SummonAidEvent fireGoblinSummonAid(EntityTTMGoblin goblin, World world, int x, int y, int z, LivingEntity attacker, double summonChance)
    {
        TTMGoblinEvent.SummonAidEvent summonEvent = new TTMGoblinEvent.SummonAidEvent(goblin, world, x, y, z, attacker, summonChance);
        MinecraftForge.EVENT_BUS.post(summonEvent);
        return summonEvent;
    }
}
