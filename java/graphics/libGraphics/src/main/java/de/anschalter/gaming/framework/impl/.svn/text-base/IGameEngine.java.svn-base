/*
 * Copyright by Peter Trebing; All rights reserved
 * 
 * mail@peter-trebing.de
 * http://www.peter-trebing.de
 * 
 * Created on 11.05.2005
 *
 */
package de.anschalter.gaming.framework.impl;

import java.awt.DisplayMode;
import java.util.List;

import de.anschalter.gaming.framework.IDrawable;
import de.anschalter.graphics.IPhysicalSimulator;

public interface IGameEngine {

    public abstract void configureDisplay(DisplayMode mode, boolean fullScreen);
    public abstract void startup();
    public abstract void shutdown();
    public abstract void pause();
    public abstract boolean isPaused();
    public abstract void resume();
    public abstract void addGameObjects(List<Object> gObjects);
    public abstract void removeGameObjects(List<Object> gObjects);
    public abstract void addDrawAble(List<IDrawable> drawAbles);
    public abstract void removeDrawAble(List<IDrawable> drawAbles);
    public abstract void addPhysicalSimulators(List<IPhysicalSimulator> pSim);
    public abstract void removePhysicalSimulators(List<IPhysicalSimulator> pSim);
    public abstract void addAIs(List<IAI> ai);  
    public abstract void removeAIs(List<IAI> ai);   
   
}
