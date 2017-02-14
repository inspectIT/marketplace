package rocks.inspectit.marketplace.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import rocks.inspectit.marketplace.mvc.app.model.DashBoardModel;
import rocks.inspectit.marketplace.mvc.domain.ResultFilter;
import rocks.inspectit.marketplace.repository.ProductEntityRepository;
import rocks.inspectit.marketplace.repository.jpa.entity.ProductEntity;
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
	 * Return simple Mock List.
	 * Filter not used, yet.
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
	 * @param type  probably unused
	 * @param limit probably unused
	 * @return {@link List} of {@link ProductEntity}
	 */
	@Override
	public List<ProductEntity> getSimpleDashboardOverviewByType(final String type, final boolean limit) {

		if (type.equalsIgnoreCase("download")) {

		} else if (type.equalsIgnoreCase("rating")) {

		} else if (type.equalsIgnoreCase("recent")) {
			return this.getTop20MostRecentUploadedProducts();
		} else if (type.equalsIgnoreCase("featured")) {

		}

		return (List<ProductEntity>) this.productRepository.findAll();

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
