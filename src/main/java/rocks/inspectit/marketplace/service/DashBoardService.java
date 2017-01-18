package rocks.inspectit.marketplace.service;

import java.util.List;

import rocks.inspectit.marketplace.mvc.angular.model.DashBoardModel;
import rocks.inspectit.marketplace.mvc.domain.ResultFilter;

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
	 *
	 * @param tag
	 * @param limit
	 * @return
	 */
	List<DashBoardModel> getSimpleDashboardOverviewByType(final String tag, final boolean limit);
}
