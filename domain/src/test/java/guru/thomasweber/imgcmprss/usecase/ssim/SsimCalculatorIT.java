package guru.thomasweber.imgcmprss.usecase.ssim;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class SsimCalculatorIT {

	@Test
	void test_comparing_test_images_leads_to_expected_results() throws SsimException, IOException {
		// given
		File referenceFile = new File("src/test/resources/ssim/original.jpg");
		File compareFile = new File("src/test/resources/ssim/mildly-optimized.jpg");
		SsimCalculator calculator = new SsimCalculator(referenceFile);
		// when
		double result8 = calculator.compareTo(compareFile, 8);
		double result16 = calculator.compareTo(compareFile, 16);
		assertEquals(0.9812371527632519, result8);
		assertEquals(0.9903481582523859, result16);
	}
	
	@Test
	void test_comparing_an_image_with_itself_results_to_1() throws SsimException, IOException {
		// given
		File referenceFile = new File("src/test/resources/ssim/original.jpg");
		File compareFile = new File("src/test/resources/ssim/original.jpg");
		SsimCalculator calculator = new SsimCalculator(referenceFile);
		// when
		double result8 = calculator.compareTo(compareFile, 8);
		double result16 = calculator.compareTo(compareFile, 16);
		assertEquals(1, result8);
		assertEquals(1, result16);
	}
}
