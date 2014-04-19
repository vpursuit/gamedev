package de.anschalter.util.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Hashtable;

public class Text2D {

	protected static final int FONT_SIZE = 24;

	private static final int INDENT = 5;
	private static final int GAP = 2;
	private int lineNo;
	private int width;
	private int height;

	private static final ColorModel glAlphaColorModel = new ComponentColorModel(
			ColorSpace.getInstance(ColorSpace.CS_sRGB),
			new int[] { 8, 8, 8, 8 }, true, false,
			ComponentColorModel.TRANSLUCENT, DataBuffer.TYPE_BYTE);

	private static final ColorModel glColorModel = new ComponentColorModel(
			ColorSpace.getInstance(ColorSpace.CS_sRGB),
			new int[] { 8, 8, 8, 0 }, false, false, ComponentColorModel.OPAQUE,
			DataBuffer.TYPE_BYTE);

	public static final Color DEFAULT_BACKGROUND = Color.BLACK;
	public static final Color DEFAULT_FOREGROUND = Color.LIGHT_GRAY;

	private BufferedImage bi;

	public Text2D(int width, int height) {
		this.width = width;
		this.height = height;
		init(width, height);
	}

	private void test() {
		clear();
		out("Hello world!");
	}

	private void init(int width, int height) {
		WritableRaster raster = null;
		// bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		if (false) {
			raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE,
					width, height, 4, null);
			bi = new BufferedImage(glAlphaColorModel, raster, false,
					new Hashtable());
		} else {
			raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE,
					width, height, 3, null);
			bi = new BufferedImage(glColorModel, raster, false, new Hashtable());
		}
	}

	public ByteBuffer getRasters() {
		out("Hello world!");
		byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer())
				.getData();
		ByteBuffer imageBuffer = ByteBuffer.allocate(data.length);
		imageBuffer.order(ByteOrder.nativeOrder());
		imageBuffer.put(data, 0, data.length);
		return imageBuffer;
	}

	protected void out(String s) {
		// g2D.drawString(s, INDENT, lineNo * FONT_SIZE + GAP);
		BufferedImage im = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		im.createGraphics().drawString(s, 0, 0);
		bi.getGraphics().drawImage(im, 0, 0, Color.BLACK, null);
	}

	protected void setLineNo(int line) {
		lineNo = line;
	}

	protected void nextLine() {
		lineNo++;
	}

	protected void clear() {
		Graphics2D g2D = (Graphics2D) bi.getGraphics();
		g2D.setColor(DEFAULT_BACKGROUND);
		g2D.fillRect(0, 0, width, height);
		g2D.setColor(DEFAULT_FOREGROUND);
		setLineNo(1);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
