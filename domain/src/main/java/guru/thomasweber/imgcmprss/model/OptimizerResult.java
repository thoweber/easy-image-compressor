package guru.thomasweber.imgcmprss.model;

import java.util.Optional;

/**
 * The result of an image optimizer.
 * 
 * @author Thomas Weber
 */
public interface OptimizerResult {

	/**
	 * Returns the reference image as an {@code ImageSource}.
	 * 
	 * @return the reference image as an {@code ImageSource}
	 */
	ImageSource getReferenceImage();

	/**
	 * Returns the produced image as an {@code ImageSource}.
	 * 
	 * @return the produced image as an {@code ImageSource}
	 */
	Optional<ImageSource> getProducedImage();

	/**
	 * The structural similarity between the reference and the produced image.
	 * 
	 * @return a value between {@code 0} and {@code 1}, where {@code 1} means
	 *         structural identity and {@code 0} means the images are totally
	 *         different.
	 */
	double getStructuralSimilarity();
	
}
