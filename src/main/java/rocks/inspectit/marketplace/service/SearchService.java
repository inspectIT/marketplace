package rocks.inspectit.marketplace.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import rocks.inspectit.marketplace.dao.repository.jpa.entity.ProductEntity;

/**
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.6-SNAPSHOT
 */
public interface SearchService {
	Page<ProductEntity> getAllProductEntitiesBySearchTerm(final String searchTerm, final Pageable pageable);
}
