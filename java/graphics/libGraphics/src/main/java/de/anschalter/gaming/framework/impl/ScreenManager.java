package de.anschalter.gaming.framework.impl;

import java.awt.DisplayMode;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;

/**
 * The ScreenManager class manages initializing and displaying full screen
 * graphics modes.
 */
public class ScreenManager implements IDisplayManager {

	/* A medium sized display configuration for testing purposes */
	protected static final DisplayMode DEFAULTMODE = new DisplayMode(800, 600,
			16, DisplayMode.REFRESH_RATE_UNKNOWN);

	protected static final int FONT_SIZE = 24;

	protected static final DisplayMode POSSIBLE_MODES[] = {
			new DisplayMode(1280, 1024, 32, 0),
			new DisplayMode(1280, 1024, 24, 0),
			new DisplayMode(1280, 1024, 16, 0),
			new DisplayMode(1280, 800, 32, 0),
			new DisplayMode(1280, 800, 24, 0),
			new DisplayMode(1280, 800, 16, 0),
			new DisplayMode(1024, 768, 32, 0),
			new DisplayMode(1024, 768, 24, 0),
			new DisplayMode(1024, 768, 16, 0),
			new DisplayMode(800, 600, 32, 0), new DisplayMode(800, 600, 24, 0),
			new DisplayMode(800, 600, 16, 0), new DisplayMode(640, 480, 32, 0),
			new DisplayMode(640, 480, 24, 0), new DisplayMode(640, 480, 16, 0) };

	private static Object mutex = new Object();
	private GraphicsDevice device;
	private JFrame frame;
	private static ScreenManager _instance;

	/**
	 * Creates a new ScreenManager object.
	 */
	private ScreenManager() {
		GraphicsEnvironment environment = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		device = environment.getDefaultScreenDevice();
		this.frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIgnoreRepaint(true);
		frame.setResizable(false);

	}

	public static synchronized ScreenManager getInstance() {
		if (_instance == null) {
			_instance = new ScreenManager();
		}
		return _instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.anschalter.gaming.framework.impl.IDisplayManager#getCompatibleDisplayModes
	 * ()
	 */
	public DisplayMode[] getCompatibleDisplayModes() {
		return device.getDisplayModes();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.anschalter.gaming.framework.impl.IDisplayManager#findFirstCompatibleMode
	 * ()
	 */
	public DisplayMode findFirstCompatibleMode() {
		return findFirstCompatibleMode(POSSIBLE_MODES);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.anschalter.gaming.framework.impl.IDisplayManager#findFirstCompatibleMode
	 * (java.awt.DisplayMode[])
	 */
	public DisplayMode findFirstCompatibleMode(DisplayMode modes[]) {

		GraphicsDevice device = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		DisplayMode currentMode = device.getDisplayMode();

		for (int i = 0; i < modes.length; i++) {
			if (displayModesMatch(modes[i], currentMode)) {
				return modes[i];
			}
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.anschalter.gaming.framework.impl.IDisplayManager#getCurrentDisplayMode
	 * ()
	 */
	public DisplayMode getCurrentDisplayMode() {
		return device.getDisplayMode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.anschalter.gaming.framework.impl.IDisplayManager#displayModesMatch
	 * (java.awt.DisplayMode, java.awt.DisplayMode)
	 */
	public boolean displayModesMatch(DisplayMode mode1, DisplayMode mode2)

	{
		if (mode1.getWidth() != mode2.getWidth()
				|| mode1.getHeight() != mode2.getHeight()) {
			return false;
		}

		if (mode1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI
				&& mode2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI
				&& mode1.getBitDepth() != mode2.getBitDepth()) {
			return false;
		}

		if (mode1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN
				&& mode2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN
				&& mode1.getRefreshRate() != mode2.getRefreshRate()) {
			return false;
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.anschalter.gaming.framework.impl.IDisplayManager#setFullScreen(java
	 * .awt.DisplayMode, javax.swing.JFrame)
	 */
	public void initScreen(DisplayMode displayMode, boolean fullScreen) {

		if (displayMode != null) {

			if (fullScreen && device.isFullScreenSupported()) {
				frame.setUndecorated(true);
				device.setFullScreenWindow(frame);
			} else {
				frame.setSize(displayMode.getWidth(), displayMode.getHeight());
			}
			frame.setVisible(true);

			if (device.isDisplayChangeSupported()) {
				try {
					device.setDisplayMode(displayMode);
				} catch (IllegalArgumentException ex) {
				}
			}

			// avoid potential deadlock in 1.4.1_02
			try {
				EventQueue.invokeAndWait(new Runnable() {
					public void run() {
						frame.createBufferStrategy(2);
					}
				});
			} catch (InterruptedException ex) {
				// ignore
			} catch (InvocationTargetException ex) {
				// ignore
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.anschalter.gaming.framework.impl.IDisplayManager#getGraphics()
	 */
	public Graphics2D getGraphics() {
		if (frame != null) {
			BufferStrategy strategy = frame.getBufferStrategy();
			return (Graphics2D) strategy.getDrawGraphics();
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.anschalter.gaming.framework.impl.IDisplayManager#update()
	 */
	public void update() {
		if (frame != null) {
			BufferStrategy strategy = frame.getBufferStrategy();
			if (!strategy.contentsLost()) {
				strategy.show();
			}
		}
		// Sync the display on some systems.
		// (on Linux, this fixes event queue problems)
		Toolkit.getDefaultToolkit().sync();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.anschalter.gaming.framework.impl.IDisplayManager#getFullScreenWindow()
	 */
	public Window getScreen() {
		return frame;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.anschalter.gaming.framework.impl.IDisplayManager#getX()
	 */
	public int getX() {
		if (frame != null) {
			return frame.getX();
		} else {
			return 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.anschalter.gaming.framework.impl.IDisplayManager#getY()
	 */
	public int getY() {
		if (frame != null) {
			return frame.getY();
		} else {
			return 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.anschalter.gaming.framework.impl.IDisplayManager#getWidth()
	 */
	public int getWidth() {
		if (frame != null) {
			return frame.getWidth();
		} else {
			return 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.anschalter.gaming.framework.impl.IDisplayManager#getHeight()
	 */
	public int getHeight() {
		if (frame != null) {
			return frame.getHeight();
		} else {
			return 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.anschalter.gaming.framework.impl.IDisplayManager#restoreScreen()
	 */
	public void restoreScreen() {
		if (frame != null) {
			frame.dispose();
		}
		device.setFullScreenWindow(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.anschalter.gaming.framework.impl.IDisplayManager#createCompatibleImage
	 * (int, int, int)
	 */
	public BufferedImage createCompatibleImage(int w, int h, int transparancy) {
		if (frame != null) {
			GraphicsConfiguration gc = frame.getGraphicsConfiguration();
			return gc.createCompatibleImage(w, h, transparancy);
		}
		return null;
	}
}