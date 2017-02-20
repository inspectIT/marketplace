package rocks.inspectit.marketplace.dao.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.TagEntity;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.6-SNAPSHOT
 */
public interface TagEntityRepository extends CrudRepository<TagEntity, UUID> {

	/**
	 * ## todo describe.
	 *
	 * @param tagName {@link String}
	 * @return {@link List} of {@link UUID}
	 * @since 1.0.6-SNAPSHOT
	 */
	List<UUID> findAllByTagName(final String tagName);
}

