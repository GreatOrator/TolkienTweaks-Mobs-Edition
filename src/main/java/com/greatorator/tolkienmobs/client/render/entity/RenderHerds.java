package com.greatorator.tolkienmobs.client.render.entity;

import com.greatorator.tolkienmobs.entity.EntityHerds;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

/** Borrowed from Jabelar https://github.com/jabelar */
@SuppressWarnings("rawtypes")
public class RenderHerds extends RenderLiving
{
    protected ResourceLocation herdAnimalTexture;

    /**
     * Instantiates a new render herd animal.
     *
     * @param parRenderManager the par render manager
     * @param par1ModelBase the par 1 model base
     * @param parShadowSize the par shadow size
     * @param parNormalTexture the par normal texture
     */
    public RenderHerds(
    		RenderManager parRenderManager, 
    		ModelBase par1ModelBase, 
    		float parShadowSize,
    		ResourceLocation parNormalTexture
    		)
    {
        super(parRenderManager, par1ModelBase, parShadowSize);
        herdAnimalTexture = parNormalTexture;       
    }
	
    /**
     * Pre render callback.
     *
     * @param entity the entity
     * @param f the f
     */
    @Override
	protected void preRenderCallback(EntityLivingBase entity, float f)
    {
    	preRenderCallbackHerdAnimal((EntityHerds) entity, f);
    }

    
	/**
	 * Pre render callback herd animal.
	 *
	 * @param entity the entity
	 * @param f the f
	 */
	protected void preRenderCallbackHerdAnimal(EntityHerds entity, float f)
	{
    }
    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     *
     * @param par1EntityHerdAnimal the par 1 entity herd animal
     * @return the entity texture
     */
    protected ResourceLocation getEntityTexture(EntityHerds par1EntityHerdAnimal)
    {
        return herdAnimalTexture;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     *
     * @param par1Entity the par 1 entity
     * @return the entity texture
     */
    @Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getEntityTexture((EntityHerdAnimal)par1Entity);
    }
    
    /**
     * Gets the render factory.
     *
     * @param parModelBase1 the par model base 1
     * @param parShadowSize the par shadow size
     * @param parNormalTexture the par normal texture
     * @return the render factory
     */
    public static IRenderFactory getRenderFactory(
	        ModelBase parModelBase1, 
	        float parShadowSize, 
	        ResourceLocation parNormalTexture 
			)
    {
    	return new RenderFactory(
    	        parModelBase1, 
    	        parShadowSize, 
    	        parNormalTexture 
    			);
    }
    
	private static class RenderFactory implements IRenderFactory
	{
        ModelBase model1;
        float shadowSize;
        ResourceLocation normalTexture; 
		
		public RenderFactory(
	        ModelBase parModelBase1, 
	        float parShadowSize, 
	        ResourceLocation parNormalTexture) 
		{
			model1 = parModelBase1;
			shadowSize = parShadowSize;
			normalTexture = parNormalTexture;
		}

		@Override
		public Render createRenderFor(RenderManager manager) 
		{
			return new RenderHerds(
				manager,
				model1,
				shadowSize,
				normalTexture
			);
		}	
	}
}
