package de.anschalter.util.gl;

import java.io.PrintStream;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import javax.media.opengl.GL;

public class GLInfo {

	public static void printGLVersion(PrintStream out, GL gl) {

		out.println("GL vendor: " + gl.glGetString(GL.GL_VENDOR));
		out.println("GL version: " + gl.glGetString(GL.GL_VERSION));
		out.println("GL renderer: " + gl.glGetString(GL.GL_RENDERER));
		out.println("GL extensions:");
		String[] extensions = gl.glGetString(GL.GL_EXTENSIONS).split(" ");
		int i = 0;
		while (i < extensions.length) {
			out.print("  ");
			String ext = extensions[i++];
			out.print(ext);
			if (i < extensions.length) {
				for (int j = 0; j < (40 - ext.length()); j++) {
					out.print(" ");
				}
				out.println(extensions[i++]);
			} else {
				out.println();
			}
		}

		
	//	 Set<MyEnum> s = Collections.synchronizedSet(EnumSet.noneOf(GL.));
		
		int[] caps = new int[5];
		gl.glGetIntegerv(GL.GL_MAX_MODELVIEW_STACK_DEPTH, caps, 0);
		gl.glGetIntegerv(GL.GL_MAX_PROJECTION_STACK_DEPTH, caps, 1);
		
		out.println("GL_MAX_MODELVIEW_STACK_DEPTH: " + caps[0]);
		out.println("GL_MAX_PROJECTION_STACK_DEPTH: " + caps[1]);

	}

}
