package rocks.inspectit.marketplace.enums;

import java.util.Arrays;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
public enum OrderBy {
	NAME("name"),
	USERNAME("username"),
	DATE("date"),
	RATING("rating");

	private final String value;

	/**
	 * Simple Constructor.
	 * @param value {@link String}
	 */
	OrderBy(final String value) {
		this.value = value;
	}

	/**
	 * Simple Getter.
	 * @return String {@link String}
	 */
	public String getValue() {
		return value;
	}

	/**
	 * ##TODO.
	 *
	 * @param value {@link String}
	 * @return OrderBy {@link OrderBy}
	 */
	public static OrderBy getEnumByString(final String value) {
		return Arrays.asList(OrderBy.values()).stream().filter(f -> f.getValue().equalsIgnoreCase(value)).findFirst().orElse(OrderBy.DATE);
	}
}
