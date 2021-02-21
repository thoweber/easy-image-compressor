package guru.thomasweber.imgcmprss.usecase.ssim;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.image.BufferedImage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.thomasweber.imgcmprss.usecase.ssim.util.SsimCalculator;

@ExtendWith(MockitoExtension.class)
class SsimComparisonServiceImplTest {

	@Mock
	SsimCalculator calculator;

	@InjectMocks
	SsimComparisonServiceImpl serviceImpl;

	@Test
	void test_compare_applies_expected_weighing() {
		// given
		lenient().when(calculator.similarityScore(any(BufferedImage.class), any(BufferedImage.class), eq(8)))
				.thenReturn(.5);
		lenient().when(calculator.similarityScore(any(BufferedImage.class), any(BufferedImage.class), eq(16)))
				.thenReturn(.75);
		// when
		double score = serviceImpl.compare(new BufferedImage(10, 10, BufferedImage.TYPE_3BYTE_BGR),
				new BufferedImage(10, 10, BufferedImage.TYPE_3BYTE_BGR));
		// then
		/*
		 * weighing is {@code (3x score8 + score16) ÷ 4}
		 */
		assertEquals(0.5625, score);
	}

}
