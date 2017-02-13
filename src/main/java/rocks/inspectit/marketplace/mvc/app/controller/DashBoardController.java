package rocks.inspectit.marketplace.mvc.app.controller;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import rocks.inspectit.marketplace.mvc.app.model.DashBoardModel;
import rocks.inspectit.marketplace.mvc.domain.ResultFilter;
import rocks.inspectit.marketplace.repository.jpa.entity.ProductEntity;
import rocks.inspectit.marketplace.service.DashBoardService;
import rocks.inspectit.marketplace.service.impl.DashBoardServiceImpl;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
@RestController
@RequestMapping("app")
public class DashBoardController {
	private static final Logger LOG = LoggerFactory.getLogger(DashBoardServiceImpl.class);

	private final DozerBeanMapper mapper;
	private final DashBoardService service;

	/**
	 * Use constructor injection.
	 *
	 * @param mapper  {@link DozerBeanMapper}
	 * @param service {@link DashBoardService}
	 */
	@Autowired
	public DashBoardController(final DozerBeanMapper mapper, final DashBoardService service) {
		this.mapper = mapper;
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

	/**
	 * Limit results to 20 items.
	 *
	 * @param type of {@link String}
	 * @return list of {@link DashBoardModel} as JSON
	 * @since 1.0.4-SNAPSHOT
	 */
	@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
	@GetMapping(value = "/get/dashboard/simple/{type}")
	public List<DashBoardModel> getSimpleDashboardOverview(
			@PathVariable
			final String type) {
		final List<DashBoardModel> modelList = this.getModelFromEntity(this.service.getSimpleDashboardOverviewByType(type, true));
		return modelList;
	}

	/**
	 * keep service clean and avoid leaking model information into service.
	 * map from entity to model here.
	 *
	 * @param productEntityList as {@link List} of {@link ProductEntity}
	 * @return {@link List} of {@link DashBoardModel}
	 */
	private List<DashBoardModel> getModelFromEntity(final List<ProductEntity> productEntityList) {
		final List<DashBoardModel> returnModel = new ArrayList<>();
		productEntityList.forEach(it -> {
			final DashBoardModel tmpModel = this.mapper.map(it, DashBoardModel.class);
			tmpModel.setRating(it.getTotalRating());

			// map blob to byte array
			final byte[] blobAsBytes = it.getPreviewImage()
					.map(blob -> {
								byte[] bytes;
								try {
									bytes = blob.getBytes(1, (int) blob.length());
									//release the blob and free up memory. (since JDBC 4.0)
									blob.free();
								} catch (SQLException e) {
									bytes = new byte[0];
									LOG.error(e.getMessage(), e);
								}
								return bytes;
							}
					).orElse(new byte[0]);
			tmpModel.setPreviewImage(DatatypeConverter.printBase64Binary(blobAsBytes));
			returnModel.add(tmpModel);
		});
		return returnModel;
	}
}
