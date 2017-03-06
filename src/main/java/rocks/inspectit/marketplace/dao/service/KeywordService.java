package rocks.inspectit.marketplace.dao.service;

import java.util.List;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.KeywordEntity;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.1.0-SNAPSHOT
 */
public interface KeywordService {

	/**
	 * ## todo : describe.
	 *
	 * @param strings {@link List} of {@link String}
	 * @return {@link List} of {@link KeywordEntity}
	 */
	List<KeywordEntity> getKeywordEntityListByAliasList(final List<String> strings);
}
