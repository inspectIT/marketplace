package rocks.inspectit.marketplace.dao.service;

import java.util.UUID;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.RatingEntity;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.1.1-SNAPSHOT
 */
public interface RatingService {
	/**
	 * ## todo : describe.
	 *
	 * @param userName  {@link String}
	 * @param productId {@link UUID}
	 * @return {@link Boolean}
	 */
	boolean ratingExistForUserAndProduct(final String userName, final UUID productId);

	/**
	 * ## todo : describe.
	 *
	 * @param entity {@link RatingEntity}
	 * @return {@link RatingEntity}
	 */
	RatingEntity persistRatingEntity(final RatingEntity entity);
}
