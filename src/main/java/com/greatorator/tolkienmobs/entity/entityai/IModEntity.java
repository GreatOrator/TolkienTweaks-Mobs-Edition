package com.greatorator.tolkienmobs.entity.entityai;

/** Borrowed from Jabelar https://github.com/jabelar */
public interface IModEntity 
{
    
    /**
     * use clear tasks for subclasses then build up their ai task list specifically.
     */
    void clearAITasks();

    /**
     * Sets the scale factor.
     *
     * @param parScaleFactor the new scale factor
     */
    // common encapsulation methods
    void setScaleFactor(float parScaleFactor);
    
    /**
     * Gets the scale factor.
     *
     * @return the scale factor
     */
    float getScaleFactor();
}
