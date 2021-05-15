package guru.thomasweber.imgcmprss.model.vendor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

class ToolInstallerTest {

	static final String VENDOR_BASE_PATH = "vendor";
	static final String VENDOR_PATH = "vendor/test";

	@Test
	void test_cannot_be_instantiated() throws NoSuchMethodException, SecurityException {
		Constructor<ToolInstaller> constructor = ToolInstaller.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		assertThrows(InvocationTargetException.class, () -> {
			constructor.newInstance();
		});
	}

	@Test
	void test_getResourceAsFile_can_return_vendor_path() {
		File vendorDir = ToolInstaller.getResourceAsFile(VENDOR_PATH);
		assertTrue(vendorDir.isDirectory());
		File[] files = vendorDir.listFiles();
		assertEquals(1, files.length);
		assertEquals("test-tool", files[0].getName());
	}

	@Test
	void test_install_installs_files() throws IOException {
		// clear existing installation, if exists
		if (Files.exists(Path.of(VENDOR_BASE_PATH))) {
			Files.walk(Path.of(VENDOR_BASE_PATH)).sorted(Comparator.reverseOrder()).map(Path::toFile)
					.forEach(File::delete);
		}
		// when
		ToolInstaller.install(VENDOR_PATH);
		// then
		assertTrue(Files.exists(Path.of(VENDOR_PATH, "test-tool")));
		// and delete
		Files.walk(Path.of(VENDOR_BASE_PATH)).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
	}

}
