package rocks.inspectit.marketplace.service;

import java.util.UUID;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.7-SNAPSHOT
 */
public interface DetailService {

	/**
	 * ## todo describe
	 *
	 * @param productId {@link UUID}
	 * @return {@link ProductEntity}
	 */
	ProductEntity getProductEntityById(final UUID productId);

}
