package guru.thomasweber.imgcmprss.usecase.ssim.util;

import java.util.Collection;
import java.util.Iterator;

class WindowContainer implements Iterable<Pair<Window>> {

	private final Collection<Window> primary;
	private final Collection<Window> secondary;

	WindowContainer(final Collection<Window> primary, final Collection<Window> secondary) {
		if (primary.size() != secondary.size()) {
			throw new IllegalArgumentException("Sizes of window collections must match");
		}

		this.primary = primary;
		this.secondary = secondary;
	}

	@Override
	public Iterator<Pair<Window>> iterator() {
		return new PairIterator<>(this.primary, this.secondary);
	}
}