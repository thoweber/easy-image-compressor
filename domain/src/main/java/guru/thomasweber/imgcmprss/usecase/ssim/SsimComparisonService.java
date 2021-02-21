package guru.thomasweber.imgcmprss.usecase.ssim;

import java.awt.image.BufferedImage;

/**
 * Service to compare an image against a reference using Structured Similarity
 * (SSIM).
 * 
 * @author Thomas Weber
 */
public interface SsimComparisonService {

	/**
	 * Performs a weighed SSIM comparison between the reference and the comparison
	 * image.
	 * 
	 * @param reference the reference image
	 * @param comp      the comparison image
	 * @return a value between {@code 0} and {@code 1}, where {@code 1} means
	 *         structural identity and {@code 0} means the images are totally
	 *         different.
	 */
	double compare(BufferedImage reference, BufferedImage comp);
}
