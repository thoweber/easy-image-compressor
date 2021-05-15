package guru.thomasweber.imgcmprss.model.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import guru.thomasweber.imgcmprss.model.ImageFormat;

class FileImageSourceTest {

	@Test
	void test_FileImageSource_constructor_set_parameters_as_expected() throws IOException {
		File file = new File("src/test/resources/ssim/original.jpg");
		FileImageSource fis = new FileImageSource(file);
		assertEquals(ImageFormat.JPEG, fis.getImageFormat());
		assertNotNull(fis.asBufferedImage());
	}

}
