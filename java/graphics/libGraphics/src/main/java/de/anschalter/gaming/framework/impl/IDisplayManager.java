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

import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.image.BufferedImage;

public interface IDisplayManager {

	public abstract DisplayMode[] getCompatibleDisplayModes();

	public abstract DisplayMode findFirstCompatibleMode();

	public abstract DisplayMode findFirstCompatibleMode(DisplayMode modes[]);

	public abstract DisplayMode getCurrentDisplayMode();

	/**
	 * Determines if two display modes "match". Two display modes match if they
	 * have the same resolution, bit depth, and refresh rate. The bit depth is
	 * ignored if one of the modes has a bit depth of
	 * DisplayMode.BIT_DEPTH_MULTI. Likewise, the refresh rate is ignored if one
	 * of the modes has a refresh rate of DisplayMode.REFRESH_RATE_UNKNOWN.
	 */
	public abstract boolean displayModesMatch(DisplayMode mode1,
			DisplayMode mode2);

	public abstract void initScreen(DisplayMode displayMode, boolean fullScreen);

	public abstract Graphics2D getGraphics();

	public abstract void update();

	public abstract Window getScreen();

	public abstract int getX();

	public abstract int getY();

	public abstract int getWidth();

	public abstract int getHeight();

	public abstract void restoreScreen();

	/**
	 * Creates an image compatible with the current display.
	 */
	public abstract BufferedImage createCompatibleImage(int w, int h,
			int transparancy);

}