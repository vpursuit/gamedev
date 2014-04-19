package de.anschalter.test;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;

import de.anschalter.util.gl.GLInfo;

public class GLInfoViewer implements GLEventListener {

	public static void main(String[] args) {

		new GLInfoViewer();

	}

	public GLInfoViewer() {
		// Frame testFrame = new Frame();
		GLCanvas canvas = new GLCanvas();
		canvas.addGLEventListener(this);
		GLInfo.printGLVersion(System.out, canvas.getGL());
		// testFrame.add(canvas);
		// testFrame.setVisible(true);
	}

	public void display(GLAutoDrawable drawable) {
	}

	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
			boolean deviceChanged) {
	}

	public void init(GLAutoDrawable drawable) {
		GLInfo.printGLVersion(System.out, drawable.getGL());
		System.exit(0);
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
	}

}
