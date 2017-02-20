package rocks.inspectit.marketplace.mvc.app.model;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.7-SNAPSHOT
 */
public enum SortOptionEnum {
	DOWNLOADS("numberOfDownloads"),
	RECENT("creationDate"),
	RATING("rating"),
	FEATURED("featured"),
	PROMOTED("promoted");

	private final String sortPropertyName;

	SortOptionEnum(final String sortPropertyName) {
		this.sortPropertyName = sortPropertyName;
	}

	public String getValue() {
		return sortPropertyName;
	}
}
