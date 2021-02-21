package guru.thomasweber.imgcmprss.usecase.ssim.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class WindowPairIteratorFactory {

	private WindowPairIteratorFactory() {
		throw new IllegalStateException("no instances");
	}

	public static Iterator<Pair<Window>> of(final BufferedImage refImage, final BufferedImage comparisonImage,
			final int windowSize) {
		if (refImage.getWidth() != comparisonImage.getWidth() || refImage.getHeight() != comparisonImage.getHeight()) {
			throw new IllegalArgumentException("Image dimensions are not the same");
		}

		int width = nextMultipleOf(refImage.getWidth(), windowSize);
		int height = nextMultipleOf(refImage.getHeight(), windowSize);

		BufferedImage normalizedRefImage = placeImage(refImage, width, height);
		BufferedImage normalizedComparisonImage = placeImage(comparisonImage, width, height);

		final int numWinX = width / windowSize;
		final int numWinY = height / windowSize;

		final List<Window> refWindows = getSsimWindowsForImage(normalizedRefImage, numWinX, numWinY, windowSize);
		final List<Window> compWindows = getSsimWindowsForImage(normalizedComparisonImage, numWinX, numWinY,
				windowSize);
		return new PairIterator<>(refWindows, compWindows);
	}

	static int nextMultipleOf(final int dimension, final int multiple) {
		int modulus = dimension % multiple;
		if (modulus == 0) {
			return dimension;
		}
		return dimension + multiple - modulus;
	}

	static BufferedImage placeImage(final BufferedImage image, final int width, final int height) {
		if (image.getWidth() == width && image.getHeight() == height) {
			return image;
		}

		BufferedImage newImage = new BufferedImage(width, height, image.getType());
		Graphics g = newImage.createGraphics();
		g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
		g.dispose();
		return newImage;
	}

	private static List<Window> getSsimWindowsForImage(final BufferedImage image, final int numXWindows,
			final int numYWindows, final int windowSize) {
		final List<Window> windows = new ArrayList<>();

		for (int i = 0; i < numXWindows; i++) {
			for (int j = 0; j < numYWindows; j++) {
				windows.add(new Window(image, windowSize, (i * windowSize), (j * windowSize)));
			}
		}

		return windows;
	}

}
