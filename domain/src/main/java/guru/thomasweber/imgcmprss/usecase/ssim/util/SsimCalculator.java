package guru.thomasweber.imgcmprss.usecase.ssim.util;

import java.awt.image.BufferedImage;

/**
 * Calculates the SSIM score for two images.
 * 
 * @author Thomas Weber
 */
public interface SsimCalculator {

	/**
	 * Returns an instance of the {@code SsimCalculator}.
	 * 
	 * @return an instance of the {@code SsimCalculator}
	 */
	static SsimCalculator instance() {
		return SsimCalculatorImpl.instance();
	}

	/**
	 * Calculates the strucural similarity between two images.
	 * 
	 * @param referenceImage the reference image
	 * @param compImage      the comparison image
	 * @param winSize        the window size
	 * @return a value between {@code 0} and {@code 1}, where {@code 1} means
	 *         structural identity and {@code 0} means the images are totally
	 *         different.
	 */
	double similarityScore(BufferedImage referenceImage, BufferedImage compImage, int winSize);

}