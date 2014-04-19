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

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;

/**
 * @author keeper
 * 
 */
public class OpenGLSimulator {

	private static final Dimension FRAME_SIZE = new Dimension(600, 800);

	private GLAutoDrawable drawable;

	private OpenGLAnimator animator;

	public OpenGLSimulator(GLEventListener renderer) {
		getGLAutoDrawable().addGLEventListener(renderer);
		Frame frame = new Frame("Fountain");
		frame.add((Canvas) getGLAutoDrawable());
		frame.setSize(FRAME_SIZE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				getAnimator().stop();
				System.exit(0);
			}
		});
		frame.setVisible(true);
		getAnimator().add(getGLAutoDrawable());
		getAnimator().start();
	}

	public GLAutoDrawable getGLAutoDrawable() {
		if (drawable == null) {
			drawable = new GLCanvas();
		}
		return drawable;
	}

	public OpenGLAnimator getAnimator() {
		if (animator == null) {
			animator = new DefaultAnimator();
		}
		return animator;
	}
}
