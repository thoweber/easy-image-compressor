package guru.thomasweber.imgcmprss.usecase.ssim.util;

import static java.lang.Math.pow;

import java.awt.image.BufferedImage;
import java.util.Iterator;

/**
 * Singleton implementation of {@code SsimCalculator}.
 * 
 * @author rhys-e, Thomas Weber
 * @see https://en.wikipedia.org/wiki/Structural_similarity
 */
final class SsimCalculatorImpl implements SsimCalculator {

	private static final SsimCalculatorImpl INSTANCE = new SsimCalculatorImpl();

	private static final float K1 = 0.01f;
	private static final float K2 = 0.03f;

	private SsimCalculatorImpl() {
	}

	public static SsimCalculator instance() {
		return INSTANCE;
	}

	@Override
	public double similarityScore(final BufferedImage referenceImage, final BufferedImage compImage,
			final int winSize) {

		final WindowManager manager = new WindowManager(referenceImage, compImage, winSize);

		final int[] size = referenceImage.getColorModel().getComponentSize();
		final long L = (long) pow(2, size[0]) - 1;
		final double c1 = pow((K1 * L), 2);
		final double c2 = pow((K2 * L), 2);

		int numWindows = 0;
		double mssim = 0.0f;

		final Iterator<Pair<Window>> iterator = manager.getWindowContainer().iterator();

		// calculate ssim for each window
		while (iterator.hasNext()) {
			final Pair<Window> pair = iterator.next();

			final double[] yx = pair.getPrimary().getLumaValues();
			final double[] yy = pair.getSecondary().getLumaValues();

			final double mx = pair.getPrimary().getAverageLuma();
			final double my = pair.getSecondary().getAverageLuma();

			// calculate variance and covariance
			double sigxy = 0f;
			double sigsqx = 0f;
			double sigsqy = 0f;
			for (int i = 0; i < yx.length; i++) {
				sigsqx += pow((yx[i] - mx), 2);
				sigsqy += pow((yy[i] - my), 2);

				sigxy += (yx[i] - mx) * (yy[i] - my);
			}

			final double numPixelsInWin = (double) yx.length - 1;
			sigsqx /= numPixelsInWin;
			sigsqy /= numPixelsInWin;
			sigxy /= numPixelsInWin;

			// perform ssim calculation on window
			final double numerator = (2 * mx * my + c1) * (2 * sigxy + c2);
			final double denominator = (pow(mx, 2) + pow(my, 2) + c1) * (sigsqx + sigsqy + c2);

			final double ssim = numerator / denominator;

			mssim += ssim;
			numWindows++;
		}
		if (numWindows == 0) {
			return 0;
		}

		return mssim / (double) numWindows;
	}

}
