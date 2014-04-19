package de.anschalter.graphics;

import java.awt.Image;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

/**
 * @author keeper
 * 
 */
public class ImageFactory {

	public enum Dimension {

		DIM16x16("dim_16x16"), DIM32x32("dim_32x32"), DIMOTHER("dim_other");

		private final String locator;

		Dimension(String locator) {
			this.locator = locator;
		}

		public String toString() {
			return locator;
		}

	}

	private static final String IMAGE_LOCATION = "de/anschalter/gaming/resources/images/";

	private static ImageFactory _instance;

	private static final Object mutex = new Object();

	private final Map<String, Image> imageMap;

	private static final Logger log = Logger.getLogger(ImageFactory.class
			.getName());

	/**
	 * Returns an Image with the predefined dimension and name
	 * 
	 * @param dim
	 *            the dimension DIM16x16, DIM32x32 or DIMOTHER
	 * @param name
	 *            the image name
	 * @return the image, null if not found
	 */
	public static Image getImage(Dimension dim, String name) {
		final String adress = dim + "/" + name;
		Image result = getInstance().imageMap.get(adress);
		if (result == null) {
			synchronized (mutex) {
				final ClassLoader loader = getInstance().getClass()
						.getClassLoader();
				URL url = loader.getResource(IMAGE_LOCATION + adress + ".png");
				if (url == null)
					url = loader.getResource(IMAGE_LOCATION + adress + ".jpg");
				if (url == null) {
					log.log(Level.WARNING, "Image not found: " + adress);
					return null;
				} else {
					result = new ImageIcon(url).getImage();
					getInstance().imageMap.put(adress, result);
				}
			}
		}
		return result;
	}

	/**
	 * Returns an image with dimension 16x16 pixel and a given name
	 * 
	 * @param name
	 *            the image name
	 * @return the image, null if not found
	 */
	public static Image get16x16Image(String name) {
		return getImage(Dimension.DIM16x16, name);
	}

	/**
	 * Returns an image with dimension 32x32 pixel and a given name
	 * 
	 * @param name
	 *            the image name
	 * @return the image, null if not found
	 */
	public static Image get32x32Image(String name) {
		return getImage(Dimension.DIM32x32, name);
	}

	/**
	 * Returns an image with unkown dimension with a given name
	 * 
	 * @param name
	 *            the image name
	 * @return the image, null if not found
	 */
	public static Image getOtherImage(String name) {
		return getImage(Dimension.DIMOTHER, name);
	}

	private ImageFactory() {
		imageMap = new HashMap<String, Image>();
	}

	private static synchronized final ImageFactory getInstance() {
		if (_instance == null) {
			_instance = new ImageFactory();
		}
		return _instance;
	}

}