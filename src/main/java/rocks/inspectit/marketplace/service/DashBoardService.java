package rocks.inspectit.marketplace.service;

import java.util.List;

import rocks.inspectit.marketplace.mvc.app.model.DashBoardModel;
import rocks.inspectit.marketplace.mvc.domain.ResultFilter;
import rocks.inspectit.marketplace.repository.jpa.entity.ProductEntity;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
public interface DashBoardService {

	/**
	 * ##TODO.
	 *
	 * @param filter {@link ResultFilter}
	 * @return List {@link List} of {@link DashBoardModel} Items
	 */
	List<DashBoardModel> getFilteredDashBoardOverview(final ResultFilter filter);

	/**
	 * ##TODO.
	 *
	 * @param type  probably unused
	 * @param limit probably unused
	 * @return List {@link List} of {@link ProductEntity} Items
	 */
	List<ProductEntity> getSimpleDashboardOverviewByType(final String type, final boolean limit);

	List<ProductEntity> getTop20MostRecentUploadedProducts();
}
