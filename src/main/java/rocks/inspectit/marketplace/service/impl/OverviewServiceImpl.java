package rocks.inspectit.marketplace.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;
import rocks.inspectit.marketplace.dao.service.ProductService;
import rocks.inspectit.marketplace.service.OverviewService;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
@Service
public class OverviewServiceImpl implements OverviewService {

	private final ProductService productService;

	/**
	 * use constructor injection.
	 *
	 * @param productService {@link ProductService}
	 * @since 1.0.3-SNAPSHOT
	 */
	@Autowired
	public OverviewServiceImpl(final ProductService productService) {
		this.productService = productService;
	}

	/**
	 * ##todo describe.
	 *
	 * @param pageable {@link Pageable}
	 * @return {@link Page} of {@link ProductEntity}
	 * @since 1.0.8-SNAPSHOT
	 */
	@Override
	public Page<ProductEntity> getProductsByRatingAndPageable(List<String> limitToList, final Pageable pageable) {
		if (limitToList == null || limitToList.isEmpty()) {
			return this.productService.getAllProductsOrderedByRatingDesc(pageable);
		} else {
			return this.productService.getAllProductsFilteredByKeywordsAndOrderedByRatingDesc(limitToList, pageable);
		}
	}

	/**
	 * ##todo describe.
	 *
	 * @param pageable {@link Pageable}
	 * @return {@link Page} of {@link ProductEntity}
	 * @since 1.0.8-SNAPSHOT
	 */
	@Override
	public Page<ProductEntity> getProductsByTagAndPageable(final String param, List<String> limitToList, final Pageable pageable) {
		if (limitToList == null || limitToList.isEmpty()) {
			return this.productService.getPagedProductsByTagNameOrderedByDateAndDownloads(param, pageable);
		} else {
			return this.productService.getPagedProductsByTagNameFilteredByKeywordsOrderedByDateAndDownloads(param, limitToList, pageable);
		}
	}

	/**
	 * ##todo describe.
	 *
	 * @param pageable {@link Pageable}
	 * @return {@link Page} of {@link ProductEntity}
	 * @since 1.0.8-SNAPSHOT
	 */
	@Override
	public Page<ProductEntity> getProductsByPageable(List<String> limitToList, final Pageable pageable) {
		if (limitToList == null || limitToList.isEmpty()) {
			return this.productService.getPagedProductsByPageable(pageable);
		} else {
			return this.productService.getPagedProductsFilteredByKeywordsByPageable(limitToList, pageable);
		}
	}
}
