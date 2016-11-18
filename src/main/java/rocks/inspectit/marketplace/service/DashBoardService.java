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

	List<DashBoardModel> getFilteredDashBoardOverview(final ResultFilter filter);
}
