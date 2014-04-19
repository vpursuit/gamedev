/*
 * Copyright by Peter Trebing; All rights reserved
 * 
 * mail@peter-trebing.de
 * http://www.peter-trebing.de
 * 
 * Created on 29.03.2005
 *
 */
package de.anschalter.graphics;

import java.awt.Image;

/**
 * @author keeper
 *
 */
public interface IAnimation {
    /**
     * Adds an image to the animation with the specified duration (time to
     * display the image).
     */
    public void addFrame(Image image, long duration);

    /**
     * Starts this animation over from the beginning.
     */
    public void reset();

    /**
     * Updates this animation's current image (frame), if neccesary.
     */
    public void update(long elapsedTime);

    /**
     * Gets this Animation's current image. Returns null if this animation has
     * no images.
     */
    public Image getImage();
}