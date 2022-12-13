package com.greatorator.tolkienmobs.utils;

import net.minecraft.util.Mth;

public class LerpedFloatUtility {
    protected float current;
    protected float previous;

    public LerpedFloatUtility()
    {
        current = previous = 0;
    }

    public LerpedFloatUtility(float start)
    {
        current = previous = start;
    }

    public float get(float x)
    {
        return MathUtility.linTerp(previous, current, x);
    }

    public float get()
    {
        return current;
    }

    public void set(float value)
    {
        sync();
        current = value;
    }

    public void add(float value)
    {
        sync();
        current += value;
    }

    public void sync()
    {
        previous = current;
    }

    public float getPrevious()
    {
        return previous;
    }

    public static LerpedFloatUtility.Clamped unit()
    {
        return new Clamped(0, 1);
    }

    public static class Clamped extends LerpedFloatUtility
    {
        private final float min;
        private final float max;

        public Clamped(float start, float min, float max)
        {
            super(Mth.clamp(start, min, max));
            this.min = min;
            this.max = max;
        }

        public Clamped(float min, float max)
        {
            this(0, min, max);
        }

        @Override
        public void set(float value)
        {
            super.set(Mth.clamp(value, min, max));
        }

        @Override
        public void add(float value)
        {
            super.add(value);
            current = Mth.clamp(current, min, max);
        }

        public float getMin()
        {
            return min;
        }

        public float getMax()
        {
            return max;
        }
    }
}