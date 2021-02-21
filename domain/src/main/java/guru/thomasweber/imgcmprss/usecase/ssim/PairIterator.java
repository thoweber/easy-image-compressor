package guru.thomasweber.imgcmprss.usecase.ssim;

import java.util.Collection;
import java.util.Iterator;

class PairIterator<E> implements Iterator<Pair<E>> {
	private final Iterator<E> primaryIterator;
	private final Iterator<E> secondaryIterator;

	public PairIterator(Collection<E> primary, Collection<E> secondary) {
		if (primary.size() != secondary.size()) {
			throw new IllegalArgumentException("Collections must match in size");
		}
		this.primaryIterator = primary.iterator();
		this.secondaryIterator = secondary.iterator();
	}

	@Override
	public boolean hasNext() {
		return primaryIterator.hasNext();
	}

	@Override
	public Pair<E> next() {
		return new Pair<>(primaryIterator.next(), secondaryIterator.next());
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
