package rocks.inspectit.marketplace.mvc.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
@RequestMapping("app")
public class DashBoardController {
	private final OverviewService service;
	private final ObjectMapper mapper;

	/**
	 * Use constructor injection.
	 *
	 * @param service {@link OverviewService}
	 * @param mapper {@link ObjectMapper}
	 */
	@Autowired
	public DashBoardController(final OverviewService service, final ObjectMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	/**
	 * Limit results to 20 items.
	 *
	 * @param sortOption of {@link SortOptionEnum}
	 * @return list of {@link OverviewItemModel} as JSON
	 * @since 1.0.4-SNAPSHOT
	 */
	@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
	@GetMapping(value = "/get/dashboard/simple/{type}")
	public List<OverviewItemModel> getSimpleDashboardOverview(@PathVariable final SortOptionEnum sortOption) {
		final List<OverviewItemModel> modelList = this.mapper.getListModelFromEntityList(this.service.getSimpleDashboardOverviewByType(sortOption));
		return modelList;
	}
}
