package com.greatorator.tolkienmobs.utils;

import com.greatorator.tolkienmobs.entity.EntityTMAquatics;
import com.greatorator.tolkienmobs.entity.hostile.EntityTMMirkwoodSpider;
import com.greatorator.tolkienmobs.entity.hostile.EntityTMSwampHag;
import com.greatorator.tolkienmobs.entity.passive.EntityTMGoat;
import com.greatorator.tolkienmobs.entity.ambient.EntityTMToad;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.fixes.*;

public class TTMDataFixes extends DataFixesManager {
    private static void registerFixes(DataFixer fixer)
    {
        fixer.registerFix(FixTypes.ENTITY, new EntityArmorAndHeld());
        fixer.registerFix(FixTypes.ENTITY, new MinecartEntityTypes());
        fixer.registerFix(FixTypes.ENTITY, new StringToUUID());
        fixer.registerFix(FixTypes.ENTITY, new EntityHealth());
        fixer.registerFix(FixTypes.ENTITY, new HorseSaddle());
        fixer.registerFix(FixTypes.ENTITY, new PaintingDirection());
        fixer.registerFix(FixTypes.ENTITY, new RedundantChanceTags());
        fixer.registerFix(FixTypes.ENTITY, new RidingToPassengers());
        fixer.registerFix(FixTypes.ENTITY, new ArmorStandSilent());
        fixer.registerFix(FixTypes.ENTITY, new ZombieProfToType());
        fixer.registerFix(FixTypes.ENTITY, new ElderGuardianSplit());
        fixer.registerFix(FixTypes.ENTITY, new SkeletonSplit());
        fixer.registerFix(FixTypes.ENTITY, new ZombieSplit());
        fixer.registerFix(FixTypes.ENTITY, new HorseSplit());
        fixer.registerFix(FixTypes.ENTITY, new EntityId());
        fixer.registerFix(FixTypes.ENTITY, new ShulkerBoxEntityColor());
    }

    public static DataFixer createFixer()
    {
        DataFixer datafixer = new DataFixer(1343);
        datafixer = new net.minecraftforge.common.util.CompoundDataFixer(datafixer);
        EntityTMGoat.registerFixesGoat(datafixer);
        EntityTMMirkwoodSpider.registerFixesMirkwoodSpider(datafixer);
        EntityTMToad.registerFixesToaddle(datafixer);
        EntityTMAquatics.registerFixesTMAquatics(datafixer);
        EntityTMSwampHag.registerFixesTMSwampHag(datafixer);
        registerFixes(datafixer);
        return datafixer;
    }
}
