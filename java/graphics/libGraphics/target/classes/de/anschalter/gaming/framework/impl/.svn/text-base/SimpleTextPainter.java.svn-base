/*
 * Copyright by Peter Trebing; All rights reserved
 * 
 * mail@peter-trebing.de
 * http://www.peter-trebing.de
 * 
 * Created on 13.05.2005
 *
 */
package de.anschalter.gaming.framework.impl;

import java.awt.Color;

import de.anschalter.gaming.framework.IDrawable;
import de.anschalter.gaming.framework.IGraphicsDevice;

public class SimpleTextPainter implements IDrawable {

	private static final int INDENT = 5;
	private static final int GAP = 2;
	private int lineNo;
	private int width;
	private int height;
	public static final Color DEFAULT_BACKGROUND = Color.BLUE;
	public static final Color DEFAULT_FOREGROUND = Color.LIGHT_GRAY;

	public SimpleTextPainter(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void init(IGraphicsDevice g) {
		clean(g);
	}

	public void draw(IGraphicsDevice g) {
		Color gC = g.getColor();
		g.setColor(DEFAULT_FOREGROUND);
		setLineNo(1);
		out(g, "SimpleTextPainter, Copyright By Peter Trebing 2005");
		g.setColor(gC);
	}

	/**
	 * @param g
	 */
	protected void out(IGraphicsDevice g, String s) {
		g.drawText(s, INDENT, lineNo * ScreenManager.FONT_SIZE + GAP);
	}

	protected void setLineNo(int line) {
		lineNo = line;
	}

	protected void nextLine() {
		lineNo++;
	}

	protected void clean(IGraphicsDevice g) {
		g.setColor(DEFAULT_BACKGROUND);
		g.fillRect(0, 0, width, height);
		g.setColor(DEFAULT_FOREGROUND);
		setLineNo(1);
	}

}
