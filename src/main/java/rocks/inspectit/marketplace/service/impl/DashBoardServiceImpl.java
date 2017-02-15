package rocks.inspectit.marketplace.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;
import rocks.inspectit.marketplace.dao.service.ProductService;
import rocks.inspectit.marketplace.service.DashBoardService;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.3-SNAPSHOT
 */
@Service
public class DashBoardServiceImpl implements DashBoardService {
	private static final Logger LOG = LoggerFactory.getLogger(DashBoardServiceImpl.class);

	private final ProductService productService;

	/**
	 * use constructor injection.
	 *
	 * @param productService {@link ProductService}
	 * @since 1.0.3-SNAPSHOT
	 */
	@Autowired
	public DashBoardServiceImpl(final ProductService productService) {
		this.productService = productService;
	}

	/**
	 * FIXME
	 * there are currently different methods returning exactly what the front-end expects, due to missing entities. please implement entities and rewrite functions properly
	 * maybe use pagination instead of "topX"
	 * <p>
	 * <p>
	 * Since this function returns products for the dashboard it's  sufficient
	 * Check value of type.
	 * If download: return top 20 of most downloaded products.
	 * If rating: return top 20 of highest rated products.
	 * If recent: return top 20 of newest uploaded products.
	 * Else: return 20 products with corresponding tag ordered by creation date and # of downloads.
	 *
	 * Ignore "position literals first in comparison" because "yoda-notation" decrease logic and readability.
	 *
	 * @param type indicates if products should be ordered by "downloads", "ratings", "creation_date" or "tag_name"
	 * @return {@link List} of {@link ProductEntity}
	 */
	@SuppressWarnings("PMD.PositionLiteralsFirstInCaseInsensitiveComparisons")
	@Override
	public List<ProductEntity> getSimpleDashboardOverviewByType(final String type) {
		final List<ProductEntity> returnList;
		if (type.equalsIgnoreCase("download")) {
			returnList = this.productService.getTop20MostDownloadedProducts();
		} else if (type.equalsIgnoreCase("rated")) {
			returnList = this.productService.getTop20BestRatedProducts();
		} else if (type.equalsIgnoreCase("recent")) {
			returnList = this.productService.getTop20MostRecentUploadedProducts();
		} else {
			LOG.info(String.format("Type \"%s\" didn't match \"download\". \"rating\" or \"recent\". Use fallback strategy and select by TAG name.", type));
			returnList = this.productService.get20ProductsBaTagNameOrderedByDateAndDownloads(type).getContent();
		}

		return returnList;
	}
}
