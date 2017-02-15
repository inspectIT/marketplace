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
import rocks.inspectit.marketplace.service.DashBoardService;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
@RestController
@RequestMapping("app")
public class DashBoardController {
	private final DashBoardService service;
	private final ObjectMapper mapper;

	/**
	 * Use constructor injection.
	 *
	 * @param service {@link DashBoardService}
	 */
	@Autowired
	public DashBoardController(final DashBoardService service, final ObjectMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	/**
	 * Limit results to 20 items.
	 *
	 * @param type of {@link String}
	 * @return list of {@link OverviewItemModel} as JSON
	 * @since 1.0.4-SNAPSHOT
	 */
	@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
	@GetMapping(value = "/get/dashboard/simple/{type}")
	public List<OverviewItemModel> getSimpleDashboardOverview(@PathVariable final String type) {
		final List<OverviewItemModel> modelList = this.mapper.getModelFromEntity(this.service.getSimpleDashboardOverviewByType(type));
		return modelList;
	}
}
