package rocks.inspectit.marketplace.mvc.advice.model;

/**
 * POJO for JSON serialisation.
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.2-SNAPSHOT
 */
public class ErrorMessage {
	private final String url;
	private final String ex;

	/**
	 * Simple Constructor.
	 *
	 * @param url {@link String}
	 * @param ex {@link Exception}
	 */
	public ErrorMessage(final String url, final Exception ex) {
		this.url = url;
		this.ex = ex.getLocalizedMessage();
	}

	public String getUrl() {
		return url;
	}

	public String getEx() {
		return ex;
	}
}
