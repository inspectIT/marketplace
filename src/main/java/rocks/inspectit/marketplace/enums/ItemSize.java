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

	ItemSize(final int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}
}
