package rocks.inspectit.marketplace.mvc.angular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import rocks.inspectit.marketplace.mvc.angular.model.DashBoardModel;
import rocks.inspectit.marketplace.mvc.domain.ResultFilter;
import rocks.inspectit.marketplace.service.DashBoardService;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
@RestController
public class DashBoardController {

	private final DashBoardService service;

	/**
	 * Use constructor injection.
	 *
	 * @param service {@link DashBoardService}
	 */
	@Autowired
	public DashBoardController(final DashBoardService service) {
		this.service = service;
	}

	/**
	 * ##TODO: add documentation.
	 * ##TODO: use bean mapper to map from service-domain to model.
	 * {@link PathVariable} will be uses to create a {@link ResultFilter} to limit Dashboard Result Objects.
	 *
	 * @param size  {@link String}; {@link PathVariable} represents {@link rocks.inspectit.marketplace.enums.ItemSize} Enum
	 * @param order {@link String}; {@link PathVariable} represents {@link rocks.inspectit.marketplace.enums.OrderBy} Enum
	 * @param sort  {@link String}; {@link PathVariable} represents {@link rocks.inspectit.marketplace.enums.SortBy} Enum
	 * @return {@link DashBoardModel} as list
	 */
	@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
	@GetMapping(value = "/get/dashboard/simple/{size}/{order}/{sort}")
	public List<DashBoardModel> getSimpleDashboardOverview(
			@PathVariable final String size,
			@PathVariable final String order,
			@PathVariable final String sort) {
		final ResultFilter filter = new ResultFilter.Builder().orderBy(order).resultSize(size).sortBy(sort).create();
		return this.service.getFilteredDashBoardOverview(filter);
	}
}
