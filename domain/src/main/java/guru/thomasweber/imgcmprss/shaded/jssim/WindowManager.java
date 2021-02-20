package guru.thomasweber.imgcmprss.shaded.jssim;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

class WindowManager {

	/**
	 * 8 is the default.
	 */
	private int WIN_SIZE = 16;// 8;

	private final WindowContainer windowContainer;

	public WindowManager(BufferedImage refImage, BufferedImage comparisonImage,
			final int winSize) throws SsimException {

		this.WIN_SIZE = winSize;

		if (refImage.getWidth() != comparisonImage.getWidth()
				|| refImage.getHeight() != comparisonImage.getHeight()) {
			throw new SsimException("Image dimmensions are not the same");
		}

		int width = getNearestMultipleOf(refImage.getWidth(), this.WIN_SIZE);
		int height = getNearestMultipleOf(refImage.getHeight(), this.WIN_SIZE);

		refImage = rescaleImage(refImage, width, height);
		comparisonImage = rescaleImage(comparisonImage, width, height);

		// all but 1 window should be 8x8 blocks (unless the image happens
		// to evenly divide by 8x8, in which case, all windows will be 8x8)
		final int numWinX = width / this.WIN_SIZE;
		final int numWinY = height / this.WIN_SIZE;

		final List<Window> refWindows = getSsimWindowsForImage(refImage,
				numWinX, numWinY);

		final List<Window> compWindows = getSsimWindowsForImage(
				comparisonImage, numWinX, numWinY);

		this.windowContainer = new WindowContainer(refWindows, compWindows);
	}

	private int getNearestMultipleOf(final int dimension, final int multiple) {
		return Math.round(dimension / multiple) * multiple;

	}

	private BufferedImage rescaleImage(final BufferedImage image,
			final int width, final int height) {
		if (image.getWidth() == width && image.getHeight() == height) {
			return image;
		}

		BufferedImage newImage = new BufferedImage(width, height,
				image.getType());

		Graphics g = newImage.createGraphics();
		g.drawImage(image, 0, 0, width, height, null);
		g.dispose();

		return newImage;
	}

	private List<Window> getSsimWindowsForImage(final BufferedImage image,
			final int numXWindows, final int numYWindows) {
		final List<Window> windows = new ArrayList<Window>();

		for (int i = 0; i < numXWindows; i++) {
			for (int j = 0; j < numYWindows; j++) {
				windows.add(new Window(image, this.WIN_SIZE,
						(i * this.WIN_SIZE), (j * this.WIN_SIZE)));
			}
		}

		return windows;
	}

	public WindowContainer getWindowContainer() {
		return this.windowContainer;
	}

	public static class WindowContainer implements Iterable<Pair<Window>> {

		private final Collection<Window> primary;
		private final Collection<Window> secondary;

		private WindowContainer(final Collection<Window> primary,
				final Collection<Window> secondary) throws SsimException {
			if (primary.size() != secondary.size()) {
				throw new SsimException("");
			}

			this.primary = primary;
			this.secondary = secondary;
		}

		@Override
		public Iterator<Pair<Window>> iterator() {
			return new PairIterator<Window>(this.primary, this.secondary);
		}
	}
}
