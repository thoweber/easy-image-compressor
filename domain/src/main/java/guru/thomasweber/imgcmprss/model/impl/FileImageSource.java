package guru.thomasweber.imgcmprss.model.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import javax.imageio.ImageIO;

import guru.thomasweber.imgcmprss.model.ImageFormat;
import guru.thomasweber.imgcmprss.model.ImageSource;

public class FileImageSource implements ImageSource {

	private final Path imagePath;
	private final ImageFormat imageFormat;
	private final byte[] bytes;

	public FileImageSource(File image) throws IOException {
		this.imagePath = image.toPath().toAbsolutePath();
		this.imageFormat = ImageFormat.ofMimeType(Files.probeContentType(this.imagePath));
		this.bytes = bufferImage();
	}

	private byte[] bufferImage() throws IOException {
		try (var fileChannel = (FileChannel) Files.newByteChannel(imagePath, StandardOpenOption.READ)) {
			var buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
			if (buffer.hasArray()) {
				return buffer.array();
			}
			var bufferedBytes = new byte[buffer.remaining()];
			buffer.get(bufferedBytes);
			return bufferedBytes;
		}
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
		return ImageIO.read(new ByteArrayInputStream(bytes));
	}

}
