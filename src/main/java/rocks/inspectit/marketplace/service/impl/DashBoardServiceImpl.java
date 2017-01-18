package rocks.inspectit.marketplace.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import rocks.inspectit.marketplace.mvc.angular.model.DashBoardModel;
import rocks.inspectit.marketplace.mvc.domain.ResultFilter;
import rocks.inspectit.marketplace.repository.ProductEntityRepository;
import rocks.inspectit.marketplace.service.DashBoardService;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
@Service
public class DashBoardServiceImpl implements DashBoardService {

	private final ProductEntityRepository repository;
	private final DozerBeanMapper mapper;

	/**
	 * use constructor injection.
	 *
	 * @param repository {@link ProductEntityRepository}
	 * @version %I%, %G%
	 */
	@Autowired
	public DashBoardServiceImpl(final ProductEntityRepository repository, final DozerBeanMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
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
	 */
	@Override
	public List<DashBoardModel> getSimpleDashboardOverviewByType(final String tag, final boolean limit) {
		final List<DashBoardModel> returnModel = new ArrayList<>();
		if(limit)
		this.repository.findTop10ByTag(tag)
				.forEach(it -> returnModel.add(this.mapper.map(it, DashBoardModel.class)));
		return returnModel;
	}
}
