/*
 * Copyright by Peter Trebing; All rights reserved
 * 
 * mail@peter-trebing.de
 * http://www.peter-trebing.de
 * 
 * Created on 15.05.2005
 *
 */
package de.anschalter.gaming.framework.impl;

import java.awt.Graphics2D;

import de.anschalter.gaming.framework.IDrawable;

public abstract class AbstractPainter implements
        IDrawable {

    private int x = 0;
    private int y = 0;
    private int width = 0;
    private int height = 0;

    public AbstractPainter() {
    }

    public void init(Graphics2D drawable) {
    }

    public abstract void draw(Graphics2D g);

    public void reshape(Graphics2D drawable, int x, int y,
            int width, int height) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    public void displayChanged(Graphics2D drawable,
            boolean modeChanged, boolean deviceChanged) {
    }

    /**
     * @param x
     *            The x to set.
     */
    protected void setX(int x) {
        this.x = x;
    }

    /**
     * @return Returns the x.
     */
    protected int getX() {
        return x;
    }

    /**
     * @param y
     *            The y to set.
     */
    protected void setY(int y) {
        this.y = y;
    }

    /**
     * @return Returns the y.
     */
    protected int getY() {
        return y;
    }

    /**
     * @param width
     *            The width to set.
     */
    protected void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return Returns the width.
     */
    protected int getWidth() {
        return width;
    }

    /**
     * @param height
     *            The height to set.
     */
    protected void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return Returns the height.
     */
    protected int getHeight() {
        return height;
    }

}
