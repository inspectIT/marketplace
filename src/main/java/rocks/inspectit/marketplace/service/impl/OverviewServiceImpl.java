package rocks.inspectit.marketplace.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;
import rocks.inspectit.marketplace.dao.service.ProductService;
import rocks.inspectit.marketplace.mvc.app.model.SortOptionEnum;
import rocks.inspectit.marketplace.service.OverviewService;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
@Service
public class OverviewServiceImpl implements OverviewService {
	private static final Logger LOG = LoggerFactory.getLogger(OverviewServiceImpl.class);

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
	 * FIXME
	 * there are currently different methods returning exactly what the front-end expects, due to missing entities. please implement entities and rewrite functions properly
	 * maybe use pagination instead of "topX"
	 * <p>
	 * <p>
	 * Since this function returns products for the dashboard it's sufficient
	 * Check value of type.
	 * If download: return top 20 of most downloaded products.
	 * If rating: return top 20 of highest rated products.
	 * If recent: return top 20 of newest uploaded products.
	 * Else: return 20 products with corresponding tag ordered by creation date and # of downloads.
	 *
	 * @param sortOption of {@link SortOptionEnum} order by "downloads", "ratings", "creation_date" or "tag_name"
	 * @return {@link List} of {@link ProductEntity}
	 */
	@Override
	public List<ProductEntity> getSimpleDashboardOverviewByType(final SortOptionEnum sortOption) {
		return this.getPopulatedProductEntity(sortOption, this.getDefaultPageable(sortOption.getValue())).getContent();
	}

	/**
	 * ## todo describe
	 *
	 * @param sortOrder   {@link String}  Sort order; maybe "desc" or "asc"
	 * @param sortOption  {@link SortOptionEnum} defines query - use custom query TAG for values "featured" or "promoted"
	 * @param limitToList {@link List} of {@link String} - use custom query KEYWORDS to limit results
	 * @param pageable    {@link Pageable} paging information
	 * @return {@link Page} of {@link ProductEntity}
	 */
	@Override
	public Page<ProductEntity> getPageableProductEntityByFilter(final String sortOrder, final SortOptionEnum sortOption,
			final List<String> limitToList, final Pageable pageable) {
		final Pageable customPageable = this.getPopulatedPageable(pageable.getPageNumber(), pageable.getPageSize(), sortOrder, sortOption.getValue());
		if (limitToList.isEmpty()) {
			return this.getPopulatedProductEntity(sortOption, customPageable);
		} else {
			return this.getPopulatedProductEntityByFilter(sortOption, limitToList, customPageable);
		}
	}

	/**
	 * ## todo describe.
	 *
	 * @param type
	 * @return
	 */
	private Pageable getDefaultPageable(final String type) {
		return this.getPopulatedPageable(0, 20, "DESC", type);
	}

	/**
	 * ## todo describe.
	 *
	 * @param page
	 * @param size
	 * @param sortOrder
	 * @param properties
	 * @return
	 */
	private Pageable getPopulatedPageable(final int page, final int size, final String sortOrder, final String... properties) {
		final Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.fromString(sortOrder), properties));
		return pageable;
	}

	/**
	 * ## todo describe.
	 *
	 * @param sortOption
	 * @param pageable
	 * @return
	 */
	private Page<ProductEntity> getPopulatedProductEntity(final SortOptionEnum sortOption, final Pageable pageable) {
		// download; recent; featured; promoted;
		if (sortOption.equals(SortOptionEnum.RATING)) {
			return this.productService.getAllProductsOrderedByRatingDesc(pageable);
		} else if (sortOption.equals(SortOptionEnum.FEATURED) || sortOption.equals(SortOptionEnum.PROMOTED)) {
			LOG.info(String.format("Type \"%s\" didn't match \"download\". \"rating\" or \"recent\". "
					+ "Use fallback strategy and select by TAG name.", sortOption.getValue()));
			return this.productService.getPagedProductsByTagNameOrderedByDateAndDownloads(sortOption.getValue(), pageable);
		} else {
			return this.productService.getPagedProductsByPageable(pageable);
		}
	}

	/**
	 * ## todo describe.
	 *
	 * @param sortOption
	 * @param limitToList
	 * @param pageable
	 * @return
	 */
	private Page<ProductEntity> getPopulatedProductEntityByFilter(final SortOptionEnum sortOption, final List<String> limitToList, final Pageable pageable) {
		// download; recent; featured; promoted;
		if (sortOption.equals(SortOptionEnum.RATING)) {
			return this.productService.getAllProductsFilteredByKeywordsAndOrderedByRatingDesc(limitToList, pageable);
		} else if (sortOption.equals(SortOptionEnum.FEATURED) || sortOption.equals(SortOptionEnum.PROMOTED)) {
			LOG.info(String.format("Type \"%s\" didn't match \"download\". \"rating\" or \"recent\". "
					+ "Use fallback strategy and select by TAG name.", sortOption.getValue()));
			return this.productService.getPagedProductsByTagNameFilteredByKeywordsOrderedByDateAndDownloads(sortOption.getValue(), limitToList, pageable);
		} else {
			return this.productService.getPagedProductsFilteredByKeywordsByPageable(limitToList, pageable);
		}
	}
}
