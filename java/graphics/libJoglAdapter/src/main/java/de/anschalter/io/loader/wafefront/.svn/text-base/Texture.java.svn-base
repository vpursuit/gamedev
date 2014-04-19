package de.anschalter.io.loader.wafefront;

import java.awt.image.BufferedImage;

import javax.media.opengl.GL;

/**
 * A texture to be bound within JOGl
 * 
 * @author Kevin Glass
 */
public class Texture {
	
	private int target;
	private int textureID;
	private int height;
	private int width;
	private int texWidth;
	private int texHeight;
	private float widthRatio;
	private float heightRatio;

	private BufferedImage buffer;

	static Texture lastbind = null;

	public Texture(int target, int textureID) {
		this.target = target;
		this.textureID = textureID;

	}

	public void setBufferedImage(BufferedImage buffer) {
		this.buffer = buffer;
	}

	public BufferedImage getBufferedImage() {
		return buffer;
	}

	public void bind(GL gl) {
		if ((lastbind == null) || (lastbind != this)) {
			gl.glBindTexture(target, textureID);
		}
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getImageHeight() {
		return height;
	}

	public int getImageWidth() {
		return width;
	}

	public float getHeight() {
		return heightRatio;
	}

	public float getWidth() {
		return widthRatio;
	}

	public void setTextureHeight(int texHeight) {
		this.texHeight = texHeight;
		setHeight();
	}

	public void setTextureWidth(int texWidth) {
		this.texWidth = texWidth;
		setWidth();
	}

	private void setHeight() {
		if (texHeight != 0) {
			heightRatio = ((float) height) / texHeight;
		}
	}

	private void setWidth() {
		if (texWidth != 0) {
			widthRatio = ((float) width) / texWidth;
		}
	}
}
