/*
 * Copyright by Peter Trebing; All rights reserved
 * 
 * mail@peter-trebing.de
 * http://www.peter-trebing.de
 * 
 * Created on 07.05.2005
 *
 */
package de.anschalter.dna.framework.input;

public interface IGameAction {

    public static final int DETECT_IS_PRESSED = 0;
    public static final int DETECT_INITIAL_PRESS_ONLY = 1;

    public abstract String getName();

    public abstract void reset();

    public abstract void tap();
 
    public abstract void release();

    public abstract void press();
    
    public abstract void press(int amount);
    
    public abstract boolean isPressed();

}