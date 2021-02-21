package guru.thomasweber.imgcmprss.usecase.ssim.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

class PairIteratorTest {

	@Test
	void test_Constructor_enforces_same_size() {
		// given
		List<String> strings2 = Arrays.asList("A", "B");
		List<String> strings3 = Arrays.asList("A", "B", "C");
		// when / then
		assertThrows(IllegalArgumentException.class, () -> {
			new PairIterator<>(strings2, strings3);
		});
	}

	@Test
	void test_iterator_returns_correct_pairs() {
		// given
		List<String> primary = Arrays.asList("A", "B", "C");
		List<String> secondary = Arrays.asList("1", "2", "3");
		// when
		PairIterator<String> pairIterator = new PairIterator<>(primary, secondary);
		// then
		assertTrue(pairIterator.hasNext());
		Pair<String> pair = pairIterator.next();
		assertEquals("A", pair.getPrimary());
		assertEquals("1", pair.getSecondary());
		assertTrue(pairIterator.hasNext());
		pair = pairIterator.next();
		assertEquals("B", pair.getPrimary());
		assertEquals("2", pair.getSecondary());
		assertTrue(pairIterator.hasNext());
		pair = pairIterator.next();
		assertEquals("C", pair.getPrimary());
		assertEquals("3", pair.getSecondary());
		assertFalse(pairIterator.hasNext());
	}

	@Test
	void test_remove_throws_UnsupportedOperationException() {
		// given
		PairIterator<String> pairIterator = new PairIterator<>(Collections.emptyList(), Collections.emptyList());
		// when / then
		assertThrows(UnsupportedOperationException.class, () -> {
			pairIterator.remove();
		});
	}
}
