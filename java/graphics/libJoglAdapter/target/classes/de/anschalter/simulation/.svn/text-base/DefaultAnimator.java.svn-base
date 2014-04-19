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

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;

/**
 * @author keeper
 * 
 */
public class DefaultAnimator implements OpenGLAnimator {

	private Animator animator;

	public DefaultAnimator() {
		animator = new FPSAnimator(60);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.anschalter.simulation.OpenGLAnimator#addGLAutoDrawable()
	 */
	public void add(GLAutoDrawable drawable) {
		animator.add(drawable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.anschalter.simulation.OpenGLAnimator#removeAutoDrawable()
	 */
	public void remove(GLAutoDrawable drawable) {
		animator.remove(drawable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.anschalter.simulation.OpenGLAnimator#start()
	 */
	public void start() {
		animator.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.anschalter.simulation.OpenGLAnimator#stop()
	 */
	public void stop() {
		animator.stop();
	}

}
