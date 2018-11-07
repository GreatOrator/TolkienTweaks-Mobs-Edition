package com.greatorator.tolkienmobs.block.slab;

public class WoodDoubleSlab extends WoodSlab
{
	public WoodDoubleSlab(String name, float hardness, float resistance)
	{
		super(name, hardness, resistance);
	}
	
	@Override
	public boolean isDouble() 
	{
		return true;
	}
	
}
