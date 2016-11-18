package rocks.inspectit.marketplace.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import rocks.inspectit.marketplace.dao.mocks.MockRepository;
import rocks.inspectit.marketplace.mvc.angular.model.DashBoardModel;
import rocks.inspectit.marketplace.mvc.domain.ResultFilter;
import rocks.inspectit.marketplace.service.DashBoardService;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
@Service
public class DashBoardServiceImpl implements DashBoardService {

	private final MockRepository repository;

	/**
	 * use constructor injection.
	 *
	 * @param repository {@link MockRepository}
	 * @version %I%, %G%
	 */
	@Autowired
	public DashBoardServiceImpl(final MockRepository repository) {
		this.repository = repository;
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
		return this.repository.getMockedDashBoardValues();
	}
}
