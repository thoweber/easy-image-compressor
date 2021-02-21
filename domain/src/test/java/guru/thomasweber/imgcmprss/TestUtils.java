package guru.thomasweber.imgcmprss;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public final class TestUtils {

	private TestUtils() {
		throw new IllegalStateException();
	}

	
	public static BufferedImage readImage(File file) throws IOException {
		try (final InputStream is = new FileInputStream(file)) {
			return ImageIO.read(is);
		}
	}
}
