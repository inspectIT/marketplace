package rocks.inspectit.marketplace.dao.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.6-SNAPSHOT
 */
public interface ProductService {

	/**
	 * ## todo describe.
	 *
	 * @param defaultPageable {@link Pageable}
	 * @return {@link Page} of {@link ProductEntity}
	 */
	Page<ProductEntity> getPagedProductsByPageable(final Pageable defaultPageable);

	/**
	 * ## todo describe.
	 *
	 * @param pageable {@link Pageable}
	 * @return {@link Page} of {@link ProductEntity}
	 */
	Page<ProductEntity> getAllProductsOrderedByRatingDesc(final Pageable pageable);

	/**
	 * ## todo describe.
	 *
	 * @param tagName  {@link String}
	 * @param pageable {@link Pageable}
	 * @return {@link Page} of {@link ProductEntity}
	 */
	Page<ProductEntity> getPagedProductsByTagNameOrderedByDateAndDownloads(final String tagName, final Pageable pageable);

	/**
	 * ## todo describe.
	 *
	 * @param searchTerm {@link String}
	 * @param pageable   {@link Pageable}
	 * @return {@link Page} of {@link ProductEntity}
	 */
	Page<ProductEntity> getAllProductsBySearchTerm(final String searchTerm, final Pageable pageable);

	/**
	 * ## todo describe.
	 *
	 * @param productUuid {@link UUID}
	 * @return {@link Page} of {@link ProductEntity}
	 */
	ProductEntity getProductByProductUuid(final UUID productUuid);

	/**
	 * ## todo describe.
	 *
	 * @param limitToList {@link List} of {@link String}
	 * @param pageable    {@link Pageable}
	 * @return {@link Page} of {@link ProductEntity}
	 */
	Page<ProductEntity> getAllProductsFilteredByKeywordsAndOrderedByRatingDesc(final List<String> limitToList, final Pageable pageable);

	/**
	 * ## todo describe.
	 *
	 * @param value       {@link String}
	 * @param limitToList {@link List} of {@link String}
	 * @param pageable    {@link Pageable}
	 * @return {@link Page} of {@link ProductEntity}
	 */
	Page<ProductEntity> getPagedProductsByTagNameFilteredByKeywordsOrderedByDateAndDownloads(final String value, final List<String> limitToList, final Pageable pageable);

	/**
	 * ## todo describe.
	 *
	 * @param limitToList {@link List} of {@link String}
	 * @param pageable    {@link Pageable}
	 * @return {@link Page} of {@link ProductEntity}
	 */
	Page<ProductEntity> getPagedProductsFilteredByKeywordsByPageable(final List<String> limitToList, final Pageable pageable);

	/**
	 * ## todo : describe.
	 *
	 * @param productEntity {@link ProductEntity}
	 * @return {@link ProductEntity}
	 */
	ProductEntity persistProductEntity(final ProductEntity productEntity);
}
