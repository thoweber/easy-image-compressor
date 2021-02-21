package guru.thomasweber.imgcmprss.usecase.ssim.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
class Pair<X> {
	private final X primary;
	private final X secondary;
}
