package rocks.inspectit.marketplace.dao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	@Autowired
	public ProductServiceImpl(final ProductEntityRepository productRepository) {
		this.productRepository = productRepository;
	}

	/**
	 * Select most downloaded Products.
	 * Limit result to 20.
	 *
	 * @return {@link List} of {@link ProductEntity}
	 * @since 1.0.6-SNAPSHOT
	 */
	@Override
	public List<ProductEntity> getTop20MostDownloadedProducts() {
		final long minDownloads = 0;
		return this.productRepository.findTop20ByNumberOfDownloadsGreaterThanOrderByNumberOfDownloadsDesc(minDownloads);
	}

	/**
	 * Select most recent Products, whose creation date is lower than "tomorrow".
	 * Set up {@link Pageable} Limit result to 20.
	 * <p>
	 * Since this select is build by a custom query, a {@link CustomQueryDTO} object was used to return {@link ProductEntity} instead of {@link Object} Array.
	 *
	 * @return {@link List} of {@link ProductEntity}
	 * @since 1.0.6-SNAPSHOT
	 */
	@Override
	public List<ProductEntity> getTop20BestRatedProducts() {
		final List<ProductEntity> returnList = new ArrayList<>();

		final Page<CustomQueryDTO> dtoList = this.productRepository.findPageableProductEntitiesGroupByProductEntityOrderedByRatingDesc(this.getDefaultPageable());
		dtoList.forEach(page -> returnList.add(page.getProductEntity()));

		return returnList;
	}

	/**
	 * Select most recent Products, whose creation date is lower than "tomorrow".
	 * Limit result to 20.
	 * <p>
	 * Create {@link LocalDate} with the date of tomorrow and convert it to {@link Date}
	 *
	 * @return {@link List} of {@link ProductEntity}
	 * @since 1.0.6-SNAPSHOT
	 */
	@Override
	public List<ProductEntity> getTop20MostRecentUploadedProducts() {
		// get tomorrow date
		final LocalDate tomorrow = LocalDate.now().plusDays(1);
		final Date date = Date.from(tomorrow.atStartOfDay(ZoneId.systemDefault()).toInstant());
		// return the 20 most recent uploads
		return this.productRepository.findTop20ByCreationDateLessThanOrderByCreationDateDesc(date);
	}

	/**
	 * ## TODO
	 *
	 * @param tagName {@link String}
	 * @return {@link List} of {@link ProductEntity}
	 * @since 1.0.6-SNAPSHOT
	 */
	@Override
	public Page<ProductEntity> get20ProductsBaTagNameOrderedByDateAndDownloads(final String tagName) {
		return this.productRepository.findPageableByTagNameFromTagEntityOrderedByCreationDateAndNumberOfDownloadsDesc(tagName, this.getDefaultPageable());
	}

	/**
	 * ## TODO
	 *
	 * @param searchTerm {@link String}
	 * @param pageable   {@link Pageable}
	 * @return {@link List} of {@link ProductEntity}
	 * @since 1.0.6-SNAPSHOT
	 */
	@Override
	public Page<ProductEntity> getAllProductEntitiesBySearchTerm(final String searchTerm, final Pageable pageable) {
		return this.productRepository.findPageableByProductNameOrUsernameOrderByNameDesc(searchTerm, pageable);
	}

	/**
	 * Set up a default pageable.
	 * set page to 0 and size to 20.
	 *
	 * @return {@link Pageable}
	 * @since 1.0.6-SNAPSHOT
	 */
	private Pageable getDefaultPageable() {
		final Pageable pageable = new PageRequest(0, 20);
		return pageable;
	}
}
