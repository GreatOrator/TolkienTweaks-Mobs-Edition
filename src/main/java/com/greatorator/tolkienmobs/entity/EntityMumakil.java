package com.greatorator.tolkienmobs.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

/** Borrowed from Jabelar https://github.com/jabelar */
public class EntityMumakil extends EntityHerds
{
	public EntityMumakil(World par1World) 
	{
		super(par1World);

    	setScaleFactor(2.0F);
        setSize(width*getScaleFactor(), height*getScaleFactor());
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
    public EntityMumakil createChild(EntityAgeable par1EntityAgeable)
    {
        return new EntityMumakil(world);
    }

 }
