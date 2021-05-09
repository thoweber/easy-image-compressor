package guru.thomasweber.imgcmprss.usecase;

import guru.thomasweber.imgcmprss.model.ImageFormat;
import guru.thomasweber.imgcmprss.model.ImageSource;
import guru.thomasweber.imgcmprss.model.OptimizerResult;
import guru.thomasweber.imgcmprss.model.OptimizerType;

/**
 * Interface for image optimizers.
 * 
 * @author Thomas Weber
 */
public interface OptimizerUseCase {

	/**
	 * Returns the image format the {@code OptimizerUseCase} can be applied to.
	 * 
	 * @return the image format
	 */
	ImageFormat getImageFormat();

	/**
	 * Returns the type of the optimizer.
	 * 
	 * @return the type of the optimizer
	 */
	OptimizerType getType();

	/**
	 * Optimizes a given {@code ImageSource} returning the result as an
	 * {@code OptimizerResult}.
	 * 
	 * @param source the source image
	 * @return the result of the optimization
	 */
	OptimizerResult optimize(ImageSource source);

}
