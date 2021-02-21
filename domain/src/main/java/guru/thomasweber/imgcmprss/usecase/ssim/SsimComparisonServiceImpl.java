package guru.thomasweber.imgcmprss.usecase.ssim;

import java.awt.image.BufferedImage;

import guru.thomasweber.imgcmprss.usecase.ssim.util.SsimCalculator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SsimComparisonServiceImpl implements SsimComparisonService {

	private final SsimCalculator calculator;

	public SsimComparisonServiceImpl() {
		this.calculator = SsimCalculator.instance();
	}

	@Override
	public double compare(BufferedImage reference, BufferedImage comp) {
		double ssim8 = calculator.similarityScore(reference, comp, 8);
		double ssim16 = calculator.similarityScore(reference, comp, 16);
		return (3 * ssim8 + ssim16) / 4;
	}

}
