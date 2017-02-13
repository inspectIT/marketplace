package rocks.inspectit.marketplace.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import rocks.inspectit.marketplace.mvc.app.model.DashBoardModel;
import rocks.inspectit.marketplace.mvc.domain.ResultFilter;
import rocks.inspectit.marketplace.repository.ProductEntityRepository;
import rocks.inspectit.marketplace.repository.RatingEntityRepository;
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
	private final RatingEntityRepository ratingRepository;

	/**
	 * use constructor injection.
	 *
	 * @param productRepository {@link ProductEntityRepository}
	 * @param ratingRepository  {@link RatingEntityRepository}
	 * @since 1.0.3-SNAPSHOT
	 */
	@Autowired
	public DashBoardServiceImpl(final ProductEntityRepository productRepository, final RatingEntityRepository ratingRepository) {
		this.productRepository = productRepository;
		this.ratingRepository = ratingRepository;
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
	 * TODO:
	 * add lo
	 */
	@Override
	public List<ProductEntity> getSimpleDashboardOverviewByType(final String tag, final boolean limit) {
		return (List<ProductEntity>) this.productRepository.findAll();

	}
}
