package guru.thomasweber.imgcmprss.optimizer.jpeg;

import guru.thomasweber.imgcmprss.model.ImageFormat;
import guru.thomasweber.imgcmprss.model.ImageSource;
import guru.thomasweber.imgcmprss.model.OptimizerResult;
import guru.thomasweber.imgcmprss.model.OptimizerType;
import guru.thomasweber.imgcmprss.model.vendor.ToolInstaller;
import guru.thomasweber.imgcmprss.usecase.OptimizerUseCase;

public class MozJpegOptimizerUseCase implements OptimizerUseCase {
	
	public static final String VENDOR_PATH = "vendor/mozjpeg";
	
	public MozJpegOptimizerUseCase() {
		ToolInstaller.install(VENDOR_PATH);
	}

	@Override
	public ImageFormat getImageFormat() {
		return ImageFormat.JPEG;
	}

	@Override
	public OptimizerType getType() {
		return OptimizerType.LOSSY;
	}

	@Override
	public OptimizerResult optimize(ImageSource source) {
		// TODO Auto-generated method stub
		return null;
	}

}
