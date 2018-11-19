package com.greatorator.tolkienmobs.entity;

import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/** Borrowed from Jabelar https://github.com/jabelar */
public class EntityMumakil extends EntityHerds
{
    private int texture_index;
    public static final ResourceLocation LOOT = new ResourceLocation(TolkienMobs.MODID, "entities/mumakil");

	public EntityMumakil(World par1World) 
	{
		super(par1World);

    	setScaleFactor(2.5F);
        this.setSize(2.2F*getScaleFactor(), 2.7F*getScaleFactor());
        this.texture_index = rand.nextInt(4);
	}

    @Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LOOT;
    }

    @Override
    public EntityMumakil createChild(EntityAgeable par1EntityAgeable)
    {
        return new EntityMumakil(world);
    }

    public int getTextureIndex() {
        return this.texture_index;
    }

 }
