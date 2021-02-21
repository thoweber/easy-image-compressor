package guru.thomasweber.imgcmprss.usecase.ssim.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

class WindowManager {

	private final int windowSize;

	private final WindowContainer windowContainer;

	public WindowManager(BufferedImage refImage, BufferedImage comparisonImage, final int winSize) {

		this.windowSize = winSize;

		if (refImage.getWidth() != comparisonImage.getWidth() || refImage.getHeight() != comparisonImage.getHeight()) {
			throw new IllegalArgumentException("Image dimmensions are not the same");
		}

		int width = getNearestMultipleOf(refImage.getWidth(), this.windowSize);
		int height = getNearestMultipleOf(refImage.getHeight(), this.windowSize);

		refImage = rescaleImage(refImage, width, height);
		comparisonImage = rescaleImage(comparisonImage, width, height);

		// all but 1 window should be 8x8 blocks (unless the image happens
		// to evenly divide by 8x8, in which case, all windows will be 8x8)
		final int numWinX = width / this.windowSize;
		final int numWinY = height / this.windowSize;

		final List<Window> refWindows = getSsimWindowsForImage(refImage, numWinX, numWinY);

		final List<Window> compWindows = getSsimWindowsForImage(comparisonImage, numWinX, numWinY);

		this.windowContainer = new WindowContainer(refWindows, compWindows);
	}

	private int getNearestMultipleOf(final int dimension, final int multiple) {
		return Math.round(dimension / multiple) * multiple;

	}

	private BufferedImage rescaleImage(final BufferedImage image, final int width, final int height) {
		if (image.getWidth() == width && image.getHeight() == height) {
			return image;
		}

		BufferedImage newImage = new BufferedImage(width, height, image.getType());

		Graphics g = newImage.createGraphics();
		g.drawImage(image, 0, 0, width, height, null);
		g.dispose();

		return newImage;
	}

	private List<Window> getSsimWindowsForImage(final BufferedImage image, final int numXWindows,
			final int numYWindows) {
		final List<Window> windows = new ArrayList<>();

		for (int i = 0; i < numXWindows; i++) {
			for (int j = 0; j < numYWindows; j++) {
				windows.add(new Window(image, this.windowSize, (i * this.windowSize), (j * this.windowSize)));
			}
		}

		return windows;
	}

	public WindowContainer getWindowContainer() {
		return this.windowContainer;
	}
}
