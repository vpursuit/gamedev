package de.anschalter.gaming.framework.impl;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import de.anschalter.gaming.framework.IGraphicsDevice;

public class Graphics2DDevice implements IGraphicsDevice {

	private Graphics2D dev;

	public Graphics2DDevice(Graphics2D dev) {
		this.dev = dev;
	}

	public void dispose() {
		dev.dispose();
	}

	public void drawText(String s, int x, int y) {
		dev.drawString(s, x, y);
	}

	public void fillRect(int x, int y, int width, int height) {
		dev.fillRect(x, y, width, height);
	}

	public void drawImage(Image im, int x, int y) {
		dev.drawImage(im, x, y, null);
	}

	public void setColor(Color c) {
		dev.setColor(c);
	}

	public Color getColor() {
		return dev.getColor();
	}

    @Override
    public void swapBuffers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void makeContextCurrent() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void createWindow(String name, int width, int height) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
