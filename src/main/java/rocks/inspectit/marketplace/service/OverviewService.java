package rocks.inspectit.marketplace.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;
import rocks.inspectit.marketplace.mvc.app.model.SortOptionEnum;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.7-SNAPSHOT
 */
public interface OverviewService {

	/**
	 * ##todo describe
	 *
	 * @param sortOption {@link SortOptionEnum}
	 * @return {@link List} of {@link ProductEntity}
	 */
	List<ProductEntity> getSimpleDashboardOverviewByType(final SortOptionEnum sortOption);

	/**
	 * ## todo describe.
	 *
	 * @param sortOrder   {@link String}
	 * @param sortOption  {@link SortOptionEnum}
	 * @param limitToList {@link String}
	 * @param pageable    {@link Pageable}
	 * @return {@link Page} of {@link ProductEntity}
	 */
	Page<ProductEntity> getPageableProductEntityByFilter(final String sortOrder, final SortOptionEnum sortOption,
			final List<String> limitToList, final Pageable pageable);
}
