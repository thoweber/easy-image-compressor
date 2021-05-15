package guru.thomasweber.imgcmprss.model.vendor;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ToolInstaller {

	private ToolInstaller() {
		throw new IllegalStateException("no instances");
	}

	public static void install(String vendorPath) {
		var vendorInstallDir = createVendorDir(getInstallationDirectory(), vendorPath);

		for (File tool : getResourceAsFile(vendorPath).listFiles()) {
			var installedTool = vendorInstallDir.resolve(tool.getName());
			if (!Files.exists(installedTool)) {
				copyResource(tool, vendorInstallDir);
			}
		}
	}

	static void copyResource(File tool, Path vendorInstallDir) {
		var installPath = vendorInstallDir.resolve(tool.getName());
		try {
			Files.copy(tool.toPath(), installPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new ToolInstallationException(
					"Cannot create installation at " + installPath.toAbsolutePath().toString(), e);
		}
	}

	static Path createVendorDir(Path installDir, String vendorPath) {
		var vendorDir = installDir.resolve(vendorPath);
		if (!Files.exists(vendorDir)) {
			try {
				return Files.createDirectories(vendorDir);
			} catch (IOException e) {
				throw new ToolInstallationException(
						"Cannot create vendor dir at " + vendorDir.toAbsolutePath().toString(), e);
			}
		} else if (!Files.isDirectory(vendorDir)) {
			throw new ToolInstallationException(
					"Vendor dir is not a directory; check " + vendorDir.toAbsolutePath().toString());
		}
		return vendorDir;
	}

	static File getResourceAsFile(String res) {
		var url = ToolInstaller.class.getClassLoader().getResource(res);
		try {
			return new File(url.toURI());
		} catch (URISyntaxException e) {
			log.warn("Error creating file from classloader URL", e);
			return new File(url.getPath());
		}
	}

	static Path getInstallationDirectory() {
		return Path.of("").toAbsolutePath();
	}
}
