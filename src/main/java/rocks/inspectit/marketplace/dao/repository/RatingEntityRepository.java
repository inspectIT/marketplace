package rocks.inspectit.marketplace.dao.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.RatingEntity;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.6-SNAPSHOT
 */
public interface RatingEntityRepository extends CrudRepository<RatingEntity, UUID> {

	/**
	 * ## todo : describe.
	 *
	 * @param userUuid    {@link UUID}
	 * @param productUuid {@link UUID}
	 * @return {@link List} of {@link RatingEntity}
	 */
	List<RatingEntity> findAllByUserEntityUserUuidAndProductEntityProductUuid(final UUID userUuid, final UUID productUuid);

	/**
	 * ## todo : describe.
	 *
	 * @param userName    {@link String}
	 * @param productUuid {@link UUID}
	 * @return {@link List} of {@link RatingEntity}
	 * @since 1.1.1-SNAPSHOT
	 */
	List<RatingEntity> findAllByUserEntityNameAndProductEntityProductUuid(final String userName, final UUID productUuid);
}

