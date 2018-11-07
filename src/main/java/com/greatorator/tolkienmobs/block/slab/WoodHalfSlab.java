package com.greatorator.tolkienmobs.block.slab;

public class WoodHalfSlab extends WoodSlab
{
	public WoodHalfSlab(String name, float hardness, float resistance)
	{
		super(name, hardness, resistance);
	}
	
	@Override
	public boolean isDouble() 
	{
		return false;
	}

}
