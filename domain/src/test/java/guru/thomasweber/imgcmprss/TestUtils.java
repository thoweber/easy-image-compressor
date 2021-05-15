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

	public static boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
		if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
			for (int x = 0; x < img1.getWidth(); x++) {
				for (int y = 0; y < img1.getHeight(); y++) {
					if (img1.getRGB(x, y) != img2.getRGB(x, y))
						return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}
}
