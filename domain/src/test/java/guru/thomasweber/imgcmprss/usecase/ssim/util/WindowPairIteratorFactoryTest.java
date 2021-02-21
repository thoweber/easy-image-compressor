package guru.thomasweber.imgcmprss.usecase.ssim.util;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class WindowPairIteratorFactoryTest {

	@Test
	void test_cannot_be_instantiated() throws NoSuchMethodException, SecurityException {
		Constructor<WindowPairIteratorFactory> constructor = WindowPairIteratorFactory.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		assertThrows(InvocationTargetException.class, () -> {
			constructor.newInstance();
		});
	}

	@Test
	void test_of_returns_an_expected_iterator() {
		// given
		BufferedImage refImage = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
		BufferedImage comparisonImage = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
		// when
		Iterator<Pair<Window>> iterator = WindowPairIteratorFactory.of(refImage, comparisonImage, 8);
		// then
		List<Pair<Window>> list = new ArrayList<>();
		iterator.forEachRemaining(list::add);
		assertEquals(16, list.size());
	}

	@Test
	void test_of_throws_IllegalArgumentException_if_images_differ_in_size() {
		// given
		BufferedImage refImage = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
		BufferedImage comparisonImage = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
		// when
		assertThrows(IllegalArgumentException.class, () -> {
			WindowPairIteratorFactory.of(refImage, comparisonImage, 8);
		});
	}

	@ParameterizedTest
	@MethodSource(value = "nearestMulitpleOfValues")
	void test_nextMulitpleOf(int[] values) {
		int multiple = 8;
		assertEquals(values[1], WindowPairIteratorFactory.nextMultipleOf(values[0], multiple));
	}

	static List<int[]> nearestMulitpleOfValues() {
		return Arrays.asList(new int[] { 1, 8 }, new int[] { 15, 16 }, new int[] { 16, 16 });
	}

	@Test
	void test_placeImage_for_correctly_sized_image_returns_original() {
		// given
		BufferedImage correctSize = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
		// when
		BufferedImage result = WindowPairIteratorFactory.placeImage(correctSize, 128, 128);
		// then
		assertSame(correctSize, result);
	}

	@ParameterizedTest
	@MethodSource(value = "placeImageValues")
	void test_placeImage_for_wrongly_sized_image_returns_image_of_requested_size(BufferedImage wronglySized) {
		// when
		BufferedImage result = WindowPairIteratorFactory.placeImage(wronglySized, 128, 256);
		// then
		assertEquals(128, result.getWidth());
		assertEquals(256, result.getHeight());
	}

	static List<BufferedImage> placeImageValues() {
		return Arrays.asList(new BufferedImage(120, 120, BufferedImage.TYPE_INT_ARGB),
				new BufferedImage(128, 120, BufferedImage.TYPE_INT_ARGB),
				new BufferedImage(120, 256, BufferedImage.TYPE_INT_ARGB));
	}
}
