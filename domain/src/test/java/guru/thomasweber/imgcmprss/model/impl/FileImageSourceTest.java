package guru.thomasweber.imgcmprss.model.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import guru.thomasweber.imgcmprss.TestUtils;
import guru.thomasweber.imgcmprss.model.ImageFormat;

class FileImageSourceTest {

	@Test
	void test_FileImageSource_constructor_set_parameters_as_expected() throws IOException {
		File file = new File("src/test/resources/ssim/original.jpg");
		FileImageSource fis = new FileImageSource(file);
		assertEquals(ImageFormat.JPEG, fis.getImageFormat());
	}
	
	@Test
	void test_asBufferedImage_returns_the_same_image_as_reading_directly() throws IOException {
		// given
		File file = new File("src/test/resources/ssim/original.jpg");
		BufferedImage referenceImage = TestUtils.readImage(file);
		FileImageSource fis = new FileImageSource(file);
		// when
		BufferedImage compareImage = fis.asBufferedImage();
		//then
		assertTrue(TestUtils.bufferedImagesEqual(referenceImage, compareImage));
	}

}
