package rocks.inspectit.marketplace.enums;

import java.util.Arrays;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
public enum SortBy {

	ASC("asc"),
	DESC("desc");

	private final String value;

	/**
	 * Simple Constructor.
	 * @param value {@link String}
	 */
	SortBy(final String value) {
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
	 * @return SortBy {@link SortBy}
	 */
	public static SortBy getEnumByString(final String value) {
		return Arrays.asList(SortBy.values()).stream().filter(f -> f.getValue().equalsIgnoreCase(value)).findFirst().orElse(SortBy.ASC);
	}
}
