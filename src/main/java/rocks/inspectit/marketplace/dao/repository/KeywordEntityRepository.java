package rocks.inspectit.marketplace.dao.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.KeywordEntity;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.7-SNAPSHOT
 */
public interface KeywordEntityRepository extends CrudRepository<KeywordEntity, UUID> {

	/**
	 * find all keywords by alias list.
	 * alis are in lower case, map alias list to lower case.
	 *
	 * @param aliasList {@link List} of {@link String}
	 * @return {@link List} of {@link KeywordEntity}
	 */
	List<KeywordEntity> findByAliasIn(final List<String> aliasList);

}

