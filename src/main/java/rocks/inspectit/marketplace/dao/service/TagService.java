package rocks.inspectit.marketplace.dao.service;

import java.util.List;
import java.util.UUID;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.7-SNAPSHOT
 */
public interface TagService {

	/**
	 * ## todo describe.
	 *
	 * @param tagName as {@link String}
	 * @return {@link List} of {@link UUID}
	 */
	List<UUID> getAllProductUuidByTagName(final String tagName);

}
