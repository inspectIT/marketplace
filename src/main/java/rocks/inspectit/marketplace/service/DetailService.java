package rocks.inspectit.marketplace.service;

import java.util.List;
import java.util.UUID;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.KeywordEntity;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.RatingEntity;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.UserEntity;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.7-SNAPSHOT
 */
public interface DetailService {

	/**
	 * ## todo describe.
	 *
	 * @param productId {@link UUID}
	 * @return {@link ProductEntity}
	 */
	ProductEntity getProductEntityById(final UUID productId);

	/**
	 * @param userName {@link String}
	 * @return {@link UserEntity}
	 */
	UserEntity getUserByUserName(final String userName);

	/**
	 * @param commaSeparatedKeywords {@link String}
	 * @return {@link List} of {@link KeywordEntity}
	 */
	List<KeywordEntity> getKeywordEntityListByAlias(final String commaSeparatedKeywords);

	/**
	 * ## todo : describe
	 *
	 * @param productEntity {@link ProductEntity}
	 * @return {@link ProductEntity}
	 */
	ProductEntity persistProductEntity(final ProductEntity productEntity);

	/**
	 * ## todo : describe
	 *
	 * @param entity {@link RatingEntity}
	 * @return {@link RatingEntity}
	 * @since 1.1.1-SNAPSHOT
	 */
	RatingEntity persistRatingEntity(final RatingEntity entity);
}
