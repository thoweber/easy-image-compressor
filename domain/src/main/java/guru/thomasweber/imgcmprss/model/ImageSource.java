package guru.thomasweber.imgcmprss.model;

import java.awt.image.BufferedImage;
import java.io.InputStream;

/**
 * Represents a source for an image.
 * 
 * @author Thomas Weber
 */
public interface ImageSource {

	/**
	 * Returns the image format of the source.
	 * 
	 * @return the image format of the source
	 */
	ImageFormat getImageFormat();

	/**
	 * Returns an input stream to read the binary data of the image.
	 * 
	 * @return an input stream to read the binary data of the image
	 */
	InputStream getInputStream();

	/**
	 * Returns the image as a {@code BufferedImage}.
	 * 
	 * @return the image as a {@code BufferedImage}
	 */
	BufferedImage asBufferedImage();

}
