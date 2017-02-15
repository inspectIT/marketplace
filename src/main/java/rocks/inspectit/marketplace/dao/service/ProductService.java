package rocks.inspectit.marketplace.dao.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.6-SNAPSHOT
 */
public interface ProductService {

	List<ProductEntity> getTop20MostDownloadedProducts();

	List<ProductEntity> getTop20BestRatedProducts();

	List<ProductEntity> getTop20MostRecentUploadedProducts();

	Page<ProductEntity> get20ProductsBaTagNameOrderedByDateAndDownloads(final String tagName);

	Page<ProductEntity> getAllProductEntitiesBySearchTerm(final String searchTerm, final Pageable pageable);
}
