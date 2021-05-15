package guru.thomasweber.imgcmprss.model.vendor;

/**
 * Exception for tool installation errors.
 * 
 * @author Thomas Weber
 */
public class ToolInstallationException extends RuntimeException {

	private static final long serialVersionUID = 8308281919120294045L;

	public ToolInstallationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ToolInstallationException(String message) {
		super(message);
	}

	public ToolInstallationException(Throwable cause) {
		super(cause);
	}

}
