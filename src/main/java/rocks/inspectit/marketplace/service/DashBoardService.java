package rocks.inspectit.marketplace.service;

import java.util.List;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
public interface DashBoardService {

	/**
	 * ##todo describe
	 *
	 * @param type {@link String}
	 * @return {@link List} of {@link ProductEntity}
	 */
	List<ProductEntity> getSimpleDashboardOverviewByType(final String type);
}
