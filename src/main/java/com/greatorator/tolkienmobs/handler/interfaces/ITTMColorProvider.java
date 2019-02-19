package com.greatorator.tolkienmobs.handler.interfaces;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface ITTMColorProvider {
    @SideOnly(Side.CLIENT)
    IItemColor getItemColor();
}
