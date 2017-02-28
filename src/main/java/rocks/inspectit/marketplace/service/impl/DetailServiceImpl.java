package rocks.inspectit.marketplace.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.KeywordEntity;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.UserEntity;
import rocks.inspectit.marketplace.dao.service.KeywordService;
import rocks.inspectit.marketplace.dao.service.ProductService;
import rocks.inspectit.marketplace.dao.service.UserService;
import rocks.inspectit.marketplace.service.DetailService;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.7-SNAPSHOT
 */
@Service
public class DetailServiceImpl implements DetailService {

	private final ProductService productService;
	private final UserService userService;
	private final KeywordService keywordService;

	/**
	 * constructor injection.
	 *
	 * @param productService {@link ProductService}
	 * @param userService    {@link UserService}
	 * @param keywordService {@link KeywordService}
	 */
	@Autowired
	public DetailServiceImpl(final ProductService productService, final UserService userService, final KeywordService keywordService) {
		this.productService = productService;
		this.userService = userService;
		this.keywordService = keywordService;
	}

	/**
	 * ## todo : describe.
	 *
	 * @param productId {@link UUID}
	 * @return {@link ProductEntity}
	 */
	@Override
	public ProductEntity getProductEntityById(final UUID productId) {
		return this.productService.getProductByProductUuid(productId);
	}

	/**
	 * ## todo : describe
	 *
	 * @param userName {@link String}
	 * @since 1.1.0-SNAPSHOT
	 */
	@Override
	public UserEntity getUserByUserName(final String userName) {
		return this.userService.getUserEntityByUsername(userName);
	}

	/**
	 * @param commaSeparatedKeywords {@link String}
	 * @since 1.1.0-SNAPSHOT
	 */
	@Override
	public List<KeywordEntity> getKeywordEntityListByAlias(final String commaSeparatedKeywords) {
		return this.keywordService.getKeywordEntityListByAliasList(Arrays.asList(commaSeparatedKeywords.toLowerCase().split(",")));
	}

	/**
	 * ## todo : describe
	 *
	 * @param productEntity {@link ProductEntity}
	 * @return {@link ProductEntity}
	 */
	@Override
	public ProductEntity persistProductEntity(final ProductEntity productEntity) {
		return this.productService.persistProductEntity(productEntity);
	}
}
