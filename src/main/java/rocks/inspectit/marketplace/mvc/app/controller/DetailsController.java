package rocks.inspectit.marketplace.mvc.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import rocks.inspectit.marketplace.mvc.app.helper.ObjectMapper;
import rocks.inspectit.marketplace.mvc.app.model.DetailModel;
import rocks.inspectit.marketplace.service.DetailService;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.7-SNAPSHOT
 */
@RestController
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RequestMapping("app")
public class DetailsController {

	private final DetailService service;
	private final ObjectMapper mapper;

	/**
	 * constructor injection.
	 *
	 * @param service {@link DetailService}
	 * @param mapper {@link ObjectMapper}
	 */
	@Autowired
	public DetailsController(final DetailService service, final ObjectMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	/**
	 * ## todo describe.
	 *
	 * @param productId {@link String}
	 * @return {@link DetailModel}
	 */
	@GetMapping("get/product/{productId}")
	public DetailModel getDetailModelById(@PathVariable final String productId) {
		final DetailModel detailModel = this.mapper.getSimpleModelFromEntity(this.service.getProductEntityById(UUID.fromString(productId)));
		return detailModel;
	}

}
