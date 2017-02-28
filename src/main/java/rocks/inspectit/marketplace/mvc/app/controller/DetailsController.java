package rocks.inspectit.marketplace.mvc.app.controller;

import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.KeywordEntity;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.UserEntity;
import rocks.inspectit.marketplace.mvc.advice.GeneralControllerAdvice;
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
		final DetailModel detailModel = this.mapper.getSimpleModelFromEntity(this.service.getProductEntityById(productId));
		return detailModel;
	}

	//	private UUID productId;
	//	private String productName;
	//	private String productDescription;
	//	private String productPreviewImage;
	//	private String productCreationDate;
	//	private Long numberOfDownloads;
	//
	//	private UUID userId;
	//	private String userName;
	//	private String userAvatarUrl;
	//
	//	private Double rating;
	//	private List<RatingItemModel> ratingList = new ArrayList<>();

	/**
	 * ## todo : add proper mapping.
	 *
	 * @param request {@link HttpServletRequest}
	 */
	@PostMapping("add/product")
	public void addAndPersistProduct(final HttpServletRequest request) {
		final MultipartFile product = ((StandardMultipartHttpServletRequest) request).getMultiFileMap().getFirst("products");
		final MultipartFile image = ((StandardMultipartHttpServletRequest) request).getMultiFileMap().getFirst("images");

		final String commaSeperatedKeywords = request.getParameter("keywords");
		final String userName = request.getParameter("username");

		final UserEntity userEntity = this.service.getUserByUserName(userName);
		final List<KeywordEntity> keywordEntityList = this.service.getKeywordEntityListByAlias(commaSeperatedKeywords);
		final ProductEntity productEntity = new ProductEntity();

		productEntity.setName(request.getParameter("name"));
		productEntity.setDescription(request.getParameter("description"));

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
	}

}
