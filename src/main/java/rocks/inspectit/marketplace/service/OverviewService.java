package rocks.inspectit.marketplace.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.7-SNAPSHOT
 */
public interface OverviewService {

	/**
	 * ##todo describe.
	 *
	 * @param limitToList {@link List} of {@link String}
	 * @param pageable    {@link Pageable}
	 * @return {@link Page} of {@link ProductEntity}
	 * @since 1.0.8-SNAPSHOT
	 */
	Page<ProductEntity> getProductsByRatingAndPageable(final List<String> limitToList, final Pageable pageable);

	/**
	 * ##todo describe.
	 *
	 * @param param       {@link String}
	 * @param limitToList {@link List} of {@link String}
	 * @param pageable    {@link Pageable}
	 * @return {@link Page} of {@link ProductEntity}
	 * @since 1.0.8-SNAPSHOT
	 */
	Page<ProductEntity> getProductsByTagAndPageable(final String param, final List<String> limitToList, final Pageable pageable);

	/**
	 * ##todo describe.
	 *
	 * @param limitToList {@link List} of {@link String}
	 * @param pageable    {@link Pageable}
	 * @return {@link Page} of {@link ProductEntity}
	 * @since 1.0.8-SNAPSHOT
	 */
	Page<ProductEntity> getProductsByPageable(final List<String> limitToList, final Pageable pageable);
}
