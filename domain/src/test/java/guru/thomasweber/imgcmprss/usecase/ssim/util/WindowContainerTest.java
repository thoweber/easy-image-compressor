package guru.thomasweber.imgcmprss.usecase.ssim.util;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WindowContainerTest {

	@Mock
	Window window;

	@Test
	void test_WindowContainer_throws_SsimException_when_window_collections_are_of_different_size() {
		// given
		Collection<Window> windows2 = Arrays.asList(window, window);
		Collection<Window> windows3 = Arrays.asList(window, window, window);
		// when/then
		assertThrows(IllegalArgumentException.class, () -> {
			new WindowContainer(windows2, windows3);
		});
	}

	@Test
	void test_WindowContainer_returns_an_Iterator_with_correct_pairs() {
		// given
		Collection<Window> windowsA = Arrays.asList(window, window);
		Collection<Window> windowsB = Arrays.asList(window, window);
		WindowContainer container = new WindowContainer(windowsA, windowsB);
		// when
		Iterator<Pair<Window>> iterator = container.iterator();
		// then
		assertNotNull(iterator.next());
		assertNotNull(iterator.next());
	}
}
