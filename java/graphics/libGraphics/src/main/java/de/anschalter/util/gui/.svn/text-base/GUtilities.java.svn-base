package de.anschalter.util.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

public class GUtilities {

	public static void centerWindow(Component frame) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();

		if (frameSize.width > screenSize.width)
			frameSize.width = screenSize.width;
		if (frameSize.height > screenSize.height)
			frameSize.height = screenSize.height;

		frame.setLocation(
		(screenSize.width - frameSize.width) >> 1,
				(screenSize.height - frameSize.height) >> 1
		);
	}

}
