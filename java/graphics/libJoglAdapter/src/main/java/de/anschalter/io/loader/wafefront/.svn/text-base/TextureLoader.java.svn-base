package de.anschalter.io.loader.wafefront;

import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

/**
 * A utility class to load textures for JOGL
 * 
 * @author Kevin Glass
 */
public class TextureLoader {

	private HashMap<String, Texture> table = new HashMap<String, Texture>();
	private static GLU glu = new GLU();

	private static final ColorModel glAlphaColorModel = new ComponentColorModel(
			ColorSpace.getInstance(ColorSpace.CS_sRGB),
			new int[] { 8, 8, 8, 8 }, true, false,
			ComponentColorModel.TRANSLUCENT, DataBuffer.TYPE_BYTE);

	private static final ColorModel glColorModel = new ComponentColorModel(
			ColorSpace.getInstance(ColorSpace.CS_sRGB),
			new int[] { 8, 8, 8, 0 }, false, false, ComponentColorModel.OPAQUE,
			DataBuffer.TYPE_BYTE);

	public TextureLoader() {
	}

	private static int createTextureID(GL gl) {
		int[] tmp = new int[1];
		gl.glGenTextures(1, tmp, 0);
		return tmp[0];
	}

	public void initTexture(String name, BufferedImage bufferedImage) {
		Texture tex = getTexture(name, bufferedImage, GL.GL_TEXTURE_2D, // target
				GL.GL_RGBA, // dst pixel format
				GL.GL_LINEAR, // min filter (unused)
				GL.GL_LINEAR, // mag filter (unused)
				true, // wrap?
				false); // mipmap?

		table.put(name, tex);
	}

	public void initTexture(String name, String resourceName)
			throws IOException {
		BufferedImage bufferedImage = loadImage(resourceName);
		Texture tex = getTexture(name, bufferedImage, GL.GL_TEXTURE_2D, // target
				GL.GL_RGBA, // dst pixel format
				GL.GL_LINEAR, // min filter (unused)
				GL.GL_LINEAR, // mag filter (unused)
				true, // wrap?
				false); // mipmap?

		table.put(name, tex);
	}

	public Texture getTexture(String name) throws IOException {
		return (Texture) table.get(name);
	}

	public static Texture getTexture(String name,
			final BufferedImage bufferedImage, int target, int dstPixelFormat,
			int minFilter, int magFilter, boolean wrap, boolean mipmapped) {
		int srcPixelFormat = 0;

		GL gl = GLU.getCurrentGL();

		// create the texture ID for this texture
		int textureID = createTextureID(gl);
		System.out.println("Loading texture " + name + " to ID: " + textureID);
		Texture texture = new Texture(target, textureID);

		// bind this texture
		gl.glBindTexture(target, textureID);

		// Getting the real Width/Height of the Texture in the Memory
		int realWidth = get2Fold(bufferedImage.getWidth());
		int realHeight = get2Fold(bufferedImage.getHeight());

		// don't need it?
		texture.setBufferedImage(bufferedImage);
		texture.setWidth(realWidth);
		texture.setHeight(realHeight);

		if (bufferedImage.getColorModel().hasAlpha()) {
			srcPixelFormat = GL.GL_RGBA;
		} else {
			srcPixelFormat = GL.GL_RGB;
		}

		// convert that image into a byte buffer of texture data
		int texWidth = get2Fold(bufferedImage.getWidth());
		int texHeight = get2Fold(bufferedImage.getHeight());

		texture.setTextureHeight(texHeight);
		texture.setTextureWidth(texWidth);

		ByteBuffer textureBuffer = convertImageData(bufferedImage);
		int wrapMode = wrap ? GL.GL_REPEAT : GL.GL_CLAMP;

		if (target == GL.GL_TEXTURE_2D) {
			gl.glTexParameteri(target, GL.GL_TEXTURE_WRAP_S, wrapMode);
			gl.glTexParameteri(target, GL.GL_TEXTURE_WRAP_T, wrapMode);
			gl.glTexParameteri(target, GL.GL_TEXTURE_MIN_FILTER, minFilter);
			gl.glTexParameteri(target, GL.GL_TEXTURE_MAG_FILTER, magFilter);
		}

		// create either a series of mipmaps of a single texture image based on
		// what's loaded
		if (mipmapped) {
			glu.gluBuild2DMipmaps(target, dstPixelFormat, realWidth,
					realHeight, srcPixelFormat, GL.GL_UNSIGNED_BYTE,
					textureBuffer);
		} else {
			gl.glTexImage2D(target, 0, dstPixelFormat, realWidth, realHeight,
					0, srcPixelFormat, GL.GL_UNSIGNED_BYTE, textureBuffer);
		}

		return texture;
	}

	private static int get2Fold(int fold) {
		int ret = 2;
		while (ret < fold) {
			ret *= 2;
		}
		return ret;
	}

	@SuppressWarnings("rawtypes")
	public static ByteBuffer convertImageData(BufferedImage bufferedImage) {
		ByteBuffer imageBuffer = null;
		WritableRaster raster;
		BufferedImage texImage;

		int texWidth = get2Fold(bufferedImage.getWidth());
		int texHeight = get2Fold(bufferedImage.getHeight());

		if (bufferedImage.getColorModel().hasAlpha()) {
			raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE,
					texWidth, texHeight, 4, null);
			texImage = new BufferedImage(glAlphaColorModel, raster, false,
					new Hashtable());
		} else {
			raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE,
					texWidth, texHeight, 3, null);
			texImage = new BufferedImage(glColorModel, raster, false,
					new Hashtable());
		}

		Graphics g = texImage.getGraphics();
		g.drawImage(bufferedImage, 0, 0, null);

		byte[] data = ((DataBufferByte) texImage.getRaster().getDataBuffer())
				.getData();

		imageBuffer = ByteBuffer.allocate(data.length);
		imageBuffer.order(ByteOrder.nativeOrder());
		imageBuffer.put(data, 0, data.length);

		return imageBuffer;
	}

	private static BufferedImage loadImage(String ref) throws IOException {
		File file = new File(ref);

		if (!file.isFile()) {
			throw new IOException("Cannot find: " + ref);
		}

		BufferedImage bufferedImage = ImageIO.read(new BufferedInputStream(
				new FileInputStream(file)));

		return bufferedImage;
	}
}
