package com.greatorator.tolkienmobs.block;

import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fluids.Fluid;

public class BlockFluids extends Fluid {
    protected static int mapColor = 0x000000;
    protected static float overlayAlpha = 0.2F;
    protected static SoundEvent emptySound = SoundEvents.ITEM_BUCKET_EMPTY;
    protected static SoundEvent fillSound = SoundEvents.ITEM_BUCKET_FILL;
    protected boolean bucketEnabled = false;

    public BlockFluids(String fluidName, ResourceLocation still, ResourceLocation flowing)
    {
        super(fluidName, still, flowing);
    }

    public BlockFluids(String fluidName, ResourceLocation still, ResourceLocation flowing, int mapColor)
    {
        this(fluidName, still, flowing);
        setColor(mapColor);
    }

    public BlockFluids(String fluidName, ResourceLocation still, ResourceLocation flowing, int mapColor, float overlayAlpha)
    {
        this(fluidName, still, flowing, mapColor);
        setAlpha(overlayAlpha);
    }

    @Override
    public int getColor()
    {
        return mapColor;
    }

    public BlockFluids setColor(int parColor)
    {
        mapColor = parColor;
        return this;
    }

    public float getAlpha()
    {
        return overlayAlpha;
    }

    public BlockFluids setAlpha(float parOverlayAlpha)
    {
        overlayAlpha = parOverlayAlpha;
        return this;
    }

    @Override
    public BlockFluids setEmptySound(SoundEvent parSound)
    {
        emptySound = parSound;
        return this;
    }

    @Override
    public SoundEvent getEmptySound()
    {
        return emptySound;
    }

    @Override
    public BlockFluids setFillSound(SoundEvent parSound)
    {
        fillSound = parSound;
        return this;
    }

    @Override
    public SoundEvent getFillSound()
    {
        return fillSound;
    }

    public BlockFluids setHasBucket(boolean parEnableBucket)
    {
        bucketEnabled = parEnableBucket;
        return this;
    }

    public boolean isBucketEnabled()
    {
        return bucketEnabled;
    }
}
