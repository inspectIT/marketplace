package rocks.inspectit.marketplace.mvc.app.model;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.7-SNAPSHOT
 */
public enum SortOptionEnum {
	DOWNLOADS("numberOfDownloads"),
	RATING("avgRating", "sumRating"), // workaround for {@link DashboardService}
	RECENT("creationDate"),
	NAME("name"),
	FEATURED("name", "featured"), // workaround for {@link DashboardService}
	PROMOTED("name", "promoted"); // workaround for {@link DashboardService}

	private String[] sortOptionParameter;

	/**
	 * simple constructor.
	 *
	 * @param sortOptionParameter {@link String}
	 */
	SortOptionEnum(final String... sortOptionParameter) {
		this.sortOptionParameter = sortOptionParameter;
	}

	/**
	 * Array fort sorting and ordering of JPA Result.
	 * use first value of enum for sorting only; use second value as param for predicate (featured or promoted) or for sorting (rating).
	 *
	 * @return array of {@link String}
	 */
	public String[] getValue() {
		return sortOptionParameter;
	}
}
