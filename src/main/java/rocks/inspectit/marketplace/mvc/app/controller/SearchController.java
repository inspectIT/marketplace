package rocks.inspectit.marketplace.mvc.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;
import rocks.inspectit.marketplace.mvc.app.helper.ObjectMapper;
import rocks.inspectit.marketplace.mvc.app.model.OverviewItemModel;
import rocks.inspectit.marketplace.service.SearchService;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.6-SNAPSHOT
 */
@RestController
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RequestMapping("app")
public class SearchController {
	private final SearchService service;
	private final ObjectMapper mapper;

	@Autowired
	public SearchController(final SearchService service, final ObjectMapper mapper) {
		this.mapper = mapper;
		this.service = service;
	}

	/**
	 * ## todo : decribe
	 *
	 * create {@link Page} with overviewItemModelList as content, pageable as pageabel and productEntitiesPage.getTotalElements for size;
	 * return a paging object without mapping from {@link Page} to a similar custom page object
	 *
	 * @param searchTerm {@link String}
	 * @param pageable {@link Pageable}
	 * @return {@link Page} of {@link OverviewItemModel}
	 */
	@GetMapping("/get/search/{searchTerm}")
	public Page<OverviewItemModel> getsearchResultByTerm(@PathVariable final String searchTerm,
			final Pageable pageable) {

		final Page<ProductEntity> productEntitiesPage = this.service.getAllProductEntitiesBySearchTerm(searchTerm, pageable);
		List<OverviewItemModel> overviewItemModelList = this.mapper.getModelFromEntity(productEntitiesPage.getContent());
		return new PageImpl<>(overviewItemModelList, pageable, productEntitiesPage.getTotalElements());
	}
}
