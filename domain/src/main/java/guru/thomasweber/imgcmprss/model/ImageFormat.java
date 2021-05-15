package guru.thomasweber.imgcmprss.model;

public enum ImageFormat {

	JPEG("image/jpeg"), UNKNOWN(null);

	private final String mimeType;

	private ImageFormat(String mimeType) {
		this.mimeType = mimeType;
	}
	
	public static ImageFormat ofMimeType(String mt) {
		if (mt == null) {
			throw new IllegalArgumentException("The given MIME type must not be null");
		}
		for (ImageFormat imgFormat : values()) {
			if (mt.equals(imgFormat.mimeType)) {
				return imgFormat;
			}
		}
		return UNKNOWN;
	}
}
