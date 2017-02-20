package rocks.inspectit.marketplace.mvc.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
 * @since 1.0.7-SNAPSHOT
 */
@RestController
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RequestMapping("app")
public class OverviewController {

	private final ObjectMapper mapper;
	private final OverviewService service;

	/**
	 * User constructor injection.
	 *
	 * @param mapper  {@link ObjectMapper}
	 * @param service {@link OverviewService}
	 */
	@Autowired
	public OverviewController(final ObjectMapper mapper, final OverviewService service) {
		this.mapper = mapper;
		this.service = service;
	}

	/**
	 * @param sortOrder  {@link String} sort should be "DESC" or "ASC"
	 * @param sortOption {@link SortOptionEnum} attribute to order by; like "name2, "rating", "downloads"...
	 * @param limitTo    {@link String} keywords to limit result to; like "spring", "hibernate", "jpa"-..
	 * @param pageable   {@link Pageable} for paginated results
	 * @return {@link Page} of {@link OverviewItemModel}
	 */
	@GetMapping("/list")
	public Page<OverviewItemModel> getOverviewItemListByFilter(@RequestParam(defaultValue = "desc") final String sortOrder,
			@RequestParam(defaultValue = "RECENT") final SortOptionEnum sortOption,
			@RequestParam(required = false) final List<String> limitTo,
			final Pageable pageable) {

		final Page<ProductEntity> productEntitiesPage = this.service.getPageableProductEntityByFilter(sortOrder, sortOption, limitTo, pageable);
		List<OverviewItemModel> overviewItemModelList = this.mapper.getListModelFromEntityList(productEntitiesPage.getContent());
		return new PageImpl<>(overviewItemModelList, pageable, productEntitiesPage.getTotalElements());
	}
}
