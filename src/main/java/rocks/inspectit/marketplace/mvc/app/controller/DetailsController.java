package rocks.inspectit.marketplace.mvc.app.controller;

import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.KeywordEntity;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.RatingEntity;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.UserEntity;
import rocks.inspectit.marketplace.mvc.advice.GeneralControllerAdvice;
import rocks.inspectit.marketplace.mvc.app.helper.ObjectMapper;
import rocks.inspectit.marketplace.mvc.app.model.DetailModel;
import rocks.inspectit.marketplace.mvc.app.model.RatingModel;
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
	private static final Logger LOG = LoggerFactory.getLogger(GeneralControllerAdvice.class);

	private final DetailService service;
	private final ObjectMapper mapper;

	/**
	 * constructor injection.
	 *
	 * @param service {@link DetailService}
	 * @param mapper  {@link ObjectMapper}
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
	public DetailModel getDetailModelById(@PathVariable final UUID productId) {
		final DetailModel detailModel = this.mapper.getDetailModelFromProductEntity(this.service.getProductEntityById(productId));
		return detailModel;
	}

	/**
	 * ## todo : add proper mapping.
	 *
	 * @param request {@link HttpServletRequest}
	 */
	@PostMapping("add/product")
	public Map<String, Boolean> addAndPersistProduct(final HttpServletRequest request) {
		final MultipartFile product = ((StandardMultipartHttpServletRequest) request).getMultiFileMap().getFirst("products");
		final MultipartFile image = ((StandardMultipartHttpServletRequest) request).getMultiFileMap().getFirst("images");

		// manually replace quote char
		final String commaSeparatedKeywords = request.getParameter("keywords").replaceAll("\"", "");
		final String userName = request.getParameter("username");

		final UserEntity userEntity = this.service.getUserByUserName(userName);
		final List<KeywordEntity> keywordEntityList = this.service.getKeywordEntityListByAlias(commaSeparatedKeywords);
		final ProductEntity productEntity = new ProductEntity();

		// manually replace quote char
		productEntity.setName(request.getParameter("name").replaceAll("\"", ""));
		productEntity.setDescription(request.getParameter("description").replaceAll("\"", ""));

		try (final InputStream imgInput = image.getInputStream();
				final InputStream cntInput = product.getInputStream()) {
			final byte[] imgArray = IOUtils.toByteArray(imgInput); // Apache commons IO.
			productEntity.setPreviewImage(imgArray);

			final byte[] cntArray = IOUtils.toByteArray(cntInput); // Apache commons IO.
			productEntity.setProductItem(cntArray);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}

		productEntity.setUserEntity(userEntity);
		productEntity.setKeywordEntityList(keywordEntityList);

		this.service.persistProductEntity(productEntity);
		final Map<String, Boolean> returnMap = new HashMap<>();
		returnMap.put("success", productEntity.getProductUuid() != null);
		return returnMap;
	}

	/**
	 * simple update function. update download counter and modify date.
	 *
	 * @param productId {@link UUID}
	 * @return {@link DetailModel}
	 * @since 1.1.1-SNAPSHOT
	 */
	@GetMapping("update/product/{productId}/download")
	public DetailModel getUpdatedProductEntity(@PathVariable final UUID productId) {
		final ProductEntity productEntity = this.service.getProductEntityById(productId);
		productEntity.setNumberOfDownloads(productEntity.getNumberOfDownloads() + 1);
		productEntity.setModifyDate(new Date());
		this.service.persistProductEntity(productEntity);

		final DetailModel detailModel = this.mapper.getDetailModelFromProductEntity(productEntity);
		return detailModel;
	}

	/**
	 * ## todo describe.
	 *
	 * @param productId {@link UUID}
	 * @param userName  {@link String}
	 * @param rating    {@link RatingEntity}
	 * @return {@link Map} of {@link String} and {@link Boolean}
	 * @since 1.1.1-SNAPSHOT
	 */
	@PostMapping("add/rating/{productId}/{userName}")
	public Map<String, Boolean> addAndPersistRating(@PathVariable final UUID productId,
			@PathVariable final String userName,
			@RequestBody RatingModel rating) {
		final UserEntity userEntity = this.service.getUserByUserName(userName);
		final ProductEntity productEntity = this.service.getProductEntityById(productId);

		final RatingEntity entity = this.mapper.getRatingEntityFromRatingModel(rating);
		entity.setProductEntity(productEntity);
		entity.setUserEntity(userEntity);

		this.service.persistRatingEntity(entity);
		final Map<String, Boolean> returnMap = new HashMap<>();
		returnMap.put("success", entity.getRatingUuid() != null);
		return returnMap;
	}

	@GetMapping("/download/product/{productId}")
	public ResponseEntity<byte[]> getFile(@PathVariable final UUID productId) {
		final ProductEntity entity = service.getProductEntityById(productId);
		final byte[] content = entity.getProductItem();
		final String filename = entity.getName() + ".xml";

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/xml"));
		headers.setContentDispositionFormData(filename, filename);
//		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

		final ResponseEntity<byte[]> response = new ResponseEntity<>(content, headers, HttpStatus.OK);
		return response;
	}
}
