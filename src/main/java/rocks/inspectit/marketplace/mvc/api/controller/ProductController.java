package rocks.inspectit.marketplace.mvc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;
import rocks.inspectit.marketplace.dao.service.ProductService;
import rocks.inspectit.marketplace.mvc.app.helper.ObjectMapper;
import rocks.inspectit.marketplace.mvc.app.model.DetailModel;
import rocks.inspectit.marketplace.mvc.app.model.OverviewItemModel;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.1.1-SNAPSHOT
 */
@RestController
@RequestMapping("api")
public class ProductController {

	private final ProductService productService;
	private final ObjectMapper mapper;

	@Autowired
	public ProductController(final ProductService productService, final ObjectMapper mapper) {
		this.productService = productService;
		this.mapper = mapper;
	}

	@GetMapping("get/product/list")
	public List<OverviewItemModel> getProductList(@PageableDefault(size = Integer.MAX_VALUE, direction = Sort.Direction.DESC) final Pageable pageable) {
		List<OverviewItemModel> overviewItemModelList = mapper.getOverviewItemModelListFromProductEntityList(productService.getPagedProductsByPageable(pageable).getContent());
		return overviewItemModelList;
	}

	@GetMapping("get/product/{productId}")
	public DetailModel getProductByProductId(@PathVariable final UUID productId) {
		final DetailModel detailModel = this.mapper.getDetailModelFromProductEntity(this.productService.getProductByProductUuid(productId));
		return detailModel;
	}

	@GetMapping("get/product/{productId}/download")
	public ResponseEntity<byte[]> downloadProductItemByProductId(@PathVariable final UUID productId) {
		final ProductEntity entity = this.productService.getProductByProductUuid(productId);
		final byte[] content = entity.getProductItem();
		final String filename = entity.getName() + ".xml";

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/xml"));
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

		final ResponseEntity<byte[]> response = new ResponseEntity<>(content, headers, HttpStatus.OK);
		return response;
	}

	@GetMapping("/get/product/search/{searchTerm}")
	public List<OverviewItemModel> getSearchResultByTerm(@PathVariable final String searchTerm,
			@PageableDefault(size = Integer.MAX_VALUE, direction = Sort.Direction.DESC) final Pageable pageable) {
		final List<OverviewItemModel> overviewItemModelList = mapper.getOverviewItemModelListFromProductEntityList(productService.getAllProductsBySearchTerm(searchTerm, pageable).getContent());
		return overviewItemModelList;
	}

}
