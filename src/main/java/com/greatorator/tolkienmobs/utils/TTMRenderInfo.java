package com.greatorator.tolkienmobs.utils;

import net.minecraft.client.gui.ScaledResolution;

import javax.vecmath.Vector2f;

public abstract class TTMRenderInfo<Element> {
    private Element renderElement;
    private OffsetType offsetType;
    private float xOffset;
    private float yOffset;

    public TTMRenderInfo(Element element, OffsetType type) {
        renderElement = element;
        offsetType = type;
    }

    public abstract int getWidth(ScaledResolution sr);

    public abstract int getHeight(ScaledResolution sr);

    public abstract int getBaseX(ScaledResolution sr);

    public abstract int getBaseY(ScaledResolution sr);

    public float getXOffset(ScaledResolution sr)
    {
        return xOffset;
    }

    public float getYOffset(ScaledResolution sr)
    {
        return yOffset;
    }

    public void setXOffset(float x)
    {
        xOffset = x;
    }

    public void setYOffset(float y)
    {
        yOffset = y;
    }

    public Vector2f getEffectiveXY(ScaledResolution sr) {
        int x, y;
        switch (getOffsetType())
        {
            default:
            case TOPLEFT:
                x = 0;
                y = 0;
                break;
            case TOPRIGHT:
                x = sr.getScaledWidth() - getWidth(sr);
                y = 0;
                break;
            case BOTTOMRIGHT:
                x = sr.getScaledWidth() - getWidth(sr);
                y = sr.getScaledHeight() - getHeight(sr);
                break;
            case BOTTOMLEFT:
                x = 0;
                y = sr.getScaledHeight() - getHeight(sr);
                break;
            case ORIGINAL:
                x = getBaseX(sr);
                y = getBaseY(sr);
        }

        x += getXOffset(sr);
        y += getYOffset(sr);
        return new Vector2f(x, y);
    }

    public boolean isClicked(int mouseX, int mouseY, ScaledResolution sr)
    {
        Vector2f effectiveXY = getEffectiveXY(sr);
        float x = effectiveXY.x;
        float y = effectiveXY.y;

        return mouseX >= x && mouseX <= x + getWidth(sr) && mouseY >= y && mouseY <= y + getHeight(sr);
    }

    public OffsetType getOffsetType()
    {
        return offsetType;
    }

    enum OffsetType {
        CENTRE,
        TOPLEFT,
        TOPRIGHT,
        BOTTOMLEFT,
        BOTTOMRIGHT,
        ORIGINAL
    }
}
