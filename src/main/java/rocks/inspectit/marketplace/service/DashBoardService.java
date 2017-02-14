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
	 * ## TODO.
	 * ## probably dead code
	 *
	 * @param filter {@link ResultFilter}
	 * @return List {@link List} of {@link DashBoardModel} Items
	 */
	List<DashBoardModel> getFilteredDashBoardOverview(final ResultFilter filter);

	List<ProductEntity> getSimpleDashboardOverviewByType(final String type);

	List<ProductEntity> getTop20MostDownloadedProducts();

	List<ProductEntity> getTop20MostRecentUploadedProducts();

	List<ProductEntity> getTop20BestRatedProducts();
}
