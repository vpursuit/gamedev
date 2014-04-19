/*
 * Copyright by Peter Trebing; All rights reserved.
 * 
 * mail@peter-trebing.de
 * http://www.peter-trebing.de
 * 
 * Created on 30.05.2007
 *
 */
package de.anschalter.simulation;

import javax.media.opengl.GLAutoDrawable;

/**
 * @author keeper
 * 
 */
public interface OpenGLAnimator {

	public abstract void start();

	public abstract void stop();

	public abstract void add(GLAutoDrawable drawable);

	public abstract void remove(GLAutoDrawable drawable);

}
