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
	 * @param defaultPageable
	 * @return
	 */
	Page<ProductEntity> getPagedProductsByPageable(final Pageable defaultPageable);

	/**
 	 * ## todo describe.
	 *
	 * @param pageable
	 * @return
	 */
	Page<ProductEntity> getAllProductsOrderedByRatingDesc(final Pageable pageable);

	/**
 	 * ## todo describe.
	 *
	 * @param tagName
	 * @param pageable
	 * @return
	 */
	Page<ProductEntity> getPagedProductsByTagNameOrderedByDateAndDownloads(final String tagName, final Pageable pageable);

	/**
 	 * ## todo describe.
	 *
	 * @param searchTerm
	 * @param pageable
	 * @return
	 */
	Page<ProductEntity> getAllProductsBySearchTerm(final String searchTerm, final Pageable pageable);

	/**
	 * ## todo describe.
	 *
	 * @param productUuid
	 * @return
	 */
	ProductEntity getProductByProductUuid(final UUID productUuid);

	// todo check

	/**
	 * ## todo describe.
	 *
	 * @param limitToList
	 * @param pageable
	 * @return
	 */
	Page<ProductEntity> getAllProductsFilteredByKeywordsAndOrderedByRatingDesc(final List<String> limitToList, final Pageable pageable);

	// todo check

	/**
	 * ## todo describe.
	 *
	 * @param value
	 * @param limitToList
	 * @param pageable
	 * @return
	 */
	Page<ProductEntity> getPagedProductsByTagNameFilteredByKeywordsOrderedByDateAndDownloads(final String value, final List<String> limitToList, final Pageable pageable);

	// todo check

	/**
	 * ## todo describe.
	 *
	 * @param limitToList
	 * @param pageable
	 * @return
	 */
	Page<ProductEntity> getPagedProductsFilteredByKeywordsByPageable(final List<String> limitToList, final Pageable pageable);


}
