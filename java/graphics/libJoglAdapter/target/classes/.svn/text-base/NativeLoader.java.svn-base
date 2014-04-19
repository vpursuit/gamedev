/**
 * 
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author Tom
 * 
 */
public class NativeLoader {

	static final String libraryName = "jogl.dll";
	static final File libraryFile;

	static {
		InputStream inputStream = NativeLoader.class.getClassLoader()
				.getResourceAsStream(libraryName);
		libraryFile = new File(libraryName);
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}