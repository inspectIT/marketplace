package rocks.inspectit.marketplace.mvc.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;
import rocks.inspectit.marketplace.mvc.app.helper.ObjectMapper;
import rocks.inspectit.marketplace.mvc.app.model.OverviewItemModel;
import rocks.inspectit.marketplace.mvc.app.model.SortOptionEnum;
import rocks.inspectit.marketplace.service.OverviewService;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
@RestController
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RequestMapping("app")
public class DashBoardController {
	private static final String DEFAULT_SORT_ORDER = "desc";

	private final OverviewService service;
	private final ObjectMapper mapper;
	private final Integer pageSize;

	/**
	 * Use constructor injection.
	 *
	 * @param service  {@link OverviewService}
	 * @param mapper   {@link ObjectMapper}
	 * @param pageSize {@link Integer}
	 */
	@Autowired
	public DashBoardController(final OverviewService service, final ObjectMapper mapper, @Value("${frontend.default.page.size}") final Integer pageSize) {
		this.service = service;
		this.mapper = mapper;
		this.pageSize = pageSize;
	}

	/**
	 * Limit results to 24 items.
	 *
	 * @param sortOption of {@link SortOptionEnum}
	 * @return list of {@link OverviewItemModel} as JSON
	 * @since 1.0.4-SNAPSHOT
	 */
	@GetMapping(value = "/get/dashboard/simple/{sortOption}")
	public List<OverviewItemModel> getSimpleDashboardOverview(@PathVariable final SortOptionEnum sortOption) {
		final Page<ProductEntity> productEntitiesPage = this.getProductsBySortOption(sortOption);
		final List<OverviewItemModel> modelList = this.mapper.getListModelFromEntityList(productEntitiesPage.getContent());
		return modelList;
	}

	/**
	 * ## todo : describe.
	 *
	 * @param preSortOption      {@link SortOptionEnum} attribute for pre order/sorting
	 * @param sortOrder          {@link String} sort should be "DESC" or "ASC"
	 * @param additionSortOption {@link SortOptionEnum} attribute to order by; like "name", "rating", "downloads"...
	 * @param limitTo            {@link String} keywords to limit result to; like "spring", "hibernate", "jpa"-..
	 * @param pageable           {@link Pageable} for paginated results
	 * @return {@link Page} of {@link OverviewItemModel}
	 * @since 1.0.8-SNAPSHOT
	 */
	@GetMapping("/get/overview/{preSortOption}")
	public Page<OverviewItemModel> getOverviewItemListByFilter(@PathVariable final SortOptionEnum preSortOption,
			@RequestParam(defaultValue = DEFAULT_SORT_ORDER) final String sortOrder,
			@RequestParam(required = false) final SortOptionEnum additionSortOption,
			@RequestParam(required = false) final List<String> limitTo,
			@PageableDefault(size = 24) final Pageable pageable) {
		final Page<ProductEntity> productEntitiesPage = this.getProductsBySortOptionAndSortOrderAndLimitToAndPageable(sortOrder, preSortOption, limitTo, pageable, additionSortOption);
		List<OverviewItemModel> overviewItemModelList = this.mapper.getListModelFromEntityList(productEntitiesPage.getContent());
		return new PageImpl<>(overviewItemModelList, pageable, productEntitiesPage.getTotalElements());
	}

	/**
	 * ## todo : describe.
	 *
	 * @param sortOption {@link SortOptionEnum}
	 * @return {@link Page} of {@link ProductEntity}
	 * @since 1.0.8-SNAPSHOT
	 */
	private Page<ProductEntity> getProductsBySortOption(final SortOptionEnum sortOption) {
		return this.getProductsBySortOptionAndSortOrderAndLimitToAndPageable(DEFAULT_SORT_ORDER, sortOption, null, new PageRequest(0, pageSize), null);
	}

	/**
	 * ## todo : describe.
	 *
	 * @param sortOrder          {@link String}
	 * @param sortOption         {@link SortOptionEnum}
	 * @param limitToList        {@link List} of {@link String}
	 * @param pageable           {@link Pageable}
	 * @param additionSortOption {@link SortOptionEnum}
	 * @return {@link Page} of {@link ProductEntity}
	 * @since 1.0.8-SNAPSHOT
	 */
	private Page<ProductEntity> getProductsBySortOptionAndSortOrderAndLimitToAndPageable(final String sortOrder, final SortOptionEnum sortOption, final List<String> limitToList,
			final Pageable pageable, final SortOptionEnum additionSortOption) {
		Sort sort = getPopulatedSort(sortOrder, sortOption.getValue());
		if (additionSortOption != null) {
			sort = sort.and(getPopulatedSort(sortOrder, additionSortOption.getValue()));
		}

		if (sortOption.equals(SortOptionEnum.RATING)) {
			return this.service.getProductsByRatingAndPageable(limitToList, this.getPageable(pageable.getPageNumber(), pageable.getPageSize(), sort));
		} else if (sortOption.equals(SortOptionEnum.FEATURED) || sortOption.equals(SortOptionEnum.PROMOTED)) {
			// override sort
			sort = getPopulatedSort(sortOrder, sortOption.getValue()[0]);
			if (additionSortOption != null) {
				sort = sort.and(getPopulatedSort(sortOrder, additionSortOption.getValue()));
			}
			return this.service.getProductsByTagAndPageable(sortOption.getValue()[1], limitToList, this.getPageable(pageable.getPageNumber(), pageable.getPageSize(), sort));

		} else {
			return this.service.getProductsByPageable(limitToList, this.getPageable(pageable.getPageNumber(), pageable.getPageSize(), sort));
		}
	}

	/**
	 * ## todo describe.
	 *
	 * @param pageNumber as {@link Integer}
	 * @param pageSize   as {@link Integer}
	 * @param sortBy     as {@link Sort}
	 * @return {@link Pageable}
	 * @since 1.0.8-SNAPSHOT
	 */
	private Pageable getPageable(int pageNumber, int pageSize, final Sort sortBy) {
		return new PageRequest(pageNumber, pageSize, sortBy);
	}

	/**
	 * ## todo describe.
	 *
	 * @param sortOrder   as {@link String}
	 * @param sortOptions as {@link String} array
	 * @return {@link Sort}
	 * @since 1.0.8-SNAPSHOT
	 */
	private Sort getPopulatedSort(final String sortOrder, final String... sortOptions) {
		return new Sort(Sort.Direction.fromString(sortOrder), sortOptions);
	}

}
