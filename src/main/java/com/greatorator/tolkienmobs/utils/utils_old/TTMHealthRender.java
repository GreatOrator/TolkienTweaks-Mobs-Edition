//package com.greatorator.tolkienmobs.utils;
//
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.ScaledResolution;
//import net.minecraft.entity.SharedMonsterAttributes;
//import net.minecraft.entity.ai.attributes.IAttributeInstance;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.util.math.MathHelper;
//import net.minecraftforge.client.event.RenderGameOverlayEvent;
//
//public class TTMHealthRender extends TTMRenderInfo<RenderGameOverlayEvent.ElementType> {
//    public TTMHealthRender(OffsetType offsetType)
//    {
//        super(RenderGameOverlayEvent.ElementType.HEALTH, offsetType);
//    }
//
//    @Override
//    public int getWidth(ScaledResolution sr)
//    {
//        return 81;
//    }
//
//    @Override
//    public int getHeight(ScaledResolution sr)
//    {
//        PlayerEntity entityplayer = Minecraft.getMinecraft().player;
//        IAttributeInstance iattributeinstance = entityplayer.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
//        int k1 = MathHelper.ceil(entityplayer.getAbsorptionAmount());
//        float f = (float)iattributeinstance.getAttributeValue();
//        int l1 = MathHelper.ceil((f + (float)k1) / 2.0F / 10.0F);
//        int i2 = Math.max(10 - (l1 - 2), 3);
//        int j1 = sr.getScaledHeight() - 39;
//        int j5 = MathHelper.ceil((f + (float)k1) / 2.0F) - 1;
//        int j4 = MathHelper.ceil((float)(j5 + 1) / 10.0F) - 1;
//        int l4 = j1 - j4 * i2;
//
//        int j52 = 0;
//        int j42 = MathHelper.ceil((float)(j52 + 1) / 10.0F) - 1;
//        int l42 = j1 - j42 * i2;
//
//        return l42 - l4 + 9;
//    }
//
//    @Override
//    public int getBaseX(ScaledResolution sr)
//    {
//        int j5 = 0;
//        int l = sr.getScaledWidth() / 2 - 91;
//
//        int k4 = l + j5 % 10 * 8;
//
//        return k4;
//    }
//
//    @Override
//    public int getBaseY(ScaledResolution sr)
//    {
//        PlayerEntity entityplayer = Minecraft.getMinecraft().player;
//        IAttributeInstance iattributeinstance = entityplayer.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
//        int k1 = MathHelper.ceil(entityplayer.getAbsorptionAmount());
//        float f = (float)iattributeinstance.getAttributeValue();
//        int l1 = MathHelper.ceil((f + (float)k1) / 2.0F / 10.0F);
//        int i2 = Math.max(10 - (l1 - 2), 3);
//        int j1 = sr.getScaledHeight() - 39;
//        int j5 = MathHelper.ceil((f + (float)k1) / 2.0F) - 1;
//        int j4 = MathHelper.ceil((float)(j5 + 1) / 10.0F) - 1;
//        int l4 = j1 - j4 * i2;
//
//        return l4;
//    }
//}
