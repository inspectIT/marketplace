package rocks.inspectit.marketplace.enums;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
public enum ItemSize {
	SMALL(15),
	MEDIUM(30),
	BIG(50),
	HUGE(75),
	ALL(-1);

	private final int size;

	/**
	 * Simple Constructor.
	 * @param size {@link Integer}
	 */
	ItemSize(final int size) {
		this.size = size;
	}

	/**
	 * Simple getter.
	 * @return int
	 */
	public int getSize() {
		return size;
	}
}
