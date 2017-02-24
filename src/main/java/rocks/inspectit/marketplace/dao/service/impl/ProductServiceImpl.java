package rocks.inspectit.marketplace.dao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import rocks.inspectit.marketplace.dao.repository.ProductEntityRepository;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;
import rocks.inspectit.marketplace.dao.repository.jpa.entity.helper.CustomQueryDTO;
import rocks.inspectit.marketplace.dao.service.ProductService;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.6-SNAPSHOT
 */
@Service
public class ProductServiceImpl implements ProductService {

	private final ProductEntityRepository productRepository;

	/**
	 * constructor injection.
	 *
	 * @param productRepository {@link ProductEntityRepository}
	 */
	@Autowired
	public ProductServiceImpl(final ProductEntityRepository productRepository) {
		this.productRepository = productRepository;
	}

	/**
	 * ## todo: describe.
	 *
	 * @param pageable {@link Pageable}
	 * @return {@link Page} of {@link ProductEntity}
	 * @since 1.0.7-SNAPSHOT
	 */
	@Override
	public Page<ProductEntity> getPagedProductsByPageable(final Pageable pageable) {
		return this.productRepository.findAll(pageable);
	}

	/**
	 * Select most recent Products, whose creation date is lower than "tomorrow".
	 * Set up {@link Pageable} Limit result to 20.
	 * <p>
	 * Since this select is build by a custom query, a {@link CustomQueryDTO} object was used to return {@link ProductEntity} instead of {@link Object} Array.
	 *
	 * @param pageable of {@link Pageable}
	 * @return {@link Page} of {@link ProductEntity}
	 * @since 1.0.6-SNAPSHOT
	 */
	@Override
	public Page<ProductEntity> getAllProductsOrderedByRatingDesc(final Pageable pageable) {
		final List<ProductEntity> returnList = new ArrayList<>();

		final Page<CustomQueryDTO> dtoList = this.productRepository.findProductsSumRatingAndAverageRatingGroupByProductEntity(pageable);
		dtoList.forEach(page -> returnList.add(page.getProductEntity()));

		return new PageImpl<>(returnList, pageable, returnList.size());
	}

	/**
	 * ## TODO.
	 *
	 * @param tagName {@link String}
	 * @return {@link List} of {@link ProductEntity}
	 * @since 1.0.6-SNAPSHOT
	 */
	@Override
	public Page<ProductEntity> getPagedProductsByTagNameOrderedByDateAndDownloads(final String tagName, final Pageable pageable) {
		return this.productRepository.findAllByTagNameFromTagEntity(tagName, pageable);
	}

	/**
	 * ## TODO.
	 *
	 * @param searchTerm {@link String}
	 * @param pageable   {@link Pageable}
	 * @return {@link List} of {@link ProductEntity}
	 * @since 1.0.6-SNAPSHOT
	 */
	@Override
	public Page<ProductEntity> getAllProductsBySearchTerm(final String searchTerm, final Pageable pageable) {
		return this.productRepository.findAllByProductNameOrUsernameOrderByNameDesc(searchTerm, pageable);
	}

	@Override
	public ProductEntity getProductByProductUuid(final UUID productUuid) {
		return this.productRepository.findOne(productUuid);
	}

	// limit

	/**
	 * ## todo describe.
	 * <p>
	 * Map limitToList values to lowerCase
	 *
	 * @param limitToList {@link List} of {@link String}
	 * @param pageable    {@link Pageable}
	 * @return {@link Pageable} of {@link ProductEntity}
	 */
	@Override
	public Page<ProductEntity> getAllProductsFilteredByKeywordsAndOrderedByRatingDesc(final List<String> limitToList, final Pageable pageable) {
		final List<ProductEntity> returnList = new ArrayList<>();

		final List<UUID> productUuidList = this.productRepository.findAllUuidByKeywords(
				limitToList.stream()
						.map(String::toLowerCase)
						.collect(Collectors.toList()));
		final Page<CustomQueryDTO> dtoList = this.productRepository.findProductsSumRatingAndAverageRatingByProductUuidsGroupByProductEntity(productUuidList, pageable);
		dtoList.forEach(page -> returnList.add(page.getProductEntity()));

		return new PageImpl<>(returnList, pageable, returnList.size());
	}

	/**
	 * ## todo describe.
	 * <p>
	 * map limitToList values to lower case.
	 *
	 * @param value       {@link String}
	 * @param limitToList {@link List} of {@link String}
	 * @param pageable    {@link Pageable}
	 * @return {@link Page} of {@link ProductEntity}
	 */
	@Override
	public Page<ProductEntity> getPagedProductsByTagNameFilteredByKeywordsOrderedByDateAndDownloads(final String value, final List<String> limitToList, final Pageable pageable) {
		return this.productRepository.findAllByTagNameFromTagEntityLimitByKeywords(value,
				limitToList.stream()
						.map(String::toLowerCase)
						.collect(Collectors.toList()),
				pageable);
	}

	/**
	 * ## todo describe.
	 * <p>
	 * map limitToList values to lower case.
	 *
	 * @param limitToList {@link List} of {@link String}
	 * @param pageable    {@link Pageable}
	 * @return {@link Page} of {@link ProductEntity}
	 */
	@Override
	public Page<ProductEntity> getPagedProductsFilteredByKeywordsByPageable(final List<String> limitToList, final Pageable pageable) {
		return this.productRepository.findAllByKeywordEntityNameIn(
				limitToList.stream()
						.map(String::toLowerCase)
						.collect(Collectors.toList()),
				pageable);
	}
}
