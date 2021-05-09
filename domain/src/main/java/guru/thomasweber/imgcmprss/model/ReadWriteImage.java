package guru.thomasweber.imgcmprss.model;

import java.io.OutputStream;

/**
 * Represents an image the can be read and written.
 * 
 * @author Thomas Weber
 */
public interface ReadWriteImage extends ImageSource {

	/**
	 * Returns an output stream to write the binary data of the image.
	 * 
	 * @return an output stream to write the binary data of the image
	 */
	OutputStream getOutputStream();

}
