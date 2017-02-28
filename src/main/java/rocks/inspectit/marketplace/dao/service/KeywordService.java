package rocks.inspectit.marketplace.dao.service;

import java.util.List;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.KeywordEntity;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.1.0-SNAPSHOT
 */
public interface KeywordService {
	List<KeywordEntity> getKeywordEntityListByAliasList(final List<String> strings);
}
