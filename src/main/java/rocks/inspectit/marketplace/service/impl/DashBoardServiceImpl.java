package rocks.inspectit.marketplace.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rocks.inspectit.marketplace.mvc.app.model.DashBoardModel;
import rocks.inspectit.marketplace.mvc.domain.ResultFilter;
import rocks.inspectit.marketplace.repository.ProductEntityRepository;
import rocks.inspectit.marketplace.repository.jpa.entity.ProductEntity;
import rocks.inspectit.marketplace.repository.jpa.entity.helper.CustomQueryDTO;
import rocks.inspectit.marketplace.service.DashBoardService;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
@Service
public class DashBoardServiceImpl implements DashBoardService {

	private final ProductEntityRepository productRepository;

	/**
	 * use constructor injection.
	 *
	 * @param productRepository {@link ProductEntityRepository}
	 * @since 1.0.3-SNAPSHOT
	 */
	@Autowired
	public DashBoardServiceImpl(final ProductEntityRepository productRepository) {
		this.productRepository = productRepository;
	}

	/**
	 * ##TODO
	 * ## Probably dead code
	 *
	 * @param filter {@link ResultFilter}
	 * @return {@link DashBoardModel} as list
	 */
	@Override
	public List<DashBoardModel> getFilteredDashBoardOverview(final ResultFilter filter) {
		// filter not used yet
		return null; // this.repository.getMockedDashBoardValues();
	}

	/**
	 * FIXME
	 * there are currently different methods returning exactly what the front-end expects, due to missing entities. please implement entities and rewrite functions properly
	 * maybe use pagination instead of "topX"
	 *
	 * @param type indicates if products should be ordered by "downloads", "ratings", "creation_date" or "tag_name"
	 * @return {@link List} of {@link ProductEntity}
	 */
	@Override
	public List<ProductEntity> getSimpleDashboardOverviewByType(final String type) {
		final List<ProductEntity> returnList;
		if (type.equalsIgnoreCase("download")) {
			returnList = this.getTop20MostDownloadedProducts();
		} else if (type.equalsIgnoreCase("rating")) {
			returnList = this.getTop20BestRatedProducts();
		} else if (type.equalsIgnoreCase("recent")) {
			returnList = this.getTop20MostRecentUploadedProducts();
		} else if (type.equalsIgnoreCase("featured")) {
			// TODO implement me
			returnList = null;
		} else {
			// return empty list
			returnList = new ArrayList<>();
		}

		return returnList;

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
	 * Limit result to 20.
	 * <p>
	 * Since this select is build by a custom query, a {@link CustomQueryDTO} object was used to return {@link ProductEntity} instead of {@link Object} Array.
	 *
	 * @return {@link List} of {@link ProductEntity}
	 * @since 1.0.6-SNAPSHOT
	 */
	@Override
	public List<ProductEntity> getTop20BestRatedProducts() {
		final List<ProductEntity> returnList = new ArrayList<>();

		final List<CustomQueryDTO> dtoList = this.productRepository.findAllProductEntitiesGroupByProductEntityOrderedByRatingDesc();
		dtoList.forEach(dto -> returnList.add(dto.getProductEntity()));

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
}
