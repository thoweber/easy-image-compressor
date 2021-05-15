package guru.thomasweber.imgcmprss.model.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import javax.imageio.ImageIO;

import guru.thomasweber.imgcmprss.model.ImageFormat;
import guru.thomasweber.imgcmprss.model.ImageSource;

public class FileImageSource implements ImageSource {

	private final Path imagePath;
	private final ImageFormat imageFormat;
	@SuppressWarnings("java:S3077")
	private volatile BufferedImage bufferedImage;

	public FileImageSource(File image) throws IOException {
		this.imagePath = image.toPath().toAbsolutePath();
		this.imageFormat = ImageFormat.ofMimeType(Files.probeContentType(this.imagePath));
	}

	@Override
	public ImageFormat getImageFormat() {
		return this.imageFormat;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return Files.newInputStream(imagePath, StandardOpenOption.READ);
	}

	@Override
	public BufferedImage asBufferedImage() throws IOException {
		BufferedImage result = this.bufferedImage;
		if (result == null) {
			synchronized (this) {
				if (this.bufferedImage == null) {
					this.bufferedImage = result = loadBufferedImage();
				}
			}
		}
		return result;

	}

	private BufferedImage loadBufferedImage() throws IOException {
		try (var in = getInputStream()) {
			return ImageIO.read(in);
		}
	}

}
