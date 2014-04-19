/*
 * Copyright by Peter Trebing; All rights reserved
 * 
 * mail@peter-trebing.de
 * http://www.peter-trebing.de
 * 
 * Created on 07.05.2005
 *
 */
package de.anschalter.gaming.framework;

import java.awt.Color;
import java.awt.Image;

public interface IGraphicsDevice {

    public abstract void createWindow(String name, int width, int height);

    public abstract void dispose();

    public abstract void swapBuffers();

    public abstract void makeContextCurrent();

    public abstract void fillRect(int x, int y, int width, int height);

    public abstract void drawText(String s, int x, int y);

    public abstract void drawImage(Image im, int x, int y);

    public abstract void setColor(Color c);

    public abstract Color getColor();
}
