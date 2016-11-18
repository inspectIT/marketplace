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

	OrderBy(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static OrderBy getEnumByString(final String value) {
		return Arrays.asList(OrderBy.values()).stream().filter(f -> f.getValue().equalsIgnoreCase(value)).findFirst().orElse(OrderBy.DATE);
	}
}
