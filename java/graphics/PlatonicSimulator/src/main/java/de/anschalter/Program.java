package de.anschalter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 
 * @author keeper
 */
public class Program {
	static {
		String os = System.getProperty("os.name");
		os = os.toLowerCase();
		if (os.contains("win")) {
			extractAndLoadLibrary("jogl.dll");
			extractAndLoadLibrary("jogl_awt.dll");
			extractAndLoadLibrary("jogl_cg.dll");
		} else if (os.contains("mac")) {
			extractAndLoadLibrary("libjogl.jnilib");
			extractAndLoadLibrary("libjogl_awt.jnilib");
			extractAndLoadLibrary("libjogl_cg.jnilib");
		}

	}

	private static void extractAndLoadLibrary(String libraryName) {
		InputStream inputStream = Program.class.getClassLoader()
				.getResourceAsStream(libraryName);
		File libraryFile = new File(libraryName);
		System.out.println(libraryFile.getAbsolutePath());
		libraryFile.deleteOnExit();
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(
					libraryFile);
			byte[] buffer = new byte[8192];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) > 0) {
				fileOutputStream.write(buffer, 0, bytesRead);
			}
			fileOutputStream.close();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new PlatonicSolidsSimulator().run();
	}
}
