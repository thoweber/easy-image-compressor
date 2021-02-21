package guru.thomasweber.imgcmprss.usecase.ssim.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import guru.thomasweber.imgcmprss.TestUtils;

class SsimCalculatorIT {

	@Test
	void test_comparing_test_images_leads_to_expected_results() throws IOException {
		// given
		BufferedImage referenceImage = TestUtils.readImage(new File("src/test/resources/ssim/original.jpg"));
		BufferedImage compareImage = TestUtils.readImage(new File("src/test/resources/ssim/mildly-optimized.jpg"));
		SsimCalculator calculator = SsimCalculatorImpl.instance();
		// when
		double result8 = calculator.similarityScore(referenceImage, compareImage, 8);
		double result16 = calculator.similarityScore(referenceImage, compareImage, 16);
		assertEquals(0.9858300283297764, result8);
		assertEquals(0.9915032204266269, result16);
	}

	@Test
	void test_comparing_an_image_with_itself_results_to_1() throws IOException {
		// given
		BufferedImage referenceImage = TestUtils.readImage(new File("src/test/resources/ssim/original.jpg"));
		BufferedImage compareImage = TestUtils.readImage(new File("src/test/resources/ssim/original.jpg"));
		SsimCalculator calculator = SsimCalculatorImpl.instance();
		// when
		double result8 = calculator.similarityScore(referenceImage, compareImage, 8);
		double result16 = calculator.similarityScore(referenceImage, compareImage, 16);
		assertEquals(1, result8);
		assertEquals(1, result16);
	}

	
}
