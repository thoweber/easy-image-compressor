package guru.thomasweber.imgcmprss.usecase.ssim;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import guru.thomasweber.imgcmprss.TestUtils;

class SsimComparisonServiceImplIT {

	@Test
	void test_compare_returns_the_expected_results_for_test_data() throws IOException {
		// given
		BufferedImage referenceImage = TestUtils.readImage(new File("src/test/resources/ssim/original.jpg"));
		BufferedImage compareImage = TestUtils.readImage(new File("src/test/resources/ssim/mildly-optimized.jpg"));
		SsimComparisonService service = new SsimComparisonServiceImpl();
		// when
		final double ssimScore = service.compare(referenceImage, compareImage);
		// then
		assertEquals(0.9872483263539891, ssimScore);
	}
	
}
